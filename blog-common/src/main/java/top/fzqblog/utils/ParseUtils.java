package top.fzqblog.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.util.HtmlUtils;
import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.po.model.Knowledge;




public class ParseUtils {
	
	public static void beginParse() throws IOException{
		for (int i = 1; i < 2; i++) {
			String url = "http://www.ulewo.com/knowledge/categoryId/10050?pageNo="
					+ i;
			parse(url);
		}

	}
	
	public static void parse(String url) throws IOException {
		Document doc = Jsoup
				.connect(url)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
				.get();
		Elements links = doc.select("a.title");
		Knowledge knowledge = parsecontent(links);
	}
	
	public static Knowledge parsecontent(Elements links) throws IOException{
		for (int i = 0; i < links.size(); i++) {
			String theUrl = links.get(i).attr("abs:href");
			Knowledge knowledge = new Knowledge();
			//System.out.println(theUrl);
			Document document = Jsoup
					.connect(theUrl)
					.userAgent(
							"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
					.get();
			String title = document.select("div.topic-title").text();
			knowledge.setTitle(title);
			Element content = document.select("div.topic-detail").get(0);
			pareseImage(content, knowledge);
			knowledge.setpCategoryId(0);
			knowledge.setCategoryId(1);
			knowledge.setUserName("JAVABLOG小编");
			knowledge.setUserId(10000);
			knowledge.setUserIcon("user_icon/6.jpg");
			knowledge.setCreateTime(new Date());
			String c= knowledge.getContent();
			String summary = StringUtils.cleanHtmlTag(HtmlUtils.htmlUnescape(c));
			knowledge.setSummary(summary);
			return knowledge;
//			System.out.println("文章标题" + title + "-------------:");
//			System.out.println("文章内容：=============================" + content);
		}
		return null;
	}
	
	public static void pareseImage(Element content, Knowledge knowledge) throws IOException{
		Elements Image = content.select("img[data-original]");
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < Image.size(); i++){
			String image = Image.get(i).attr("data-original");
			if(image.contains("grey.gif")){
				continue;
			}
			String suffix = image.substring(image.lastIndexOf(".") + 1);
			String current = String.valueOf(System.currentTimeMillis());
			String fileName = current + "." + suffix;
			String saveDir = DateUtil.format(new Date(), DateTimePatternEnum.YYYYMM.getPattern());
			Response resultImageResponse = Jsoup.connect(image).userAgent(
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31").ignoreContentType(true).execute();
			
			File f = new File("G:/picture/" + saveDir);
			if(!f.exists()){
				f.mkdirs();
			}
			saveDir = "G:/picture/" + saveDir;
			String realpath = saveDir + "/" + fileName;
			if(i < 3){
				sb.append(realpath + "|");
			}
			knowledge.setTopicImage(sb.toString());
			Element c = content.select("img[data-original]").get(i).attr("src", realpath).attr("data-original", "");
			//System.out.println(content);
			knowledge.setContent(content.toString());
			File file = new File(saveDir + "/" + fileName);
			FileOutputStream out = (new FileOutputStream(file));
			out.write(resultImageResponse.bodyAsBytes());           
			out.close();
			//System.out.println("文章图片：=============================" + Image.get(i1).attr("data-original"));
		}
	}
	
}

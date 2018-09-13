package top.fzqblog.test.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.HtmlUtils;
import org.testng.annotations.Test;

import bsh.This;
import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.po.enums.StatusEnum;
import top.fzqblog.po.model.Attachment;
import top.fzqblog.po.model.Knowledge;
import top.fzqblog.po.query.KnowledgeQuery;
import top.fzqblog.service.CommentService;
import top.fzqblog.service.KnowledgeService;
import top.fzqblog.utils.DateUtil;
import top.fzqblog.utils.PageResult;
import top.fzqblog.utils.ParseUtils;
import top.fzqblog.utils.StringUtils;


@ContextConfiguration(locations = {"classpath:spring.xml"})
public class KnowledgeServiceTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private KnowledgeService knowledgeService;
	
	@Test
	public void testAddKnowledge(){
		
	}
	
	@Test
	public void testFindKnowledgeByPage(){
		PageResult<Knowledge> pageResult = this.knowledgeService.findKnowledgeByPage(new KnowledgeQuery());
		System.out.println(pageResult);
	}
	
	
	@Test
	public void testAddComment() throws IOException, BussinessException{
		beginParse();
	}
	
	public void beginParse() throws IOException, BussinessException{
		for (int i = 4; i < 10; i++) {
			String url = "http://www.ulewo.com/knowledge/categoryId/10050?pageNo="
					+ i;
			parse(url);
		}

	}
	
	public void parse(String url) throws IOException, BussinessException {
		Document doc = Jsoup
				.connect(url)
				.userAgent(
						"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
				.get();
		Elements links = doc.select("a.title");
		parsecontent(links);
	}
	
	public  void parsecontent(Elements links) throws IOException, BussinessException{
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
			knowledge.setContent(content.html().toString());
			pareseImage(content, knowledge);
			knowledge.setpCategoryId(1);
			knowledge.setCategoryId(6);
			knowledge.setUserName("JAVABLOG小编");
			knowledge.setUserId(10000);
			knowledge.setUserIcon("user_icon/10000.jpg");
			knowledge.setCreateTime(new Date());
			knowledge.setStatus(StatusEnum.AUDIT);
			String c= knowledge.getContent();
			this.knowledgeService.addKnowledge(knowledge,new Attachment());
//			System.out.println("文章标题" + title + "-------------:");
//			System.out.println("文章内容：=============================" + content);
		}

	}
	
	public void pareseImage(Element content, Knowledge knowledge) throws IOException{
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
			String des = "G:/development/Web深入/FzqBlog/blog-web/src/main/webapp/upload/" + saveDir;
			File f = new File("G:/development/Web深入/FzqBlog/blog-web/src/main/webapp/upload/" + saveDir);
			if(!f.exists()){
				f.mkdirs();
			}
			saveDir = "http://www.fzqblog.top/upload/" + saveDir;
			String realpath = saveDir + "/" + fileName;
			if(i < 3){
				sb.append(realpath + "|");
			}
			knowledge.setTopicImage(sb.toString());
			Element c = content.select("img[data-original]").get(i).attr("src", realpath).attr("data-original", "");
			//System.out.println(content);
			knowledge.setContent(content.html().toString());
			File file = new File(des + "/" + fileName);
			FileOutputStream out = (new FileOutputStream(file));
			out.write(resultImageResponse.bodyAsBytes());           
			out.close();
			//System.out.println("文章图片：=============================" + Image.get(i1).attr("data-original"));
		}
	}
	
}

package top.fzqblog.test.service;

import java.util.Date;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import bsh.This;
import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.model.Comment;
import top.fzqblog.po.query.CommentQuery;
import top.fzqblog.service.CommentService;


@ContextConfiguration(locations = {"classpath:spring.xml"})
public class CommentServiceTest extends AbstractTestNGSpringContextTests{
	@Autowired
	private CommentService commentService;
	
	@Test
	public void testAddComment(){
		for(int i = 0; i < 10; i++){
			addCommentBatch();
		}
	}
	
	@Test
	public void testLoadComment(){
		System.out.println(this.commentService.findCommentByPage(new CommentQuery()));
	}
	
	public void addCommentBatch(){
		Comment comment = new Comment();
		comment.setContent("哈哈哈");
		comment.setPid(1);
		comment.setCreateTime(new Date());
		comment.setUserId(10045);
		comment.setArticleId(46);
		comment.setUserName("fan_抽离");
		comment.setArticleType(ArticleType.TOPIC);
		comment.setUserIcon("user_icon/4.jpg");
		try {
			this.commentService.addComment(comment);
		} catch (BussinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

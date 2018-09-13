package top.fzqblog.test.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import top.fzqblog.po.enums.ArticleType;
import top.fzqblog.po.enums.DateTimePatternEnum;
import top.fzqblog.po.enums.MessageType;
import top.fzqblog.po.model.MessageParams;
import top.fzqblog.po.query.CategoryQuery;
import top.fzqblog.service.CategoryService;
import top.fzqblog.service.MessageService;
import top.fzqblog.service.UserService;
import top.fzqblog.utils.DateUtil;

@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MessageServiceTest extends AbstractTestNGSpringContextTests{
	
	@Autowired
	private MessageService messageService;
	
	@Test
	public void testCreateMessage(){
		MessageParams messageParams = new MessageParams();
		messageParams.setArticleId(136);
		messageParams.setArticleType(ArticleType.BLOG);
		messageParams.setArticleUserId(10034);
		messageParams.setMessageType(MessageType.COMMENT_MESSAGE);
		messageParams.setSendUserName("抽离");
		messageParams.setSendUserId(10033);
		Set<Integer> userSets = new HashSet<Integer>();
		userSets.add(10034);
		userSets.add(10035);
		userSets.add(10036);
		userSets.add(10037);
		userSets.add(10038);
		userSets.add(10039);
		userSets.add(10040);
		messageParams.setReceiveUserIds(userSets);
		messageParams.setCommentId(1);
		messageParams.setPageNum(1);
		messageService.createMessage(messageParams);
	}
}

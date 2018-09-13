package top.fzqblog.service;



import top.fzqblog.exception.BussinessException;
import top.fzqblog.po.model.Comment;
import top.fzqblog.po.query.CommentQuery;
import top.fzqblog.utils.PageResult;

public interface CommentService {
	
	public PageResult<Comment> findCommentByPage(CommentQuery commentQuery);
	
	public Comment getCommentById(Integer commentId);
	
	public void addComment(Comment comment) throws BussinessException;
	
}

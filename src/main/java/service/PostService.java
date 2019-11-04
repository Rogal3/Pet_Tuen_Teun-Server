package service;

import java.util.ArrayList;
import java.util.List;

import manager.PostManager;
import manager.CommentManager;
import model.Post;
import model.Comment;

public class PostService {
	private PostManager PostManager;
	private CommentManager commentManager;
	
	public PostService() {
		super();
		this.PostManager = new PostManager();
		this.commentManager = new CommentManager();
	}
	
	public PostService(PostManager postManager, CommentManager commentManager) {
		super();
		this.PostManager = postManager;
		this.commentManager = commentManager;
	}
	
	public PostManager getPostManager() {
		return PostManager;
	}
	
	public void setPostManager(PostManager postManager) {
		this.PostManager = postManager;
	}
	
	public CommentManager getCommentManager() {
		return commentManager;
	}
	
	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}
	
	public List<Post> searchPostByType(String type){
		return this.PostManager.searchByType(type);
	}
	
	public ArrayList<Post> searchPostByWriter(String writer){
		return this.searchPostByWriter(writer);
	}
	
	public List<Post> searchPostByWrterOfType(String writer,String type){
		return this.PostManager.searchByWriter(writer);
	}
	public List<Post> searchPostByTitle(String title,String type){
		List<Post> list = null;
		if(type.equals("total")) {
			list = this.PostManager.searchByTitle(title);
		}else {
			list = this.PostManager.searchByTypeAndTitle(type, title);
		}
		return list;
	}
	
	public List<String> searchBoardType(){
		return this.PostManager.getTypes();
	}
	
	public byte modifyBoard(String id,Post board) {
		return this.PostManager.addPost(board);
	}
	
	public byte deleteBoard(String id) {
		return this.PostManager.deletePost(id);
	}
	
	public byte addBoard(Post post) {
		return this.PostManager.addPost(post);
	}
	
	public byte addComment(String boardID,Comment comment) {
		return this.commentManager.addComment(boardID, comment);
	}
	
	public byte deleteComment(String boardID,Comment comment) {
		return this.commentManager.deleteComment(boardID,comment.getWriteTime());
	}
	
	public byte modifyComment(String boardID,Comment comment){
		return this.commentManager.modifyComment(boardID, comment);
	}
}

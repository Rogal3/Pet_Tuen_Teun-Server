package service;

import java.util.ArrayList;

import manager.PostManager;
import manager.CommentManager;
import model.Post;
import model.Comment;

public class PostService {
	private PostManager PostManager;
	private CommentManager commentManager;
	public PostService(PostManager postManager, CommentManager commentManager) {
		super();
		this.PostManager = postManager;
		this.commentManager = commentManager;
	}
	public PostManager getBoardManager() {
		return PostManager;
	}
	public void setBoardManager(PostManager postManager) {
		this.PostManager = postManager;
	}
	public CommentManager getCommentManager() {
		return commentManager;
	}
	public void setCommentManager(CommentManager commentManager) {
		this.commentManager = commentManager;
	}
	public ArrayList<Post> searchBoardByType(String type){
		return this.PostManager.searchByType(type);
	}
	public ArrayList<Post> searchBoardByWriter(String writer){
		return this.searchBoardByWriter(writer);
	}
	/**
	 * 타입과 작성자로 검색인데 일단 보류!
	 * @param writer 작성자
	 * @param type 타입
	 * @return
	 */
	public ArrayList<Post> searchBoardByWrterOfType(String writer,String type){
		return this.PostManager.searchBoardByWriter(writer);
	}
	public ArrayList<Post> searchBoardByTitle(String title,String type){
		ArrayList<Post> emp=new ArrayList<Post>();
		if("total".equals(type)) {
			ArrayList<String> types=searchBoardType();
			
			for(int i=0;i<types.size();++i) {
				ArrayList<Post> postList=this.PostManager.searchByType(types.get(i));
				for(int j=0;j<postList.size();++j) {
					emp.add(postList.get(j));
				}
			}
		}else {
			ArrayList<Post> postList=this.PostManager.searchByType(type);
			for(int i=0;i<postList.size();++i) {
				emp.add(postList.get(i));
			}
		}
		return emp;
	}
	public ArrayList<String> searchBoardType(){
		return this.PostManager.searchTypes();
	}
	public byte modifyBoard(String id,Post board) {
		return this.PostManager.addBoard(board);
	}
	public byte deleteBoard(String id) {
		return this.PostManager.deleteBoard(id);
	}
	public byte addBoard(Post post) {
		return this.PostManager.addBoard(post);
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

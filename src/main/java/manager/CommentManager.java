package manager;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import DB.CommentDAO;
import model.Comment;

public class CommentManager {
	/**
	 * key=postID
	 */
	private HashMap<String, ArrayList<Comment>> comments;
	// TODO
	//@Autowired
	private CommentDAO commentDAO;
	
	public CommentManager() {
		super();
		commentDAO = new CommentDAO();
		this.comments=commentDAO.load();
	}
	public CommentManager(HashMap<String, ArrayList<Comment>> comments) {
		super();
		this.comments = comments;
	}
	public HashMap<String, ArrayList<Comment>> getComments() {
		return comments;
	}
	public void setComments(HashMap<String, ArrayList<Comment>> comments) {
		this.comments = comments;
	}
	public ArrayList<Comment> searchCommentList(String postID){
		return this.comments.get(postID);
	}
	public byte modifyComment(String postID,Comment comment) {
		if(searchCommentList(postID)==null)return 0;
		int i=0;
		
		ArrayList<Comment> list=this.comments.get(postID);
		for(int j=0;j<list.size();++j) {
			if(list.get(j).getWriteTime().equals(comment.getWriteTime())) {
				i=j;
				list.remove(i);
				break;
			}
		}
		list.add(i, comment);
		
		return 1;
	}
	
	public byte deleteComment(String postID,String writeTime) {
		if(searchCommentList(postID)==null)return 0;
		
		ArrayList<Comment> list=this.comments.get(postID);
		for(int j=0;j<list.size();++j) {
			if(list.get(j).getWriteTime().equals(writeTime)) {
			
				list.remove(j);
				break;
			}
		}
		
		return 1;
	}
	public byte addComment(String postID,Comment comment) {
		if(searchCommentList(postID)!=null)return 0;
		
		this.comments.get(postID).add(comment);
		return 1;
	}
}

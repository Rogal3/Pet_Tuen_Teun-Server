package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import DB.CommentDAO;
import model.Comment;

public class CommentManager {
	/**
	 * key=PostID
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

	public Comment get(String postId, String num) {
		int idx = Integer.parseInt(num);
		Comment comment = comments.get(postId).get(idx);
		return comment;
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
	
	public byte addComment(String postId, String writer, String content, String writeTime) {
		ArrayList<Comment> list = comments.get(postId);
		if (list == null) {
			list = new ArrayList<Comment>();
			comments.put(postId, list);
		}
		Comment comment = new Comment(writer, content, writeTime);
		list.add(comment);
		return 1;
	}
	
	public byte addComment(String postID,Comment comment) {
		if(searchCommentList(postID)!=null)return 0;
		
		this.comments.get(postID).add(comment);
		return 1;
	}
}

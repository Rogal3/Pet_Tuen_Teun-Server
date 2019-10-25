package manager;

import java.util.ArrayList;
import java.util.HashMap;

import model.Comment;

public class CommentManager {
	private HashMap<String, ArrayList<Comment>> comments;
	
	public CommentManager() {
		super();
		this.comments=new HashMap<String,ArrayList<Comment>>();
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
	public ArrayList<Comment> searchCommentList(String boardID){
		return this.comments.get(boardID);
	}
	public byte modifyComment(String boardID,Comment comment) {
		if(searchCommentList(boardID)==null)return 0;
		int i=0;
		
		ArrayList<Comment> list=this.comments.get(boardID);
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
	
	public byte deleteComment(String boardID,String writeTime) {
		if(searchCommentList(boardID)==null)return 0;
		
		ArrayList<Comment> list=this.comments.get(boardID);
		for(int j=0;j<list.size();++j) {
			if(list.get(j).getWriteTime().equals(writeTime)) {
			
				list.remove(j);
				break;
			}
		}
		
		return 1;
	}
	public byte addComment(String boardID,Comment comment) {
		if(searchCommentList(boardID)!=null)return 0;
		
		this.comments.get(boardID).add(comment);
		return 1;
	}
}

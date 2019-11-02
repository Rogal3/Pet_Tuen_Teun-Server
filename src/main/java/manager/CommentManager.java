package manager;

import java.util.ArrayList;
import java.util.HashMap;

import model.Comment;

public class CommentManager {
	/**
	 * key=boardID
	 */
	private HashMap<String, ArrayList<Comment>> comments;
	
	public CommentManager() {
		super();
		this.comments=new HashMap<String,ArrayList<Comment>>();
		
		ArrayList<Comment> cmt=new ArrayList<Comment>();
		cmt.add(new Comment("aaa","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("ccc","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("ddd","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("bbb","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		
		ArrayList<Comment> cmt2=new ArrayList<Comment>();
		cmt.add(new Comment("aaa","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("ccc","�ㅋㅋㅋㅋㅋㅋㅋㅋ�","19/10/30"));
		cmt.add(new Comment("bbb","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		
		ArrayList<Comment> cmt3=new ArrayList<Comment>();
		cmt.add(new Comment("aaa","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("bbb","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		
		ArrayList<Comment> cmt4=new ArrayList<Comment>();
		cmt.add(new Comment("aaa","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("ccc","ㅋㅋㅋㅋㅋㅋㅋㅋ�","19/10/30"));
		cmt.add(new Comment("ddd","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("bbb","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		
		ArrayList<Comment> cm5=new ArrayList<Comment>();
		cmt.add(new Comment("aaa","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("ccc","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		cmt.add(new Comment("ddd","ㅋㅋㅋㅋㅋㅋㅋㅋ","19/10/30"));
		
		this.comments.put("f1",cmt);
		this.comments.put("n2",cmt2);
		this.comments.put("m3",cmt3);
		this.comments.put("m1",cmt4);
		this.comments.put("t1",cm5);
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

package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import model.Post;

public class PostManager {
	private HashMap<String,Post> posts;
	private HashMap<String,ArrayList<Post>> boardTypeList;
	
	public PostManager() {
		super();
		this.posts=new HashMap<String,Post>();
		this.boardTypeList=new HashMap<String,ArrayList<Post>>();
	}
	public PostManager(HashMap<String, Post> posts, HashMap<String, ArrayList<Post>> boardTypeList) {
		super();
		this.posts = posts;
		this.boardTypeList = boardTypeList;
	}
	public HashMap<String, Post> getPosts() {
		return posts;
	}
	public void setBoards(HashMap<String, Post> posts) {
		this.posts = posts;
	}
	public HashMap<String, ArrayList<Post>> getBoardTypeList() {
		return boardTypeList;
	}
	public void setBoardTypeList(HashMap<String, ArrayList<Post>> boardTypeList) {
		this.boardTypeList = boardTypeList;
	}
	public ArrayList<Post> searchByType(String type){
		if(this.boardTypeList.get(type)==null)return null;
		return this.boardTypeList.get(type);
	}
	public Post searchById(String id) {
		return this.posts.get(id);
	}
	public HashMap<String,ArrayList<Post>> searchByTitle(String title){
		HashMap<String,ArrayList<Post>> emp=new HashMap<String,ArrayList<Post>>();
		

		Iterator<String> iter=this.boardTypeList.keySet().iterator();
		
		while(iter.hasNext()) {
			ArrayList<Post> temp=new ArrayList<Post>();
			String key=iter.next();
			ArrayList<Post> putting=this.boardTypeList.get(key);
			
			for(int i=0;i<putting.size();++i){
				if(putting.get(i).getTitle().contains(title)) {
					temp.add(putting.get(i));
				}
			}
			emp.put(key,temp);
		}
		return emp;
	}
	public ArrayList<Post> searchBoardByWriter(String writer){
		Iterator<String> iter=this.boardTypeList.keySet().iterator();
		ArrayList<Post> emp=new ArrayList<Post>();
		while(iter.hasNext()) {
			Post temp=this.posts.get(iter.next());
			
				if(temp.getWriter().equals(writer)) {
					emp.add(temp);
				}
		}
		return emp;
	}
	public ArrayList<String> searchTypes(){
		ArrayList<String> emp=new ArrayList<String>();
		Iterator<String> iter=this.boardTypeList.keySet().iterator();
		while(iter.hasNext()) {
			emp.add(iter.next());
		}
		return emp;
	}
	public byte modifyPost(String id, Post post) {
		if(searchById(id)==null)return 0;
		
		this.posts.put(id, post);
		
		return 1;
	}
	public byte deletePost(String id) {
		if(searchById(id)==null)return 0;
		this.posts.remove(id);
		return 1;
	}
	public byte addBoard(Post board) {
		ArrayList<Post> ary=this.boardTypeList.get(board.getType());
		
		String id=board.getType().charAt(0)+""+ary.size();
		board.setBoardID(id);
		
		this.posts.put(board.getId(),board);
		this.boardTypeList.get(board.getType()).add(board);
		return 1;
	}
	
}

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
		
		ArrayList<Post> f1=new ArrayList<Post>();
		f1.add(new Post("f1","favorite","title","aaa","asdf","19.10.30"));
		f1.add(new Post("f2","favorite","title1","bbb","asdf","19.10.30"));
		f1.add(new Post("f3","favorite","title2","ccc","asdf","19.10.30"));
		f1.add(new Post("f4","favorite","title3","ccc","asdf","19.10.30"));
		f1.add(new Post("f5","favorite","title4","aaa","asdf","19.10.30"));
		
		ArrayList<Post> n1=new ArrayList<Post>();
		f1.add(new Post("n1","new","title","aaa","asdf","19.10.30"));
		f1.add(new Post("n2","new","title","bbb","asdf","19.10.30"));
		f1.add(new Post("n3","new","title","ccc","asdf","19.10.30"));
		f1.add(new Post("n4","new","title","ccc","asdf","19.10.30"));
		f1.add(new Post("n5","new","title","aaa","asdf","19.10.30"));
		
		ArrayList<Post> m1=new ArrayList<Post>();
		f1.add(new Post("m1","my","title","aaa","asdf","19.10.30"));
		f1.add(new Post("m2","my","title","bbb","asdf","19.10.30"));
		f1.add(new Post("m3","my","title","ccc","asdf","19.10.30"));
		f1.add(new Post("m4","my","title","ccc","asdf","19.10.30"));
		f1.add(new Post("m5","my","title","aaa","asdf","19.10.30"));
		
		ArrayList<Post> t1=new ArrayList<Post>();
		f1.add(new Post("t1","tip","title","ddd","asdf","19.10.30"));
		f1.add(new Post("t2","tip","title","ddd","asdf","19.10.30"));
		f1.add(new Post("t3","tip","title","ddd","asdf","19.10.30"));
		f1.add(new Post("t4","tip","title","ccc","asdf","19.10.30"));
		f1.add(new Post("t5","tip","title","aaa","asdf","19.10.30"));
		
		for(int i=0;i<5;i++) {
			this.posts.put(f1.get(i).getId(),f1.get(i));
			this.posts.put(n1.get(i).getId(),n1.get(i));
			this.posts.put(m1.get(i).getId(),m1.get(i));
			this.posts.put(t1.get(i).getId(),t1.get(i));
		}
		this.boardTypeList.put("favorite", f1);
		this.boardTypeList.put("new", n1);
		this.boardTypeList.put("my", m1);
		this.boardTypeList.put("tip", t1);
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
	public byte modifyBoard(String id,Post post) {
		if(searchById(id)==null)return 0;
		
		this.posts.put(id, post);
		
		return 1;
	}
	public byte deleteBoard(String id) {
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

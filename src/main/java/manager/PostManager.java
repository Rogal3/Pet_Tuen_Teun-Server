package manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import DB.PostDAO;
import model.Post;

public class PostManager {
	private List<Post> posts;
	// TODO
	//@Autowired
	private PostDAO postDAO;
	
	public PostManager() {
		super();
		postDAO = new PostDAO();
		this.posts=postDAO.load();
	}
	public PostManager(List<Post> posts) {
		super();
		this.posts = posts;
	}

	public List<Post> getPosts() {
		return posts;
	}
	
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	
	private static List<Post> searchByType(List<Post> posts, String type) {
		List<Post> list = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getType().equals(type)) {
				list.add(post);
			}
		}
		return list;
	}
	
	public List<Post> searchByType(String type) {
		return searchByType(posts, type);
	}
	
	public Post searchById(String id) {
		for (Post post : posts) {
			if (post.getId().equals(id)) {
				return post;
			}
		}
		return null;
	}
	
	private static List<Post> searchByTitle(List<Post> posts, String title) {
		List<Post> list = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getTitle().contains(title)) {
				list.add(post);
			}
		}
		return list;
	}
	
	public List<Post> searchByTitle(String title) {
		return searchByTitle(posts, title);
	}
	
	public List<Post> searchByWriter(String writer) {
		List<Post> list = new ArrayList<Post>();
		for (Post post : posts) {
			if (post.getWriter().contains(writer)) {
				list.add(post);
			}
		}
		return list;
	}
	
	public List<Post> searchByTypeAndTitle(String type, String title) {
		return searchByType(searchByTitle(posts, title), type);
	}
	
	public List<String> getTypes() {
		Set<String> set = new HashSet<String>();
		for (Post post : posts) {
			set.add(post.getType());
		}
		String[] setToArray = (String[]) set.toArray();
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < setToArray.length; ++i) {
			list.add(setToArray[i]);
		}
		return list;
	}
	
	public byte modifyPost(String id, Post post) {
		for (Post oldPost : posts) {
			if (oldPost.getId().equals(id)) {
				oldPost.setBoardID(post.getId());
				oldPost.setType(post.getType());
				oldPost.setTitle(post.getTitle());
				oldPost.setWriter(post.getWriter());
				oldPost.setContent(post.getContent());
				oldPost.setWriteTime(post.getWriteTime());
				return 1;
			}
		}
		return 0;
	}
	
	public byte deletePost(String id) {
		int pos = -1;
		for (int i = 0; i < posts.size(); ++i) {
			if (posts.get(i).getId().equals(id)) {
				pos = i;
				break;
			}
		}
		if (pos != -1) {
			posts.remove(pos);
			return 1;
		}
		return 0;
	}
	
	public String getNextId() {
		if (posts.isEmpty()) {
			return "p000001";
		} else {
			String lastId = posts.get(posts.size() - 1).getId();
			int i_id = Integer.parseInt(lastId.substring(1)) + 1;
			return Integer.toString(i_id);
		}
	}
	
	public byte addPost(String type, String title, String writer, String content, String writeTime) {
		Post post = new Post(getNextId(), type, title, writer, content, writeTime);
		posts.add(post);
		return 1;
	}
	
	public byte addPost(Post post) {
		posts.add(post);
		return 1;
	}
	
}

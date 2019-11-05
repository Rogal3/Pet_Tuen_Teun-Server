package DB;

import java.io.Serializable;

public class CommentDTO implements Serializable {
	private String id;
	private String writer;
	private String post;
	private String content;
	private String writeTime;
	
	public CommentDTO() {
		super();
	}
	
	public CommentDTO(String id, String writer, String post, String content, String writeTime) {
		super();
		this.id = id;
		this.writer = writer;
		this.post = post;
		this.content = content;
		this.writeTime = writeTime;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriteTime() {
		return writeTime;
	}
	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}


	@Override
	public String toString() {
		return "CommentDTO [id=" + id + ", writer=" + writer + ", post=" + post + ", content=" + content
				+ ", writeTime=" + writeTime + "]";
	}

	
}

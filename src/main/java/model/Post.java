package model;

public class Post {
	private String id;
	private String type;
	private String title;
	private String writer;
	private String content;
	private String writeTime;
	
	public Post() {
		super();
	}
	public Post(String id, String type, String title, String writer, String content, String writeTime) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.writeTime = writeTime;
	}
	public String getId() {
		return id;
	}
	public void setBoardID(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
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
	public byte modifyType(String type) {
		this.type=type;
		return 1;
	}
	public byte modifyTitle(String title) {
		this.title=title;
		return 1;
	}
	public byte modifyContent(String content) {
		this.content=content;
		return 1;
	}
	public byte modifyWriteTime(String time) {
		this.writeTime=time;
		return 1;
	}
	@Override
	public String toString() {
		return "Post [id=" + id + ", type=" + type + ", title=" + title + ", writer=" + writer + ", content=" + content
				+ ", writeTime=" + writeTime + "]";
	}
}

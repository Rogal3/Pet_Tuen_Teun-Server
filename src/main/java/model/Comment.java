package model;

public class Comment {
	private String writer;
	private String content;
	private String writeTime;
	
	public Comment() {
		super();
	}
	public Comment(String writer, String content, String writeTime) {
		super();
		this.writer = writer;
		this.content = content;
		this.writeTime = writeTime;
	}
	public String getWrite() {
		return writer;
	}
	public void setWrite(String write) {
		this.writer = write;
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
	public byte modifyContent(String content) {
		this.content=content;
		return 1;
	}
	
}

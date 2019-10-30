package DB;

import java.io.Serializable;

public class CommentDTO implements Serializable {
	private String writer;
	private String content;
	private String writeTime;
	public CommentDTO() {
		super();
	}
	public CommentDTO(String writer, String content, String writeTime) {
		super();
		this.writer = writer;
		this.content = content;
		this.writeTime = writeTime;
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
	@Override
	public String toString() {
		return "CommentDTO [writer=" + writer + ", content=" + content + ", writeTime=" + writeTime + "]";
	}
}

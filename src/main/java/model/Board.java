package model;

public class Board {
	private String boardID;
	private String type;
	private String title;
	private String writer;
	private String content;
	private String wirteTime;
	
	public Board() {
		super();
	}
	public Board(String boardID, String type, String title, String writer, String content, String wirteTime) {
		super();
		this.boardID = boardID;
		this.type = type;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.wirteTime = wirteTime;
	}
	public String getBoardID() {
		return boardID;
	}
	public void setBoardID(String boardID) {
		this.boardID = boardID;
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
	public String getWirteTime() {
		return wirteTime;
	}
	public void setWirteTime(String wirteTime) {
		this.wirteTime = wirteTime;
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
		this.wirteTime=time;
		return 1;
	}
}

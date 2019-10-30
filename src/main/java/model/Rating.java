package model;

public class Rating {
	private String id;
	private String writer;
	private String hospital;
	private int scale;
	private String content;
	
	public Rating() {
		super();
	}

	public Rating(String id, String writer, String hospital, int scale, String content) {
		super();
		this.writer = writer;
		this.hospital = hospital;
		this.scale = scale;
		this.content = content;
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

	public String getHospital() {
		return hospital;
	}

	public void setHospital(String hospital) {
		this.hospital = hospital;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public byte modifyScale(int scale) {
		this.scale = scale;
		return 1;
	}
	public byte modifyContent(String content) {
		this.content = content;
		return 1;
	}
}

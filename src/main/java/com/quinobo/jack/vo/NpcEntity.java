package com.quinobo.jack.vo;

public class NpcEntity {
	private String name;
	private String pic_Link;
	private String memo;
	private String writer;
	private String wdate;
	private String npno;
	
	public NpcEntity() {
		super();
	}
	
	public NpcEntity(String name, String pic_Link, String memo, String writer, String wdate, String npno) {
		super();
		this.name = name;
		this.pic_Link = pic_Link;
		this.memo = memo;
		this.writer = writer;
		this.wdate = wdate;
		this.npno = npno;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic_Link() {
		return pic_Link;
	}
	public void setPic_Link(String pic_Link) {
		this.pic_Link = pic_Link;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWdate() {
		return wdate;
	}
	public void setWdate(String wdate) {
		this.wdate = wdate;
	}
	public String getNpno() {
		return npno;
	}
	public void setNpno(String npno) {
		this.npno = npno;
	}
	
	
}

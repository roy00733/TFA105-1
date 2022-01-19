package com.post.model;

import java.sql.Date;
import java.sql.Timestamp;

public class PostVO {
	private Integer postid;
	private Integer memberid;
	private String posttitle;
	private Integer posttypeid; 
	private String postcontent; 
	private Timestamp posttime;
	private Integer poststatus;
	private Integer postrewardpoints;
	
	public Integer getPostid() {
		return postid;
	}
	public void setPostid(Integer postid) {
		this.postid = postid;
	}
	public Integer getMemberid() {
		return memberid;
	}
	public void setMemberid(Integer memberid) {
		this.memberid = memberid;
	}
	public String getPosttitle() {
		return posttitle;
	}
	public void setPosttitle(String posttitle) {
		this.posttitle = posttitle;
	}
	public Integer getPosttypeid() {
		return posttypeid;
	}
	public void setPosttypeid(Integer posttypeid) {
		this.posttypeid = posttypeid;
	}
	public String getPostcontent() {
		return postcontent;
	}
	public void setPostcontent(String postcontent) {
		this.postcontent = postcontent;
	}
	public Timestamp getPosttime() {
		return posttime;
	}
	public void setPosttime(Timestamp posttime) {
		this.posttime = posttime;
	}
	public Integer getPoststatus() {
		return poststatus;
	}
	public void setPoststatus(Integer poststatus) {
		this.poststatus = poststatus;
	}
	public Integer getPostrewardpoints() {
		return postrewardpoints;
	}
	public void setPostrewardpoints(Integer postrewardpoints) {
		this.postrewardpoints = postrewardpoints;
	}
	
	
}

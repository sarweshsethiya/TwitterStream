package com.hsbc.twitter.TwitterStream;

import java.io.Serializable;

public class TwitterStreamBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String screenName;
	private long Id;
	private String content;
	private String inReplyUserName;
	private String retweetUserName;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInReplyUserName() {
		return inReplyUserName;
	}
	public void setInReplyUserName(String inReplyUserName) {
		this.inReplyUserName = inReplyUserName;
	}
	public String getRetweetUserName() {
		return retweetUserName;
	}
	public void setRetweetUserName(String retweetUserName) {
		this.retweetUserName = retweetUserName;
	}
	
	

}

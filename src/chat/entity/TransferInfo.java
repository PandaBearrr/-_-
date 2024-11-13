package chat.entity;

import java.io.Serializable;

public class TransferInfo implements Serializable{
	private static final long serialVersionUID = -436846513104870438L;
	
	private String userName;
	private String passWord;
	private boolean LogSuccessFlag=false;
	//聊天内容
	private String content;
	private String path;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	//发送人
	private String sender;
	
	//接收人
	private String reciver;
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReciver() {
		return reciver;
	}
	public void setReciver(String reciver) {
		this.reciver = reciver;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	private String notice;
	//消息类型枚举
	private ChatStatus statusEnum;
	//在线的用户列表
	private String[] userOnlineArray;
	
	public String[] getUserOnlineArray() {
		return userOnlineArray;
	}
	public void setUserOnlineArray(String[] userOnlineArray) {
		this.userOnlineArray = userOnlineArray;
	}
	public ChatStatus getStatusEnum() {
		return statusEnum;
	}
	public void setStatusEnum(ChatStatus statusEnum) {
		this.statusEnum = statusEnum;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public boolean getLogSuccessFlag() {
		return LogSuccessFlag;
	}
	public void setLogSuccessFlag(boolean logSuccessFlag) {
		LogSuccessFlag = logSuccessFlag;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	
	
}

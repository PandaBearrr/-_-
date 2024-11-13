package chat.entity;

/*
 * 消息类型枚举
 */

public enum ChatStatus {
	LOGIN(1,"登陆消息"),NOTICE(2,"系统消息"),CHAT(3,"聊天消息"),DO(4,"抖动消息"), ULIST(5,"刷新消息");
	private Integer status;
	private String desc;
	private ChatStatus(int status,String desc) {
		this.desc=desc;
		this.status=status;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}

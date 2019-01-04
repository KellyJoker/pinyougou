package entity;

import java.io.Serializable;

public class ResultMsg implements Serializable{
	private boolean flag;
	private String message;
	public ResultMsg(boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}
	public ResultMsg() {
		super();
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}

package cn.tarena.weather.entity;

public class Head {
	private String cmd;
	private String msg;
	private String code;
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Head [cmd=" + cmd + ", msg=" + msg + ", code=" + code + "]";
	}
	
}

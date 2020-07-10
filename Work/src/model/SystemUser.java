package model;

public class SystemUser {
	public static SystemUser currenSystemUser=null;
	private String SystemUser_id;
	private String SystemUser_name;
	private String SystemUser_pwd;
	public String getSystemUser_id() {
		return SystemUser_id;
	}
	public void setSystemUser_id(String systemUser_id) {
		SystemUser_id = systemUser_id;
	}
	public String getSystemUser_name() {
		return SystemUser_name;
	}
	public void setSystemUser_name(String systemUser_name) {
		SystemUser_name = systemUser_name;
	}
	public String getSystemUser_pwd() {
		return SystemUser_pwd;
	}
	public void setSystemUser_pwd(String systemUser_pwd) {
		SystemUser_pwd = systemUser_pwd;
	}

}

package ostracon.ostracon_project.account;

public class ChangePasswordForm {
	
	private Long accountId;
	private String oldPassword;
	private String newPassword;
	
	public Long getAccountId() {
		return accountId;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
}

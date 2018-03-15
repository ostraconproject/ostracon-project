package ostracon.ostracon_project.account;

import org.hibernate.validator.constraints.NotBlank;

public class ChangePasswordForm {
	
	private static final String NOT_BLANK_MESSAGE = "{blankField.message}";
	
	private Long accountId;
	
	@NotBlank(message = ChangePasswordForm.NOT_BLANK_MESSAGE)
	private String oldPassword;
	
	@NotBlank(message = ChangePasswordForm.NOT_BLANK_MESSAGE)
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

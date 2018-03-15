package ostracon.ostracon_project.account;

import org.hibernate.validator.constraints.NotBlank;

public class UpdateAccountInfoForm {
	
	private static final String FIRST_NAME_BLANK_MESSAGE = "{emptyFirstName.message}";
	private static final String LAST_NAME_BLANK_MESSAGE = "{emptyLastName.message}";
	private static final String EMAIL_BLANK_MESSAGE = "{emptyEmailAccount.message}";
	
	private Long accountId;
	
	@NotBlank(message = UpdateAccountInfoForm.FIRST_NAME_BLANK_MESSAGE)
	private String firstName;
	
	@NotBlank(message = UpdateAccountInfoForm.LAST_NAME_BLANK_MESSAGE)
	private String lastName;
	
	@NotBlank(message = UpdateAccountInfoForm.EMAIL_BLANK_MESSAGE)
	private String email;
	
	private String role;
	
	private String password;
	
	public Long getAccountId() {
		return accountId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}

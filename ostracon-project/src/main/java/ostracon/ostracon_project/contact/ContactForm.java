package ostracon.ostracon_project.contact;

import org.hibernate.validator.constraints.NotBlank;

public class ContactForm 
{
	private static final String NAME_EMPTY_MESSAGE = "{emptyName.message}";
	private static final String EMAIL_EMPTY_MESSAGE = "{emptyEmail.message}";
	private static final String SUBJECT_EMPTY_MESSAGE = "{emptySubject.message}";
	private static final String MESSAGE_EMPTY_MESSAGE = "{emptyMessage.message}";
	
	@NotBlank(message = ContactForm.NAME_EMPTY_MESSAGE)
	private String name;
	
	@NotBlank(message = ContactForm.EMAIL_EMPTY_MESSAGE)
	private String email;
	
	@NotBlank(message = ContactForm.SUBJECT_EMPTY_MESSAGE)
	private String subject;
	
	@NotBlank(message = ContactForm.MESSAGE_EMPTY_MESSAGE)
	private String message;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

package br.com.geninho.dto;

public class SendEmailDTO {

	private String name;
	private String from;
	private String subject;
	private String email;
	private String message;

	public SendEmailDTO(String name, String from, String subject, String email, String message) {
		super();
		this.name = name;
		this.from = from;
		this.subject = subject;
		this.email = email;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SendEmailDTO() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
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

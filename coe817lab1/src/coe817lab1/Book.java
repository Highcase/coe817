package coe817lab1;

public class Book {
	
	private String title;
	private String author;
	private String callNumber;
	
	public Book() {
		super();
		this.title = "";
		this.author = "";
		this.callNumber = "";
	}
	
	public Book(String t, String a) {
		this.title = t;
		this.author = a;
		this.callNumber = "";
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

}

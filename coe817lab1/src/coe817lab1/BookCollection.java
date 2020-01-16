package coe817lab1;

public class BookCollection {
	
	private final int MAXBOOKS = 3000;
	
	private Book[] bookList = new Book[MAXBOOKS];
	
	public BookCollection() {
		super();
	}
	
	public int countScienceBooks() {
		//code
		return 1;
	}

	public Book[] getBookList() {
		return bookList;
	}

	public void setBookList(Book[] bookList) {
		this.bookList = bookList;
	}

}

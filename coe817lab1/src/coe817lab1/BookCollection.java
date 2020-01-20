package coe817lab1;

public class BookCollection {
	
	private final int MAXBOOKS = 3000;
	
	private Book[] bookList = new Book[MAXBOOKS];
	
	public BookCollection() {
		super();
	}
	
	public int countScienceBooks() {
		int count = 0;
		for(Book book: bookList) {
			if(book != null)
				if(book.getCallNumber().charAt(0) == 'Q') 
					count++;
		}
		return count;
	}

	public Book[] getBookList() {
		return bookList;
	}

	public void setBookList(Book[] bookList) {
		this.bookList = bookList;
	}
	
	//tester adds one book at first and hundredth place and checks count
//	public static void main(String[] args) {
//		BookCollection bc = new BookCollection();
//		Book book = new Book();
//		book.setCallNumber("QQQQQQ");
//		bc.getBookList()[0] = book;
//		bc.getBookList()[100] = book;
//		System.out.println(bc.countScienceBooks());
//	}
}

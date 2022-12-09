package by.grsu.kshcherbina.library;

import java.sql.Timestamp;
import java.util.Date;

import by.grsu.kshcherbina.library.db.model.Book;
import by.grsu.kshcherbina.library.db.model.Library;
import by.grsu.kshcherbina.library.db.model.Order;
import by.grsu.kshcherbina.library.db.model.UserAccount;


public class Main {
	public static void main(String[] args) {
		Book book = new Book();
		book.setId(1);
		book.setName("Karina");
		book.setAuthor("Pushkin");
		book.setPage(100);
		book.setLibraryId(3);
		System.out.println(book.toString());

		Library library = new Library();
		library.setId(1);
		library.setTelephone(467);
		library.setAddress("Belue rosy");
		library.setEmail("shchekari");
		System.out.println(library.toString());

		UserAccount userAccount = new UserAccount();
		userAccount.setId(1);
		userAccount.setFirstName("Anna");
		userAccount.setLastName("Bogdel");
		userAccount.setCreated (new Timestamp(new Date().getTime()));
		userAccount.setEmail("bagisha");
		userAccount.setAddress("Boldina");
		userAccount.setTelephone(345);
		System.out.println(userAccount.toString());

		Order order = new Order();
		order.setId(1);
		order.setTakenOn(new Timestamp(new Date().getTime()));
		order.setBookId(45);
		order.setUserAccount(12);
		order.setReturnOn(new Timestamp(new Date().getTime()));
		System.out.println(order.toString());

	}

}

package by.grsu.kshcherbina.library.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.kshcherbina.library.db.dao.IDao;
import by.grsu.kshcherbina.library.db.dao.impl.BookDaoImpl;
import by.grsu.kshcherbina.library.db.dao.impl.LibraryDaoImpl;
import by.grsu.kshcherbina.library.db.model.Library;
import by.grsu.kshcherbina.library.db.model.Book;


public class BookDaoTest extends AbstractTest {
	private static final IDao<Integer, Library> libraryDao = LibraryDaoImpl.INSTANCE;
	private static final IDao<Integer, Book> bookDao = BookDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Book entity = new Book();
		
		entity.setName("Karina");
		entity.setAuthor("Pushkin");
		entity.setPage(100);
		entity.setLibraryId(saveLibrary(1).getId());
		bookDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Book entity = new Book();
		entity.setName("Karina");
		entity.setAuthor("Pushkin");
		entity.setPage(100);
		entity.setLibraryId(saveLibrary(1).getId());
		bookDao.insert(entity);

		String newName = "Anna";
		entity.setName(newName);
		entity.setAuthor("marina");
		entity.setPage(200);
		bookDao.update(entity);

		Book updatedEntity = bookDao.getById(entity.getId());
		Assertions.assertEquals(newName, updatedEntity.getName());
		Assertions.assertEquals(false, updatedEntity.getAuthor());
	}


	@Test
	public void testDelete() {
		 Book entity = new Book();
			entity.setName("Karina");
			entity.setAuthor("Pushkin");
			entity.setPage(100);
			entity.setLibraryId(saveLibrary(1).getId());
		bookDao.insert(entity);

		bookDao.delete(entity.getId());

		Assertions.assertNull(bookDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Book entity = new Book();
		entity.setName("Karina");
		entity.setAuthor("Pushkin");
		entity.setPage(100);
		entity.setLibraryId(saveLibrary(1).getId());
		bookDao.insert(entity);

		Book selectedEntity = bookDao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getAuthor(), selectedEntity.getAuthor());
		Assertions.assertEquals(entity.getPage(), selectedEntity.getPage());
		Assertions.assertEquals(entity.getLibraryId(), selectedEntity.getLibraryId());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Book entity = new Book();
			entity.setName("Karina");
			entity.setAuthor("Pushkin");
			entity.setPage(100);
			entity.setLibraryId(1);
			bookDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, bookDao.getAll().size());
	}

	private Library saveLibrary(int i) {
		Library  entity = new Library ();
		entity.setTelephone(2345678);
		entity.setAddress("gaspadarchaya");
		entity.setEmail("ffsdfg@gmail.com");
		libraryDao.insert(entity);
		return entity;
	}
}
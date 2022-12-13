package by.grsu.kshcherbina.library.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.kshcherbina.library.db.dao.IDao;
import by.grsu.kshcherbina.library.db.model.Order;
import by.grsu.kshcherbina.library.db.model.Book;
import by.grsu.kshcherbina.library.db.model.UserAccount;
import by.grsu.kshcherbina.library.db.dao.impl.BookDaoImpl;
import by.grsu.kshcherbina.library.db.dao.impl.UserAccountDaoImpl;
import by.grsu.kshcherbina.library.db.dao.impl.OrderObjectDaoImpl;
import by.grsu.kshcherbina.library.db.dao.impl.LibraryDaoImpl;
import by.grsu.kshcherbina.library.db.model.Library;

public class OrderObjectDaoTest extends AbstractTest {
	private static final IDao<Integer, Order> orderDao = OrderObjectDaoImpl.INSTANCE;
	private static final IDao<Integer, Book> bookDao = BookDaoImpl.INSTANCE;
	private static final IDao<Integer, UserAccount> userDao = UserAccountDaoImpl.INSTANCE;
	private static final IDao<Integer, Library> libraryDao = LibraryDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Order entity = new Order();
		entity.setTakenOn(getCurrentTime());
		entity.setBookId(saveBook().getId());
		entity.setUserAccount(saveUserAccount().getId());
		entity.setReturnOn(getCurrentTime());
		orderDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Order entity = new Order();
		entity.setTakenOn(getCurrentTime());
		entity.setBookId(saveBook().getId());
		entity.setUserAccount(saveUserAccount().getId());
		entity.setReturnOn(getCurrentTime());
		orderDao.insert(entity);

		UserAccount userEntity = new UserAccount();
		userEntity.setFirstName("Gleb");
		userEntity.setLastName("Glebov");
		userEntity.setCreated(getCurrentTime());
		userEntity.setEmail("1234@gmail.com");
		userEntity.setAddress("1234123411");
		userEntity.setTelephone(124123413);
		userDao.insert(userEntity);

		entity.setUserAccount(userEntity.getId());
		orderDao.update(entity);

		Order updatedEntity = orderDao.getById(entity.getId());
		Assertions.assertEquals(userEntity.getId(), updatedEntity.getUserAccount());
	}

	@Test
	public void testDelete() {
		Order entity = new Order();
		entity.setTakenOn(getCurrentTime());
		entity.setBookId(saveBook().getId());
		entity.setUserAccount(saveUserAccount().getId());
		entity.setReturnOn(getCurrentTime());
		orderDao.insert(entity);

		orderDao.delete(entity.getId());

		Assertions.assertNull(orderDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Order entity = new Order();
		entity.setTakenOn(getCurrentTime());
		entity.setBookId(saveBook().getId());
		entity.setUserAccount(saveUserAccount().getId());
		entity.setReturnOn(getCurrentTime());
		orderDao.insert(entity);

		Order selectedEntity = orderDao.getById(entity.getId());

		Assertions.assertEquals(entity.getUserAccount(), selectedEntity.getUserAccount());
		Assertions.assertEquals(entity.getBookId(), selectedEntity.getBookId());
		Assertions.assertEquals(entity.getReturnOn(), selectedEntity.getReturnOn());
		Assertions.assertEquals(entity.getTakenOn(), selectedEntity.getTakenOn());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Order entity = new Order();
			entity.setTakenOn(getCurrentTime());
			entity.setBookId(saveBook().getId());
			entity.setUserAccount(saveUserAccount().getId());
			entity.setReturnOn(getCurrentTime());
			orderDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, orderDao.getAll().size());
	}

	private UserAccount saveUserAccount() {
		UserAccount entity = new UserAccount();
		entity.setFirstName("Ivan");
		entity.setLastName("Ivanov");
		entity.setCreated(getCurrentTime());
		entity.setEmail("123@gmail.com");
		entity.setAddress("123412341");
		entity.setTelephone(124123412);
		userDao.insert(entity);
		return entity;
	}

	private Book saveBook() {
		Library libEntity = new Library();
		libEntity.setTelephone(1234123);
		libEntity.setEmail("pivo@gmail.com");
		libEntity.setAddress("oasosoda");
		libraryDao.insert(libEntity);

		Book entity = new Book();
		entity.setName("trash");
		entity.setAuthor("Me");
		entity.setPage(123);
		entity.setLibraryId(libEntity.getId());
		bookDao.insert(entity);
		return entity;
	}
}
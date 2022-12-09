package by.grsu.kshcherbina.library.impl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.kshcherbina.library.db.dao.IDao;
import by.grsu.kshcherbina.library.db.dao.impl.LibraryDaoImpl;
import by.grsu.kshcherbina.library.db.model.Library;

public class LibraryDaoTest extends AbstractTest {
	private static final IDao<Integer,Library> dao = LibraryDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Library entity = new Library();
		entity.setTelephone(34455);
		entity.setAddress("Belye Rosy");
		entity.setEmail("karishche");
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Library entity = new Library();
		entity.setTelephone(34455);
		entity.setAddress("Belye Rosy");
		entity.setEmail("karishche");
		dao.insert(entity);

		Integer newTelephone = 9039949;
		entity.setTelephone(newTelephone);
		String newAddress = "Pushkino";
		entity.setAddress(newAddress);
		String newEmail = "Margoha";
		entity.setEmail(newEmail);
		dao.update(entity);

		Library updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newTelephone, updatedEntity.getTelephone());
		Assertions.assertEquals( newAddress, updatedEntity.getAddress());
		Assertions.assertEquals( newEmail, updatedEntity.getEmail());
		
	}

	@Test
	public void testDelete() {
		Library entity = new Library();
		entity.setTelephone(9039949);
		entity.setAddress("Belye Rosy");
		entity.setEmail("karishche");
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Library entity = new Library();
		entity.setTelephone(9039949);
		entity.setAddress("Belye Rosy");
		entity.setEmail("karishche");
		dao.insert(entity);

		Library selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getTelephone(), selectedEntity.getTelephone());
		Assertions.assertEquals(entity.getAddress(), selectedEntity.getAddress());
		Assertions.assertEquals(entity.getEmail(), selectedEntity.getEmail());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Library entity = new Library();
			 // generate some random meaningless name as it is just a test (the data can be unreal)
			entity.setTelephone(9039949 +i);
			entity.setAddress("Belye Rosy");
			entity.setEmail("karishche");
			dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}


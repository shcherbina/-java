package by.grsu.kshcherbina.library.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.kshcherbina.library.db.dao.IDao;
import by.grsu.kshcherbina.library.db.model.UserAccount;
import by.grsu.kshcherbina.library.db.dao.impl.UserAccountDaoImpl;

public class UserAccountTest extends AbstractTest{
    private static final IDao<Integer,UserAccount> dao = UserAccountDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		UserAccount entity = new UserAccount();
		entity.setFirstName("Ivan");
		entity.setLastName("Ivanov");
		entity.setCreated(getCurrentTime());
		entity.setEmail("123@gmail.com");
		entity.setAddress("123412341");
		entity.setTelephone(124123412);
		dao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		UserAccount entity = new UserAccount();
		entity.setFirstName("Ivan");
		entity.setLastName("Ivanov");
		entity.setCreated(getCurrentTime());
		entity.setEmail("123@gmail.com");
		entity.setAddress("123412341");
		entity.setTelephone(124123412);
		dao.insert(entity);

		Integer newTelephone = 9039949;
		entity.setTelephone(newTelephone);
		String newAddress = "Pushkino";
		entity.setAddress(newAddress);
		String newEmail = "Margoha";
		entity.setEmail(newEmail);
		dao.update(entity);

		UserAccount updatedEntity = dao.getById(entity.getId());
		Assertions.assertEquals( newTelephone, updatedEntity.getTelephone());
		Assertions.assertEquals( newAddress, updatedEntity.getAddress());
		Assertions.assertEquals( newEmail, updatedEntity.getEmail());
		
	}

	@Test
	public void testDelete() {
		UserAccount entity = new UserAccount();
		entity.setFirstName("Ivan");
		entity.setLastName("Ivanov");
		entity.setCreated(getCurrentTime());
		entity.setEmail("123@gmail.com");
		entity.setAddress("123412341");
		entity.setTelephone(124123412);
		dao.insert(entity);

		dao.delete(entity.getId());

		Assertions.assertNull(dao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		UserAccount entity = new UserAccount();
		entity.setFirstName("Ivan");
		entity.setLastName("Ivanov");
		entity.setCreated(getCurrentTime());
		entity.setEmail("123@gmail.com");
		entity.setAddress("123412341");
		entity.setTelephone(124123412);
		dao.insert(entity);

		UserAccount selectedEntity = dao.getById(entity.getId());

		Assertions.assertEquals(entity.getTelephone(), selectedEntity.getTelephone());
		Assertions.assertEquals(entity.getAddress(), selectedEntity.getAddress());
		Assertions.assertEquals(entity.getEmail(), selectedEntity.getEmail());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			UserAccount entity = new UserAccount();
            entity.setFirstName("Ivan");
            entity.setLastName("Ivanov");
            entity.setCreated(getCurrentTime());
            entity.setEmail("123@gmail.com");
            entity.setAddress("123412341");
            entity.setTelephone(124123412);
            dao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, dao.getAll().size());
	}
}

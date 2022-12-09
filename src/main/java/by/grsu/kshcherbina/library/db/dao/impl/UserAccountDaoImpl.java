package by.grsu.kshcherbina.library.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.kshcherbina.library.db.dao.AbstractDao;
import by.grsu.kshcherbina.library.db.dao.IDao;
import by.grsu.kshcherbina.library.db.model.UserAccount;

public class UserAccountDaoImpl extends AbstractDao implements IDao<Integer, UserAccount> {

	// single instance of this class to be used by the all consumers
	public static final UserAccountDaoImpl INSTANCE = new UserAccountDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private UserAccountDaoImpl() {
		super();
	}

	@Override
	public void insert(UserAccount entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into user_account(first_name, last_name, created,email,address,telephone) values(?,?,?,?,?,?)");
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setTimestamp(3, entity.getCreated());
			pstmt.setString(4, entity.getEmail());
			pstmt.setString(5, entity.getAddress());
			pstmt.setInt(6, entity.getTelephone());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "user_account"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert user_account entity", e);
		}
	}

	@Override
	public void update(UserAccount entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into user_account(first_name, last_name, created,email,address,telephone) values(?,?,?,?,?,?)");
			pstmt.setString(1, entity.getFirstName());
			pstmt.setString(2, entity.getLastName());
			pstmt.setTimestamp(3, entity.getCreated());
			pstmt.setString(4, entity.getEmail());
			pstmt.setString(5, entity.getAddress());
			pstmt.setInt(6, entity.getTelephone());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "user_account"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert user_account entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from user_account where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Brand entity", e);
		}

	}

	@Override
	public UserAccount getById(Integer id) {
		UserAccount entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from user_account where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Brand entity by id", e);
		}

		return entity;
	}

	@Override
	public List<UserAccount> getAll() {
		List<UserAccount> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from user_account");
			while (rs.next()) {
				UserAccount entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select UserAccount entities", e);
		}

		return entitiesList;
	}

	private UserAccount rowToEntity(ResultSet rs) throws SQLException {
		UserAccount entity = new UserAccount();
		entity.setId(rs.getInt("id"));
		entity.setFirstName(rs.getString("first_name"));
		entity.setLastName(rs.getString("last_name"));
		entity.setCreated(rs.getTimestamp("created"));
		entity.setEmail(rs.getString("email"));
		entity.setAddress(rs.getString("address"));
		entity.setTelephone(rs.getInt("telephone"));
		
		return entity;
	}

	

}
package by.grsu.kshcherbina.library.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.kshcherbina.library.db.dao.AbstractDao;
import  by.grsu.kshcherbina.library.db.dao.IDao;
import  by.grsu.kshcherbina.library.db.model.Library;

public class LibraryDaoImpl extends AbstractDao implements IDao<Integer, Library> {

	// single instance of this class to be used by the all consumers
	public static final LibraryDaoImpl INSTANCE = new LibraryDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private LibraryDaoImpl() {
		super();
	}

	@Override
	public void insert(Library entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into library (telephone, address, email) values(?,?,?)");
			pstmt.setInt(1, entity.getTelephone());
			pstmt.setString(2, entity.getAddress());
			pstmt.setString(3, entity.getEmail());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "library"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert library entity", e);
		}
	}

	@Override
	public void update(Library entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update library set telephone=?, address=? , email=? where id=?");
			pstmt.setInt(1, entity.getTelephone());
			pstmt.setString(2, entity.getAddress());
			pstmt.setString(3, entity.getEmail());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Library entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from library where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Library entity", e);
		}

	}

	@Override
	public Library getById(Integer id) {
		Library entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from library where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Library entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Library> getAll() {
		List<Library> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from library");
			while (rs.next()) {
				Library entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Library entities", e);
		}

		return entitiesList;
	}

	private Library rowToEntity(ResultSet rs) throws SQLException {
		Library entity = new Library();
		entity.setId(rs.getInt("id"));
		entity.setTelephone(rs.getInt("telephone"));
		entity.setAddress(rs.getString("address"));
		entity.setEmail(rs.getString("email"));
		return entity;
	}

}
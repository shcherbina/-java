package by.grsu.kshcherbina.library.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.kshcherbina.library.db.dao.AbstractDao;
import by.grsu.kshcherbina.library.db.dao.IDao;
import by.grsu.kshcherbina.library.db.model.Book;

public class BookDaoImpl extends AbstractDao implements IDao<Integer, Book> {
	public static final BookDaoImpl INSTANCE = new BookDaoImpl();

	private BookDaoImpl() {
		super();
	}

	@Override
	public void insert(Book entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into book(name, author, page, library_id) values(?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getAuthor());
			pstmt.setInt(3, entity.getPage());
			pstmt.setInt(4, entity.getLibraryId());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "book"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert Book entity", e);
		}

	}

	@Override
	public void update(Book entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update book set name=?, author=?, page=?, library_id=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getAuthor());
			pstmt.setInt(3, entity.getPage());
			pstmt.setInt(4, entity.getLibraryId());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Book entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from book where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete Book entity", e);
		}
	}

	@Override
	public Book getById(Integer id) {
		Book entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from book where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Book entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Book> getAll() {
		List<Book> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from book");
			while (rs.next()) {
				Book entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Book entities", e);
		}

		return entitiesList;
	}

	private Book rowToEntity(ResultSet rs) throws SQLException {
		Book entity = new Book();
		entity.setId(rs.getInt("id"));
		entity.setLibraryId(rs.getInt("library_id"));
		entity.setName(rs.getString("name"));
		entity.setAuthor(rs.getString("author"));
		entity.setPage(rs.getInt("page"));
		return entity;
	}

}

package by.grsu.kshcherbina.library.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.kshcherbina.library.db.dao.AbstractDao;
import by.grsu.kshcherbina.library.db.dao.IDao;
import by.grsu.kshcherbina.library.db.model.Order;


public class OrderObjectDaoImpl extends AbstractDao implements IDao<Integer, Order> {
	public static final OrderObjectDaoImpl INSTANCE = new OrderObjectDaoImpl();

	private OrderObjectDaoImpl() {
		super();
	}

	@Override
	public void insert(Order entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("insert into order_object(taken_on, book_id, user_accound, return_on) values(?,?,?,?)");
			pstmt.setTimestamp(1, entity.getTakenOn());
			pstmt.setInt(2, entity.getBookId());
			// book_id has type Integer, but if it can be null we have to use setObject()
			// instead of setInt()
			pstmt.setInt(3, entity.getUserAccount());
			pstmt.setTimestamp(4, entity.getReturnOn());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "order_object"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert OrderObject entity", e);
		}

	}

	@Override
	public void update(Order entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c
					.prepareStatement("update order_object set book_id=?, user_account=?,  return_on=? where id=?");
			pstmt.setInt(1, entity.getBookId());
			pstmt.setInt(2, entity.getUserAccount());
			pstmt.setTimestamp(3, entity.getReturnOn());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update Book entity", e);
		}

	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from order_object where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete order_object entity", e);
		}
	}

	@Override
	public Order getById(Integer id) {
		Order entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from order_object where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get order_object entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Order> getAll() {
		List<Order> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from order");
			while (rs.next()) {
				Order entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select Order entities", e);
		}

		return entitiesList;
	}

	private Order rowToEntity(ResultSet rs) throws SQLException {
	Order entity = new Order();
		entity.setId(rs.getInt("id"));
		entity.setBookId(rs.getInt("book_id"));
		entity.setTakenOn(rs.getTimestamp("taken_on"));
		// getObject() is unsupported by current JDBC driver. We will get "0" if field
		// is NULL in DB
		entity.setUserAccount(rs.getInt("user_account"));
		entity.setReturnOn(rs.getTimestamp("return_on"));
		return entity;
	}

	
}

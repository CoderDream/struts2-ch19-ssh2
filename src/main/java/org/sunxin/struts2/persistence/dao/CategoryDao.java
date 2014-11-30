package org.sunxin.struts2.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.sunxin.struts2.persistence.entity.Category;

public class CategoryDao implements ICategoryDao {
	private DataSource dataSource;

	public CategoryDao() {
		Context ctx;
		try {
			ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/test");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunxin.struts2.persistence.dao.ICategoryDao#findById(java.lang.Integer)
	 */
	public Category findById(Integer id) {
		if (null == id)
			return null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getDataSource().getConnection();

			String sql = "select * from category where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				Category cat = new Category();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				return cat;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunxin.struts2.persistence.dao.ICategoryDao#findAll()
	 */
	public List<Category> findAll() {
		List<Category> categories = new ArrayList<Category>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getDataSource().getConnection();

			String sql = "select * from category";
			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Category cat = new Category();
				cat.setId(rs.getInt("id"));
				cat.setName(rs.getString("name"));
				categories.add(cat);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closeStatement(stmt);
			closeConnection(conn);
		}

		return categories;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunxin.struts2.persistence.dao.ICategoryDao#makePersistent(org.sunxin.struts2.persistence.entity.Category)
	 */
	public void makePersistent(Category cat) {
		if (null != cat.getId())
			update(cat);
		else
			save(cat);
	}

	/**
	 * 保存分类对象
	 * 
	 * @param cat
	 */
	private void save(Category cat) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getDataSource().getConnection();

			String sql = "insert into category(name) values(?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cat.getName());

			pstmt.executeUpdate();
			rs = pstmt.executeQuery("select last_insert_id()");

			if (rs.next()) {
				cat.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResultSet(rs);
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
	}

	/**
	 * 更新分类对象
	 * 
	 * @param cat
	 */
	private void update(Category cat) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getDataSource().getConnection();

			String sql = "update category set name = ? where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cat.getName());
			pstmt.setInt(2, cat.getId());
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.sunxin.struts2.persistence.dao.ICategoryDao#delete(java.lang.Integer)
	 */
	public void delete(Integer id) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getDataSource().getConnection();

			String sql = "delete from category where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closePreparedStatement(pstmt);
			closeConnection(conn);
		}
	}

	private void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	private void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pstmt = null;
		}
	}

	private void closeStatement(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			stmt = null;
		}
	}

	private void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}
}

package org.sunxin.struts2.persistence.dao;

import org.sunxin.struts2.persistence.dao.hibernate.HibernateDaoFactory;

public abstract class DaoFactory {
	private static final DaoFactory HIBERNATE = new HibernateDaoFactory();

	public static DaoFactory getInstance() {
		return HIBERNATE;
	}

	public abstract ICategoryDao getCategoryDao();
}
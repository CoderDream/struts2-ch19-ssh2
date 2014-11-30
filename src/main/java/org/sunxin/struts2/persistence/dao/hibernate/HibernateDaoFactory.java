package org.sunxin.struts2.persistence.dao.hibernate;

import org.hibernate.Session;
import org.sunxin.struts2.persistence.dao.DaoFactory;
import org.sunxin.struts2.persistence.dao.ICategoryDao;
import org.sunxin.struts2.util.hibernate.HibernateUtil;

public class HibernateDaoFactory extends DaoFactory {
	@Override
	public ICategoryDao getCategoryDao() {
		// 使用当前的Session对象来构造CategoryHibernateDao对象
		CategoryHibernateDao catDao = new CategoryHibernateDao(getCurrentSession());
		return catDao;
	}

	private Session getCurrentSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
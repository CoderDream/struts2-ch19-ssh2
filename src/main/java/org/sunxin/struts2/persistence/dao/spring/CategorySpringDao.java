package org.sunxin.struts2.persistence.dao.spring;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.sunxin.struts2.persistence.dao.ICategoryDao;
import org.sunxin.struts2.persistence.entity.Category;

public class CategorySpringDao extends HibernateDaoSupport implements ICategoryDao {

	@Override
	public void delete(Integer id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(Category.class, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		return getHibernateTemplate().find("from Category");
	}

	@Override
	public Category findById(Integer id) {
		return (Category) getHibernateTemplate().get(Category.class, id);
	}

	@Override
	public void makePersistent(Category cat) {
		getHibernateTemplate().saveOrUpdate(cat);
	}
}
package org.sunxin.struts2.persistence.dao.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.sunxin.struts2.persistence.dao.ICategoryDao;
import org.sunxin.struts2.persistence.entity.Category;

public class CategoryHibernateDao implements ICategoryDao {
	private Session session;

	/**
	 * 使用session对象来构建CategoryHibernateDao对象
	 * 
	 * @param session
	 */
	public CategoryHibernateDao(Session session) {
		this.session = session;
	}

	public Session getSession() {
		if (session == null)
			throw new IllegalStateException("Session has not been set on DAO before usage");
		return session;
	}

	/**
	 * 删除分类对象
	 */
	public void delete(Integer id) {
		getSession().delete(getSession().get(Category.class, id));
	}

	@SuppressWarnings("unchecked")
	/**
	 * 获取所有分类
	 */
	public List<Category> findAll() {
		Criteria crit = getSession().createCriteria(Category.class);
		return crit.list();
	}

	/**
	 * 根据分类id查询分类
	 */
	public Category findById(Integer id) {
		return (Category) getSession().get(Category.class, id);
	}

	/**
	 * 将分类对象持久化到数据库中。根据分类id是否为null，来判断是保存还是更新。
	 */
	public void makePersistent(Category cat) {
		// Session的saveOrUpdate()方法根据Category对象的id属性是否有值来判断是保存还是更新
		getSession().saveOrUpdate(cat);
	}
}
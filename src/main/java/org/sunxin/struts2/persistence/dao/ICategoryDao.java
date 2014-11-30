package org.sunxin.struts2.persistence.dao;

import java.util.List;

import org.sunxin.struts2.persistence.entity.Category;

public interface ICategoryDao {

	/**
	 * 根据分类id查询分类
	 * 
	 * @param id
	 *            分类id
	 * @return
	 */
	public abstract Category findById(Integer id);

	/**
	 * 获取所有分类
	 * 
	 * @return
	 */
	public abstract List<Category> findAll();

	/**
	 * 将分类对象持久化到数据库中。根据分类id是否为null，来判断是保存还是更新。
	 * 
	 * @param cat
	 */
	public abstract void makePersistent(Category cat);

	/**
	 * 删除分类对象
	 * 
	 * @param id
	 */
	public abstract void delete(Integer id);

}
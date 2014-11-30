package org.sunxin.struts2.action;

import java.util.List;

import org.sunxin.struts2.persistence.dao.ICategoryDao;
import org.sunxin.struts2.persistence.entity.Category;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class CategoryAction extends ActionSupport implements Preparable {
	private static final long serialVersionUID = 1802654925858268045L;
	private List<Category> categories;
	private ICategoryDao catDao;
	private Category category;
	// 分类ID
	private Integer id;

	public void setCatDao(ICategoryDao catDao) {
		this.catDao = catDao;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * 列出所有的分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		categories = catDao.findAll();
		return SUCCESS;
	}

	/**
	 * 删除分类
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delete() throws Exception {
		if (null != id)
			catDao.delete(id);

		return SUCCESS;
	}

	/**
	 * 创建或者更新分类。catDao的makePersistent()方法会根据分类id是否为null， 来判断是创建还是更新分类。
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		catDao.makePersistent(category);
		return SUCCESS;
	}

	/**
	 * Preparable接口的prepare()方法，由PrepareInterceptor拦截器调用
	 */
	public void prepare() throws Exception {
		if (null != id)
			category = catDao.findById(id);
	}
}
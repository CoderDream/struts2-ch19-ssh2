package org.sunxin.struts2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.sunxin.struts2.util.hibernate.HibernateUtil;

public class HibernateThreadFilter implements Filter {
	private SessionFactory sf = null;

	public void init(FilterConfig arg0) throws ServletException {
		sf = HibernateUtil.getSessionFactory();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {

			sf.getCurrentSession().beginTransaction();

			chain.doFilter(request, response);

			sf.getCurrentSession().getTransaction().commit();

		} catch (StaleObjectStateException staleEx) {
			throw staleEx;
		} catch (Throwable ex) {
			ex.printStackTrace();
			try {
				// 如果发生异常，让事务回滚。
				if (sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
			} catch (Throwable rbEx) {
				System.err.println(rbEx.toString());
			}
		}
	}

	public void destroy() {
	}

}

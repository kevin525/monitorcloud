package com.sys.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.common.pagetag.PageGridPost;
import com.sys.dao.BaseDao;
import com.sys.domain.model.BaseModel;

/**
 * @author 李涛
 */
@Repository(value="baseDao")
public class BaseDaoImpl extends HibernateDaoSupport implements BaseDao {
	private static final Logger log  = LoggerFactory.getLogger(BaseDaoImpl.class);
	/**
	 * 通过HQL语句查询，返回List
	 * 
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List query(final String hql) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql).list();
			}
		});
	}

	/**
	 * 通过HQL语句查询，带参数Object[]
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List query(final String hql, final Object[] params) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return createQuery(session, hql, params).list();
			}
		});
	}

	/**
	 * 通过HQL语句查询，带参数Map
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List query(final String hql, final Map<String, ?> params) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return createQuery(session, hql, params).list();
			}
		});
	}

	/**
	 * 通过sql语句查询，返回List
	 * 
	 * @param sql
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List queryBySQL(final String sql) {
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql).list();
			}
		});
	}

	
	/**
	 * 通过执行sql语句更新数据信息
	 * @param sql
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int executeBySql(final String sql){
		return getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createSQLQuery(sql).executeUpdate();
			}
			
		});
	}
	
	
	
	
	
	
	
	/**
	 * 通过hql语句查询，带参数Object[]
	 * 
	 * @param session
	 * @param hql
	 * @param params
	 * @return
	 */
	private Query createQuery(Session session, String hql, Object[] params) {
		Query query = session.createQuery(hql);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				query.setParameter(i, params[i]);
			}
		}
		return query;
	}

	/**
	 * 通过hql语句查询，带参数Map
	 * 
	 * @param session
	 * @param hql
	 * @param params
	 * @return
	 */
	private Query createQuery(Session session, String hql, Map<String, ?> params) {
		Query query = session.createQuery(hql);
		if (params != null) {
			query.setProperties(params);
		}
		return query;
	}

	/**
	 * 通过主键查找指定的Class对象
	 * 
	 * @param clazz
	 * @param id
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object findById(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	/**
	 * 保存对象
	 * 
	 * @param o
	 */
	public void save(Object o) {
		getHibernateTemplate().save(o);
	}

	/**
	 * 保存或者更新对象
	 * 
	 * @param o
	 */
	public void saveOrUpdate(Object o) {
		getHibernateTemplate().saveOrUpdate(o);
	}

	/**
	 * 批量保存或者更新对象
	 * 
	 * @param lists
	 */
	@SuppressWarnings("rawtypes")
	public void saveAll(Collection lists) {
		getHibernateTemplate().saveOrUpdateAll(lists);
	}

	/**
	 * 更新对象
	 * 
	 * @param o
	 */
	public void update(Object o) {
		getHibernateTemplate().update(o);
	}

	/**
	 * 删除对象
	 * 
	 * @param o
	 */
	public void delete(Object o) {
		getHibernateTemplate().delete(o);
	}
	
	/**
	 * 通过Id来批量删除对象
	 * @param entityClass
	 * @param entityIds
	 */
	public <T> void delete(Class<T> entityClass, Object[] entityIds) {
		 for(Object id : entityIds) {
			 getHibernateTemplate().delete(getHibernateTemplate().get(entityClass, (Serializable) id));
		 }
	 }
	
	/**
	 * 通过Id来删除对象
	 * @param entityClass
	 * @param entityIds
	 */
	public <T> void delete(Class<T> entityClass, Object entityId) {
		getHibernateTemplate().delete(getHibernateTemplate().get(entityClass, (Serializable) entityId));
	}

	/**
	 * 批量删除对象
	 * 
	 * @param lists
	 */
	@SuppressWarnings("rawtypes")
	public void deleteAll(Collection lists) {
		getHibernateTemplate().deleteAll(lists);
	}

	/**
	 * 批量删除对象，通过指定的主键
	 * 
	 * @param clazz
	 * @param primaryKey
	 * @param ids
	 */
	@SuppressWarnings("rawtypes")
	public void deleteAll(Class clazz, String primaryKey, Serializable[] ids) {
		if ((ids == null) || (ids.length <= 0))
			return;
		String oids = "";
		for (int i = 0; i < ids.length; i++)
			if (ids[i] != null) {
				if (!oids.equals(""))
					oids = oids + ",";
				if ((ids[i] instanceof String))
					oids = oids + "'" + ids[i] + "'";
				else
					oids = oids + ids[i];
			}
		String hql = "delete " + clazz.getName() + " o where o." + primaryKey
				+ " in(" + oids + ") ";
		getHibernateTemplate().bulkUpdate(hql);
	}
	
	
	
	/**
	 * 获得分页的总条数
	 * @param vo(实体类)
	 * @param whereClause
	 * @return
	 */
	public int getDataTotalNum(final BaseModel vo, final String whereClause){
		try {
				String className = vo.getClass().getName();
				className = className.substring(className.lastIndexOf(".") + 1);
				String hql = "select count(*) from " + className + " ";
				if (whereClause != null && !whereClause.trim().equals("")) {
					final int wherePos = whereClause.trim().toUpperCase().indexOf("where");
					if (wherePos >= 0)
						hql += whereClause.trim();
					else
						hql += " where " + whereClause.trim();
				}
				final Session session = getSession();
				final Query query = session.createQuery(hql);
				final List<?> list = query.list();
				releaseSession(session); // 关闭session
				if (list != null && !list.isEmpty()) {
					return Integer.parseInt(list.get(0).toString());
				} else{
					return 0;
				}
		} catch (Exception e) {
				e.printStackTrace();
				return 0;
		}
	}
	
	
	/**
	 * 通过hql语句和参数获得分页总数
	 * @param hql
	 * @param filter
	 * @return
	 */
	public int getDataTotalNum(String hql, Object... filter) {
		try {
			QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql,
					Collections.EMPTY_MAP,
					(SessionFactoryImplementor) this.getSessionFactory());
			queryTranslator.compile(Collections.EMPTY_MAP, false);
			String tempSQL = queryTranslator.getSQLString();
			String countSQL = "select count(*) from (" + tempSQL + ") tmp_count_t";
			final Session session = getSession(); // 取得session
			Query query = session.createSQLQuery(countSQL);
			int amount = 0;
			for (int i = 0; i < filter.length; i++) {
				query = query.setParameter(i, filter[i]);
			}
			final List<?> list = query.list();
			if (!list.isEmpty()) {
				amount = Integer.parseInt(list.get(0).toString());
			}
			releaseSession(session); // 关闭session
			return amount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} 
	}
	
	
	/**
	 * 获得当前页数的数据信息
	 * @param hql
	 * @param page_size
	 * @param pageNo
	 * @param args
	 * @return
	 */
	public List<?> getPageDataByHQL(String hql, int page_size, int pageNo,Object... args) {
		List<?> list = new ArrayList<Object>();
		int total_page = 0; // 总页数
		final int total_num = getDataTotalNum(hql, args); // 总记录数
		if (total_num % page_size == 0) { // 取得总页数
			total_page = total_num / page_size;
		} else {
			total_page = total_num / page_size + 1;
		}
		if (pageNo > total_page)
			pageNo = total_page;
		final int start = (pageNo - 1) * page_size; // 开始数据的位置
		final int rowNum = page_size; // 每页显示数据
		final Session session = getSession(); // 取得session
		Query query = null;
		query = session.createQuery(hql); // 查询符合条件的数据
		for (int i = 0; i < args.length; i++) {
			query = query.setParameter(i, args[i]);
		}
		query.setReadOnly(true); // 设置此连接为只读属性
		query.setFirstResult(start);
		query.setMaxResults(rowNum);
		list = query.list();
		releaseSession(session); // 关闭session
		return list;
	}
	
	@SuppressWarnings("rawtypes")
	public  List<?> queryByPage( final String hql, final PageGridPost pageGridPost) {
		List<?> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int fromIndex = hql.toLowerCase().indexOf("from");
				String countHql = "select count(*) " +hql.substring(fromIndex,hql.length());
				countHql = replaceOrderBy(countHql);
				/*************** 查询分页 *********************/
				Query query = session.createQuery(hql);
				setPageParam(query,pageGridPost); // 分页参数设置
				List<?> list = query.list();
				if(pageGridPost != null) {
					/***************** 统计数量 ***********************/
					query = session.createQuery(countHql);
					Long totalCount = (Long) query.uniqueResult();
					pageGridPost.setTotalCount(totalCount);
				}
				return list;
			}
		});
		return list;
	}
	/**
	 * 分页查询
	 * @param hql
	 * @param paramMap
	 * @param pageGridPost
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public  List<?> queryByPage(final String hql,final Map<String, Object> paramMap,final PageGridPost pageGridPost) {
		List<?> list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				int fromIndex = hql.toLowerCase().indexOf("from");
				String countHql = "select count(*) " +hql.substring(fromIndex,hql.length());
				countHql = replaceOrderBy(countHql);
				/*************** 查询分页 *********************/
				Query query = session.createQuery(hql);
				if(paramMap != null) {
					query.setProperties(paramMap);
				}
				setPageParam(query,pageGridPost); // 分参数设置
				List<?> list = query.list();
				/***************** 统计数量 ***********************/
				if(pageGridPost != null) {
					query = session.createQuery(countHql);
					if(paramMap !=null) {
						query.setProperties(paramMap);
					}
					Long totalCount = (Long) query.uniqueResult();
					pageGridPost.setTotalCount(totalCount);
				}
				return list;
			}
		});
		return list;
	}
	/**
	 * 替换order by 
	 * @param sql
	 * @return
	 */
	public static String replaceOrderBy (String sql) {
		String rst = "";
		if(sql != null) {
			Matcher m1 = Pattern.compile("order\\s+by\\s+(([0-9a-z\\_\\#]+){0,1}\\s*(\\.\\s*[0-9a-z\\_\\#]+)*)(,?([0-9a-z\\_\\#]+){0,1}\\s*(\\.\\s*[0-9a-z\\_\\#]+)*)*(\\s*(asc|desc){0,1})",Pattern.CASE_INSENSITIVE).matcher(sql);  
			rst = m1.replaceAll("");
		}
		return rst;
	}
	/**
	 * 设置分页参数
	 * @param query
	 * @param extGridPost
	 * @return
	 */
	private  Query setPageParam(Query query, PageGridPost extGridPost) {
		if(extGridPost != null  ) {
			Integer start = extGridPost.getStart()==null?0:extGridPost.getStart();
			Integer bb = extGridPost.getLimit()==null?0:extGridPost.getLimit();
			Integer limit = extGridPost.getPageSize()==null?bb:extGridPost.getPageSize();
			if(start != null && limit != null) {
				query.setFirstResult(start);
				query.setMaxResults(limit);
			} else {
				log.warn("无法进行分页, 将查询所有数据 start {}, limit: {} ", start, limit);
			}
		} else {
			log.warn("无法进行分页, 将查询所有数据 extGridPost is null");
		}
		return query;
	}
	
	//批量Hql语句批量（插入\更新\删除）数据
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int batchOp(final String hql){
		return getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql).executeUpdate();
			}
			
		});
	}
	
}

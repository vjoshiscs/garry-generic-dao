package com.crud.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.crud.dao.GenericDAO;
import com.crud.pagination.Page;

/**
 * Implementation of data access layer
 * 
 * @author aspire
 * 
 */
public class GenericDAOImpl<T, ID extends Serializable> extends HibernateDaoSupport implements
    GenericDAO<T, Serializable> {

  private Logger log = Logger.getLogger(GenericDAOImpl.class);
  private Class<? extends T> clazz;

  public GenericDAOImpl() {
    log.info("GenericDaoImpl Default Constructor");
  }

  public GenericDAOImpl(Class<? extends T> clazz) {
    this.clazz = clazz;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.aspire.student.dao.GenericDAO#create(java.lang.Object)
   */
  @Override
  public T create(T entity) throws Exception {
    log.debug("Executing method : create()");
    getSessionFactory().getCurrentSession().save(entity);
    return entity;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.aspire.student.dao.GenericDAO#findById(java.lang.Long)
   */
  @SuppressWarnings("unchecked")
  public T findById(Integer id) throws Exception {
    log.debug("Executing method : findById() with id = " + id);
    log.debug("class is " + clazz);
    return (T) getSessionFactory().getCurrentSession().get(clazz, id);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.aspire.student.dao.GenericDAO#merge(java.lang.Object)
   */
  @Override
  public T merge(T transientObject) throws Exception {
    log.debug("Executing method : merge()");
    getSessionFactory().getCurrentSession().merge(transientObject);
    return transientObject;

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.aspire.student.dao.GenericDAO#delete(java.lang.Object)
   */
  @Override
  public void delete(T persistentObject) throws Exception {
    log.debug("Executing method : delete()");
    getSessionFactory().getCurrentSession().delete(persistentObject);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.aspire.student.dao.GenericDAO#findAll()
   */
  @SuppressWarnings("unchecked")
  public List<T> findAll() throws Exception {
    log.debug("Executing method : findAll()");
    return (List<T>) getHibernateTemplate().loadAll(this.clazz);
  }
  
  
  public List<T> findAll(Page page, String queryName) throws Exception {
	  log.debug("Executing method : findAll(Page page)");
	  Query query = createdNamedQuery(queryName);
	  query.setFirstResult(page.getStartIndex());
	  query.setMaxResults(page.getRecordPerPage());
	  return runQuery(query);
  }
  
  
public List<T> findColumnBased(Map<String, Object> columnName, Page page) throws Exception{
	  
	  Criteria cr = getSession().createCriteria(this.clazz);

	    if (columnName != null) {
	      Iterator<Map.Entry<String, Object>> queryParamIterator =
	    		  columnName.entrySet().iterator();
	      while (queryParamIterator.hasNext()) {
	        Map.Entry<String, Object> parameter = queryParamIterator.next();
        	 if(parameter.getValue()!=null&&parameter.getValue()!="")
	       	      cr.add(Restrictions.like(parameter.getKey(), parameter.getValue()));
	      }
	    }
	  
	  cr.setFirstResult(page.getStartIndex());
	  cr.setMaxResults(page.getRecordPerPage());
		
	  return (List<T>)cr.list();
	
}

  /*
   * (non-Javadoc)
   * 
   * @see com.aspire.student.dao.GenericDAO#findByQueryParams(java.lang.String, java.util.Map)
   */
  public List<T> findByQueryParams(String queryName, Map<String, Object> queryParameters)
      throws Exception {
    log.debug("Executing method : findByQueryParams()");
    Query query = createdNamedQuery(queryName);
    setParametersInQuery(query, queryParameters);
    return runQuery(query);
  }

  /**
   * Create name query with given queryname
   * 
   * @param queryName
   * @return
   * @throws Exception
   */
  private Query createdNamedQuery(String queryName) throws Exception {
    log.debug("Executing method : createdNamedQuery()");
    Query query = getSession().getNamedQuery(queryName);
    query.setCacheable(true);
    return query;
  }

  /**
   * Set the parameter into the query
   * 
   * @param query
   * @param queryParameters
   * @throws Exception
   */
  private static void setParametersInQuery(Query query, Map<String, Object> queryParameters)
      throws Exception {
    if (queryParameters != null) {
      Iterator<Map.Entry<String, Object>> queryParamIterator =
          queryParameters.entrySet().iterator();
      while (queryParamIterator.hasNext()) {
        Map.Entry<String, Object> entry = queryParamIterator.next();
        if (entry.getValue() instanceof Collection)
          query.setParameterList(entry.getKey(), (Collection) entry.getValue());
        else
          query.setParameter(entry.getKey(), entry.getValue());
      }
    }
  }

  /**
   * Runs the query and return result object list
   * 
   * @param query
   * @return
   * @throws Exception
   */
  @SuppressWarnings("unchecked")
  private List<T> runQuery(Query query) throws Exception {
    query.setCacheable(true);
    log.debug("Query :" + query.toString());
    return (List<T>) query.list();
  }
}

package com.ivo.activiticlient.common.dao;

import com.ivo.activiticlient.Constants;
import com.ivo.activiticlient.common.util.Util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 *
 * @author David Yang
 */
@SuppressWarnings("unchecked")
public class HibernateCaller extends HibernateDaoSupport {
    protected Log logger = LogFactory.getLog(this.getClass());

    public HibernateCaller() {}

    public void flush() {
        getHibernateTemplate().flush();
    }

    public void clear() {
        getHibernateTemplate().clear();
    }


    /**
     * create Query object
     * Example:
     * 	caller.createQuery(hql)
     * 	caller.createQuery(hql,arg0);
     * 	caller.createQuery(hql,arg0,arg1);
     * 	caller.createQuery(hql,new Model[arg0,arg1,arg2])
     * @param values variable parameter
     */
    public Query createQuery(String hql, Object... values) {
        Assert.hasText(hql);
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        return query;
    }

    /**
     * Create Criteria Model
     * @param criterions variable parameter
     */
    public Criteria createCriteria(Class entityClass, Criterion... criterions) {
        Criteria criteria = getSession().createCriteria(entityClass);
        for (Criterion c : criterions) {
            criteria.add(c);
        }
        return criteria;
    }

    /**
     *
     * @see #createCriteria(Class, Criterion[])
     */
    public Criteria createCriteria(Class entityClass, String orderBy, boolean isAsc, Criterion... criterions) {
        Assert.hasText(orderBy);

        Criteria criteria = createCriteria(entityClass, criterions);

        if (isAsc)
            criteria.addOrder(Order.asc(orderBy));
        else
            criteria.addOrder(Order.desc(orderBy));

        return criteria;
    }
    /**
     * search by hql
     * @param values valiable parameter
     */
    public List find(String hql, Object... values) {
        Assert.hasText(hql);
        return getHibernateTemplate().find(hql, values);
    }

    public List find(String hql) {
        Assert.hasText(hql);
        return getHibernateTemplate().find(hql);
    }
    /**
     * search by hql Top result
     * @param values valiable parameter
     */
    public List find(String hql, int maxResult, Object... values) {
        Assert.hasText(hql);
        Query query = this.createQuery(hql, values);
        query.setFirstResult(0);
        query.setMaxResults(maxResult);
        List list = query.list();
        return list;
    }

    /**
     * Get object by id
     */
    public <T>T getObject(Class<T> clazz, Serializable id) {
        T o = getHibernateTemplate().get(clazz, id);

        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, id);
        }

        return o;
    }
    /**
     * Get unique Model
     */
    public <T>T getObject(Class<T> clazz, String columnName, String clumnValue) {
        T o = (T)getSession().createCriteria(clazz)
                .add(Restrictions.like(columnName, clumnValue))
                .uniqueResult();
        if (o == null) {
            throw new ObjectRetrievalFailureException(clazz, columnName);
        }
        return o;
    }

    /**
     * New Method Get Employeess
     */

    public List getObjectByQuery(StringBuffer sqlquery)
    {
        List list = new ArrayList();
        Query query = getSession().createQuery(sqlquery.toString());
        list = query.list();
        return list;
    }

    /**
     * Get objects
     */
    public List getObjects(Class clazz) {
        return getHibernateTemplate().loadAll(clazz);
    }
    /**
     * Save Model
     */
    public void saveObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
        getHibernateTemplate().flush();
    }
    /**
     * Save Or Update Model
     */
    public void saveOrUpdateObject(Object o) {
        getHibernateTemplate().saveOrUpdate(o);
        getHibernateTemplate().flush();
    }
    /**
     * Only update Model
     */
    public void updateObject(Object o) {
        getHibernateTemplate().update(o);
        getHibernateTemplate().flush();
    }

    /**
     * update by hql
     *
     * @param values
     *            valiable parameter
     */
    public void update(String hql, Object... values) {
        Assert.hasText(hql);
        Query query = getSession().createQuery(hql);
        for (int i = 0; i < values.length; i++) {
            query.setParameter(i, values[i]);
        }
        query.executeUpdate();
    }

    /**
     * Remove object by id
     */
    public void removeObject(Class clazz, Serializable id) {
        getHibernateTemplate().delete(getObject(clazz, id));
        this.flush();
    }
    public void removeObject(Object obj) {
        getHibernateTemplate().delete(obj);
        this.flush();
    }


    //////////////////// Look up object//////////////////////////////////
    /**
     * Get Model by object's specific parameter
     * Example:
     * 	dao.createQuery(hql)
     * 	dao.createQuery(hql,arg0);
     * 	dao.createQuery(hql,arg0,arg1);
     * 	dao.createQuery(hql,new Model[arg0,arg1,arg2])
     * @param parameters valiable parameter
     */
//    public Object getObject(Object object, String... parameters) {
//        Criteria criteria = getSession().createCriteria(object.getClass());
//        for (int i = 0; i < parameters.length; i++) {
//            criteria.add(Restrictions.eq(parameters[i], BeanUtil.getPropertyValue(object, parameters[i])));
//        }
//        return criteria.uniqueResult();
//    }
    /**
     * Get Objects by object's specific parameter
     * @param parameters valiable parameter
     */
//    public List getObjects(Object object, String... parameters) {
//        Criteria criteria = getSession().createCriteria(object.getClass());
//        for (int i = 0; i < parameters.length; i++) {
//            criteria.add(Restrictions.eq(parameters[i], BeanUtil.getPropertyValue(object, parameters[i])));
//        }
//        return criteria.list();
//    }
    /**
     * Get results by criterias
     * @param clazz     Class
     * @param criterias Map
     * @return
     */
    public List getObjects(Class clazz, Map criterias) {
        Criteria criteria = getSession().createCriteria(clazz);
        for (Iterator iter = criterias.keySet().iterator(); iter.hasNext();) {
            String key = (String) iter.next();
            criteria.add(Restrictions.eq(key, criterias.get(key)));
        }
        return criteria.list();
    }

    /**
     * 按属性查找唯一对象,匹配方式为相等.
     */
    public Object findUnique(final Class entityClass,
                             final String propertyName, final Object value) {
        Assert.hasText(propertyName, "propertyName must not be null");
        Criterion criterion = Restrictions.eq(propertyName, value);
        return createCriteria(entityClass, criterion).uniqueResult();
    }
    /**
     * search by sql
     * return map list
     * @param values valiable parameter
     */
    public List sqlQuery(String sql, List<String> columns) {
        Assert.hasText(sql);
        Session session = this.getSession();
        try {
            SQLQuery query = session.createSQLQuery(sql);
            if(columns != null && columns.size() > 0){
                for(String a : columns){
                    query.addScalar(a, new StringType());
                }
            }
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List list = query.list();
            return list;
        } finally {
            releaseSession(session); //释放session
        }
    }

    public List sqlQuery(String sql) {
        Assert.hasText(sql);
        Session session = this.getSession();
        try {
            Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List list = query.list();
            return list;
        } finally {
            releaseSession(session); //释放session
        }
    }

    public <T> List<T> sqlEntityQuery(String sql,Class<T> clazz) {
        Assert.hasText(sql);
        Session session = this.getSession();
        try {
            Query query = session.createSQLQuery(sql).addEntity(clazz);
            List<T> list = query.list();
            return list;
        } finally {
            releaseSession(session); //释放session
        }
    }

    public List execProc(String sql, Object... values) {
        Assert.hasText(sql);
        Session session = this.getSession();
        try {
            Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }

            List list = query.list();
            return list;
        } finally {
            releaseSession(session); //释放session
        }
    }

    /**
     * 按Criteria查询对象列表.
     *
     * @param criterions
     *            数量可变的Criterion.
     */
    public List find(final Class entityClass, final Criterion... criterions) {
        return createCriteria(entityClass, criterions).list();
    }


    /**
     * 按属性查找对象列表,匹配方式为相等.
     */
    public List find(final Class entityClass, final String propertyName,
                     final Object value) {
        Assert.hasText(propertyName, "propertyName must not be null");

        Criterion criterion = Restrictions.eq(propertyName, value);
        return find(entityClass, criterion);
    }

    /**
     * 按属性查找对象列表,匹配方式为相等.
     *
     * @param propertyName
     * @param value
     * @return
     */
    public List find(final Class entityClass, final String[] propertyName,
                     final Object[] value) {

        List<Criterion> criterionList = new ArrayList<Criterion>();
        for (int i = 0; i < propertyName.length; i++) {
            Criterion criterion = Restrictions.eq(propertyName[i], value[i]);
            criterionList.add(criterion);
        }

        Criterion[] cri = criterionList.toArray(new Criterion[criterionList
                .size()]);

        return find(entityClass, cri);
    }

    public List findByPage(int page, int size, String hql, Object... values) {
        if (hql == null)
            return new ArrayList<Object>();
        if (page < 1)
            page = 1;
        if (size <= 0)
            size = 25;
        else if (size > 500)
            size = 500;
        Query queryObject = getSession().createQuery(hql);
        queryObject.setFirstResult((page - 1) * size);
        queryObject.setMaxResults(size);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return queryObject.list();
    }

    public <T> List<T> findBySQL(Class<T> entityClass,String sql, Object... values) {
        return findBySQL(entityClass,false, sql, values);
    }

    public List<?> findBySQL(String sql, Object... values) {
        return findBySQL(false, sql, values);
    }

    public List<?> findBySQL(boolean enableQueryCache, String sql, Object... values) {
        if (sql == null)
            return null;
        Query queryObject = createSQLQuery(sql, enableQueryCache);
        //queryObject.setMaxResults(Constants.MAX_QUERY_RESULTS);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        List list = queryObject.list();
        return list;
    }

    public List<?> findBySQL(boolean enableQueryCache, int pageNumber, int pageSize, String sql, Object... values) {
        if (sql == null)
            return null;
        Query queryObject = createSQLQuery(sql, enableQueryCache);
        queryObject.setFirstResult((pageNumber - 1) * pageSize);
        queryObject.setMaxResults(pageSize);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        List list = queryObject.list();
        return list;
    }

    public <T> List<T> findBySQL(Class<T> entityClass, boolean enableQueryCache, String sql, Object... values) {
        if (sql == null)
            return null;
        Query queryObject = createSQLQuery(entityClass,sql, enableQueryCache);
        queryObject.setMaxResults(Constants.MAX_QUERY_RESULTS);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        List list = queryObject.list();
        return list;
    }

    protected <T> Query createSQLQuery(Class<T> entityClass, String sql, boolean enableQueryCache) {
        Query query = getSession().createSQLQuery(sql).addEntity(entityClass);
        query.setTimeout(Constants.MAX_TIME_OUT);
        if (enableQueryCache) {
            query.setCacheable(true);
            query.setCacheRegion("QueryCache");
        }
        return query;
    }
    protected Query createSQLQuery(String sql, boolean enableQueryCache) {
        Query query = getSession().createSQLQuery(sql);
        query.setTimeout(Constants.MAX_TIME_OUT);
        if (enableQueryCache) {
            query.setCacheable(true);
            query.setCacheRegion("QueryCache");
        }
        return query;
    }

    public Long count(String hql, Object... values) {
        hql = Util.removeSelect(hql);
        if (hql == null)
            return null;
        hql = "select count(*) " + hql;
        Query queryObject = createQuery(hql, false);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                queryObject.setParameter(i, values[i]);
            }
        }
        return (Long) queryObject.uniqueResult();
    }

    public List<Object> sqlQuery(Class<Object> class1, String string) {
        // TODO Auto-generated method stub
        return null;
    }

    public Session getSession() {
        return getSessionFactory().getCurrentSession();
    }

    public void releaseSession(Session session) {

    }
}


package org.openmrs.module.radiotest.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioItem;
import org.openmrs.module.radiotest.RadioItemType;
import org.openmrs.module.radiotest.RadioTransItem;
import org.openmrs.module.radiotest.api.db.RadioInventoryDAO;

@SuppressWarnings("unchecked")
public class HibernateRadioInventoryDAO implements RadioInventoryDAO {

	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
     * @param sessionFactory the sessionFactory to set
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
	    this.sessionFactory = sessionFactory;
    }
    
	/**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
	    return sessionFactory;
    }
    
	@Override
	public RadioItemType saveItemType(RadioItemType type) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(type);
		return type;
	}

	@Override
	public RadioItemType getItemType(Integer typeId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioItemType) sessionFactory.getCurrentSession().get(RadioItemType.class, typeId);
	}

	@Override
	public List<RadioItemType> getAllItemTypes(boolean includeVoided)
			throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioItemType.class);
		
		if (!includeVoided){
			criteria.add(Restrictions.eq("voided", false));
		}
		
		return (List<RadioItemType>) criteria.list();
	}

	@Override
	public void deleteItemType(RadioItemType type) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(type);
	}

	@Override
	public RadioItem saveItem(RadioItem item) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(item);
		return item;
	}

	@Override
	public RadioItem getItem(Integer itemId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioItem) sessionFactory.getCurrentSession().get(RadioItem.class, itemId);
	}

	@Override
	public List<RadioItem> getItemByType(RadioItemType type)
			throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession()
								.createCriteria(RadioItem.class)
								.add(Restrictions.eq("type", type));
		
		return (List<RadioItem>) criteria.list();
	}

	@Override
	public List<RadioItem> getAllItems(boolean includeVoided)
			throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioItem.class);
		
		if (!includeVoided){
			criteria.add(Restrictions.eq("voided", false));
		}
		
		return (List<RadioItem>) criteria.list();
	}

	@Override
	public void deleteItem(RadioItem item) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(item);
	}

	@Override
	public RadioTransItem saveTransItem(RadioTransItem item)
			throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(item);
		return item;
	}

	@Override
	public RadioTransItem getTransItem(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioTransItem) sessionFactory.getCurrentSession().get(RadioTransItem.class, id);
	}

}

package org.openmrs.module.radiotest.api.db.hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.db.RadioTransactionDAO;

public class HibernateRadioTransactionDAO implements RadioTransactionDAO {

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
	public RadioTransaction saveTransaction(RadioTransaction trans)
			throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(trans);
		return trans;
	}
}

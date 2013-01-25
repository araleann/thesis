package org.openmrs.module.radiotest.api.db.hibernate;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioNoteType;
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

	@Override
	public RadioTransaction getTransaction(Integer transId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioTransaction) sessionFactory.getCurrentSession().get(RadioTransaction.class, transId);
	}

	@Override
	public RadioNoteType saveNoteType(RadioNoteType noteType)
			throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(noteType);
		return noteType;
	}

	@Override
	public RadioNoteType getNoteType(Integer typeId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioNoteType) sessionFactory.getCurrentSession().get(RadioNoteType.class, typeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RadioNoteType> getAllNoteTypes() throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioNoteType.class);
		return (List<RadioNoteType>) criteria.list();
	}
}

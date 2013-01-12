package org.openmrs.module.radiotest.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.api.db.RadioExamDAO;

public class HibernateRadioExamDAO implements RadioExamDAO {

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
	public RadioExam saveExam(RadioExam exam) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(exam);
		return exam;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RadioExam> getAllExams() throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioExam.class);
		return (List<RadioExam>) criteria.list();
	}

	@Override
	public RadioExam getExam(Integer examId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioExam) sessionFactory.getCurrentSession().get(RadioExam.class, examId);
	}
	
	@Override
	public RadioExamType saveExamType(RadioExamType type) throws DAOException {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(type);
		return type;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RadioExamType> getAllExamTypes() throws DAOException {
		// TODO Auto-generated method stub
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(RadioExamType.class);
		return (List<RadioExamType>) criteria.list();
	}

	@Override
	public RadioExamType getExamType(Integer typeId) throws DAOException {
		// TODO Auto-generated method stub
		return (RadioExamType) sessionFactory.getCurrentSession().get(RadioExamType.class, typeId);
	}	
}

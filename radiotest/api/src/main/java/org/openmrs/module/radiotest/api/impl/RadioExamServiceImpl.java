package org.openmrs.module.radiotest.api.impl;

import java.util.LinkedHashSet;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.radiotest.RadioCategoryExam;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.api.RadioExamService;
import org.openmrs.module.radiotest.api.db.RadioExamDAO;
import org.springframework.transaction.annotation.Transactional;

public class RadioExamServiceImpl extends BaseOpenmrsService implements RadioExamService {

	protected final Log log = LogFactory.getLog(this.getClass());
	
	private RadioExamDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(RadioExamDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public RadioExamDAO getDao() {
	    return dao;
    }
    
	@Override
	public RadioExam saveExam(RadioExam exam) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveExam(exam);
	}
	
	@Override
	public List<RadioExam> getAllExams() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllExams();
	}

	@Override
	public RadioExam getExam(Integer examId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getExam(examId);
	}
	
	@Override
	public RadioExamType saveExamType(RadioExamType type) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveExamType(type);
	}
	
	@Override
	public List<RadioExamType> getAllExamTypes() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllExamTypes();
	}

	@Override
	public RadioExamType getExamType(Integer typeId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getExamType(typeId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioExam> getExamByType(RadioExamType type) throws APIException {
		// TODO Auto-generated method stub
		return dao.getExamByType(type);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioExam updateExam(RadioExam exam) throws APIException {
		// TODO Auto-generated method stub
		exam = dao.getExam(exam.getId());
		exam.setCategoryFees(new LinkedHashSet<RadioCategoryExam>(exam.getCategoryFees()));
		
		return exam;
	}
}

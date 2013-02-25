package org.openmrs.module.radiotest.api.impl;

import java.util.ArrayList;
import java.util.Collections;
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
import org.openmrs.module.radiotest.propertyeditor.RadioComparator;
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
		return dao.getAllExams(false);
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
		return dao.getAllExamTypes(false);
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
		
		List<RadioCategoryExam> catFees = new ArrayList<RadioCategoryExam>(exam.getCategoryFees());
		Collections.sort(catFees, new RadioComparator());
		exam.setCategoryFees(new LinkedHashSet<RadioCategoryExam>(catFees));
		
		return exam;
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioExamType> getAllExamTypes(boolean includeVoided)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllExamTypes(includeVoided);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioExam> getAllExams(boolean includeVoided)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllExams(includeVoided);
	}

	@Override
	public void deleteExamType(RadioExamType type) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteExamType(type);
	}

	@Override
	public void deleteExam(RadioExam exam) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteExam(exam);
	}
}

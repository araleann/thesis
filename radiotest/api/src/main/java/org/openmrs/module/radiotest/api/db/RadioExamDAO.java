package org.openmrs.module.radiotest.api.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;

public interface RadioExamDAO {

	public RadioExamType saveExamType(RadioExamType type) throws DAOException;
	public RadioExamType getExamType(Integer typeId) throws DAOException;
	public List<RadioExamType> getAllExamTypes(boolean includeVoided) throws DAOException;
	public void deleteExamType(RadioExamType type) throws DAOException;
	
	public RadioExam saveExam(RadioExam exam) throws DAOException;
	public List<RadioExam> getAllExams(boolean includeVoided) throws DAOException;
	public RadioExam getExam(Integer examId) throws DAOException;
	public List<RadioExam> getExamByType(RadioExamType type) throws DAOException;
	public void deleteExam(RadioExam exam) throws DAOException;
	
}

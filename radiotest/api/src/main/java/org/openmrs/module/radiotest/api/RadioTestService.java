/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.radiotest.api;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransaction;
import org.springframework.transaction.annotation.Transactional;

/**
 * This service exposes module's core functionality. It is a Spring managed bean which is configured in moduleApplicationContext.xml.
 * <p>
 * It can be accessed only via Context:<br>
 * <code>
 * Context.getService(RadioTestService.class).someMethod();
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
@Transactional
public interface RadioTestService extends OpenmrsService {
    
	// PATIENT SERVICES
	public RadioPatient savePatient(RadioPatient patient) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioPatient getPatient(Integer patientId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioPatient> getAllPatients(boolean includeVoided) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioCategory getCategory(Integer categoryId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioCategory> getAllCategories() throws APIException;
	
	@Transactional(readOnly = true)
	public RadioAlias getAlias(Integer aliasId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioAlias> getAliases(Integer patientId, boolean includeVoided) throws APIException;
	
	// EXAM SERVICES
	public RadioExamType saveExamType(RadioExamType type) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioExamType> getAllExamTypes() throws APIException;
	
	@Transactional(readOnly = true)
	public RadioExamType getExamType(Integer typeId) throws APIException;
	
	public RadioExam saveExam(RadioExam exam) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioExam> getAllExams() throws APIException;
	
	@Transactional(readOnly = true)
	public RadioExam getExam(Integer examId) throws APIException;
	
	// TRANSACTION SERVICES
	public RadioTransaction saveTransaction(RadioTransaction trans) throws APIException;
	
	
}
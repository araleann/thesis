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
package org.openmrs.module.radiotest.api.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioAlias;
import org.openmrs.module.radiotest.RadioCategory;
import org.openmrs.module.radiotest.RadioExam;
import org.openmrs.module.radiotest.RadioExamType;
import org.openmrs.module.radiotest.RadioPatient;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioTestService;

/**
 *  Database methods for {@link RadioTestService}.
 */
public interface RadioTestDAO {
	
	// PATIENT FUNCTIONS
	public RadioPatient savePatient(RadioPatient patient) throws DAOException;
	public RadioPatient getPatient(Integer patientId) throws DAOException;
	public List<RadioPatient> getAllPatients(boolean includeVoided) throws DAOException;
	
	public RadioCategory getCategory(Integer categoryId) throws DAOException;
	public List<RadioCategory> getAllCategories() throws DAOException;
	
	public RadioAlias getAlias(Integer aliasId) throws DAOException;
	public List<RadioAlias> getAliases(Integer patientId, boolean includeVoided) throws DAOException;
	
	// EXAM FUNCTIONS
	public RadioExamType saveExamType(RadioExamType type) throws DAOException;
	public RadioExam saveExam(RadioExam exam) throws DAOException;
	
	// TRANSACTION FUNCTIONS
	public RadioTransaction saveTransaction(RadioTransaction trans) throws DAOException;
}
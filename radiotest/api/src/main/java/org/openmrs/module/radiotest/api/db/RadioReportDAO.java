package org.openmrs.module.radiotest.api.db;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioReport;

public interface RadioReportDAO {
	
	public String generateReport(RadioReport report) throws DAOException;
	
}

package org.openmrs.module.radiotest.api;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.radiotest.RadioReport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RadioReportService extends OpenmrsService {
	
	public String generateReport(RadioReport report) throws APIException;

}

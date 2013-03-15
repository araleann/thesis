package org.openmrs.module.radiotest.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.radiotest.api.RadioReportService;
import org.openmrs.module.radiotest.api.db.RadioReportDAO;

public class RadioReportServiceImpl extends BaseOpenmrsService implements
		RadioReportService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private RadioReportDAO dao;

	public RadioReportDAO getDao() {
		return dao;
	}

	public void setDao(RadioReportDAO dao) {
		this.dao = dao;
	}

}

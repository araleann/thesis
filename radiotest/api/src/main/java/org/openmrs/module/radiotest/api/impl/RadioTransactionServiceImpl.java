package org.openmrs.module.radiotest.api.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.radiotest.RadioTransaction;
import org.openmrs.module.radiotest.api.RadioTransactionService;
import org.openmrs.module.radiotest.api.db.RadioTransactionDAO;

public class RadioTransactionServiceImpl extends BaseOpenmrsService implements RadioTransactionService {

	protected final Log log = LogFactory.getLog(this.getClass());
	
	private RadioTransactionDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(RadioTransactionDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public RadioTransactionDAO getDao() {
	    return dao;
    }
    
	@Override
	public RadioTransaction saveTransaction(RadioTransaction trans)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveTransaction(trans);
	}
}

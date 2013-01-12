package org.openmrs.module.radiotest.api;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.radiotest.RadioTransaction;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RadioTransactionService extends OpenmrsService {
	
	public RadioTransaction saveTransaction(RadioTransaction trans) throws APIException;
}

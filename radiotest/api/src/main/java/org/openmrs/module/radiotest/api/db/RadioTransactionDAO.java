package org.openmrs.module.radiotest.api.db;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.radiotest.RadioTransaction;

public interface RadioTransactionDAO {

	public RadioTransaction saveTransaction(RadioTransaction trans) throws DAOException;
}

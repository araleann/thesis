package org.openmrs.module.radiotest.api.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.radiotest.RadioItem;
import org.openmrs.module.radiotest.RadioItemType;
import org.openmrs.module.radiotest.RadioStockListing;
import org.openmrs.module.radiotest.RadioTransItem;
import org.openmrs.module.radiotest.api.RadioInventoryService;
import org.openmrs.module.radiotest.api.db.RadioInventoryDAO;
import org.springframework.transaction.annotation.Transactional;

public class RadioInventoryServiceImpl extends BaseOpenmrsService implements
		RadioInventoryService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private RadioInventoryDAO dao;
	
	/**
	 * @param dao the dao to set
	 */
	public void setDao(RadioInventoryDAO dao){
		this.dao = dao;
	}
	
	/**
	 * @return the dao
	 */
	public RadioInventoryDAO getDao(){
		return dao;
	}

	@Override
	public RadioItemType saveItemType(RadioItemType type) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveItemType(type);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioItemType getItemType(Integer typeId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getItemType(typeId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioItemType> getAllItemTypes() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllItemTypes(false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioItemType> getAllItemTypes(boolean includeVoided)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllItemTypes(includeVoided);
	}

	@Override
	public RadioItem saveItem(RadioItem item) throws APIException {
		// TODO Auto-generated method stub
		return dao.saveItem(item);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioItem getItem(Integer itemId) throws APIException {
		// TODO Auto-generated method stub
		return dao.getItem(itemId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioItem> getItemByType(RadioItemType type)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getItemByType(type);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioItem> getAllItems() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllItems(false);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioItem> getAllItems(boolean includeVoided)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllItems(includeVoided);
	}

	@Override
	public RadioTransItem saveTransItem(RadioTransItem item)
			throws APIException {
		// TODO Auto-generated method stub
		return dao.saveTransItem(item);
	}

	@Override
	@Transactional(readOnly = true)
	public RadioTransItem getTransItem(Integer id) throws APIException {
		// TODO Auto-generated method stub
		return dao.getTransItem(id);
	}

	@Override
	@Transactional(readOnly = true)
	public void deleteItemType(RadioItemType type) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteItemType(type);
	}

	@Override
	public void deleteItem(RadioItem item) throws APIException {
		// TODO Auto-generated method stub
		dao.deleteItem(item);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RadioStockListing> getAllListings() throws APIException {
		// TODO Auto-generated method stub
		return dao.getAllListings();
	}

}

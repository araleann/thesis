package org.openmrs.module.radiotest.api;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.radiotest.RadioExamItem;
import org.openmrs.module.radiotest.RadioItem;
import org.openmrs.module.radiotest.RadioItemType;
import org.openmrs.module.radiotest.RadioStockListing;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface RadioInventoryService extends OpenmrsService {

	public RadioItemType saveItemType(RadioItemType type) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioItemType getItemType(Integer typeId) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioItemType> getAllItemTypes() throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioItemType> getAllItemTypes(boolean includeVoided) throws APIException;
	
	public void deleteItemType(RadioItemType type) throws APIException;
	
	
	public RadioItem saveItem(RadioItem item) throws APIException;
	
	@Transactional(readOnly = true)
	public RadioItem getItem(Integer itemId) throws APIException;
		
	@Transactional(readOnly = true)
	public List<RadioItem> getItemByType(RadioItemType type) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioItem> getAllItems() throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioItem> getAllItems(boolean includeVoided) throws APIException;
	
	public void deleteItem(RadioItem item) throws APIException;
	
	public RadioItem updateItem(RadioItem item) throws APIException;
		
	
	public List<RadioStockListing> saveListings(List<RadioStockListing> listings) throws APIException;
	
	@Transactional(readOnly = true)
	public List<RadioStockListing> getAllListings() throws APIException;
	
	
	public List<RadioExamItem> saveExamItems(List<RadioExamItem> items) throws APIException;
}

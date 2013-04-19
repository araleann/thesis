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
package org.openmrs.module.radiotest;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Role;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.radiotest.api.RadioPatientService;
import org.openmrs.module.radiotest.utils.RadioConstants;
import org.openmrs.util.PrivilegeConstants;

/**
 * This class contains the logic that is run every time this module is either started or stopped.
 */
public class RadioTestActivator implements ModuleActivator {
	
	protected Log log = LogFactory.getLog(getClass());
		
	/**
	 * @see ModuleActivator#willRefreshContext()
	 */
	public void willRefreshContext() {
		log.info("Refreshing Radio Test Module");
	}
	
	/**
	 * @see ModuleActivator#contextRefreshed()
	 */
	public void contextRefreshed() {
		log.info("Radio Test Module refreshed");
	}
	
	/**
	 * @see ModuleActivator#willStart()
	 */
	public void willStart() {
		log.info("Starting Radio Test Module");
	}
	
	/**
	 * @see ModuleActivator#started()
	 */
	public void started() {
		log.info("Radio Test Module started");
		
		UserService us = Context.getUserService();
		Context.addProxyPrivilege(PrivilegeConstants.MANAGE_ROLES);
		
		if(us.getRole(RadioConstants.HEAD_ROLE) == null){
			Role deptHead = new Role(RadioConstants.HEAD_ROLE, "Head/Admin for the Radiology Module");
			deptHead.addPrivilege(us.getPrivilege(PrivilegeConstants.ADD_USERS));
			deptHead.addPrivilege(us.getPrivilege(PrivilegeConstants.VIEW_USERS));
			deptHead.addPrivilege(us.getPrivilege(RadioConstants.EDIT_SETTINGS_PRIVILEGE));
			us.saveRole(deptHead);
		}
		
		if(us.getRole(RadioConstants.EMPLOYEE_ROLE) == null){
			Role deptEmployee = new Role(RadioConstants.EMPLOYEE_ROLE, "Employee for the Radiology Module");
			us.saveRole(deptEmployee);
		}
		
		Context.removeProxyPrivilege(PrivilegeConstants.MANAGE_ROLES);
		
		RadioPatientService ps = Context.getService(RadioPatientService.class);
		if(ps.getCounter() == null){
			RadioCounter.initialize();
		}
	}
	
	/**
	 * @see ModuleActivator#willStop()
	 */
	public void willStop() {
		log.info("Stopping Radio Test Module");
	}
	
	/**
	 * @see ModuleActivator#stopped()
	 */
	public void stopped() {
		log.info("Radio Test Module stopped");
	}
		
}

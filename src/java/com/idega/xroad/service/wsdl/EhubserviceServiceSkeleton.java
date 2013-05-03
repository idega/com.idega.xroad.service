/**
 * EhubserviceServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
package com.idega.xroad.service.wsdl;

import is.idega.idegaweb.egov.message.business.CommuneMessageBusiness;
import is.idega.idegaweb.egov.message.data.UserMessage;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.FinderException;

import net.x_rd.ee.ehubservice.producer.GetMessagesList;
import net.x_rd.ee.ehubservice.producer.GetMessagesListE;
import net.x_rd.ee.ehubservice.producer.GetMessagesListResponse;
import net.x_rd.ee.ehubservice.producer.GetMessagesListResponseE;
import net.x_rd.ee.ehubservice.producer.GetServiceList;
import net.x_rd.ee.ehubservice.producer.GetServiceListE;
import net.x_rd.ee.ehubservice.producer.GetServiceListResponse;
import net.x_rd.ee.ehubservice.producer.GetServiceListResponseE;
import net.x_rd.ee.ehubservice.producer.LabelType;
import net.x_rd.ee.ehubservice.producer.LangType;
import net.x_rd.ee.ehubservice.producer.Message_type0;
import net.x_rd.ee.ehubservice.producer.Request_type8;
import net.x_rd.ee.ehubservice.producer.Request_type9;
import net.x_rd.ee.ehubservice.producer.Response_type10;
import net.x_rd.ee.ehubservice.producer.Response_type5;

import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.User;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;

/**
 * EhubserviceServiceSkeleton java skeleton for the axisService
 */
/**
 * <p>TODO</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 May 2, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public class EhubserviceServiceSkeleton implements
		EhubserviceServiceSkeletonInterface {
	
	protected static final Logger LOGGER = Logger.getLogger(
			EhubserviceServiceSkeleton.class.getName());
	
	private CommuneMessageBusiness communeMessageBusiness;
	
	private UserBusiness userBusiness = null;
	
	/**
	 * Auto generated method signature
	 * 
	 * @param getCaseList54
	 * @return getCaseListResponse61
	 */

	public net.x_rd.ee.ehubservice.producer.GetCaseListResponseE getCaseList(
			net.x_rd.ee.ehubservice.producer.GetCaseListE getCaseList54) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#getCaseList");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getCaseDetails62
	 * @return getCaseDetailsResponse69
	 */

	public net.x_rd.ee.ehubservice.producer.GetCaseDetailsResponseE getCaseDetails(
			net.x_rd.ee.ehubservice.producer.GetCaseDetailsE getCaseDetails62) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#getCaseDetails");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getXFormLabels70
	 * @return getXFormLabelsResponse77
	 */

	public net.x_rd.ee.ehubservice.producer.GetXFormLabelsResponseE getXFormLabels(
			net.x_rd.ee.ehubservice.producer.GetXFormLabelsE getXFormLabels70) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#getXFormLabels");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getNotifications78
	 * @return getNotificationsResponse85
	 */

	public net.x_rd.ee.ehubservice.producer.GetNotificationsResponseE getNotifications(
			net.x_rd.ee.ehubservice.producer.GetNotificationsE getNotifications78) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#getNotifications");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getDocument86
	 * @return getDocumentResponse93
	 */

	public net.x_rd.ee.ehubservice.producer.GetDocumentResponseE getDocument(
			net.x_rd.ee.ehubservice.producer.GetDocumentE getDocument86) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#getDocument");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param allowedMethods94
	 * @return allowedMethodsResponse101
	 */

	public net.x_rd.ee.ehubservice.producer.AllowedMethodsResponse allowedMethods(
			net.x_rd.ee.ehubservice.producer.AllowedMethods allowedMethods94) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#allowedMethods");
	}
	
	/**
	 * 
	 * <p>Searches database for available {@link ServiceEntity} by given 
	 * provider id. If provider id not givent, exception will be thrown.</p>
	 * @param serviceListRequestE - request form service, which contains 
	 * provider id, not <code>null</code>;
	 * @return response filled with data from {@link Collection} of 
	 * {@link ServiceEntity}s.
	 * @throws NullPointerException when service provider id not given
	 * ot nothing found by given provider id.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public GetServiceListResponseE getServiceList(GetServiceListE serviceListRequestE) {
		if (serviceListRequestE == null) {
			throw new java.lang.NullPointerException("Request of class " +
					GetServiceListE.class.getName()  + " is null, probably request is " +
							"not provided.");
		}
				
		GetServiceList serviceListRequest = serviceListRequestE.getGetServiceList();
		if (serviceListRequest == null) {
			throw new java.lang.NullPointerException("Request of class " +
					GetServiceList.class.getName()  + " is null, probably request " +
							" does not have required service list.");
		}

		Request_type9 request = serviceListRequest.getRequest();
		if (request == null) {
			throw new java.lang.NullPointerException(
					Request_type9.class.getName()  + " is null.");
		}

		String serviceProviderId = request.getServiceProviderId();
		if (StringUtil.isEmpty(serviceProviderId)) {
			throw new java.lang.NullPointerException("No provider id is given, " +
					"nothing to return.");
		}
		
//		List<ServiceEntity> services = getServiceEntityDAO().getServices();
//		if (ListUtil.isEmpty(services)) {
//			throw new java.lang.NullPointerException("Unable to find services by " +
//					"provider id: " + serviceProviderId);
//		}
		
		Response_type10 response = new Response_type10();
//		for (ServiceEntity service : services) {
			
			/* Setting service id */
//			ServiceEntry_type0 serviceEntry = new ServiceEntry_type0();
//			serviceEntry.setId(service.getId().toString());
			
			/* Setting label */
//			LabelType labelType = new LabelType();
//			LangType langType = new LangType();
//			langType.setLangType(CoreUtil.getCurrentLocale().getLanguage());
//			labelType.setLang(langType);
//			labelType.setText(service.getName());
//			serviceEntry.setNameLabel(labelType);
			
			/* URI to icon */
//			try {
//				serviceEntry.setIcon(new DataHandler(new URL(service.getIconURI())));
//			} catch (MalformedURLException e) {
//				throw new java.lang.NullPointerException(
//						"Unable to create URL for: " + service.getIconURI());
//			}
			
//			response.addServiceEntry(serviceEntry);
//		}
		
		GetServiceListResponse serviceListResponse = new GetServiceListResponse();
		serviceListResponse.setResponse(response);
		
		GetServiceListResponseE serviceListResponseE = new GetServiceListResponseE();
		serviceListResponseE.setGetServiceListResponse(serviceListResponse);		

		return serviceListResponseE;
	}
	
	/**
	 * 
	 * <p>Searches data source for available messages by given 
	 * service provider id and {@link User} personal id.</p>
	 * @param messagesListRequestE - request form service, which contains 
	 * provider id and {@link User} personal ID, not <code>null</code>;
	 * @return response filled with data from {@link Collection} of 
	 * {@link UserMessage}s.
	 * @throws NullPointerException when service provider id, 
	 * {@link User} personal id not given or nothing found by given credentials.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public GetMessagesListResponseE getMessagesList(GetMessagesListE messagesListRequestE) {
		if (messagesListRequestE == null) {
			throw new NullPointerException(GetMessagesListE.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		GetMessagesList messagesListRequest = messagesListRequestE.getGetMessagesList();
		if (messagesListRequest == null) {
			throw new NullPointerException(GetMessagesList.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		Request_type8 request = messagesListRequest.getRequest();
		if (request == null) {
			throw new NullPointerException(Request_type8.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		String serviceProviderID = request.getServiceProviderId();
		if (StringUtil.isEmpty(serviceProviderID)) {
			throw new NullPointerException("Service provider ID " + 
					"is null. Please provide correct service provider id!");
		}
		
		String personalID = request.getCitizenId();
		if (StringUtil.isEmpty(personalID)) {
			throw new NullPointerException("Personal ID of " + User.class + 
					"is null. Please provide correct personal id!");
		}
		
		Collection<UserMessage> messages = null;
		try {
			messages = getCommuneMessageBusiness().findMessages(getUser(personalID));
		} catch (Exception e) {
			LOGGER.log(Level.WARNING, "Unable to find " + 
					UserMessage.class.getName() + " for " + User.class + 
					" by id: " + personalID, e);
		}
		
		if (ListUtil.isEmpty(messages)) {
			throw new NullPointerException("No messages found for " + 
					User.class.getName() + " by personal id: " + personalID);
		}
		
		Response_type5 response = new Response_type5();
		for (UserMessage message : messages) {
			Message_type0 messageType = new Message_type0();
			messageType.setBody(message.getBody());
			messageType.setDate(message.getCreated());
			messageType.setId(message.getId());
			messageType.setOfficial(message.getOwner().getName());
			messageType.setOrgLabel(getLabelType(message.getSubject()));
			messageType.setSubject(message.getSubject());
			
			response.addMessage(messageType);
		}
		
		GetMessagesListResponse messagesListResponse = new GetMessagesListResponse();
		messagesListResponse.setResponse(response);
		
		GetMessagesListResponseE messagesListResponseE = new GetMessagesListResponseE();
		messagesListResponseE.setGetMessagesListResponse(messagesListResponse);
		
		return messagesListResponseE;
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param submitDocument118
	 * @return submitDocumentResponse125
	 */

	public net.x_rd.ee.ehubservice.producer.SubmitDocumentResponseE submitDocument(
			net.x_rd.ee.ehubservice.producer.SubmitDocumentE submitDocument118) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#submitDocument");
	}

	/**
	 * Auto generated method signature
	 * 
	 * @param getPrefilledDocument126
	 * @return getPrefilledDocumentResponse133
	 */

	public net.x_rd.ee.ehubservice.producer.GetPrefilledDocumentResponseE getPrefilledDocument(
			net.x_rd.ee.ehubservice.producer.GetPrefilledDocumentE getPrefilledDocument126) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#getPrefilledDocument");
	}
	
	protected CommuneMessageBusiness getCommuneMessageBusiness() {
		if (this.communeMessageBusiness != null) {
			return this.communeMessageBusiness;
		}
		
		try {
			this.communeMessageBusiness = IBOLookup.getServiceInstance(
					CoreUtil.getIWContext(), CommuneMessageBusiness.class);
		} catch (IBOLookupException e) {
			LOGGER.log(Level.WARNING, 
					"Unable to get: " + CommuneMessageBusiness.class + ": ", e);
		}
		
		return this.communeMessageBusiness;
	}
	
	protected UserBusiness getUserBusiness() {
		if (this.userBusiness != null) {
			return this.userBusiness;
		}

		try {
			this.userBusiness = IBOLookup.getServiceInstance(
					CoreUtil.getIWContext(), UserBusiness.class);
		} catch (IBOLookupException e) {
			LOGGER.log(Level.WARNING, 
					"Unable to get: " + UserBusiness.class + ": ", e);
		}

		return this.userBusiness;
	}
	
	protected User getUser(String personalId) {
		if (StringUtil.isEmpty(personalId)) {
			return null;
		}
		
		try {
			return getUserBusiness().getUser(personalId);
		} catch (RemoteException e) {
			LOGGER.log(Level.WARNING, "Unable to connect database: ", e);
		} catch (FinderException e) {
			LOGGER.log(Level.WARNING, "Unable to find " + User.class + 
					" by personal id: " + personalId);
		}
		
		return null;
	}
	
	protected LabelType getLabelType(String label) {
		if (StringUtil.isEmpty(label)) {
			return null;
		}
		
		LabelType labelType = new LabelType();
		labelType.setText(label);
		
		LangType langType = new LangType();
		langType.setLangType(CoreUtil.getCurrentLocale().getLanguage());
		labelType.setLang(langType);
		
		return labelType;
	}
}

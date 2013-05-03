/**
 * EhubserviceServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
package com.idega.xroad.service.wsdl;

import net.x_rd.ee.ehubservice.producer.GetServiceList;
import net.x_rd.ee.ehubservice.producer.GetServiceListE;
import net.x_rd.ee.ehubservice.producer.GetServiceListResponse;
import net.x_rd.ee.ehubservice.producer.GetServiceListResponseE;
import net.x_rd.ee.ehubservice.producer.Request_type9;
import net.x_rd.ee.ehubservice.producer.Response_type10;

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
	 * Auto generated method signature
	 * 
	 * @param getMessagesList110
	 * @return getMessagesListResponse117
	 */

	public net.x_rd.ee.ehubservice.producer.GetMessagesListResponseE getMessagesList(
			net.x_rd.ee.ehubservice.producer.GetMessagesListE getMessagesList110) {
		// TODO : fill this with the necessary business logic
		throw new java.lang.UnsupportedOperationException("Please implement "
				+ this.getClass().getName() + "#getMessagesList");
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
}

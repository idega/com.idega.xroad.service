/**
 * EhubserviceServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.1  Built on : Aug 31, 2011 (12:22:40 CEST)
 */
package com.idega.xroad.service.wsdl;

import is.idega.idegaweb.egov.application.data.Application;
import is.idega.idegaweb.egov.message.data.UserMessage;

import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.activation.DataHandler;

import net.x_rd.ee.ehubservice.producer.CaseProcessingStep_type0;
import net.x_rd.ee.ehubservice.producer.Case_type0;
import net.x_rd.ee.ehubservice.producer.GetCaseDetails;
import net.x_rd.ee.ehubservice.producer.GetCaseDetailsE;
import net.x_rd.ee.ehubservice.producer.GetCaseDetailsRequest;
import net.x_rd.ee.ehubservice.producer.GetCaseDetailsResponse;
import net.x_rd.ee.ehubservice.producer.GetCaseDetailsResponseE;
import net.x_rd.ee.ehubservice.producer.GetCaseList;
import net.x_rd.ee.ehubservice.producer.GetCaseListE;
import net.x_rd.ee.ehubservice.producer.GetCaseListRequest;
import net.x_rd.ee.ehubservice.producer.GetCaseListResponse;
import net.x_rd.ee.ehubservice.producer.GetCaseListResponseE;
import net.x_rd.ee.ehubservice.producer.GetMessagesList;
import net.x_rd.ee.ehubservice.producer.GetMessagesListE;
import net.x_rd.ee.ehubservice.producer.GetMessagesListRequest;
import net.x_rd.ee.ehubservice.producer.GetMessagesListResponse;
import net.x_rd.ee.ehubservice.producer.GetMessagesListResponseE;
import net.x_rd.ee.ehubservice.producer.GetServiceList;
import net.x_rd.ee.ehubservice.producer.GetServiceListE;
import net.x_rd.ee.ehubservice.producer.GetServiceListRequest;
import net.x_rd.ee.ehubservice.producer.GetServiceListResponse;
import net.x_rd.ee.ehubservice.producer.GetServiceListResponseE;
import net.x_rd.ee.ehubservice.producer.GetXFormLabels;
import net.x_rd.ee.ehubservice.producer.GetXFormLabelsE;
import net.x_rd.ee.ehubservice.producer.GetXFormLabelsRequest;
import net.x_rd.ee.ehubservice.producer.GetXFormLabelsResponse;
import net.x_rd.ee.ehubservice.producer.GetXFormLabelsResponseE;
import net.x_rd.ee.ehubservice.producer.LabelPair_type0;
import net.x_rd.ee.ehubservice.producer.LabelType;
import net.x_rd.ee.ehubservice.producer.LangType;
import net.x_rd.ee.ehubservice.producer.Message_type0;
import net.x_rd.ee.ehubservice.producer.Response_type10;
import net.x_rd.ee.ehubservice.producer.Response_type12;
import net.x_rd.ee.ehubservice.producer.Response_type3;
import net.x_rd.ee.ehubservice.producer.Response_type5;
import net.x_rd.ee.ehubservice.producer.Response_type8;
import net.x_rd.ee.ehubservice.producer.ServiceEntry_type0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.block.form.data.XForm;
import com.idega.block.process.data.Case;
import com.idega.core.business.DefaultSpringBean;
import com.idega.idegaweb.IWMainApplication;
import com.idega.idegaweb.IWMainApplicationSettings;
import com.idega.user.data.User;
import com.idega.util.CoreConstants;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.datastructures.map.MapUtil;
import com.idega.util.expression.ELUtil;
import com.idega.xroad.service.business.ApplicationService;
import com.idega.xroad.service.business.BPMCasesService;
import com.idega.xroad.service.business.CasesService;
import com.idega.xroad.service.business.LegacyCasesService;
import com.idega.xroad.service.business.XFormService;

/**
 * EhubserviceServiceSkeleton java skeleton for the axisService
 */
/**
 * <p>Implementation of EHub service for X-Road</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 May 2, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class EhubserviceServiceSkeleton extends DefaultSpringBean implements
		EhubserviceServiceSkeletonInterface {
	
	protected static final Logger LOGGER = Logger.getLogger(
			EhubserviceServiceSkeleton.class.getName());
	
	@Autowired
	private XFormService xFormService;
	
	@Autowired
	private BPMCasesService bpmCasesService = null;
	
	@Autowired
	private LegacyCasesService legacyCasesService = null;
	
	@Autowired
	private ApplicationService applicationService = null;
	
	// providerId = process id
	/**
	 * 
	 * <p>Searches database for available {@link Case}s by given 
	 * service provider id an {@link User#getId()}. 
	 * If service provider id not given, exception will be thrown.</p>
	 * @param caseListRequestE - request from client, which contains 
	 * service provider id and {@link User#getId()}, not <code>null</code>;
	 * @return response filled with data from {@link Collection} of 
	 * {@link Case}s.
	 * @throws NullPointerException when service provider id not given
	 * or nothing found by given provider id.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public GetCaseListResponseE getCaseList(GetCaseListE caseListRequestE) {
		if (caseListRequestE == null) {
			throw new NullPointerException(GetCaseListE.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		
		GetCaseList caseListRequest = caseListRequestE.getGetCaseList();
		if (caseListRequest == null) {
			throw new NullPointerException(GetCaseList.class.getName() + 
					" is null. Please provide correct request!");
		}
				
		GetCaseListRequest request = caseListRequest.getRequest();
		if (request == null) {
			throw new NullPointerException(GetCaseListRequest.class.getName() + 
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
		
		Collection<Case> activeCases = getCasesService(null).getCases(personalID);
		if (ListUtil.isEmpty(activeCases)) {
			throw new NullPointerException("No cases found for " + 
					User.class.getName() + " by personalID: " + personalID);
		}
		
		Response_type3 response = new Response_type3();
		for (Case activeCase : activeCases) {
			Case_type0 caseType = new Case_type0();
			caseType.setId(activeCase.getCaseIdentifier());
			caseType.setLastOperationDate(getCasesService(activeCase)
					.getLastOperationDate(activeCase, null));
			caseType.setNameLabel(getLabelType(activeCase.getSubject()));
			caseType.setNextOperationTime(null);
			caseType.setOfficial(getCasesService(activeCase).getOfficialName(activeCase));
			caseType.setOrgLabel(getLabelType(getOrganizationName()));
			caseType.setServiceId(serviceProviderID);
			caseType.setStatusLabel(
					getLabelType(getCasesService(activeCase).getStatus(activeCase, null)));
			caseType.setTypeLabel(getLabelType(getApplicationService()
					.getServiceDescription(activeCase)));
				
			response.add_case(caseType);
		}
		
		GetCaseListResponse caseListResponse = new GetCaseListResponse();
		caseListResponse.setResponse(response);
		caseListResponse.setRequest(request);
				
		GetCaseListResponseE caseListResponseE = new GetCaseListResponseE();
		caseListResponseE.setGetCaseListResponse(caseListResponse);
		return caseListResponseE;
	}

	/**
	 * 
	 * <p>Searches database for {@link Case} by given 
	 * service provider id an {@link Case#getCaseIdentifier()}. 
	 * If service provider id or case identifier not given, 
	 * exception will be thrown.</p>
	 * @param caseDetailsRequestE - request from client, which contains 
	 * service provider id and {@link Case#getCaseIdentifier()}, 
	 * not <code>null</code>;
	 * @return response filled with data from {@link Case}.
	 * @throws NullPointerException when service provider id not given
	 * or nothing found by given case identifier.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public GetCaseDetailsResponseE getCaseDetails(
			GetCaseDetailsE caseDetailsRequestE) {
		if (caseDetailsRequestE == null) {
			throw new NullPointerException(GetCaseDetailsE.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		GetCaseDetails caseDetails = caseDetailsRequestE.getGetCaseDetails();
		if (caseDetails == null) {
			throw new NullPointerException(GetCaseDetails.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		GetCaseDetailsRequest request = caseDetails.getRequest();
		if (request == null) {
			throw new NullPointerException(GetCaseDetailsRequest.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		String serviceProviderID = request.getServiceProviderId();
		if (StringUtil.isEmpty(serviceProviderID)) {
			throw new NullPointerException("Service provider ID " + 
					"is null. Please provide correct service provider id!");
		}
		
		String caseID = request.getCaseId();
		if (StringUtil.isEmpty(caseID)) {
			throw new NullPointerException("Case ID " + 
					"is null. Please provide correct case id!");
		}
		
		Case selectedCase = getCasesService(null).getCase(caseID);
		if (selectedCase == null) {
			throw new NullPointerException(Case.class.getName() + " by id: " +
					caseID + " not found!");
		}
		
		List<Long> ids = getCasesService(selectedCase)
				.getStepIDs(selectedCase);
		if (ListUtil.isEmpty(ids)) {
			throw new NullPointerException(Case.class.getName() + " by id: " +
					caseID + " does not have steps!");
		}
		
		Response_type8 response = new Response_type8();
		for (long id : ids) {
			CaseProcessingStep_type0 caseProcessingStep = new CaseProcessingStep_type0();
			caseProcessingStep.setDocumentId(getCasesService(selectedCase)
					.getFirstDocumentID(selectedCase, id));
			caseProcessingStep.setOfficial(getCasesService(selectedCase)
					.getOfficialName(selectedCase));
			caseProcessingStep.setOperationDate(getCasesService(selectedCase)
					.getLastOperationDate(selectedCase, id));
			caseProcessingStep.setOrgLabel(getLabelType(getOrganizationName()));
			caseProcessingStep.setStatusLabel(getLabelType(
					getCasesService(selectedCase).getStatus(selectedCase, id)));
			caseProcessingStep.setStepId(String.valueOf(id));
			caseProcessingStep.setStepName(getCasesService(selectedCase)
					.getName(selectedCase, id));
			caseProcessingStep.setStepUrl(
					getCasesService(selectedCase).getStepURL(selectedCase, id));
			
			response.addCaseProcessingStep(caseProcessingStep);
		}
				
		GetCaseDetailsResponse caseDetailsResponse = new GetCaseDetailsResponse();
		caseDetailsResponse.setResponse(response);
		caseDetailsResponse.setRequest(request);
		
		GetCaseDetailsResponseE caseDetailsResponseE = new GetCaseDetailsResponseE();
		caseDetailsResponseE.setGetCaseDetailsResponse(caseDetailsResponse);
		
		return caseDetailsResponseE;
	}

	/**
	 * 
	 * <p>Searches database for localized labels of {@link XForm} by given 
	 * service provider id, language and {@link XForm#getFormId()}. 
	 * If service provider id or case identifier not given, 
	 * exception will be thrown.</p>
	 * @param xFormLabelsE - request from client, which contains 
	 * service provider id, language and {@link XForm#getFormId()}, 
	 * not <code>null</code>;
	 * @return response filled with labels from {@link XForm}.
	 * @throws NullPointerException when service provider id not given
	 * or nothing found by given {@link XForm} identifier.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public GetXFormLabelsResponseE getXFormLabels(GetXFormLabelsE xFormLabelsE) {
		if (xFormLabelsE == null) {
			throw new NullPointerException(GetXFormLabelsE.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		GetXFormLabels xformsLabels = xFormLabelsE.getGetXFormLabels();
		if (xformsLabels == null) {
			throw new NullPointerException(GetXFormLabels.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		GetXFormLabelsRequest request = xformsLabels.getRequest();
		if (request == null) {
			throw new NullPointerException(GetXFormLabelsRequest.class.getName() + 
					" is null. Please provide correct request!");
		}
		
		String serviceProviderID = request.getServiceProviderId();
		if (StringUtil.isEmpty(serviceProviderID)) {
			throw new NullPointerException("Service provider ID " + 
					"is null. Please provide correct service provider id!");
		}
		
		String xformID = request.getXFormId();
		if (StringUtil.isEmpty(xformID)) {
			throw new NullPointerException("XForm ID " + 
					"is null. Please provide correct xforms id!");
		}

		LangType language = request.getLang();
		if (language == null) {
			throw new NullPointerException("XForm language not provided. " + 
					" Please provide labels language!");
		}
		
		Map<String, String> labels = getXFormService()
				.getXFormLabels(xformID, language.getLangType());
		if (MapUtil.isEmpty(labels)) {
			throw new NullPointerException("No labels for " + 
					XForm.class.getName() + " by id: " + xformID);
		}
		
		Response_type12 response = new Response_type12();
		for (String key : labels.keySet()) {
			LabelPair_type0 labelPair = new LabelPair_type0();
			labelPair.setKey(key);
			labelPair.setLabel(labels.get(key));
			response.addLabelPair(labelPair);
		}	
		
		GetXFormLabelsResponse xFormLabelsResponse = new GetXFormLabelsResponse();
		xFormLabelsResponse.setResponse(response);
		xFormLabelsResponse.setRequest(request);
		
		GetXFormLabelsResponseE xFormsLabelsResponseE = new GetXFormLabelsResponseE();
		xFormsLabelsResponseE.setGetXFormLabelsResponse(xFormLabelsResponse);
		
		return xFormsLabelsResponseE;
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

		GetServiceListRequest request = serviceListRequest.getRequest();
		if (request == null) {
			throw new java.lang.NullPointerException(
					GetServiceListRequest.class.getName()  + " is null.");
		}
		
		String serviceProviderId = request.getServiceProviderId();
		if (StringUtil.isEmpty(serviceProviderId)) {
			throw new java.lang.NullPointerException("No provider id is given, " +
					"nothing to return.");
		}
		
		Collection<Application> services = getApplicationService().getServices();
		if (ListUtil.isEmpty(services)) {
			throw new java.lang.NullPointerException("No services found on this system!");
		}
		
		Response_type10 response = new Response_type10();
		for (Application service : services) {
			
			/* Setting service id */
			ServiceEntry_type0 serviceEntry = new ServiceEntry_type0();
			serviceEntry.setId(service.getPrimaryKey().toString());
			
			/* Setting label */
			LabelType labelType = new LabelType();
			LangType langType = new LangType();
			langType.setLangType(CoreUtil.getCurrentLocale().getLanguage());
			labelType.setLang(langType);
			labelType.setText(service.getName());
			serviceEntry.setNameLabel(labelType);
			
			/* Setting application icon */
			URL icon = getApplicationService().getServiceLogoURL(service);
			if (icon != null) {
				serviceEntry.setIcon(new DataHandler(icon));
			}
			
			response.addServiceEntry(serviceEntry);
		}
		
		GetServiceListResponse serviceListResponse = new GetServiceListResponse();
		serviceListResponse.setResponse(response);
		serviceListResponse.setRequest(request);
		
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
		
		GetMessagesListRequest request = messagesListRequest.getRequest();
		if (request == null) {
			throw new NullPointerException(GetMessagesListRequest.class.getName() + 
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
		
		Collection<UserMessage> messages = getCasesService(null).getMessages(personalID);
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
			messageType.setOfficial(getCasesService(message).getOfficialName(message));
			messageType.setOrgLabel(getLabelType(getOrganizationName()));
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

	protected LabelType getLabelType(String label) {
		LabelType labelType = new LabelType();
		labelType.setText(label);
		
		LangType langType = new LangType();
		langType.setLangType(CoreUtil.getCurrentLocale().getLanguage());
		labelType.setLang(langType);
		
		return labelType;
	}
	
	protected String getOrganizationName() {
		IWMainApplicationSettings settings = IWMainApplication
				.getDefaultIWMainApplication().getSettings();
		if (settings == null) {
			return null;
		}
		
		return settings.getProperty(CoreConstants.ORGANIZATION_NAME, "Idega");
	}
	
	protected CasesService getCasesService(Case theCase) {		
		if (theCase == null || StringUtil.isEmpty(theCase.getCaseManagerType())) {
			if (this.legacyCasesService == null) {
				ELUtil.getInstance().autowire(this);
			}
			
			return this.legacyCasesService;
		} else {
			if (this.bpmCasesService == null) {
				ELUtil.getInstance().autowire(this);
			}
			
			return this.bpmCasesService;
		}
	}
	
	protected XFormService getXFormService() {
		if (this.xFormService == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return this.xFormService;
	}
	
	protected ApplicationService getApplicationService() {
		if (this.applicationService == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return this.applicationService;
	}
}

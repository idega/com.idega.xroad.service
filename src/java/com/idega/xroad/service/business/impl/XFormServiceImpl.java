/**
 * @(#)XFormServiceImpl.java    1.0.0 1:02:07 PM
 *
 * Idega Software hf. Source Code Licence Agreement x
 *
 * This agreement, made this 10th of February 2006 by and between 
 * Idega Software hf., a business formed and operating under laws 
 * of Iceland, having its principal place of business in Reykjavik, 
 * Iceland, hereinafter after referred to as "Manufacturer" and Agura 
 * IT hereinafter referred to as "Licensee".
 * 1.  License Grant: Upon completion of this agreement, the source 
 *     code that may be made available according to the documentation for 
 *     a particular software product (Software) from Manufacturer 
 *     (Source Code) shall be provided to Licensee, provided that 
 *     (1) funds have been received for payment of the License for Software and 
 *     (2) the appropriate License has been purchased as stated in the 
 *     documentation for Software. As used in this License Agreement, 
 *     Licensee shall also mean the individual using or installing 
 *     the source code together with any individual or entity, including 
 *     but not limited to your employer, on whose behalf you are acting 
 *     in using or installing the Source Code. By completing this agreement, 
 *     Licensee agrees to be bound by the terms and conditions of this Source 
 *     Code License Agreement. This Source Code License Agreement shall 
 *     be an extension of the Software License Agreement for the associated 
 *     product. No additional amendment or modification shall be made 
 *     to this Agreement except in writing signed by Licensee and 
 *     Manufacturer. This Agreement is effective indefinitely and once
 *     completed, cannot be terminated. Manufacturer hereby grants to 
 *     Licensee a non-transferable, worldwide license during the term of 
 *     this Agreement to use the Source Code for the associated product 
 *     purchased. In the event the Software License Agreement to the 
 *     associated product is terminated; (1) Licensee's rights to use 
 *     the Source Code are revoked and (2) Licensee shall destroy all 
 *     copies of the Source Code including any Source Code used in 
 *     Licensee's applications.
 * 2.  License Limitations
 *     2.1 Licensee may not resell, rent, lease or distribute the 
 *         Source Code alone, it shall only be distributed as a 
 *         compiled component of an application.
 *     2.2 Licensee shall protect and keep secure all Source Code 
 *         provided by this this Source Code License Agreement. 
 *         All Source Code provided by this Agreement that is used 
 *         with an application that is distributed or accessible outside
 *         Licensee's organization (including use from the Internet), 
 *         must be protected to the extent that it cannot be easily 
 *         extracted or decompiled.
 *     2.3 The Licensee shall not resell, rent, lease or distribute 
 *         the products created from the Source Code in any way that 
 *         would compete with Idega Software.
 *     2.4 Manufacturer's copyright notices may not be removed from 
 *         the Source Code.
 *     2.5 All modifications on the source code by Licencee must 
 *         be submitted to or provided to Manufacturer.
 * 3.  Copyright: Manufacturer's source code is copyrighted and contains 
 *     proprietary information. Licensee shall not distribute or 
 *     reveal the Source Code to anyone other than the software 
 *     developers of Licensee's organization. Licensee may be held 
 *     legally responsible for any infringement of intellectual property 
 *     rights that is caused or encouraged by Licensee's failure to abide 
 *     by the terms of this Agreement. Licensee may make copies of the 
 *     Source Code provided the copyright and trademark notices are 
 *     reproduced in their entirety on the copy. Manufacturer reserves 
 *     all rights not specifically granted to Licensee.
 *
 * 4.  Warranty & Risks: Although efforts have been made to assure that the 
 *     Source Code is correct, reliable, date compliant, and technically 
 *     accurate, the Source Code is licensed to Licensee as is and without 
 *     warranties as to performance of merchantability, fitness for a 
 *     particular purpose or use, or any other warranties whether 
 *     expressed or implied. Licensee's organization and all users 
 *     of the source code assume all risks when using it. The manufacturers, 
 *     distributors and resellers of the Source Code shall not be liable 
 *     for any consequential, incidental, punitive or special damages 
 *     arising out of the use of or inability to use the source code or 
 *     the provision of or failure to provide support services, even if we 
 *     have been advised of the possibility of such damages. In any case, 
 *     the entire liability under any provision of this agreement shall be 
 *     limited to the greater of the amount actually paid by Licensee for the 
 *     Software or 5.00 USD. No returns will be provided for the associated 
 *     License that was purchased to become eligible to receive the Source 
 *     Code after Licensee receives the source code. 
 */
package com.idega.xroad.service.business.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.chiba.web.IWBundleStarter;
import org.chiba.web.WebAdapter;
import org.chiba.web.session.XFormsSession;
import org.chiba.web.session.XFormsSessionManager;
import org.chiba.web.session.impl.DefaultXFormsSessionManagerImpl;
import org.chiba.xml.xforms.XFormsConstants;
import org.chiba.xml.xforms.config.XFormsConfigException;
import org.chiba.xml.xforms.exception.XFormsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.w3c.dom.Node;

import com.idega.block.form.data.XForm;
import com.idega.block.form.data.dao.XFormsDAO;
import com.idega.chiba.web.session.impl.IdegaXFormSessionManagerImpl;
import com.idega.chiba.web.upload.XFormTmpFileResolverImpl;
import com.idega.core.business.DefaultSpringBean;
import com.idega.idegaweb.IWBundle;
import com.idega.presentation.IWContext;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;
import com.idega.util.xml.XmlUtil;
import com.idega.xformsmanager.business.Document;
import com.idega.xformsmanager.business.DocumentManager;
import com.idega.xformsmanager.business.DocumentManagerFactory;
import com.idega.xroad.service.business.XFormService;
/**
 * @see XFormService
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 May 9, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
@Service(XFormService.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class XFormServiceImpl extends DefaultSpringBean implements XFormService {

	@Autowired
	private XFormsDAO xFormsDAO;

	@Autowired
	private DocumentManagerFactory documentManagerFactory;

	/*
	 * (non-Javadoc)
	 * @see com.idega.xroad.service.business.XFormService#getXFormTemplate(java.lang.String)
	 */
	@Override
	public org.w3c.dom.Document getXFormTemplate(String xFormID) {
		if (StringUtil.isEmpty(xFormID)) {
			return null;
		}
		
		return getXFormsDocument(getXForm(xFormID));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.idega.xroad.service.business.XFormService#getLoadedXFormDocument(java.lang.String)
	 */
	@Override
	public org.w3c.dom.Document getLoadedXFormDocument(	String xFormID) {
		if (StringUtil.isEmpty(xFormID)) {
			return null;
		}
		
		return getInstantiatedXFormsDocument(getXForm(xFormID));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.idega.xroad.service.business.XFormService#getXFormLabels(java.lang.String, java.lang.String)
	 */
	@Override
	public Map<String, String> getXFormLabels(String xFormID, String language) {
		if (xFormID == null) {
			return Collections.emptyMap();
		}
		
		if (StringUtil.isEmpty(language)) {
			language = getCurrentLocale().getLanguage();
		}
		
		org.w3c.dom.Document document = getXFormsDocument(getXForm(xFormID));
		if (document == null) {
			return Collections.emptyMap();
		}
		
		List<Node> localizedStringsNodes = XmlUtil.getChildNodes(
				document.getDocumentElement(), 
				null, "localized_strings", null, null);
		if (ListUtil.isEmpty(localizedStringsNodes) || localizedStringsNodes.size() > 1) {
			return Collections.emptyMap();
		}
		
		List<Node> requiredNodes = XmlUtil.getChildNodes(
				localizedStringsNodes.get(0).getOwnerDocument().getDocumentElement(), 
				null, null, "lang", language);
		if (ListUtil.isEmpty(requiredNodes)) {
			return Collections.emptyMap();
		}
		
		Map<String, String> labels = new TreeMap<String, String>();
		for (Node requiredNode : requiredNodes) {
			labels.put(requiredNode.getNodeName(), requiredNode.getTextContent());
		}
		
		return labels;
	}
	
	protected XForm getXForm(String xForm) {
		if (StringUtil.isEmpty(xForm)) {
			return null;
		}
		
		return getXFormsDAO().getXFormById(Long.valueOf(xForm));
	}
	
	protected org.w3c.dom.Document getXFormsDocument(XForm xForm) {
		if (xForm == null) {
			return null;
		}
		
		DocumentManager documentmanagerLocal = getDocumentManagerFactory()
				.newDocumentManager(getApplication());
		if (documentmanagerLocal == null) {
			return null;
		}
		
		Document document = documentmanagerLocal.openFormLazy(
				xForm.getFormId());
		if (document == null) {
			return null;
		}
		
		return document.getXformsDocument();
	}
	
	protected org.w3c.dom.Document getInstantiatedXFormsDocument(XForm xForm) {
		org.w3c.dom.Document document = getXFormsDocument(xForm);
		
		IWContext iwc = CoreUtil.getIWContext();
		
		HttpServletRequest request = iwc.getRequest();
		HttpServletResponse response = iwc.getResponse();
		HttpSession session = iwc.getSession();
		
		XFormsSessionManager sessionManager = null;
		XFormsSession xformsSession = null;
		WebAdapter adapter = null;
		
		try {
			sessionManager = getXFormsSessionManager(session);
		} catch (XFormsConfigException e) {
			getLogger().log(Level.WARNING, "Unable to get " + 
					XFormsSessionManager.class + " cause of: ", e);
		}
		
		try {
			xformsSession = sessionManager
					.createXFormsSession(request, response, session);
			adapter = xformsSession.getAdapter();
			setupAdapter(adapter, document, xformsSession, iwc);
			adapter.init();
			return ((org.w3c.dom.Document) adapter.getXForms());
		} catch (XFormsException e) {
			getLogger().log(Level.WARNING, 
					"Unable to get instantiated XForm document", e);
		}
		
		return null;
	}
	
	protected XFormsSessionManager getXFormsSessionManager(
			HttpSession session) throws XFormsConfigException {
		XFormsSessionManager manager = (XFormsSessionManager) session
				.getAttribute(XFormsSessionManager.XFORMS_SESSION_MANAGER);
		if (manager == null) {
			manager = DefaultXFormsSessionManagerImpl.createXFormsSessionManager(
					IdegaXFormSessionManagerImpl.class.getName());
			session.setAttribute(
					XFormsSessionManager.XFORMS_SESSION_MANAGER, manager);
		}

		return manager;
	}
	
	protected void setupAdapter(WebAdapter adapter, 
			org.w3c.dom.Document document, XFormsSession xformsSession, 
			FacesContext context) throws XFormsException {
		adapter.setXFormsSession(xformsSession);
		adapter.setXForms(document);

		Map<String, String> servletMap = new HashMap<String, String>();
		servletMap.put(WebAdapter.SESSION_ID, xformsSession.getKey());
		adapter.setContextParam(XFormsConstants.SUBMISSION, servletMap);

		IWBundle bundle = getApplication().getBundle(
				IWBundleStarter.BUNDLE_IDENTIFIER);
		adapter.setBaseURI(bundle.getResourcesVirtualPath());
		adapter.setUploadDestination(XFormTmpFileResolverImpl.UPLOADS_PATH);
		// storeCookies(request, adapter);
	}
	
	protected XFormsDAO getXFormsDAO() {
		if (this.xFormsDAO == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return this.xFormsDAO;
	}
	
	protected DocumentManagerFactory getDocumentManagerFactory() {
		if (this.documentManagerFactory == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return this.documentManagerFactory;
	}
}

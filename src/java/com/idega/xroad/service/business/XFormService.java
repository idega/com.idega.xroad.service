/**
 * @(#)XFormService.java    1.0.0 1:00:07 PM
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
package com.idega.xroad.service.business;

import is.idega.idegaweb.egov.application.data.Application;

import java.util.Collections;
import java.util.Map;

import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.def.Task;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.w3c.dom.Document;

import com.idega.block.form.data.XForm;
import com.idega.jbpm.exe.TaskInstanceW;
import com.idega.user.data.User;

/**
 * <p>Services for X-Road connected to XForms.</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 May 9, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
public interface XFormService {
	
	public static final String BEAN_NAME = "xFormService";

	/**
	 * 
	 * @param personalID -  {@link User#getPersonalID()}, not <code>null</code>;
	 * @param applicationID -  {@link Application#getPrimaryKey()}, not 
	 * <code>null</code>;
	 * @param taskID - is {@link TaskInstance#getId()}, if <code>null</code>
	 * then new {@link ProcessInstance} will be created and first task will 
	 * be returned;
	 * @param language the form should be translated to, if <code>null</code>
	 * the {@link Document} in default language is returned.
	 * @return {@link XForm} {@link Document} filled with data from 
	 * {@link com.idega.user.data.User}
	 * and prepared for XSLT transformation or <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Document getPrefilledDocument(
			String personalID, 
			String applicationID, 
			String taskID, 
			String language);

	/**
	 * 
	 * @param taskID is {@link TaskInstance#getId()}, not <code>null</code>;
	 * @param language the form should be translated to, if <code>null</code>
	 * the {@link Document} in default language is returned.
	 * @return filled {@link Document} of {@link Task} by 
	 * {@link TaskInstance#getId()} which ir prepared for XSLT transformation
	 * or <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Document getPrefilledDocument(String taskID, String language);
	
	/**
	 * 
	 * <p>Prepares {@link XForm} document to be transformed with
	 * XSLT transformation and puts all variable values to it.</p>
	 * @param taskInstanceID is {@link TaskInstance#getId()} by which 
	 * {@link XForm} {@link Document} should be taken, not <code>null</code>;
	 * @return processed {@link XForm}, which should be passed to XSLT 
	 * transformation to get HTML {@link Document} or 
	 * <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Document getProcessedXFormDocument(long taskInstanceID);

	/**
	 * 
	 * <p>Prepares {@link XForm} document to be transformed with
	 * XSLT transformation and puts all variable values to it.</p>
	 * @param xFormID is {@link XForm#getFormId()}, not <code>null</code>;
	 * @return processed {@link XForm}, which should be passed to XSLT 
	 * transformation to get HTML {@link Document} or 
	 * <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Document getProcessedXFormDocument(String xFormID);
	
	/**
	 * 
	 * <p>Prepares {@link XForm} document to be transformed with
	 * XSLT transformation and puts all variable values to it.</p>
	 * @param taskInstance by which {@link XForm} {@link Document} should be 
	 * taken, not <code>null</code>;
	 * @return processed {@link XForm}, which should be passed to XSLT 
	 * transformation to get HTML {@link Document} or 
	 * <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Document getProcessedXFormDocument(TaskInstanceW taskInstance);
	
	/**
	 * <p>Reads tags in "localized_strings" tag, which has lang attribute
	 * equal to given language or current language, when language not given.</p>
	 * @param taskInstanceID is {@link TaskInstance#getId()} by which 
	 * {@link XForm} {@link Document} should be taken, not <code>null</code>;
	 * @param language to get tag values translated to, when <code>null</code>
	 * tags of system language will be returned.
	 * @return {@link Map} where key is tag name and value is text value of 
	 * tag or {@link Collections#emptyMap()} on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Map<String, String> getXFormLabels(long taskInstanceID, String language);

	/**
	 * <p>Reads tags in "localized_strings" tag, which has lang attribute
	 * equal to given language or current language, when language not given.</p>
	 * @param xFormID is {@link XForm#getFormId()}, not <code>null</code>;
	 * @param language to get tag values translated to, when <code>null</code>
	 * tags of system language will be returned.
	 * @return {@link Map} where key is tag name and value is text value of 
	 * tag or {@link Collections#emptyMap()} on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Map<String, String> getXFormLabels(String xFormID, String language);

	/**
	 * <p>Loads empty {@link XForm} {@link Document}.</p>
	 * @param taskInstanceID is {@link TaskInstance#getId()} by which 
	 * {@link XForm} {@link Document} should be taken, not <code>null</code>;
	 * @return empty {@link XForm} or <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Document getXFormTemplate(long taskInstanceID);

	/**
	 * <p>Loads empty {@link XForm} {@link Document}.</p>
	 * @param xFormID is {@link XForm#getFormId()} of form to load,
	 * not <code>null</code>;
	 * @return empty {@link XForm} or <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Document getXFormTemplate(String xFormID);

	/**
	 * <p>Loads empty {@link XForm} {@link Document}.</p>
	 * @param taskInstance by which {@link XForm} {@link Document} should be 
	 * taken, not <code>null</code>;
	 * @return empty {@link XForm} or <code>null</code> on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Document getXFormTemplate(TaskInstanceW taskInstanceW);
}

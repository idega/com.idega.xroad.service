/**
 * @(#)CasesService.java    1.0.0 12:51:00 PM
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
 *     �Licensee� shall also mean the individual using or installing 
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

import is.idega.idegaweb.egov.cases.business.CasesBusiness;
import is.idega.idegaweb.egov.message.business.CommuneMessageBusiness;
import is.idega.idegaweb.egov.message.data.UserMessage;

import java.net.URL;
import java.rmi.RemoteException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.FinderException;

import com.idega.block.process.business.CaseBusiness;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseLog;
import com.idega.block.process.data.CaseLogHome;
import com.idega.block.process.data.CaseStatus;
import com.idega.block.process.data.CaseStatusHome;
import com.idega.block.process.message.presentation.UserMessages;
import com.idega.business.IBOLookup;
import com.idega.business.IBOLookupException;
import com.idega.core.business.DefaultSpringBean;
import com.idega.data.IDOLookup;
import com.idega.data.IDOLookupException;
import com.idega.user.business.UserBusiness;
import com.idega.user.data.Group;
import com.idega.user.data.User;
import com.idega.util.CoreConstants;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.xroad.service.wsdl.EhubserviceServiceSkeleton;

/**
 * <p>Service for providing data to {@link EhubserviceServiceSkeleton} 
 * realization.</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 May 6, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */

public abstract class CasesService extends DefaultSpringBean{
	
	private CommuneMessageBusiness communeMessageBusiness = null;
	
	private CasesBusiness casesBusiness;
	
	private UserBusiness userBusiness = null;
	
	private CaseLogHome caseLogHome = null;
	
	private CaseStatusHome caseStatusHome = null;
	
	/**
	 * 
	 * @param personalID - {@link User#getPersonalID()}, not <code>null</code>;
	 * @return {@link UserMessage}s of {@link Case}s or <code>null</code>
	 * on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Collection<UserMessage> getMessages(String personalID) {
		if (StringUtil.isEmpty(personalID)) {
			return null;
		}
		
		return getMessages(getUser(personalID));
	}
	
	/**
	 * 
	 * @param user - {@link User}, which can view {@link UserMessages},
	 * not <code>null</code>;
	 * @return {@link UserMessage}s of {@link Case}s or <code>null</code>
	 * on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Collection<UserMessage> getMessages(User user) {
		try {
			return getCommuneMessageBusiness().findMessages(user);
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, "Unable to connect data source: ", e);
		} catch (Exception e) {
			getLogger().log(Level.WARNING, "No " + UserMessage.class + 
					" found!");
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param caseIdentifier - {@link Case#getCaseIdentifier()}, 
	 * not <code>null</code>;
	 * @return {@link Case} by identifier or <code>null</code>
	 * on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Case getCase(String caseIdentifier) {
		if (StringUtil.isEmpty(caseIdentifier)) {
			return null;
		}
		
		try {
			return getCasesBusiness().getCaseByIdentifier(caseIdentifier);
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, "Unable to connect data source:", e);
		} catch (FinderException e) {
			getLogger().log(Level.WARNING, Case.class + " not found");
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param personalID - {@link User#getPersonalID()}, not <code>null</code>;
	 * @return {@link Case}s for {@link User} or <code>null</code> on failure.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Collection<Case> getCases(String personalID) {
		if (StringUtil.isEmpty(personalID)) {
			return null;
		}
		
		return getCases(getUser(personalID));
	}
	
	/**
	 * 
	 * @param user - {@link User}, who has permission to view {@link Case}s,
	 * not <code>null</code>;
	 * @return {@link Case}s for {@link User} or <code>null</code> on failure.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public Collection<Case> getCases(User user) {
		if (user == null) {
			return null;
		}
		
		try {
			return getCasesBusiness().getAllCasesForUser(user);
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, "Unable to connect datasource: ", e);
		} catch (FinderException e) {
			getLogger().log(Level.WARNING, "Unable to find cases for user: " + 
					user.getName());
		}
		
		return null;
	}
	
	/**
	 * 
	 * @param theCase to search handler for, not <code>null</code>
	 * @return name of handler or his {@link Group}, 
	 * {@link CoreConstants#EMPTY} on failure;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getOfficialName(Case theCase);
	
	/**
	 * 
	 * <p>Takes handler id of the {@link Case}.</p>
	 * @param theCase to get handler of, not <code>null</code>;
	 * @return {@link User#getPersonalID()} of handler or <code>null</code>
	 * on failure.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getOfficialID(Case theCase);
	
	/**
	 * 
	 * @param theCase which log should be found, not <code>null</code>;
	 * @return {@link Date} when status was changed last time or 
	 * <code>null</code> on failure.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract Date getLastOperationDate(Case theCase, Long stepID);
	
	/**
	 * 
	 * @param theCase which status should be found, not <code>null</code>;
	 * @return name of {@link CaseStatus} by the given {@link Case}, 
	 * or <code>null</code> on failure.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public String getStatus(Case theCase, Long stepID) {
		if (theCase == null)  {
			return null;
		}

		CaseLog log = getCaseLog(theCase, stepID);
		if (log == null) {
			return null;
		}

		try {
			return getCasesBusiness().getLocalizedCaseStatusDescription(
					theCase, getCaseStatus(log.getStatusAfter()), getCurrentLocale());
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, "Failed to get localized case " +
					"status for " + log.getStatusAfter(), e);
		}

		return null;
	}
	
	/**
	 * 
	 * @param theCase which name or its task name should be found, 
	 * not <code>null</code>;
	 * @param stepId - id of task in {@link Case}, which name should be found.
	 * Name of {@link Case} will be provided when <code>null</code>;
	 * @return name of task of {@link Case} or {@link Case} name or 
	 * <code>null</code> on failure.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getName(Case theCase, Long stepId);
		
	/**
	 * 
	 * @param theCase which has task, not <code>null</code>;
	 * @param stepID - id of task, which has attachments, not <code>null</code>;
	 * @return first document ID in {@link Collection} which is found by
	 * given criteria.
	 * @deprecated use {@link CasesService#getDocumentsIDs(Case, Long)} instead,
	 * this will be removed as soon as possible.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public String getFirstDocumentID(Case theCase, Long stepID) {
		if (theCase == null || stepID == null) {
			return CoreConstants.EMPTY;
		}
		
		List<String> documentsIDs = getDocumentsIDs(theCase, stepID);
		if (ListUtil.isEmpty(documentsIDs)) {
			return CoreConstants.EMPTY;
		}
		
		return documentsIDs.get(0);
	}
	
	/**
	 * 
	 * @param theCase which has task, not <code>null</code>;
	 * @param stepID - id of task, which has attachments, not <code>null</code>;
	 * @return {@link List} of document ids or {@link Collections#emptyList()} 
	 * on failure.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract List<String> getDocumentsIDs(Case theCase, Long stepID);
	
	/**
	 * 
	 * @param theCase which tasks should be found, not <code>null</code>;
	 * @return {@link List} of ids of tasks, which can be provided by 
	 * given {@link Case} or <code>null</code> on failure. 
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract List<Long> getStepIDs(Case theCase);
	
	/**
	 * `
	 * @param theCase which data of task is required, not <code>null</code>;
	 * @param stepID - task id, which {@link URL} is required, 
	 * not <code>null</code>;
	 * @return url to task or <code>null</code> on failure.
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract String getStepURL(Case theCase, Long stepID);
	
	/**
	 * 
	 * <p>Checks if given step of {@link Case} is submitted.</p>
	 * @param theCase to check, not <code>null</code>;
	 * @param stepId is {@link TaskInstance#getId()} or 
	 * {@link CaseLog#getPrimaryKey()}, not <code>null</code>;
	 * @return <code>true</code> when submitted, <code>false</code> when not 
	 * and <code>null</code> on error;
	 * @author <a href="mailto:martynas@idega.com">Martynas Stakė</a>
	 */
	public abstract Boolean isSubmitted(Case theCase, Long stepId);
	
	protected CaseLog getCaseLog(Case theCase, Long stepID) {
		if (theCase == null) {
			return null;
		}

		try {
			if (stepID == null) {
				return getCaseLogHome().findLastCaseLogForCase(theCase);
			} else {
				return getCaseLogHome().findByPrimaryKey(stepID);
			}

		} catch (FinderException e) {
			getLogger().log(Level.WARNING, "Unable to find " + CaseLog.class + 
					" by case: " + theCase);
		}

		return null;
	}
	
	protected CaseLogHome getCaseLogHome() {
		if (this.caseLogHome != null) {
			return this.caseLogHome;
		}
		
		this.caseLogHome = null;
		try {
			this.caseLogHome = (CaseLogHome) IDOLookup.getHome(CaseLog.class);
		} catch (IDOLookupException e) {
			getLogger().log(Level.WARNING, "Unable to find home class for entity "
					+ CaseLog.class.getName() + " cause of:" , e);
		}
		
		return this.caseLogHome;
	}
	
	protected CaseStatusHome getCaseStatusHome() {
		if (this.caseStatusHome != null) {
			return this.caseStatusHome;
		}
		
		try {
			return (CaseStatusHome) IDOLookup.getHome(CaseStatus.class);
		} catch (IDOLookupException e) {
			getLogger().log(Level.WARNING, "Failed to get home for " + 
					CaseStatus.class + " cause of: " , e);
		}
		
		return null;
	}
	
	protected CaseStatus getCaseStatus(Object primaryKey) {
		if (primaryKey == null) {
			return null;
		}

		try {
			return getCaseStatusHome().findByPrimaryKey(primaryKey);
		} catch (FinderException e) {
			getLogger().log(Level.WARNING, "Unable to find " + 
					CaseStatus.class + " by id: " + primaryKey, e);
		}

		return null;
	}
	
	protected User getUser(String personalId) {
		if (StringUtil.isEmpty(personalId)) {
			return null;
		}
		
		try {
			return getUserBusiness().getUser(personalId);
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, "Unable to connect database: ", e);
		} catch (FinderException e) {
			getLogger().log(Level.WARNING, "Unable to find " + User.class + 
					" by personal id: " + personalId);
		}
		
		return null;
	}
	
	protected CasesBusiness getCasesBusiness() {
		if (this.casesBusiness != null) {
			return this.casesBusiness;
		}
		
		try {
			this.casesBusiness = IBOLookup.getServiceInstance(
					CoreUtil.getIWContext(), CasesBusiness.class);
		} catch (IBOLookupException e) {
			getLogger().log(Level.WARNING, 
					"Unable to get: " + CaseBusiness.class + ": ", e);
		}
		
		return this.casesBusiness;
	}
	
	protected CommuneMessageBusiness getCommuneMessageBusiness() {
		if (this.communeMessageBusiness != null) {
			return this.communeMessageBusiness;
		}
		
		try {
			this.communeMessageBusiness = IBOLookup.getServiceInstance(
					CoreUtil.getIWContext(), CommuneMessageBusiness.class);
		} catch (IBOLookupException e) {
			getLogger().log(Level.WARNING, 
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
			getLogger().log(Level.WARNING, 
					"Unable to get: " + UserBusiness.class + ": ", e);
		}

		return this.userBusiness;
	}
	
	protected Collection<CaseLog> getCaseLogs(
			Case theCase, Long fromDate, Long toDate, User performer) {
		if (theCase == null) {
			return null;
		}
		
		try {
			return getCaseLogHome().findAllCaseLogs(
					theCase, 
					fromDate != null ? new Timestamp(fromDate) : null, 
					toDate != null ? new Timestamp(toDate) : null, 
					performer);
		} catch (FinderException e) {
			getLogger().log(Level.WARNING, "Unable find " + CaseLog.class + 
					" for " + Case.class.getName() + " by id:  " + 
					theCase.getCaseIdentifier());
		}
		
		return null;
	}
}

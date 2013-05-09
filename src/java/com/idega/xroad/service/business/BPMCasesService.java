/**
 * @(#)BPMCasesService.java    1.0.0 2:31:03 PM
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

import is.idega.idegaweb.egov.bpm.cases.manager.BPMCasesRetrievalManagerImpl;
import is.idega.idegaweb.egov.bpm.cases.presentation.beans.CasesBPMAssetsState;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import org.jbpm.taskmgmt.exe.TaskInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.block.process.business.CasesRetrievalManager;
import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseLog;
import com.idega.block.process.presentation.UserCases;
import com.idega.core.builder.business.BuilderServiceFactory;
import com.idega.jbpm.exe.BPMFactory;
import com.idega.jbpm.exe.ProcessInstanceW;
import com.idega.jbpm.exe.ProcessManager;
import com.idega.jbpm.exe.TaskInstanceW;
import com.idega.jbpm.identity.BPMUser;
import com.idega.jbpm.variables.BinaryVariable;
import com.idega.user.data.User;
import com.idega.util.CoreConstants;
import com.idega.util.CoreUtil;
import com.idega.util.ListUtil;
import com.idega.util.StringUtil;
import com.idega.util.expression.ELUtil;

/**
 * <p>Cases service for BPM cases.</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 May 6, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
@Service(BPMCasesService.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class BPMCasesService extends CasesService {

	public static final String BEAN_NAME = "bpmCasesService";
	
	@Autowired
	@Qualifier(BPMCasesRetrievalManagerImpl.beanIdentifier)
	private CasesRetrievalManager casesRetrievalManager;
	
	@Autowired
	private BPMFactory bpmFactory;
	
	/* (non-Javadoc)
	 * @see com.idega.xroad.service.business.CasesService#getName(com.idega.block.process.data.Case)
	 */
	@Override
	public String getName(Case theCase, Long taskInstanceID) {
		if (theCase == null) {
			return null;
		}
		
		if (taskInstanceID == null) {
			return theCase.getSubject();
		}
		
		TaskInstanceW taskInstance = getTaskInstanceW(theCase, taskInstanceID);
		if (taskInstance == null) {
			return null;
		}
		
		return taskInstance.getName(getCurrentLocale());
	}

	/* (non-Javadoc)
	 * @see com.idega.xroad.service.business.CasesService#getDocumentsIDs(com.idega.block.process.data.Case)
	 */
	@Override
	public List<String> getDocumentsIDs(Case theCase, Long taskInstanceID) {
		if (taskInstanceID == null || theCase == null) {
			return null;
		}

		TaskInstanceW taskInstance = getTaskInstanceW(theCase, taskInstanceID);
		if (taskInstance == null || !taskInstance.isSubmitted()) {
			return null;
		}

		List<BinaryVariable> attachments = taskInstance.getAttachments();
		if (ListUtil.isEmpty(attachments)) {
			return null;
		}

		List<String> attachmentsIDs = new ArrayList<String>();
		for (BinaryVariable variable: attachments) {
			attachmentsIDs.add(variable.getIdentifier());
		}

		return attachmentsIDs;
	}

	/* (non-Javadoc)
	 * @see com.idega.xroad.service.business.CasesService#getStepID(com.idega.block.process.data.Case)
	 */
	@Override
	public List<Long> getStepIDs(Case theCase) {
		if (theCase == null) {
			return null;
		}
		
		List<TaskInstanceW> taskInstances = getTaskInstancesW(theCase);
		if (ListUtil.isEmpty(taskInstances)) {
			return null;
		}
		
		ArrayList<Long> ids = new ArrayList<Long>(taskInstances.size());
		for (TaskInstanceW taskInstance: taskInstances) {
			ids.add(taskInstance.getTaskInstanceId());
		}
		
		return ids;
	}

	/* (non-Javadoc)
	 * @see com.idega.xroad.service.business.CasesService#getStepURL(com.idega.block.process.data.Case)
	 */
	@Override
	public String getStepURL(Case theCase, Long taskInstanceID) {
		if (theCase == null || taskInstanceID == null) {
			return null;
		}
		
		TaskInstanceW taskInstance = getTaskInstanceW(theCase, taskInstanceID);
		if (taskInstance == null) {
			return null;
		}
		
		String assetsPageURL = null;
		try {
			assetsPageURL = BuilderServiceFactory.getBuilderService(
					CoreUtil.getIWContext()).getFullPageUrlByPageType(
							CoreUtil.getIWContext(),
							BPMUser.defaultAssetsViewPageType, true);
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, "Unable to get " + 
					com.idega.core.builder.business.BuilderService.class + 
					" cause of: ", e);
		}
		
		if (StringUtil.isEmpty(assetsPageURL)) {
			return null;
		}
		
		/* http://lydheilsusjodurtest.sidan.is/pages/minlydheilsusjodur/minarumsoknir/umsoknir/?prm_case_pk=492&tiId=847&cp_prm_action=8 */
		StringBuilder url = new StringBuilder(assetsPageURL);
		url.append(CoreConstants.QMARK)
		.append(UserCases.PARAMETER_CASE_PK)
		.append(CoreConstants.EQ).append(theCase.getId())
		.append(CoreConstants.AMP)
		.append(CasesBPMAssetsState.TASK_INSTANCE_ID_PARAMETER)
		.append(CoreConstants.EQ)
		.append(taskInstanceID)
		.append(CoreConstants.AMP)
		.append(UserCases.PARAMETER_ACTION)
		.append(CoreConstants.EQ)
		.append(UserCases.ACTION_CASE_MANAGER_VIEW);
	
		return url.toString();
	}
	
	@Override
	public String getOfficialName(Case theCase) {
		if (theCase == null) {
			return CoreConstants.EMPTY;
		}
		
		User user = getOfficial(theCase);
		if (user == null) {
			return CoreConstants.EMPTY;
		}
		
		return user.getName();
	}

	@Override
	public String getOfficialID(Case theCase) {
		if (theCase == null) {
			return CoreConstants.EMPTY;
		}
		
		User user = getOfficial(theCase);
		if (user == null) {
			return CoreConstants.EMPTY;
		}
		
		return user.getPersonalID();
	}

	protected ProcessInstanceW getProcessInstance(Case selectedCase) {
		if (selectedCase == null) {
			return null;
		}
		
		Long processInstanceID = getCasesRetrievalManager()
				.getProcessInstanceId(selectedCase);
		if (processInstanceID == null) {
			return null;
		}
		
		ProcessManager processManager = getBPMFactory()
				.getProcessManagerByProcessInstanceId(processInstanceID);
		if (processManager == null) {
			return null;
		}
		
		return processManager.getProcessInstance(processInstanceID);
	}
	
	protected CasesRetrievalManager getCasesRetrievalManager() {
		if (this.casesRetrievalManager == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return this.casesRetrievalManager;
	}
	
	protected BPMFactory getBPMFactory() {
		if (this.bpmFactory == null) {
			ELUtil.getInstance().autowire(this);
		}
		
		return this.bpmFactory;
	}
	
	protected List<TaskInstanceW> getTaskInstancesW(Case theCase) {
		if (theCase == null) {
			return null;
		}
		
		return getProcessInstance(theCase).getAllTaskInstances();
	}
	
	protected TaskInstanceW getTaskInstanceW(Case theCase, Long taskInstanceID) {
		if (theCase == null || taskInstanceID == null) {
			return null;
		}
		
		List<TaskInstanceW> taskInstances = getTaskInstancesW(theCase);
		if (ListUtil.isEmpty(taskInstances)) {
			return null;
		}
		
		for (TaskInstanceW taskInstance: taskInstances) {
			if (taskInstanceID.equals(taskInstance.getTaskInstanceId())) {
				return taskInstance;
			}
		}

		return null;
	}
	
	protected User getOfficial(Case theCase) {
		if (theCase == null) {
			return null;
		}
		
		ProcessInstanceW processInstance = getProcessInstance(theCase);
		if (processInstance == null) {
			return null;
		}
		
		Integer handlerID = processInstance.getHandlerId();
		if (handlerID == null) {
			return null;
		}
		
		try {
			return getUserBusiness().getUser(handlerID);
		} catch (RemoteException e) {
			getLogger().log(
					Level.WARNING, 
					User.class + " not found by id: " + 
					processInstance.getHandlerId(), e);
		}
		
		return null;
	}

	@Override
	public Date getLastOperationDate(Case theCase, Long taskInstanceID) {
		if (theCase == null) {
			return null;
		}
		
		if (taskInstanceID == null) {
			CaseLog caselog = getCaseLog(theCase, null);
			if (caselog == null) {
				return null;
			}
			
			return caselog.getTimeStamp();
		}
			
		TaskInstanceW taskInstanceW = getTaskInstanceW(theCase, taskInstanceID);
		if (taskInstanceW == null) {
			return null;
		}
		
		TaskInstance taskInstance = taskInstanceW.getTaskInstance();
		if (taskInstance == null) {
			return null;
		}
		
		Date date = taskInstance.getEnd();
		if (date != null) {
			return date;
		}
		
		date = taskInstance.getStart();
		if (date != null) {
			return date;
		}
		
		date = taskInstance.getCreate();
		if (date != null) {
			return date;
		}
		
		return null;
	}
	
	protected CaseLog getCaseLog(Date timeCreated, Case theCase, String actorPrimaryKey) {
		if (theCase == null) {
			return null;
		}
		
		Long incrementedTime = null; 
		Long decrementedTime = null; 
		if (timeCreated != null) {
			incrementedTime = timeCreated.getTime() + 30000;
			decrementedTime = timeCreated.getTime() - 30000;
		}
		
		User user = null;
		if (!StringUtil.isEmpty(actorPrimaryKey)) {
			try {
				user = getUserBusiness().getUser(Integer.valueOf(actorPrimaryKey));
			} catch (NumberFormatException e) {
				getLogger().log(Level.WARNING, 
						"Unable to parse " + Integer.class.getName() + 
						" from: " + actorPrimaryKey);
			} catch (RemoteException e) {
				getLogger().log(Level.WARNING, "Unable to connect data source:", e);
			}
		}

		Collection<CaseLog> logs = getCaseLogs(theCase, decrementedTime, incrementedTime, user);
		if (ListUtil.isEmpty(logs)) {
			return null;
		}
		
		return logs.iterator().next();
	}
	
	@Override
	public String getStatus(Case theCase, Long taskInstanceID) {
		if (theCase == null) {
			return CoreConstants.EMPTY;
		}
		
		if (taskInstanceID == null) {
			return super.getStatus(theCase, null);
		}
		
		TaskInstanceW taskInstanceW = getTaskInstanceW(theCase, taskInstanceID);
		if (taskInstanceW == null) {
			return CoreConstants.EMPTY;
		}
		
		TaskInstance taskInstance = taskInstanceW.getTaskInstance();
		if (taskInstance == null) {
			return CoreConstants.EMPTY;
		}
		
		Date end = taskInstance.getEnd();
		if (end == null) {
			return CoreConstants.EMPTY;
		}
		
		CaseLog caseLog = getCaseLog(end, theCase, taskInstance.getActorId());
		if (caseLog == null) {
			return CoreConstants.EMPTY;
		}
		
		try {
			return getCasesBusiness().getLocalizedCaseStatusDescription(
					theCase, 
					getCaseStatus(caseLog.getStatusAfter()), getCurrentLocale());
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, "Failed to get localized case " +
					"status for " + caseLog.getStatusAfter(), e);
		}
		
		return null;
	}
}


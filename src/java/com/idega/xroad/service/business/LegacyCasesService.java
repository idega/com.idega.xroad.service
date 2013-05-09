/**
 * @(#)LegacyCasesService.java    1.0.0 2:31:44 PM
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

import is.idega.idegaweb.egov.cases.presentation.CaseViewer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

import javax.ejb.FinderException;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.idega.block.process.data.Case;
import com.idega.block.process.data.CaseLog;
import com.idega.builder.bean.AdvancedProperty;
import com.idega.builder.business.BuilderLogic;
import com.idega.user.data.Group;
import com.idega.util.CoreConstants;
import com.idega.util.ListUtil;

/**
 * <p>Service for {@link Case}s, which are not BPM {@link Case}s.</p>
 * <p>You can report about problems to: 
 * <a href="mailto:martynas@idega.is">Martynas Stakė</a></p>
 *
 * @version 1.0.0 May 6, 2013
 * @author <a href="mailto:martynas@idega.is">Martynas Stakė</a>
 */
@Service(LegacyCasesService.BEAN_NAME)
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class LegacyCasesService extends CasesService {

	public static final String BEAN_NAME = "legacyCasesService";
	
	/* (non-Javadoc)
	 * @see com.idega.xroad.service.business.CasesService#getName(com.idega.block.process.data.Case)
	 */
	@Override
	public String getName(Case theCase, Long stepID) {
		return getStatus(theCase, stepID);
	}

	/* (non-Javadoc)
	 * @see com.idega.xroad.service.business.CasesService#getDocumentsIDs(com.idega.block.process.data.Case)
	 */
	@Override
	public List<String> getDocumentsIDs(Case theCase, Long stepID) {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.idega.xroad.service.business.CasesService#getStepID(com.idega.block.process.data.Case)
	 */
	@Override
	public List<Long> getStepIDs(Case theCase) {
		if (theCase == null) {
			return null;
		}

		Collection<CaseLog> logs = getCaseLogs(theCase);
		if (ListUtil.isEmpty(logs)) {
			return null;
		}

		List<Long> ids = new ArrayList<Long>(logs.size());
		for (CaseLog log: logs) {
			ids.add(Long.valueOf(log.getPrimaryKey().toString()));
		}

		return ids;
	}

	/* (non-Javadoc)
	 * @see com.idega.xroad.service.business.CasesService#getStepURL(com.idega.block.process.data.Case)
	 */
	@Override
	public String getStepURL(Case theCase, Long stepID) {
		if (theCase == null || stepID == null) {
			return null;
		}
		
		BuilderLogic logic = BuilderLogic.getInstance();
		if (logic == null) {
			return null;
		}
		
		List<AdvancedProperty> parameters = new ArrayList<AdvancedProperty>(2);
		parameters.add(new AdvancedProperty(
				CaseViewer.PARAMETER_CASE_PK, theCase.getId()));
		parameters.add(new AdvancedProperty(
				CaseViewer.PARAMETER_ACTION, 
				String.valueOf(CaseViewer.ACTION_VIEW)));

		return getHost() + logic.getUriToObject(CaseViewer.class, parameters);
	}

	protected Collection<CaseLog> getCaseLogs(Case theCase) {
		if (theCase == null) {
			return null;
		}
		
		try {
			return getCasesBusiness().getCaseLogsByCase(theCase);
		} catch (RemoteException e) {
			getLogger().log(Level.WARNING, 
					"Unable to connect data source: ", e);
		} catch (FinderException e) {
			getLogger().log(
					Level.WARNING, "Unable to get logs by " + 
							Case.class + ": " + theCase);
		}
		
		return null;
	}

	@Override
	public String getOfficialName(Case theCase) {
		if (theCase == null) {
			return CoreConstants.EMPTY;
		}
		
		return theCase.getHandler().getName();
	}

	@Override
	public String getOfficialID(Case theCase) {
		if (theCase == null) {
			return null;
		}
		
		Group handler = theCase.getHandler();
		if (handler == null) {
			return null;
		}
		
		return handler.getPrimaryKey().toString();
	}

	@Override
	public Date getLastOperationDate(Case theCase, Long caseLogId) {
		if (theCase == null) {
			return null;
		}
		
		CaseLog log = getCaseLog(theCase, caseLogId);
		if (log == null) {
			return null;
		}
		
		return log.getTimeStamp();
	}
}

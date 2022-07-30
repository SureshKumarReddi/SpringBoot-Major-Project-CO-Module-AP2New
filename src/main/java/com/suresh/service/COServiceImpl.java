package com.suresh.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suresh.entities.COTriggers;
import com.suresh.entities.EligibilityDetails;
import com.suresh.repository.COTriggersRepository;
import com.suresh.repository.EligibilityDetailsRepository;
import com.suresh.utils.EmailUtils;
import com.suresh.utils.GenerateApprovedPlanPdf;
import com.suresh.utils.GenerateDeniedPlanPdf;

@Service
public class COServiceImpl implements COService {

	@Autowired
	private COTriggersRepository coTriggersRepository;

	@Autowired
	private EligibilityDetailsRepository eligibilityDetailsRepository;

	@Autowired
	private HttpServletResponse response;
	@Autowired
	private EmailUtils email;

	@Override
	public void findALLPendingStatus() throws DocumentException, IOException {
		List<COTriggers> list = coTriggersRepository.findByTriggerStatus("Pending");
		System.out.println(list);
		for (COTriggers triggers : list) {
			EligibilityDetails ed = eligibilityDetailsRepository.findByCaseNo(triggers.getCaseNo());
			if (("APPROVED").equals(ed.getPlanStatus())) {
				 
				GenerateApprovedPlanPdf approvedPdf = new GenerateApprovedPlanPdf(ed);
				approvedPdf.buildApprovedPlanPdf(response);
				email.sendEmail("Co Approved  Plans", ed.toString(), "sureshkumarreddyraina143@gmail.com");
			} else {
				GenerateDeniedPlanPdf deniedPdf = new GenerateDeniedPlanPdf(ed);
				deniedPdf.buildDeniedPlanPd(response);
				email.sendEmail("Co Denied  Plans", ed.toString(), "sureshkumarreddyraina143@gmail.com");
			}
		}
	}

 

	 

}

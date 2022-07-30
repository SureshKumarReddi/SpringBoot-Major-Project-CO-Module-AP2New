package com.suresh.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.entities.COTriggers;
import com.suresh.entities.EligibilityDetails;
import com.suresh.repository.COTriggersRepository;
import com.suresh.repository.EligibilityDetailsRepository;
import com.suresh.service.COService;
import com.suresh.utils.GenerateApprovedPlanPdf;
import com.suresh.utils.GenerateDeniedPlanPdf;

@RestController
public class CORestController {
 
	@Autowired
	public COService service;
	
	@GetMapping
	public void findALLPendingStatus() throws DocumentException, IOException {

		service.findALLPendingStatus();
 
 
	}
 

}









 
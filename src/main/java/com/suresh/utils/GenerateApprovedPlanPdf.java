package com.suresh.utils;

 
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.suresh.entities.EligibilityDetails;


public class GenerateApprovedPlanPdf {
	
	private EligibilityDetails ed;
	
	public GenerateApprovedPlanPdf(EligibilityDetails ed) {
		this.ed = ed;
	}
 
	public void buildApprovedPlanPdf(HttpServletResponse response) throws DocumentException, IOException {
		export(response);
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Case Number", font));

		table.addCell(cell);

		cell.setPhrase(new Phrase(" Name ", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase(" SSN ", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Plan Status", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Start Date ", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("End Date", font));
		table.addCell(cell);
		
		cell.setPhrase(new Phrase("Benefit Amount", font));
		table.addCell(cell);

	}

	private void writeTableData(PdfPTable table) {
		 
			table.addCell(String.valueOf(ed.getCaseNo()));
			table.addCell(ed.getName());
			table.addCell(ed.getSsn());
			table.addCell(String.valueOf(ed.getPlanName()));
			table.addCell(ed.getPlanStatus());
			table.addCell(ed.getStartDate());
			table.addCell(ed.getEndDate());
			table.addCell(String.valueOf(ed.getBenefitAmt()));
		
	}

	public void export(HttpServletResponse response ) throws DocumentException, IOException {
		
		/*
		 * response.setContentType("application/pdf"); DateFormat dateFormatter = new
		 * SimpleDateFormat("yyyy-MM-dd_HH:mm:ss"); String currentDateTime =
		 * dateFormatter.format(new Date());
		 * 
		 * String headerKey = "Content-Disposition"; String headerValue =
		 * "attachment; filename=Triggers_" + currentDateTime + ".pdf";
		 * response.setHeader(headerKey, headerValue);
		 */
		
		Document document = new Document(PageSize.A4);
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Users\\sresh\\Downloads\\triggers.pdf"));
		
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Approved Plan Notice", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f , 3.0f, 3.0f, 1.5f});
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();
		//writer.w
		writer.close();
	}
}

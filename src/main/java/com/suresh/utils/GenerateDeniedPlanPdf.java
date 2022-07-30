package com.suresh.utils;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

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


public class GenerateDeniedPlanPdf {
	
	private EligibilityDetails ed;

	public GenerateDeniedPlanPdf(EligibilityDetails ed) {
		this.ed = ed;
	}
	
	public void buildDeniedPlanPd(HttpServletResponse response) throws DocumentException, IOException {
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
		
		cell.setPhrase(new Phrase("Denial Reason ", font));
		table.addCell(cell);
 

	}

	private void writeTableData(PdfPTable table) {
		 
			table.addCell(String.valueOf(ed.getCaseNo()));
			table.addCell(ed.getName());
			table.addCell(ed.getSsn());
			table.addCell(String.valueOf(ed.getPlanName()));
			table.addCell(ed.getPlanStatus());
			table.addCell(ed.getDenialReason());
		 
		 
	}

	public void export(HttpServletResponse response ) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("Denied Plan Notice", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(6);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f , 3.0f});
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}

<%@page import="com.itextpdf.text.*"%>
<%@page import="com.itextpdf.text.pdf.*"%>
<%@page import="org.openmrs.module.radiotest.model.*"%>
<%@page import="org.openmrs.module.radiotest.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>

<%
	//Accessing the beans
	RadioTransaction transaction = (RadioTransaction) request.getAttribute("transaction");
	RadioPatient patient = transaction.getPatient();
	RadioCategory category = patient.getCategory();
	Set<RadioTransExam> exams = transaction.getExams();
	Double totalAmount = 0.0;
	
	PdfPTable table = new PdfPTable(3);
	Font fontbold = FontFactory.getFont("Arial", 14, Font.BOLD);
	PdfPCell examRow, typeCell, feeCell, row1, row2, row3, totalHead, totalAmt;
	
	//Generating PDF
	response.setContentType("application/pdf");
	Document document = new Document(PageSize.LETTER);
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();
	document.addTitle("Radiology Department");
	
	Paragraph dept = new Paragraph("University of the Philippines Health Services", fontbold);
	dept.setAlignment(Element.ALIGN_CENTER);
	Paragraph uphs = new Paragraph("Radiology Department", fontbold);
	uphs.setAlignment(Element.ALIGN_CENTER);
	
	document.add(dept);
	document.add(uphs);
	document.add(Chunk.NEWLINE);
	
	String fullName = patient.getLastName() + ", " + patient.getFirstName() + " " + patient.getMiddleInitial() + ".";
	row1 = new PdfPCell(new Phrase(fullName)); 
	row1.setColspan(2); 
	table.addCell(row1);
	
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	table.addCell(sdf.format(date));
	
	row2 = new PdfPCell(new Paragraph("Category: " + category.getCategory()));
    row2.setColspan(3);
    table.addCell(row2);
	
	row3 = new PdfPCell(new Paragraph("Exams:\n"));
    row3.setColspan(3);
    table.addCell(row3);
	
	PdfPCell blank; 
	blank = new PdfPCell(new Paragraph(""));
    blank.setColspan(1);
	
	for (RadioTransExam transExam : exams) {		
		RadioExam examDetails = transExam.getExam();
		Paragraph examList = new Paragraph(examDetails.getName());
			 
		examRow = new PdfPCell(examList);
		examRow.setColspan(3);
		table.addCell(examRow);
		
		RadioCategoryExam catFee = examDetails.getFees(category);
		Set <RadioFee> fees = catFee.getFees();
		
		for(RadioFee fee : fees) {
			String type = fee.getType().getName();
			Double amount = fee.getAmount();
			totalAmount = totalAmount + amount;
			String amountStr = String.valueOf(amount);
			
			table.addCell(blank);
				 
			typeCell = new PdfPCell(new Paragraph(type));
			typeCell.setColspan(1);
			
			feeCell = new PdfPCell(new Paragraph("Php " + amountStr + "0"));
			feeCell.setColspan(1);
			feeCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
			
			table.addCell(typeCell);
			table.addCell(feeCell);
		}	
	}

	totalHead = new PdfPCell(new Paragraph("Total Amount:",fontbold));
    totalHead.setColspan(2);
    table.addCell(totalHead);
	
	String totalStr = String.valueOf(totalAmount);
	totalAmt = new PdfPCell(new Paragraph("Php " + totalStr + "0", fontbold));
    totalAmt.setColspan(2);
	totalAmt.setHorizontalAlignment(Element.ALIGN_RIGHT);
    table.addCell(totalAmt);
	
	document.add(table);
	document.close();
%>

<%@page import="com.itextpdf.text.*"%>
<%@page import="com.itextpdf.text.pdf.*"%>
<%@page import="org.openmrs.module.radiotest.model.*"%>
<%@page import="org.openmrs.module.radiotest.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>

<%
	//Accessing the beans
	RadioPatient patient = (RadioPatient) request.getAttribute("patient");
	RadioTransExam transExam = (RadioTransExam) request.getAttribute("transExam");
	RadioResult result = (RadioResult) request.getAttribute("result");
		
	PdfPTable table = new PdfPTable(8);
	table.setWidthPercentage(100);
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	
	Font fontbold = FontFactory.getFont("Arial", 14, Font.BOLD);
	Font radioFont = FontFactory.getFont("Arial", 12, Font.BOLD|Font.UNDERLINE);
	Font bigFont = FontFactory.getFont("Arial", 25);
	Font smallFont = FontFactory.getFont("Arial", 10);
	Font mediumFont = FontFactory.getFont("Arial", 20);
	PdfPCell headerCell, caseWord, caseNumber, radioCell, dateWord, dateVal, name, age;
	
	//Generating PDF
	response.setContentType("application/pdf");
	Document document = new Document(PageSize.LETTER);
	PdfWriter.getInstance(document, response.getOutputStream());
	document.open();
	document.addTitle("Radiology Department");
	
	Paragraph uhs = new Paragraph("THE UNIVERSITY HEALTH SERVICES", fontbold);
	Paragraph up = new Paragraph("\nUniversity of the Philippines\nDiliman, Quezon City");
	Paragraph header = new Paragraph();
	header.add(uhs);
	header.add(up);
	
	headerCell = new PdfPCell( header ); 
	headerCell.setColspan(5); 
	headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	headerCell.setBorder(Rectangle.NO_BORDER);
	table.addCell(headerCell);
	
	caseWord = new PdfPCell(new Phrase("Case Number:")); 
	caseWord.setColspan(1); 
	caseWord.setBorder(Rectangle.NO_BORDER);
	table.addCell(caseWord);
	
	caseNumber = new PdfPCell(new Paragraph(patient.getCaseNumber(),bigFont));
	caseNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
    caseNumber.setColspan(2);
    table.addCell(caseNumber);
	
	Paragraph radiology = new Paragraph("\nRADIOLOGY DEPARTMENT\n\n",radioFont);
	radioCell = new PdfPCell( radiology ); 
	radioCell.setColspan(5); 
	radioCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	radioCell.setBorder(Rectangle.NO_BORDER);
	table.addCell(radioCell);
	
	dateWord = new PdfPCell(new Phrase("Date:")); 
	dateWord.setColspan(1); 
	dateWord.setBorder(Rectangle.NO_BORDER);
	table.addCell(dateWord);
	
	Date date = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
	
	dateVal = new PdfPCell(new Paragraph(sdf.format(date),bigFont));
	dateVal.setHorizontalAlignment(Element.ALIGN_CENTER);
    dateVal.setColspan(2);
    table.addCell(dateVal);
	
	Paragraph nameWord = new Paragraph("Name", smallFont);
	Paragraph nameVal = new Paragraph("Jaymalin, Jean Dominique B.\n", mediumFont);
	Paragraph namePar = new Paragraph();
	namePar.add(nameVal);
	namePar.add(nameWord);
	
	name = new PdfPCell(namePar); 
	name.setColspan(7); 
	name.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(name);
	
	Paragraph ageWord = new Paragraph("Age", smallFont);
	Paragraph ageVal = new Paragraph("19\n", mediumFont);
	Paragraph agePar = new Paragraph();
	agePar.add(ageVal);
	agePar.add(ageWord);
	age = new PdfPCell(agePar); 
	age.setColspan(1); 
	age.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(age);
	
	document.add(table);
	document.close();
%>

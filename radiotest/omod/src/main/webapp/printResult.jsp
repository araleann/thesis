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
	RadioSignature signature = (RadioSignature) request.getAttribute("signature");
	RadioResult result = (RadioResult) request.getAttribute("result");
	RadioCategory category = patient.getCategory();
	RadioTransaction transaction = transExam.getTransaction();
	
	PdfPTable table = new PdfPTable(8);
	table.setWidthPercentage(100);
    table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	
	Font fontbold = FontFactory.getFont("Arial", 14, Font.BOLD);
	Font radioFont = FontFactory.getFont("Arial", 12, Font.BOLD|Font.UNDERLINE);
	Font bigFont = FontFactory.getFont("Arial", 25);
	Font smallFont = FontFactory.getFont("Arial", 10);
	Font mediumFont = FontFactory.getFont("Arial", 20);
	Font resultFont = FontFactory.getFont("Arial", 16);
	
	PdfPCell headerCell, caseWord, caseNumber, radioCell, dateWord, dateVal, name, age, sex;
	PdfPCell examCell, room, categoryCell, purpose, resHeadCell, resContentCell, blank;
	 
	blank = new PdfPCell(new Paragraph(""));
    blank.setColspan(1);
	blank.setBorder(Rectangle.NO_BORDER);
	
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
	
	String fullName = patient.getLastName() + ", " + patient.getFirstName() + " " + patient.getMiddleInitial() + "\n";
	Paragraph nameWord = new Paragraph("Name", smallFont);
	Paragraph nameVal = new Paragraph(fullName, mediumFont);
	Paragraph namePar = new Paragraph();
	namePar.add(nameVal);
	namePar.add(nameWord);	
	name = new PdfPCell(namePar); 
	name.setColspan(6); 
	name.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(name);
	
	int ageNum = patient.getAge();
	String ageStr = String.valueOf(ageNum);
	Paragraph ageWord = new Paragraph("Age", smallFont);
	Paragraph ageVal = new Paragraph(ageStr + "\n", mediumFont);
	Paragraph agePar = new Paragraph();
	agePar.add(ageVal);
	agePar.add(ageWord);
	age = new PdfPCell(agePar); 
	age.setColspan(1); 
	age.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(age);
	
	Paragraph sexWord = new Paragraph("Sex", smallFont);
	Paragraph sexVal = new Paragraph(patient.getGender() + "\n", mediumFont);
	Paragraph sexPar = new Paragraph();
	sexPar.add(sexVal);
	sexPar.add(sexWord);
	sex = new PdfPCell(sexPar); 
	sex.setColspan(1); 
	sex.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(sex);
	
	RadioExam exam = transExam.getExam();
	Paragraph examWord = new Paragraph("Examination", smallFont);
	Paragraph examVal = new Paragraph(exam.getName() + "\n", mediumFont);
	Paragraph examPar = new Paragraph();
	examPar.add(examVal);
	examPar.add(examWord);
	examCell = new PdfPCell(examPar); 
	examCell.setColspan(6); 
	examCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(examCell);
	
	Paragraph roomWord = new Paragraph("Room No.", smallFont);
	Paragraph roomVal = new Paragraph(transaction.getPatientType() + "\n" , mediumFont);
	Paragraph roomPar = new Paragraph();
	roomPar.add(roomVal);
	roomPar.add(roomWord);
	room = new PdfPCell(roomPar); 
	room.setColspan(2); 
	room.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(room);
	
	String catName = category.getCategory() + "\n";
	Paragraph catWord = new Paragraph("Category", smallFont);
	Paragraph catVal = new Paragraph( catName, mediumFont);
	Paragraph catPar = new Paragraph();
	catPar.add(catVal);
	catPar.add(catWord);
	categoryCell = new PdfPCell(catPar); 
	categoryCell.setColspan(2); 
	categoryCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(categoryCell);
	
	Paragraph purposeWord = new Paragraph("Purpose", smallFont);
	Paragraph purposeVal = new Paragraph( transaction.getPurpose() + "\n", mediumFont);
	Paragraph purposePar = new Paragraph();
	purposePar.add(purposeVal);
	purposePar.add(purposeWord);
	purpose = new PdfPCell(purposePar); 
	purpose.setColspan(6); 
	purpose.setHorizontalAlignment(Element.ALIGN_CENTER);
	table.addCell(purpose);
	
	Paragraph resHead = new Paragraph( "\n( ) Roentgenological Report");
	resHeadCell = new PdfPCell(resHead); 
	resHeadCell.setColspan(8); 
	//resHeadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	resHeadCell.setBorder(Rectangle.NO_BORDER);
	table.addCell(resHeadCell);
	
	table.addCell(blank);
	
	Paragraph resContent = new Paragraph("\n\n" + result.getFindings());
	resContentCell = new PdfPCell(resContent); 
	resContentCell.setColspan(7); 
	//resHeadCell.setHorizontalAlignment(Element.ALIGN_CENTER);
	resContentCell.setBorder(Rectangle.NO_BORDER);
	table.addCell(resContentCell);
	
	Paragraph sign = new Paragraph("\n\n"+ signature.getName() + "\n" + signature.getPosition());
	sign.setAlignment(Element.ALIGN_RIGHT);
	document.add(table);
	document.add(new Paragraph(""));

	document.add(Chunk.NEWLINE);
	document.add(Chunk.NEWLINE);
	document.add(sign);
	
	document.close();
%>
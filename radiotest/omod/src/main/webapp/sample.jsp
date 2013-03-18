<%@page import="com.itextpdf.text.*"%>
<%@page import="com.itextpdf.text.pdf.*"%>
<%@page import="org.openmrs.module.radiotest.model.*"%>
<%@page import="org.openmrs.module.radiotest.*"%>
<c:set var="p" value="${ patient }" />

<%
   response.setContentType("application/pdf");
   Document document = new Document(PageSize.A6);
   PdfWriter.getInstance(document, response.getOutputStream());
   document.open();
   document.addTitle("Document Title");
   document.add(new Paragraph("Paragraph line"));
   document.close();
%>
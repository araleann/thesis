<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

Total Number of Exams : ${ numOfExams }
<br>

Payment Details
<br>
<br>

<c:forEach var="transExam" items="${ exams }" varStatus="status">
	Exam No: ${ status.count }
	<br>
	
	Exam Type: ${ transExam.exam.type.type }
	<br>
	
	Exam Name: ${ transExam.exam.name }
	<br>
	
	Exam Fee: ${ transExam.examFee }
	<br>
	
	Reading Fee: ${ transExam.readingFee }
	<br>
	<br>
</c:forEach>

Total Amount Due: ${ total }
<br>
<br>

<button type="button">Print Assessment Form</button>
<br>
<br>
<%@ include file="/WEB-INF/template/footer.jsp"%>
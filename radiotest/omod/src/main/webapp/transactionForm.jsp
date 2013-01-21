<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

Total Number of Exams : ${ numOfExams }
<br>

Payment Details
<br>
<br>

<c:forEach var="transExam" items="${ exams }" varStatus="status">
	<spring:nestedPath path="exam">
		Exam No: ${ status.count }
		<br>
		
		<spring:bind path="type">
		
		</spring:bind>
	</spring:nestedPath>
</c:forEach>

<%@ include file="/WEB-INF/template/footer.jsp"%>
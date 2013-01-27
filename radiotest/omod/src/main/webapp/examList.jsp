<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="exams">
	<form id="exam">
		<input type="hidden" name="examId">
	</form>
	<c:forEach var="transExam" items="${ exams }" varStatus="status">
		Exam Number: ${ status.count }
		<br>
		<c:set var="e" value="${ transExam.exam }" />
		Exam Type: ${ e.type.type }
		<br>
		Exam Name: ${ e.name }
		<br>
		<br>
	</c:forEach>
</div>
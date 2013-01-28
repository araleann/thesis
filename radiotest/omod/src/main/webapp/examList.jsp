<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="exams">
	<c:forEach var="transExam" items="${ exams }" varStatus="status">
		<c:set var="c" value="${ status.count }" />
		Exam Number: ${ c }
		<button type="button" onclick="submit(${ transExam.id }, ${ c })"></button>
		<br>
		<c:set var="e" value="${ transExam.exam }" />
		Exam Type: ${ e.type.type }
		<br>
		Exam Name: ${ e.name }
		<br>
		<br>
	</c:forEach>
</div>
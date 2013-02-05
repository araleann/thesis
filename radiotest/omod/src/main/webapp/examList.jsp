<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="exams">
	<c:forEach var="transExam" items="${ exams }" varStatus="status">
		<c:set var="c" value="${ status.count }" />
		<c:set var="id" value="${ transExam.id }" />
		Exam Number: ${ c }
		<button type="button" onclick="result(${ id }, ${ c })"></button>
		<br>
		<c:set var="e" value="${ transExam.exam }" />
		Exam Type: ${ e.type.type }
		<br>
		Exam Name: ${ e.name }
		<br>
		Borrowed: 
			<c:choose>
				<c:when test="${ transExam.borrowed }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
		<button type="button" onclick="borrow(${ id })"></button>
		<br>
	</c:forEach>
</div>
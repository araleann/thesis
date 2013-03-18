<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="exams">
	<c:forEach var="transExam" items="${ exams }" varStatus="status">
		<c:set var="c" value="${ status.count }" />
		<c:set var="id" value="${ transExam.id }" />
		<div id="exam${ id }">
			<label>Exam Number:</label> ${ c }
			<button type="button" onclick="result(${ id }, ${ c })"></button>
			<br>
			<c:set var="e" value="${ transExam.exam }" />
			<label>Exam Type:</label> ${ e.type.type }
			<br>
			<label>Exam Name:</label> ${ e.name }
			<br>
			<label>Borrowed: </label>
				<c:choose>
					<c:when test="${ transExam.borrowed }">
						YES
					</c:when>
					<c:otherwise>
						NO
					</c:otherwise>
				</c:choose>
			<button type="button" onclick="borrow(${ id })"></button>
		</div>
		<br>
	</c:forEach>
</div>
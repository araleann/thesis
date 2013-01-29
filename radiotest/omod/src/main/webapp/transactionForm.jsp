<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="transaction">
	Transaction Summary
	<br>
	<br>
	
	Total Number of Exams: ${ transaction.numberOfExams }
	<br>
	<br>
	Payment Details:
	<br>
	<br>
	<c:forEach var="transExam" items="${ transaction.exams }" varStatus="status">
		Exam Number: ${ status.count }
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
	<br>
	
	Total Amount Due: ${ transaction.total }
	<br>
	<br>
	
	<button type="button">Print Assessment Form</button>
	<br>
	<br>
	
	<form:form method="post" id="payment">
		<button type="button" onclick="addPayment()">Add Payment</button>
		<br>
		OR Number: <input type="text" name="orNumber">
		<br>
	</form:form>
	<br>
	
	Notes
	<br>
	<div id="notes">
		<c:forEach var="note" items="${ transaction.notes }">
			<c:choose>
				<c:when test="${ empty note.type.id }">
					${ note.description }
				</c:when>
				<c:otherwise>
					${ note.type.name }
				</c:otherwise>
			</c:choose>
			<br>
			${ note.note }
			<br>
			${ note.date }
			<br>
			<br>
		</c:forEach>
	</div>
	
	<form:form method="post" modelAttribute="transModel" id="noteForm">
		<button type="button" onclick="saveNote()">Save Note</button>
		<br>
		<spring:nestedPath path="note">
			<form:select path="type" onchange="noteTypesEvent()" id="noteType">
				<option value=" "></option>
				<form:options items="${ noteTypes }" itemLabel="name" itemValue="id" />
				<option value="0">Others</option>
			</form:select>
			<spring:bind path="description">
				<input type="text" id="desc" name="${ status.expression }" hidden>
			</spring:bind>
			<br>
			<form:textarea path="note" />
		</spring:nestedPath>
</form:form>
</div>
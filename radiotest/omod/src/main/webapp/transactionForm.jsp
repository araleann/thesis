<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/transaction.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/redirect.js" />

<script>
function printAssessment(){
	$j("#transaction")
		.attr("action", GeneralUtils.modulePath("/prntAssmnt.htm"))
		.submit();
}
</script>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>

<c:set var="p" value="${ patient }" />
<fieldset>
<legend><h2>${ p.fullName }</h2></legend>
<div id="details">
<div id="transaction">
	<h2>Transaction Summary</h2>
	<label>Total No. of Exams:</label>${ transaction.numberOfExams }
	<br>
	<br>
	<h3>Payment Details</h3>
	<br>
	<table class="tbldesign">
	<tr>
	<th>Exam Number</th>
	<th>Exam Type</th>
	<th>Exam Name</th>
	<c:forEach var="fee" items="${ feeType }">
		<th>${ fee.name }</th>
	</c:forEach>
	</tr>
	<c:forEach var="transExam" items="${ transaction.exams }" varStatus="status">
		<tr>
		<td> ${ status.count } </td>
		<td> ${ transExam.exam.type.type } </td>
		<td> ${ transExam.exam.name } </td>
		<c:forEach var="fee" items="${ transExam.feeMap }">
			<td align="right"> Php ${ fee.value }0 </td>
		</c:forEach>
		</tr>
	</c:forEach>
	</table>
	<br>
	<label style="color:#009d8e;">Total Amount Due:</label> <font style="font-size:18px; color:#009d8e;">Php ${ transaction.total }0 </font>
	<br><br>
	<button type="button" class="buttondesign" style="width: 180px;" onclick="load_assessment()">Print Assessment</button>
	<br>
	<br>

	<hr>
	<div id="paymentDiv">
	<c:choose>
		<c:when test="${ empty transaction.orNumber }">
			<form:form method="post" id="payment" modelAttribute="transaction" action="javascript:addPayment()">
				<c:set var="paid" value="${ transaction.paid? 'Paid' : 'Not yet paid' }" />
				<br><h3>${ paid }</h3>
				<input type="hidden" name="transId" value="${ transaction.id }">
				<br><input type="text" class="patientinputmedium" placeholder="OR Number" name="orNumber">
				<button type="button" class="buttondesign" style="width: 145px;" onclick="addPayment()">Add Payment</button>
			</form:form>
		</c:when>
		<c:otherwise>
			<br>
			<h3>Paid</h3>
			<font style="font-size:17px; color:#009d8e; font-weight:bold;">OR Number: ${ transaction.orNumber }</font>
		</c:otherwise>
	</c:choose>
	</div>
	<br>
	<br>
	<h3>Notes</h3>
	<div id="notes">
		<c:forEach var="note" items="${ transaction.notes }">
			<c:choose>
				<c:when test="${ empty note.type.id }">
					${ note.description } <br>
				</c:when>
				<c:otherwise>
					${ note.type.name } <br>
				</c:otherwise>
			</c:choose>
			${ note.note }
			<br>
			${ note.date }
			<br><br>
		</c:forEach>
	</div>	
	<form:form method="post" modelAttribute="transModel" id="noteForm">
		<input type="hidden" name="transId" value="${ transaction.id }">
		<spring:nestedPath path="note">
			<form:select path="type" onchange="noteTypesEvent()" cssClass="patientinput" id="noteType">
				<option value=" "></option>
				<form:options items="${ noteTypes }" itemLabel="name" itemValue="id" />
				<option value="0">Others</option>
			</form:select>
			<spring:bind path="description">
				<input type="text" id="desc" name="${ status.expression }" hidden>
			</spring:bind>
			<br><br>
			<form:textarea path="note" cssClass="patientinputtextarea" />
		</spring:nestedPath>
		<br><button type="button" class="searchbutton" onclick="saveNote()">Save Note</button>
</form:form>
<br><br>
<button type="button" class="buttondesign" style="width:150px;background-color:#f29c22;" onclick="load_profile()">Back to Profile</button>
<br><br>
</div>
</div>

</fieldset>

</div>
</div>
<div class="col2">
<div class="sideholder">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
</div>
</div>
</div>
</div>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var saveNotePath = modulePath + "/saveNote.htm"
var transPath = modulePath + "/transactionForm.htm";

function saveNote(){
	$j.post(saveNotePath, $j("#noteForm").serialize(), function(data){
		var $note = $j("#note", $j(data));
		$note.unwrap();
		$j("#notes").prepend($note);
	});
}
function addPayment(){
	$j.post(transPath, $j("#payment").serialize(), function(data){
		alert("Payment added");
	});
}
function noteTypesEvent(){
	var $desc = $j("#desc");
	var isHidden = $desc.attr("hidden");
	var others = $j("#noteType").val() === "0";
	
	if (others == isHidden){
		if (isHidden){
			$desc.removeAttr("hidden");
		} else {
			$desc.attr("hidden", "hidden");
		}
	}
}
function load_profile(){
	GeneralUtils.redirect("patientProfile.htm");
}
//-->
</script>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
<div id="details">
<c:set var="p" value="${ patient }" />
<fieldset>
<legend><h2>${ p.fullName }</h2></legend>
<div id="transaction">
	<h2>Transaction Summary</h2>
	<label>Total No. of Exams:</label>${ transaction.numberOfExams }
	<br>
	<br>
	<h3>Payment Details</h3>
	<br>
	<c:forEach var="transExam" items="${ transaction.exams }" varStatus="status">
		<label>Exam Number:</label> ${ status.count }
		<br><label>Exam Type: </label> ${ transExam.exam.type.type }
		<br><label>Exam Name: </label> ${ transExam.exam.name }
		<br>
		<c:forEach var="fee" items="${ transExam.feeMap }">
			<label>${ fee.key }: </label> ${ fee.value }
			<br>
		</c:forEach>
		<br>
	</c:forEach>
	<label style="color:#009d8e;">Total Amount Due:</label> <font style="font-size:18px; color:#009d8e;">${ transaction.total } </font>
	<br><br>
	<button type="button" class="buttondesignsmalllong">Print Assessment Form</button>
	<br>
	<br>
	<hr>
	<c:choose>
		<c:when test="${ empty transaction.orNumber }">
			<form:form method="post" id="payment" modelAttribute="transaction">
				<br><h3>Not yet paid</h3>
				<input type="hidden" name="transId" value="${ transaction.id }">
				<br><font style="font-size:17px; color:#009d8e; font-weight:bold;">OR Number: </font><input type="text" name="orNumber">
				<button type="button" class="buttondesignsmalllong" onclick="addPayment()">Add Payment</button>
			</form:form>
		</c:when>
		<c:otherwise>
			<br>
			<h3>Paid</h3>
			<font style="font-size:17px; color:#009d8e; font-weight:bold;">OR Number: ${ transaction.orNumber }</font>
		</c:otherwise>
	</c:choose>
	<br>
	<br>
	<h3>Notes</h3>
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
			${ note.note }
			<br>
			${ note.date }
			<br>
		</c:forEach>
	</div>	
	<form:form method="post" modelAttribute="transModel" id="noteForm">
		<input type="hidden" name="transId" value="${ transaction.id }">
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
		<br><button type="button" class="buttondesignsmalllong" onclick="saveNote()">Save Note</button>
</form:form>
<br><br>
<button type="button" class="buttondesignmedium" onclick="load_profile()" style="background-color:#f29c22;">Back to profile</button>
<br><br>
</div>
</div>
</fieldset>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
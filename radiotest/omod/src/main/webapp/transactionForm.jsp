<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

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

function noteTypesEvent(){
	var $desc = $j("#desc");
	var isHidden = $desc.attr("hidden");
	var others = $j("#noteType").val() == 0;
	
	if (others == isHidden){
		if (isHidden){
			$desc.removeAttr("hidden");
		} else {
			$desc.attr("hidden", "hidden");
		}
	}
}

function addPayment(){
	console.log($j("#payment").serialize());
	$j.post(transPath, $j("#payment").serialize(), function(data){
		alert("Payment added");
	});
}

$j(function(){
	GeneralUtils.addPlaceholderById("ornumber", "OR Number");
	
});
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">

<div id="transaction">
	<c:set var="id" value="${ transaction.id }" />
	<h2>Transaction Summary</h2>
	<br>
	<h3>Total Number of Exams:</h3> ${ transaction.numberOfExams }
	<br>
	<h3>Payment Details:</h3>
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
	
	<h3>Total Amount Due:</h3> ${ transaction.total }
	<br>
	<button type="button" class="buttondesignlong">Print Assessment Form</button>
	<br>
	<br>
	
	<form:form method="post" id="payment">
		<button type="button" class="buttondesign" onclick="addPayment()">Add Payment</button>
		<br>
		<br>
		<input type="hidden" name="transId" value="${ id }">
		<input type="text" placeholder="OR Number" class="patientinput" name="orNumber">
		<br>
	</form:form>
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
			<br>
			${ note.note }
			<br>
			${ note.date }
			<br>
			<br>
		</c:forEach>
	</div>
	
	<form:form method="post" modelAttribute="transModel" id="noteForm">
		<spring:bind path="transaction">
			<input type="hidden" name="${ status.expression }" value="${ id }">
		</spring:bind>
		<spring:nestedPath path="note">
			<form:select path="type" cssClass="patientinput" onchange="noteTypesEvent()" id="noteType">
				<option value=" "></option>
				<form:options items="${ noteTypes }" itemLabel="name" itemValue="id" />
				<option value="0">Others</option>
			</form:select>
			<spring:bind path="description">
				<input type="text" id="desc" name="${ status.expression }" hidden>
			</spring:bind>
			<br>
			<br>
			<form:textarea path="note" cssClass="addressinput" />
			<br>
			<br>
			<button type="button" class="buttondesign" onclick="saveNote()">Save Note</button>
		</spring:nestedPath>
</form:form>
</div>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
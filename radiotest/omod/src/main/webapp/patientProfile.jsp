<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var editPatientPath = modulePath + "/editPatient.htm";

function newTransaction(){
	GeneralUtils.redirect("transExamForm.htm");
	//$("#details").load("${pageContext.request.contextPath}/module/radiotest/transExamForm.htm #details");
}

function editPatient(){
	$j.get(editPatientPath, function(data){
		var $form = $j("#patient", $j(data));
		$j("#patient").replaceWith($form);
		
		var inputPlaceholders = {
			firstname: "First Name",
			midname: "Middle Initial",
			lastname: "Last Name",
			alias: "Alias",
			type: "Type",
			philhealth: "Philhealth Number",
			birthday: "Birthday",
			institution: "Institution",
			contactno: "Contact Number",
			region: "Region",
			city: "City",
			barangay: "Barangay/Municipality",
			street: "Street Address"
		};
		GeneralUtils.addPlaceholderById(inputPlaceholders);
		GeneralUtils.addDatepicker($j("#birthday"));
		
	});
}
function load_transaction(){
	$("#details").load("${pageContext.request.contextPath}/module/radiotest/transactions.htm #details");
}
function load_profile(){
	$("#details").load("${pageContext.request.contextPath}/module/radiotest/patientProfile.htm #details");
}
function load_results(){
	$("#details").load("${pageContext.request.contextPath}/module/radiotest/results.htm #details");
}
var modulePath = openmrsContextPath + "/module/radiotest";
var viewPath = modulePath + "/viewTransaction.htm";

function viewTransaction(id){
	$j("#transId").val(id);
	$j("#transaction")
		.attr("action", viewPath)
		.submit();
}
function createTransaction(){
	GeneralUtils.redirect("transExamForm.htm");
}
var modulePath = openmrsContextPath + "/module/radiotest";
var getExamsPath = modulePath + "/getExamList.htm";
var borrowPath = modulePath + "/borrowResults.htm";
var resultPath = modulePath + "/results.htm";

function getExams(){
	console.log($j("#trans").serialize());
	$j.post(getExamsPath, $j("#trans").serialize(), function(data){
		var $list = $j("div#exams", $j(data));
		$j("#exams").replaceWith($list);
	});
}

function result(id, count){
	$j("#examId").val(id);
	$j("#count").val(count);
	$j("#exam")
		.attr("action", resultPath)
		.submit();
}

function borrow(id){
	var examId = "#exam" + id;
	$j.post(borrowPath, { examId: id }, function(data){
		var $updatedDiv = $j(examId, data);
		$j(examId).replaceWith($updatedDiv);
	});
}
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
<c:set var="p" value="${ patient }" />
<fieldset>
<legend><h2>${ p.fullName }</h2></legend>
	<jsp:include page="/WEB-INF/view/topnav.jsp"/>
<div id="details">	
<div id="patient">	
	<p>
	Date of Last Update: ${ p.updateDate }
	</p>
	<h3>Basic Information</h3>
	<br><label>Full Name:</label>${ p.fullName }
	<br>
	<c:set var="c" value="${ p.category }" />
	<label>Category:</label>${ c.category }
	<br>
	<c:set var="a" value="${ p.alias }" />
	<label>Alias: </label> ${ a.alias } 
	<br><label>Philhealth: </label> ${ p.philhealth }
	<br><label>Sex: </label> ${ p.gender }
	<br><label>Civil Status: </label> ${ p.civilStatus }
	<br><label id="patient">Birthday: </label> ${ p.birthday }
	
	<br><br>
	<h3>Contact Details</h3>
	<br><label>Contact Number: </label> ${ p.contactNo }
	<br><label id="patient">Institution: </label> ${ p.institution }

	<br><br>
	<h3>Housing</h3>
	<br><label id="patient">Street Address: </label> ${ p.streetAddress }
	<br><label id="patient">Barangay: </label> ${ p.barangay }
	<br><label id="patient">City: </label> ${ p.city }
	<br><label id="patient">Region: </label> ${ p.region }
	<br><br>
	<button type="button" class="buttondesignmedium" onclick="editPatient()">Edit Profile</button>
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
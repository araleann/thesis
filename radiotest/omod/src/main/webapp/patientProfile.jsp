<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var editPatientPath = modulePath + "/editPatient.htm";

function newTransaction(){
	GeneralUtils.redirect("transExamForm.htm");
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
//-->
</script>

<button type="button" onclick="editPatient()">Edit Patient</button>
<button type="button" onclick="newTransaction()">Create new transaction</button>
<br>
<br>

Patient Profile

<c:set var="p" value="${ patient }" />

<p>
Date of Last Update: ${ p.updateDate }
</p>
<br>

<div id="patient">
	<p>
	Full Name: ${ p.fullName }
	</p>
	
	<br>
	Basic Information
	
	<p>
	<c:set var="c" value="${ p.category }" />
	Category: ${ c.category }
	</p>
	<p>
	<c:set var="a" value="${ p.alias }" />
	Alias: ${ a.alias }
	</p>
	<br>
	<p>
	Philhealth: ${ p.philhealth }
	</p>
	<p>
	Sex: ${ p.gender }
	</p>
	<p>
	Civil Status: ${ p.civilStatus }
	</p>
	<p>
	Birthday: ${ p.birthday }
	</p>
	
	<br>
	Contact Details
	
	<p>
	Contact Number: ${ p.contactNo }
	</p>
	<p>
	Institution: ${ p.institution }
	</p>
	
	<br>
	Housing
	
	<p>
	Street Address: ${ p.streetAddress }
	</p>
	<p>
	Barangay: ${ p.barangay }
	</p>
	<p>
	City: ${ p.city }
	</p>
	<p>
	Region: ${ p.region }
	</p>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>
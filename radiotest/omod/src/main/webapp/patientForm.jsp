<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<html lang="en">
<head>
  <script>
  $(function() {
    $( "#birthday" ).datepicker({
      changeMonth: true,
      changeYear: true,
	  yearRange: '1900:2012',
	  minDate: new Date(1900, 1 - 1, 1),
	  dateFormat: 'dd/mm/yy',
	  maxDate: "+1D",
	  onSelect: function() {
	  	var d = new Date();
	    d = $("#birthday").datepicker('getDate');
	  }
    });
    
  });
  </script>

<script>  
	$j(function(){
		GeneralUtils.addPlaceholderById("firstname", "First Name");
		GeneralUtils.addPlaceholderById("midname", "Middle Initial");
		GeneralUtils.addPlaceholderById("lastname", "Last Name");
		GeneralUtils.addPlaceholderById("alias", "Alias");
		GeneralUtils.addPlaceholderById("type", "Type");
		GeneralUtils.addPlaceholderById("philhealth", "PhilHealth Number");
		GeneralUtils.addPlaceholderById("birthday", "Birthday");
		GeneralUtils.addPlaceholderById("institution", "Institution");
		GeneralUtils.addPlaceholderById("contactno", "Contact Number");
		GeneralUtils.addPlaceholderById("region", "Region");
		GeneralUtils.addPlaceholderById("city", "City");
		GeneralUtils.addPlaceholderById("barangay", "Barangay/Municipality");
		GeneralUtils.addPlaceholderById("street", "Street Address");
	});
</script>
  
</head>

<body>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
			<!-- Column 1 start -->
			<!-- Column 1 end -->
			
<form:form method="post" modelAttribute="patientModel">
	
	<p>
	<form:input id="firstname" cssClass="patientinput" path="patient.firstName" />
	<form:input id="midname" cssClass="patientinput" path="patient.middleInitial" />
	<form:input id="lastname" cssClass="patientinput" path="patient.lastName" />
	</p>
	<br>
	
	<h2>Basic Information</h2>
	<p>
	<form:select id="type" cssClass="patientinput" path="alias.category">
		<form:options items="${ categories }" itemLabel="category" itemValue="id" />
	</form:select>
	<form:input id="alias" cssClass="patientinput" path="alias.alias" />
	</p>
	
	<p> 
	<form:input id="philhealth" cssClass="patientinput" path="patient.philhealth" />
	</p>
	
	<p><label for="sex">Sex:</label>
	<form:radiobutton cssClass="regular-radio" path="patient.gender" value="M" />Male
	<form:radiobutton cssClass="regular-radio" path="patient.gender" value="F" />Female
	</p>
	
	<p> <label for="status">Civil Status:</label>
	<form:radiobutton cssClass="regular-radio" path="patient.civilStatus" value="Single" />Single
	<form:radiobutton cssClass="regular-radio" path="patient.civilStatus" value="Married" />Married
	<form:radiobutton cssClass="regular-radio" path="patient.civilStatus" value="Widowed" />Widowed
	</p>
	
	<p>
	<form:input id="birthday" cssClass="patientinput" path="patient.birthday" />
	</p>
	
	<br>
	<h2>Contact details</h2>
	<p>
	<form:input id="contactno" cssClass="patientinput" path="patient.contactNo" />
	</p>
	<p>
	<form:input id="institution" cssClass="patientinput" path="patient.institution" />
	</p>
	
	<br>
	<h2>Housing</h2>
	<p>
	<form:input id="region" cssClass="patientinput" path="patient.region" />
	</p>
	<p>
	<form:input id="city" cssClass="patientinput" path="patient.city" />
	</p>
	<p>
	<form:input id="barangay" cssClass="patientinput" path="patient.barangay" />
	</p>
	<p>
	<form:input id="street" cssClass="patientinput" path="patient.streetAddress" />
	</p>
	<br>
	<input class="buttondesign" type="submit" value="Save" />
</form:form>

</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
</body>
</html>
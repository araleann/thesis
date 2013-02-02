<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var searchPath = modulePath + "/searchPatient.htm";

function search(){
	$j.post(searchPath, $j("#search").serialize(), function(data){
		var url = data.url;
		if(url){
			window.location = openmrsContextPath + url;
		} else {
			var $list = $j("#patientList", $j(data));
			$j("#patientList").replaceWith($list);	
		}
	});
	return false;
}

function getPatient(pid){
	$j("#patientId").val(pid);
	$j("#patient").submit();
}

function newPatient(){
	window.location = modulePath + "/patientForm.htm";
}
//-->
</script>

Search Patient
<br>
<br>
<form method="post" id="search" onsubmit="javascript: return search()">
	<input type="text" name="searchText">
	<button type="button" onclick="search()">Search</button>
</form>
<br>
<br>

<form:form method="post" id="patient">
	<input type="hidden" name="patientId" id="patientId">
</form:form>

<div id="patientList">

</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
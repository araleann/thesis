<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var searchPath = modulePath + "/searchPatientForm.htm";
var patientPath = modulePath + "/getPatient.htm"

function search(){
	$j.post(searchPath, $j("#search").serialize(), function(data){
		var $list = $j("#patientList", $j(data));
		$j("#patientList").replaceWith($list);
	});
}

function getPatient(pid){
	console.log(pid);
	var obj = { id : pid };
	$j.post(patientPath, obj, function(data){
		console.log(data);
	});
}
//-->
</script>

Search Patient
<br>
<br>
<form method="post" id="search">
	<input type="text" name="searchText">
	<button type="button" onclick="search()">Search</button>
</form>
<br>
<br>

<div id="patientList">

</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
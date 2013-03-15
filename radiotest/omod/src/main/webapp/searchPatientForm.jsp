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

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>	
<h2>Search Patient</h2>
<br>
<form method="post" id="search" onsubmit="javascript: return search()">
	<input type="text" class="patientinput" placeholder="Enter Patient Name or ID" name="searchText">
	<button type="button" onclick="search()"class="buttondesign" >Search</button>
</form>
<br>

<form:form method="post" id="patient">
	<input type="hidden" name="patientId" id="patientId">
</form:form>

<div id="patientList">

</div>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
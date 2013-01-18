<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
$j(function(){
	var modulePath = openmrsContextPath + "/module/radiotest";
	var getExams = modulePath + "/getExams #exam";
	$j("#examType").change(function(){
		$j(this).after().load(getExams, { type : $j(this).val() });
	});
});
//-->
</script>

<form:form modelAttribute="transModel">
	<spring:nestedPath path="exams[${ index }]">
		<form:select path="exam.type" id="examType">
			<option value="0"></option>
			<form:options items="${ examTypes }" itemLabel="type" itemValue="id" />
		</form:select>
		<form:select path="exam" id="exam">
			<option value="0"></option>
			<form:options items="${ exam }" itemLabel="name" itemValue="id" />
		</form:select>
	</spring:nestedPath>
</form:form>
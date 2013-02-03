<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script>
function loadExamTypes(){
	var $form = $j("#typeForm"); 
	$j.post($form.attr("action"), $form.serialize(), function(data){
		var $types = $j("#examTypes", $j(data));
		$j("#examTypes").replaceWith($types);
		$j("input[type=text]").val("");
	});
}

$j(function(){
	GeneralUtils.addPlaceholderByName("type", "Exam Type");
});
</script>

<h2>Add Exam Type</h2>
<form:form method="post" modelAttribute="examType" id="typeForm">
	<form:label path="type">Exam Type</form:label>
	<form:input path="type" />
	<br>
	<button type="button" onclick="loadExamTypes()">Save</button>
</form:form>
<br>

<div id="examTypes">
	<c:forEach var="type" items="${ examTypes }">
		${ type.type }
		<br>
	</c:forEach>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>
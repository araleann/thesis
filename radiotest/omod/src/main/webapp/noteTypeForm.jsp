<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
function loadNoteTypes(){
	var $form = $j("#typeForm");
	$j.post($form.attr("action"), $form.serialize(), function(data){
		var $types = $j("#noteTypes", $j(data));
		$j("#noteTypes").replaceWith($types);
		$j("input[type=text]").val("");
	});
}

//-->
</script>

<h2>Add Note Type</h2>
<form:form method="post" modelAttribute="noteType" id="typeForm">
	<form:input path="name" />
	<button type="button" onclick="loadNoteTypes()">Save</button>
</form:form>
<br>

<h2>Note Types</h2>
<div id="noteTypes">
	<c:forEach var="type" items="${ noteTypes }">
		${ type.name }
		<br>
	</c:forEach>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>
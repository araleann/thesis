<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
function loadCategories(){
	$form = $j("#catForm");
	$j.post($form.attr("action"), $form.serialize(), function(data){
		var $cat = $j("#categories", $j(data));
		$j("#categories").replaceWith($cat);
		$j("input[type=text]").val("");
	});
}
//-->
</script>

<h2>Add Category</h2>
<form:form method="post" modelAttribute="category" id="catForm">
	<form:input path="category" />
	<button type="button" onclick="loadCategories()">Save</button>
</form:form>
<br>

<h2>Categories</h2>
<div id="categories">
	<c:forEach var="cat" items="${ categories }">
		${ cat.category }
		<br>
	</c:forEach>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
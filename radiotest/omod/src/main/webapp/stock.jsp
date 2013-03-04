<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var itemPath = modulePath + "/getItems.htm";

var itemIndex = 0;
function getItems(lid){
	
}
//-->
</script>

<h2>Add Stock</h2>

<form:form method="post" modelAttribute="stockModel">
	<div id="listings">
		<spring:nestedPath path="listings[0]">
			<form:select path="item.type">
				<option value="0"></option>
				<form:options items="${ itemTypes }" itemLabel="name" itemValue="id" />
			</form:select>
			
		</spring:nestedPath>
	</div>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
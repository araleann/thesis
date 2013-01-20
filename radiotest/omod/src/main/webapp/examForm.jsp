<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script>
$j(function(){
	GeneralUtils.addPlaceholderByName("examFee", "Exam Fee");
	GeneralUtils.addPlaceholderByName("readingFee", "Reading Fee");
});
</script>

<form:form method="post" modelAttribute="examModel">
	<button type="submit">Save Exam</button>
	<br>
	<br>
	<spring:nestedPath path="exam">
		<form:label path="type">Exam Type</form:label>
		<form:select path="type">
			<option value="0"></option>
			<form:options items="${ types }" itemLabel="type" itemValue="id" />
		</form:select>
		<br>
		<br>
		<form:label path="name">Exam Name</form:label>
		<form:input path="name" />
		<br>
	</spring:nestedPath>
	<c:forEach var="cat" items="${ categories }" varStatus="status">
		<spring:nestedPath path="categoryFees[${ status.index }]">
			<br>
			<h4>${ cat.category }</h4>
			<spring:bind path="category">
				<input type="hidden" name="${ status.expression }" value="${ cat.id }">
			</spring:bind>
			<form:input path="examFee" />
			<form:input path="readingFee" />
			<br>
		</spring:nestedPath>
	</c:forEach>
</form:form>
<%@ include file="/WEB-INF/template/footer.jsp"%>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script>
$j(function(){
	$j("#type").select(function(){
		
	});
});
</script>

<form:form method="post" modelAttribute="transModel">
	<form:select path="${ type }">
		<form:options path="${ examTypes }" />
	</form:select>
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
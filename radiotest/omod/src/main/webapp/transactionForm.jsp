<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script>
function addExam(){
	var modulePath = openmrsContextPath + "/module/radiotest";
	var addExam = modulePath + "/addExam #examType";
	
}

$j(function(){
	
});
</script>

<form:form method="post" modelAttribute="transModel" id="transaction">
	
</form:form>

<%@ include file="/WEB-INF/template/footer.jsp"%>
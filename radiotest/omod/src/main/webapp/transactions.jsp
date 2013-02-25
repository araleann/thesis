<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var viewPath = modulePath + "/viewTransaction.htm";

function viewTransaction(id){
	$j("#transId").val(id);
	$j("#transaction")
		.attr("action", viewPath)
		.submit();
}
//-->
</script>

<p>
${ patient.fullName } <br>
<c:set var ="a" value="${ patient.alias }" />
${ a.alias } <br>
${ a.category.category } <br>
</p>

<form id="transaction" method="post">
	<input type="hidden" name="transId" id="transId">
</form>

<h2>Transactions</h2>
<c:forEach var="trans" items="${ transList }">
	<c:set var="id" value="${ trans.id }" />
	Transaction No: ${ id } 
	<button type="button" onclick="viewTransaction(${ id })"></button>
	<br>
	Date: ${ trans.visitDate } <br>
	No. of Exams: ${ trans.numberOfExams } <br>
	Status:
	<c:choose>
		<c:when test="${ trans.paid }">
			PAID
		</c:when>
		<c:otherwise>
			NOT PAID
		</c:otherwise>
	</c:choose>
	<br>
	<br>
</c:forEach>

<%@ include file="/WEB-INF/template/footer.jsp"%>
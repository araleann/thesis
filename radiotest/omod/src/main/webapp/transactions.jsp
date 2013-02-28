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

function createTransaction(){
	GeneralUtils.redirect("transExamForm.htm");
}
//-->
</script>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
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
<p>
	<button type="button" class="buttondesignlong" onclick="createTransaction()">Create New Transaction</button>
</p>
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
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
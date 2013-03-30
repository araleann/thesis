<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/resources.jsp" %>
<%@ include file="template/tables.jsp" %>

<openmrs:htmlInclude file="/moduleResources/radiotest/GeneralUtils.js" />

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var getExamsPath = modulePath + "/getExamList.htm";
var borrowPath = modulePath + "/borrowResults.htm";
var resultPath = modulePath + "/results.htm";

function getExams(){
	console.log($j("#trans").serialize());
	$j.post(getExamsPath, $j("#trans").serialize(), function(data){
		var $list = $j("div#exams", $j(data));
		$j("#exams").replaceWith($list);
	});
}

function result(id, count){
	$j("#examId").val(id);
	$j("#count").val(count);
	$j("#exam")
		.attr("action", resultPath)
		.submit();
}

function borrow(id){
	var examId = "#exam" + id;
	$j.post(borrowPath, { examId: id }, function(data){
		var $updatedDiv = $j(examId, data);
		$j(examId).replaceWith($updatedDiv);
	});
}
function load_transaction(){
	GeneralUtils.redirect("transactions.htm");
}
function load_profile(){
	GeneralUtils.redirect("patientProfile.htm");
}
function load_results(){
	GeneralUtils.redirect("results.htm");
}
$(document).ready( function () {
    $j("#res").dataTable();
});
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<c:set var="p" value="${ patient }" />
<fieldset>
<legend><h2>${ p.fullName }</h2></legend>
	

<p>
<c:if test="${ empty patient }">
	<c:set var="patient" value="${ trans.patient }" />
</c:if>
</p>

<c:choose>
	<c:when test="${ empty trans }">
		<jsp:include page="/WEB-INF/view/topnav.jsp"/>
		<div id="details">
		<h3>Transactions</h3>
		<table id="res" class="tbldesign">
			<thead>
				<tr>
					<th>Transaction No</th>
					<th>Date</th>
					<th>Exams w/ Results</th>
					<th>Status</th>
				</tr>
			</thead>
		<tbody>
			<c:forEach var="trans" items="${ transList }">
			<c:choose>
				<c:when test="${ trans.pending }">	
					<tr style="color:red;">
						<td><center> ${ trans.id } </center></td>
						<td> ${ trans.visitDate } </td>
						<td><center>${ trans.doneExams }/${ trans.numberOfExams }</center></td>
						<td>
						<c:choose>
							<c:when test="${ trans.pending }">					
								PENDING
							</c:when>
							<c:otherwise>
								DONE
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td><center> ${ trans.id } </center></td>
						<td> ${ trans.visitDate } </td>
						<td><center>${ trans.doneExams }/${ trans.numberOfExams }</center></td>
						<td>
						<c:choose>
							<c:when test="${ trans.pending }">					
								PENDING
							</c:when>
							<c:otherwise>
								DONE
							</c:otherwise>
						</c:choose>
						</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</c:forEach>
		</tbody>
		</table>
		<br>
		<h3>View/Update Results</h3>
		<form id="trans" action="javascript:getExams()">
			<label>Transaction Number:</label><input class="patientinputshort" type="text" name="transId">
			
			<button type="button" class="buttondesignmediumshort" onclick="getExams()">View</button>
		</form>
		<br>
		</div>
	</c:when>
	<c:otherwise>
		<div id="details">
		<h3>Transaction</h3>		
		<c:set var="id" value="${ trans.id }" />
		<label>Transaction No.:</label> ${ id } <br>
		<label>Date:</label>${ trans.visitDate } <br>
		<label>Exams w/ Results:</label>${ trans.doneExams }/${ trans.numberOfExams } <br>
		<label>Status:</label>PENDING
		</div>
	</c:otherwise>
</c:choose>

<form:form method="post" id="exam">
	<input type="hidden" id="examId" name="examId">
	<input type="hidden" id="count" name="count">
</form:form>
<br>
<div id="details">
<h3>Exam List</h3>
<div id="exams">
	<c:if test="${ not empty trans }">
		<table id="patient">
		<tr>
		<th> Exam Number </th>
		<th> Exam Type </th>
		<th> Exam Name </th>
		<th> Borrowed </th>
		</tr>
		<c:forEach var="transExam" items="${ trans.exams }" varStatus="status">
			<c:set var="c" value="${ status.count }" />
			<c:set var="id" value="${ transExam.id }" />
			<div id="exam${ id }">
				<tr onclick="result(${ id }, ${ c })">
				<td> ${ c } </td>
				<c:set var="e" value="${ transExam.exam }" />
				<td> ${ e.type.type } </td>
				<td> ${ e.name } </td>
				<td onclick="borrow(${ id })">
					<c:choose>
						<c:when test="${ transExam.borrowed }">
							YES
						</c:when>
						<c:otherwise>
							NO
						</c:otherwise>
					</c:choose>				
				<button type="button" onclick="borrow(${ id })"></button>
				</td>
				</tr>
			</div>
		</c:forEach>
		</table>
		<br>
	</c:if>
</div>
</div>
</div>
</div>
<div class="col2">
<div class="sideholder">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
</div>
</div>
</div>
</div>
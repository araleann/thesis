<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/exam.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<h2>Exams</h2>
<div id="examForm">
	<form:form method="post" modelAttribute="examModel" id="form">
		<c:set var="numOfFeeTypes" value="${ fn:length(feeTypes) }" />
		<spring:bind path="numOfFeeTypes">
			<input type="hidden" name="${ status.expression }" value="${ numOfFeeTypes }">
		</spring:bind>
		<spring:nestedPath path="exam">
			<form:hidden path="id" />
			<label>Exam Type</label>
			<form:select path="type" cssClass="patientinput">
				<option value></option>
				<form:options items="${ types }" itemLabel="type" itemValue="id" />
			</form:select>
			<br>
			<br>
			<label>Exam Name</label>
			<form:input path="name" cssClass="patientinput"/>
			<br>
		</spring:nestedPath>
		<c:forEach var="cat" items="${ categories }" varStatus="status">
			<c:set var="cind" value="${ status.index }" />
			<spring:nestedPath path="categoryFees[${ cind }]">
				<br>
				<h3>${ cat.category }</h3>
				<spring:bind path="category">
					<input type="hidden" name="${ status.expression }" value="${ cat.id }">
				</spring:bind>
			</spring:nestedPath>
			<c:set var="ftind" value="${ cind * numOfFeeTypes }" />
			<c:forEach var="feeType" items="${ feeTypes }" varStatus="s">
				<c:set var="find" value="${ ftind + s.index }" />
				<spring:nestedPath path="fees[${ find }]">
					<spring:bind path="type">
						<input type="hidden" name="${ status.expression }" value="${ feeType.id }">
					</spring:bind>
					<p>
					<label>${ feeType.name }:</label>
					<spring:bind path="amount">
						<input type="text" class="patientinputshort" name="${ status.expression }" value="0">
					</spring:bind>
					</p>
				</spring:nestedPath>
			</c:forEach>
		</c:forEach>
	</form:form>
	<br>
	<button type="button" onclick="saveExam()" class="buttondesign">Save</button>
	<button type="button" onclick="clearForm()" class="buttondesign">Clear</button>
</div>
<br>
<hr>
<h2>Existing Exams</h2>
<div id="examList">
	<c:forEach var="exam" items="${ exams }">
		<br>
		<c:set var="id" value="${ exam.id }" />
		<div id="exam${ id }">
			Type : ${ exam.type.type } <br>
			Name: <b> ${ exam.name } </b>
			<br>
			Voided:
			<c:choose>
				<c:when test="${ exam.voided }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when test="${ exam.voided }">
					<button type="button" onclick="voidExam(${ id })" class="buttondesignvoid">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidExam(${ id })" class="buttondesignvoid">Void</button>
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="deleteExam(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="loadExam(${ id })" class="buttondesignsmall">Edit</button>
		</div>
	</c:forEach>
	<br>
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
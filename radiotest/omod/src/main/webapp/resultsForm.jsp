<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
	var template = "${ template }";
</script>

<openmrs:htmlInclude file="/moduleResources/radiotest/pages/result.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/inventory.js" />
<openmrs:htmlInclude file="/moduleResources/radiotest/pages/redirect.js" />

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<fieldset>
<legend><h2>${ patient.fullName }</h2></legend>
<div id="details">
<br>
<label> Exam Number: </label> ${ count } <br>
<c:set var="exam" value="${ transExam.exam }" />
<label> Exam Type: </label> ${ exam.type.type } <br>
<label> Exam Name: </label> ${ exam.name }
<br><br>
<div id="results">
	<c:choose>
		<c:when test="${ result.draft }">
			<form:form method="post" modelAttribute="result" id="result">
				<input type="hidden" name="examId" value="${ transExam.id }">
				<form:hidden path="id" />
				<form:hidden path="draft" id="draft" />
				<h3> Result </h3>
				<form:radiobutton cssClass="regular-radio" path="positive" value="true" onclick="writeTemplate()" /><b>Positive</b>
				<form:radiobutton cssClass="regular-radio" path="positive" value="false" onclick="writeTemplate()" /><b>Negative</b>
				<br><br>
				<spring:bind path="findings">
					<c:choose>
						<c:when test="${ result.positive }">
							<c:set var="res" value="${ result.findings }" />
						</c:when>
						<c:otherwise>
							<c:set var="res" value="${ exam.resultTemplate }" />
						</c:otherwise>
					</c:choose>
					<textarea class="patientinputtemplate" id="findings" name="${ status.expression }">${ res }</textarea>
				</spring:bind>
				<br>
			</form:form>
			<button type="button" class="buttondesign" onclick="save()">Save</button>
			<button type="button" class="buttondesign" style="width:150px;" onclick="saveDraft()">Save As Draft</button>
			<br><br>
		</c:when>
		<c:otherwise>		
		<b><font style="padding-left: 25px">Result:</font></b>
		<div id="tab">
			<font style="color:#009d8e">
			<c:choose>
				<c:when test="${ result.positive }">
					<b>POSITIVE</b>
				</c:when>
				<c:otherwise>
					<b>NEGATIVE</b>
				</c:otherwise>
			</c:choose>
			</font>
			<br>
			${ findings }
			<br><button class="buttondesignsmall" type="button" onclick="edit()">Edit</button>
			</div>
			<br>
			<form id="result" method="post">
				<input type="hidden" name="examId" value="${ transExam.id }">
				<select class="patientinput" style="width:300px;" name="sign">
					<option value></option>
					<c:forEach var="sign" items="${ signatures }">
						<option value="${ sign.id }">${ sign.name }, ${ sign.position }</option>
					</c:forEach>
				</select>
			</form>
			<br>
			<button type="button" class="buttondesign" style="width:200px" onclick="printResult()">Print Results Form</button>
			<br><br>
			<h3>Items Used</h3>
			<div id="inventory">
			<div id=tab>
				<c:choose>
					<c:when test="${ empty transExam.items }">
						<div id="select">
							<select id="type" class="patientinputmedium" name="type" onchange="getItems()">
								<option value="0">All</option>
								<c:forEach var="type" items="${ itemTypes }">
									<option value="${ type.id }">${ type.name }</option>
								</c:forEach>
							</select>
							<div class="items">
								<select id="items" class="patientinputmediummult validate[required]" name="items" size="5" multiple>
									<c:forEach var="item" items="${ items }">
										<c:set var="id" value="${ item.id }" />
										<option value="${ id }" ondblclick="addItems()">${ item.name }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div id="items">
							<form:form method="post" modelAttribute="stockModel" id="itemForm">
								<input type="hidden" name="examId" value="${ transExam.id }">
								<br>
								<div id="listings">
									
								</div>
								<button type="button" class="buttondesignsmall" onclick="updateStock()">Save</button>
							</form:form>
						</div>
						<button type="button" class="buttondesign1" onclick="addItems()">Add Item(s)</button>
					</c:when>
					<c:otherwise>
						<c:forEach var="i" items="${ transExam.items }">
							<c:set var="item" value="${ i.item }" />
							${ item.name }: ${ i.quantity } ${ item.unit } <br>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</div>
		</c:otherwise>
	</c:choose>	
	<br>	
	<button type="button" class="buttondesignmedium" onclick="load_profile()" style="background-color:#f29c22;">Back to profile</button>	
	</div>
	<br><br>
</fieldset>

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
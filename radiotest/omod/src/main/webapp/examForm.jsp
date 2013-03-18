<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script>
var modulePath = openmrsContextPath + "/module/radiotest";
var savePath = modulePath + "/examForm.htm";
var loadPath = modulePath + "/loadExam.htm";
var nullPath = modulePath + "/nullExam.htm";

function loadExam(id){
	$j.post(loadPath, { examId : id }, function(data){
		var $form = $j("#examForm", $j(data));
		$j("#examForm").replaceWith($form);
	});
}

function saveExam(){
	$j("#form")
		.attr("action", savePath)
		.submit();
}

function clearForm(){
	$j("#form")
		.find(":selected, :checked")
			.each(function(i){
				var $this = $j(this);
				if($this.attr("selected")){
					$this.attr("selected", false);
				} else {
					$this.attr("checked", false);
				}
			});
	
	$j("#form")
		.find(":input:not(button)")
			.each(function(i){
				var $this = $j(this);
				switch(this.tagName){
				case "TEXTAREA":
					$this.text("");
					break;
				case "INPUT":
					var type = $this.attr("type");
					if(type == "text" || type == "hidden")
						$this.val("");
					break;
				}
			});
}

function voidExam(id){
	var obj = {
		eid : id,
		action : "void"
	}
	post(id, obj);
}

function deleteExam(id){
	var obj = {
		eid : id,
		action : "delete"
	}
	if(confirm("Are you sure you want to delete?")){
		post(id, obj);
	}
}

function post(id, obj){
	var examId = "#exam" + id;
	$j.post(nullPath, obj, function(data){
		var $exam = $j(examId, $j(data));
		if($exam.length){
			$j(examId).replaceWith($exam);
		} else {
			$j(examId).remove();
		}
	})
}
</script>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
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
				<option value="0"></option>
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
					<form:input path="amount" cssClass="patientinputsmall"/>
					</p>
				</spring:nestedPath>
			</c:forEach>
		</c:forEach>
	</form:form>
	<br>
	<button type="button" onclick="clearForm()" class="buttondesign">Clear Form</button>
	<button type="button" onclick="saveExam()" class="buttondesign">Save Exam</button>
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
			Name: ${ exam.name }
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
			<button type="button" onclick="voidExam(${ id })" class="buttondesignvoid">Void</button>
			<button type="button" onclick="deleteExam(${ id })" class="buttondesignsmall">Delete</button>
			<button type="button" onclick="loadExam(${ id })" class="buttondesignsmall">Edit</button>
		</div>
	</c:forEach>
	<br>
</div>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
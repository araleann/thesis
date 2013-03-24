<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var savePath = modulePath + "/resultsForm.htm";
var editPath = modulePath + "/editResultForm.htm";
var template = "${ template }";

var itemPath = modulePath + "/getItems.htm";
var stockPath = modulePath + "/addListing.htm";
var updatePath = modulePath + "/updateStock.htm";

function saveDraft(){
	$j.post(savePath, $j("#result").serialize(), function(data){
		alert("Saved as draft!");
	});
}

function save(){
	$j("#draft").val(false);
	$j.post(savePath, $j("#result").serialize(), function(data){
		alert("Saved!");
		var $results = $j("#results", $j(data));
		$j("#results").replaceWith($results);
	});
}

function edit(){
	if(confirm("Are you sure you want to edit?")){
		$j.post(editPath, $j("#result").serialize(), function(data){
			var $results = $j("#results", $j(data));
			$j("#results").replaceWith($results);
		});	
	}
}

function writeTemplate(){
	var isNegative = $j("#positive2").attr("checked");
	var $findings = $j("#findings");
	
	if (isNegative){
		$findings.val(template);
	} else {
		$findings.val("");
	}
}


function getItems(){
	$j.post(itemPath, $j("#type").serialize(), function(data){
		var $items = $j(".items", data);
		$j(".items").replaceWith($items);
	});
}

function deleteItem(buttonElem){
	$j(buttonElem)
		.parent()
			.remove();
}


function addItems(){
	$j.post(stockPath, $j("#item").serialize(), function(data){
		var $listings = $j(".listings", data);
		$listings
			.find("input:text")
				.val("1");
		$j("#listings").append($listings.children());
	});
}

function updateStock(){
	$j("#listings .item").each(function(i){
		var item = "examItems[" + i + "]";
		$j("input:hidden", this)
			.attr("name", item + ".item");
		$j("input:text", this)
			.attr("name", item + ".quantity");
	});
	
	$j.post(updatePath, $j("#itemForm").serialize(), function(data){
		console.log(data);
		var $inventory = $j("#inventory", data);
		$j("#inventory").replaceWith($inventory);
	});
}
function load_profile(){
	GeneralUtils.redirect("patientProfile.htm");
}
//-->
</script>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
<fieldset>
<legend><h2>${ p.fullName }</h2></legend>
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
							<c:set var="res" value="${ exam.type.template }" />
						</c:otherwise>
					</c:choose>
					<textarea class="addressinput" id="findings" name="${ status.expression }">${ res }</textarea>
				</spring:bind>
				<br>
			</form:form>
			<button type="button" class="buttondesignsmallmedium" onclick="save()">Save</button>
			<button type="button" class="buttondesignsmalllong" onclick="saveDraft()">Save As Draft</button>
			<br><br>
		</c:when>
		<c:otherwise>		
			<form id="result">
				<input type="hidden" name="examId" value="${ transExam.id }">
			</form>
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
			<button type="button" class="buttondesignsmalllong">Print Results Form</button><br><br>
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
								<select id="item" class="patientinputmediummult" name="items" size="5" multiple>
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
								<div id="listings">
									
								</div>
								<button type="button" class="buttondesignsmall" onclick="updateStock()">Save</button>
							</form:form>
						</div>
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
	</div>
	<br>	
	<button type="button" class="buttondesignmedium" onclick="load_profile()" style="background-color:#f29c22;">Back to profile</button>
	<br><br>
</fieldset>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
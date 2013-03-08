<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var savePath = modulePath + "/resultsForm.htm";
var editPath = modulePath + "/editResultForm.htm";
var template = "${ template }";

var itemPath = modulePath + "/getItems.htm";
var stockPath = modulePath + "/addListing.htm";

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
		$j("#listings").append($listings.children());
	});
}
//-->
</script>

<h2>Exam Result Details</h2>
<br>
<br>

Exam Number: ${ count }
<br>
<c:set var="exam" value="${ transExam.exam }" />
Exam Type: ${ exam.type.type }
<br>
Exam Name: ${ exam.name }
<br>
<br>

<div id="results">
	<c:choose>
		<c:when test="${ result.draft }">
			<form:form method="post" modelAttribute="result" id="result">
				<input type="hidden" name="examId" value="${ transExam.id }">
				<form:hidden path="id" />
				<form:hidden path="draft" id="draft" />
				Result: 
				<form:radiobutton path="positive" label="Positive" value="true" onclick="writeTemplate()" />
				<form:radiobutton path="positive" label="Negative" value="false" onclick="writeTemplate()" />
				<br>
				<spring:bind path="findings">
					<c:choose>
						<c:when test="${ result.positive }">
							<c:set var="res" value="${ result.findings }" />
						</c:when>
						<c:otherwise>
							<c:set var="res" value="${ exam.type.template }" />
						</c:otherwise>
					</c:choose>
					<textarea id="findings" name="${ status.expression }">${ res }</textarea>
				</spring:bind>
				<br>
				<br>
			</form:form>
			<button type="button" onclick="save()">Save</button>
			<button type="button" onclick="saveDraft()">Save As Draft</button>
		</c:when>
		<c:otherwise>
			<form id="result">
				<input type="hidden" name="examId" value="${ transExam.id }">
			</form>
			<c:choose>
				<c:when test="${ result.positive }">
					POSITIVE
				</c:when>
				<c:otherwise>
					NEGATIVE
				</c:otherwise>
			</c:choose>
			<br>
			<br>
			${ findings }
			<br>
			<button type="button" onclick="edit()">Edit</button>
			<h2>Items Used</h2>
			<div id="inventory">
				<div id="select">
					<select id="type" name="type" onchange="getItems()">
						<option value="0">All</option>
						<c:forEach var="type" items="${ itemTypes }">
							<option value="${ type.id }">${ type.name }</option>
						</c:forEach>
					</select>
					<div class="items">
						<select id="item" name="items" size="5" multiple>
							<c:forEach var="item" items="${ items }">
								<c:set var="id" value="${ item.id }" />
								<option value="${ id }" ondblclick="addItems()">${ item.name }</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div id="items">
					<form:form method="post" modelAttribute="stockModel" id="itemForm">
						<div id="listings">
							
						</div>
					</form:form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<br>
<br>
<button type="button">Print Results Form</button>

<%@ include file="/WEB-INF/template/footer.jsp"%>
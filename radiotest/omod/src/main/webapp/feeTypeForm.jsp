<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var nullFeePath = modulePath + "/nullFeeType.htm";

function loadFeeTypes(){
	var divId = "#feeTypes";
	var $form = $j("#feeForm");
	var obj = $form.serialize();
	var path = $form.attr("action");
	
	function focus(){
		$j("input[type=text]")
			.val("")
			.focus();
	}
	
	post(divId, obj, path, focus);
}

function voidFeeType(id){
	var divId = "#type" + id;
	var obj = {
		tid : id,
		action : "void"
	};
	
	post(divId, obj, nullFeePath);
}

function deleteFeeType(id){
	var divId  = "#type" + id;
	var obj = {
		tid : id,
		action : "delete"
	};
	
	if (confirm("Are you sure you want to delete?")){
		post(divId, obj, nullFeePath);
	}
}

function post(divId, obj, path, callback){
	$j.post(path, obj, function(data){
		var $divElem = $j(divId);
		var $updatedDiv = $j(divId, $j(data));
		if ($updatedDiv.length){
			$divElem.replaceWith($updatedDiv);
		} else {
			$divElem.remove();
		}
		if (callback){
			callback();
		}
	});
}

$j(function(){	
	GeneralUtils.addPlaceholderById("input", "Enter Fee Type");
});
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		
<br>
<h2>Add Fee Type</h2>
<br>
<form:form method="post" modelAttribute="feeType" id="feeForm">
	<form:input id="input" cssClass="patientinput" path="name" />
	<br><br>
	<button class="buttondesign" type="button" onclick="loadFeeTypes()">Save</button>
</form:form>

<br>
<hr>
<br>
<h2>Existing Fee Types</h2>
<div id="feeTypes">
	<c:forEach var="type" items="${ feeTypes }">
		<c:set var="id" value="${ type.id }" />
		<div id="type${ id }">
			<b><font size=4>${ type.name }</font></b><br>
			Voided:
			<c:choose>
				<c:when test="${ type.voided }">
					YES
				</c:when>
				<c:otherwise>
					NO	
				</c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when test="${ type.voided }">
					<button type="button" onclick="voidFeeType(${ id })" class="buttondesignvoid">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidFeeType(${ id })" class="buttondesignvoid">Void</button>	
				</c:otherwise>
			</c:choose>				
			<button type="button" onclick="deleteFeeType(${ id })" class="buttondesignsmall">Delete</button>
		</div>
		<br>
	</c:forEach>
</div>

</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div>
</div>
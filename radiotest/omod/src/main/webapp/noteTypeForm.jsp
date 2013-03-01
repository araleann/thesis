<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var nullPath = modulePath + "/nullNoteType.htm";

function loadNoteTypes(){
	var $form = $j("#typeForm");
	$j.post($form.attr("action"), $form.serialize(), function(data){
		var $types = $j("#noteTypes", $j(data));
		$j("#noteTypes").replaceWith($types);
		$j("input[type=text]")
			.val("")
			.focus();
	});
}

function voidNoteType(id){
	var obj = {
		nid : id,
		action : "void"
	}
	post(id, obj);
}

function deleteNoteType(id){
	var obj = {
		nid : id,
		action : "delete"
	}
	if(confirm("Are you sure you want to delete?")){
		post(id, obj);
	}
}

function post(id, obj){
	var noteId = "#note" + id;
	$j.post(nullPath, obj, function(data){
		var $note = $j(noteId, $j(data));
		if($note.length){
			$j(noteId).replaceWith($note);
		} else {
			$j(noteId).remove();
		}
	})
}

$j(function(){
	GeneralUtils.addPlaceholderById("notetype", "Enter Note Type");
});
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
			<!-- Column 1 start -->
			<!-- Column 1 end -->
<br>
<h2>Add Note Type</h2>
<br>
<form:form method="post" modelAttribute="noteType" id="typeForm">
	<form:input id="notetype" path="name" cssClass="patientinput" />
	<br><br>
	<button type="button" onclick="loadNoteTypes()" class="buttondesign">Save</button>
</form:form>
<br>
<hr>
<br>
<h2>Existing Note Types</h2>
<div id="noteTypes">
	<c:forEach var="type" items="${ noteTypes }">
		<c:set var="id" value="${ type.id }" />
		<div id="note${ id }">
			<font size=4><b>${ type.name }</b></font>
			<br>
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
					<button type="button" onclick="voidNoteType(${ id })" class="buttondesignvoid">Unvoid</button>			
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidNoteType(${ id })" class="buttondesignvoid">Void</button>
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="deleteNoteType(${ id })" class="buttondesignsmall">Delete</button>
		</div>
		<br>
	</c:forEach>
</div>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
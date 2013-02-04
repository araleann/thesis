<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

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
//-->
</script>

<h2>Add Note Type</h2>
<form:form method="post" modelAttribute="noteType" id="typeForm">
	<form:input path="name" />
	<button type="button" onclick="loadNoteTypes()">Save</button>
</form:form>
<br>

<h2>Note Types</h2>
<div id="noteTypes">
	<c:forEach var="type" items="${ noteTypes }">
		<br>
		<c:set var="id" value="${ type.id }" />
		<div id="note${ id }">
			${ type.name } <br>
			Voided:
			<c:choose>
				<c:when test="${ type.voided }">
					YES			
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="voidNoteType(${ id })"></button>
			<br>
			<button type="button" onclick="deleteNoteType(${ id })">Delete</button>
		</div>
		<br>
	</c:forEach>
</div>
<%@ include file="/WEB-INF/template/footer.jsp"%>
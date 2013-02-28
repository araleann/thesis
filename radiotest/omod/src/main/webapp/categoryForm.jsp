<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="template/resources.jsp" %>

<script type="text/javascript">
<!--
var modulePath = openmrsContextPath + "/module/radiotest";
var nullPath = modulePath + "/nullCategory.htm";

function loadCategories(){
	$form = $j("#catForm");
	$j.post($form.attr("action"), $form.serialize(), function(data){
		var $cat = $j("#categories", $j(data));
		$j("#categories").replaceWith($cat);
		$j("input[type=text]")
			.val("")
			.focus();
	});
}

function voidCategory(id){
	var obj = {
		cid : id,
		action : "void"
	}
	post(id, obj);
}

function deleteCategory(id){
	var obj = {
		cid : id,
		action : "delete"
	}
	if(confirm("Are you sure you want to delete?")){
		post(id, obj);
	}
}

function post(id, obj){
	var catId = "#category" + id;
	$j.post(nullPath, obj, function(data){
		var $cat = $j(catId, $j(data));
		if($cat.length){
			$j(catId).replaceWith($cat);
		} else {
			$j(catId).remove();
		}
	})
}

$j(function(){	
	GeneralUtils.addPlaceholderById("input", "Enter a category");
});
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		
<br>
<h2>Add Category</h2>
<br>
<form:form method="post" modelAttribute="category" id="catForm">
	<form:input id="input" cssClass="patientinput" path="category" /><br><br>
	<button class="buttondesign" type="button" onclick="loadCategories()">Save</button>
</form:form>
<br>
<hr>
<br>
<h2>Categories</h2>
<br>
<div id="categories">
	<c:forEach var="cat" items="${ categories }">
		<c:set var="id" value="${ cat.id }" />
		<div id="category${ id }">
			<font size="4"><b>${ cat.category }</b></font> <br>
			Voided:
			<c:choose>
				<c:when test="${ cat.voided }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			<br>
			<c:choose>
				<c:when test="${ cat.voided }">
					<button type="button" onclick="voidCategory(${ id })" class="buttondesignvoid">Unvoid</button>
				</c:when>
				<c:otherwise>
					<button type="button" onclick="voidCategory(${ id })" class="buttondesignvoid">Void</button>
				</c:otherwise>
			</c:choose>					
			<button type="button" onclick="deleteCategory(${ id })" class="buttondesignsmall">Delete</button>
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

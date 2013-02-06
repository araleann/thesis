<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

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
//-->
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		

<h2>Add Category</h2>
<form:form method="post" modelAttribute="category" id="catForm">
	<form:input cssClass="patientinput" path="category" />
	<button class="buttondesign" type="button" onclick="loadCategories()">Save</button>
</form:form>
<br>

<h2>Categories</h2>
<div id="categories">
	<c:forEach var="cat" items="${ categories }">
		<br>
		<c:set var="id" value="${ cat.id }" />
		<div id="category${ id }">
			${ cat.category } <br>
			Voided:
			<c:choose>
				<c:when test="${ cat.voided }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			<button type="button" onclick="voidCategory(${ id })" class="buttondesignvoid">Void</button>
			<br>
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

<%@ include file="/WEB-INF/template/footer.jsp"%>
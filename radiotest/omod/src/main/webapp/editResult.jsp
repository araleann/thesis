<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="results">
	<c:choose>
		<c:when test="${ result.draft }">
			<form:form method="post" modelAttribute="result" id="result">
				<input type="hidden" name="examId" value="${ examId }">
				<form:hidden path="id" />
				<form:hidden path="draft" id="draft" />
				Result: 
				<form:radiobutton path="positive" label="Positive" value="true" />
				<form:radiobutton path="positive" label="Negative" value="false" />
				<br>
				<form:textarea path="findings" />
				<br>
				<br>
				<button type="button" onclick="save()">Save</button>
				<button type="button" onclick="saveDraft()">Save As Draft</button>
			</form:form>
		</c:when>
		<c:otherwise>
			<form id="result">
				<input type="hidden" name="examId" value="${ examId }">
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
			${ result.findings }
			<br>
			<br>
			<button type="button" onclick="edit()">Edit</button>
		</c:otherwise>
	</c:choose>
</div>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<h2>Inventory</h2>

<c:forEach var="itemMap" items="${ items }">
	<c:set var="type" value="${ itemMap.key }" />
	<c:set var="list" value="${ itemMap.value }" />
	<h4>${ type.name }</h4>
	<c:forEach var="i" items="${ list }">
		Name: ${ i.name } <br>
		Quantity: ${ i.quantity } ${ i.unit } <br>
		At threshold:
		<c:choose>
			<c:when test="${ i.atLimit }">
				YES
			</c:when>
			<c:otherwise>
				NO
			</c:otherwise>
		</c:choose>
		<br>
	</c:forEach>
</c:forEach>
<%@ include file="/WEB-INF/template/footer.jsp"%>
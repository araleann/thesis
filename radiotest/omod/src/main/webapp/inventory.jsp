<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
<br>
<h2>Inventory</h2>
<c:forEach var="itemMap" items="${ items }">
	<c:set var="type" value="${ itemMap.key }" />
	<c:set var="list" value="${ itemMap.value }" />
	<h3>${ type.name }</h3>
	<table id="inventory">
	<tr>
	<th> Name </th>
	<th> Quantity </th>
	<th> At Threshold? </th>
	<tr>
	<c:forEach var="i" items="${ list }">
		<tr>
		<td> ${ i.name } </td>
		<td> ${ i.quantity } ${ i.unit } </td>
		<td><center>
		<c:choose>
			<c:when test="${ i.atLimit }">
				YES
			</c:when>
			<c:otherwise>
				NO
			</c:otherwise>
		</c:choose>
		</center>
		</td>
		</tr>
	</c:forEach>
	</table>
	<br>
</c:forEach>
</div>
<div class="col2">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
		</div>
</div></div>
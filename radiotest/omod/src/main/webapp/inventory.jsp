<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/tables.jsp"%>


<script type="text/javascript">
$j(function(){
	$j("#inv").dataTable();
});
</script>
<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<br>
<h2>Inventory</h2>
<c:forEach var="itemMap" items="${ items }">
	<c:set var="type" value="${ itemMap.key }" />
	<c:set var="list" value="${ itemMap.value }" />
	<div id="notif">
		<c:forEach var="i" items="${ list }">
			<c:choose>
				<c:when test="${ i.atLimit }">
					<div class="notif">
						<div id="tab">
						<b> WARNING! </b> ${ i.name }  is at threshold.
						</div>
					</div>
				</c:when>
			</c:choose>
		</c:forEach>
	</div>
	<br>
	<h3>${ type.name }</h3>
	<table id="inv" class="tbldesign">
	<thead>
		<tr>
		<th> Name </th>
		<th> Quantity </th>
		<th> At Threshold? </th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="i" items="${ list }">
			<tr onclick="">
			<td> ${ i.name } </td>
			<td> ${ i.quantity } ${ i.unit } </td>
			<td>
			<c:choose>
				<c:when test="${ i.atLimit }">
					YES
				</c:when>
				<c:otherwise>
					NO
				</c:otherwise>
			</c:choose>
			</td>
			</tr>
		</c:forEach>
	</tbody>
	</table>
	<br>
</c:forEach>

</div>
</div>
<div class="col2">
<div class="sideholder">
			<!-- Column 2 start -->
			<jsp:include page="/WEB-INF/view/sidemenu.jsp"/>
</div>
</div>
</div>
</div>
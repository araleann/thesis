<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/tables.jsp"%>


<script type="text/javascript">

	$(document).ready( function () {
		$j("#history").dataTable();
	});
</script>

<div class="colmask leftmenu">
	<div class="colleft">
		<div class="col1">
		<div class="cont">
<br>
<h2>Stock History</h2>
<p>
Last Update: ${ updateDate }
</p>
<table id="history" class="tbldesign">
	<thead>
		<tr>
			<th> Date </th>
			<th> Item Type </th>
			<th> Item Name </th>
			<th> Quantity Added </th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="listing" items="${ listings }">
		<c:set var="item" value="${ listing.item }" />
		<tr>
			<td> ${ listing.listingDate } </td>
			<td> ${ item.type.name } </td>
			<td> ${ item.name } </td>
			<td> ${ listing.quantity } ${ item.unit } </td>
		</tr>
	</c:forEach>
	</tbody>
</table>

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
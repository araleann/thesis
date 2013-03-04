<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<h2>Stock Listings</h2>

<p>
Last Update: ${ updateDate }
</p>

<c:forEach var="listing" items="${ listings }">
	<c:set var="item" value="${ listing.item }" />
	Date: ${ listing.listingDate } <br>
	Item Type: ${ item.type.name } <br>
	Item Name: ${ item.name } <br>
	Quantity added: ${ listing.quantity } <br>
	<br>
</c:forEach>

<%@ include file="/WEB-INF/template/footer.jsp"%>
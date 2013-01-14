<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<form method="post">
	<input type="text" name="search">
	<input type="submit" value="Search">
</form>

<c:forEach var="res" items="${ result }">
	${ res.firstName }
	${ res.middleInitial }
	${ res.lastName }
	<br>
	<c:forEach var="alias" items="${ res.aliases }">
		${ alias.alias }
	</c:forEach>
	<br>
	<br>
</c:forEach>
<%@ include file="/WEB-INF/template/footer.jsp"%>
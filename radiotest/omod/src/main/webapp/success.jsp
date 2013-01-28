<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<%@ include file="template/localHeader.jsp"%>

${ patient.firstName }
${ patient.middleInitial }
${ patient.lastName }

<br />

<c:forEach items="${ patient.aliases }" var="a">
	${ a.alias }
	<br />
	<c:set value="${ a.category }" var="c" />
		${ c.category }
</c:forEach>

<br />

${ patient.gender }

<br />

${ patient.contactNo }

<br />

${ patient.philhealth }

<br />

${ patient.institution }

<br />

${ patient.birthday }

<%@ include file="/WEB-INF/template/footer.jsp"%>
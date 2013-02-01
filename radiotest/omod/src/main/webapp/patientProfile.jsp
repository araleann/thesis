<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<script type="text/javascript">
<!--
function newTransaction(){
	
}
//-->
</script>

<button type="button">Edit Patient</button>
<button type="button">Create new transaction</button>
<br>
<br>

Patient Profile

<p>
<c:set var="p" value="${ patient }" />
Full Name: ${ p.fullName }
</p>

<br>
Basic Information

<p>
<c:set var="c" value="${ p.category }" />
Category: ${ c.category }
</p>
<p>
<c:set var="a" value="${ p.alias }" />
Alias: ${ a.alias }
</p>
<br>
<p>
Philhealth: ${ p.philhealth }
</p>
<p>
Sex: ${ p.gender }
</p>
<p>
Civil Status: ${ p.civilStatus }
</p>
<p>
Birthday: ${ p.birthday }
</p>

<br>
Contact Details

<p>
Contact Number: ${ p.contactNo }
</p>
<p>
Institution: ${ p.institution }
</p>

<br>
Housing

<p>
Street Address: ${ p.streetAddress }
</p>
<p>
Barangay: ${ p.barangay }
</p>
<p>
City: ${ p.city }
</p>
<p>
Region: ${ p.region }
</p>

<%@ include file="/WEB-INF/template/footer.jsp"%>
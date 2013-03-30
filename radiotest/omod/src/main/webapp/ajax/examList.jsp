<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>
<%@ include file="../template/resources.jsp" %>
<%@ include file="../template/tables.jsp" %>

<openmrs:htmlInclude file="/moduleResources/radiotest/radiotest.css" />

<script type="text/javascript">
$j(function(){
	$j("#ex").dataTable();
});
</script>

<div id="exams">
	<c:forEach var="transExam" items="${ exams }" varStatus="status">
		<c:set var="c" value="${ status.count }" />
		<c:set var="id" value="${ transExam.id }" />
		<div id="exam${ id }">
			<table id="ex" class="tbldesign">
				<thead>
					<tr>
						<th> Exam Numbers </th>
						<th> Exam Type </th>
						<th> Exam Name </th>
						<th> Borrowed </th>
					</tr>
				</thead>
				<tbody>
					<tr onclick="result(${ id }, ${ c })">
						<td> ${ c } </td>
						<td> 
							<c:set var="e" value="${ transExam.exam }" />
							${ e.type.type }
						</td>
						<td> ${ e.name } </td>
						<td> 
							<c:choose>
								<c:when test="${ transExam.borrowed }">
									YES
								</c:when>
								<c:otherwise>
									NO
								</c:otherwise>
							</c:choose>
							<button type="button" onclick="borrow(${ id })"></button>
						</td>
					</tr>
				</tbody>			
			</table>
		</div>
		<br>
	</c:forEach>
</div>
<%@ include file="/WEB-INF/template/include.jsp"%>
<%@ include file="/WEB-INF/template/header.jsp"%>

<div id="exams">
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
	<c:forEach var="transExam" items="${ exams }" varStatus="status">
		<c:set var="c" value="${ status.count }" />
		<c:set var="id" value="${ transExam.id }" />
		<div id="exam${ id }">
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
		</div>
		<br>
	</c:forEach>
	</tbody>			
	</table>
</div>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="/WEB-INF/jsp/includes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Management</title>
</head>
<script type="text/javascript">

function nextPage(currentPage){
	document.getElementById("currentPageNo").value = currentPage + 1;
}

function previousPage(currentPage){
	document.getElementById("currentPageNo").value = currentPage - 1;
}

</script>
<style>
table td,
table th{
    border: 1px solid silver;
}
.headerSortDown:after,
.headerSortUp:after{
    content: ' ';
    position: relative;
    left: 2px;
    border: 8px solid transparent;
}
.headerSortDown:after{
    top: 10px;
    border-top-color: silver;
    left: 30%;
}
.headerSortUp:after{
    bottom: 15px;
    border-bottom-color: silver;
    left: 30%;
}
.headerSortDown,
.headerSortUp{
    padding-right: 10px;
}
</style>
<body>
<h1>Student Data</h1>
<form:form action="student.do" method="POST" commandName="student">

<table>
	<tr>
		<td>Student ID</td>
		<td><form:input path="studentId"/> </td>
	</tr>
	<tr>
		<td>First Name</td>
		<td><form:input path="firstName"/> </td>
	</tr>
	<tr>
		<td>Last Name</td>
		<td><form:input path="lastName"/> </td>
	</tr>
	<tr>
		<td>Year Level</td>
		<td><form:input path="yearLevel"/> </td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="submit" name="action" value="Add">
		<input type="submit" name="action" value="Edit">
		<input type="submit" name="action" value="Delete">
		<input type="submit" name="action" value="Search">
		</td>
	</tr>
</table>
</form:form>
<br>
<table border="1" cellspacing="0" width="50%">

<thead>
            <tr>
                <th class="headerSortDown">ID</th>
                <th class="headerSortDown">First Name</th>
                <th class="headerSortDown">Last Name</th>
                <th class="headerSortDown">Year Level</th>
            </tr>
 </thead>
 
  <form:form action="studentSearch.do" method="POST" commandName="student">
  <tfoot>
 
  <tr>
                <th><input type="text" name="studentId"/></th>
                <th><input type="text" name="firstName"/></th>
                <th><input type="text" name="lastName"/></th>
                <th><input type="text" name="yearLevel"/></th>
   </tr>
 	<tr>
 		<td><input type="submit" name="action" value="ColumnSearch"/></td>
 	</tr>
 </tfoot>
 </form:form>

<tbody>

<c:forEach items="${studentList}" var="student">
		<tr>
			<td>${student.studentId}</td>
			<td>${student.firstName}</td>
			<td>${student.lastName}</td>
			<td>${student.yearLevel}</td>
		</tr>
	</c:forEach>


</tbody>


</table>
<form:form action="student.do" method="POST" commandName="page" id="pageForm">
<table>
	<tr>
		<td><input type="submit" name="action" value="Previous" onclick="javascript:previousPage(${page.currentPageNo});"><form:input path="currentPageNo" size="1" id="currentPageNo" readonly="true"/></td>
		<td><input type="submit" name="action" value="Next" onclick="javascript:nextPage(${page.currentPageNo});"></td>	
	</tr>
</table>
</form:form>
</body>
</html>
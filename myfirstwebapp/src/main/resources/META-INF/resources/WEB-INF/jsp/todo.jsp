<%@ taglib prefix="c" uri="jakarta.tags.core" %>



<html>
<head>
<link href="webjars/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
<title>This is Todo Display Page</title>
</head>
<body>
	<%@ include file="common/navigation.jsp" %>
	<div class="container">
	<h1>The Todos are:</h1>
	<table class="table">
		<thead>
		<tr>
			<th>Description</th>
			<th>TargetDate</th>
			<th>isDone?</th>
			<th></th>
			<th></th>
		</tr>		
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.desc}</td>
					<td>${todo.targetDate}</td>
					<td>${todo.done}</td>
					<td><a href="delete-todo?id=${todo.id}" type="btn" class="btn btn-warning">Delete</a></td>
					<td><a href="update-todo?id=${todo.id}" type="btn" class="btn btn-success">Update</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="add-todo" class="btn btn-success">Add ToDo</a>
	</div>
	<%@ include file="common/footer.jsp" %>
</body>
</html>
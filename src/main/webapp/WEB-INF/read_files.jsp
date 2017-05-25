<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
     
<head>
	<jsp:include page="header.jsp">
    	<jsp:param name="title" value="Выбор документов"/>
	</jsp:include>
</head>

<body>
	<h1>Выбор документов</h1>
	<br/>
	
	<div class="container">
		<form id="patterns_form" enctype="multipart/form-data" action="/read" method="POST">
			<div class="form-group">
				<label for="id_file_pattern">Файлы шаблона</label>
				<input type="file" name="uploadingFiles" multiple />
			</div>
			
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
				<input class="btn btn-primary" type="submit" value="Загрузить"/>
				</div>
			</div>
			
		</form>
	</div>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>
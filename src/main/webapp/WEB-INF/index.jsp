<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
     
<head>
	<jsp:include page="header.jsp">
    	<jsp:param name="title" value="Заполнение документов"/>
	</jsp:include>
</head>

<body>
	<h1>Заполнение документов</h1>
	<br/>
	
	<div class="container">
		<form id="document_form" >
		
			<c:forEach items="${params}" var="param">
        	<div class="form-group row">
				<label for="id_surname" class="col-sm-2 col-form-label">${param.value}</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="${param.key}">
				</div>
			</div>
    		</c:forEach>
			
			<div class="form-group">
				<label for="id_file_pattern">Файлы шаблона</label>
				<input type="file" id="fileinput" multiple />
			</div>
    
			<div class="form-group row">
				<div class="offset-sm-2 col-sm-10">
				<button class="btn btn-primary" onclick="saveTextAsFile()">Загрузить</button>
				</div>
			</div>
		</form>
	</div>
	
	<input type="hidden" id="id_text"></input>
	<script type="text/javascript" src="${contextPath}/resources/js/document-form.js"></script>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>
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
		<form id="document_form" action="/upload">
			<c:forEach items="${params}" var="map">
	        	<div class="form-group row">
					<label for="id_surname" class="col-sm-2 col-form-label">${map.value}</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="${map.key}">
					</div>
				</div>
    		</c:forEach>
    		
    		<div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" id="bth-search" class="btn btn-primary btn-lg">
                    	Сформировать
                    </button>
                </div>
            </div>
		</form>
	</div>
	
	<jsp:include page="footer.jsp"/>
	<script type="text/javascript" src="${contextPath}/resources/js/document-form.js"></script>
</body>
</html>
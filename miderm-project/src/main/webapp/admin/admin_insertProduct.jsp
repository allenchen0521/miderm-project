<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../share/header.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_insertProduct</title>
<script src="<c:url value='/js/ckeditor/ckeditor.js' />"></script>
</head>
<script>
$(document).ready(function() {
	CKEDITOR.replace('textareaContent',{
		height: 400,
		filebrowserUploadUrl: 'http://localhost:8080/midermproject/admin/InsertProductCkEditorServlet?action=upload'
	});
})
</script>
<body>
	<textarea id="textareaContent"></textarea>
</body>
</html>
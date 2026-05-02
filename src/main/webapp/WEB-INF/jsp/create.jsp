<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { padding: 20px; background-color: #f8f9fa; }
        .container { max-width: 600px; background-color: white; padding: 30px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); margin-top: 50px; }
    </style>
</head>
<body>
    <div class="container">
        <h3 class="mb-4">Add New Book</h3>

        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>

        <form:form action="/create" method="post" modelAttribute="book">
            <div class="mb-3">
                <label for="title" class="form-label">Book Title</label>
                <form:input path="title" id="title" class="form-control" required="required" />
            </div>

            <div class="mb-3">
                <label for="isbn" class="form-label">ISBN</label>
                <form:input path="isbn" id="isbn" class="form-control" required="required" />
            </div>

            <div class="mb-3">
                <label for="author" class="form-label">Select Author</label>
                <form:select path="author.id" id="author" class="form-select" required="required">
                    <form:option value="" label="-- Select Author --"/>
                    <form:options items="${authors}" itemValue="id" itemLabel="name"/>
                </form:select>
            </div>

            <button type="submit" class="btn btn-success">Save Book</button>
            <a href="/" class="btn btn-secondary">Cancel</a>
        </form:form>
    </div>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Tour Packages</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>
<body>
    <header>
        <h1>Admin Portal</h1>
        <nav>
            <a href="${pageContext.request.contextPath}/admin">Admin Dashboard</a>
            <a href="${pageContext.request.contextPath}/admin/logout">Sign Out</a>
        </nav>
    </header>
    <main>
        <h2>Manage Tour Packages</h2>
        <c:if test="${not empty error}">
            <p style="color: red;">${error}</p>
        </c:if>
        <a href="${pageContext.request.contextPath}/admin/packages/add">Add New Package</a>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Title</th>
                    <th>Destination</th>
                    <th>Duration</th>
                    <th>Price</th>
                    <th>Featured</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="tourPackage" items="${packages}">
                    <tr>
                        <td>${tourPackage.id}</td>
                        <td>${tourPackage.title}</td>
                        <td>${tourPackage.destination}</td>
                        <td>${tourPackage.duration}</td>
                        <td>${tourPackage.price}</td>
                        <td>${tourPackage.featured}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/packages/edit/${tourPackage.id}">Edit</a>
                            <a href="${pageContext.request.contextPath}/admin/packages/delete/${tourPackage.id}" 
                               onclick="return confirm('Are you sure you want to delete this package?');">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </main>
    <footer>
        <p>Travel &copy; 2025</p>
    </footer>
</body>
</html>
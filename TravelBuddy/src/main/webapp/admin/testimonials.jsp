<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Testimonials</title>
</head>
<body>
    <h1>All Testimonials</h1>
    <table border="1">
        <tr>
            <th>Name</th>
            <th>Comment</th>
            <th>Rating</th>
        </tr>
        <c:forEach var="testimonial" items="${testimonials}">
            <tr>
                <td>${testimonial.name}</td>
                <td>${testimonial.comment}</td>
                <td>${testimonial.rating}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

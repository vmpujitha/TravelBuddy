<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Tour Package</title>
    <style>
        .error { color: red; }
    </style>
</head>
<body>
    <div class="container">
        <h2>Add Tour Package</h2>
        <c:if test="${not empty error}">
            <p class="error">${error}</p>
        </c:if>
        <form:form modelAttribute="tourPackage" method="post" action="${pageContext.request.contextPath}/admin/packages/add">
            <div>
                <label>Title:</label>
                <form:input path="title" required="true"/>
                <form:errors path="title" cssClass="error"/>
            </div>

            <div>
                <label>Destination:</label>
                <form:input path="destination" required="true"/>
                <form:errors path="destination" cssClass="error"/>
            </div>

            <div>
                <label>Duration:</label>
                <form:input path="duration" required="true"/>
                <form:errors path="duration" cssClass="error"/>
            </div>

            <div>
                <label>Price:</label>
                <form:input path="price" type="number" step="0.01" required="true"/>
                <form:errors path="price" cssClass="error"/>
            </div>

            <div>
                <label>Main Image URL:</label>
                <form:input path="imageUrl" required="true"/>
                <form:errors path="imageUrl" cssClass="error"/>
            </div>

            <div>
                <label>Description:</label>
                <form:textarea path="description" required="true"/>
                <form:errors path="description" cssClass="error"/>
            </div>

            <div>
                <label>Featured:</label>
                <form:checkbox path="featured"/>
            </div>

            <div>
                <label>Photos (comma-separated URLs):</label>
                <form:input path="photos"/>
            </div>

            <div>
                <label>Amenities (comma-separated):</label>
                <form:input path="amenities"/>
            </div>

            <div>
                <label>Offers (comma-separated):</label>
                <form:input path="offers"/>
            </div>

            <div>
                <label>Key Offerings (comma-separated):</label>
                <form:input path="keyOfferings"/>
            </div>

            <div>
                <label>Room Options (format: roomType|price|taxesAndFees|squareFeet|features|offers|refundable;...):</label>
                <form:textarea path="roomOptions" rows="5" placeholder="Standard Room|1500.0|300.0|172|Queen Bed,Air Conditioning|10% off on Spa|true;Deluxe Room|2000.0|400.0|269|King Bed,Heater|Breakfast Included|true"/>
            </div>

            <div>
                <label>Itinerary (format: day|title|description;...):</label>
                <form:textarea path="itinerary" rows="5" placeholder="1|Arrival and Check-in|Arrive at the destination and check into the hotel;2|Sightseeing Tour|Full-day sightseeing tour of the city"/>
                <form:errors path="itinerary" cssClass="error"/>
            </div>

            <div>
                <label>Included (comma-separated):</label>
                <form:input path="included"/>
            </div>

            <div>
                <label>Excluded (comma-separated):</label>
                <form:input path="excluded"/>
            </div>

            <button type="submit">Add Package</button>
        </form:form>
    </div>
</body>
</html>
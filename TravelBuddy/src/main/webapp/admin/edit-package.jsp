<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Tour Package</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Animate.css -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" rel="stylesheet">
    <!-- Custom CSS for Animations and Styling -->
    <style>
        body {
            background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Poppins', sans-serif;
        }
        .container {
            background: white;
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            max-width: 800px;
            margin: 20px auto;
        }
        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 30px;
            animation: fadeInDown 1s ease-in-out;
        }
        .form-label {
            font-weight: 500;
            color: #555;
        }
        .form-control, .form-check-input {
            border-radius: 8px;
            transition: all 0.3s ease;
        }
        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 10px rgba(0, 123, 255, 0.2);
            transform: scale(1.02);
        }
        .error {
            color: #dc3545;
            font-size: 0.9em;
            margin-top: 5px;
            animation: shake 0.5s ease-in-out;
        }
        .btn-primary {
            background: #007bff;
            border: none;
            padding: 10px 30px;
            border-radius: 8px;
            transition: all 0.3s ease;
            animation: pulse 2s infinite;
        }
        .btn-primary:hover {
            background: #0056b3;
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(0, 123, 255, 0.4);
        }
        /* Custom Animations */
        @keyframes pulse {
            0% { transform: scale(1); }
            50% { transform: scale(1.05); }
            100% { transform: scale(1); }
        }
        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            10%, 30%, 50%, 70%, 90% { transform: translateX(-5px); }
            20%, 40%, 60%, 80% { transform: translateX(5px); }
        }
        .form-group {
            margin-bottom: 20px;
            animation: fadeInUp 0.8s ease-in-out;
            animation-delay: calc(var(--delay) * 100ms);
        }
        .form-check-label {
            margin-left: 10px;
        }
    </style>
</head>
<body>
    <div class="container animate__animated animate__fadeIn">
        <h2>Edit Tour Package</h2>
        <c:if test="${not empty error}">
            <p class="error animate__animated animate__shakeX">${error}</p>
        </c:if>
        <form:form modelAttribute="tourPackage" method="post" action="${pageContext.request.contextPath}/admin/packages/edit/${tourPackage.id}">
            <div class="form-group" style="--delay: 1">
                <label for="title" class="form-label">Title:</label>
                <form:input path="title" id="title" cssClass="form-control" required="true" placeholder="Enter package title"/>
                <form:errors path="title" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <div class="form-group" style="--delay: 2">
                <label for="destination" class="form-label">Destination:</label>
                <form:input path="destination" id="destination" cssClass="form-control" required="true" placeholder="Enter destination"/>
                <form:errors path="destination" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <div class="form-group" style="--delay: 3">
                <label for="duration" class="form-label">Duration:</label>
                <form:input path="duration" id="duration" cssClass="form-control" required="true" placeholder="Enter duration (e.g., 5 days)"/>
                <form:errors path="duration" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <div class="form-group" style="--delay: 4">
                <label for="price" class="form-label">Price:</label>
                <form:input path="price" id="price" type="number" step="0.01" cssClass="form-control" required="true" placeholder="Enter price"/>
                <form:errors path="price" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <div class="form-group" style="--delay: 5">
                <label for="imageUrl" class="form-label">Main Image URL:</label>
                <form:input path="imageUrl" id="imageUrl" cssClass="form-control" required="true" placeholder="Enter main image URL"/>
                <form:errors path="imageUrl" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <div class="form-group" style="--delay: 6">
                <label for="description" class="form-label">Description:</label>
                <form:textarea path="description" id="description" cssClass="form-control" rows="4" required="true" placeholder="Enter package description"/>
                <form:errors path="description" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <div class="form-group form-check" style="--delay: 7">
                <form:checkbox path="featured" id="featured" cssClass="form-check-input"/>
                <label for="featured" class="form-check-label">Featured</label>
            </div>

            <%-- Precompute the photos string --%>
            <c:set var="photosString">
                <c:choose>
                    <c:when test="${not empty tourPackage.photos}">
                        <c:forEach var="photo" items="${tourPackage.photos}" varStatus="loop">
                            <c:out value="${photo}${loop.last ? '' : ','}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value=""/>
                    </c:otherwise>
                </c:choose>
            </c:set>
            <div class="form-group" style="--delay: 8">
                <label for="photos" class="form-label">Photos (comma-separated URLs):</label>
                <form:input path="photos" id="photos" cssClass="form-control" placeholder="Enter photo URLs (e.g., url1, url2)" value="${photosString}"/>
                <c:if test="${empty photosString}">
                    <form:input path="photos" type="hidden" value=""/>
                </c:if>
                <form:errors path="photos" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <%-- Precompute the amenities string --%>
            <c:set var="amenitiesString">
                <c:choose>
                    <c:when test="${not empty tourPackage.amenities}">
                        <c:forEach var="amenity" items="${tourPackage.amenities}" varStatus="loop">
                            <c:out value="${amenity}${loop.last ? '' : ','}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value=""/>
                    </c:otherwise>
                </c:choose>
            </c:set>
            <div class="form-group" style="--delay: 9">
                <label for="amenities" class="form-label">Amenities (comma-separated):</label>
                <form:input path="amenities" id="amenities" cssClass="form-control" placeholder="Enter amenities (e.g., Wi-Fi, Pool)" value="${amenitiesString}"/>
                <c:if test="${empty amenitiesString}">
                    <form:input path="amenities" type="hidden" value=""/>
                </c:if>
                <form:errors path="amenities" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <%-- Precompute the offers string --%>
            <c:set var="offersString">
                <c:choose>
                    <c:when test="${not empty tourPackage.offers}">
                        <c:forEach var="offer" items="${tourPackage.offers}" varStatus="loop">
                            <c:out value="${offer}${loop.last ? '' : ','}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value=""/>
                    </c:otherwise>
                </c:choose>
            </c:set>
            <div class="form-group" style="--delay: 10">
                <label for="offers" class="form-label">Offers (comma-separated):</label>
                <form:input path="offers" id="offers" cssClass="form-control" placeholder="Enter offers (e.g., 10% Discount, Free Breakfast)" value="${offersString}"/>
                <c:if test="${empty offersString}">
                    <form:input path="offers" type="hidden" value=""/>
                </c:if>
                <form:errors path="offers" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <%-- Precompute the keyOfferings string --%>
            <c:set var="keyOfferingsString">
                <c:choose>
                    <c:when test="${not empty tourPackage.keyOfferings}">
                        <c:forEach var="offering" items="${tourPackage.keyOfferings}" varStatus="loop">
                            <c:out value="${offering}${loop.last ? '' : ','}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value=""/>
                    </c:otherwise>
                </c:choose>
            </c:set>
            <div class="form-group" style="--delay: 11">
                <label for="keyOfferings" class="form-label">Key Offerings (comma-separated):</label>
                <form:input path="keyOfferings" id="keyOfferings" cssClass="form-control" placeholder="Enter key offerings (e.g., Guided Tours, Luxury Stay)" value="${keyOfferingsString}"/>
                <c:if test="${empty keyOfferingsString}">
                    <form:input path="keyOfferings" type="hidden" value=""/>
                </c:if>
                <form:errors path="keyOfferings" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <%-- Precompute the roomOptions string with default values --%>
            <c:set var="roomOptionsString">
                <c:choose>
                    <c:when test="${not empty tourPackage.roomOptions}">
                        <c:forEach var="room" items="${tourPackage.roomOptions}" varStatus="loop">
                            <c:set var="roomType" value="${room.roomType != null ? room.roomType : 'Unknown Room Type'}"/>
                            <c:set var="price" value="${room.price != null ? room.price : '0.0'}"/>
                            <c:set var="taxesAndFees" value="${room.taxesAndFees != null ? room.taxesAndFees : '0.0'}"/>
                            <c:set var="squareFeet" value="${room.squareFeet != null ? room.squareFeet : '0'}"/>
                            <c:set var="features" value="${room.features != null && room.features != '' ? room.features : 'None'}"/>
                            <c:set var="offers" value="${room.offers != null && room.offers != '' ? room.offers : 'None'}"/>
                            <c:set var="refundable" value="${room.refundable != null ? room.refundable : 'false'}"/>
                            <c:out value="${roomType}|${price}|${taxesAndFees}|${squareFeet}|${features}|${offers}|${refundable}${loop.last ? '' : ';'}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value=""/>
                    </c:otherwise>
                </c:choose>
            </c:set>
            <div class="form-group" style="--delay: 12">
                <label for="roomOptions" class="form-label">Room Options (format: roomType|price|taxesAndFees|squareFeet|features|offers|refundable;...):</label>
                <form:textarea path="roomOptions" id="roomOptions" cssClass="form-control" rows="5" placeholder="e.g., Deluxe Room|1500.0|200.0|300|AC, Wi-Fi|Free Breakfast|true" value="${roomOptionsString}"/>
                <c:if test="${empty roomOptionsString}">
                    <form:input path="roomOptions" type="hidden" value=""/>
                </c:if>
                <form:errors path="roomOptions" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <%-- Precompute the itinerary string with default values --%>
            <c:set var="itineraryString">
                <c:choose>
                    <c:when test="${not empty tourPackage.itinerary}">
                        <c:forEach var="item" items="${tourPackage.itinerary}" varStatus="loop">
                            <c:set var="day" value="${item.day != null ? item.day : '1'}"/>
                            <c:set var="title" value="${item.title != null ? item.title : 'Untitled Day'}"/>
                            <c:set var="description" value="${item.description != null ? item.description : 'No description'}"/>
                            <c:out value="${day}|${title}|${description}${loop.last ? '' : ';'}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value=""/>
                    </c:otherwise>
                </c:choose>
            </c:set>
            <div class="form-group" style="--delay: 13">
                <label for="itinerary" class="form-label">Itinerary (format: day|title|description;...):</label>
                <form:textarea path="itinerary" id="itinerary" cssClass="form-control" rows="5" placeholder="e.g., 1|Arrival|Arrive in Zurich;2|Ski Day|Skiing on the slopes" value="${itineraryString}"/>
                <c:if test="${empty itineraryString}">
                    <form:input path="itinerary" type="hidden" value=""/>
                </c:if>
                <form:errors path="itinerary" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <%-- Precompute the included string --%>
            <c:set var="includedString">
                <c:choose>
                    <c:when test="${not empty tourPackage.included}">
                        <c:forEach var="item" items="${tourPackage.included}" varStatus="loop">
                            <c:out value="${item}${loop.last ? '' : ','}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value=""/>
                    </c:otherwise>
                </c:choose>
            </c:set>
            <div class="form-group" style="--delay: 14">
                <label for="included" class="form-label">Included (comma-separated):</label>
                <form:input path="included" id="included" cssClass="form-control" placeholder="Enter included items (e.g., Breakfast, Transfers)" value="${includedString}"/>
                <c:if test="${empty includedString}">
                    <form:input path="included" type="hidden" value=""/>
                </c:if>
                <form:errors path="included" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <%-- Precompute the excluded string --%>
            <c:set var="excludedString">
                <c:choose>
                    <c:when test="${not empty tourPackage.excluded}">
                        <c:forEach var="item" items="${tourPackage.excluded}" varStatus="loop">
                            <c:out value="${item}${loop.last ? '' : ','}"/>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <c:out value=""/>
                    </c:otherwise>
                </c:choose>
            </c:set>
            <div class="form-group" style="--delay: 15">
                <label for="excluded" class="form-label">Excluded (comma-separated):</label>
                <form:input path="excluded" id="excluded" cssClass="form-control" placeholder="Enter excluded items (e.g., Personal Expenses, Tips)" value="${excludedString}"/>
                <c:if test="${empty excludedString}">
                    <form:input path="excluded" type="hidden" value=""/>
                </c:if>
                <form:errors path="excluded" cssClass="error animate__animated animate__shakeX"/>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary">Update Package</button>
            </div>
        </form:form>
    </div>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <!-- Custom JS for Additional Interactivity -->
    <script>
        // Add focus animation to form inputs
        document.querySelectorAll('.form-control').forEach(input => {
            input.addEventListener('focus', function() {
                this.classList.add('animate__animated', 'animate__pulse');
            });
        });

        // Validate form on submit
        document.querySelector('form').addEventListener('submit', function(event) {
            let isValid = true;
            document.querySelectorAll('input[required], textarea[required]').forEach(field => {
                if (!field.value.trim()) {
                    isValid = false;
                    field.classList.add('animate__animated', 'animate__shakeX');
                    const errorElement = field.nextElementSibling;
                    if (errorElement && errorElement.classList.contains('error')) {
                        errorElement.textContent = 'This field is required.';
                    }
                }
            });
            if (!isValid) {
                event.preventDefault();
            }
        });
    </script>
</body>
</html>
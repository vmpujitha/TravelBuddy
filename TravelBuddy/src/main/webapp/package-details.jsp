<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>${tourPackage.title} - Details</title>
    <link rel="stylesheet" href="/css/package.css">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <h1>${tourPackage.title}</h1>
        <p><strong>Location:</strong> ${tourPackage.destination} | ${tourPackage.duration}</p>
        <p>${tourPackage.description}</p>

        <!-- Photo Gallery -->
        <div class="photo-gallery">
            <c:forEach var="photo" items="${tourPackage.photos}">
                <img src="${photo}" alt="Package Photo" class="gallery-img">
            </c:forEach>
        </div>

        <!-- Amenities -->
        <div class="amenities-section">
            <h3>Amenities</h3>
            <ul class="amenities-list">
                <c:forEach var="amenity" items="${tourPackage.amenities}">
                    <li>${amenity}</li>
                </c:forEach>
            </ul>
        </div>

        <!-- Room Options -->
        <div class="room-options-section">
            <h3>Room Options</h3>
            <c:forEach var="room" items="${tourPackage.roomOptions}">
                <div class="room-card">
                    <h4>${room.roomType}</h4>
                    <p><strong>Size:</strong> ${room.squareFeet} sq.ft</p>
                    <p><strong>Features:</strong></p>
                    <ul>
                        <c:forEach var="feature" items="${room.features}">
                            <li>${feature}</li>
                        </c:forEach>
                    </ul>
                    <p><strong>Offers:</strong></p>
                    <ul>
                        <c:forEach var="offer" items="${room.offers}">
                            <li>${offer}</li>
                        </c:forEach>
                    </ul>
                    <p><strong>Price:</strong> ₹${room.price} + ₹${room.taxesAndFees} Taxes & Fees per night</p>
                    <p>${room.refundable ? 'Refundable' : 'Non-Refundable'}</p>
                    <a href="/booking?packageId=${tourPackage.id}" class="select-room-btn">Select Room</a>
                </div>
            </c:forEach>
        </div>

        <!-- Key Offerings -->
        <div class="key-offerings-section">
            <h3>Key Offerings</h3>
            <div class="key-offerings">
                <c:forEach var="offering" items="${tourPackage.keyOfferings}">
                    <div class="offering-item">${offering}</div>
                </c:forEach>
            </div>
        </div>
    </div>
    <script src="/JS/package-detail.js"></script>
</body>
</html>
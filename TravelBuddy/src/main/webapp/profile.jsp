<%-- profile.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelLux - My Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="navbar.jsp"/>

    <section class="py-5">
        <div class="container">
            <h2 class="font-serif fs-3 fw-bold text-primary mb-4">My Profile</h2>
            <div class="row">
                <div class="col-md-4 mb-4">
                    <div class="bg-white rounded shadow p-4">
                        <h3 class="font-serif fs-5 fw-bold mb-3">Account Details</h3>
                        <p><strong>Username:</strong> ${user.username}</p>
                        <p><strong>Email:</strong> ${user.email}</p>
                        <p><strong>Phone:</strong> ${user.phone}</p>
                        <a href="/logout" class="btn btn-outline-primary w-100">Logout</a>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="bg-white rounded shadow p-4">
                        <h3 class="font-serif fs-5 fw-bold mb-3">My Bookings</h3>
                        <c:choose>
                            <c:when test="${not empty bookings}">
                                <c:forEach var="booking" items="${bookings}">
                                    <div class="border-bottom py-3">
                                        <h4 class="fs-6 fw-bold text-primary">${booking.tourPackage.title}</h4>
                                        <p class="small text-muted"><i class="bi bi-geo-alt me-1"></i>${booking.tourPackage.destination}</p>
                                        <p class="small text-muted"><i class="bi bi-calendar me-1"></i>Travel Date: ${booking.travelDate}</p>
                                        <p class="small text-muted"><i class="bi bi-people me-1"></i>Adults: ${booking.adults}, Children: ${booking.children}</p>
                                        <p class="small text-muted"><i class="bi bi-clock me-1"></i>Booked on: ${booking.bookingDate}</p>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p class="text-muted">You have no bookings yet. <a href="/packages">Explore our packages</a> to start planning your trip!</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/js/scripts.js"></script>
</body>
</html>
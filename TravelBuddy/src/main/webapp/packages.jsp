<%-- packages.jsp --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelLux - Tour Packages</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500&family=Playfair+Display:wght@700&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="navbar.jsp"/>

    <!-- Hero Section -->
    <section class="hero-section" style="background-image: url('https://cdn.pixabay.com/photo/2017/03/27/16/50/beach-2179624_1280.jpg');">
        <div class="hero-overlay"></div>
        <div class="container position-relative">
            <div class="max-w-3xl">
                <h1 class="display-3 font-serif fw-bold text-white mb-4">Luxury Tour Packages</h1>
                <p class="lead text-white-90 mb-4">Curated travel experiences designed for the discerning traveler</p>
            </div>
        </div>
    </section>

    <!-- Packages Section -->
    <section class="py-5">
        <div class="container">
            <!-- Search and Filters -->
            <div class="bg-white rounded shadow p-4 mb-5">
                <form id="package-filter-form">
                    <div class="row g-3">
                        <div class="col-md-3">
                            <div class="input-group">
                                <span class="input-group-text"><i class="bi bi-search"></i></span>
                                <input type="text" id="search" name="search" class="form-control" placeholder="Search packages..." value="${search}">
                            </div>
                        </div>
                        <div class="col-md-3">
                            <select id="destination" name="destination" class="form-select">
                                <option value="">All Destinations</option>
                                <c:forEach var="dest" items="${destinations}">
                                    <option value="${dest}" ${dest == selectedDestination ? 'selected' : ''}>${dest}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select id="duration" name="duration" class="form-select">
                                <option value="">All Durations</option>
                                <c:forEach var="dur" items="${durations}">
                                    <option value="${dur}" ${dur == selectedDuration ? 'selected' : ''}>${dur}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <div class="d-flex align-items-center mb-2">
                                <i class="bi bi-currency-dollar me-1 text-muted"></i>
                                <span class="small fw-medium text-muted">Price Range: $${priceMin} - <span id="price-range-value">$${priceMax}</span></span>
                            </div>
                            <input type="range" id="price-range" name="priceMax" class="form-range" min="0" max="10000" step="500" value="${priceMax}">
                        </div>
                        <div class="col-12 text-center mt-3">
                            <button type="submit" class="btn btn-primary">Apply Filters</button>
                        </div>
                    </div>
                </form>
            </div>

            <!-- Results -->
            <div>
                <div class="d-flex justify-content-between align-items-center mb-4">
                    <h2 class="font-serif fs-3 fw-bold text-primary">${packages.size()} Tour Packages Found</h2>
                    <div class="d-flex align-items-center">
                        <i class="bi bi-funnel me-2 text-muted"></i>
                        <select class="form-select">
                            <option>Sort by Popularity</option>
                            <option>Sort by Price: Low to High</option>
                            <option>Sort by Price: High to Low</option>
                            <option>Sort by Duration</option>
                        </select>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${not empty packages}">
                        <div class="row">
                            <c:forEach var="pkg" items="${packages}">
                                <div class="col-md-4 mb-4">
                                    <div class="card">
                                        <div class="position-relative">
                                            <img src="${pkg.imageUrl}" class="card-img-top" alt="${pkg.title}" style="height: 200px; object-fit: cover;">
                                            <c:if test="${pkg.featured}">
                                                <span class="position-absolute top-0 start-0 bg-warning text-white small px-3 py-1 rounded-end">Featured</span>
                                            </c:if>
                                        </div>
                                        <div class="card-body">
                                            <h5 class="font-serif fw-bold text-primary">${pkg.title}</h5>
                                            <p class="small text-muted"><i class="bi bi-geo-alt me-1"></i>${pkg.destination}</p>
                                            <p class="small text-muted"><i class="bi bi-clock me-1"></i>${pkg.duration}</p>
                                            <p class="text-muted small">${pkg.description}</p>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div>
                                                    <p class="small text-muted mb-0">Starting from</p>
                                                    <p class="fs-5 font-serif fw-bold text-primary">$${pkg.price}</p>
                                                    <p class="small text-muted">per person</p>
                                                </div>
                                                <a href="/packages/${pkg.id}" class="btn btn-primary btn-sm">View Details <i class="bi bi-chevron-right ms-1"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="text-center py-5">
                            <p class="fs-5 text-muted">No tour packages found matching your criteria.</p>
                            <a href="/packages" class="btn btn-outline-primary">Clear Filters</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </section>

    <jsp:include page="footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="/js/scripts.js"></script>
</body>
</html>
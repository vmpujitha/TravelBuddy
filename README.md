# 🌍 Travel Buddy – Java Full Stack Web Application

## 💡 Overview
**Travel Buddy** is a full-stack Travel and Tourism Management System web application built using Java technologies. It connects tourists with local homestay destinations, providing a platform to search, filter, explore, and book authentic travel experiences. The system also enables interaction with local guides for insights into cultural and regional attractions, helping travelers make better vacation decisions while supporting local communities.

## 🧩 Modules
### 1. 👤 User Module
- Allows users (tourists and hosts) to register and log in.
- Secure login system with role-based access control.
- Tourists can search and book homestays.
- Hosts can manage their property listings.

### 2. 🛠️ Admin Module
- Manages users, properties, feedback, and tour packages.
- Adds and edits destinations and handles reported issues.
- Monitors system activities.

## 🔧 Tech Stack
- **Backend**: Spring Boot (Java)
- **Frontend**: HTML, CSS, Bootstrap
- **Database**: MySQL
- **Persistence Layer**: JPA (Java Persistence API)
- **Build Tool**: Maven
- **IDE**: Eclipse / IntelliJ

## 🎯 Key Features
- 👥 User and Admin registration/login
- 🏡 Homestay listing and management (CRUD)
- 🔍 Search and filter by location, price, rating
- 📅 Real-time booking system with confirmation
- 🧭 Local guide recommendations
- ✉️ Contact and feedback form
- 📱 Responsive UI using Bootstrap

## 🧱 Project Structure (MVC Architecture)
src/
├── controller/ # Handles incoming requests and routes
├── model/ # Java entity classes for database
├── repository/ # Spring Data JPA interfaces
├── service/ # Business logic and service layer
├── resources/ # Configuration and templates
└── webapp/ # Frontend JSP or HTML files

## 🚀 How to Run the Project
1. Clone the repository from GitHub.
2. Open it in your IDE (Eclipse or IntelliJ).
3. Create a MySQL database named `travelbuddy_db`.
4. Update `application.properties` with your MySQL credentials.
5. Run the main Spring Boot application class.
6. Open a browser and go to `http://localhost:8080`.

## 📸 Screenshots (Optional - Add image links)
- Homepage
- Search Page
- Booking Confirmation
- Admin Dashboard

## 👩‍💻 Developer
**Pujitha Jangalapalli**  
- Developed end-to-end CRUD operations.  
- Designed responsive frontend UI.  
- Built secure authentication system.  
- Integrated booking logic and dashboard flows.

# Atlas Culinary System Backend

This is a backend API for the Atlas Culinary system developed using Spring Boot with JPA and PostgreSQL.

## System Requirements

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+

## Installation and Setup

### 1. Database Preparation

Create PostgreSQL database:
```sql
CREATE DATABASE atlas_culinary_db;

### 2. Build and Run Application

```bash
mvn clean install
mvn spring-boot:run
```

Or run the jar file directly:
```bash
java -jar target/atlas-culinary-backend-0.0.1-SNAPSHOT.jar
```

## Entity Structure

This project is designed using a code-first approach with the following entities:

### Core Entities
- **Account**: User account information management
- **UserProfile**: Detailed information for regular users
- **VendorProfile**: Detailed information for vendors/restaurant owners
- **AdminProfile**: Detailed information for administrators

### Geographic Entities
- **Province**: Province/City
- **District**: District/County
- **Ward**: Ward/Commune

### Business Entities
- **BusinessLicense**: Vendor business license
- **Restaurant**: Restaurant information
- **Dish**: Dish information

### Tag System
- **DishTag**: Tags for dishes
- **RestaurantTag**: Tags for restaurants
- **DishTagMap**: Dish-tag mapping
- **RestaurantTagMap**: Restaurant-tag mapping

### Interaction Entities
- **Review**: User reviews and ratings
- **Report**: Violation reports
- **Notification**: System notifications

### Analytics & Recommendation
- **RestaurantStats**: Restaurant statistics
- **SearchHistory**: User search history
- **RecommendationData**: Recommendation data

### Permission System
- **Role**: System roles
- **Action**: Available actions
- **RoleActionMap**: Role-action mapping
- **AccountRoleMap**: Account-role assignment

## Supported Use Cases

The Atlas Culinary system supports the following main use cases:

### 1. Account Management (Admin)
- View, search, and filter account lists
- Block/unblock accounts
- Manage permissions and roles
- Create new accounts
- Send system notifications
- View account statistics

### 2. Personal Information Management
- Account registration/login
- View and edit personal information
- Password recovery

### 3. Restaurant Management (Vendor)
- Register restaurant on map
- Manage restaurant information (menu, prices, opening hours)
- Receive notifications about feedback/reports
- Manage restaurant status
- View basic statistics
- Restaurant approval (Admin)

### 4. Dish Management (Vendor)
- Add/update dish information
- Manage dish status
- Dish approval (Admin)

### 5. Review & Comment Management
- Add, edit, delete reviews (User)
- View public reviews
- Respond to reviews (Vendor)
- Report inappropriate reviews
- Review moderation (Admin)

### 6. Report Management
- Submit violation reports
- Track report status
- Process reports (Admin)
- Report statistics

### 7. Culinary Discovery
- View culinary map
- Search & filter dishes/restaurants
- View detailed restaurant/dish information
- Smart dish recommendations

## API Documentation

Server run on: `http://localhost:8080`
test


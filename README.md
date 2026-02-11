# FoodFactory 🍽️

Welcome to **FoodFactory** – a modern, full-stack food ordering web application built with Spring Boot, Thymeleaf, and MySQL. This project is designed to provide a seamless experience for users to browse, register, log in, and order delicious food online, while also offering robust admin management features.

---

## 🚀 Features

- User registration & secure login
- Beautiful, responsive UI with modern CSS
- Browse a variety of fast food items
- Add, update, and delete users (admin)
- Add, update, and delete products (admin)
- Order food and view order success page
- Error handling and user-friendly messages
- Password visibility toggle for better UX
- MySQL database integration

---

## 🖥️ Tech Stack

- **Backend:** Spring Boot, Java
- **Frontend:** Thymeleaf, HTML5, CSS3, JavaScript
- **Database:** MySQL
- **Version Control:** Git & GitHub

---

## 📦 Project Structure

```
FoodFactory/
├── src/
│   ├── main/
│   │   ├── java/com/example/demo/           # Java source code
│   │   ├── resources/
│   │   │   ├── static/                      # CSS, JS, Images
│   │   │   └── templates/                   # Thymeleaf HTML templates
│   │   └── ...
│   └── test/
├── pom.xml                                 # Maven config
├── README.md
└── ...
```

---

## ⚡ Quick Start

1. **Clone the repository:**
   ```bash
   git clone https://github.com/imvaibhav100/Foodfactory.git
   cd Foodfactory
   ```

2. **Setup MySQL Database:**
   - Install and start MySQL server on your local machine
   - Create a database (it will be auto-created if it doesn't exist):
     ```sql
     CREATE DATABASE foodfactory;
     ```
   - Update MySQL credentials in `src/main/resources/application-local.properties` if needed:
     ```properties
     spring.datasource.username=root
     spring.datasource.password=YourPassword
     ```

3. **Build and run the project:**
   
   **Option 1: Using Maven (Recommended for local development)**
   ```bash
   ./mvnw spring-boot:run
   ```
   
   **Option 2: Using IDE**
   - Run `FoodFactoryApplication.java` as a Spring Boot Application
   
   **Note:** The application is configured to use local MySQL by default. For production deployment on Render (Oracle MySQL Cloud), set environment variable `SPRING_PROFILES_ACTIVE=prod`

4. **Open in browser:**
   - Visit [http://localhost:8080](http://localhost:8080)

---

## 🗄️ Database Configuration

This project uses **MySQL** (TiDB Cloud compatible) for both local development and production:

### **Local Development (Default)**
- **File:** `application.properties`
- **Database:** Local MySQL Server (localhost:3306)
- **Credentials:** Update in `application.properties`
  ```properties
  spring.datasource.username=root
  spring.datasource.password=Mydatabase@2006
  ```

### **Production - Render (TiDB Cloud)**
- **File:** `application-prod.properties`
- **Activation:** Set `SPRING_PROFILES_ACTIVE=prod` on Render
- **Required Environment Variables:**
  ```bash
  SPRING_PROFILES_ACTIVE=prod
  DATABASE_URL=jdbc:mysql://3AHxi4bKkwrCqvA.root:password@gateway01.ap-southeast-1.prod.aws.tidbcloud.com:4000/test
  DB_USER=3AHxi4bKkwrCqvA.root
  DB_PASSWORD=GxgBEyntx5OqzQ5t
  ```

---

## 👤 Contributors

- Vaibhav (imvaibhav100)
- [Add your name here!]

---

## 📸 Screenshots

<p align="center">
  <img src="src/main/resources/static/Images/homepage.jpg" alt="Home Page" width="400"/>
  <img src="src/main/resources/static/Images/foodielogo1.png" alt="Logo" width="120"/>
</p>

---

## 💡 Inspiration

This project was created to learn and demonstrate full-stack web development with Java Spring Boot, and to provide a real-world food ordering experience.

---

## 📬 Feedback

Have suggestions or want to contribute? Open an issue or submit a pull request!

---

<p align="center"><b>Bon Appétit! 🍕🍔🍟</b></p>
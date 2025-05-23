# 🥗 MyPlateFit App

**MyPlateFit** is a Spring Boot-powered web application for personalized health and nutrition tracking. It provides users with tools to log their weight, monitor meals, calculate BMI, and track daily calorie goals. Inspired by the USDA MyPlate model, this app offers a clean, intuitive interface built with Thymeleaf and Bootstrap.

---

## 🚀 Features

- ✅ **User Authentication** (Register/Login)
- 🔐 **Forgot Password** flow with Gmail SMTP reset link
- ⚖️ **Weight & BMI Tracking** with visual history
- 🍽️ **Meal Logging** and calorie counting
- 🔍 **USDA Food API Integration** for nutrient auto-fill
- 🛠️ Responsive UI with health-inspired green theme

---

## 💻 Tech Stack

| Layer       | Tools                                   |
|-------------|------------------------------------------|
| Backend     | Java 17, Spring Boot, Spring Security    |
| Frontend    | Thymeleaf, Bootstrap 5, Chart.js         |
| Database    | PostgreSQL                               |
| Email       | Spring Mail + Gmail SMTP                 |
| APIs        | USDA FoodData Central                    |

---

## 📸 Screenshots

> _Coming soon_: login/register/dashboard UI previews

---

## 🛠️ Setup Instructions

1. **Clone the repository**

```bash
git clone https://github.com/Garima001011/myplatefit-app.git
cd myplatefit-app
```

2. **Configure Database** in `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nutrition_db
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```

3. **Configure Gmail SMTP** for password reset:

```properties
spring.mail.username=your_gmail@gmail.com
spring.mail.password=your_app_password
```

4. **Run the app**

```bash
./mvnw spring-boot:run
```

Access: `http://localhost:8080`

---

## 📬 Contact

**Developer:** Garima Niraula 
**GitHub:** [Garima001011](https://github.com/Garima001011)  
**Email:** garimaniraula258@gmail.com

---

> Built with ❤️ for wellness, balance, and modern nutrition tracking.

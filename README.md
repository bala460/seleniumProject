# UI Automation Framework – Selenium Java with Extent Reports

## 📌 Overview
This project is a **UI Test Automation Framework** built using **Selenium WebDriver**, **Java**, and **TestNG**, with **Extent Reports** for rich and interactive test reporting.  
It follows best practices such as **Page Object Model (POM)**, reusable utilities, and clean test structure to support scalable and maintainable automation.

---

## 🛠 Tech Stack
- **Language**: Java
- **Automation Tool**: Selenium WebDriver
- **Test Runner**: TestNG
- **Build Tool**: Maven
- **Reporting**: Extent Reports
- **Design Pattern**: Page Object Model (POM)
- **Browser Support**: Chrome, Firefox, Edge

---

## ⚙️ Prerequisites
- Java **JDK 11+**
- Maven **3.6+**
- Chrome / Firefox browser
- IDE (IntelliJ IDEA / Eclipse)

---

## 📦 Maven Dependencies
Key dependencies used:
- `selenium-java`
- `testng`
- `extent-reports`
- `webdrivermanager`

All dependencies are managed in `pom.xml`.

---

## 🚀 How to Run Tests

### Run via Maven
```bash
mvn clean test
mvn clean test -DsuiteXmlFile=testng.xml
```

## 📊 Extent Report

- Extent Report is automatically generated after execution

- **Location:**
```bash
/test-output/extentReport.html
```

## Report Features

- Step-wise execution logs 
- Pass / Fail / Skip status 
- Screenshots on failure 
- Execution time & environment info

## 🧩 Key Framework Features

- Centralized WebDriver management
- Page Object Model (POM)
- Reusable utilities and helpers
- Parallel execution support
- Extent Report integration
- Configurable via properties file
- Easy browser switching

## 📸 Screenshot on Failure

- Screenshots are captured automatically on test failure
- Embedded directly into Extent Report

## 👨‍💻 Author

- **Name:** Bala Gopal Pasupuleti
- **Email:** balagopal563@gmail.com
- **SDET / Automation Engineer**

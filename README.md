# Selenium Cucumber Automation Framework



A scalable and maintainable Selenium Automation Framework built using:

* Java
* Selenium WebDriver
* Cucumber BDD
* TestNG
* Maven
* Page Object Model (POM)
* Apache POI (Excel Data Handling)
* Extent Reports
* Allure Reports

\---





## Features



* Cucumber BDD Framework
* Page Object Model Design Pattern
* TestNG Integration
* Parallel Execution Support
* Excel Data Driven Testing
* Reusable Utility Classes
* WebDriver Manager Integration
* Extent HTML Reporting
* Allure Reporting
* Cross Browser Support
* Environment Configurable
* Maven Build Support
* CI/CD Friendly Framework Structure

\---





## Installation \& Setup



### Clone Repository



git clone https://github.com/souravsahu3490/selenium-cucumber-framework-saucedemo.git



### Navigate to Project



cd your-repo-name



### Install Dependencies



mvn clean install



\---





## Running Tests



### Run Tests based on tags provided in `testng.xml`



mvn clean test



### Run Tests and Generate Allure Report



mvn clean test allure:report



### Run Tests and Open Allure Report



mvn clean test allure:serve



\---





## Reporting



Framework generates:

* Extent HTML Reports
* Allure Reports
* Console Logs
* TestNG Reports





### Report Locations



/target/cucumber-report.html
/target/extent-report/ExtentReport.html
/target/site/allure-maven-plugin/index.html
---





## Parallel Execution



Framework supports parallel execution using:

* TestNG Parallel Suite
* Thread-safe WebDriver Handling

\---





## Browser Support



Supported browsers:

* Chrome
* Edge
* Firefox

Browser can be configured from:

* `config.properties`

\---





## Best Practices Implemented



* Explicit Waits
* Centralized Configuration
* Reusable Utilities
* Clean Page Object Structure
* Minimal Hardcoded Values
* Modular Framework Design

\---





## Author

**Sourav Sahu**


## Автоматизация тестов для REST API
В данном проекте представлен пример API автотестов на проверку функциональности сайтов [**Demo Web Shop**](http://demowebshop.tricentis.com), [**Book Store**](https://demoqa.com/books/), [**ReqRes.in**](https://reqres.in/).<br/>
### Тестируемая функциональность


**Reqres.in**
- Create new user;
- Update user info;
- Successful user registration;
- Unsuccessful user registration;
- Checking project support message;
- Find a user by name/surname.


**Book Store**
- User registration;
- User token generation;
- Getting a book info by ISBN.


**Demo Web Shop**
- User registration;
- Adding an item to the Shopping Cart;
- Sending feedback by 'Contact Us' form.

---
### Использованный технологический стек
<img src="src/test/resources/images/logos/java.svg" width="27" /> `Java` 
<img src="src/test/resources/images/logos/rest-assured.svg" width="33" /> `REST Assured` 
<img src="src/test/resources/images/logos/junit.svg" width="33" /> `JUnit` 
<img src="src/test/resources/images/logos/selenide.svg" width="42" /> `Selenide` - написание исходного кода;<br/>
<img src="src/test/resources/images/logos/gradle.svg" width="35" height="35" /> `Gradle` - сборка проекта;<br/>
<img src="src/test/resources/images/logos/jenkins.svg" width="35" height="35" /> `Jenkins` - конфигурация и запуск сборок;<br/>
<img src="src/test/resources/images/logos/postman.svg" width="29" height="29" /> `Postman` - отправка тестовых запросов;<br/>
<img src="src/test/resources/images/logos/allure.svg" width="28" /> `Allure Report` - формирование отчетности по итогам тестирования;<br/>
<img src="src/test/resources/images/logos/allure_testops.svg" width="24.7" /> `Allure TestOps` - создание тестовой документации;<br/>
<img src="src/test/resources/images/logos/jira.svg" width="30" /> `Jira` - создание задачи в таск-трекере.<br/>


---
### Запуск тестов
Для запуска автотестов сконфигурирована :arrow_right: **[job](https://jenkins.autotests.cloud/job/08-WakeUpTheo-REST-Assured/)** :arrow_left: в CI Jenkins. В Jenkins job добавлена интеграция с Allure Report и Allure TestOps, а также настроена расслылка оповещений о результатах выполнения тестов в мессенджеры Slack и Telegram.<br/>

Команда для запуска тестов в консоли:
```
gradle clean test
```

---
### Результаты выполнения тестов

### Отчет в Allure Report
![](src/test/resources/images/screenshots/allure_1.png)


![](src/test/resources/images/screenshots/allure_2.png)


![](src/test/resources/images/screenshots/allure_3.png)

---
### Тестовая документация, сгенерированная в [Allure TestOps](https://allure.autotests.cloud/project/815/dashboards) по итогам прохождения тестов
![](src/test/resources/images/screenshots/testops_1.png)


![](src/test/resources/images/screenshots/testops_2.png)

---
### [Задача](https://jira.autotests.cloud/browse/HOMEWORK-295) в таск-трекере Jira с данными, экспортированными из Allure TestOps
![](src/test/resources/images/screenshots/jira.png)

---
### Оповещения о статусе сборки в мессенджерах
<img src="src/test/resources/images/logos/slack.svg" width="80" /><br/>
![](src/test/resources/images/screenshots/slack_notice.png)
---
<img src="src/test/resources/images/logos/telegram.svg" width="100" /><br/>
![](src/test/resources/images/screenshots/telegram_notice.png)

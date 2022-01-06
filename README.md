## Автоматизация тестов для REST API
В данном проекте представлен пример API автотестов на проверку функциональности сайтов [**Demo Web Shop**](http://demowebshop.tricentis.com), [**Book Store (demoqa.com)**](https://demoqa.com/books/), [**ReqRes.in**](https://reqres.in/).<br/>
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
<img src="./images/logos/java.svg" width="27" /> `Java` 
<img src="./images/logos/rest-assured.svg" width="33" /> `REST Assured` 
<img src="./images/logos/junit.svg" width="33" /> `JUnit` 
<img src="./images/logos/selenide.svg" width="42" /> `Selenide` - написание исходного кода;<br/>
<img src="./images/logos/gradle.svg" width="35" height="35" /> `Gradle` - сборка проекта;<br/>
<img src="./images/logos/jenkins.svg" width="35" height="35" /> `Jenkins` - конфигурация и запуск сборок;<br/>
<img src="./images/logos/postman.svg" width="28" height="28" /> `Postman` - отправка тестовых запросов;<br/>
<img src="./images/logos/allure.svg" width="28" /> `Allure Report` - формирование отчетности по итогам тестирования;<br/>
<img src="./images/logos/allure_testops.svg" width="24.7" /> `Allure TestOps` - создание тестовой документации;<br/>
<img src="./images/logos/jira.svg" width="30" /> `Jira` - создание задачи в таск-трекере.<br/>


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
![](./images/screenshots/allure_1.png)


![](./images/screenshots/allure_2.png)


![](./images/screenshots/allure_3.png)

---
### Тестовая документация, сгенерированная в [Allure TestOps](https://allure.autotests.cloud/project/815/dashboards) по итогам прохождения тестов
![](./images/screenshots/testops_1.png)


![](./images/screenshots/testops_2.png)

---
### [Задача](https://jira.autotests.cloud/browse/HOMEWORK-295) в таск-трекере Jira с данными, экспортированными из Allure TestOps
![](./images/screenshots/jira.png)

---
### Оповещения о статусе сборки в мессенджерах
<img src="./images/logos/slack.svg" width="80" /><br/>
![](./images/screenshots/slack_notice.png)
---
<img src="./images/logos/telegram.svg" width="100" /><br/>
![](./images/screenshots/telegram_notice.png)

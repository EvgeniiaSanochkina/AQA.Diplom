# Дипломный проект по профессии «Тестировщик»

Дипломный проект — автоматизация тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.

## Подготовительный этап
1. Клонировать репозиторий с дипломным заданием с GitHub;
2. Запустить IntelliJ IDEA, открыть проект;
3. Запустить Docker Desktop. 

## Запуск приложениям с подключением к SQL, запуск тестов и выгрузка отчета
1. Ввести в терминале IntelliJ IDEA команду docker-compose up;
2. Ввести в новом терминале IntelliJ IDEA команду java "-Dspring.datasource.url=jdbc:mysql://localhost:3306/app"  "-Dspring.datasource.username=app" "-Dspring.datasource.password=pass" -jar aqa-shop.jar
3. Ввести в новом терминале команду ./gradlew clean test "-Durl=jdbc:mysql://localhost:3306/app" "-Duser=app" "-Dpassword=pass";
4. Ввести в новом терминале команду ./gradlew allureServe;
5. Нажать в каждом из терминалов сочетание клавиш ctrl + с для остановки приложения;
6. Ввести Y в терминале с командой ./gradlew allureServe.

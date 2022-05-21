# Проект по автоматизации тестирования сайта ПРИОРБАНК
<a target="_blank" href="https://www.priorbank.by/">Сайт Приорбанк</a> и <a target="_blank" href="https://www.prior.by/web/">интернет-банкинг Приорбанк</a>

<a href="https://www.jetbrains.com/idea/"><img src="images\priorbank.jpg" height="80" alt="Priorbank"/></a>

## Содержание:
- [Технологии и инструменты](#alien-технологии-и-инструменты)
- [Реализованные проверки](#fairy-Реализованные-проверки)
- [Запуск из терминала](#alien-Запуск-тестов-из-терминала)
- [Запуск тестов в Jenkins](#fairy-Запуск-тестов-в-Jenkins)
- [Allure отчет](#alien-Allure-отчет)
- [Видео прохождения тестов](#fairy-Видео-прохождения-тестов)
- [Отчет в Telegram](#alien-Отчет-в-Telegram)

## :alien: Технологии и инструменты

<p align="center">
<a href="https://www.jetbrains.com/idea/"><img src="images\logo\Idea.svg" width="50" height="50"  alt="IDEA"/></a>
<a href="https://rest-assured.io/"><img src="images\logo\RestAssured.svg" width="50" height="50"  alt="RestAssured"/></a>
<a href="https://www.java.com/"><img src="images\logo\Java.svg" width="50" height="50"  alt="Java"/></a>
<a href="https://github.com/"><img src="images\logo\GitHub.svg" width="50" height="50"  alt="Github"/></a>
<a href="https://junit.org/junit5/"><img src="images\logo\Junit5.svg" width="50" height="50"  alt="JUnit 5"/></a>
<a href="https://gradle.org/"><img src="images\logo\Gradle.svg" width="50" height="50"  alt="Gradle"/></a>
<a href="https://selenide.org/"><img src="images\logo\Selenide.svg" width="50" height="50"  alt="Selenide"/></a>
<a href="https://aerokube.com/selenoid/"><img src="images\logo\Selenoid.svg" width="50" height="50"  alt="Selenoid"/></a>
<a href="https://github.com/allure-framework/allure2"><img src="images\logo\Allure.svg" width="50" height="50"  alt="Allure"/></a>
<a href="https://www.jenkins.io/"><img src="images\logo\Jenkins.svg" width="50" height="50"  alt="Jenkins"/></a>
<a href="https://web.telegram.org/"><img width="50" height="50"  alt="Telegram" src="images\logo\Telegram.svg"></a>
</p>

Перечень технологий и инструментом, использованных при реализации этого проекта:
- Автотесты написаны на языке `Java`
- Для UI-тестов используется тестовый фреймворк `Selenide`
- Для сборки проекта используется `Gradle`
- Библиотека для модульного тестирования: `JUnit 5`
- Для API-тестов используется библиотека `Rest-assured`
- `Jenkins` выполняет удаленный запуск тестов в визуальном-онлайн интерфейсе. Установки дополнительных приложений на компьютер пользователя не требуется.
- `Selenoid` демонстрирует пример запуска браузеров в контейнерах Docker (и записывает видео).
- `Allure Report` формирует наглядный графический отчет о результатах  запуска тестов.
- После завершения прогона тестов, специальный `Telegram Bot` отправляются в `Telegram` краткий вариант Allure Report

## :fairy: Реализованные проверки
UI тесты:
- [✓] Проверка отображения формы входа
- [✓] Проверка перехода в демо-версию АЧ
- [✓] Отображение элементов на вкладке Вопросы и ответы
- [✓] Отображение иконок соцсетей
- [✓] Отображение курсов валют за наличные
- [✓] Создание депозита

API тесты:
- [✓] Отображение главной страницы оценки сайта
- [✓] Отображение первой формы оценки
- [✓] Отображение второй формы оценки
- [✓] Отображение третьей формы оценки

## :alien: Запуск тестов из терминала
###### Локальный запуск:
```
gradle clean test
```
###### Удаленный запуск:
```
clean
test
-Duser=${USER}
-Dpassword=${PASSWORD}
-DremoteBrowser=${REMOTE_BROWSER}
-Dbrowser=${BROWSER}
-Dsize=${BROUSERSIZE}
-Dversion=${VERSION}
```

## :fairy: Запуск тестов в Jenkins
Шаги:
1. Зарегистрированным пользователем перейти на страницу сборки проекта по ссылке: <a target="_blank" href="https://jenkins.autotests.cloud/job/011-alexiaair-hw15-rb/">Jenkins Alexiaair</a>
2. Перечисленные ниже параметры можно менять в графическом интерфейсе.
3. Запустить выполнение тестов кнопкой "Собрать" (внизу страницы)

Основные параметры сборки:
- `BROWSER` – браузер, в котором будут выполняться тесты (по умолчанию - Chrome).
- `BROWSER_VERSION` версия браузера, в которой будут выполняться тесты (по умолчанию - 91.0).
- `BROWSER_SIZE` – размер окна браузера, в котором будут выполняться тесты (по умолчанию - 1920x1080).
- `REMOTE_BROWSER` - адрес удаленного сервера (Selenoid), на котором будут запускаться тесты.

Дополнительные параметры сборки, задействованные для выгрузки краткого отчета в Telegram:
- `PROJECT_NAME`  название проекта
- `ENVIRONMENT` - тестовый стенд (prod, demo, stage...), на котором запускались тесты. <i>Можно выставить значение из доступных в выпадающем списке</i>.
- `COMMENT` - ваш текстовой комментарий

## :alien: Allure отчет
После того как тесты завершились, можно получить визуальный Allure отчет.
<br>Способ 1: Сформировать отчет средствами IJ IDEA (Allure Serve)
<br>Способ 2:
<br>1. Выполнить сборку в Jenkins
<br>2. Убедиться, что в блоке История сборок (напротив номера #) появился желтый значок Allure Report
<br>3. Кликнуть по значку Allure Report
<br>Ожидаемый результат: Откроется страница с готовым Allure Report

###### Главный экран отчета (Owerwiev)
<p align="center">
<img title="Allure Graphics" src="images\allure_mian.png">
</p>

###### Страница с проведенными тестами (Suites)
<p align="center">
<img title="Allure Graphics" src="images\allure_tests.png">
</p>

## :fairy: Видео прохождения тестов
К каждому тесту (в отчете) прилагается автоматически сгенерированное Selenoid видео. Пример:
<p align="center">
  <img title="Selenoid Video" src="images\video_test.gif" alt="video">
</p>

## :alien: Отчет в Telegram
После завершения сборки специальный Telegram-бот отправляет сообщение с отчетом о прогоне тестов.
Чтобы видеть сообщения от бота, вступите (временно) в телеграм-группу `TestNotification_alexiaair`

<p align="center">
<img title="Telegram Bot" src="images\telegram.png">
</p>
# Java Rest API для картотеки супергероев Marvel.

Реализованы методы (пример https://developer.marvel.com):
=

1)	Базовые API методы 
+	GET /v1/public/characters 
+	GET /v1/public/characters/{characterId}
+	GET /v1/public/characters/{characterId}/comics
+	GET /v1/public/comics
+	GET /v1/public/comics/{comicId}
+	GET /v1/public/comics/{comicId}/characters
  
2)	POST/PUT методы для наполнения базы/файлов c комиксами и героями
   
Выполнены следущие требования:
=

1)	Постраничная загрузка, сортировка, фильтрация для запросов, возвращающих списки
2)	Работа с изображениями (загрузка, отображение)
3)	Обработанны ошибки в ответе:
+ 404 если не найдена сущность
+ 400 если запрос не прошёл валидацию
4)	Документирование API (например, через Swagger)
1)	Формат запросов и ответов упрощён по сравнению с Marvel (оставлены только базовые поля)

 Реализация проверки токена безопасности отсутствует
   
Инструментарий:
=
1.	Java openjdk-19 (Oracle OpenJDK version 19.0.2)
2.	Сборка – Maven
3.	Spring Boot version 3.0.0
5.	База данных. PostgreSQL
    
Для запуска необходимо создать БД и указать логин/пароль
=
+ spring.datasource.url=jdbc:postgresql://localhost:5432/**your_name_db**
+ spring.datasource.username=**your_login_db**
+ spring.datasource.password=**your_password_db**

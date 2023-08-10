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
   
Обязательные требования:
=

1)	Постраничная загрузка, сортировка, фильтрация для запросов, возвращающих списки
2)	Работа с изображениями (загрузка, отображение)
3)	Адекватные ошибки в ответе. Например:
a.	404 если не найден персонаж
b.	400 если запрос не прошёл валидацию
4)	Документирование API (например, через Swagger)
Допущения:
1)	Формат запросов и ответов упрощён по сравнению с Marvel (оставлены только базовые поля)
2)	Реализация проверки токена безопасности отсутствует
   
Инструментарий:
=
1.	Java openjdk-19 (Oracle OpenJDK version 19.0.2)
2.	Сборка – Maven
3.	Spring Boot version
5.	(Желательно) использование базы данных. Например Mongo
6.	(Совсем здорово) использование docker

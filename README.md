###LESSON_1 Домашнее задание:

  +  Сделать много картинок для одного продукта;
  + Оформить страницу продукта;
  +  Обработать исключение 500 Internal Service Error;
  + Сделать утилиту для проверки корректности UUID;
  +  Выводить доступные / недоступные продукты;
    Сделать фильтрацию продуктов по их доступности;
    (Необязательно) Сделать обновление категорий без перезагрузки страницы.

###LESSON_2 Домашнее задание:

+ Переделать локацию чтения файлов изображений. Пояснение - в прошлом уроке, мы рассмотрели возможность хранения файлов изображений в папке resources/static/images Для теста это хорошо, но в целом - так делать нельзя, поскольку папка resources это статические ресурсы. Поэтому нам необходимо поменять локацию загрузки файлов. Например C:\uploads\images. Подсказка - небходимо будет поработать с классом org.springframework.core.io.UrlResource (как вариант);

+ На странице http://localhost:8080/myshop/admin сделать возможность загрузки изображений (можно по одному изображению) и прикреплять их к определённому продукту; Опять же - загружать файлы мы будем во внешнюю директорию ( C:\uploads\images ), потому что загружать таким способом в папку статических ресурсов не получится. Подсказка: Поработайте с классом org.springframework.web.multipart.MultipartFile;

+ Добавить поддержку JPG формата.

+ Обработать ошибку, если произошла попытка загрузить файл некорректного формата.

###LESSON_3 Домашнее задание:

+ Модерация комментариев . Сделать так, что если комментарий к товару, оставляет обычный покупатель (роль customer), то этот комментарий виден вначале только пользователю под ролью ADMIN; администратор естественно может одобрить этот комментарий и тогда он будет виден всем пользователям.
+ Сделать возможность прикреплять к отзыву 1 картинку.
- (Необязательно) с помощью аспекта залогировать методы класса CaptchaGenerator;

###LESSON_4 Домашнее задание:

+ Скачать, собрать и запустить SOAP веб-сервис : https://github.com/serpentcross/soap-producer-example
+ Изучить содержимое проекта;
+ Добавить своё поле в XSD схему (например : price) следовательно - вам придётся перегенерить проект веб-сервиса и модифицировать WSDL в основном проекте;

###LESSON_5 Домашнее задание:

  + Сделать страницу с выбором способа оплаты и подтверждением заказа (если кто-то ещё не сделал), по аналогии, что было на демонстрации.
  + Задокументировать все методы из ваших REST-сервисов при помощи Swagger;
  + Написать подобный gradle скрипт для jib, чтобы ваше приложение запускалось в Docker.

###LESSON_6 Домашнее задание:
   + Переписать метод findAll() в контроллере ShopController используя entityManager сделать так, чтобы можно было возвращать продукты отфильтрованные по категории, и например по какому-нибудь другому полю. К примеру - страна происхождения, Russia, USA и т.д.
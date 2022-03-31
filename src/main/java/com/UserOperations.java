package com;

import com.model.Tokens;
import com.model.UserRegisterResponse;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class UserOperations {

    public static final String EMAIL_POSTFIX = "@yandex.ru";

    /*
     метод регистрации нового пользователя
     возвращает мапу с данными: имя, пароль, имэйл
     если регистрация не удалась, возвращает пустую мапу
     */
    public Map<String, String> register() {

        // с помощью библиотеки RandomStringUtils генерируем имэйл
        // метод randomAlphabetic генерирует строку, состоящую только из букв, в качестве параметра передаём длину строки
        String email = RandomStringUtils.randomAlphabetic(10) + EMAIL_POSTFIX;
        // с помощью библиотеки RandomStringUtils генерируем пароль
        String password = RandomStringUtils.randomAlphabetic(10);
        // с помощью библиотеки RandomStringUtils генерируем имя пользователя
        String name = RandomStringUtils.randomAlphabetic(10);

        // создаём и заполняем мапу для передачи трех параметров в тело запроса
        Map<String, String> inputDataMap = new HashMap<>();
        inputDataMap.put("email", email);
        inputDataMap.put("password", password);
        inputDataMap.put("name", name);

        // отправляем запрос на регистрацию пользователя и десериализуем ответ в переменную response
        UserRegisterResponse response = given()
                .spec(Base.getBaseSpec())
                .and()
                .body(inputDataMap)
                .when()
                .post("auth/register")
                .body()
                .as(UserRegisterResponse.class);

        // возвращаем мапу с данными
        Map<String, String> responseData = new HashMap<>();
        if (response != null) {
            responseData.put("email", response.getUser().getEmail());
            responseData.put("name", response.getUser().getName());
            responseData.put("password", password);

            // токен, нужный для удаления пользователя, кладем в статическое поле класса с токенами
            // убираем слово Bearer в начале токена
            // так же запоминаем refreshToken
            Tokens.setAccessToken(response.getAccessToken().substring(7));
            Tokens.setRefreshToken(response.getRefreshToken());
        }
        return responseData;
    }

    /*
     метод удаления пользователя по токену, возвращенному после создания
     пользователя. Удаляем только в случае, если token заполнен.
     */
    public void delete() {
        if (Tokens.getAccessToken() == null) {
            return;
        }
        given()
                .spec(Base.getBaseSpec())
                .auth().oauth2(Tokens.getAccessToken())
                .when()
                .delete("auth/user")
                .then()
                .statusCode(202);
    }

    @Step("Авторизация пользователя на бэке")
    public static String authorizationBack(String email, String password) {
        Map<String, String> request = new HashMap<>();
        request.put("email", email);
        request.put("password", password);

        Response response = given()
                .spec(Base.getBaseSpec())
                .and()
                .log().body()
                .body(request)
                .when()
                .post("auth/login");

        response.then()
                .statusCode(200);

        return response.then().extract().path("accessToken");
    }

    @Step("Удаление пользователя")
    public static void delete(String token) {
        given()
                .spec(Base.getBaseSpec())
                .and()
                .header("Authorization", token)
                .when()
                .delete("auth/user")
                .then()
                .log().body()
                .statusCode(202);
    }

}

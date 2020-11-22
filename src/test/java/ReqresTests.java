import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;

public class ReqresTests {

  @Test
  public void postLoguinTest() {

    RestAssured
        .given()
        .log().all()
        .contentType(ContentType.JSON)
        .body("{\n"
            + "    \"email\": \"eve.holt@reqres.in\",\n"
            + "    \"password\": \"cityslicka\"\n"
            + "}")
        .post("https://reqres.in/api/login")
        .then()
        .log().all()
        .statusCode(200)
        .body("token", notNullValue())
        .body("token", equalTo("QpwL5tke4Pnpja7X4"));

  }

  @Test
  public void GetSingleUserTest() {
    RestAssured
        .given()
        .log().all()
        .get("https://reqres.in/api/users/2")
        .then()
        .log().all()
        .statusCode(200)
        .body("data.id", equalTo(2))
        .body("data.first_name", equalToIgnoringCase("janet"))
        .body("support.text", equalToIgnoringCase(
            "To keep ReqRes free, contributions towards server costs are appreciated!"));
  }

  @Test
  public void postCreateTest() {

    RestAssured
        .given()
        .log().all()
        .contentType(ContentType.JSON)
        .body("{\n"
            + "    \"name\": \"Carlos\",\n"
            + "    \"job\": \"Automatizador\"\n"
            + "}")
        .post("https://reqres.in/api/users")
        .then()
        .log().all()
        .statusCode(201)
        .body("name", equalTo("Carlos"))
        .body("job", equalTo("Automatizador"));

  }
}

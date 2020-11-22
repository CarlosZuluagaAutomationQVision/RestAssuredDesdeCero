import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;

public class ReqresTests {

  @Before
  public void setup() {
    RestAssured.baseURI = "https://reqres.in";
    RestAssured.basePath = "/api";
    RestAssured
        .filters(new RequestLoggingFilter(), new ResponseLoggingFilter(), new ErrorLoggingFilter());
    RestAssured.requestSpecification = new RequestSpecBuilder().setContentType(ContentType.JSON)
        .build();
  }

  @Test
  public void postLoguinTest() {
    given()
        .body("{\n"
            + "    \"email\": \"eve.holt@reqres.in\",\n"
            + "    \"password\": \"cityslicka\"\n"
            + "}")
        .post("login")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .body("token", notNullValue())
        .body("token", equalTo("QpwL5tke4Pnpja7X4"));

  }

  @Test
  public void GetSingleUserTest() {
    given()
        .get("users/2")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .body("data.id", equalTo(2))
        .body("data.first_name", equalToIgnoringCase("janet"))
        .body("support.text", equalToIgnoringCase(
            "To keep ReqRes free, contributions towards server costs are appreciated!"));
  }

  @Test
  public void postCreateTest() {
    given()
        .body("{\n"
            + "    \"name\": \"Carlos\",\n"
            + "    \"job\": \"Automatizador\"\n"
            + "}")
        .post("users")
        .then()
        .statusCode(HttpStatus.SC_CREATED)
        .body("name", equalTo("Carlos"))
        .body("job", equalTo("Automatizador"));
  }

  @Test
  public void deleteUserTest() {
    given()
        .delete("users/2")
        .then()
        .statusCode(HttpStatus.SC_NO_CONTENT);
  }

  @Test
  public void patchUserTest() {
    String nombreActualizado = given()
        .when()
        .body("{\n"
            + "    \"name\": \"Carlos\",\n"
            + "    \"job\": \"Automatizador\"\n"
            + "}")
        .patch("users/2")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract().jsonPath().getString("name");

    assertThat(nombreActualizado, equalTo("Carlos"));
  }

  @Test
  public void putUserTest() {
    String trabajoActualizado = given()
        .when()
        .body("{\n"
            + "    \"name\": \"Carlos\",\n"
            + "    \"job\": \"Automatizador\"\n"
            + "}")
        .put("users/2")
        .then()
        .statusCode(HttpStatus.SC_OK)
        .extract().jsonPath().getString("job");

    assertThat(trabajoActualizado, equalTo("Automatizador"));
  }

}

package Tasks;

import static Enum.EnumVariablesSesion.RESPONSE_REGISTRO_USUARIO;
import static io.restassured.RestAssured.given;
import static net.serenitybdd.screenplay.Tasks.instrumented;

import Models.post.RequestRegisterUser;
import Models.post.ResponseRegisterUser;
import io.restassured.http.ContentType;
import java.util.Map;
import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

@AllArgsConstructor
public class ConsumirServicioPostRegister implements Task {

  private final Map<String, String> mapDatosUsuario;

  public static ConsumirServicioPostRegister conLosDatos(
      Map<String, String> mapDatosUsuario) {
    return instrumented(ConsumirServicioPostRegister.class, mapDatosUsuario);
  }

  @Override
  public <T extends Actor> void performAs(T actor) {
    RequestRegisterUser requestRegisterUser = new RequestRegisterUser();
    requestRegisterUser.setEmail(mapDatosUsuario.get("Email"));
    requestRegisterUser.setPassword(mapDatosUsuario.get("Password"));

    actor.remember(RESPONSE_REGISTRO_USUARIO.getVariableSesion(),
        given()
            .when()
            .body(requestRegisterUser)
            .log()
            .all()
            .contentType(ContentType.JSON)
            .post("https://reqres.in/api/register")
            .then()
            .log()
            .all()
            .extract()
            .response()
            .as(ResponseRegisterUser.class));

  }
}


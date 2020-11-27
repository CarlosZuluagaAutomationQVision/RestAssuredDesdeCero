package questions;

import static Enum.EnumVariablesSesion.RESPONSE_REGISTRO_USUARIO;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import Models.post.ResponseRegisterUser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidarToken implements Question<String> {

  public static ValidarToken delRespose() {
    return new ValidarToken();
  }

  @Override
  public String answeredBy(Actor actor) {
    ResponseRegisterUser responseRegisterUser = theActorInTheSpotlight().recall(RESPONSE_REGISTRO_USUARIO.getVariableSesion());
    return responseRegisterUser.getToken();
  }
}

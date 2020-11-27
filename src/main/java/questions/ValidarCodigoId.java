package questions;

import static Enum.EnumVariablesSesion.RESPONSE_REGISTRO_USUARIO;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import Models.post.ResponseRegisterUser;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidarCodigoId implements Question<String> {


  public static ValidarCodigoId delRespose() {
    return new ValidarCodigoId();
  }


  @Override
  public String answeredBy(Actor actor) {
    ResponseRegisterUser responseRegisterUser = theActorInTheSpotlight().recall(RESPONSE_REGISTRO_USUARIO.getVariableSesion());
    return (String.valueOf(responseRegisterUser.getId()));
  }

}

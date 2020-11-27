package stepdefinitions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import Tasks.ConsumirServicioPostRegister;
import io.cucumber.java.Before;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import java.util.Map;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;
import questions.ValidarCodigoId;
import questions.ValidarToken;

public class PracticaServiciosStepDefinitions {

  @Before
  public void configurarActor() {
    OnStage.setTheStage(new OnlineCast());
    theActorCalled("Carlos");
  }

  @Cuando("Carlos hace el registro de un usuario con las características:")
  public void registrarUsuario(Map<String, String> mapDatosUsuario) {
    theActorInTheSpotlight().attemptsTo(ConsumirServicioPostRegister.conLosDatos(mapDatosUsuario));
  }


  @Entonces("el registro se realiza de manera correcta con un id {string} y con token {string}")
  public void validarRegistro(String id, String token) {
    theActorInTheSpotlight().should(seeThat(ValidarCodigoId.delRespose(), Matchers.equalTo(id)));
    theActorInTheSpotlight().should(seeThat(ValidarToken.delRespose(), Matchers.equalTo(token)));
  }
}

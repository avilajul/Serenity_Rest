package co.com.empresa.certificacion.api.questions;

import co.com.empresa.certificacion.api.exceptions.ErrorExceptions;
import co.com.empresa.certificacion.api.models.ResponseBody;
import io.restassured.path.json.JsonPath;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.empresa.certificacion.api.utils.constants.StatusCodes.STATUS_CODE_200;
import static net.serenitybdd.rest.SerenityRest.lastResponse;

public class ResponseUpdatedUserInformation implements Question<Boolean> {
    public static ResponseUpdatedUserInformation updateSuccessful(){
        return new ResponseUpdatedUserInformation();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean expectedResult = false;
        ResponseBody responseBody;

        if (lastResponse().statusCode() != STATUS_CODE_200){
            throw new ErrorExceptions("ERROR: El servicio respondió con código " + lastResponse().statusCode());
        }else {
            responseBody = lastResponse().jsonPath().getObject("", ResponseBody.class);
        }

        if (responseBody.getUpdatedAt().isEmpty()){
            throw new ErrorExceptions("ERROR: El servicio no actualizó la información del usuario");
        }else {
            expectedResult = true;
        }
        return expectedResult;
    }
}

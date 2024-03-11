package connectors;

import core.EnvSerenity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.HttpStatus;

public class TypiCodeConnector {

    private RequestSpecification baseRequest() {
        return SerenityRest.with()
                .accept(ContentType.JSON)
                .baseUri(EnvSerenity.baseURI);
    }

    public Response getToDos() {
        return baseRequest()
                .get("/todos")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
    }

    public Response getUsers() {
        return baseRequest()
                .get("/users")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
    }
}

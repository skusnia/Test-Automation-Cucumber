package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class ApiStep {
    private static final String BASE_URL = "https://api.punkapi.com";
    private static String jsonString;
    @Given("an API")
    public void anAPI() {
    }

    @And("Expect value will be {int}")
    public void expectValueWillBe(int arg0) {
        RestAssured.baseURI = "https://api.punkapi.com";
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/v2/beers?page=2&per_page="+arg0);
        jsonString = response.asString();
        List<Map<String, String>> listOfBeer = JsonPath.from(jsonString).get();
        Assert.assertEquals(arg0, listOfBeer.size());
    }
}

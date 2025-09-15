package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Assert;
import utils.JsonUtils;

import static org.hamcrest.Matchers.*;

import static org.hamcrest.Matchers.hasKey;

public class ApiSteps {

    private Response response;

    @Given("I send a GET request to {string}")
    public void i_send_a_get_request_to(String url) {
        response = RestAssured
                .given()
                .when()
                .get(url);
    }

    @Then("the response should contain the fields {string}, {string}, and all {string}")
    public void the_response_should_contain_the_fields_and_all(String pathKey, String ipKey, String headersKey) {
        response.then().statusCode(200);
        response.then().body("$", hasKey(pathKey));
        response.then().body("$", hasKey(ipKey));
        response.then().body("$", hasKey(headersKey));
    }

    @Given("I send a POST request to {string} with valid order payload")
    public void i_send_a_post_request_to_with_payload(String url) {
        String filePath = "src/test/resources/payloads/orderPayload.json";
        String jsonPayload = JsonUtils.readJsonFromFile(filePath);

        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post(url);
    }

    @Then("the response should contain correct customer, payment, and product details")
    public void validate_post_response_fields() {
        response.then().statusCode(200);

        // Optional debug
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());

        // ✅ Validate customer
        response.then().body("parsedBody.customer.name", equalTo("Jane Smith"));
        response.then().body("parsedBody.customer.email", equalTo("janesmith@example.com"));
        response.then().body("parsedBody.customer.phone", equalTo("1-987-654-3210"));

        // ✅ Validate payment
        response.then().body("parsedBody.payment.method", equalTo("credit_card"));
        response.then().body("parsedBody.payment.transaction_id", equalTo("txn_67890"));
        response.then().body("parsedBody.payment.amount", equalTo(111.97f)); // float
        response.then().body("parsedBody.payment.currency", equalTo("USD"));

        // ✅ Validate items
        response.then().body("parsedBody.items[0].product_id", equalTo("A101"));
        response.then().body("parsedBody.items[0].name", equalTo("Wireless Headphones"));

        response.then().body("parsedBody.items[1].product_id", equalTo("B202"));
        response.then().body("parsedBody.items[1].name", equalTo("Smartphone Case"));
    }

}

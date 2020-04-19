package api.controller;

import api.support.PropertyUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;

public class Executor {

    Response response;

    private static RequestSpecification requestSpecification;

    public static PropertyUtil propertyUtils = new PropertyUtil();

    private RequestSpecification getAuthenticatedRequestHandle() {
         requestSpecification = RestAssured.given().auth()
                 .oauth2("xoxb-1065164580470-1065172616630-zhgnsyfTzvDinwt5VzaNexyg")
                .contentType(propertyUtils.getContentType());
                //.baseUri(propertyUtils.getBaseUrl());
        return requestSpecification;
    }

    public Response makeGetRequest(String endPoint) {
        requestSpecification = getAuthenticatedRequestHandle();
        return requestSpecification.get(endPoint);
    }


    public Response get(String uRI) {

        return get(null, uRI);
    }

    public Response get(HashMap<String, String> header, String uRI)  {
        RestAssured.useRelaxedHTTPSValidation();
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification = getAuthenticatedRequestHandle();
        requestSpecification.contentType(ContentType.JSON);
        if (header != null)
            requestSpecification.headers(header);
        response = requestSpecification.get(uRI);
        return response;
    }

    public Response makePostRequest(String endPoint, String payLoad) {
        requestSpecification = getAuthenticatedRequestHandle();

        requestSpecification.body(payLoad);

        return requestSpecification.post(endPoint);
    }

    public Response post(String uRI, String bodyJSON) {
        return post(null, uRI, bodyJSON);
    }

    public Response post(HashMap<String, String> header, String uRI, String bodyJSON) {
        RestAssured.useRelaxedHTTPSValidation();

        requestSpecification = getAuthenticatedRequestHandle().given().body(bodyJSON    );

        //RequestSpecification requestSpecification = RestAssured.given().body(bodyJSON);

        if (header != null)
            requestSpecification.headers(header);
        response = requestSpecification.post(uRI);
        return response;
    }
}

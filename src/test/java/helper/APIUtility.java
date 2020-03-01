package helper;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import java.io.*;
import java.util.Properties;

public class APIUtility extends TestBase {

    public static JsonPath jsonPathEvaluator;

//This method to send any request type(get, post or put) in flight
    public static String sendRequestFlight(String requestUrl, String Body, String method)throws IOException {
        Response response = null;
        Properties prop = new Properties();
        String propFileName = null;
        if(requestUrl.contains("nz")){
            propFileName = "NzConfigHeader.properties";
        }
        else if(requestUrl.contains("www")){
            propFileName = "WwConfigHeader.properties";
        }
        else if(requestUrl.contains("au")){
            propFileName = "AuConfigHeader.properties";
        }
        else if(requestUrl.contains("my")){
            propFileName = "MyConfigHeader.properties";
        }
        InputStream inputStream;
        inputStream = new FileInputStream("src/test/java/Config/"+propFileName+"");
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
        }

        String xStoreId = prop.getProperty("x-store-id").trim();
        String authorization = prop.getProperty("authorization").trim();
        String xStoreUser = prop.getProperty("x-store-user").trim();
        RestAssured.baseURI = requestUrl;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type","application/json" );
        httpRequest.header("x-store-id", "'"+xStoreId+"'");
        httpRequest.header("authorization", ""+authorization+"");
        httpRequest.header("x-store-user", ""+xStoreUser+"");
        if(method.equalsIgnoreCase("post")){
            response = httpRequest.body(Body).post(RestAssured.baseURI);
        }
        else if(method.equalsIgnoreCase("get")){
            response = httpRequest.get(RestAssured.baseURI);
        }
        else if(method.equalsIgnoreCase("put")){
            httpRequest.body(Body);
            httpRequest.put(requestUrl);
            response = httpRequest.put();
        }
        ResponseBody body = response.getBody();
        jsonPathEvaluator = response.jsonPath();
        String bodyStringValue = body.asString();
        return bodyStringValue;
    }

//This method to send any request type(get, post or put) in hub
    public static String sendRequestHub(String requestUrl, String Body, String method)throws IOException {
        Response response = null;
        Properties prop = new Properties();
        InputStream inputStream;
        inputStream = new FileInputStream("src/test/java/Config/HubConfig/HubConfigHeader.properties");
        if (inputStream != null) {
            prop.load(inputStream);
        } else {
            throw new FileNotFoundException("property file HubConfigHeader.properties not found in the classpath");
        }

        String xUserToken = prop.getProperty("x-user-token").trim();
        String authorization = prop.getProperty("authorization").trim();
        RestAssured.baseURI = requestUrl;
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");
        httpRequest.header("x-user-token", "" + xUserToken + "");
        httpRequest.header("authorization", "" + authorization + "");
        if(method.equalsIgnoreCase("post")){
            response = httpRequest.body(Body).post(RestAssured.baseURI);
        }
        else if(method.equalsIgnoreCase("get")){
            response = httpRequest.get(RestAssured.baseURI);
        }
        else if(method.equalsIgnoreCase("put")){
            httpRequest.body(Body);
            httpRequest.put(requestUrl);
            response = httpRequest.put();
        }
        ResponseBody body = response.getBody();
        jsonPathEvaluator = response.jsonPath();
        String bodyStringValue = body.asString();
        return bodyStringValue;
    }
}


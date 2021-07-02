package PlaceAPI;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.Addbodypayload;


public class validatationAddPlaceAPI {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		
		String res  = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Addbodypayload.addbody())
		
		.when().post("/maps/api/place/add/json")
		
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
		
		.extract().response().asString();
		
		// TO print the response we are using System.out.println or log().all() method under extract method
		System.out.println(res);
		
		
		
		

	}

}

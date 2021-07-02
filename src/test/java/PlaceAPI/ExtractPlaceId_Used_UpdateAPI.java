package PlaceAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.Addbodypayload;
import files.ReusableMethod;


public class ExtractPlaceId_Used_UpdateAPI {
	
	public static void main(String[] args) {
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		//*****************************Add place API*****************
		String res= given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Addbodypayload.addbody())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope",equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		System.out.println(res);
		
		
		/*
		 * JsonPath js=new JsonPath(res); String place_id=js.getString(place_id);
		 */
		
		
		
		JsonPath js=ReusableMethod.jsonConverter(res);//json is used to paurse sting to json
		String placeid=js.get("place_id");
		System.out.println(placeid);
		
		
		//********************Extract place id and use in put method
		
		String newaddress="70 Summer walk, USA";
		
		given().log().all().queryParam("key", "qaclick123")
		.body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+newaddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
		
		
		//********************Get address************************
		
		String getres  =given().log().all().queryParam("key", "qaclick123").queryParam("place_id", ""+placeid+"")
				
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath js1=ReusableMethod.jsonConverter(getres);
		String actualaddress=js1.getString("address");
		
		//check validation using TestNg
		
		Assert.assertEquals( actualaddress,newaddress);
	
		
		
		
		


	}

}

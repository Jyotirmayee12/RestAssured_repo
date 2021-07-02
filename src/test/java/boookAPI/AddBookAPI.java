package boookAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import files.Addbodypayload;
import files.ReusableMethod;

public class AddBookAPI {

	
	@Test

	public void Addlibrarybook() {
		
		
		
		RestAssured.baseURI="http://216.10.245.166";
		
		//*******************************Add book details through isbn and aisle*****************
		
		String res=given().log().all().header("Content-Type","application/json").body(Addbodypayload.BookDetails())
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString(); //return String
		
		JsonPath js2=ReusableMethod.jsonConverter(res);
		String id=js2.get("ID");
		System.out.println(id);
	}
		

}
	

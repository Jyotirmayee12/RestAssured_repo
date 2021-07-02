package boookAPI;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import files.Addbodypayload;
import io.restassured.RestAssured;

public class AddLibraryBookAPI {
	
	
	//**********************Build dynamic payload with external data input********************
	
	@Test
	public void Add_Dynamic_LibraryBook()

	{
	
		RestAssured.baseURI="http://216.10.245.166";
		
		given().log().all().header("Content-Type","application/json").body(Addbodypayload.AddLibrarybook("micro controller","book89","8856","harry porter"))
		.when().post("/Library/Addbook.php")
		.then().log().all().statusCode(200);
		
		
		
		
		

	}



}

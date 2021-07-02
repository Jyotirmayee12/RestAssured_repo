package boookAPI;

import static io.restassured.RestAssured.given;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;




import io.restassured.RestAssured;

public class SendingStaticJsonFile {


	@Test
	public void Add_Dynamic_LibraryBook() throws Exception

	{

		RestAssured.baseURI="http://216.10.245.166";
		
		
		//get the File path
		Path filePath  = Paths.get("F:\\Eclipse WorkSpace\\RestAssuredProject_HomePratice\\Json\\staticJsonFile.json");
		
		//read/convert the Data in Byte format
		byte[] bytes =Files.readAllBytes(filePath);
		
		//Convert byte to string because body read the data in string format
		String s = new String(bytes);

		given().log().all().header("Content-Type","application/json").body(s)
		.when().post("/Library/Addbook.php")
		.then().log().all().statusCode(200);



	}
}


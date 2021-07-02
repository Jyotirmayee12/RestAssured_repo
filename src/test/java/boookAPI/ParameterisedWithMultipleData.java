package boookAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Addbodypayload;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class ParameterisedWithMultipleData {
	
	
	@Test(dataProvider="booksdata1")
	public void MultipleAddBook(String bookname, String isbncode,String aislecode, String author)
	{
		
		RestAssured.baseURI="http://216.10.245.166";
		
		given().log().all().header("Content-Type","application/json").body(Addbodypayload.AddLibrarybook(bookname, isbncode, aislecode, author))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200);
	}
	
	
	
	
	@DataProvider(name="booksdata1")
	public Object[][] parameterisedmethod() {
		
	Object[][] obj1=new Object[][] {{"2State","book@2","2233","chetan bhagat"},{"2State-Part2","book@3","2234","chetan bhagat"},{"2State-Part3","book@4","2235","chetan bhagat"}};
		return obj1;
		
		
		
		
		
		
		
		
		
		
	}
	
	

}

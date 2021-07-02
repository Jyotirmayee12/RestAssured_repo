package JiraAPI;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class logIn_jiraAPI_CookiesAuthentication {

	public static void main(String[] args) {

		RestAssured.baseURI="http://localhost:8080";


//********************************LogIn API*********************
		/*
		 * String res=given().body("{\r\n" + "    \"username\": \"031jyotibehera\",\r\n"
		 * + "    \"password\": \"Jyoti@456\"\r\n" +
		 * "}").header("content-type","application/json").when().post(
		 * "/rest/auth/1/session") .then().assertThat().statusCode(200)
		 * .extract().response().asString();
		 * 
		 * JsonPath js1= new JsonPath(res); String session_id=
		 * js1.getString("session.value"); System.out.println(session_id);
		 */
  //**********************************Other Way to get cookies based Authentication****************
		
		
		System.out.println("**************Login in Jira**************************");
		
			SessionFilter session= new SessionFilter();
			
			String res=given().relaxedHTTPSValidation().body("{\r\n" + 
					"    \"username\": \"031jyotibehera\",\r\n" + 
					"    \"password\": \"Jyoti@456\"\r\n" + 
					"}").header("content-type","application/json").filter(session)
					
					
					.when().post("/rest/auth/1/session")
					.then().log().all().assertThat().statusCode(200)
					.extract().response().asString();
			
			
			
		
		
		
		
		
		
//********************************Add Commnent**********************
			
			
			System.out.println("***************Add Commnent*******************");

		String expectedCommnet="Create new Commnent by rest Assured by QA";
		
		  String comment=given().log().all().pathParam("key","GEN-3").log().all().body("{\r\n" +
		  "    \"body\": \""+expectedCommnet+"\",\r\n" +
		  "    \"visibility\": {\r\n" + "        \"type\": \"role\",\r\n" +
		  "        \"value\": \"Administrators\"\r\n" + "    }\r\n" +
		  "}").header("content-type","application/json").filter(session)
		  .when().post("/rest/api/2/issue/{key}/comment")
		  .then().log().all().assertThat().statusCode(201).extract().response().asString();
		  
		  JsonPath js1=new JsonPath(comment);
		  String commnetId=js1.getString("id");
		  System.out.println(commnetId);
		 
		 


		//***********Add Attachment using multiPart method with header "Content-type","multipart/form-data" *********************
		  
			
		/*
		 * given().header("X-Atlassian-Token","no-check").header("Content-type",
		 * "multipart/form-data") .pathParam("key","GEN-3").multiPart("file",new
		 * File("nitu.jpg")).filter(session)
		 * .when().post("/rest/api/2/issue/{key}/attachments")
		 * .then().log().all().assertThat().statusCode(200);
		 */
			
			
		//**************get the Issue Details and Verify if added comment and attachmnet exist using get api**************
			
		
		/*
		 * String
		 * str=given().pathParam("Key","GEN-3").header("content-type","application/json"
		 * ).filter(session)
		 * .when().get("/rest/api/2/issue/{Key}").then().assertThat().statusCode(200).
		 * extract().response().asString();
		 * 
		 * System.out.println(str);//it return 48 records
		 */		  
		  
		  System.out.println("***************get added Commnent*******************");
		  
		  String str=given().pathParam("Key","GEN-3").queryParam("fields", "comment").header("content-type","application/json").filter(session)
				  .when().get("/rest/api/2/issue/{Key}").then().assertThat().statusCode(200).
				  extract().response().asString();
				  
				  System.out.println(str);
				  
				  JsonPath js2=new JsonPath(str);
				 int commentSize =js2.getInt("fields.comment.comments.size()");
				 
				 for(int i = 0;i<commentSize;i++)
					
				 {
					 String allid=js2.getString("fields.comment.comments["+i+"].id");
					 
					 if(allid.equals(commnetId))
					 {
						 System.out.println(allid);
						 
						 String commnetbody=js2.getString("fields.comment.comments["+i+"].body");
						 System.out.println(commnetbody);
						 
						 Assert.assertEquals(commnetbody, expectedCommnet);
						 
						 break;
						 
					 }
					 
					 
					 
					 
					 
				 }
				 
			
			
			
			//***********Note: relaxedHTTPSValidation() method is used to handle https certification
		  

	}

}

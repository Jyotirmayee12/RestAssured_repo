package specBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class AddPlace_ResponseSpecBuilder {



	@Test
	public void AddGoogleMap()
	{

		//******************* pojo serialisation*************

		Googlemap_pojo map=new Googlemap_pojo();

		LocationClass loc=new LocationClass();
		loc.setLat(-38.383494);
		loc.setLat(33.427362);
		map.setLocation(loc);

		map.setAccuracy(50);
		map.setName("Frontline house");
		map.setPhone_number("(+91) 983 893 3937");
		map.setAddress("29, side layout, cohen 09");


		List<String> str=new ArrayList<String>();
		str.add("shoe park");
		str.add("shop");
		map.setTypes(str);

		map.setWebsite("http://google.com");
		map.setLanguage("French-IN");

		//*************************************request spec builder**************

		RequestSpecification res=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();



		//*************************************response spec builder**************

		ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();		


		//				RequestSpecification respon=given().spec(res).body(map);
		//				
		//				String res1=respon.when().post("/maps/api/place/add/json").
		//				then().spec(resspec)
		//				.extract().response().asString();
		//				
		//				System.out.println(res1);




		String respon1=given().spec(res).body(map)
				.when().post("/maps/api/place/add/json").then().spec(resspec)
				.extract().response().asString();
		System.out.println(respon1);

	}

}







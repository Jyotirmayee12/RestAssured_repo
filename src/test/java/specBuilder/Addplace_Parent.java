package specBuilder;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class Addplace_Parent {

		
		
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
			
			//*****************request spec builder**************
			
		RequestSpecification res=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
			
			RequestSpecification respon=given().spec(res).body(map);
			
			String res1=respon.when().post("/maps/api/place/add/json").
			then().assertThat().statusCode(200)
			.extract().response().asString();
			
			
			System.out.println(res1);
			
			
		}

	}




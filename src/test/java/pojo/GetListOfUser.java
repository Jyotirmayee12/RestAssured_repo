package pojo;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetListOfUser {

	@Test
	public void allUsers() {


		//**********get the total number page using deserialisation*************

		RestAssured.baseURI="https://reqres.in";

		ListUser res=given().queryParam("page","2").expect().defaultParser(Parser.JSON).
				when().get("/api/users?page=2").as(ListUser.class);

		//System.out.println(res.getTotal());


		//***************get url from Support using deserialisation*****************

		System.out.println(res.getSupport().getUrl());



		//***********************get first name from 5th index of data----Statically

		System.out.println(res.getData().get(5).getFirst_name());
		
		

		//*****************get first name from 5th index of data----dynamically 

		List<DataClass> d1=res.getData();

		for(int i=0;i<d1.size();i++)
		{
			System.out.println(d1.get(i).getEmail());


		}
		
		
		//***************
		
		
		String[] expectedfirstname= {"Michael","Lindsay","Tobias","Byron","George","Rachel"};
		
		//we could not compare array with arrayList so convert array to array List by using asList method
		
		List<String> expectedlist=Arrays.asList(expectedfirstname);
		
		
		ArrayList<String> actuallist= new ArrayList<String>();
//		str.add("Michael");
//		str.add("Lindsay");
//		str.add("Tobias");
//		str.add("Byron");
//		str.add("George");
//		str.add("Rachel");
//		System.out.println("ArrayList : " + str);
		
		for(int i=0;i<d1.size();i++)
		{
			actuallist.add(d1.get(i).getEmail());
		}
		
		Assert.assertTrue(actuallist.equals(expectedlist));

	}


}

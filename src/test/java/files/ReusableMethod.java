package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {
	
	
	public static JsonPath jsonConverter(String response)
	{
		JsonPath js3=new JsonPath( response);
				return js3;
	}

	

}

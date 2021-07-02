package PlaceAPI;

import files.Addbodypayload;
import io.restassured.path.json.JsonPath;

public class printnOfCourse {

	public static void main(String[] args) {


		int i=0;



		//*********************program for Print No of courses returned by API******************

		JsonPath js2=new JsonPath(Addbodypayload.AddBook());
		int count=	js2.getInt("courses.size()");
		System.out.println(count);




		//*********************Print Purchase Amount*********************



		int purchase_Amount=js2.get("dashboard.purchaseAmount");
		System.out.println(purchase_Amount);


		//******************************Print Title of the first course*******************


		String title_of_firstCourse=js2.getString("courses[0].title");
		System.out.println(title_of_firstCourse);
		System.out.println("***************************************");


		//****************************Print All course titles and their respective Prices********************

		for(i=0;i<count;i++) {

			String allCourse_Title=js2.getString("courses["+i+"].title");

			System.out.println(js2.get("courses["+i+"].price"));

			System.out.println(allCourse_Title);

		}
		System.out.println("***************************************");



		//***************************Print no of copies sold by RPA Course************************

		for(i=0;i<count;i++) {

			String allCourse_Title2=js2.getString("courses["+i+"].title");



			if(allCourse_Title2.equals("RPA"))
			{
				int copies=js2.getInt("courses["+i+"].copies");
				System.out.println(copies);


				System.out.println("***************************************");
				break;
				//return true;

			}
		}


		//*******************************Verify if Sum of all Course prices matches with Purchase Amount************
		
		int sumOfAllCoursePrice=0;
		for(i=0;i<count;i++)
		{

			int copies=js2.getInt("courses["+i+"].copies");
			int addprice=js2.getInt("courses["+i+"].price");


			sumOfAllCoursePrice=sumOfAllCoursePrice+(addprice*copies);

		}
		
		
		System.out.println("sumOfAllCoursePrice value = "+ sumOfAllCoursePrice);
		System.out.println("purcharge amount value = "+ purchase_Amount);

		if(sumOfAllCoursePrice==purchase_Amount)
		{
			System.out.println("all Course prices matches with Purchase Amount");
		}
		else
		{
			System.out.println("all Course prices is not matches with Purchase Amount");
		}







   }
}



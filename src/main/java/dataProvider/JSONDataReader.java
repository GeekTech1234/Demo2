/*
 * => First, Copy json-simple dependency to pom.xl file
 * 
 *  - For JSon reading, FileReader is preferrable over FileInputSteam.
 *  
 *  
| Feature           | FileReader                                                                 | FileInputStream                           |
| ----------------- | -------------------------------------------------------------------------- | ----------------------------------------- |
| Type              | Character stream                                                           | Byte stream                               |
| Reads             | Text data (characters)                                                     | Binary data (raw bytes)                   |
| Best used for     | .txt, .json, .csv, .xml (text files)                                       | Images, PDFs, audio, video (binary files) |
| Encoding handling | Uses default character encoding                                            | No encoding conversion (just bytes)       |

 *  
 *  ✅ Why FileReader is preferred for JSON?
 *  
 *  1. JSON is a text-based format, so it must be read as characters, not bytes.
 *  2. FileReader automatically converts bytes → characters internally, which is missing in FileInputSteam, need to do manually.
 *  3. JSON parsers (JsonParser etc.) expect a Reader (like FileReader) rather than raw InputStream.
 * 
 *  ✅ So why not FileInputStream?
 *  
 *  -> You can use it, but then you must convert bytes to characters manually:
 *  
 *  => JSONObject internally works as a Map.
 *   - Therefore we can use JSONObject.get(key), which will return Object obj, that we have to type cast to String as needed.
 *  
 *  => But, JSONObject is not excatly map, therefore JSONObject.entrySet() returns raw set without generics (raw Set — no <...>>)
 *     Whereas, Map.entrySet() returns Set<Map.Entry<K, V>> (With Generics)
 *   - So, each element in that set is treated as Object, therefore we've to cast it to Map.Entry<String,Object>, then
 *     we can use getKey() and getValue(). 
 *  
 *  => To get String and JSONArray value using key, we need to cast accordingly to String and JSONArray.
 */

package dataProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class JSONDataReader {
/*	
	public static Object[][] readJSONData()
	{
		JSONParser jsonParser=new JSONParser();
		Object[][] arr=null;
		
		try {
			File src=new File(System.getProperty("user.dir")+"//testdata//data.json");
			FileReader reader=new FileReader(src);
			Object obj = jsonParser.parse(reader);  //parse method returns Object
			JSONObject jsonObject= (JSONObject)obj;
//			getStringValue(jsonObject,key);
//			getJsonArray(jsonObject, jsonArrayKey);			
			
			int row=jsonObject.size();
			arr=new Object[row][2];
			int i=0;
			
			for(Object entryObj :jsonObject.entrySet())
			{
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) entryObj;
				arr[i][0] = entry.getKey();
				arr[i][1] = entry.getValue();
				i++;
			}
		}
		catch (IOException | ParseException e)
		{
			System.out.println("Could not read or parse file "+e.getMessage());
		}
		return arr;
	}
	
	
	public static String getStringValue(JSONObject jsonObject, String key)
	{
		Object obj= jsonObject.get(key);
		String value=(String) obj;
		return value;
	}
	
	public static JSONArray getJsonArray(JSONObject jsonObject, String jsonArrayKey)
	{
		Object obj=jsonObject.get(jsonArrayKey);
		JSONArray jsonArray = (JSONArray) obj;
		return jsonArray;
	}
*/	
	
	public static Object[][] readJSONData0()
	{
		/*
		     {
                "username": "testuser",
                "password": "password123"
             }
		*/
		
		
	        JSONParser parser = new JSONParser();
	        Object[][] arr=null;
	    
	        File src=new File(System.getProperty("user.dir")+"//testdata//data.json");

			try {
		        FileReader reader=new FileReader(src);
		        JSONObject jsonObject = (JSONObject) parser.parse(reader);
		        String username = (String) jsonObject.get("username");
		        String password = (String) jsonObject.get("password");
		        
		        arr[0][0]=username;
		        arr[0][1]=password;
		        
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
       return arr;		
	}
	
	public static Object[][] readJSONData1()
	{
		/*
		
		[
           { "username": "testuser1", "password": "password123" },
           { "username": "testuser2", "password": "password1234" }
        ]
		
		*/
		
		JSONParser parser = new JSONParser();
        Object[][] arr=null;
    
        File src=new File(System.getProperty("user.dir")+"//testdata//data.json");
        try {
			FileReader reader=new FileReader(src);
			JSONArray jsonArray = (JSONArray)parser.parse(reader);
			
			arr=new Object[jsonArray.size()][2];
			
			for(int i=0;i<jsonArray.size();i++)
			{
			   JSONObject jsonObject=(JSONObject)jsonArray.get(i);
			   arr[i][0]  = (String) jsonObject.get("username");
			   arr[i][1]  = (String) jsonObject.get("password");
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return arr;
	}
	
	
	
	public static Object[][] readJSONData2()
	{
		/* 
	       JSON Data is : 
	       
		     {
                "username": "testuser",
                "password": "password123",
                "items": [
                          {"name": "item1", "price": 10.99},
                          {"name": "item2", "price": 20.50}
                          ]
             }
		 */ 
		
		Object[][] arr=null;
		
		JSONParser parser=new JSONParser();
		
		File src=new File(System.getProperty("user.dir")+"\\testdata\\data.json");
		try {
			FileReader reader=new FileReader(src);
			Object obj = parser.parse(reader);
			JSONObject jsonObject = (JSONObject) obj;
			
			String username = (String)jsonObject.get("username");
			String password = (String) jsonObject.get("password");
			
			JSONArray jsonArray = (JSONArray) jsonObject.get("items");
			
			arr=new Object[jsonArray.size()][4];
		
			for(int i=0;i<jsonArray.size();i++)
			{
				JSONObject jObject = (JSONObject) jsonArray.get(i);
				
				arr[i][0]=username;
				arr[i][1]=password;
				arr[i][2]=(String)jObject.get("name");
				arr[i][3]=(Double)jObject.get("price"); 		
			}
			
		} catch (IOException | ParseException e) {
			e.getMessage();
		}
		
		return arr;			
	}
	
	/*       
    {
       "users": [
                { "id": 1, "name": "John" },
                { "id": 2, "name": "Smith" }
                ],
   "loginData": [
                { "username": "admin", "password": "admin123" },
                { "username": "bhavik", "password": "bhavik694" },
                { "username": "user", "password": "user123" }
                ]
    }
  
  */
	
	
	
	//Method 1 (Combined but Grouped DataProvider) :  
	
	public static Object[][] readJSONData3()
	{		
		
		Object[][] arr=null;
		JSONParser jsonParser=new JSONParser();
		
		File src=new File(System.getProperty("user.dir")+"//testdata//data.json");
		try {
			FileReader reader = new FileReader(src);
			JSONObject jsonObject =  (JSONObject)jsonParser.parse(reader);
			
			JSONArray userArray= (JSONArray) jsonObject.get("users");
			JSONArray loginDataArray= (JSONArray) jsonObject.get("loginData");
			
			int size=Math.min(userArray.size(), loginDataArray.size());       //Because both have different number of details
			arr=new Object[size][4];
			
			for(int i=0;i<size;i++)
			{
				JSONObject userObject = (JSONObject)userArray.get(i);
				JSONObject loginObject = (JSONObject) loginDataArray.get(i);
				
				arr[i][0] = (long)userObject.get("id");  //Json-simple library returns Long object for id, therefore we're casting to long, not int
				arr[i][1] = (String)userObject.get("name");
				arr[i][2] = (String)loginObject.get("username");
				arr[i][3] = (String)loginObject.get("password");
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		
		return arr;
	}		
		
	//Method 2 (Two Separate DataProviders) : 

	public static Object[][] getUsersData()
	{
		Object[][] arr=null;
		JSONParser parser1=new JSONParser();
		
		File src=new File(System.getProperty("user.dir")+"//testdata//data.json");
		
		try {
			FileReader reader1 = new FileReader(src);
			JSONObject jsonObject = (JSONObject) parser1.parse(reader1);
			
			JSONArray userArray = (JSONArray) jsonObject.get("users");
			
			arr=new Object[userArray.size()][2];
			
			for(int i=0;i<userArray.size();i++)
			{
				JSONObject userObject = (JSONObject) userArray.get(i);
				arr[i][0] = (long)userObject.get("id");
				arr[i][1] = (String)userObject.get("name");
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return arr;	
	}
	
	public static Object[][] getLoginData()
	{
		Object[][] arr=null;
		JSONParser parser1=new JSONParser();
		
		File src=new File(System.getProperty("user.dir")+"//testdata//data.json");
		
		try {
			FileReader reader1 = new FileReader(src);
			JSONObject jsonObject = (JSONObject) parser1.parse(reader1);
			
			JSONArray loginArray = (JSONArray) jsonObject.get("loginData");
			
			arr=new Object[loginArray.size()][2];
			
			for(int i=0;i<loginArray.size();i++)
			{
				JSONObject loginObject = (JSONObject) loginArray.get(i);
				arr[i][0] = (String)loginObject.get("username");
				arr[i][1] = (String)loginObject.get("password");
			}
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return arr;	
	}
}

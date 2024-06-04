package json_handling;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Function object where execute(String filename) returns the JSONObject from a JSON file.
 * 
 * @author Darcy Mazloum
 *
 */
public class Get_JSONObject 
{
	/*
	public static void main(String[] args)
	{
		System.out.println( execute("src//main//resources//resultats.json") );
	}
	*/
	
	public static JSONObject execute(String filename)
	{
		File file = new File(filename);
        Scanner sc;
        
        try
        {
            sc = new Scanner(file);
            String jsonStr = "";
            while (sc.hasNextLine()) jsonStr += sc.nextLine();
            sc.close();
            
            return new JSONObject(jsonStr);
        }
        catch (JSONException | FileNotFoundException e) 
        {
            e.printStackTrace();
        }

        return (JSONObject) JSONObject.NULL;
	}
}

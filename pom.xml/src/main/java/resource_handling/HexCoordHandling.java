package resource_handling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONArray;

import election_objects.Quebec;
import election_objects.Riding;

public class HexCoordHandling 
{

    private static String hex_coords_file_name = "hex_coords_by_riding.json";

    /**
     * 
     * @param election_obj holds the election data that this function can access.
     * @return 0 for successful run, 1 if file already exists, and 2 if there is an IO error.
     */
    public static int makeHexCoordsFile(Quebec election_obj)
    {

        // This returns the folder ...../pom.xml/target/classes which is indeed where resources are.
		// But, if I put in hex_coord_file_name.json and it returns null then it doesn't exist.
		// So, if it is not null, then return 1.
		// Else, create the file using the below command (this str + hex_coord_file_name.json) and return 0.
		// URL resource_folder = CreateRegionsData.class.getClassLoader().getResource("");
		// System.out.println(resource_folder);


        URL hex_coord_path = HexCoordHandling.class.getClassLoader().getResource(hex_coords_file_name);
        // If it DOES exist, then we may not want to make a new file.
        if (hex_coord_path != null)
        {
            while (true)
            {
                Scanner validate_replace = new Scanner(System.in);
                System.out.println(
                    "HexCoordHandling.makeHexCoordsFile would like to make a new file for the hex coords but one already exist. Type y to replace, type n to cancel the operation.");
                String response = validate_replace.nextLine();
                validate_replace.close();

                if (response.equals("n"))
                {
                    System.out.println("Operation cancelled.");
                    return 1;
                }
                else if (response.equals("y"))
                {
                    // By putting false into the FileWriter constructor, the file is replaced.
                    System.out.println("File replaced.");
                    break;
                }
            }
                

        }
        
        // Now get the path to the resource folder and add hex_coord_whatever to it and make that file.
        String hex_coord_full_path_str = 
            URLtoReadablePath( HexCoordHandling.class.getClassLoader().getResource("") ) 
            + hex_coords_file_name;
        
        // JSONObject to put into the file.
        JSONObject hex_data_to_print = new JSONObject();

        election_obj.keySet().forEach((riding_str) -> {
            hex_data_to_print.put(riding_str, new JSONArray());
        });
        
        try
		{
            File hex_file = new File(hex_coord_full_path_str);
            hex_file.createNewFile();

            FileWriter writer = new FileWriter(hex_coord_full_path_str, false);
			writer.write(hex_data_to_print.toString(4));
            writer.close();
            return 0;
		} 
		catch (IOException e) {
			System.out.println("An error occurred while creating/writing to the file.");
			e.printStackTrace();
            return 2;
		}
    }

    public static JSONObject getHexDataJsonObject()
    {
        URL hex_coord_path = HexCoordHandling.class.getClassLoader().getResource(hex_coords_file_name);
        // If it DOES NOT exist, then we inform them and return 1.
        if (hex_coord_path == null)
        {
            System.out.println("An attempt was made to access hex_coords_by_riding.json but the file does not exist.");
            return null;
        }

        return GetJSONObject.execute( URLtoReadablePath(hex_coord_path) );
    }

    public static void addHexDataList(String riding, Collection<int[]> list)
    {
        list.forEach((qrs) -> {
            updateHexCoordFile(riding, qrs);
        });
    }

    public static int addHexData(Riding riding, int[] qrs)
    {
        return updateHexCoordFile(riding.getRidingName(), qrs);
    }

    public static int addHexData(Riding riding, int q, int r, int s)
    {
        int[] arr = {q,r,s};
        return updateHexCoordFile(riding.getRidingName(), arr);
    }

    public static int addHexData(String riding, int q, int r, int s)
    {
        int[] arr = {q,r,s};
        return updateHexCoordFile(riding, arr);
    }

    public static int addHexData(String riding, int[] qrs)
    {
        if (qrs.length != 3)
        {
            System.out.println("Array must be of length 3. addHexData failed.");
            return 1;
        }

        return updateHexCoordFile(riding, qrs);
    }

    /**
     * 
     * @param riding is the String name of the riding.
     * @param qrs is an int array of size 3 representing the hex coords being added to this riding.
     * @return 0 on success, 1 if file does not exist, 2 if the input is a duplicate coordinate, 3 if the function was unable to create or write the file.
     */
    private static int updateHexCoordFile(String riding, int[] qrs)
    {
        // Get json object from file
        // Update it
        // Put it back
        
        URL hex_coord_path = HexCoordHandling.class.getClassLoader().getResource(hex_coords_file_name);
        // If it DOES NOT exist, then we inform them and return 1.
        if (hex_coord_path == null)
        {
            System.out.println("An attempt was made to update hex_coords_by_riding.json but the file does not exist.");
            return 1;
        }

        JSONObject hex_data = GetJSONObject.execute( URLtoReadablePath(hex_coord_path) );
        
        // Go to the key riding, and get its JSONArray value.
        //   Then put it back with the new coords.
        JSONArray riding_hex = hex_data.getJSONArray(riding);

        // If there is nothing in the array then it will skip the for loop entirely.
        boolean contains = (riding_hex.length() == 0) ? false : true;
        // For each existing coord.
        //   Check if any of them are equal to the item we are inputting.
        for (int hex_i = 0;hex_i<riding_hex.length();hex_i++)
        {
            JSONArray hex_arr_ = (JSONArray) riding_hex.getJSONArray(hex_i);
            for (int qrs_i=0;qrs_i<qrs.length;qrs_i++)
            {
                if (hex_arr_.getInt(qrs_i) != qrs[qrs_i]) 
                {
                    // If one coordinate of three is different...
                    contains = false;
                }
                // ... then break this loop.
                if (!contains) break;
            }
            // If contains is still true, it means all coordinates were the same.
            if (contains) break;
            // Otherwise, the code will check the next coordinate.

            // If we are not at the end, we reset the boolean contains to be true.
            if (! (hex_i == riding_hex.length() - 1)) contains = true;
            // Otherwise, the last coordinate must not have been the same.
        }
            
        // If this coordinate already exists in the array, then it will not be added.
        if (!contains) hex_data.put(riding,   hex_data.getJSONArray(riding).put(new JSONArray(qrs))   );
        else return 3;

        String hex_coord_full_path_str = URLtoReadablePath(hex_coord_path);

        try
		{
            File hex_file = new File(hex_coord_full_path_str);
            hex_file.createNewFile();

            FileWriter writer = new FileWriter(hex_coord_full_path_str, false);
			writer.write(hex_data.toString(4));
            writer.close();
            return 0;
		} 
		catch (IOException e) {
			System.out.println("An error occurred while creating/writing to the file.");
			e.printStackTrace();
            return 2;
		}
    }

    private static String URLtoReadablePath(URL url)
    {
        return url
            .toString()
            .substring(6)
            .replace("%20", " ");
    }
}

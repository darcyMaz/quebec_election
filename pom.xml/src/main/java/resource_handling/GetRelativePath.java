package resource_handling;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class GetRelativePath 
{
    /*
     * Returns the relative Path of a given file.
     * Intention: that this project can use simple filenames without revealing a computer's full path to that file.
    */
    public static Path execute(String filename)
    {
        try
        {
            URL resource = CreateRegionsData.class.getClassLoader().getResource(filename);
            if (resource == null) 
			{
                throw new IllegalArgumentException("File not found!");
            }
			return Paths.get(resource.toURI());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }    
}

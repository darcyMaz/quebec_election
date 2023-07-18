package election_years;

import election_objects.Quebec;
import json_handling.Get_JSONObject;

public class Election_2022 extends Election_Year
{
	private Quebec election_object;
	
	public Election_2022( String filename ) 
	{
		election_object = new Quebec(  Get_JSONObject.execute(filename)  );
	}
	
	@Override
	public Quebec getElectionObject() { return election_object; }

}

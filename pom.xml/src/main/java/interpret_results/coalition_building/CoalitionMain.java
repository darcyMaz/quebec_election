package interpret_results.coalition_building;

import java.util.List;

import org.json.JSONObject;

import java.util.ArrayList;

import election_objects.Quebec;

public class CoalitionMain 
{
	public static String coalitionsAsString(Quebec election_object, List<String> coalitionParties)
	{	
		ArrayList<String> toReturnList = new ArrayList<>();
		
		CoalitionFiltering.filter(election_object, coalitionParties).forEach
		(
			(ridingName,coalitionList) -> 
			{
				toReturnList.add( ridingName + ": \n" );	
				for ( Coalition coalition : coalitionList ) toReturnList.add( coalition.getParties() + "\n" );
				toReturnList.add( "\n" );
			}
		);
		
		String toReturn = "";
		for ( String str : toReturnList ) toReturn += str;
		
		return toReturn;
	}
	
	public static String coalitionsAsJSON(Quebec election_object, List<String> coalitionParties)
	{
		ArrayList<String> toReturnList = new ArrayList<>();
		
		JSONObject toReturnJSON = new JSONObject();

		
		
		CoalitionFiltering.filter(election_object, coalitionParties).forEach
		(
			(ridingName,coalitionList) -> 
			{
				toReturnList.add( ridingName + ": \n" );	
				for ( Coalition coalition : coalitionList ) toReturnList.add( coalition.getParties() + "\n" );
				toReturnList.add( "\n" );
				
				/*
				  
				  
				 
				 */
			}
		);
		
		String toReturn = "";
		for ( String str : toReturnList ) toReturn += str;
		
		return toReturn;
	}
}

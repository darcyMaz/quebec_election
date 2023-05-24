package app;

import election_objects.Quebec;
import election_years.Election_2022;
import interpret_results.CoalitionBuilding;
import interpret_results.FPP_Results;

public class App 
{
	public static void main(String[] args)
	{
		/*
		 * I want to make it so this class can run from a terminal but i don't feel like figuring out how
		 * to do that with Eclipse so for now it just has the election year.
		 * 
		int year;
		try 
		{ 
			year = Integer.parseInt(args[0]); 
			System.out.println(year);
		} 
		catch (NumberFormatException e) 
		{
			System.err.println("Usage: javac App [int representing an election year].");
		}
		*/
		
		// int year = 2022;
		
		/*
		 * So here I want to go through each file in the election_years package and run the class
		 * which matches the input year. Don't know how to actually run this class from here though.
		 * Create and run a simple bash script? Easy way to call the class? I'll figure it out.
		 * 
		File folder = new File("src/main/java/election_years");
		for ( File election_year_file : folder.listFiles() )
		{
			String filename = election_year_file.getName();
			if (filename.equals("Election_Year.java")) continue;
			
			try 
			{
				int current_election_year =  Integer.parseInt( filename.split("_")[1] );
				if (current_election_year == year) 
					// in this case run the current file.
					//election_year_file.
					;
			}
			catch (Exception e) 
			{
				System.err.println("There's a problem parsing a filename in App.java");
				e.printStackTrace();
			}	
		}
		*/
		
		Election_2022 election2022 = new Election_2022( "src/main/resources/resultats.json" );
		//System.out.println( election2022.getElectionObject() + "" + election2022.getElectionObject().getNumOfRidings() );
		
		// I want to go thru each riding and look at results.
		// Then I want to go thru each riding and do some math. What coalitions can be made to beat a CAQ winner?
		// yes, specifically a CAQ winner. ignore any ridings where a winner got >50%.
		// What coalitions are possible let's print them all out for each riding.
		
		Quebec election_obj = election2022.getElectionObject();
		FPP_Results results = new FPP_Results(election_obj);
		System.out.println( results.getResults() );
		
		System.out.println( CoalitionBuilding.makeCoalition(election_obj) );
		
		/*
		election_obj.forEach( (k,v) -> 
			); // return results to start
		*/
	}
}

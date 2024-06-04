package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import election_objects.Quebec;
import election_years.Election_2022;
import interpret_results.FPP_Results;
import interpret_results.coalition_building.Coalition;
//import interpret_results.coalition_building.CoalitionBuilding;
import interpret_results.coalition_building.CoalitionFiltering;
import interpret_results.coalition_building.CoalitionMain;

/**
 * App runs this project and returns the result of a given Quebec election as well as the coalitions that could be created by losing parties.
 * This information is returned in the form of a single JSONObject which can then be turned into a JSON file.
 * 
 * 
 * @author Darcy Mazloum
 *
 */
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
		
		Election_2022 election2022 = new Election_2022( "src//main//resources//resultats.json" );
		Quebec election_obj = election2022.getElectionObject();
		

		FPP_Results results = new FPP_Results(election_obj);
		System.out.println( results.toString() );
		
		/* 
		ArrayList<String> coalitionParties = new ArrayList<>();
		coalitionParties.add("P.L.Q./Q.L.P.");
		coalitionParties.add("P.C.Q-E.E.D.");
		coalitionParties.add("Q.S.");
		coalitionParties.add("P.Q.");
		
		String coalitionStr = CoalitionMain.coalitionsAsString(election_obj, coalitionParties);
		System.out.println(coalitionStr);
		*/
		/*
			P.Li.Q.
			P.V.Q./G.P.Q.
			P.A.P.E.
			P.C.Q./C.P.Q
			B.M.-E.B.H.
			D.D.
			P.Cu.Q.
			P.H.Q.
			Ind.
			P51
			P.M.L.Q.
			A.F.C.
			P.L.Q./Q.L.P.
			E.A.
			C.Q.
			P.N.
			C.A.Q.-E.F.L.
			Q.S.
			U.F.F.
			P.Q.
			U.N.
			P.C.Q-E.E.D.
		 */
		
	}
}

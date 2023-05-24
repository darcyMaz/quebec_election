package interpret_results;

import java.util.HashMap;
import java.util.Map.Entry;

import election_objects.Quebec;

/**
 * Class which can return the results of this election which assumes that the first past the post (plurality)
 * voting system is used.
 * 
 * I feel like there doesn't need to be a constructor yk? That it can just be one static method? I'll
 * figure it out.
 * @author 15148
 *
 */
public class FPP_Results 
{
	private Quebec electionObject;
	
	public FPP_Results(Quebec pElectionObject)
	{
		electionObject = pElectionObject;
	}
	
	public String getResults()
	{
		HashMap<String,Integer> ridingTotals = new HashMap<>();
		
		for ( String partyAbrevs : electionObject.getPartyAbbreviations() )
			ridingTotals.put(partyAbrevs, 0);
		
		// This line of code goes through each Riding Name, Riding Object pair in electionObject and
		// takes the first Candidate from each Candidate list, looks at their party affiliation, and then
		// adds one to that party's score in the ridingTotals HashMap.
		// This can be done because getCandidates() function in Riding is ordered from most votes to
		// least votes.
		electionObject.forEach
		( 
			// There was a problem that I found. In the party explanation at the top of the JSOn file
			// there is a party called independents. Of course that's not an actual party but that does
			// not matter. What matters is that their abbreviation is "Ind." while the abbreviation elsewhere
			// for independents is "Ind"  That's a problem since I used the abbreviations. Anyway it's fine
			// but maybe in a future election it all goes haywire :)
			(k,v) -> 
			{
				String currPartyAbbr = v.getCandidates().iterator().next().getPartyAbreviation();
				
				ridingTotals.put
					( 
							currPartyAbbr,
							ridingTotals.get(currPartyAbbr) + 1
					);
			}
		);
		
		String toReturn = "";
		int mostRidings = 0;
		String winner = "ERROR: NO WINNER";
		
		for ( Entry<String, Integer> entry : ridingTotals.entrySet() )
		{
			if (entry.getValue() > 0) toReturn += entry.getKey() + " : " + entry.getValue() + "; ";
			if (entry.getValue() > mostRidings) 
			{
				mostRidings = entry.getValue();
				winner = "Winner of the election with " + entry.getValue() + " seats is " + entry.getKey();
			}
		}
		
		return toReturn + "\n" + winner;
	}

}

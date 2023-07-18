package interpret_results;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONObject;
import org.json.JSONArray;

import election_objects.Candidate;
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
	
	@Override
	public String toString()
	{
		HashMap<String,Integer> ridingTotals = new HashMap<>();
		
		for ( String partyAbrevs : electionObject.getPartyAbbreviations() )
			ridingTotals.put(partyAbrevs, 0);
		
		// This line of code goes through each <String ridingName, Riding Object> pair in electionObject and
		// takes the first Candidate from each Candidate list, looks at their party affiliation, and then
		// adds one to that party's score in the ridingTotals HashMap.
		// This can be done because getCandidates() function in Riding is ordered from most votes to
		// least votes.
		electionObject.forEach
		( 
			// There was a problem that I found. In the party explanation at the top of the JSOn file
			// there is a party called independents. Of course that's not an actual party but that does
			// not matter. What matters is that their abbreviation is "Ind." while the abbreviation elsewhere
			// for independents is "Ind"  That's a problem since I used the abbreviations defined at the start which cannot be different
			//. Anyway it's fine
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

	/**
	 * @return JSONObject formatted like this:
	 * { 
	 *  "seat_distribution": 
	 *  	{ "party_abrev": # of seats, "":#, ... },
	 *  "ridings":
	 *  	{ "riding_name": 
	 *  		{ ridingNumber: #, ridingName: "", validVotes: # , rejectedVotes: #, totalElectors: #, candidates: [ {candidateName, partyAbrev, voteTotal}, ... ] },
	 *  	  "riding_name_1":
	 *  		{...},
	 *  	}
	 *  }
	 *  
	 */
	public JSONObject getJSONObjectResult() 
	{
		JSONObject toReturn = new JSONObject();
		toReturn.put("seat_distribution", new JSONObject());
		toReturn.put("ridings", new JSONObject());
		
		for ( String partyAbrevs : electionObject.getPartyAbbreviations() )
			(toReturn.getJSONObject("seat_distribution")).put( partyAbrevs, 0  );
		
		electionObject.forEach
		(
				(ridingName, riding) -> 
				{	
					String winner_partyAbrev = riding.getCandidates().iterator().next().getPartyAbreviation() ;
					toReturn.getJSONObject("seat_distribution").put
						(  winner_partyAbrev , toReturn.getJSONObject("seat_distribution").getInt(winner_partyAbrev) + 1 );
					
					
					JSONObject ridingInfo = new JSONObject();
					
					// add stuff to the JSONObject called ridingInfo here
					ridingInfo.put("ridingNumber", riding.getRidingNumber());
					ridingInfo.put("ridingName", riding.getRidingName());
					ridingInfo.put("validVotes", riding.getValidVotes());
					ridingInfo.put("rejectedVotes", riding.getRejectedVotes());
					ridingInfo.put("totalElectors", riding.getTotalElectors());
					
					JSONArray candidates = new JSONArray();
					
					for ( Candidate candidate : riding.getCandidates() )
					{
						JSONObject candJSONObj = new JSONObject();
						
						candJSONObj.put("candidateName", candidate.getName());
						candJSONObj.put("partyAbrev", candidate.getPartyAbreviation());
						candJSONObj.put("voteTotal", candidate.getVoteTotal());
						
						candidates.put(candJSONObj);
					}
					
					ridingInfo.put("candidates", candidates);
					
					toReturn.getJSONObject("ridings").put(ridingName, ridingInfo);
					
				}
		);
		
		return toReturn;
	}

}

package interpret_results.coalition_building;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import election_objects.Candidate;
import election_objects.Quebec;
import election_objects.Riding;

public class CoalitionFiltering 
{

	/**
	 * Function which filters out coalitions which are not included in the partiesToKeep function.
	 * @param electionObject has data from the election.
	 * @param partiesToKeep a list of parties which you'd like to keep in the coalitions.
	 * @return list of coalitions without parties not mentioned in partiesToKeep.
	 */
	public static HashMap<String, List<Coalition> > filter(Quebec electionObject, List<String> partiesToKeep)
	{
		HashMap<Riding, List<Coalition>> ridingsToCoalitions = CoalitionBuilding.makeCoalitions(electionObject);
		HashMap<String, List<Coalition>> filtered = new HashMap<>();
		
		ridingsToCoalitions.forEach
		(
				(riding, coalitions) -> 
					filtered.put
						(
								riding.getRidingName(), 
								filterHelper(coalitions,  partiesToKeep,  riding.getCandidates().iterator().next()) 
						)
		);
			
		return filtered;
	}
	
	private static List<Coalition> filterHelper(List<Coalition> coalitionList, List<String> partiesToKeep, Candidate pluralityWinner)
	{
		List<Coalition> toReturn = new ArrayList<>();
		
		// This case means one of two things: either a CAQ candidate won with >50% of the vote or
		// another party won the seat.
		if (coalitionList.size() == 1 && coalitionList.get(0).getParties().size() == 1) 
			return coalitionList;
		
		for ( Coalition coalition : coalitionList )
		{
			boolean addCoalition = true;
			for ( String party : coalition.getParties() ) 
			{
				if (!partiesToKeep.contains(party)) {addCoalition = false; break;}
			}
			if (addCoalition) toReturn.add(coalition);
		}
		
		// It is likely that after filtering coalitions, there are actually none that work as requested. 
		// In that case make a one party coalition of the actual winner.
		if (toReturn.isEmpty())
		{
			List<Candidate> oneCoalition = new ArrayList<>();
			oneCoalition.add( pluralityWinner );
			toReturn.add( new Coalition( oneCoalition ) );
		}
		
		return toReturn;
	}
	
}

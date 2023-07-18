package interpret_results.coalition_building;

import java.util.List;
import java.util.ArrayList;


import election_objects.Candidate;

public class Coalition
{
	private List<Candidate> candList;

	public Coalition(List<Candidate> pCandList)
	{
		candList = pCandList;
	}

	public List<String> getParties()
	{
		List<String> toReturn = new ArrayList<>();
		for ( Candidate cand : candList ) toReturn.add(cand.getPartyAbreviation());
		return toReturn;
	}
	public List<String> getCandidateName()
	{
		List<String> toReturn = new ArrayList<>();
		for ( Candidate cand : candList ) toReturn.add(cand.getName());
		return toReturn;
	}
	
	@Override
	public String toString()
	{
		String toReturn = "";
		
		for ( Candidate cand : candList ) toReturn += cand.getPartyAbreviation() 
				+ " : "
				+ cand.getName()
				+ "; ";
		
		return toReturn;
	}
}

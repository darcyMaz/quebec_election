package interpret_results;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import election_objects.Candidate;
import election_objects.Quebec;
import election_objects.Riding;

public class CoalitionBuilding 
{
	public static String makeCoalition(Quebec electionObject)
	{
		ArrayList<String> strList = new ArrayList<>();
		
		electionObject.forEach
		(
				(k,v) ->
				{
					strList.add(k + ":");
					strList.add( doRidingMath(v) );
					strList.add("\n");
				}
		);
		
		String toReturn = "";
		for ( String str : strList ) toReturn += ("\n"+ str);
		return toReturn;
	}
	
	private static String doRidingMath(Riding riding)
	{
		List<Candidate> candArr = new ArrayList<>(riding.getCandidates());
		
		Candidate winner = candArr.get(0);
		candArr.remove(0);
		
		Stack<Candidate> candStack = new Stack<>();
		
		int numOfCandsMoinUne = candArr.size();
		for (int index = 0; index < numOfCandsMoinUne; index++)
			{ candStack.push(candArr.get(index)); }
		
		candArr.clear();
		for (int index = 0;index < numOfCandsMoinUne;index++)
			{ candArr.add(candStack.pop()); }
		
		// I needed to reverse the candArr list to go from most votes first to most votes last.
		
		boolean isWinnerCAQ = winner.getPartyAbreviation().equals("C.A.Q.-E.F.L.");
		if (!isWinnerCAQ) return "Winner is not in the C.A.Q.-E.F.L. party.";
		
		double validVotes = ( (double) riding.getValidVotes());
		if ( ( ( (double) winner.getVoteTotal() ) / ((double) validVotes) ) > 0.5) 
			return "Winner is in the C.A.Q.-E.F.L. and has > 50.0% of the votes.";
		else if (( (double) winner.getVoteTotal() ) / ((double) validVotes) == 50.0) 
			return "The winner is in the is in the C.A.Q.-E.F.L. but got exactly half the vote. "
					+ "Thus the only coalition is with every other candidate. ";
		
		
		String toReturn = "";
		
		List<List<Candidate>> coalitionList = findCombinations( candArr,winner.getVoteTotal() );
		for ( List<Candidate> list : coalitionList )
		{
			for ( Candidate cand : list ) toReturn += cand.getPartyAbreviation() + " + ";
			toReturn = toReturn.substring(0, toReturn.length()-3);
			toReturn += "\n";
		}
		
		return "Here are the possible coalitions in " +riding.getRidingName() +":"+ "\n" + toReturn;
	}
	

	private static List<List<Candidate>> findCombinations(List<Candidate> candList, int target) 
	{
        List<List<Candidate>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), candList, target, 0);
        return result;
    }

    private static void backtrack(List<List<Candidate>> result, List<Candidate> tempList, List<Candidate> candList, int remain, int start) 
    {
        if (remain <= 0) {
            if (tempList.size() > 0) {
                result.add(new ArrayList<>(tempList));
            }
        } else {
            for (int i = start; i < candList.size(); i++) {
                tempList.add(candList.get(i));
                backtrack(result, tempList, candList, remain - candList.get(i).getVoteTotal(), i + 1);
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    /*
    private class candidateCoalition
    {
    	private List<Candidate> candList;
    	
    	public candidateCoalition(List<Candidate> pCandList)
    	{
    		candList = pCandList;
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
    */
	
}

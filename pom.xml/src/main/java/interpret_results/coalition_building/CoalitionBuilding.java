package interpret_results.coalition_building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import election_objects.Candidate;
import election_objects.Quebec;
import election_objects.Riding;

public class CoalitionBuilding 
{
	public static HashMap<Riding,List<Coalition>> makeCoalitions(Quebec electionObject)
	{
		HashMap<Riding,List<Coalition>> ridingNameToCoalitionList = new HashMap<>();
		
		electionObject.forEach
		(
				(ridingName,riding) -> ridingNameToCoalitionList.put(riding, doRidingMath(riding))
		);
		
		return ridingNameToCoalitionList;
	}
	
	private static List<Coalition> doRidingMath(Riding riding)
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
		// I needed to reverse the candArr list to go from most votes first to most votes last. //
		
		boolean isWinnerCAQ = winner.getPartyAbreviation().equals("C.A.Q.-E.F.L.");
		double validVotes = ( (double) riding.getValidVotes() );
		
		if ( ( (( (double) winner.getVoteTotal() ) / validVotes ) == 0.5) && isWinnerCAQ)
		{
			List<Coalition> oneCoalition = new ArrayList<>();
			oneCoalition.add(new Coalition(candArr));
			return oneCoalition;
		}
		else if (( (((double) winner.getVoteTotal() ) / validVotes ) < 0.5 ) && isWinnerCAQ)
		{
			List<Coalition> coalitions = new ArrayList<>();
			
			List<List<Candidate>> coalitionList = findCombinations( candArr,winner.getVoteTotal() );
			
			for ( List<Candidate> list : coalitionList )
			{
				coalitions.add( new Coalition( list ) );
			}
			return coalitions;	
		}
		else
		{
			return oneWinnerCoalition(winner);
		}
		
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
    
    private static List<Coalition> oneWinnerCoalition(Candidate winner)
    {
    	ArrayList<Candidate> oneWinnerCand = new ArrayList<>();
		oneWinnerCand.add( winner );
		
		List<Coalition> oneWinnerCoalition = new ArrayList<>();
		oneWinnerCoalition.add(new Coalition(oneWinnerCand));
		
		return oneWinnerCoalition;
    }
    
}

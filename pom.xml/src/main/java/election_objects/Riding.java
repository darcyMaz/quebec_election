package election_objects;

import java.util.TreeMap;
import java.util.Collection;

import org.json.JSONObject;
import org.json.JSONArray;

public class Riding
{

	private JSONObject jsonObject;	
	private TreeMap<Integer, Candidate> candidates;
	
	public Riding(JSONObject pJsonObject)
	{
		// try catch JSONException should contain this whole constructor.
		
		jsonObject = pJsonObject;
		candidates = new TreeMap<>( (a,b) -> b-a );
		
		// The file resultats.json has circonscriptions JSONObjects which hold JSONArrays with candidate data
		// represented by JSONObjects. This is interpreted by this "line" of code below.
		JSONArray candidateArray = pJsonObject.getJSONArray("candidats");
		candidateArray.forEach
			(  
				(JSONObj_cand) -> 
				
				{
					Candidate toAdd = new Candidate( (JSONObject) JSONObj_cand);
					candidates.put
					( 
							toAdd.getVoteTotal(), toAdd
					);
				}
			);
	}
	
	public int getRidingNumber() 
		{ return jsonObject.getInt("numeroCirconscription"); }
	
	public String getRidingName() 
		{ return jsonObject.getString("nomCirconscription"); }
	
	public int getValidVotes() 
		{ return jsonObject.getInt("nbVoteValide"); }
	
	public int getRejectedVotes() 
		{ return jsonObject.getInt("nbVoteRejete"); }
	
	public int getTotalElectors() 
		{ return jsonObject.getInt("nbElecteurInscrit"); }

	public Collection<Candidate> getCandidates() 
		{ return candidates.values(); }
	
	@Override
	public String toString()
	{
		return getRidingName() + " (" + getRidingNumber() + ")";
	}
}

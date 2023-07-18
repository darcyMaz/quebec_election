package election_objects;

import org.json.JSONObject;

public class Candidate 
{
	private JSONObject jsonObject;
	
	public Candidate(JSONObject pJsonObj)
	{
		jsonObject = pJsonObj;
	}
	
	public int getNumber() { return jsonObject.getInt("numeroCandidat"); }
	public String getName() { return jsonObject.getString("prenom") + " " + jsonObject.getString("nom"); }
	public String getPartyAbreviation() { return jsonObject.getString("abreviationPartiPolitique"); }
	public int getPartyNumber() { return jsonObject.getInt("numeroPartiPolitique"); }
	public int getVoteTotal() { return jsonObject.getInt("nbVoteTotal"); }
	
	@Override
	public String toString()
	{
		return getName() + " "+ getPartyAbreviation();
	}
	
}

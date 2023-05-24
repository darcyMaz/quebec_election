package election_objects;

import org.json.JSONObject;

/**
 * Object representing a political party in a Quebec election.
 * @author 15148
 *
 */
public class Party 
{

	private JSONObject jsonObject;
	
	public Party(JSONObject pJsonObject) 
	{
		jsonObject = pJsonObject;
	}
	
	public int getPartyNumber() { return jsonObject.getInt("numeroPartiPolitique"); }
	public String getPartyName() { return jsonObject.getString("nomPartiPolitique"); }
	public String getPartyAbreviation() { return jsonObject.getString("abreviationPartiPolitique"); }
	public int getVoteTotal() { return jsonObject.getInt("nbVoteTotal"); }
	public double getVotePercentage() { return jsonObject.getDouble("tauxVoteTotal"); }
	public int getRidingTotal() { return jsonObject.getInt("nbCirconscriptionsEnAvance"); }
	public double getPercentageOfRidings() { return jsonObject.getDouble("tauxCirconscriptionsEnAvance"); }

}

package election_objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

public class Quebec implements Map<String, Riding>
{
	//private static Quebec null_object = new Quebec( JSONObject.NULL );
	
	private JSONObject statistiques;
	private HashMap<String,Party> parties;
	private TreeMap<String, Riding> ridings;
	
	public Quebec(JSONObject json_obj) 
	{
		try
		{
			statistiques = json_obj.getJSONObject("statistiques");
			parties = new HashMap<>();
			ridings = new TreeMap<>();
			
			// An error could occur here if statistiques.get("partisPolitiques") isn't a JSONArray
			// But it is, at least for the 2022 election file.
			JSONArray parties_jsonArr = statistiques.getJSONArray("partisPolitiques");
			for ( Object partyObj : parties_jsonArr )
			{
				JSONObject party_jsonObj = (JSONObject) partyObj;
				
				Party newParty = new Party(party_jsonObj);
				parties.put(newParty.getPartyAbreviation(), newParty);
			}
			
			JSONArray ridings_jsonArr = json_obj.getJSONArray("circonscriptions");
			for ( Object ridingObj : ridings_jsonArr )
			{
				JSONObject riding_jsonObj = (JSONObject) ridingObj;
				
				Riding newRiding = new Riding(riding_jsonObj);
				ridings.put(newRiding.getRidingName(), newRiding);
			}
			
		}
		catch (JSONException e)
		{
			statistiques = (JSONObject) JSONObject.NULL;
			ridings = new TreeMap<>();
			parties = new HashMap<>();
			e.printStackTrace();
		}
	}
	
	
	public int getNumOfRidings() { return statistiques.getInt("nbCirconscription"); }
	public int getTotalElectors() { return statistiques.getInt("nbElecteurInscrit"); }
	public int getRejectedVotes() { return statistiques.getInt("nbVoteRejete"); }
	public int getValidVotes() { return statistiques.getInt("nbVoteValide"); }
	
	/**
	 * Returns an ArrayList<String> of all party abbreviations for all parties.
	 * @return ArrayList<String> with each party abbreviation.
	 */
	public ArrayList<String> getPartyAbbreviations() 
	{
		ArrayList<String> toReturn = new ArrayList<>();
		parties.forEach( (key,val) -> toReturn.add(key) );
		return toReturn;
	}
	
	/*
	public static Quebec getNull() 
		{ return null_object; }
	 */
	
	@Override
	public int size() 
		{ return ridings.size(); }


	@Override
	public boolean isEmpty() 
		{ return ridings.isEmpty(); }


	@Override
	public boolean containsKey(Object key) 
		{ return ridings.containsKey(key); }


	@Override
	public boolean containsValue(Object value) 
		{ return ridings.containsValue(value); }


	@Override
	public Riding get(Object key) 
		{ return ridings.get(key); }


	@Override
	public Riding put(String key, Riding value) 
		{ return ridings.put(key, value); }


	@Override
	public Riding remove(Object key) 
		{ return ridings.remove(key); }


	@Override
	public void putAll(Map<? extends String, ? extends Riding> m) 
		{ ridings.putAll(m); }


	@Override
	public void clear() 
		{ ridings.clear(); }


	@Override
	public Set<String> keySet() 
		{ return ridings.keySet(); }


	@Override
	public Collection<Riding> values() 
		{ return ridings.values(); }


	@Override
	public Set<Entry<String, Riding>> entrySet() 
		{ return ridings.entrySet(); }
	
	@Override
	public String toString()
	{
		String toReturn = "";
		
		for ( String keys : ridings.keySet() )
		{
			toReturn += keys + "\n";
		}
		
		return toReturn;
	}
}

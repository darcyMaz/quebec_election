package resource_handling;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import election_objects.Candidate;
import election_objects.Quebec;
import election_objects.Riding;

public class CreateRegionsData 
{
    /**
     *  Generates the data necessary, by region, that must be sent to the frontend.
     *  Reads the regions.json file to see which ridings are in which regions.
     *  Reads the hex_coords.json file to see the hex coords of each riding.
     */
    public static void execute()
    {

    }

    public static void create_regions_data(Quebec election_obj)
    {
		JSONObject regions_to_print = new JSONObject();	
		String regions_path = GetRelativePath.execute("regions.json").toString();

		// For each region, go thru the JSONArray and get the riding.
		// For each riding, get its info + its candidates and add them to an array.
		// Pack it all together into a JSON file.
		HashMap<String, JSONArray> region_hash_map = getRegionHashMap(regions_path);
	
		region_hash_map.forEach(
			(region,riding_array) -> {
				JSONObject region_json_obj = new JSONObject();

				riding_array.forEach((riding_) -> {
					String riding_str = (String) riding_;
					Riding riding = election_obj.get((String) riding_);
					JSONObject riding_json_obj;
					if (riding == null)
					{
						riding_json_obj = null;  // DANGEROUS CODE HERE WEEWOO WEEWOO
						System.out.println("Riding not found.");
					}
					else 
					{
						riding_json_obj = new JSONObject();
						riding_json_obj.put("Name", riding_str);
						riding_json_obj.put("Hex_Coords", new JSONArray());
						riding_json_obj.put("Party_Winner", getWinner(riding).getPartyAbreviation());

						JSONArray candidates_json_array = new JSONArray();
						riding.getCandidates().forEach((cand) -> 
						{
							JSONObject candidate_json_obj = new JSONObject();
							candidate_json_obj.put("Name", cand.getName());
							candidate_json_obj.put("Party", cand.getPartyAbreviation());
							candidate_json_obj.put("Vote_Total", cand.getVoteTotal());
							candidates_json_array.put(candidate_json_obj);
						});

						riding_json_obj.put("Candidates", candidates_json_array);
					}
					region_json_obj.put(riding_str, riding_json_obj);
				});
				
				regions_to_print.put(region, region_json_obj);
		});
		
		
		String regions_data_path = GetRelativePath.execute("regions_data.json").toString();

		try (FileWriter writer = new FileWriter(regions_data_path, false)) 
		{
			writer.write(regions_to_print.toString(4));
		} 
		catch (IOException e) {
			System.out.println("An error occurred while creating/writing to the file.");
			e.printStackTrace();
		}

    }

	private static HashMap<String, JSONArray> getRegionHashMap(String filename)
	{
		JSONObject regions_json = Get_JSONObject.execute(filename);
		JSONArray all_regions = regions_json.getJSONArray("regions");
		HashMap< String, JSONArray > region_hmap = new HashMap<>();
		all_regions.forEach((region) -> 
		{
			JSONObject _region = (JSONObject) region;
			String riding_name = _region.getString("region");
			JSONArray ridings = _region.getJSONArray("ridings");
			region_hmap.put(riding_name, ridings);
		});

		return region_hmap;
	}

    private static Candidate getWinner(Riding riding)
	{
		Candidate toReturn = null;
		int highest_vote_total = 0;

		for ( Candidate candidate : riding.getCandidates() )
		{
			if (candidate.getVoteTotal() > highest_vote_total)
			{
				highest_vote_total = candidate.getVoteTotal();
				toReturn = candidate;
			}
		}

		return toReturn;
	}
}

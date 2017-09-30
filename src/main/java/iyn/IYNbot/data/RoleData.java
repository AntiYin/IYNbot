package iyn.IYNbot.data;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class RoleData {

	//Transient = gson wont see this field when turning this class into a json
	/**Discord User ID*/
	public final String ID; 
	public String roleID = null;
	public String roleName = null;
	public Color color = new Color(0, 0, 0);
	
	public RoleData(String id) {
		ID = id;
	}
	
	public static RoleData getData(String id){
		File playerFolder = new File("./data/roles");
		playerFolder.mkdirs();
		File playerFile = new File(playerFolder, id + ".json");
		
		if(playerFile.exists()){
			Gson gson = new GsonBuilder().create();
			try {
				return gson.fromJson(new FileReader(playerFile), RoleData.class);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			RoleData data = new RoleData(id);
			data.save();
			return data;
		}
	}
	
	public void save(){
		File playerFolder = new File("./data/roles");
		playerFolder.mkdirs();
		File playerFile = new File(playerFolder, ID + ".json");
		if(!playerFile.exists()){
			try {
				playerFile.createNewFile();	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			Gson gson = new GsonBuilder().create();
			String json = gson.toJson(this);
			FileWriter writer = new FileWriter(playerFile);
			writer.write(json);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

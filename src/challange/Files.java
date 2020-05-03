package challange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.Gson;

/**
 * This class is responsable to save and update the json file to computer
 * @see java.io.File
 * @see java.io.FileWriter
 */
public class Files {
		
	public void createFile(String json) {
			File arquivo = new File("src\\challange\\answer.json");
			try {
				arquivo.createNewFile();
				FileWriter gravador = new FileWriter(arquivo);
				gravador.write(json);
				gravador.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public int readFile() {
		String aux = "";
		Data data = new Data();
		File arquivo = new File("src\\challange\\answer.json");
		try {
			Scanner reader = new Scanner(arquivo);
			while(reader.hasNextLine()) {
				 aux = reader.nextLine();
			}
			reader.close();
			Gson gson = new Gson();
			data = gson.fromJson(aux, Data.class);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data.numero_casas;
	}
	
	
	public void updateFile(String decodedText, String hashGenerated  ) {
		String aux = "";
		Data data = new Data();
		File arquivo = new File("src\\challange\\answer.json");
		
		//Read the json file
		try {
			Scanner reader = new Scanner(arquivo);
			while(reader.hasNextLine()) {
				 aux = reader.nextLine();
			}
			reader.close();
			Gson gson = new Gson();
			data = gson.fromJson(aux, Data.class);
			data.decifrado = decodedText;
			data.resumo_criptografico = hashGenerated;
			
			
			aux = gson.toJson(data);
			
			//Update the Json with the generated hash
			arquivo.createNewFile();
			FileWriter gravador = new FileWriter(arquivo);
			gravador.write(aux);
			gravador.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}

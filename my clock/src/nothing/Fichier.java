package nothing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;



public abstract class Fichier
{
	static final Path FICHIER_DE_CONFIGURATION = Paths.get("config.ini");
	

  public static boolean ecrireFichierConfiguration(String contenu, String parametre){
	  
	  
	  
	  BufferedWriter writer;
	try {
		
		
	
	  if(FICHIER_DE_CONFIGURATION.toFile().exists()&&FICHIER_DE_CONFIGURATION.toFile().canRead()){		 
			  
		  List<String> lignes = Files.readAllLines(FICHIER_DE_CONFIGURATION,StandardCharsets.UTF_16);

		  for(int i = 0; i < lignes.size(); i++)
		  {	
		  if(lignes.get(i).startsWith(parametre)){	
			  lignes.remove(i);
			
			  }

		  }
		  writer = Files.newBufferedWriter(FICHIER_DE_CONFIGURATION,StandardCharsets.UTF_16,
				  StandardOpenOption.TRUNCATE_EXISTING);
		  
		  writer.write(contenu);
		  writer.close();
		  
		  BufferedWriter writers = Files.newBufferedWriter(FICHIER_DE_CONFIGURATION,StandardCharsets.UTF_16,StandardOpenOption.APPEND);
		  for(int i = 0; i < lignes.size(); i++)		  {	
			  
			  writers.newLine();
			  writers.write(lignes.get(i));
			  
		  }
		  writers.close();
		  	return true;	
			 

	  }else{
		  BufferedWriter writers = Files.newBufferedWriter(FICHIER_DE_CONFIGURATION,StandardCharsets.UTF_16,
				  StandardOpenOption.CREATE);
		 writers.newLine();
			writers.write(contenu);
			writers.flush();
			writers.close();
		  return true;
	  }
	
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
		return false;
	}
	  
  }
  public static String lireFichierConfiguration(String parametre){
	 
		  BufferedReader reader = null;
		  try  {
			  reader = Files.newBufferedReader(FICHIER_DE_CONFIGURATION, StandardCharsets.UTF_16);
		  String line = null;
		  while ((line = reader.readLine()) != null) {
			  if(line.startsWith(parametre)){
				  return line.substring(parametre.length()+4, line.length()-2);
			  }
		  }
		  }catch(IOException e){
			  return null;
		  }finally{
			  try {
				reader.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return null;
			}
		  }
		return null;
  }
}
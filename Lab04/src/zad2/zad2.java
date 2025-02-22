package zad2;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
public class zad2 {
	JavaFXStarter javafx;
	static Set<String[]> questions;
	
	public zad2(JavaFXStarter javafx) {
		this.javafx = javafx;
		javafx.fooController.setMainApp(javafx);
		Gson gson = new Gson();
        try (Reader reader = new FileReader("C:\\Users\\Tobiasz\\eclipse-workspace\\Lab04\\src\\jsonfile.json")) {
            JsonFile json1 = gson.fromJson(reader, JsonFile.class);
            //generowanie pytan
            questions = generateRandom(json1);

           //zapisywanie pytan 
            String[] questionNames = getQuestionNames(questions);

            //ustawienie tego w java fx wyswietlenie  
            javafx.fooController.setQuestions(questionNames);
            
           
		        
		        
        } catch (IOException e){
            e.printStackTrace();
        }
		 	
 }
	


	  private String[] getQuestionNames(Set<String[]> questions) {
		  Iterator value = questions.iterator(); 
          String[] questionNames = new String[5];
          int next = 0 ;  
          while (value.hasNext()) { 
              
              String[] s = (String[]) value.next();
              for(int length = 0 ; length < s.length; length++)
              {
              	
              	if(length==0)
              		questionNames[next] = s[0];
              	
              }
              System.out.println(next);
              next++;
          } 
          return questionNames;
		
	}



	Set<String[]> generateRandom(JsonFile JsonFile)
	{
		Random rng = new Random(); // Ideally just create one instance globally
		int numbersNeeded = 5;
		Set<String[]> generated = new LinkedHashSet<String[]>();
		Set<String> names = new LinkedHashSet<String>();
		while (generated.size() < numbersNeeded)
		{
			
		    int next = rng.nextInt(5) + 1;
		    
		    
		    //zabeizpiecznie na brak powtorzen
		    if(!names.add(JsonFile.pytania[next][0]))
		    {
		     continue;
		    }
		    
		    int length = 0;
		    for(length = 0 ; length < JsonFile.pytania[next].length ; length++);
		    
		    String[] s = new String[length];
		    for(length = 0 ; length < JsonFile.pytania[next].length ; length++)
		    {
		    	s[length] = JsonFile.pytania[next][length];
		    }
		    
		    generated.add(s);
		}
		return generated;
	}

	
	static class  JsonFile {

		private String[][] pytania;
	
		JsonFile()
		{
			pytania = new String[5][2];
		}
    }
	
	
	
	
	
	protected static JsonFile createJsonFile(Map<String,String> answers) {

		 //get respond
		 Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 System.out.println(answers);
         JsonFile answer1 = createStaffObject(answers);
       
        
        //check respond with answers //leveinstein calc //get points
        //make json in it	
	        // Java objects to String
	        // String json = gson.toJson(staff);

	        // Java objects to File
        try (FileWriter writer = new FileWriter("C:\\Users\\Tobiasz\\eclipse-workspace\\Lab04\\src\\answers.json")) {
            gson.toJson(answer1, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       	return answer1;
	        
	
	}
	
	
	 protected static JsonFile createStaffObject(Map<String, String> answers) {

		
		JsonFile json = new JsonFile();
		int i = 0;
		for (Map.Entry<String,String> entry : answers.entrySet())  {
			json.pytania[i][0] = entry.getKey();
			json.pytania[i][1] = entry.getValue();
            i++;
		}
		

        return json;

	    }
	 
	 protected static double getScore (JsonFile answer1) {
		 
		 	//compare user answers with answers from json - json:qestions
		    Iterator value = questions.iterator(); 
		    String[] questionNames = new String[5];
			int next = 0 ;  
			double score = 0;
			LevensteinCalc comparatorLeveinstein = new LevensteinCalc();
			int questionNumber = 0;
			while(questionNumber < 5 )
			{
				
			              String[] s = (String[]) value.next();
			              
			              
			              for(int l = 0 ;l < 5; l++ ){
			            	  
			            	  if(answer1.pytania[l][0] == s[0]){
			            		  
					              for(int length = 1 ; length < s.length; length++){
			
					            	  	System.out.println(s[length] + " " +answer1.pytania[l][1]);
					            	 
					            		  if(comparatorLeveinstein.Calc(s[length], answer1.pytania[l][1])==1){
					            			  score+=0.5;
					            		  }
					            		  else if(comparatorLeveinstein.Calc(s[length], answer1.pytania[l][1])==0.00)
					            		  {
					            			  score+=1;
					            			  break;
					            		  }
					      			  
					              }
					              break;
			            	  }
		      			  }
			             
			          
			              questionNumber++;
				
			}
			

	        return score;

		    }

}



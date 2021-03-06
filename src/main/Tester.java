 package main;

import java.util.Arrays;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import piazza.APiazzaClass;
import piazza.APiazzaClassWithDiaries;
import piazza.APiazzaClassWithDiaries_2;
import piazza.APiazzaClassWithDiaries_3;
import piazza.APiazzaClassWithDiaries_TA;
import piazza.APiazzaClassWithDiaries_Yicheng;
import piazza.LoginFailedException;
import piazza.NotLoggedInException;
import piazza.PiazzaClass;

public class Tester {
	
	/* Regular grading involves grading the newest set of diary entries within the current grading period by
	 * posting a followup post to each students' diary and generating summary and detailed .csv files
	 * Update csv is used mainly in the case where there were manual changes made to the diary grades from directly and manually
	 * changing student diary grades from within the followup post on Piazza. In this case, only an updated summary .csv file is generated
	 */
	public enum Method {
		REGULAR_GRADING_WITH_CSV, UPDATE_CSV_FROM_PIAZZA;
	}
	
	public static void main(String[] argv) throws ClientProtocolException, IOException, LoginFailedException, NotLoggedInException {
		
		BufferedReader configReader = new BufferedReader(new FileReader("config.json"));
		
		String text = "";
		String line = configReader.readLine();
		
		while (line != null) {
			text = text + line;
			line = configReader.readLine();
		}
		
		JSONObject config = new JSONObject(text);
		String email = config.getString("email");
		String password = config.getString("password");
		String classID = config.getString("class_id");
		
		
		String filePath = "/Path/To/Where/File/Is/Saved";
		String contactName = "one of the TAs";
		
		APiazzaClassWithDiaries_3 comp533 = new APiazzaClassWithDiaries_3(email, password, classID, contactName);
		
		// Set the method to the desired operation. See the Method enum declaration for details
		comp533.setMethod(Method.REGULAR_GRADING_WITH_CSV);
		comp533.generateDiaryGradesCSV(filePath);
		
		System.out.println("DONE!");
		
		configReader.close();
	}

}

package Scouting;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;


public class GetData {
	final static String basehttpsURL = "https://www.thebluealliance.com/api/v3";
	final static String year = "2018";
	//{teamkey}=frc{team_number}
	
	
	
	//THESE ARE THE COMMENTS THAT TELL YOU HOW THIS THING WORKS:
	//BASICALLY THERE ARE TWO METHODS THAT MAKE GETDATA WORK
	//1. getthing(String ming)
	//		THIS TAKES THE STRING FROM THE BLUE ALLIANCE API PAGE
	//		https://www.thebluealliance.com/apidocs/v3
	//		AND RETURNS THE ENTIRE JSON DATA PASSED BACK FROM THE WEBPAGE AS A WHOLE STRING
	// 
	//2. geththingsfromthing(Sring gottonthing, String hingtogetfromthinggotton)
	//		THIS TAKES THE FULL JSON DATA PASSED FROM THE gething METHOD IN THE gottonthing PARAMETER
	// 		IT ALSO TAKES A STRING THAT LABELS WHICH PEICE OF DATA YOU WANT PARSED FROM THE JSON FILE
	// 		THEN IT RETURNS ALL PEICES OF DATA IN THAT JSON FILE YOU PASSED THAT WERE LABELED IN THE thingtogetfromthinggotton PARAMETER
	public static void main(String[] a) {
		for (String r : getEventsforYear(year)){
			System.out.println(r);
		}
	}
	
	static ArrayList<String> getTeamNames(String eventkey) {
		ArrayList<String> teamNames = getthingsfromthing(getthing("/event/2016wvrox/teams"), "nickname");
		return teamNames;
	}
	static ArrayList<String> getTeamKeys(String eventkey) {
		ArrayList<String> teamNames = getthingsfromthing(getthing("/event/2016wvrox/teams"), "key");
		return teamNames;
	}
	static ArrayList<String> getTeamNumbers(String eventkey) {
		ArrayList<String> teamNames = getthingsfromthing(getthing("/event/2016wvrox/teams"), "team_number");
		return teamNames;
	}
	
	
	
	
	
	static ArrayList<String> getEventsforTeam(String teamkey) {
		ArrayList<String> teamNames = getthingsfromthing(getthing("/team/"+teamkey+"/events"), "key");
		return teamNames;
	}
	static ArrayList<String> getEventsforYear(String year) {
		ArrayList<String> teamNames = getthingsfromthing(getthing("/events/"+year), "key");
		return teamNames;
	}	
	static ArrayList<String> getthingsfromthing(String gottonthing, String thingtogetfromthinggotton) {
		ArrayList<String> allthings = new ArrayList<String>();
		thingtogetfromthinggotton = "\""+thingtogetfromthinggotton+"\"";
		while(gottonthing.indexOf(thingtogetfromthinggotton)!=-1) {
			gottonthing=gottonthing.substring(gottonthing.indexOf(thingtogetfromthinggotton)+thingtogetfromthinggotton.length()+3);
			allthings.add(gottonthing.substring(0, gottonthing.indexOf(",")));
		}
		for(int i=0;i<allthings.size();i++) {
			for(int j=0;j<allthings.get(i).length();j++) {
				if(allthings.get(i).substring(j, j+1).equals("\"")) {
					allthings.set(i, allthings.get(i).substring(0, j)+allthings.get(i).substring(j+1));
				}
			}
		}
		return allthings;
	}
	static String getthing(String accessurl) {
		String returnvalue="";
		try {
			URL myurl = new URL(basehttpsURL+accessurl);
			HttpsURLConnection con = (HttpsURLConnection)myurl.openConnection();
			con.setRequestProperty("X-TBA-Auth-Key", "VgROWHnjISDEo7guDFThY9jdaCU6ewKhlutN80JpIdMNYGVNP9pg7xyq5xwYOB9o"); 
			con.setRequestProperty("User-Agent", ""); 

			DataInputStream input;
			input = new DataInputStream(con.getInputStream());
			for( int c = input.read(); c != -1; c = input.read() ) 
				returnvalue+=(char)c; 
			input.close(); 

			} catch (Exception e) {
				e.printStackTrace();
			}

		return returnvalue;
	}
}

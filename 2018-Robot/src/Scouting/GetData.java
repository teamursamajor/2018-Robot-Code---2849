package Scouting;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
public class GetData {
	//TODO This entire file is riddled with terrible naming conventions and difficult to understand code. Let's redo it
	
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
	//3. getstufftoget (String gottonthing)
	//		THIS ALSO TAKES THE GET THING AS FIRST AND ONLY PARAMETER, RETURNS AN ARRAY OF EVERYTHING LEGAL TO PASS TO getthingsfromthing that is not null
	
	
	//rank, record
	//ranking points, match points, auto points, other categories of points in game menu; average, total
	//whether or not they were picked for elimination last event
	//OPR, DPR
	
	public static void main(String[] a) {
		//do thing for events that is already done for teams
		
		
		
		
		String EVENTKEY = "2016wvrox";//for testing change to use for real
		String got = getthing("/event/2016wvrox/teams");
		System.out.println(got);
//		for (String r : getstufftoget(got)){
//			System.out.println(r);
//		}
//		System.out.println(got);
//		ArrayList<String> teamnames = getTeamNames(got,"2016wvrox");
//		ArrayList<String> teamkeys = getTeamKeys(got,"2016wvrox");
//		ArrayList<String> teamnumbers = getTeamNumbers(got,"2016wvrox");
//		for(int i=0;i<teamnames.size();i++) {
//			System.out.println(i+new String(new char[Math.abs(2-(int)Math.log10(i==0?1:i))]).replace('\0', ' ')+"] "+teamnames.get(i)+new String(new char[Math.abs(35-teamnames.get(i).length())]).replace('\0', ' ')+teamnumbers.get(i));
////			System.out.println(teamkeys.get(i));
//		}
//		
//		System.out.println("Which Team would you like to see more of?");
//		int teamnumberchosen;
//		Scanner reader = new Scanner(System.in);
//		do {
//			
//			teamnumberchosen = reader.nextInt();
//			System.out.println(teamnumberchosen);
//		}while(!(teamnumbers.contains(""+teamnumberchosen)));
//		reader.close();
//		int nthteamchosen = teamnumbers.indexOf(""+teamnumberchosen);
//		System.out.println("Youhaveselected"+ teamnames.get(nthteamchosen));
//		System.out.println(getthing("/team/"+teamkeys.get(nthteamchosen)));
		System.out.println(getthing("/team/"+"frc2849"));
	}
	
	static ArrayList<String> getTeamNames(String gotton, String eventkey) {//gotton=getthing("/event/2016wvrox/teams")
		ArrayList<String> teamNames = getthingsfromthing(gotton, "nickname");
		return teamNames;
	}
	static ArrayList<String> getTeamKeys(String gotton,String eventkey) {//gotton=getthing("/event/2016wvrox/teams")
		ArrayList<String> teamNames = getthingsfromthing(gotton, "key");
		return teamNames;
	}
	static ArrayList<String> getTeamNumbers(String gotton,String eventkey) {//gotton=getthing("/event/2016wvrox/teams")
		ArrayList<String> teamNames = getthingsfromthing(gotton, "team_number");
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
			gottonthing=gottonthing.substring(gottonthing.indexOf(thingtogetfromthinggotton)+thingtogetfromthinggotton.length()+2);
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
	static ArrayList<String> getstufftoget(String gottonthing){
		ArrayList<String> legalparameters = new ArrayList<String>();
		gottonthing=gottonthing.substring(gottonthing.indexOf("{")+1, gottonthing.indexOf("}"));
		
		//THE LINE OF CODE ABOVE DOESN"T WORK BECAUSE THERE IS A SECOND PAIR OF CURLY BRACES INSIDE THE ONE I WANT
		
		
		for(int j=0;j<gottonthing.length();j++) {
			if(gottonthing.substring(j, j+1).equals("\"")) {
				gottonthing=gottonthing.substring(0, j)+gottonthing.substring(j+1);
			}
			if(gottonthing.substring(j, j+1).equals("{")) {
				gottonthing=gottonthing.substring(0, j)+gottonthing.substring(gottonthing.indexOf("}"));
			}
		}
		while(gottonthing.indexOf(":")!=-1) {
			legalparameters.add(gottonthing.substring(1,gottonthing.indexOf(":")-2));
			gottonthing=gottonthing.substring(gottonthing.indexOf(",")+1);
		}
		return legalparameters;
	}
}

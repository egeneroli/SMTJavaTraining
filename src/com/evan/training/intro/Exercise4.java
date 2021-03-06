/**
 * 
 */
package com.evan.training.intro;

import java.util.TreeMap;

/****************************************************************************
* <b>Title</b>: Exercise4.java
* <b>Project</b>: SMTJavaTraining
* <b>Package</b>: com.evan.training.intro
* <b>Description: </b> Creates map of states/state FIPS codes 
* 
* @author egeneroli
* @version 1.0
* @since Dec 14, 2021
* @updates:
****************************************************************************/
public class Exercise4 {
	
	public static void main(String[] args) {
		int[] stateCodes = {1,2,4,5,6,8,9,10,12,13,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,44,45,46,47,48,49,50,51,53,54,55,56,60,66,69,72,78};
		String[] stateNames = {"Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Carolina","North Dakota","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming","American Samoa","Guam","Northern Mariana Islands","Puerto Rico","Virgin Islands"};
		
		TreeMap<String,Integer> map = new TreeMap<String,Integer>();
		for(int i = 0; i < stateCodes.length; i++) {
			map.put(stateNames[i], stateCodes[i]);		
		}
		
		System.out.println(map);
		System.out.println(map.descendingMap());
		
		//for (String key: map.keySet()) {
		//	System.out.println(key + ": " + map.get(key));
		//}
		
	}
	
	
	
	
	
}

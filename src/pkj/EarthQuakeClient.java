package pkj;

import java.util.ArrayList;

public class EarthQuakeClient {

	public EarthQuakeClient() {
		
	}

    public void dumpCSV(ArrayList<QuakeEntry> list){
    	System.out.println("Latitude,Longitude,Magnitude,Info");
    	for(QuakeEntry qe: list) {
    	    System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
    	    		qe.getLocation().getLatitude(),
    	    		qe.getLocation().getLongitude(),
    	    		qe.getMagnitude(),
    	    		qe.getInfo());
    	}
    }
    
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        
        ArrayList<QuakeEntry> listfiltered = filterByMagnitude(list,5.0);
    	for(QuakeEntry qe : listfiltered) {
    		System.out.println(qe);
    	}
    	System.out.println("Found " + listfiltered.size() + " that match that criteria");
    }
    
    public void createCSV() {
    	EarthQuakeParser ep = new EarthQuakeParser();
    	String source = "nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    	ArrayList<QuakeEntry> list = ep.read(source);
    	dumpCSV(list);
    	System.out.println("# quakes read: " + list.size());
    	for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
//        String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        Location Durham = new Location(35.988, -78.907);
        Location Bridgeport = new Location(38.17, -118.82);
        ArrayList<QuakeEntry> distancefiltered = filterByDistanceFrom(list, 1000, Bridgeport);
        for(QuakeEntry qe : distancefiltered) {
        	double distance = qe.getLocation().distanceTo(Bridgeport);
        	System.out.println(distance/1000 + " " + qe.getInfo());
        }
        System.out.println("Found " + distancefiltered.size() +" quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> list, double magnitude){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe : list) {
        	if(qe.getMagnitude()>magnitude) {
        		answer.add(qe);
        	}
    	}
    	return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> list, double distMax,Location from){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe:list) {
    		double distance = qe.getLocation().distanceTo(from);
    		distance = distance/1000;
    		if(distance < distMax) {
    			answer.add(qe);
    		}
    	}
    	return answer;
    }
    
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> list, double minDepth, double maxDepth){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe : list) {
        	if(minDepth < qe.getDepth() && qe.getDepth() < maxDepth) {
        		answer.add(qe);
        	}
    	}
    	return answer;
    }
    
    public void quakesOfDepth() {
    	EarthQuakeParser parser = new EarthQuakeParser();
//      String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
      String source = "nov20quakedata.atom";
      ArrayList<QuakeEntry> list  = parser.read(source);
      System.out.println("read data for "+list.size()+" quakes");
      
      double minDepth = -4000.0;
      double maxDepth = -2000.0;
      
      ArrayList<QuakeEntry> depthFiltered  = filterByDepth(list, minDepth, maxDepth);
      System.out.println("Find quakes with depth between " + minDepth +" and " + maxDepth);
      
      for(QuakeEntry qe : depthFiltered) {
    	  System.out.println(qe);
      }
      System.out.println("Found " + depthFiltered.size() +" quakes that match that criteria");
    }
    
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> list, String where, String phrase){
    	ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
    	for(QuakeEntry qe : list) {
    		String title = qe.getInfo();
    		if(where.equalsIgnoreCase("start")) {
    			if(title.startsWith(phrase)) {
    				answer.add(qe);
    			}
    		}
    		else if(where.equalsIgnoreCase("any")) {
    			if(title.contains(phrase)){
    				answer.add(qe);
    			}
    		}
    		else if(where.equalsIgnoreCase("end")) {
    			if(title.endsWith(phrase)){
    				answer.add(qe);
    			}
    		}
    	}
    	return answer;
    }
    
    public void quakesByPhrase() {
    	EarthQuakeParser parser = new EarthQuakeParser();
//      String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
      String source = "nov20quakedata.atom";
      ArrayList<QuakeEntry> list  = parser.read(source);
      System.out.println("read data for "+list.size()+" quakes");
      
      String phrase = "Can";
      String where = "any";
      
      ArrayList<QuakeEntry> phraseFiltered  = filterByPhrase(list, where, phrase);
      
      for(QuakeEntry qe : phraseFiltered) {
    	  System.out.println(qe);
      }
      System.out.println("Found " + phraseFiltered.size() +" quakes that match " + phrase + " at " + where);
    }
}
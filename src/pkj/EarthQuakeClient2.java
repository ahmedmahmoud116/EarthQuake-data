package pkj;

import java.util.ArrayList;

public class EarthQuakeClient2 {

	public EarthQuakeClient2() {
		
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
	 
	 public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> list,Filter f){
		 ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		 for(QuakeEntry qe: list) {
			 if(f.satisfies(qe)) {
				 answer.add(qe);
			 }
		 }
		 return answer;
	 }
	 
	 public void quakesWithFilter() {
		 EarthQuakeParser parser = new EarthQuakeParser();
	     //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	     String source = "nov20quakedata.atom";
	     ArrayList<QuakeEntry> list = parser.read(source);
	     
	     double minMag = 3.5;
	     double maxMag = 4.5;
	     String namefilter = "MagnitudeFilter";
	     
	     Filter f = new MagnitudeFilter(minMag,maxMag,namefilter);
	     
	     ArrayList<QuakeEntry> filteredlist = filter(list, f);
	     
	     double minDepth = -55000.0;
	     double maxDepth = -20000.0;
	     namefilter = "DepthFilter";
	     
	     f = new DepthFilter(minDepth, maxDepth, namefilter);
	     
	     ArrayList<QuakeEntry> filteredlist2 = filter(filteredlist, f);
	     
	     Location loc = new Location(39.7392, -104.9903);
	     double distance = 1000000;
	     namefilter = "DistanceFilter";
	     
	     f = new DistanceFilter(loc, distance, namefilter);
	     
	     ArrayList<QuakeEntry> filteredlist3 = filter(list, f);
	     
	     String where = "end";
	     String phrase = "a";
	     namefilter = "PhraseFilter";
	     
	     f = new PhraseFilter(where, phrase , namefilter);
	     
	     ArrayList<QuakeEntry> filteredlist4 = filter(filteredlist3, f);
	     
	     
	     for(QuakeEntry qe : filteredlist2) {
	    	 System.out.println(qe);
	     }
	     System.out.println("The number of Earthquakes found: " + filteredlist2.size());
	 }
	 
	 public void testMatchAllFilter() {
		 
		 EarthQuakeParser parser = new EarthQuakeParser();
	     //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	     String source = "nov20quakedata.atom";
	     ArrayList<QuakeEntry> list = parser.read(source);
	     System.out.println("read data for "+list.size()+" quakes");
	     
	     MatchAllFilter maf = new MatchAllFilter();
	     
	     double minMag = 1.0;
	     double maxMag = 4.0;
	     String namefilter = "MagnitudeFilter";
	     
	     Filter f = new MagnitudeFilter(minMag,maxMag, namefilter);
	     maf.addFilter(f);
	     
	     double minDepth = -180000.0;
	     double maxDepth = -30000.0;
	     namefilter = "DepthFilter";
	     
	     f = new DepthFilter(minDepth, maxDepth, namefilter);
	     maf.addFilter(f);
	     
	     String where = "any";
	     String phrase = "o";
	     namefilter = "PhraseFilter";
	     
	     f = new PhraseFilter(where, phrase, namefilter);
	     maf.addFilter(f);
	     
	     ArrayList<QuakeEntry> filteredlist = filter(list, maf);
	     
	     for(QuakeEntry qe : filteredlist) {
	    	 System.out.println(qe);
	     }
	     System.out.println("The number of Earthquakes found: " + filteredlist.size());
	 }
	 
	 public void testMatchAllFilter2() {
		 
		 EarthQuakeParser parser = new EarthQuakeParser();
	     //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	     String source = "nov20quakedata.atom";
	     ArrayList<QuakeEntry> list = parser.read(source);
	     System.out.println("read data for "+list.size()+" quakes");
	     
	     MatchAllFilter maf = new MatchAllFilter();
	     
	     double minMag = 0.0;
	     double maxMag = 5.0;
	     String namefilter = "MagnitudeFilter";
	     
	     Filter f = new MagnitudeFilter(minMag,maxMag, namefilter);
	     maf.addFilter(f);
	     
	     Location loc = new Location(55.7308, 9.1153);
	     double distance = 3000000;
	     namefilter = "DistanceFilter";
	     
	     f = new DistanceFilter(loc, distance, namefilter);
	     maf.addFilter(f);
	     
	     String where = "any";
	     String phrase = "e";
	     namefilter = "PhraseFilter";
	     
	     f = new PhraseFilter(where, phrase, namefilter);
	     maf.addFilter(f);
	     
	     ArrayList<QuakeEntry> filteredlist = filter(list, maf);
	     
	     for(QuakeEntry qe : filteredlist) {
	    	 System.out.println(qe);
	     }
	     System.out.println("The number of Earthquakes found: " + filteredlist.size());
	     
	     System.out.println("Filters used are: " + maf.getName());
	 }
}

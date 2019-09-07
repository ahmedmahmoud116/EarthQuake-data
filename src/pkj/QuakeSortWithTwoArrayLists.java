package pkj;

import java.util.ArrayList;

public class QuakeSortWithTwoArrayLists {

	public QuakeSortWithTwoArrayLists() {
		
	}
	
	public QuakeEntry getSmallestMagnitude(ArrayList<QuakeEntry> list) {
		QuakeEntry minimum = list.get(0);
		for(QuakeEntry qe: list) {
			if(minimum.getMagnitude()> qe.getMagnitude())
			{
				minimum = qe;
			}
		}
		return minimum;
	}
	
	public ArrayList<QuakeEntry> sortByMagnitude(ArrayList<QuakeEntry> in){
		ArrayList<QuakeEntry> out  = new ArrayList<QuakeEntry>();
		while(!in.isEmpty()) {
			QuakeEntry min = getSmallestMagnitude(in);
			in.remove(min);
			out.add(min);
		}
		return out;
	}
	
	public void testSort() {

		EarthQuakeParser ep = new EarthQuakeParser();
    	String source = "nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    	ArrayList<QuakeEntry> list = ep.read(source);
    	
    	list = sortByMagnitude(list);
    	
    	System.out.println("# quakes read: " + list.size());
    	for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
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
}

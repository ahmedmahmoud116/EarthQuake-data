package pkj;

import java.util.ArrayList;

public class QuakeSortInPlace_BubbleSort {

	public QuakeSortInPlace_BubbleSort() {
		
	}
	
	 public void testSort() {
	        EarthQuakeParser parser = new EarthQuakeParser(); 
	        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	        String source = "earthQuakeDataWeekDec6sample1.atom";
	        ArrayList<QuakeEntry> list  = parser.read(source);  
	       
	        System.out.println("read data for "+list.size()+" quakes");    
	        
	        int pass = sortByMagnitudeWithBubbleSort(list);
	        
	        for (QuakeEntry qe: list) { 
	            System.out.println(qe);
	        }
	        System.out.println("Pass " + pass);
	        
	 }
	
	public boolean onePassBubbleSort(ArrayList<QuakeEntry> list,int numSorted){
		boolean swapf = false;
		for(int i = 0;i<list.size()-numSorted-1;i++) {
			if(list.get(i).getMagnitude()>list.get(i+1).getMagnitude()) {
				swapf = true;
				swap(list,i,i+1);
			}
		}
		if(swapf)
			return true;
		return false;
	}
	
	public void swap(ArrayList<QuakeEntry> list,int firstIndx,int secondIndx) {
		QuakeEntry qe = list.get(firstIndx);
		QuakeEntry min = list.get(secondIndx);
		list.set(firstIndx, min);
		list.set(secondIndx, qe);
	}
	
	public int sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> list) {

//		for (QuakeEntry qe: list) { //to print the quakes before being sorted
//            System.out.println(qe);
//        }
		int i;
		for(i =0;i<list.size(); i++) {
			boolean check = onePassBubbleSort(list, i);
			System.out.println("Printing Quakes after pass " + i);
//			for (QuakeEntry qe: list) { //to print the quakes after each pass
//	            System.out.println(qe);
//	        }
			if(!check)
				break;
		}
		return i;
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

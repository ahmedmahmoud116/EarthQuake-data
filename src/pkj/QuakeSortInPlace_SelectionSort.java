package pkj;

import java.util.ArrayList;

public class QuakeSortInPlace_SelectionSort {

	public QuakeSortInPlace_SelectionSort() {
		
	}
	
	public int getSmallestMagnitude(ArrayList<QuakeEntry> list,int from) {
		
		int min = from;
		for(int i = from+1 ; i<list.size() ; i++) {
			if(list.get(i).getMagnitude() < list.get(min).getMagnitude()) {
				min = i;
			}
		}
		return min;
	}
	
	public void sortByMagnitude(ArrayList<QuakeEntry> list) {
		
		for(int  i = 0 ; i<list.size() ; i++) {
			int minIndx = getSmallestMagnitude(list, i);
			swap(list, minIndx, i);
		}
	}
	
	public void swap(ArrayList<QuakeEntry> list,int firstIndx,int secondIndx) {
		QuakeEntry qe = list.get(firstIndx);
		QuakeEntry min = list.get(secondIndx);
		list.set(firstIndx, min);
		list.set(secondIndx, qe);
	}
	
	 public void testSort() {
	        EarthQuakeParser parser = new EarthQuakeParser(); 
	        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
	        String source = "earthQuakeDataWeekDec6sample1.atom";
	        ArrayList<QuakeEntry> list  = parser.read(source);  
	       
	        System.out.println("read data for "+list.size()+" quakes");    
//	        sortByMagnitude(list);
//	        sortByLargestDepth(list);
	        int pass =  sortByMagnitudeWithCheck(list);
	        for (QuakeEntry qe: list) { 
	            System.out.println(qe);
	        }
	    	System.out.println("Pass " + pass);
	        
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
	 
	 public int getLargestDepth(ArrayList<QuakeEntry> list,int from) {
			
			int max = from;
			for(int i = from+1 ; i<list.size() ; i++) {
				if(list.get(i).getDepth() > list.get(max).getDepth()) {
					max = i;
				}
			}
			return max;
	}
	 
	 public void sortByLargestDepth(ArrayList<QuakeEntry> list) {
			
			for(int  i = 0 ; i<50 ; i++) {
				int maxIndx = getLargestDepth(list, i);
				swap(list, maxIndx, i);
			}
	}
	 
	 public int sortByMagnitudeWithCheck(ArrayList<QuakeEntry> list) {
			int i;
			for(i = 0 ; i<list.size() ; i++) {
				
				if(checkInSortedOrder(list))
					break;
				
				int minIndx = getSmallestMagnitude(list, i);
				if(minIndx == i)
					continue;
				swap(list, minIndx, i);
			}
			return i;
	}
	 
	public boolean checkInSortedOrder(ArrayList<QuakeEntry> list) {
		for(int i =0;i < list.size()-1 ; i++) {
			if(list.get(i).getMagnitude() > list.get(i+1).getMagnitude())
				return false;
		}
		return true;
	}
}

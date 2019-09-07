package pkj;

import java.util.ArrayList;

public class LargestQuakes {
	
	public LargestQuakes() {
		
	}
	
	public void findLargestQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
//      String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
      String source = "nov20quakedata.atom";
      ArrayList<QuakeEntry> list  = parser.read(source);
      
//      for(QuakeEntry qe: list) {
//    	  System.out.println(qe);
//      }
      
      System.out.println("read data for "+list.size()+" quakes");
      
      int indexMax = indexOfLargest(list);
      System.out.println("The max index in the list: " + indexMax);
      System.out.println("The max Magnitude: " + list.get(indexMax));
      
      System.out.println();
      
      ArrayList<QuakeEntry> largestmag = getLargest(list, 50);
      System.out.println("Largest magnitudes: ");
      for(QuakeEntry qe: largestmag) {
    	  System.out.println(qe);
      }
      System.out.println("Size of largest magnitude: " + largestmag.size());
	}
	
	public int indexOfLargest(ArrayList<QuakeEntry> list) {
		int max = 0;
		for(int i = 1 ; i<list.size(); i++) {
			QuakeEntry entry = list.get(i);
			if(entry.getMagnitude() > list.get(max).getMagnitude()) {
				max = i;
			}
		}
		return max;
	}
	
	public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> list, int howMany) {
		
		ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(list);
		
		for(int i = 0 ;i<howMany; i++){
			int indexofmax = indexOfLargest(copy);
			answer.add(copy.get(indexofmax));
			copy.remove(indexofmax);
		}
		return answer;
	}
}

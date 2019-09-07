package pkj;

import java.util.ArrayList;
import java.util.Collections;

public class DifferentSorters {

	public DifferentSorters() {
		
	}
	
	public void sortWithComplareTo() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "earthQuakeDataWeekDec6sample2.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        Collections.sort(list);
        System.out.println("read data for " + list.size());
        
        for(QuakeEntry qe: list) {
        	System.out.println(qe);
        }
        
        int quakeNumber = 600;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
	}
	
	public void sortByMagnitude() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        Collections.sort(list,new MagnitudeComparator());
        
        System.out.println("read data for " + list.size());
        
        for(QuakeEntry qe: list) {
        	System.out.println(qe);
        }
	}
	
	public void sortByDistance() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        Location where = new Location(35.9886, -78.9072);
        Collections.sort(list,new DistanceComparator(where));
        
        System.out.println("read data for " + list.size());
        
        for(QuakeEntry qe: list) {
        	System.out.println(qe);
        }
	}
	
	public void sortByTitleAndDepth() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        Collections.sort(list,new TitleAndDepthComparator());
        
        System.out.println("read data for " + list.size());
        
        for(QuakeEntry qe: list) {
        	System.out.println(qe);
        }
        
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
	}
	
	public void sortByLastWordInTitleThenByMagnitude() {
		EarthQuakeParser parser = new EarthQuakeParser();
        String source = "earthQuakeDataWeekDec6sample1.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list = parser.read(source);
        
        Collections.sort(list,new TitleLastAndMagnitudeComparator());
        
        System.out.println("read data for " + list.size());
        
        for(QuakeEntry qe: list) {
        	System.out.println(qe);
        }
        
        int quakeNumber = 500;
        System.out.println("Print quake entry in position " + quakeNumber);
        System.out.println(list.get(quakeNumber));
	}
}

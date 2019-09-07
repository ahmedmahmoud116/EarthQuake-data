package pkj;

import java.util.ArrayList;

public class ClosestQuakes {

	public ClosestQuakes() {
		
	}
	
	public ArrayList<QuakeEntry> getClosest(ArrayList<QuakeEntry> list,Location current,int howMany){
		ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(list);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        
        for(int j = 0;j<howMany;j++) {
        	int minindex = 0;
        	for(int i = 1; i<copy.size();i++) {
        		QuakeEntry entry = copy.get(i);
        		Location loc = entry.getLocation();
        		if(current.distanceTo(loc) < current.distanceTo(copy.get(minindex).getLocation())) {
        			minindex = i;
        		}
        	}
        	ret.add(copy.get(minindex));
        	copy.remove(minindex);
        }
        return ret;
	}
	public void findClosestQuakes() {
		EarthQuakeParser parser = new EarthQuakeParser();
    	String source = "nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
    	ArrayList<QuakeEntry> list = parser.read(source);
    	
        System.out.println("read data for "+list.size());

        Location jakarta  = new Location(-6.211,106.845); //location of jakarte in indonesia
        ArrayList<QuakeEntry> closest = getClosest(list, jakarta, 3);
        for(QuakeEntry qe: closest) {
        	double distanceinMeters = qe.getLocation().distanceTo(jakarta);
        	System.out.printf("%4.2f /t %s\n",distanceinMeters/1000,qe);
        }
        System.out.println("number found: " + closest.size());
	}
}

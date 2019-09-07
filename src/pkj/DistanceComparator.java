package pkj;

import java.util.Comparator;

public class DistanceComparator implements Comparator<QuakeEntry>{
	
	private Location mylocation;
	public DistanceComparator(Location location) {
		this.mylocation = location;
	}
	
	@Override
	public int compare(QuakeEntry q1,QuakeEntry q2) {
		return Double.compare(mylocation.distanceTo(q1.getLocation()), mylocation.distanceTo(q2.getLocation()));
	}

}

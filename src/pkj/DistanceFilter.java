package pkj;

public class DistanceFilter implements Filter{

	private Location myLocation;
	private double maxDistance;
	private String name;
	
	public DistanceFilter(Location loc,double distance,String nameFilter) {
		this.myLocation = loc;
		this.maxDistance = distance;
		this.name = nameFilter;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {//return true if a QuakeEntry’s distance from the given 
											 //location is less than the specified maximum distance

		return (qe.getLocation().distanceTo(myLocation)<= maxDistance);
	}

	@Override
	public String getName() {
		return this.name;
	}

}

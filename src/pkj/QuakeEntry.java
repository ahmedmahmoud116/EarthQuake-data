package pkj;

public class QuakeEntry implements Comparable<QuakeEntry>{
	
	private Location mylocation;
	private String title;
	private double depth;
	private double magnitude;
	
	public QuakeEntry(String tit,double lat,double lon,double mag,double d){
		this.mylocation = new Location(lat,lon);
		this.depth = d;
		this.magnitude = mag;
		this.title = tit;
	}
	
	public Location getLocation() {
		return mylocation;
	}
	
	public String getInfo() {
		return title;
	}
	
	public double getDepth() {
		return depth;
	}
	
	public double getMagnitude() {
		return magnitude;
	}
	
	@Override
	public String toString() {
		return String.format("(%3.2f, %3.2f), mag = %3.2f, depth = %3.2f, title = %s", mylocation.getLatitude(),
				mylocation.getLongitude(),magnitude,depth,title);
	}

//	@Override
//	 public int compareTo(QuakeEntry loc) {
//        double difflat = mylocation.getLatitude() - loc.mylocation.getLatitude();
//        if (Math.abs(difflat) < 0.001) { // if they are nearly equal in latitiude
//            double diff = mylocation.getLongitude() - loc.mylocation.getLongitude(); // we compare by longitude
//            if (diff < 0) return -1; //return -1 if it is smaller
//            if (diff > 0) return 1; //return 1 if it is larger
//            return 0; // return 0 if they are equal
//        }
//        if (difflat < 0) return -1;
//        if (difflat > 0) return 1;
//
//        // never reached
//        return 0;
//    }
	
	@Override
	public int compareTo(QuakeEntry qe) {//compare by magnitude and to break ties compare by depth
		int check = Double.compare(magnitude, qe.getMagnitude());
		if(check == 0) {
			return Double.compare(depth, qe.getDepth());
		}
		return check;
	}
}

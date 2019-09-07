package pkj;

public class MagnitudeFilter implements Filter{

	private double minMag;
	private double maxMag;
	private String nameFilter;
	
	public MagnitudeFilter(double min,double max,String nameFilter) {
		this.minMag = min;
		this.maxMag = max;
		this.nameFilter = nameFilter;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		
		return (maxMag >= qe.getMagnitude() && qe.getMagnitude()>= minMag);
	}

	@Override
	public String getName() {
		return nameFilter;
	}
	
}

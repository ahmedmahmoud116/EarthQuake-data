package pkj;

public class MinMagFilter implements Filter{

	private double magMin;
	private String name;
	
	public MinMagFilter(double mag,String nameFilter) {
		this.magMin = mag;
		this.name = nameFilter;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {
	
		return qe.getMagnitude() >= this.magMin;
	}

	@Override
	public String getName() {
		
		return name;
	}
}

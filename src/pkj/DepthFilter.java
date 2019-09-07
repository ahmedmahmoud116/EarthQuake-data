package pkj;

public class DepthFilter implements Filter{

	private double minDepth;
	private double maxDepth;
	private String nameFilter;
	
	public DepthFilter(double min,double max,String nameFilter) {
		this.minDepth = min;
		this.maxDepth = max;
		this.nameFilter = nameFilter;
	}
	
	@Override
	public boolean satisfies(QuakeEntry qe) {

		return (maxDepth >= qe.getDepth() && qe.getDepth() >= minDepth);
	}

	@Override
	public String getName() {

		return this.nameFilter;
	}

}

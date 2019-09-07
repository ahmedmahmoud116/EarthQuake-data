package pkj;

import java.util.Comparator;

public class MagnitudeComparator implements Comparator<QuakeEntry>{

	public MagnitudeComparator() {
	
	}
	
	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		return Double.compare(q1.getMagnitude(), q2.getMagnitude());
	}

}

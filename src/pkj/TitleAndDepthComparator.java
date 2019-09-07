package pkj;

import java.util.Comparator;

public class TitleAndDepthComparator implements Comparator<QuakeEntry>{

	public TitleAndDepthComparator() {
		
	}
	
	@Override
	public int compare(QuakeEntry q1, QuakeEntry q2) {
		int check = q1.getInfo().compareTo(q2.getInfo());
		if(check == 0) {
			return Double.compare(q1.getDepth(), q2.getDepth());
		}
		return check;
	}
	
}

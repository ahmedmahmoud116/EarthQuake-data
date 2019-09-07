package pkj;

import java.util.Comparator;

public class TitleLastAndMagnitudeComparator implements Comparator<QuakeEntry>{

	public TitleLastAndMagnitudeComparator() {
		
	}
	
	@Override
	public int compare(QuakeEntry q1,QuakeEntry q2) {
		String title1 = q1.getInfo();
		String title2 = q2.getInfo();
		
		String[] words1 = title1.split("\\W");
		String[] words2 = title2.split("\\W");
		
		String lastword1 = words1[words1.length-1];
		String lastword2 = words2[words2.length-1];
		
		int check = lastword1.compareTo(lastword2);
		if(check == 0) {
			return Double.compare(q1.getMagnitude(), q2.getMagnitude());
		}
		return check;
	}
}

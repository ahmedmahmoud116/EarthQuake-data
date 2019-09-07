package pkj;

import java.util.ArrayList;

public class MatchAllFilter implements Filter{

	private ArrayList<Filter> list;
	
	public MatchAllFilter() {
		list = new ArrayList<Filter>();
	}
	
	public void addFilter(Filter f) {
		list.add(f);
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {
		
		for(Filter f: list) {
			if(!f.satisfies(qe))
				return false;
		}
		return true;
	}

	@Override
	public String getName() {
		StringBuilder sb = new StringBuilder();
		for(Filter f: list) {
			sb.append(f.getName() + ", ");
		}
		return sb.toString();
	}
}

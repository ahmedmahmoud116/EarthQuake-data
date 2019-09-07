package pkj;

public class PhraseFilter implements Filter{
	
	private String where;
	private String phrase;
	private String name;
	
	public PhraseFilter(String where, String phrase,String nameFilter) {
		this.phrase = phrase;
		this.where = where;
		this.name = nameFilter;
	}

	@Override
	public boolean satisfies(QuakeEntry qe) {// returns true if the phrase is found at the requested location in the title.
											 //If the phrase is not found, this method should return false.
		if(where.equalsIgnoreCase("start")) {
			if(qe.getInfo().startsWith(phrase)) {
				return true;
			}
		}
		else if(where.equalsIgnoreCase("any")) {
			if(qe.getInfo().contains(phrase)){
				return true;
			}
		}
		else if(where.equalsIgnoreCase("end")) {
			if(qe.getInfo().endsWith(phrase)){
				return true;
			}
		}	
		return false;
	}

	@Override
	public String getName() {

		return this.name;
	}
	
}

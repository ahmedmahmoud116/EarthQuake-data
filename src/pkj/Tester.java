package pkj;

public class Tester {
	
	public Tester() {
		
	}
	
	public static void main(String[] args) {
		
		EarthQuakeClient eq = new EarthQuakeClient();
//		eq.createCSV();
//		eq.bigQuakes();
//		eq.closeToMe();
//		eq.quakesOfDepth();
//		eq.quakesByPhrase();
		
		ClosestQuakes cq = new ClosestQuakes();
//		cq.findClosestQuakes();
		
		LargestQuakes lq = new LargestQuakes();
//		lq.findLargestQuakes();
		
		EarthQuakeClient2 eq2 = new EarthQuakeClient2();
//		eq2.quakesWithFilter();
//		eq2.testMatchAllFilter();
//		eq2.testMatchAllFilter2();
		
		QuakeSortWithTwoArrayLists qst = new QuakeSortWithTwoArrayLists();
//		qst.testSort();
		
		QuakeSortInPlace_SelectionSort qsi = new QuakeSortInPlace_SelectionSort();
//		qsi.testSort();
		
		QuakeSortInPlace_BubbleSort qsb = new QuakeSortInPlace_BubbleSort();
//		qsb.testSort();
		
		DifferentSorters ds = new DifferentSorters();
//		ds.sortWithComplareTo();
//		ds.sortByMagnitude();
//		ds.sortByDistance();
//		ds.sortByTitleAndDepth();
		ds.sortByLastWordInTitleThenByMagnitude();
		
	}

}

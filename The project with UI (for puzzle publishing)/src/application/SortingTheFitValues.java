package application;
import java.util.Comparator;

public class SortingTheFitValues implements Comparator<Object> {

	public int compare(Object o1, Object o2) {

		FitValues v1 = (FitValues) o1;
		FitValues v2 = (FitValues) o2;

		return Integer.compare(v2.counter, v1.counter);
	}
}
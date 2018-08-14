package application;
import java.util.Comparator;


public class MostConstrainingVariablePriority implements Comparator<Object> {

	public int compare(Object o1, Object o2) {

		Variable v1 = (Variable) o1;
		Variable v2 = (Variable) o2;

		return Integer.compare(v2.mostConstrainingVariableHeuristic, v1.mostConstrainingVariableHeuristic);
	}
}
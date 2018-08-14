package application;
import java.util.ArrayList;

public class State {

	ArrayList<Variable> variables;

	ArrayList<String> wordsUsed;
	
	String selectedWord;

	State(ArrayList<Variable> variables) {
		this.variables = variables;
		wordsUsed = new ArrayList<String>();

		for (int i = 0; i < variables.size(); i++) {
			if (variables.get(i).mostConstrainingVariableHeuristic == -1) {
				wordsUsed.add(variables.get(i).currentValue);
			}
		}

	}

	public String toString() {
		return variables.toString();
	}

}

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

import org.omg.CORBA.TRANSACTION_REQUIRED;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, CloneNotSupportedException {

		function("");

	}

	public static void function(String fileName) throws CloneNotSupportedException, FileNotFoundException {

		// words
		// --------------
		File f = new File("C:\\Users\\adyah\\Desktop\\words2.txt");
		Scanner scan = new Scanner(f);
		ArrayList<String> listOfWords = new ArrayList<String>();

		while (scan.hasNext()) {
			String ss = scan.nextLine();
			if (ss.chars().allMatch(Character::isLetter)) {
				// listOfWords.add(ss);
			}
		}

		listOfWords.add("basil");
		listOfWords.add("badaf");
		listOfWords.add("app");
		listOfWords.add("loool");
		listOfWords.add("awo");
		listOfWords.add("abcdo");
		listOfWords.add("fuk");

		//
		

		// --------------

		// hashmap from size to value object

		// --------------------------
		HashMap<Integer, Value> newMap = new HashMap<Integer, Value>();
		for (int i = 0; i < listOfWords.size(); i++) {

			String str = listOfWords.get(i);
			int size = str.length();

			if (newMap.containsKey(size)) {
				Value val = newMap.get(size);
				val.add(str);
			} else {
				Value val = new Value(size);
				val.add(str);
				newMap.put(size, val);
			}

		}
		for (Integer size : newMap.keySet()) {
			newMap.get(size).setCombinations();
		}
		// ----------------------------

		// the grid
		// -------------

		char[][] grid2 = { { ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ' },
				{ ' ', '*', ' ', ' ', ' ', ' ', '*', '*', ' ' }, { ' ', '*', ' ', '*', '*', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', '*', ' ', '*', ' ', '*' }, { '*', ' ', '*', ' ', ' ', ' ', '*', ' ', '*' },
				{ '*', ' ', '*', ' ', '*', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', ' ', '*', '*', ' ', '*', ' ' },
				{ ' ', '*', '*', ' ', ' ', ' ', ' ', '*', ' ' }, { ' ', ' ', ' ', ' ', '*', '*', ' ', '*', ' ' } };

		char[][] grid3 = { { ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
				{ '*', '*', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*' },
				{ ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', '*', ' ', ' ', ' ' },
				{ '*', '*', ' ', ' ', ' ', ' ', ' ', ' ', ' ', '*', '*', '*', '*' },
				{ ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', '*', ' ', ' ', ' ', '*', ' ', ' ', ' ', ' ', ' ' },

		};
		char[][] grid = { { ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', '*', ' ' }, { ' ', '*', ' ', ' ', ' ' },
				{ ' ', ' ', ' ', ' ', ' ' }, { ' ', ' ', ' ', '*', ' ' } };
		// -----------------

		// int x,y;
		//
		// x = new Scanner(System.in).nextInt();
		// y = new Scanner(System.in).nextInt();
		//
		// char grid2[][] = new char[x][y];
		//
		// for (int i = 0; i < x; i++) {
		// for (int j = 0; j < y; j++) {
		// grid2[i][j] = ' ';
		// }
		// }
		//
		// generateAmericanStyle(grid2);
		// print(grid2);

		// new Scanner(System.in).nextLine();
		ArrayList<Variable> horVariables = new ArrayList<Variable>();
		ArrayList<Variable> verVariables = new ArrayList<Variable>();
		findHorSlots(horVariables, grid, newMap);
		findVerSlots(verVariables, grid, newMap);
		print(grid);

		// connecting the neighbors
		// ------------------
		for (int i = 0; i < horVariables.size(); i++) {
			for (int j = 0; j < verVariables.size(); j++) {
				int[] info = horVariables.get(i).intersects(verVariables.get(j));
				if (info != null) {
					horVariables.get(i)
							.addAnotherN(new IntersectionInfo(verVariables.get(j).uniqueID, info[0], info[1]));
				}
			}
			horVariables.get(i).setMostConstrainingVariableHeuristic2();
		}
		// ------------------

		// connecting the neighbors
		// -------------------
		for (int i = 0; i < verVariables.size(); i++) {
			for (int j = 0; j < horVariables.size(); j++) {
				int[] info = verVariables.get(i).intersects(horVariables.get(j));

				if (info != null) {
					verVariables.get(i)
							.addAnotherN(new IntersectionInfo(horVariables.get(j).uniqueID, info[0], info[1]));

				}
			}
			verVariables.get(i).setMostConstrainingVariableHeuristic2();

		}
		// -------------------

		// defining heuristic1
		// -----------
		MostConstrainingVariablePriority heuristic1 = new MostConstrainingVariablePriority();
		// -----------

		// defining id based sort
		// ------------
		UniqueIdPriority sortByID = new UniqueIdPriority();
		// ------------

		// defining the initial state
		// ------------
		ArrayList<Variable> initialState = new ArrayList<Variable>();
		// ------------

		// adding the variables
		// ------------
		initialState.addAll(horVariables);
		initialState.addAll(verVariables);


		// ------------

		// sorting the initial state
		// --------------------
		Collections.sort(initialState, heuristic1);
		// --------------------

		ArrayList<Variable> shit = new ArrayList<Variable>();
		for (Variable v : initialState) {
			shit.add((Variable) v.clone());
		}

		ArrayList<Variable> triedShit = new ArrayList<Variable>(shit);

		Collections.sort(triedShit, sortByID);

		// starting the DFS
		// -----------------------

		// defining the initial state
		State initialS = new State(initialState);

		// defining the stack
		Stack<State> states = new Stack<State>();

		// pushing the initial state to the stack
		states.push(initialS);

		int iterationNumber = 0;

		char[][] currentGrid;

		System.out.println(">>> " + states.size());

		while (!states.isEmpty()) {

			iterationNumber++;

			System.out.println("This is iteration #" + iterationNumber);

			// printing the stack
			printStack(states);

			// creating an array list of variables
			ArrayList<Variable> temp = new ArrayList<Variable>();

			// printing the current grid
			currentGrid = printBasedOnState(states.peek(), grid);

			// checking if the current grid is solved
			if (isSolved(currentGrid)) {

				ArrayList<String> hors = new ArrayList<String>();
				ArrayList<String> vers = new ArrayList<String>();

				for (int i = 0; i < states.peek().variables.size(); i++) {
					if (states.peek().variables.get(i).startX == states.peek().variables.get(i).endX) {
						hors.add(states.peek().variables.get(i).currentValue);
					} else
						vers.add(states.peek().variables.get(i).currentValue);
				}
				break;
			}

			// cloning the peek to the temp
			for (Variable v : states.peek().variables) {
				temp.add((Variable) v.clone());
			}

			// creating a new temp that holds the original temp values sorted by ID
			ArrayList<Variable> temp2 = new ArrayList<Variable>(temp);

			// sorting the new temp
			Collections.sort(temp2, sortByID);

			// getting the variable with the highest heuristic
			Variable choosenVariable = temp.get(0);

			System.out.println(choosenVariable);
			int cc = choosenVariable.mostConstrainingVariableHeuristic;

			int minIndex = 0;

			int minValue = choosenVariable.mostConstrainedVariableHeuristic;

			for (int k = 0; k < temp.size(); k++) {
				if (temp.get(k).mostConstrainingVariableHeuristic == cc && temp.get(k).val != null) {
					if (temp.get(k).mostConstrainedVariableHeuristic <= minValue) {
						minIndex = k;
						minValue = temp.get(k).mostConstrainedVariableHeuristic;
					}
				} else
					break;
			}

			// now min index represent the variable with both most constraining variable and
			// minimum remaining value
			choosenVariable = temp.get(minIndex);

			System.out.println(choosenVariable);

			// if the variable is already filled
			if (choosenVariable.mostConstrainedVariableHeuristic <= 0) {
				states.clear();
				break;
			}

			// this list will have all the used words
			ArrayList<String> newList = new ArrayList<String>();

			// adding the used words in the grid
			newList.addAll(states.peek().wordsUsed);

			// adding the used words in that variable ( useful in backtracking )
			newList.addAll(triedShit.get(choosenVariable.uniqueID).words);

			// getting a list of all the values that can fit initially (with the current
			// constraints) and also sorted by their min conflict
			ArrayList<String> values = choosenVariable.assignAValue(newList, temp2);

			System.out.println(values);

			// if there's no values, then backtrack
			if (values == null) {
				triedShit.get(choosenVariable.uniqueID).words.clear();
				states.pop();
				printStack(states);
				continue;
			}

			boolean ff = false;

			// otherwise we will traverse the values to see who satisfy both the forward
			// checking and the arc consistency
			for (int i = 0; i < values.size(); i++) {

				ArrayList<Variable> t1 = new ArrayList<Variable>();

				for (Variable v : states.peek().variables) {
					t1.add((Variable) v.clone());
				}
				Variable choosen = t1.get(minIndex);

				ArrayList<Variable> t2 = new ArrayList<Variable>(t1);

				Collections.sort(t2, sortByID);

				boolean ffff = choosen.addConstraintsToNeighbors(values.get(i), t2, newList);

				// if the ith value satisfies both the arc consistency and the forward checking
				// then we will assign it safely
				if (ffff) {
					choosen.currentValue = values.get(i);
					triedShit.get(choosen.uniqueID).words.add(values.get(i));
					Collections.sort(t1, heuristic1);
					State nextState = new State(t1);
					nextState.selectedWord = choosen.currentValue;
					states.push(nextState);
					ff = true;
					break;

				}

			}

			// if the loop is done and there's no value that satisfy the forward checking
			// and the arc consistency, then we will backtrack
			if (!ff) {
				triedShit.get(choosenVariable.uniqueID).words.clear();
				states.pop();
				printStack(states);
			}
		}

		if (states.isEmpty()) {
			System.out.println("no solution found");
		} else {
			System.out.println("Optimal solution found");
		}
		// -------------------------

	}

	public static void generateAmericanStyle(char[][] arr) {

		if (arr[0].length <= 2)
			return;

		int start = arr[0].length / 2 - 1;
		int end = arr[0].length / 2 + 1;

		int base = (int) (Math.random() * (end - start + 1)) + start;

		boolean flag = false;

		for (int i = 0; i < arr.length; i++) {

			if (i <= arr.length * 0.2 || i >= arr.length * 0.8) {
				int[] array = new int[arr[0].length - Math.min(i, arr[0].length)];
				for (int k = 0; k < array.length; k++) {
					array[k] = k;
				}

				for (int k = 0; k < 0.2 * arr[0].length - i; k++) {
					int rand = (int) (Math.random() * (arr[0].length - i - k));
					array[rand] = array[rand] + array[array.length - 1 - i];
					array[array.length - 1] = array[rand] - array[array.length - 1 - i];
					array[rand] = array[rand] - array[array.length - 1 - i];
					arr[i][array[array.length - 1 - i]] = '*';
				}
			} else {

				if (!flag) {
					arr[i][base] = '*';
					flag = true;
				} else {
					int rand = (int) (Math.random() * 2) + 1;
					base = base - rand;
					if (base >= 0) {
						arr[i][base] = '*';
					}
				}

			}

		}

		for (int i = 0; i < arr.length; i++) {
			for (int j = arr[0].length - Math.min(i, arr[0].length); j < arr[0].length; j++) {
				System.out.print("-");
				arr[i][j] = arr[arr.length - 1 - i][arr[0].length - 1 - j];
			}
			System.out.println();
		}

	}

	public static void generateBritishStyle(char[][] arr) {

		int[] random = new int[arr[0].length / 2];

		for (int i = 0; i < random.length; i++) {
			random[i] = i * 2;
		}

		Integer[] selected = new Integer[random.length / 4];

		int tracker = random.length - 1;

		for (int i = 0; i < selected.length; i++) {
			int s = (int) (Math.random() * (tracker + 1));
			selected[i] = random[s];

			random[tracker] = random[tracker] ^ random[s];
			random[s] = random[tracker] ^ random[s];
			random[tracker] = random[tracker] ^ random[s];

			tracker--;
		}

		Integer[] selected2 = new Integer[selected.length];
		for (int i = 0; i < selected.length; i++) {
			selected2[i] = selected[i] + 1;
		}

		Collections.shuffle(Arrays.asList(selected2));

		for (int i = 0; i < arr.length; i++) {
			if (i % 2 == 0) {
				if (!inIt(i, selected)) {
					arr[i][(int) ((Math.random()) * arr[i].length)] = '*';
				}
			} else {
				for (int j = 0; j < arr[i].length; j++) {
					if (j % 2 == 1) {
						arr[i][j] = '*';
					}
				}
				if (inIt(i, selected2)) {
					int s = (int) ((Math.random()) * arr[i].length);
					if (s % 2 == 1) {
						s--;
					}
					arr[i][s] = '*';
				}

			}
		}

	}

	public static boolean inIt(int aim, Integer[] selected2) {
		for (int i = 0; i < selected2.length; i++) {
			if (selected2[i] == aim)
				return true;
		}
		return false;
	}

	public static void printStack(Stack<State> states) {

		System.out.println("-*-*-*-*-*-");
		for (int i = 0; i < states.size(); i++) {
			System.out.println(states.get(i).wordsUsed);
			System.out.println();
		}
		System.out.println("-*-*-*-*-*-");

	}

	public static char[][] printBasedOnState(State s, char[][] grid) {

		char[][] newGrid = new char[grid.length][grid[0].length];

		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				newGrid[i][j] = grid[i][j];
			}
		}

		ArrayList<Variable> vars = s.variables;

		for (int i = 0; i < vars.size(); i++) {
			Variable var = vars.get(i);
			assign(newGrid, var);
		}
		print(newGrid);
		System.out.println();
		System.out.println("______");
		System.out.println();
		return newGrid;
	}

	public static boolean isSolved(char[][] grid) {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == ' ')
					return false;
			}
		}
		return true;
	}

	public static void assign(char[][] arr, Variable var) {
		if (var.startX == var.endX) {
			for (int i = 0; i < var.currentValue.length(); i++) {
				arr[var.startX][var.startY + i] = var.currentValue.charAt(i);
			}
		} else {
			for (int i = 0; i < var.currentValue.length(); i++) {
				arr[var.startX + i][var.startY] = var.currentValue.charAt(i);
			}
		}
	}

	public static Variable getByID(ArrayList<Variable> state, int id) {
		for (int i = 0; i < state.size(); i++) {
			if (state.get(i).uniqueID == id)
				return state.get(i);
		}
		return null;
	}

	public static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + ",");
			}
			System.out.println();
		}
	}

	public static void findHorSlots(ArrayList<Variable> horVariables, char[][] grid, HashMap<Integer, Value> newMap) {

		for (int i = 0; i < grid.length; i++) {
			int tracker = 0;
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == '*') {
					if (tracker < j) {
						int size = j - tracker;
						if (size != 1) {
							Value val = newMap.get(size);
							if (val != null) {
								Variable var = new Variable(i, tracker, i, j - 1, val);
								System.out.println(var);
								horVariables.add(var);
							}
						}
					}
					tracker = j + 1;
				}
			}
			if (tracker != grid[i].length) {

				int size = grid[i].length - tracker;
				if (size != 1) {
					Value val = newMap.get(size);
					if (val != null) {
						Variable var = new Variable(i, tracker, i, grid[i].length - 1, val);
						System.out.println(var);

						horVariables.add(var);
					}
				}

			}
		}

	}

	public static void findVerSlots(ArrayList<Variable> verVariables, char[][] grid, HashMap<Integer, Value> newMap) {

		for (int i = 0; i < grid[0].length; i++) {
			int tracker = 0;
			for (int j = 0; j < grid.length; j++) {
				if (grid[j][i] == '*') {
					if (tracker < j) {

						int size = j - tracker;
						if (size != 1) {
							Value val = newMap.get(size);
							if (val != null) {
								Variable var = new Variable(tracker, i, j - 1, i, val);
								System.out.println(var);

								verVariables.add(var);
							}
						}
					}
					tracker = j + 1;
				}
			}
			if (tracker != grid.length) {

				int size = grid.length - tracker;
				if (size != 1) {
					Value val = newMap.get(size);
					if (val != null) {
						Variable var = new Variable(tracker, i, grid.length - 1, i, val);
						System.out.println(var);

						verVariables.add(var);
					}
				}

			}
		}

	}

	
	
	
	
	
	
	
}

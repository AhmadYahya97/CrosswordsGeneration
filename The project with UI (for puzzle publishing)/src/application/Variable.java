package application;
import java.util.ArrayList;
import java.util.Collections;

public class Variable implements Cloneable {

	Value val;

	public static int counter = 0;

	ArrayList<Constraints> cons;
	ArrayList<Constraints> arcCons;

	ArrayList<String> words;
	int startX;
	int startY;
	int endX;
	int endY;

	int uniqueID;

	ArrayList<IntersectionInfo> anotherN;

	int[][] leastConstrainingValueHeuristic;

	int size;
	String currentValue = "";
	int mostConstrainingVariableHeuristic;
	int mostConstrainedVariableHeuristic;

	int indexOfUsedWord;

	Variable(int startX, int startY, int endX, int endY, Value val) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		if (startX == endX) {
			for (int i = 0; i < (endY - startY + 1); i++) {
				currentValue += " ";
			}
		} else {
			for (int i = 0; i < (endX - startX + 1); i++) {
				currentValue += " ";
			}
		}
		this.val = val;
		cons = new ArrayList<Constraints>();
		arcCons = new ArrayList<Constraints>();
		anotherN = new ArrayList<IntersectionInfo>();
		words = new ArrayList<String>();
		uniqueID = counter++;
		if (val != null) {
			leastConstrainingValueHeuristic = new int[26][val.size];
			setleastConstrainingValueHeuristic(new ArrayList<String>());
		}

		indexOfUsedWord = -1;
	}

	public boolean arcCons(ArrayList<String> wordsUsed, ArrayList<Variable> sortedStateBasedOnID) {

		ArrayList<String> availableValues = findTheListOfAvailableWords();

		for (int i = 0; i < wordsUsed.size(); i++) {
			availableValues.remove(wordsUsed.get(i));
		}

		int[] arr = new int[val.size];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = -1;
		}

		for (int i = 0; i < anotherN.size(); i++) {
			if (sortedStateBasedOnID.get(anotherN.get(i).uniqueID).mostConstrainingVariableHeuristic != -1) {
				arr[anotherN.get(i).sourceIndex] = i;

				IntersectionInfo temp = anotherN.get(i);
				Variable neighbor = sortedStateBasedOnID.get(temp.uniqueID);
				for (int j = 0; j < 26; j++) {
					if (neighbor.val != null && neighbor.leastConstrainingValueHeuristic[j][temp.destinationIndex] != 0) {
						char c = (char) (j + 'a');
						addArcConstraints(c, temp.sourceIndex);
					}//zbt, try the old one tmam
				}
				boolean flagg = updateTheVariable(wordsUsed);
				if (!flagg) {
					return false;
				}
				for (int j = 0; j < 26; j++) {
					if (this.val!= null && leastConstrainingValueHeuristic[j][temp.sourceIndex] != 0) {
						
						char c = (char) (j + 'a');
						neighbor.addArcConstraints(c, temp.destinationIndex);
					}
				}

				flagg = neighbor.updateTheVariable(wordsUsed);
				if (!flagg) {
					return false;
				}
			}
		}
		return true;

	}

	public ArrayList<String> assignAValue(ArrayList<String> wordsUsed, ArrayList<Variable> sortedStateBasedOnID) {

		ArrayList<String> availableValues = findTheListOfAvailableWords();

		for (int i = 0; i < wordsUsed.size(); i++) {
			availableValues.remove(wordsUsed.get(i));
		}
		int[] arr = new int[val.size];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = -1;
		}

		int cc = 0;

		for (int i = 0; i < anotherN.size(); i++) {
			if (sortedStateBasedOnID.get(anotherN.get(i).uniqueID).mostConstrainingVariableHeuristic != -1) {
				arr[anotherN.get(i).sourceIndex] = i;
				cc++;
			}
		}
		ArrayList<FitValues> fitVals = new ArrayList<FitValues>();
		ArrayList<String> values = new ArrayList<String>();

		if (cc == 0) {
			if (availableValues.size() == 0)
				return null;
			return availableValues;
		}

		for (int i = 0; i < availableValues.size(); i++) {
			String str = availableValues.get(i);

			int numberOfAvailableNeighborsOptions = 0;

			boolean flag = true;
			for (int j = 0; j < str.length(); j++) {

				char ch = str.charAt(j);
				if (arr[j] != -1) {
					IntersectionInfo temp = anotherN.get(arr[j]);

					Variable neighbor = sortedStateBasedOnID.get(temp.uniqueID);
 
					int number=0;
					if(neighbor.val != null){
					number = neighbor.leastConstrainingValueHeuristic[ch - 'a'][temp.destinationIndex];
					//System.out.println("after");
				
					
					if (number != 0) {
						numberOfAvailableNeighborsOptions += number;
					} else {
						flag = false;
						break;
					}
				}
			}
			}
			if (flag) {

				fitVals.add(new FitValues(numberOfAvailableNeighborsOptions, i));

			}
		}

		SortingTheFitValues sortParam = new SortingTheFitValues();
		Collections.sort(fitVals, sortParam);
		if (fitVals.size() == 0) {
			return null;
		} else {
			for (int i = 0; i < fitVals.size(); i++) {
				values.add(availableValues.get(fitVals.get(i).index));
			}
			return values;
		}

	}

	public boolean setleastConstrainingValueHeuristic(ArrayList<String> wordsUsed) {
		ArrayList<String> availableValues = findTheListOfAvailableWords();

		for (int i = 0; i < wordsUsed.size(); i++) {
			availableValues.remove(wordsUsed.get(i));
		}
//tmam try difereent examples , wbardu jarbi ili zbtu
		setMostConstrainedVariableHeuristic(availableValues.size());

		for (int i = 0; i < availableValues.size(); i++) {
			for (int j = 0; j < availableValues.get(i).length(); j++) {
				boolean f2 = false;

				boolean there = false;
				for (int w = 0; w < arcCons.size(); w++) {
					if (arcCons.get(w).position == j) {
						there = true;
						if (arcCons.get(w).letter == availableValues.get(i).charAt(j)) {
							f2 = true;
						}
					}
				}

				if (there) {
					if (!f2) {
						availableValues.remove(i);
						i--;
						break;
					}

				}
			}
		}

		for (int i = 0; i < availableValues.size(); i++) {
			String str = availableValues.get(i);
			for (int j = 0; j < str.length(); j++) {
				char ch = str.charAt(j);
				int indexOfChar = ch - 'a';
				leastConstrainingValueHeuristic[indexOfChar][j]++;
			}
		}

		if (availableValues.size() == 0)
			return false;
		return true;
	}

	public ArrayList<String> findTheListOfAvailableWords() {

		ArrayList<String> availableValues;
		if (cons.size() != 0) {
			availableValues = new ArrayList<String>(val.get(cons.get(0).letter, cons.get(0).position));
			for (int i = 1; i < cons.size(); i++) {
				availableValues.retainAll(val.get(cons.get(i).letter, cons.get(i).position));
			}
		} else {
			availableValues = new ArrayList<String>(val.listOfWords);
		}

		return availableValues;
	}

	Variable(int startX, int startY, int endX, int endY) {
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		if (startX == endX) {
			for (int i = 0; i < (endY - startY + 1); i++) {
				currentValue += " ";
			}
		} else {
			for (int i = 0; i < (endX - startX + 1); i++) {
				currentValue += " ";
			}
		}
		anotherN = new ArrayList<IntersectionInfo>();

		uniqueID = counter++;

	}

	public void setMostConstrainingVariableHeuristic2() {
		this.mostConstrainingVariableHeuristic = anotherN.size();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {

		Variable var = (Variable) super.clone();

		var.cons = new ArrayList<Constraints>();
		for (Constraints c : cons) {
			var.cons.add((Constraints) c.clone());
		}
		var.arcCons = new ArrayList<Constraints>();
		for (Constraints c : arcCons) {
			var.arcCons.add((Constraints) c.clone());
		}

		var.currentValue = new String(currentValue);

		return var;
	}

	public void setMostConstrainedVariableHeuristic(int mostConstrainedVariableHeuristic) {
		this.mostConstrainedVariableHeuristic = mostConstrainedVariableHeuristic;
	}

	public void addConstraints(char letter, int position) {
		cons.add(new Constraints(letter, position));
	}

	public void addArcConstraints(char letter, int position) {
		arcCons.add(new Constraints(letter, position));
	}

	public boolean addConstraintsToNeighbors(String assigned, ArrayList<Variable> sortedStateBasedOnID,
			ArrayList<String> wordsUsed) {

		int[] arr = new int[val.size];

		for (int i = 0; i < arr.length; i++) {
			arr[i] = -1;
		}

		for (int i = 0; i < anotherN.size(); i++) {
			if (sortedStateBasedOnID.get(anotherN.get(i).uniqueID).mostConstrainingVariableHeuristic != -1) {
				arr[anotherN.get(i).sourceIndex] = i;
			}
		}

		for (int i = 0; i < assigned.length(); i++) {
			char ch = assigned.charAt(i);

			if (arr[i] != -1) {
				IntersectionInfo temp = anotherN.get(arr[i]);

				Variable neighbor = sortedStateBasedOnID.get(temp.uniqueID);
				neighbor.addConstraints(ch, temp.destinationIndex);
				neighbor.currentValue = neighbor.currentValue.substring(0, temp.destinationIndex) + ch
						+ neighbor.currentValue.substring(temp.destinationIndex + 1);
				neighbor.mostConstrainingVariableHeuristic--;
				boolean flagg = neighbor.updateTheVariable(wordsUsed);
				if (!flagg) {
					return false;
				}
				flagg = neighbor.arcCons(wordsUsed, sortedStateBasedOnID);
				if (!flagg) {
					return false;
				}
			}
		}
		mostConstrainingVariableHeuristic = -1;
		return true;
	}

	public boolean updateTheVariable(ArrayList<String> wordsUsed) {
		if (val != null) {
			leastConstrainingValueHeuristic = new int[26][val.size];
			boolean flag = setleastConstrainingValueHeuristic(wordsUsed);
			return flag;
		}
		return false;
	}

	public void addAnotherN(IntersectionInfo info) {
		anotherN.add(info);
	}

	public int[] intersects(Variable var) {

		int[] info = new int[2];

		if (var.startX == var.endX && startX == endX) {
			return null;
		}

		if (var.startY == var.endY && startY == endY) {
			return null;
		}

		if (startX == endX) {
			if (startY <= var.startY && endY >= var.startY) {
				if (var.startX <= startX && var.endX >= startX) {
					info[0] = var.startY - startY;
					info[1] = startX - var.startX;

					return info;
				} else
					return null;
			} else
				return null;
		}

		if (startY == endY) {
			if (startX <= var.startX && endX >= var.startX) {
				if (var.startY <= startY && var.endY >= startY) {
					info[0] = var.startX - startX;
					info[1] = startY - var.startY;

					return info;
				} else
					return null;
			} else
				return null;
		}

		return null;
	}

	public boolean isVariableSolved() {
		if (currentValue.replaceAll("\\s", "").length() == val.size) {
			return true;
		} else
			return false;
	}

	public String toString() {
		return mostConstrainingVariableHeuristic + "|" + mostConstrainedVariableHeuristic + "|(" + startX + "," + startY
				+ ") to (" + endX + " ," + endY + ")  ->  {" + currentValue + "}" + "  " + uniqueID;
	}

	@Override
	public int hashCode() {
		final int prime = 997;
		int result = 1;
		result = prime * result + endX;
		result = prime * result + endY;
		result = prime * result + startX;
		result = prime * result + startY;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Variable other = (Variable) obj;
		if (endX != other.endX)
			return false;
		if (endY != other.endY)
			return false;
		if (startX != other.startX)
			return false;
		if (startY != other.startY)
			return false;
		return true;
	}

}
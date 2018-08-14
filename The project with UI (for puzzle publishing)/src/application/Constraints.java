package application;

public class Constraints implements Cloneable{

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	char letter;
	int position;

	public Constraints(char letter, int position) {
		this.letter = letter;
		this.position = position;
	}

}

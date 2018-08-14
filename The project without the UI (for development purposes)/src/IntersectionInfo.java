
public class IntersectionInfo {

	int uniqueID;
	int sourceIndex;
	int destinationIndex;

	IntersectionInfo(int uniqueID, int sourceIndex, int destinationIndex) {
		this.uniqueID = uniqueID;
		this.sourceIndex = sourceIndex;
		this.destinationIndex = destinationIndex;
	}
	
	
	public String toString() {
		return "id : " + uniqueID + "|| source : " + sourceIndex + "|| dest:" + destinationIndex;
	}

}

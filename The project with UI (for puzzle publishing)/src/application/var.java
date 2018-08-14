package application;

public class var implements Comparable<var>{
	
	String val;
	int id,x,y;
	boolean sortX;
//	static int ID=1;
	public var(String val, int x, int y) {
		super();
		this.val = val;
		this.id = 0;
		this.x = x;
		this.y = y;
	}

	public int compareTo(var o) {
		// TODO Auto-generated method stub
		if(this.x<o.x) {
			return -1;
		}
		else if(this.x>o.x) {
			return 1;
		}else if(this.x==o.x){
			if(this.y<o.y) {
				return -1;
			}else if(this.y>o.y) {
				return 1;
			}else {
				return 0;
			}
		}
		return 0;
	}
	
	
	

}

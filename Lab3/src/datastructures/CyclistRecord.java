package datastructures;

public class CyclistRecord implements Comparable<CyclistRecord>{
	public String name;
	public double time;
	
	public CyclistRecord(String name, double time) {
			this.name = name;
			this.time = time;
	}
	public double getTime()
	{
		return this.time;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		
		return name + time;
	}
	@Override
	public int compareTo(CyclistRecord o) {
		
		return (int) (this.time-o.time);
	}
}


package files;

import java.io.PrintWriter;

public abstract class absStation {
	
	protected String totalTime;
	protected String avgTime;
	
	public absStation(){
		totalTime="0";
		avgTime="0";
	}
	
	
	public absStation(String totalTime2, String avg) {
		this.totalTime=totalTime2;
		this.avgTime=avg;
	}

	public abstract String toString();
	public abstract void saveToFile(PrintWriter pw);

}

package files;

import java.io.PrintWriter;
import java.util.Scanner;

public class Station1 {
	
	public final int MAX_UNITS = 5;
	private String avgTime;
	private String totalRuningTime;
	private String operationOrder;
	
	public Station1(){
		avgTime="00:00 D";
		totalRuningTime="00:00 D";
		operationOrder=null;
	}
	
	public Station1(String avg,String totalTime,String operationOrder){
		avgTime=avg;
		totalRuningTime=totalTime;
		this.operationOrder=operationOrder;
	}
	
	public Station1(Scanner s){
		avgTime=s.nextLine();
		totalRuningTime=s.nextLine();
		operationOrder=s.nextLine();
	}

	public String getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}

	public String getTotalRuningTime() {
		return totalRuningTime;
	}

	public void setTotalRuningTime(String totalRuningTime) {
		this.totalRuningTime = totalRuningTime;
	}

	@Override
	public String toString() {
		return "Station1: avgTime=" + avgTime + ", totalRuningTime=" + totalRuningTime;
	}
	
	public void saveToFile(PrintWriter pw){
		pw.println(avgTime);
		pw.println(totalRuningTime);
		pw.println(operationOrder);
	}
}

package files;

import java.io.PrintWriter;
import java.util.Scanner;

public class Station1 extends absStation{
	
	public final int MAX_UNITS = 5;
	private String operationOrder;
	
	public Station1(){
		super("00:00 D","00:00 D");
		operationOrder=null;
	}
	
	public Station1(String avg,String totalTime,String operationOrder){
		super(totalTime,avg);
		this.operationOrder=operationOrder;
	}
	
	public Station1(Scanner s){
		avgTime=s.nextLine();
		totalTime=s.nextLine();
		operationOrder=s.nextLine();
	}

	public String getAvgTime() {
		return avgTime;
	}

	public void setAvgTime(String avgTime) {
		this.avgTime = avgTime;
	}

	public String getTotalRuningTime() {
		return totalTime;
	}

	public void setTotalRuningTime(String totalRuningTime) {
		this.totalTime = totalRuningTime;
	}

	@Override
	public String toString() {
		return "Station1: avgTime=" + avgTime + ", totalRuningTime=" + totalTime;
	}
	
	public void saveToFile(PrintWriter pw){
		pw.println(avgTime);
		pw.println(totalTime);
		pw.println(operationOrder);
	}
}

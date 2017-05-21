package files;

import java.io.PrintWriter;
import java.util.Scanner;

public class Station2 extends absStation{
	
	private String score;
	
	public Station2() {
		super("00:00 D","00:00 D");
		this.score ="00:00 D";
	}
	
	public Station2(String avgRTime, String totalTime, String score) {
		super(totalTime,avgRTime);
		this.score = score;
	}
	
	public Station2(Scanner s){
		super(s.nextLine(),s.nextLine());
		score=s.nextLine();
	}
	
	public String getAvgRTime() {
		return avgTime;
	}
	
	public void setAvgRTime(String avgRTime) {
		this.avgTime = avgRTime;
	}
	
	public String getTotalTime() {
		return totalTime;
	}
	
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	
	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Station2: Average reaction time=" + avgTime + ", Total Time=" + totalTime + ", Score=" + score;
	}
	
	public void saveToFile(PrintWriter pw){
		pw.println(totalTime);
		pw.println(avgTime);
		pw.println(score);
	}
}

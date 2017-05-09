package files;

import java.io.PrintWriter;
import java.util.Scanner;

public class Station2 {
	
	private String avgRTime;
	private String totalTime;
	private String score;
	
	
	public Station2() {
		this.avgRTime ="00:00 D";
		this.totalTime ="00:00 D";
		this.score ="00:00 D";
	}
	
	public Station2(String avgRTime, String totalTime, String score) {
		this.avgRTime = avgRTime;
		this.totalTime = totalTime;
		this.score = score;
	}
	
	public Station2(Scanner s){
		totalTime=s.nextLine();
		avgRTime=s.nextLine();
		score=s.nextLine();
	}
	
	public String getAvgRTime() {
		return avgRTime;
	}
	
	public void setAvgRTime(String avgRTime) {
		this.avgRTime = avgRTime;
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
		return "Station2: Average reaction time=" + avgRTime + ", Total Time=" + totalTime + ", Score=" + score;
	}
	
	public void saveToFile(PrintWriter pw){
		pw.println(totalTime);
		pw.println(avgRTime);
		pw.println(score);
	}
}

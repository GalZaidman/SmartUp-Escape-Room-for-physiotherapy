package files;

import java.io.PrintWriter;
import java.util.Scanner;

public class Station4 {
	
	private String avgRTime;
	private String totalTime;
	private String score;
	private String songChosen;
	
	public Station4(){
		avgRTime="00:00 D";
		totalTime="00:00 D";
		score="0D";
		songChosen="blabla";
	}
	
	public Station4(Scanner s){
		totalTime=s.nextLine();
		avgRTime=s.nextLine();
		score=s.nextLine();
		songChosen=s.nextLine();
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

	public String getSongChosen() {
		return songChosen;
	}

	public void setSongChosen(String songChosen) {
		this.songChosen = songChosen;
	}

	@Override
	public String toString() {
		return "Station4 [avgRTime=" + avgRTime + ", totalTime=" + totalTime + ", score=" + score + ", songChosen="
				+ songChosen + "]";
	}	
	
	public void saveToFile(PrintWriter pw){
		pw.println(totalTime);
		pw.println(avgRTime);
		pw.println(score);
		pw.println(songChosen);
	}	
}

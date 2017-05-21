package files;

import java.io.PrintWriter;
import java.util.Scanner;

public class Station4 extends absStation{

	private String score;
	private String songChosen;
	
	public Station4(){
		super("00:00 D","00:00 D");
		score="0D";
		songChosen="blabla";
	}
	
	public Station4(Scanner s){
		super(s.nextLine(),s.nextLine());
		score=s.nextLine();
		songChosen=s.nextLine();
	}
	
	public Station4(String avgRTime,String totalTime,String score,String songChosen){
		super(totalTime,avgRTime);
		this.score=score;
		this.songChosen=songChosen;
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

	public String getSongChosen() {
		return songChosen;
	}

	public void setSongChosen(String songChosen) {
		this.songChosen = songChosen;
	}

	@Override
	public String toString() {
		return "Station4 [avgRTime=" + avgTime + ", totalTime=" + totalTime + ", score=" + score + ", songChosen="
				+ songChosen + "]";
	}	
	
	public void saveToFile(PrintWriter pw){
		pw.println(totalTime);
		pw.println(avgTime);
		pw.println(score);
		pw.println(songChosen);
	}	
}

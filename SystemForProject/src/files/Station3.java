package files;

import java.io.PrintWriter;
import java.util.Scanner;
//need to edit acording to station ... will wait till built
public class Station3 extends absStation{
	
	private int line;
	
	public Station3(){
		super("00:00 D","00:00 D");
		line=0;
	}
	
	public Station3(Scanner s){
		super(s.nextLine(),s.nextLine());
		line=s.nextInt();
	}

	@Override
	public String toString() {
		return "Station3 [line=" + line + ", time=" + totalTime + "]";
	}
	
	public void saveToFile(PrintWriter pw){
		pw.println(totalTime);
		pw.println(avgTime);
		pw.println(line);

	}
}

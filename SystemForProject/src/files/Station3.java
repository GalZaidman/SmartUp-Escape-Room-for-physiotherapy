package files;

import java.io.PrintWriter;
import java.util.Scanner;

public class Station3 {
	
	private int line;
	private String time;
	
	public Station3(){
		line=0;
		time="00:00 D";
	}
	
	public Station3(Scanner s){
		line=s.nextInt();
		s.nextLine();
		time=s.nextLine();
	}

	@Override
	public String toString() {
		return "Station3 [line=" + line + ", time=" + time + "]";
	}
	
	public void saveToFile(PrintWriter pw){
		pw.println(line);
		pw.println(time);
	}
}

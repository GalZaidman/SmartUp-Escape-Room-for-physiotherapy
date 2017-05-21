package files;

import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Meeting {
	
	public String d;
	public Station1 s1;
	public Station2 s2;
	public Station3 s3;
	public Station4 s4;
	
	public Meeting() {
		LocalDateTime d=LocalDateTime.now();
		this.d=d.toString();
		this.s1 = new Station1();
		this.s2 = new Station2();
		this.s3 = new Station3();
		this.s4 = new Station4();
	}
	
	public Meeting(Station1 s1, Station2 s2, Station3 s3, Station4 s4) {
		LocalDateTime d=LocalDateTime.now();
		this.d=d.toString();
		this.s1 = s1;
		this.s2 = s2;
		this.s3 = s3;
		this.s4 = s4;
	}
	
	public Meeting(Scanner s){
		d=s.nextLine();
		s1=new Station1(s);
		s2=new Station2(s);
		s3=new Station3(s);
		s4=new Station4(s);
	}

	public Station1 getS1() {
		return s1;
	}

	public void setS1(Station1 s1) {
		this.s1 = s1;
	}

	public Station2 getS2() {
		return s2;
	}

	public void setS2(Station2 s2) {
		this.s2 = s2;
	}

	public Station3 getS3() {
		return s3;
	}

	public void setS3(Station3 s3) {
		this.s3 = s3;
	}

	public Station4 getS4() {
		return s4;
	}

	public void setS4(Station4 s4) {
		this.s4 = s4;
	}

	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(d+"\n");
		if(s1!=null)sb.append(s1.toString()+"\n");else sb.append("Station 1 was not selected\n");
		if(s2!=null)sb.append(s2.toString()+"\n");else sb.append("Station 2 was not selected\n");
		if(s3!=null)sb.append(s3.toString()+"\n");else sb.append("Station 3 was not selected\n");
		if(s4!=null)sb.append(s4.toString()+"\n");else sb.append("Station 4 was not selected\n");
		return sb.toString();
	}
	
	public String meetingHeader(){
		return d.toString();
	}

	public boolean equalsByHeader(String header) {
		return d.toString().equals(header);
	}

	public void saveToFile(PrintWriter pw){
		pw.println(d);
		s1.saveToFile(pw);
		s2.saveToFile(pw);
		s3.saveToFile(pw);
		s4.saveToFile(pw);
	}
}

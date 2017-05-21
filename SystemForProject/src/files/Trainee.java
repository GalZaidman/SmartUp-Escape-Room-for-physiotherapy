package files;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.sun.jmx.remote.internal.ArrayQueue;

public class Trainee implements Comparable<Trainee>{
	
	public final int MAX_MEETINGS = 10;
	private String firstName;
	private String lastName;
	private String id;
	private int numOfMeetings;
	private Queue<Meeting> meetingQueue; 
	private Meeting[] meetingArr;
	
	public Trainee() {
		this.firstName = "Defult";
		this.lastName = "Defult";
		this.id = "01010101";
		meetingArr=new Meeting[MAX_MEETINGS];
		numOfMeetings=0;
	}
	
	public Trainee(String firstName, String lastName, String id) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		meetingArr=new Meeting[MAX_MEETINGS];
		numOfMeetings=0;
	}
	
	public Trainee(Scanner s)
	{
		id=s.nextLine();
		firstName=s.nextLine();
		lastName=s.nextLine();
		numOfMeetings=s.nextInt();
		s.nextLine();
		meetingArr=new Meeting[MAX_MEETINGS];
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public int getNumOfMeetings() {
		return numOfMeetings;
	}

	public void setNumOfMeetingse(int numOfMeetings) {
		this.numOfMeetings = numOfMeetings;
	}


	public Meeting[] getMeetingArr() {
		return meetingArr;
	}

	public void setMeetingArr(Meeting[] meetingArr) {
		this.meetingArr = meetingArr;
	}

	public void addMeeting(Meeting m){
		if(numOfMeetings==0){
			meetingArr[0]=m;
			numOfMeetings++;
		}else if(numOfMeetings<10){
			Meeting temp1=meetingArr[0];
			for(int i=1 ; i<=numOfMeetings ; i++){
				Meeting temp2=meetingArr[i];
				meetingArr[i]=temp1;
				temp1=temp2;
			}
			numOfMeetings++;
			meetingArr[0]=m;
		}else{
			Meeting temp1=meetingArr[0];
			for(int i=1 ; i<numOfMeetings ; i++){
				Meeting temp2=meetingArr[i];
				meetingArr[i]=temp1;
				temp1=temp2;
			}
			meetingArr[0]=m;
		}
	}
	
	public void saveToFile(PrintWriter pw){
		pw.println(id);
		pw.println(firstName);
		pw.println(lastName);
		pw.println(numOfMeetings);
		for(int i=0 ;i<numOfMeetings ; i++){
			meetingArr[i].saveToFile(pw);
		}
	}
	
	public Meeting findMeetingByHeader(String header){
		for(int i=0 ; i<meetingArr.length ; i++){
			if(meetingArr[i].equalsByHeader(header)){
				return meetingArr[i];
			}
		}
		return null;
	}

	@Override
	public String toString() {		
		StringBuilder sb=new StringBuilder();
		sb.append(id+"\n");
		sb.append(firstName+" "+lastName+"\n");
		for(int i=0 ; i <numOfMeetings ; i++){
			sb.append(meetingArr[i]+"\n");
		}
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Trainee other = (Trainee) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(Trainee o) {
		if(o.getId().equals(id))
			return 0;
		return o.getFirstName().compareTo(firstName);
	}
	
}

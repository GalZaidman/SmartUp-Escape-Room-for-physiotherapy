package files;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

public class Inventory {
	
	private TreeSet<Trainee> traineeSet;
	private int traineeCount;
	
	public Inventory(){
		traineeSet=new TreeSet<Trainee>();
		traineeCount=0;
	}
	
	public Inventory(Scanner s){
		traineeSet=new TreeSet<Trainee>();
		traineeCount=0;
		while (s.hasNext()) {
			s.nextLine();
			traineeSet.add(new Trainee(s));
			traineeCount++;
		}
	}
	
	public boolean addTrainee(String firstName,String lastName,String id){
		if(firstName.isEmpty()||lastName.isEmpty()||id.isEmpty()){
			return false;
		}else{
			Trainee t=new Trainee();
			t.setId(id);
			t.setFirstName(firstName);
			t.setLastName(lastName);
			traineeSet.add(t);
			traineeCount++;
			return true;
		}
	}
	
	public boolean removeTrainee(Trainee t){
		return traineeSet.remove(t);
	}
	
	public Trainee findTrinee(String id){
		for(Trainee t: traineeSet ){
			if(t.getId().equals(id))
				return t;
		}
		return null;
	}
	
	public TreeSet<Trainee> getTraineeList() {
		return traineeSet;
	}

	public int getTraineeCount() {
		return traineeCount;
	}
	
	public Meeting findMeetingInTrinee(Trainee t, String header){
		return t.findMeetingByHeader(header);
	}

	public void saveToFile() throws IOException{
		String InventoryName="Inventory.txt";
		File f=new File(InventoryName);
		PrintWriter pw=new PrintWriter(f);
		pw.print("\n");
		
		for(Trainee t: traineeSet ){
			t.saveToFile(pw);
		}
		pw.close();	
	}
	
	public String toString(){	
		StringBuilder sb=new StringBuilder();
		
		for(Trainee t: traineeSet ){
			sb.append(t+"\n");
		}
		
		return sb.toString();
	}

}

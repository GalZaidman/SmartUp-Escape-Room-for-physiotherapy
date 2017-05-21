package files;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import gui.SystemGUI;

public class StationOneHeadThread implements Runnable{

	private boolean run, dataRecived;
	private Meeting currentMeeting;
	private String orderOfOperation;
	private String results;
	private SystemGUI system;

	public StationOneHeadThread(Meeting m,String orderOfOperation, SystemGUI system) {
		run =true;
		results=null;
		currentMeeting=m;
		this.system=system;
		this.orderOfOperation=orderOfOperation;
	}

	@Override
	public void run() {
		Socket clientSocket;
		try {
			BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in)); 
			clientSocket = new Socket("localhost", 6789);//change local host to pc ip
			
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			
			outToServer.writeChars(orderOfOperation+"\n");// writeBytes(orderOfOperation); 
			while(!inFromServer.ready()){
			};
			results = inFromServer.readLine(); 
			clientSocket.close();
			String[] resultArr=results.split(":");
			Station1 s1=new Station1(resultArr[0],resultArr[1],orderOfOperation);
			currentMeeting.setS1(s1);
			system.station1State(false);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 


	}

}

package files;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

import gui.SystemGUI;

public class StationFourLegsThread implements Runnable{
	private String gameResultsStr;
	private Meeting currentMeeting;
	private Runtime rt;
	private SystemGUI system;
	private boolean run;

	public StationFourLegsThread(Meeting m, SystemGUI system) {
		currentMeeting=m;
		this.system=system;
		run=true;
		rt=Runtime.getRuntime();
	}

	@Override
	public void run() {
		Socket clientSocket;
		try {
			BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in)); 
			clientSocket = new Socket("localhost", 6789);//change local host to pc ip
			
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); 
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); 
			
			outToServer.writeChars("1\n");
			while(!inFromServer.ready()){
			};
			gameResultsStr = inFromServer.readLine();
			clientSocket.close();
			String[] resultArr=gameResultsStr.split("B");
			Station4 s4=new Station4(resultArr[0], resultArr[1], resultArr[2], resultArr[3]);//change acording to inout
			currentMeeting.setS4(s4);
			system.station4State(false,null);
		} catch (java.net.ConnectException e) {
			JOptionPane.showMessageDialog(null, "connection is refused");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
}

package files;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import gui.SystemGUI;

public class GameThread implements Runnable {
	
	
	private String gameToRunLocation;
	private String gameName;
	private String gameResultsStr;
	private int whenToStartRunning;
	private Runtime rt;
	private SerialTest2 arduino;
	private File gameResultsFile;
	private SystemGUI system;
	private boolean run;

	
	
	public GameThread(String gameToRunLocation, int whenToStartRunning, SerialTest2 arduino,String gameName,SystemGUI system) {
		this.gameToRunLocation = gameToRunLocation;
		this.gameName=gameName;
		this.whenToStartRunning = whenToStartRunning;
		this.arduino = arduino;
		this.system=system;
		run=true;
		rt=Runtime.getRuntime();
	}



	@Override
	public void run() {
		while(run==true){
			//JOptionPane.showMessageDialog(null, arduino.SerialEventNum);
			if(arduino.SerialEventNum==whenToStartRunning){
				try {
					//JOptionPane.showMessageDialog(null, arduino.SerialEventNum);
					StringBuilder sb=new StringBuilder();
					Process pros= rt.exec(gameToRunLocation+gameName);
					while(pros.isAlive());
					JOptionPane.showMessageDialog(null, "gameHasFinished");
					gameResultsFile=new File(gameToRunLocation+"Results.txt");
					Scanner s=new Scanner(gameResultsFile);
					while(s.hasNextLine()){
						sb.append(s.nextLine());
					}
					gameResultsFile.delete();
				//	system.setResultsForGame(sb.toString());
					run=false;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

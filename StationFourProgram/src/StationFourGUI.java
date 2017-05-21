import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;

public class StationFourGUI extends JFrame {

	private JPanel contentPane;
	private static String gameResultsStr;
	private static String gameToRunLocation;
	private static String gameName;
	private static Runtime rt;
	private static File gameResultsFile;

	public static void main(String[] args) {
		
		try {
			StationFourGUI frame = new StationFourGUI();
			frame.setVisible(true);
			Thread t=new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						gameToRunLocation="Game/" ;
						gameName="Station2Game";
						stationFourActivator();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "cant run main");
						e.printStackTrace();
					}
				}
			});
			t.start();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "cant run main");
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public StationFourGUI() {//gui allll gui
		setTitle("Station 2 State");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 362, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.BOLD, 15));
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblStatus, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblStatus, 10, SpringLayout.WEST, contentPane);
		contentPane.add(lblStatus);

		JLabel lblisItOn = new JLabel("is it on?");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblisItOn, 0, SpringLayout.NORTH, lblStatus);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblisItOn, 17, SpringLayout.EAST, lblStatus);
		lblisItOn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblisItOn);

		JLabel lblIP = new JLabel("IP:");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIP, 13, SpringLayout.SOUTH, lblStatus);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIP, 0, SpringLayout.WEST, lblStatus);
		lblIP.setFont(new Font("Tahoma", Font.BOLD, 15));
		contentPane.add(lblIP);

		JLabel lblHereIsTheIP = new JLabel("Here is the IP");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblHereIsTheIP, 0, SpringLayout.WEST, lblisItOn);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblHereIsTheIP, 0, SpringLayout.SOUTH, lblIP);
		lblHereIsTheIP.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblHereIsTheIP);

		JButton btnStop = new JButton("Stop");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnStop, 10, SpringLayout.NORTH, lblStatus);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnStop, 58, SpringLayout.EAST, lblHereIsTheIP);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnStop, 0, SpringLayout.SOUTH, lblIP);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnStop, 139, SpringLayout.EAST, lblHereIsTheIP);

		contentPane.add(btnStop);
	}

	public static void stationFourActivator() throws Exception{
		String WhenToStart=null; 
		ServerSocket welcomeSocket = new ServerSocket(6789); 
		StringBuilder sb=new StringBuilder();
		rt=Runtime.getRuntime();
		while(true) {
			sb=new StringBuilder();
			Socket connectionSocket = welcomeSocket.accept(); //will wait here until a tcp request from System
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
			DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			WhenToStart = inFromClient.readLine().trim();//the line got from M-system
			if(WhenToStart.equals("1")){
				Process pros= rt.exec("Game\\Station2Game.exe");//Start Game
				while(pros.isAlive());//stay here will the game is running				
				gameResultsFile=new File("Results.txt");
				while(!gameResultsFile.exists());//sray here will game is on
				Scanner s=new Scanner(gameResultsFile);
				while(s.hasNextLine()){ //read file
					sb.append(s.nextLine()+"B");
				}
				s.close();//close scanner
				Thread.sleep(1000);//sleep for 1 sec to close game;
				boolean boo=gameResultsFile.delete();//delete the file
				outToClient.writeChars(sb.toString()+"\n");//give result back to pc
			}
		}
	}
}

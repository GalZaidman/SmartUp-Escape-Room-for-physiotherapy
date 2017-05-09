import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class StationGUI extends JFrame {

	private JPanel contentPane;
	private static SerialTest2 arduinoConnection;
	private static boolean isRunning;
	private static String results;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		/*EventQueue.invokeLater(new Runnable() {
			public void run() {*/
				try {
					StationGUI frame = new StationGUI();
					frame.setVisible(true);
					Thread t=new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								stationOnActivator();
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "11111111111111");
							}
						}
					});
					t.start();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "11111111111111");
				}
			}
	//	});
	//}

	/**
	 * Create the frame.
	 */
	public StationGUI() {
		arduinoConnection=new SerialTest2(this);
		arduinoConnection.initialize();
		setTitle("Station 1 State");
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
	
	
	public static void stationOnActivator() throws Exception{

		String orderOfOperation=null; 
		isRunning=true;

		ServerSocket welcomeSocket = new ServerSocket(6789); 
		while(true) { 
	        Socket connectionSocket = welcomeSocket.accept(); 
		    BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream())); 
		    DataOutputStream  outToClient = new DataOutputStream(connectionSocket.getOutputStream()); 
		    orderOfOperation = inFromClient.readLine();
	    	JOptionPane.showMessageDialog(null, "1");
		    if(orderOfOperation!=null)
		    	JOptionPane.showMessageDialog(null, orderOfOperation);
		    if(orderOfOperation.length()>3){
				JOptionPane.showMessageDialog(null,orderOfOperation);
		    	arduinoConnection.writeData(orderOfOperation);
		    	while(isRunning){
		    	}
		    }  
		    outToClient.writeBytes(results);
	        }
	}
	
	public void setIsRunning(boolean b){
		isRunning=b;
	}
	
	public void setResults(String results){
		this.results=results;
	}

}

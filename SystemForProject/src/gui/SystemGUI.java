package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;
import javax.swing.JSplitPane;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.TreeSet;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.border.EtchedBorder;

import files.StationTwoHandThread;
import files.Inventory;
import files.Meeting;
import files.SerialTest2;
import files.StationOneHeadThread;
import files.Trainee;
import gnu.io.SerialPortEvent;
import javafx.scene.control.ComboBox;

import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class SystemGUI extends JFrame {

	private JPanel contentPane,pnlAction,pnlMenu,pnlBlank,pnlPractice,pnlSearch,pnlNewTrainee,pnlS1,pnlS2,pnlS3,pnlS4;
	private JTextField txtFN,txtLN,txtID;
	private JTextPane txtOrderOfOperation,textTrineeSearch,txtOrderOfOpS1;
	private JScrollPane scpTextScroller;
	private JLabel lblTrainee,lblStations,lblStation1,lblHeadMovement,lblStation3,lblTheMaze,lblNewLabel,lblFName,lblLName,lblId,lblAddTrainee,lblRemoveTrainee,lblPractice,lblTrineeS2,label_1,lblTrineeS3,label_2,lblTheMaze_1,label,label_3;
	private JButton btnStartPractice,btnSearch,btnAddRemoveTrainee,btn1,btn2,btn3,btn4,btn5,btnHistory,btnStart,btnEnterNewTrinee,btnStartS1,btnStartS2,btnStartS3,btnStartS4,btnRemove,btnHistoryAdd,btn1S1,btn2S1,btn3S1,btn4S1,btn5S1,btnStartSs1,btnHistoryS2,btnStartSs2,btnHistoryS3,btnStartSs3,btnHistoryS4,btnStartSs4,btnHistoryS1;
	private JSplitPane splitPane;
	private JComboBox<String> cbxPickTraineeS1,cbxTrainee,cbxStation3,cbxTrineeSearch,cbxRemoveTrinee,cbxPracticeS1,cbxTrineeS2,cbxPracticeS2,cbxTrineeS3,cbxMaze,cbxPracticeS3,cbxTrineeS4,cbxPracticeS4;
	private JCheckBox chckbxHead ,chckbxHand,chckbxArm,chckbxFootlegs,cbxSoundStation1,cbxSoundStation3,chbxSoundS1,chbxSound3;
	private CardLayout cLayout;
	private Vector<JComboBox> comboBoxToUpdate, meetingComboBoxToUpdate;

	private static Inventory inventory;
	private static SystemGUI frame;
	private Thread s1Thread,s2Thread,s3Thread,s4Thread;
	private boolean station1Selected,station2Selected,station3Selected,station4Selected,station2Fin,isInitTrinee,isInitMeeting;
	private Trainee currentTrineeS1,currentTrineeS2,currentTrineeS3,currentTrineeS4;
	//s1
	private String s1OperationOrder;
	private boolean s1Sound;
	//s3
	private boolean s3Sound;
	private int s3LanePicked;

	private JSeparator separator_3,separator_4,separator_5,separator_6;


	public static void main(String[] args) {
		inventory=new Inventory();
		frame = new SystemGUI();
		frame.setVisible(true);
	}

	public SystemGUI() {

		isInitMeeting=false;
		isInitTrinee=false;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);

		splitPane = new JSplitPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, splitPane, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, splitPane, 0, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, splitPane, 0, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, splitPane, 0, SpringLayout.EAST, contentPane);
		contentPane.add(splitPane);

		splitPane.setDividerLocation(130);
		splitPane.setEnabled(false);
		pnlMenu = new JPanel();
		splitPane.setLeftComponent(pnlMenu);
		SpringLayout sl_pnlMenu = new SpringLayout();
		pnlMenu.setLayout(sl_pnlMenu);

		btnStartPractice = new JButton("Start Practice");
		sl_pnlMenu.putConstraint(SpringLayout.SOUTH, btnStartPractice, 30, SpringLayout.NORTH, pnlMenu);
		sl_pnlMenu.putConstraint(SpringLayout.EAST, btnStartPractice, -5, SpringLayout.EAST, pnlMenu);
		sl_pnlMenu.putConstraint(SpringLayout.NORTH, btnStartPractice, 5, SpringLayout.NORTH, pnlMenu);
		sl_pnlMenu.putConstraint(SpringLayout.WEST, btnStartPractice, 5, SpringLayout.WEST, pnlMenu);
		pnlMenu.add(btnStartPractice);

		btnSearch = new JButton("Search");
		sl_pnlMenu.putConstraint(SpringLayout.NORTH, btnSearch, 5, SpringLayout.SOUTH, btnStartPractice);
		sl_pnlMenu.putConstraint(SpringLayout.WEST, btnSearch, 0, SpringLayout.WEST, btnStartPractice);
		sl_pnlMenu.putConstraint(SpringLayout.SOUTH, btnSearch, 30, SpringLayout.SOUTH, btnStartPractice);
		sl_pnlMenu.putConstraint(SpringLayout.EAST, btnSearch, 0, SpringLayout.EAST, btnStartPractice);
		pnlMenu.add(btnSearch);

		btnAddRemoveTrainee = new JButton("<html>Add\\Remove<br />Trainee</html>");
		sl_pnlMenu.putConstraint(SpringLayout.NORTH, btnAddRemoveTrainee, 6, SpringLayout.SOUTH, btnSearch);
		sl_pnlMenu.putConstraint(SpringLayout.WEST, btnAddRemoveTrainee, 0, SpringLayout.WEST, btnStartPractice);
		sl_pnlMenu.putConstraint(SpringLayout.SOUTH, btnAddRemoveTrainee, 48, SpringLayout.SOUTH, btnSearch);
		sl_pnlMenu.putConstraint(SpringLayout.EAST, btnAddRemoveTrainee, 0, SpringLayout.EAST, btnSearch);
		pnlMenu.add(btnAddRemoveTrainee);

		btnStartS1 = new JButton("Start Station 1");
		sl_pnlMenu.putConstraint(SpringLayout.NORTH, btnStartS1, 5, SpringLayout.SOUTH, btnAddRemoveTrainee);
		sl_pnlMenu.putConstraint(SpringLayout.WEST, btnStartS1, 0, SpringLayout.WEST, btnStartPractice);
		sl_pnlMenu.putConstraint(SpringLayout.SOUTH, btnStartS1, 30, SpringLayout.SOUTH, btnAddRemoveTrainee);
		sl_pnlMenu.putConstraint(SpringLayout.EAST, btnStartS1, 0, SpringLayout.EAST, btnStartPractice);
		pnlMenu.add(btnStartS1);

		btnStartS2 = new JButton("Start Station 2");
		sl_pnlMenu.putConstraint(SpringLayout.NORTH, btnStartS2, 5, SpringLayout.SOUTH, btnStartS1);
		sl_pnlMenu.putConstraint(SpringLayout.WEST, btnStartS2, 0, SpringLayout.WEST, btnStartPractice);
		sl_pnlMenu.putConstraint(SpringLayout.SOUTH, btnStartS2, 30, SpringLayout.SOUTH, btnStartS1);
		sl_pnlMenu.putConstraint(SpringLayout.EAST, btnStartS2, 0, SpringLayout.EAST, btnStartPractice);
		pnlMenu.add(btnStartS2);

		btnStartS3 = new JButton("Start Station 3");
		sl_pnlMenu.putConstraint(SpringLayout.NORTH, btnStartS3, 6, SpringLayout.SOUTH, btnStartS2);
		sl_pnlMenu.putConstraint(SpringLayout.WEST, btnStartS3, 0, SpringLayout.WEST, btnStartPractice);
		sl_pnlMenu.putConstraint(SpringLayout.EAST, btnStartS3, 0, SpringLayout.EAST, btnStartPractice);
		pnlMenu.add(btnStartS3);

		btnStartS4 = new JButton("Start Station 4");
		sl_pnlMenu.putConstraint(SpringLayout.NORTH, btnStartS4, 5, SpringLayout.SOUTH, btnStartS3);
		sl_pnlMenu.putConstraint(SpringLayout.WEST, btnStartS4, 0, SpringLayout.WEST, btnStartPractice);
		sl_pnlMenu.putConstraint(SpringLayout.SOUTH, btnStartS4, 30, SpringLayout.SOUTH, btnStartS3);
		sl_pnlMenu.putConstraint(SpringLayout.EAST, btnStartS4, 0, SpringLayout.EAST, btnStartS3);
		pnlMenu.add(btnStartS4);

		pnlAction = new JPanel();
		splitPane.setRightComponent(pnlAction);
		cLayout=new CardLayout(0,0);
		pnlAction.setLayout(cLayout);


		pnlPractice = new JPanel();
		SpringLayout sl_pnlPractice = new SpringLayout();
		pnlPractice.setLayout(sl_pnlPractice);

		JSeparator Separator1 = new JSeparator();
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, Separator1, 30, SpringLayout.NORTH, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, Separator1, 5, SpringLayout.WEST, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.EAST, Separator1, -5, SpringLayout.EAST, pnlPractice);
		pnlPractice.add(Separator1);

		lblTrainee = new JLabel("Trainee:");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, lblTrainee, 5, SpringLayout.NORTH, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, lblTrainee, 10, SpringLayout.WEST, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, lblTrainee, -5, SpringLayout.NORTH, Separator1);
		lblTrainee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlPractice.add(lblTrainee);

		cbxTrainee = new JComboBox<String>();

		sl_pnlPractice.putConstraint(SpringLayout.WEST, cbxTrainee, 80, SpringLayout.WEST, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.EAST, cbxTrainee, -100, SpringLayout.EAST, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.EAST, lblTrainee, -6, SpringLayout.WEST, cbxTrainee);
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, cbxTrainee, 5, SpringLayout.NORTH, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, cbxTrainee, -5, SpringLayout.NORTH, Separator1);
		pnlPractice.add(cbxTrainee);

		lblStations = new JLabel("Stations:");
		lblStations.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, lblStations, 5, SpringLayout.SOUTH, Separator1);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, lblStations, 10, SpringLayout.WEST, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, lblStations, 25, SpringLayout.SOUTH, Separator1);
		pnlPractice.add(lblStations);

		chckbxHead = new JCheckBox("1- Head Movement");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, chckbxHead, 0, SpringLayout.NORTH, lblStations);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, chckbxHead, 0, SpringLayout.WEST, cbxTrainee);
		pnlPractice.add(chckbxHead);

		chckbxHand = new JCheckBox("2- Hand");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, chckbxHand, 0, SpringLayout.NORTH, chckbxHead);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, chckbxHand, 3, SpringLayout.EAST, chckbxHead);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, chckbxHand, 0, SpringLayout.SOUTH, chckbxHead);
		pnlPractice.add(chckbxHand);

		chckbxArm = new JCheckBox("3- Maze");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, chckbxArm, 0, SpringLayout.NORTH, chckbxHand);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, chckbxArm, 3, SpringLayout.EAST, chckbxHand);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, chckbxArm, 0, SpringLayout.SOUTH, chckbxHand);
		pnlPractice.add(chckbxArm);

		chckbxFootlegs = new JCheckBox("4- Foot/Legs");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, chckbxFootlegs, 0, SpringLayout.NORTH, chckbxArm);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, chckbxFootlegs, 3, SpringLayout.EAST, chckbxArm);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, chckbxFootlegs, 0, SpringLayout.SOUTH, chckbxArm);
		pnlPractice.add(chckbxFootlegs);

		JSeparator separator = new JSeparator();
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, separator, 5, SpringLayout.SOUTH, chckbxHead);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, separator, 0, SpringLayout.WEST, Separator1);
		sl_pnlPractice.putConstraint(SpringLayout.EAST, separator, 0, SpringLayout.EAST, Separator1);
		pnlPractice.add(separator);

		lblStation1 = new JLabel("Station 1:");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, lblStation1, 5, SpringLayout.SOUTH, separator);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, lblStation1, 0, SpringLayout.WEST, lblStations);
		lblStation1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlPractice.add(lblStation1);

		lblHeadMovement = new JLabel("Head Movement");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, lblHeadMovement, 4, SpringLayout.SOUTH, lblStation1);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, lblHeadMovement, 0, SpringLayout.WEST, lblStation1);
		lblHeadMovement.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlPractice.add(lblHeadMovement);

		lblStation3 = new JLabel("Station 3:");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, lblStation3, 56, SpringLayout.SOUTH, lblHeadMovement);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, lblStation3, 10, SpringLayout.WEST, pnlPractice);
		lblStation3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlPractice.add(lblStation3);

		lblTheMaze = new JLabel("The Maze");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, lblTheMaze, 4, SpringLayout.SOUTH, lblStation3);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, lblTheMaze, 0, SpringLayout.WEST, lblStation3);
		lblTheMaze.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlPractice.add(lblTheMaze);

		btn1 = new JButton("1");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, btn1, 6, SpringLayout.SOUTH, separator);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, btn1, 16, SpringLayout.EAST, lblHeadMovement);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, btn1, 0, SpringLayout.SOUTH, lblHeadMovement);
		pnlPractice.add(btn1);

		btn2 = new JButton("2");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, btn2, 0, SpringLayout.NORTH, btn1);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, btn2, 10, SpringLayout.EAST, btn1);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, btn2, 0, SpringLayout.SOUTH, btn1);
		pnlPractice.add(btn2);

		btn3 = new JButton("3");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, btn3, 0, SpringLayout.NORTH, btn1);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, btn3, 10, SpringLayout.EAST, btn2);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, btn3, 0, SpringLayout.SOUTH, btn1);
		pnlPractice.add(btn3);

		btn4 = new JButton("4");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, btn4, 0, SpringLayout.NORTH, btn1);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, btn4, 10, SpringLayout.EAST, btn3);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, btn4, 0, SpringLayout.SOUTH, btn1);
		pnlPractice.add(btn4);

		btn5 = new JButton("5");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, btn5, 0, SpringLayout.NORTH, btn1);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, btn5, 10, SpringLayout.EAST, btn4);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, btn5, 0, SpringLayout.SOUTH, btn1);
		pnlPractice.add(btn5);

		txtOrderOfOperation = new JTextPane();
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, txtOrderOfOperation, 35, SpringLayout.SOUTH, btn1);
		txtOrderOfOperation.setEditable(false);
		txtOrderOfOperation.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, txtOrderOfOperation, 11, SpringLayout.SOUTH, btn1);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, txtOrderOfOperation, 0, SpringLayout.WEST, btn1);
		sl_pnlPractice.putConstraint(SpringLayout.EAST, txtOrderOfOperation, 0, SpringLayout.EAST, btn5);
		pnlPractice.add(txtOrderOfOperation);

		btnHistory = new JButton("History");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, btnHistory, 0, SpringLayout.NORTH, cbxTrainee);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, btnHistory, 5, SpringLayout.EAST, cbxTrainee);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, btnHistory, 0, SpringLayout.SOUTH, cbxTrainee);
		sl_pnlPractice.putConstraint(SpringLayout.EAST, btnHistory, 0, SpringLayout.EAST, Separator1);
		pnlPractice.add(btnHistory);

		cbxStation3 = new JComboBox<String>();

		cbxStation3.addItem("Lane1");
		cbxStation3.addItem("Lane2");
		cbxStation3.addItem("Lane3");
		cbxStation3.addItem("Lane4");

		pnlBlank = new JPanel();
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, cbxStation3, 0, SpringLayout.NORTH, lblStation3);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, cbxStation3, 0, SpringLayout.WEST, btn1);
		sl_pnlPractice.putConstraint(SpringLayout.EAST, cbxStation3, 0, SpringLayout.EAST, btn5);
		pnlPractice.add(cbxStation3);

		btnStart = new JButton("START");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, btnStart, -50, SpringLayout.SOUTH, pnlPractice);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, btnStart, 0, SpringLayout.WEST, lblStation1);
		sl_pnlPractice.putConstraint(SpringLayout.SOUTH, btnStart, -20, SpringLayout.SOUTH, pnlPractice);
		pnlPractice.add(btnStart);

		cbxSoundStation1 = new JCheckBox("Sound");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, cbxSoundStation1, 5, SpringLayout.NORTH, btn5);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, cbxSoundStation1, 10, SpringLayout.EAST, btn5);
		pnlPractice.add(cbxSoundStation1);

		cbxSoundStation3 = new JCheckBox("Sound");
		sl_pnlPractice.putConstraint(SpringLayout.NORTH, cbxSoundStation3, 5, SpringLayout.NORTH, cbxStation3);
		sl_pnlPractice.putConstraint(SpringLayout.WEST, cbxSoundStation3, 0, SpringLayout.WEST, cbxSoundStation1);
		pnlPractice.add(cbxSoundStation3);

		pnlSearch = new JPanel();

		SpringLayout sl_pnlSearch = new SpringLayout();
		pnlSearch.setLayout(sl_pnlSearch);

		lblNewLabel = new JLabel("Trainee:");
		sl_pnlSearch.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, pnlSearch);
		sl_pnlSearch.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, pnlSearch);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlSearch.add(lblNewLabel);

		cbxTrineeSearch = new JComboBox<String>();
		sl_pnlSearch.putConstraint(SpringLayout.NORTH, cbxTrineeSearch, 10, SpringLayout.NORTH, pnlSearch);
		sl_pnlSearch.putConstraint(SpringLayout.WEST, cbxTrineeSearch, 15, SpringLayout.EAST, lblNewLabel);
		sl_pnlSearch.putConstraint(SpringLayout.EAST, cbxTrineeSearch, -50, SpringLayout.EAST, pnlSearch);
		pnlSearch.add(cbxTrineeSearch);

		JSeparator separator_1 = new JSeparator();
		sl_pnlSearch.putConstraint(SpringLayout.NORTH, separator_1, 10, SpringLayout.SOUTH, lblNewLabel);
		sl_pnlSearch.putConstraint(SpringLayout.WEST, separator_1, 5, SpringLayout.WEST, pnlSearch);
		sl_pnlSearch.putConstraint(SpringLayout.EAST, separator_1, -5, SpringLayout.EAST, pnlSearch);
		pnlSearch.add(separator_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		sl_pnlSearch.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.SOUTH, separator_1);
		sl_pnlSearch.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, lblNewLabel);
		sl_pnlSearch.putConstraint(SpringLayout.SOUTH, scrollPane, -10, SpringLayout.SOUTH, pnlSearch);
		sl_pnlSearch.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, cbxTrineeSearch);
		pnlSearch.add(scrollPane);
		pnlNewTrainee = new JPanel();
		SpringLayout sl_pnlNewTrainee = new SpringLayout();
		pnlNewTrainee.setLayout(sl_pnlNewTrainee);

		lblFName = new JLabel("First Name:");
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, lblFName, 46, SpringLayout.NORTH, pnlNewTrainee);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, lblFName, 10, SpringLayout.WEST, pnlNewTrainee);
		lblFName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlNewTrainee.add(lblFName);

		lblLName = new JLabel("Last Name:");
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, lblLName, 19, SpringLayout.SOUTH, lblFName);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, lblLName, 0, SpringLayout.WEST, lblFName);
		lblLName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlNewTrainee.add(lblLName);

		txtFN = new JTextField();
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, txtFN, 46, SpringLayout.NORTH, pnlNewTrainee);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, txtFN, 11, SpringLayout.EAST, lblFName);
		sl_pnlNewTrainee.putConstraint(SpringLayout.EAST, txtFN, 259, SpringLayout.EAST, lblFName);
		pnlNewTrainee.add(txtFN);
		txtFN.setColumns(10);

		txtLN = new JTextField();
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, txtLN, 17, SpringLayout.SOUTH, txtFN);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, txtLN, 11, SpringLayout.EAST, lblLName);
		sl_pnlNewTrainee.putConstraint(SpringLayout.EAST, txtLN, 0, SpringLayout.EAST, txtFN);
		txtLN.setColumns(10);
		pnlNewTrainee.add(txtLN);

		btnEnterNewTrinee = new JButton("Enter");
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, btnEnterNewTrinee, 51, SpringLayout.SOUTH, lblLName);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, btnEnterNewTrinee, 0, SpringLayout.WEST, lblFName);
		btnEnterNewTrinee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlNewTrainee.add(btnEnterNewTrinee);

		lblId = new JLabel("id :");
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, lblId, 122, SpringLayout.NORTH, pnlNewTrainee);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, lblId, 0, SpringLayout.WEST, lblFName);
		sl_pnlNewTrainee.putConstraint(SpringLayout.SOUTH, lblId, -13, SpringLayout.NORTH, btnEnterNewTrinee);
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlNewTrainee.add(lblId);

		txtID = new JTextField();
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, txtID, 1, SpringLayout.NORTH, lblId);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, txtID, 0, SpringLayout.WEST, txtFN);
		sl_pnlNewTrainee.putConstraint(SpringLayout.EAST, txtID, 0, SpringLayout.EAST, txtFN);
		txtID.setColumns(10);
		pnlNewTrainee.add(txtID);

		lblAddTrainee = new JLabel("Add Trainee:");
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, lblAddTrainee, 10, SpringLayout.NORTH, pnlNewTrainee);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, lblAddTrainee, 0, SpringLayout.WEST, lblFName);
		lblAddTrainee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlNewTrainee.add(lblAddTrainee);

		lblRemoveTrainee = new JLabel("Remove Trainee:");
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, lblRemoveTrainee, 34, SpringLayout.SOUTH, btnEnterNewTrinee);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, lblRemoveTrainee, 0, SpringLayout.WEST, lblFName);
		lblRemoveTrainee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlNewTrainee.add(lblRemoveTrainee);

		cbxRemoveTrinee = new JComboBox<String>();
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, cbxRemoveTrinee, 16, SpringLayout.SOUTH, lblRemoveTrainee);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, cbxRemoveTrinee, 0, SpringLayout.WEST, lblFName);
		sl_pnlNewTrainee.putConstraint(SpringLayout.EAST, cbxRemoveTrinee, -17, SpringLayout.EAST, txtFN);
		pnlNewTrainee.add(cbxRemoveTrinee);

		btnHistoryAdd = new JButton("History");
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, btnHistoryAdd, -1, SpringLayout.NORTH, cbxRemoveTrinee);
		sl_pnlNewTrainee.putConstraint(SpringLayout.EAST, btnHistoryAdd, -10, SpringLayout.EAST, pnlNewTrainee);
		pnlNewTrainee.add(btnHistoryAdd);

		btnRemove = new JButton("Remove");
		sl_pnlNewTrainee.putConstraint(SpringLayout.NORTH, btnRemove, 21, SpringLayout.SOUTH, cbxRemoveTrinee);
		sl_pnlNewTrainee.putConstraint(SpringLayout.WEST, btnRemove, 0, SpringLayout.WEST, lblFName);
		btnRemove.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pnlNewTrainee.add(btnRemove);

		pnlS1 = new JPanel();
		SpringLayout sl_pnlS1 = new SpringLayout();
		pnlS1.setLayout(sl_pnlS1);

		JLabel lblTrinee = new JLabel("Trinee:");
		sl_pnlS1.putConstraint(SpringLayout.WEST, lblTrinee, 10, SpringLayout.WEST, pnlS1);
		lblTrinee.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlS1.add(lblTrinee);

		cbxPickTraineeS1 = new JComboBox();
		sl_pnlS1.putConstraint(SpringLayout.NORTH, cbxPickTraineeS1, 10, SpringLayout.NORTH, pnlS1);
		sl_pnlS1.putConstraint(SpringLayout.NORTH, lblTrinee, 0, SpringLayout.NORTH, cbxPickTraineeS1);
		sl_pnlS1.putConstraint(SpringLayout.EAST, lblTrinee, -6, SpringLayout.WEST, cbxPickTraineeS1);
		sl_pnlS1.putConstraint(SpringLayout.WEST, cbxPickTraineeS1, 80, SpringLayout.WEST, pnlS1);
		sl_pnlS1.putConstraint(SpringLayout.EAST, cbxPickTraineeS1, -100, SpringLayout.EAST, pnlS1);
		pnlS1.add(cbxPickTraineeS1);

		btnHistoryS1 = new JButton("History");
		sl_pnlS1.putConstraint(SpringLayout.NORTH, btnHistoryS1, 0, SpringLayout.NORTH, lblTrinee);
		sl_pnlS1.putConstraint(SpringLayout.WEST, btnHistoryS1, 6, SpringLayout.EAST, cbxPickTraineeS1);
		sl_pnlS1.putConstraint(SpringLayout.SOUTH, btnHistoryS1, 0, SpringLayout.SOUTH, cbxPickTraineeS1);
		sl_pnlS1.putConstraint(SpringLayout.EAST, btnHistoryS1, -5, SpringLayout.EAST, pnlS1);
		pnlS1.add(btnHistoryS1);

		JSeparator separator_2 = new JSeparator();
		sl_pnlS1.putConstraint(SpringLayout.NORTH, separator_2, 13, SpringLayout.SOUTH, lblTrinee);
		sl_pnlS1.putConstraint(SpringLayout.WEST, separator_2, 5, SpringLayout.WEST, pnlS1);
		sl_pnlS1.putConstraint(SpringLayout.EAST, separator_2, -5, SpringLayout.EAST, pnlS1);
		pnlS1.add(separator_2);

		lblPractice = new JLabel("Practice:");
		lblPractice.setFont(new Font("Tahoma", Font.PLAIN, 13));
		sl_pnlS1.putConstraint(SpringLayout.NORTH, lblPractice, 8, SpringLayout.SOUTH, separator_2);
		sl_pnlS1.putConstraint(SpringLayout.WEST, lblPractice, 10, SpringLayout.WEST, pnlS1);
		pnlS1.add(lblPractice);

		cbxPracticeS1 = new JComboBox();
		sl_pnlS1.putConstraint(SpringLayout.NORTH, cbxPracticeS1, 0, SpringLayout.NORTH, lblPractice);
		sl_pnlS1.putConstraint(SpringLayout.WEST, cbxPracticeS1, 0, SpringLayout.WEST, cbxPickTraineeS1);
		sl_pnlS1.putConstraint(SpringLayout.SOUTH, cbxPracticeS1, 5, SpringLayout.SOUTH, lblPractice);
		sl_pnlS1.putConstraint(SpringLayout.EAST, cbxPracticeS1, 0, SpringLayout.EAST, cbxPickTraineeS1);
		pnlS1.add(cbxPracticeS1);

		btn1S1 = new JButton("1");
		sl_pnlS1.putConstraint(SpringLayout.NORTH, btn1S1, 10, SpringLayout.SOUTH, cbxPracticeS1);
		sl_pnlS1.putConstraint(SpringLayout.WEST, btn1S1, 0, SpringLayout.WEST, cbxPickTraineeS1);
		sl_pnlS1.putConstraint(SpringLayout.SOUTH, btn1S1, -259, SpringLayout.SOUTH, pnlS1);
		sl_pnlS1.putConstraint(SpringLayout.EAST, btn1S1, 109, SpringLayout.WEST, lblTrinee);
		pnlS1.add(btn1S1);

		btn2S1 = new JButton("2");
		sl_pnlS1.putConstraint(SpringLayout.NORTH, btn2S1, 0, SpringLayout.NORTH, btn1S1);
		sl_pnlS1.putConstraint(SpringLayout.WEST, btn2S1, 10, SpringLayout.EAST, btn1S1);
		sl_pnlS1.putConstraint(SpringLayout.SOUTH, btn2S1, 0, SpringLayout.SOUTH, btn1S1);
		pnlS1.add(btn2S1);

		btn3S1 = new JButton("3");
		sl_pnlS1.putConstraint(SpringLayout.NORTH, btn3S1, 0, SpringLayout.NORTH, btn1S1);
		sl_pnlS1.putConstraint(SpringLayout.WEST, btn3S1, 10, SpringLayout.EAST, btn2S1);
		sl_pnlS1.putConstraint(SpringLayout.SOUTH, btn3S1, 0, SpringLayout.SOUTH, btn2S1);
		pnlS1.add(btn3S1);

		btn4S1 = new JButton("4");
		sl_pnlS1.putConstraint(SpringLayout.NORTH, btn4S1, 0, SpringLayout.NORTH, btn1S1);
		sl_pnlS1.putConstraint(SpringLayout.WEST, btn4S1, 10, SpringLayout.EAST, btn3S1);
		sl_pnlS1.putConstraint(SpringLayout.SOUTH, btn4S1, 0, SpringLayout.SOUTH, btn1S1);
		pnlS1.add(btn4S1);

		btn5S1 = new JButton("5");
		sl_pnlS1.putConstraint(SpringLayout.NORTH, btn5S1, 0, SpringLayout.NORTH, btn1S1);
		sl_pnlS1.putConstraint(SpringLayout.WEST, btn5S1, 10, SpringLayout.EAST, btn4S1);
		sl_pnlS1.putConstraint(SpringLayout.SOUTH, btn5S1, 0, SpringLayout.SOUTH, btn1S1);
		pnlS1.add(btn5S1);

		chbxSoundS1 = new JCheckBox("Sound");
		sl_pnlS1.putConstraint(SpringLayout.NORTH, chbxSoundS1, 8, SpringLayout.NORTH, btn1S1);
		sl_pnlS1.putConstraint(SpringLayout.EAST, chbxSoundS1, -23, SpringLayout.EAST, pnlS1);
		pnlS1.add(chbxSoundS1);

		txtOrderOfOpS1 = new JTextPane();
		sl_pnlS1.putConstraint(SpringLayout.WEST, txtOrderOfOpS1, 80, SpringLayout.WEST, pnlS1);
		sl_pnlS1.putConstraint(SpringLayout.EAST, txtOrderOfOpS1, 0, SpringLayout.EAST, cbxPracticeS1);
		sl_pnlS1.putConstraint(SpringLayout.NORTH, txtOrderOfOpS1, 11, SpringLayout.SOUTH, btn1S1);
		txtOrderOfOpS1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnlS1.add(txtOrderOfOpS1);

		btnStartSs1 = new JButton("START");
		sl_pnlS1.putConstraint(SpringLayout.NORTH, btnStartSs1, -50, SpringLayout.SOUTH, pnlS1);
		sl_pnlS1.putConstraint(SpringLayout.WEST, btnStartSs1, -100, SpringLayout.EAST, pnlS1);
		sl_pnlS1.putConstraint(SpringLayout.SOUTH, btnStartSs1, -10, SpringLayout.SOUTH, pnlS1);
		sl_pnlS1.putConstraint(SpringLayout.EAST, btnStartSs1, -10, SpringLayout.EAST, pnlS1);
		pnlS1.add(btnStartSs1);

		pnlS2 = new JPanel();
		SpringLayout sl_pnlS2 = new SpringLayout();
		pnlS2.setLayout(sl_pnlS2);

		lblTrineeS2 = new JLabel("Trinee:");
		sl_pnlS2.putConstraint(SpringLayout.NORTH, lblTrineeS2, 10, SpringLayout.NORTH, pnlS2);
		sl_pnlS2.putConstraint(SpringLayout.WEST, lblTrineeS2, 10, SpringLayout.WEST, pnlS2);
		lblTrineeS2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlS2.add(lblTrineeS2);

		cbxTrineeS2 = new JComboBox();
		sl_pnlS2.putConstraint(SpringLayout.NORTH, cbxTrineeS2, 0, SpringLayout.NORTH, lblTrineeS2);
		sl_pnlS2.putConstraint(SpringLayout.WEST, cbxTrineeS2, 80, SpringLayout.WEST, pnlS2);
		sl_pnlS2.putConstraint(SpringLayout.EAST, cbxTrineeS2, -100, SpringLayout.EAST, pnlS2);
		pnlS2.add(cbxTrineeS2);

		btnHistoryS2 = new JButton("History");
		sl_pnlS2.putConstraint(SpringLayout.NORTH, btnHistoryS2, 0, SpringLayout.NORTH, lblTrineeS2);
		sl_pnlS2.putConstraint(SpringLayout.WEST, btnHistoryS2, 11, SpringLayout.EAST, cbxTrineeS2);
		sl_pnlS2.putConstraint(SpringLayout.SOUTH, btnHistoryS2, 0, SpringLayout.SOUTH, cbxTrineeS2);
		sl_pnlS2.putConstraint(SpringLayout.EAST, btnHistoryS2, -5, SpringLayout.EAST, pnlS2);
		pnlS2.add(btnHistoryS2);

		separator_3 = new JSeparator();
		sl_pnlS2.putConstraint(SpringLayout.NORTH, separator_3, 10, SpringLayout.SOUTH, cbxTrineeS2);
		sl_pnlS2.putConstraint(SpringLayout.WEST, separator_3, 5, SpringLayout.WEST, pnlS2);
		sl_pnlS2.putConstraint(SpringLayout.EAST, separator_3, -5, SpringLayout.EAST, pnlS2);
		pnlS2.add(separator_3);

		label_1 = new JLabel("Practice:");
		sl_pnlS2.putConstraint(SpringLayout.NORTH, label_1, 5, SpringLayout.SOUTH, separator_3);
		sl_pnlS2.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, lblTrineeS2);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlS2.add(label_1);

		btnStartSs2 = new JButton("START");
		sl_pnlS2.putConstraint(SpringLayout.NORTH, btnStartSs2, -50, SpringLayout.SOUTH, pnlS2);
		sl_pnlS2.putConstraint(SpringLayout.WEST, btnStartSs2, -100, SpringLayout.EAST, pnlS2);
		sl_pnlS2.putConstraint(SpringLayout.SOUTH, btnStartSs2, -10, SpringLayout.SOUTH, pnlS2);
		sl_pnlS2.putConstraint(SpringLayout.EAST, btnStartSs2, -10, SpringLayout.EAST, pnlS2);
		pnlS2.add(btnStartSs2);

		cbxPracticeS2 = new JComboBox();
		sl_pnlS2.putConstraint(SpringLayout.NORTH, cbxPracticeS2, 6, SpringLayout.SOUTH, separator_3);
		sl_pnlS2.putConstraint(SpringLayout.WEST, cbxPracticeS2, 0, SpringLayout.WEST, cbxTrineeS2);
		sl_pnlS2.putConstraint(SpringLayout.EAST, cbxPracticeS2, 0, SpringLayout.EAST, cbxTrineeS2);
		pnlS2.add(cbxPracticeS2);

		pnlS3 = new JPanel();
		SpringLayout sl_pnlS3 = new SpringLayout();
		pnlS3.setLayout(sl_pnlS3);

		lblTrineeS3 = new JLabel("Trinee:");
		sl_pnlS3.putConstraint(SpringLayout.NORTH, lblTrineeS3, 10, SpringLayout.NORTH, pnlS3);
		sl_pnlS3.putConstraint(SpringLayout.WEST, lblTrineeS3, 10, SpringLayout.WEST, pnlS3);
		lblTrineeS3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlS3.add(lblTrineeS3);

		cbxTrineeS3 = new JComboBox();
		sl_pnlS3.putConstraint(SpringLayout.NORTH, cbxTrineeS3, 0, SpringLayout.NORTH, lblTrineeS3);
		sl_pnlS3.putConstraint(SpringLayout.WEST, cbxTrineeS3, 80, SpringLayout.WEST, pnlS3);
		sl_pnlS3.putConstraint(SpringLayout.EAST, cbxTrineeS3, -100, SpringLayout.EAST, pnlS3);
		pnlS3.add(cbxTrineeS3);

		btnHistoryS3 = new JButton("History");
		sl_pnlS3.putConstraint(SpringLayout.NORTH, btnHistoryS3, 0, SpringLayout.NORTH, lblTrineeS3);
		sl_pnlS3.putConstraint(SpringLayout.WEST, btnHistoryS3, 10, SpringLayout.EAST, cbxTrineeS3);
		sl_pnlS3.putConstraint(SpringLayout.SOUTH, btnHistoryS3, 0, SpringLayout.SOUTH, cbxTrineeS3);
		sl_pnlS3.putConstraint(SpringLayout.EAST, btnHistoryS3, -5, SpringLayout.EAST, pnlS3);
		pnlS3.add(btnHistoryS3);

		separator_4 = new JSeparator();
		sl_pnlS3.putConstraint(SpringLayout.NORTH, separator_4, 10, SpringLayout.SOUTH, cbxTrineeS3);
		sl_pnlS3.putConstraint(SpringLayout.WEST, separator_4, 5, SpringLayout.WEST, pnlS3);
		sl_pnlS3.putConstraint(SpringLayout.EAST, separator_4, -5, SpringLayout.EAST, pnlS3);
		pnlS3.add(separator_4);

		label_2 = new JLabel("Practice:");
		sl_pnlS3.putConstraint(SpringLayout.NORTH, label_2, 10, SpringLayout.SOUTH, separator_4);
		sl_pnlS3.putConstraint(SpringLayout.WEST, label_2, 0, SpringLayout.WEST, lblTrineeS3);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlS3.add(label_2);

		btnStartSs3 = new JButton("START");
		sl_pnlS3.putConstraint(SpringLayout.NORTH, btnStartSs3, -50, SpringLayout.SOUTH, pnlS3);
		sl_pnlS3.putConstraint(SpringLayout.WEST, btnStartSs3, -100, SpringLayout.EAST, pnlS3);
		sl_pnlS3.putConstraint(SpringLayout.SOUTH, btnStartSs3, -10, SpringLayout.SOUTH, pnlS3);
		sl_pnlS3.putConstraint(SpringLayout.EAST, btnStartSs3, -10, SpringLayout.EAST, pnlS3);
		pnlS3.add(btnStartSs3);

		cbxPracticeS3 = new JComboBox();
		sl_pnlS3.putConstraint(SpringLayout.NORTH, cbxPracticeS3, -1, SpringLayout.NORTH, label_2);
		sl_pnlS3.putConstraint(SpringLayout.WEST, cbxPracticeS3, 0, SpringLayout.WEST, cbxTrineeS3);
		sl_pnlS3.putConstraint(SpringLayout.EAST, cbxPracticeS3, 0, SpringLayout.EAST, cbxTrineeS3);
		pnlS3.add(cbxPracticeS3);

		cbxMaze = new JComboBox<String>();
		sl_pnlS3.putConstraint(SpringLayout.NORTH, cbxMaze, 22, SpringLayout.SOUTH, cbxPracticeS3);
		sl_pnlS3.putConstraint(SpringLayout.WEST, cbxMaze, 0, SpringLayout.WEST, cbxTrineeS3);
		sl_pnlS3.putConstraint(SpringLayout.EAST, cbxMaze, 0, SpringLayout.EAST, cbxTrineeS3);
		pnlS3.add(cbxMaze);

		chbxSound3 = new JCheckBox("Sound");
		sl_pnlS3.putConstraint(SpringLayout.WEST, chbxSound3, 0, SpringLayout.WEST, btnHistoryS3);
		pnlS3.add(chbxSound3);

		lblTheMaze_1 = new JLabel("The Maze:");
		sl_pnlS3.putConstraint(SpringLayout.NORTH, chbxSound3, -2, SpringLayout.NORTH, lblTheMaze_1);
		sl_pnlS3.putConstraint(SpringLayout.WEST, lblTheMaze_1, 0, SpringLayout.WEST, lblTrineeS3);
		sl_pnlS3.putConstraint(SpringLayout.SOUTH, lblTheMaze_1, 0, SpringLayout.SOUTH, cbxMaze);
		lblTheMaze_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlS3.add(lblTheMaze_1);

		separator_5 = new JSeparator();
		sl_pnlS3.putConstraint(SpringLayout.WEST, separator_5, 0, SpringLayout.WEST, separator_4);
		sl_pnlS3.putConstraint(SpringLayout.SOUTH, separator_5, -6, SpringLayout.NORTH, cbxMaze);
		sl_pnlS3.putConstraint(SpringLayout.EAST, separator_5, 0, SpringLayout.EAST, separator_4);
		pnlS3.add(separator_5);

		pnlS4 = new JPanel();
		SpringLayout sl_pnlS4 = new SpringLayout();
		pnlS4.setLayout(sl_pnlS4);

		label = new JLabel("Trinee:");
		sl_pnlS4.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, pnlS4);
		sl_pnlS4.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, pnlS4);
		sl_pnlS4.putConstraint(SpringLayout.EAST, label, 0, SpringLayout.EAST, pnlS4);
		label.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlS4.add(label);

		cbxTrineeS4 = new JComboBox();
		sl_pnlS4.putConstraint(SpringLayout.NORTH, cbxTrineeS4, 10, SpringLayout.NORTH, pnlS4);
		sl_pnlS4.putConstraint(SpringLayout.WEST, cbxTrineeS4, 80, SpringLayout.WEST, pnlS4);
		sl_pnlS4.putConstraint(SpringLayout.EAST, cbxTrineeS4, -100, SpringLayout.EAST, pnlS4);
		pnlS4.add(cbxTrineeS4);

		btnHistoryS4 = new JButton("History");
		sl_pnlS4.putConstraint(SpringLayout.NORTH, btnHistoryS4, 0, SpringLayout.NORTH, cbxTrineeS4);
		sl_pnlS4.putConstraint(SpringLayout.WEST, btnHistoryS4, 11, SpringLayout.EAST, cbxTrineeS4);
		sl_pnlS4.putConstraint(SpringLayout.EAST, btnHistoryS4, -5, SpringLayout.EAST, pnlS4);
		pnlS4.add(btnHistoryS4);

		separator_6 = new JSeparator();
		sl_pnlS4.putConstraint(SpringLayout.NORTH, separator_6, 10, SpringLayout.SOUTH, cbxTrineeS4);
		sl_pnlS4.putConstraint(SpringLayout.WEST, separator_6, 5, SpringLayout.WEST, pnlS4);
		sl_pnlS4.putConstraint(SpringLayout.EAST, separator_6, -5, SpringLayout.EAST, pnlS4);
		pnlS4.add(separator_6);

		label_3 = new JLabel("Practice:");
		sl_pnlS4.putConstraint(SpringLayout.NORTH, label_3, 10, SpringLayout.NORTH, separator_6);
		sl_pnlS4.putConstraint(SpringLayout.WEST, label_3, 0, SpringLayout.WEST, label);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		pnlS4.add(label_3);

		btnStartSs4 = new JButton("START");
		sl_pnlS4.putConstraint(SpringLayout.NORTH, btnStartSs4, -50, SpringLayout.SOUTH, pnlS4);
		sl_pnlS4.putConstraint(SpringLayout.WEST, btnStartSs4, -100, SpringLayout.EAST, pnlS4);
		sl_pnlS4.putConstraint(SpringLayout.SOUTH, btnStartSs4, -10, SpringLayout.SOUTH, pnlS4);
		sl_pnlS4.putConstraint(SpringLayout.EAST, btnStartSs4, -10, SpringLayout.EAST, pnlS4);
		pnlS4.add(btnStartSs4);

		cbxPracticeS4 = new JComboBox();
		sl_pnlS4.putConstraint(SpringLayout.NORTH, cbxPracticeS4, 0, SpringLayout.NORTH, label_3);
		sl_pnlS4.putConstraint(SpringLayout.WEST, cbxPracticeS4, 0, SpringLayout.WEST, cbxTrineeS4);
		sl_pnlS4.putConstraint(SpringLayout.EAST, cbxPracticeS4, 0, SpringLayout.EAST, cbxTrineeS4);
		pnlS4.add(cbxPracticeS4);

		textTrineeSearch = new JTextPane();
		scrollPane.setViewportView(textTrineeSearch);

		//add all pnls
		pnlAction.add(pnlBlank, "0");
		pnlAction.add(pnlPractice, "1");
		pnlAction.add(pnlSearch, "2");
		pnlAction.add(pnlNewTrainee, "3");
		pnlAction.add(pnlS1, "4");
		pnlAction.add(pnlS2, "5");
		pnlAction.add(pnlS3, "6");
		pnlAction.add(pnlS4, "7");

		//start pos
		swichActionActionLisinors();
		startPracticePnlActionLisinors();
		searchPnlActionLisinors();
		addTrineePnlActionLisinors();
		station1PnlActionLisinors();
		station2PnlActionLisinors();
		station3PnlActionLisinors();
		station4PnlActionLisinors();


		//pnl start practice
		cbxTrainee.setEnabled(false);
		btnHistory.setEnabled(false);
		chckbxHead.setEnabled(false);
		chckbxHand.setEnabled(false);
		chckbxArm.setEnabled(false);
		chckbxFootlegs.setEnabled(false);
		btn1.setEnabled(false);
		btn2.setEnabled(false);
		btn3.setEnabled(false);
		btn4.setEnabled(false);
		btn5.setEnabled(false);
		cbxSoundStation1.setEnabled(false);
		cbxSoundStation3.setEnabled(false);
		cbxStation3.setEnabled(false);
		txtOrderOfOperation.setEnabled(false);
		btnStart.setEnabled(false);	

		//pnl add
		btnRemove.setEnabled(false);
		btnHistoryAdd.setEnabled(false);
		//pnl s1
		btnHistoryS1.setEnabled(false);
		btn1S1.setEnabled(false);btn2S1.setEnabled(false);btn3S1.setEnabled(false);btn4S1.setEnabled(false);btn5S1.setEnabled(false);
		chbxSoundS1.setEnabled(false);
		txtOrderOfOpS1.setEditable(false);
		cbxPracticeS1.setEnabled(false);
		btnStartSs1.setEnabled(false);
		//pnl s2
		cbxPracticeS2.setEnabled(false);
		btnHistoryS2.setEnabled(false);
		btnStartSs2.setEnabled(false);
		//pnl s3
		chbxSound3.setEnabled(false);
		cbxPracticeS3.setEnabled(false);
		cbxMaze.setEnabled(false);
		btnHistoryS3.setEnabled(false);
		btnStartSs3.setEnabled(false);
		//pnl s4
		btnHistoryS4.setEnabled(false);
		btnStartSs4.setEnabled(false);
		cbxPracticeS4.setEnabled(false);



		comboBoxToUpdate=new Vector<>();
		comboBoxToUpdate.add(cbxTrainee);
		comboBoxToUpdate.add(cbxTrineeSearch);
		comboBoxToUpdate.add(cbxRemoveTrinee);
		comboBoxToUpdate.add(cbxPickTraineeS1);
		comboBoxToUpdate.add(cbxTrineeS2);
		comboBoxToUpdate.add(cbxTrineeS3);
		comboBoxToUpdate.add(cbxTrineeS4);

		meetingComboBoxToUpdate=new Vector<>();
		meetingComboBoxToUpdate.add(cbxPracticeS1);
		meetingComboBoxToUpdate.add(cbxPracticeS2);
		meetingComboBoxToUpdate.add(cbxPracticeS3);
		meetingComboBoxToUpdate.add(cbxPracticeS4);
	}

	public void setTextInStation1(JButton btn,JTextPane textPane, String txt){
		if(btn.isSelected())
		{
			btn.setSelected(false);
			btn.getModel().setPressed(true);
			textPane.setText(textPane.getText().replaceAll(txt, ""));
		}else{
			btn.setSelected(true);
			btn.getModel().setPressed(false);
			textPane.setText(textPane.getText()+txt);
		}
	}


	public void initTraineeList(Inventory inventory){
		isInitTrinee=true;
		for(JComboBox<Object> c : comboBoxToUpdate){
			isInitTrinee=true;
			c.removeAllItems();
		}
		TreeSet<Trainee> ts=inventory.getTraineeList();
		int i=1;
		for(Trainee t: ts ){
			isInitTrinee=true;
			for(JComboBox<Object> c : comboBoxToUpdate){
				c.addItem((i)+")."+t.getId()+" "+t.getFirstName()+" "+t.getLastName());
			}
			i++;
		}
		for(JComboBox<Object> c : comboBoxToUpdate){
			isInitTrinee=true;
			c.setSelectedIndex(-1);
		}
		isInitTrinee=false;
	}

	public void initMeetingList(Inventory inventory,Trainee t){
		if(t==null){
			return;
		}
		isInitMeeting=true;
		for(JComboBox<Object> c : meetingComboBoxToUpdate){
			isInitMeeting=true;
			c.removeAllItems();
		}
		Meeting[] meetingArr=t.getMeetingArr();
		int numOfmeetings=t.getNumOfMeetings();
		for(int i=0 ; i<numOfmeetings ;i++){
			for(JComboBox<Object> c : meetingComboBoxToUpdate){
				isInitMeeting=true;
				c.addItem((i+1)+") "+meetingArr[i].meetingHeader());
			}
		}
		for(JComboBox<Object> c : meetingComboBoxToUpdate){
			c.addItem("New Meeting");
			isInitMeeting=true;
			c.setSelectedIndex(-1);
		}
		isInitMeeting=false;
	}

	public void setResultsForGame(String results, Meeting currentMeeting){
		String res[]=results.split("\n");
		if(station2Selected&&!station2Fin){
			currentMeeting.s2.setTotalTime(res[0]);
			currentMeeting.s2.setAvgRTime(res[1]);
			currentMeeting.s2.setScore(res[2]);
			station2Fin=true;
		}else if(station4Selected&&station2Fin){
			currentMeeting.s4.setTotalTime(res[0]);
			currentMeeting.s4.setAvgRTime(res[1]);
			currentMeeting.s4.setSongChosen(res[2]);
			currentMeeting.s4.setScore(res[3]);
		}
	}

	public String getId(String str){
		int start=0,fin=0;
		for(int i=0 ; i<str.length() ; i++){
			if(str.charAt(i)=='.')
				start=i;
			else if(str.charAt(i)==' '){
				fin=i;
				break;
			}
		}
		return str.substring(start+1, fin);
	}

	public void swichActionActionLisinors(){
		//switch to start practice
		btnStartPractice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLayout.show(pnlAction, "1");
				initTraineeList(inventory);
			}
		});
		//switch to  search
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLayout.show(pnlAction, "2");
				initTraineeList(inventory);
			}
		});
		//switch to add trainee
		btnAddRemoveTrainee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLayout.show(pnlAction, "3");
				initTraineeList(inventory);
			}
		});
		//switch to input a s1
		btnStartS1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLayout.show(pnlAction, "4");
				initTraineeList(inventory);
			}
		});
		//switch to input a s2
		btnStartS2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLayout.show(pnlAction, "5");
				initTraineeList(inventory);
			}
		});
		//switch to input a s3
		btnStartS3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLayout.show(pnlAction, "6");
				initTraineeList(inventory);
			}
		});
		//switch to input a s4
		btnStartS4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cLayout.show(pnlAction, "7");
				initTraineeList(inventory);
			}
		});
	}

	public void startPracticePnlActionLisinors(){
		//select 1 unit
		btn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn1, txtOrderOfOperation, "-1");
			}
		});
		//select 2 unit
		btn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn2, txtOrderOfOperation, "-2");
			}
		});
		//select 3 unit
		btn3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn3, txtOrderOfOperation, "-3");
			}
		});
		//select 4 unit
		btn4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn4, txtOrderOfOperation, "-4");
			}
		});
		//select 5 unit
		btn5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn5, txtOrderOfOperation, "-5");
			}
		});

		cbxTrainee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxTrainee.getSelectedIndex()==-1){
					chckbxHead.setEnabled(false);
					chckbxHand.setEnabled(false);
					chckbxArm.setEnabled(false);
					chckbxFootlegs.setEnabled(false);
					btnHistory.setEnabled(false);
				}else{
					chckbxHead.setEnabled(true);
					chckbxHand.setEnabled(true);
					chckbxArm.setEnabled(true);
					chckbxFootlegs.setEnabled(true);
					btnHistory.setEnabled(true);
				}
			}
		});
		//select station 1
		chckbxHead.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxHead.isSelected()){
					btn1.setEnabled(true);
					btn2.setEnabled(true);
					btn3.setEnabled(true);
					btn4.setEnabled(true);
					btn5.setEnabled(true);
					btnStart.setEnabled(true);
					cbxSoundStation1.setEnabled(true);
					txtOrderOfOperation.setEnabled(true);
					station1Selected=true;
				}else{
					btn1.setEnabled(false);
					btn2.setEnabled(false);
					btn3.setEnabled(false);
					btn4.setEnabled(false);
					btn5.setEnabled(false);
					cbxSoundStation1.setEnabled(false);
					txtOrderOfOperation.setEnabled(false);
					station1Selected=false;
					if(chckbxHand.isSelected()||chckbxArm.isSelected()||chckbxFootlegs.isSelected())
						btnStart.setEnabled(true);
					else
						btnStart.setEnabled(false);
				}
			}
		});
		//select station 2
		chckbxHand.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxHand.isSelected()){
					station2Selected=true;
					btnStart.setEnabled(true);

				}else{
					station2Selected=false;
					station2Fin=true;
					if(chckbxHead.isSelected()||chckbxArm.isSelected()||chckbxFootlegs.isSelected())
						btnStart.setEnabled(true);
					else
						btnStart.setEnabled(false);
				}			
			}
		});
		chckbxArm.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chckbxArm.isSelected()){
					cbxSoundStation3.setEnabled(true);
					cbxStation3.setEnabled(true);
					station3Selected=true;
					btnStart.setEnabled(true);

				}else{
					cbxSoundStation3.setEnabled(false);
					cbxStation3.setEnabled(false);
					station3Selected=false;

					if(chckbxHead.isSelected()||chckbxHand.isSelected()||chckbxFootlegs.isSelected())
						btnStart.setEnabled(true);
					else
						btnStart.setEnabled(false);
				}
			}
		});
		//select station 4
		chckbxFootlegs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxFootlegs.isSelected()){
					station4Selected=true;
					btnStart.setEnabled(true);
				}else{
					station4Selected=false;
					if(chckbxHead.isSelected()||chckbxHand.isSelected()||chckbxArm.isSelected())
						btnStart.setEnabled(true);
					else
						btnStart.setEnabled(false);
				}
			}
		});

		//select Sound s1
		cbxSoundStation1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxSoundStation1.isSelected()){
					s1Sound=true;
				}else{
					s1Sound=false;
				}
			}
		});

		//select Sound s3
		cbxSoundStation3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxSoundStation3.isSelected()){
					s3Sound=true;
				}else{
					s3Sound=false;
				}
			}
		});

		//start practice
		btnStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//currentMeeting=new Meeting();

				StringBuilder sb=new StringBuilder();

				if(station1Selected){
					s1OperationOrder=txtOrderOfOperation.getText().replaceAll("-", "");
					for(int i=s1OperationOrder.length() ; i<5 ; i++){
						s1OperationOrder=s1OperationOrder.concat("9");
					}
					sb.append(1);
					sb.append(s1OperationOrder);
					if(s1Sound==true)
						sb.append(1);
					else
						sb.append(0);
				}else{
					sb.append(9999999);
				}
				if(station3Selected){
					s3LanePicked=cbxStation3.getSelectedIndex();	
					sb.append(3);
					sb.append(s3LanePicked);
					if(s3Sound==true)
						sb.append(1);
					else
						sb.append(0);
				}else{
					sb.append(999);
				}
				if(station2Selected){
					//	startStation2();
				}else{
					station2Fin=true;
				}

				if(station4Selected){
					//	startStation4();
				}else{

				}
				//arduino.writeData(sb.toString());
			}

			/*private void startStation4() {
				Runnable gameFourIsOn=new GameThread(gameForStationTwoAdress,1,arduino,"Station2Game.exe",frame);
				Thread gameFourThread=new Thread(gameFourIsOn);
				gameFourThread.start();
			}

			private void startStation2() {
				Runnable gameTwoIsOn=new GameThread(gameForStationTwoAdress,1,arduino,"Station2Game.exe",frame);
				Thread gameTwoThread=new Thread(gameTwoIsOn);
				gameTwoThread.start();
			}*/
		});

		btnHistory.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				textTrineeSearch.setText(inventory.findTrinee(getId((String)cbxTrainee.getSelectedItem())).toString());
				btnSearch.doClick();
			}
		});
	}

	public void searchPnlActionLisinors(){
		cbxTrineeSearch.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(isInitMeeting==false&&isInitTrinee==false){
					textTrineeSearch.setText(inventory.findTrinee(getId((String)cbxTrineeSearch.getSelectedItem())).toString());
				}
			}
		});
	}

	public void addTrineePnlActionLisinors(){	

		btnEnterNewTrinee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inventory.addTrainee(txtFN.getText(), txtLN.getText(), txtID.getText())){
					JOptionPane.showMessageDialog(null, "Trainee was added");
					txtFN.setText("");
					txtLN.setText("");
					txtID.setText("");
					initTraineeList(inventory);
				}else{
					JOptionPane.showMessageDialog(null, "All fields must contain text, trainee was not added");
				}
			}
		});

		cbxRemoveTrinee.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxRemoveTrinee.getSelectedIndex()==-1){
					btnRemove.setEnabled(false);
				}else{
					btnRemove.setEnabled(true);
				}
			}
		});

		btnRemove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(inventory.removeTrainee(inventory.findTrinee(getId((String)cbxRemoveTrinee.getSelectedItem())))){
					initTraineeList(inventory);
					JOptionPane.showMessageDialog(null, "Trainee was Removed");
				}else{
					JOptionPane.showMessageDialog(null, "Trainee was not Removed");
				}				
			}
		});

		btnHistoryAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textTrineeSearch.setText(inventory.findTrinee(getId((String)cbxRemoveTrinee.getSelectedItem())).toString());
				btnSearch.doClick();
			}
		});
	}

	public void station1PnlActionLisinors(){
		//select 1 unit
		btn1S1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn1S1, txtOrderOfOpS1, "-1");
			}
		});
		//select 2 unit
		btn2S1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn2S1, txtOrderOfOpS1, "-2");
			}
		});
		//select 3 unit
		btn3S1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn3S1, txtOrderOfOpS1, "-3");
			}
		});
		//select 4 unit
		btn4S1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn4S1, txtOrderOfOpS1, "-4");
			}
		});
		//select 5 unit
		btn5S1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setTextInStation1(btn5S1, txtOrderOfOpS1, "-5");
			}
		});

		cbxPickTraineeS1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxPickTraineeS1.getSelectedIndex()==-1){
					btnHistoryS1.setEnabled(false);
					btn1S1.setEnabled(false);btn2S1.setEnabled(false);btn3S1.setEnabled(false);btn4S1.setEnabled(false);btn5S1.setEnabled(false);
					chbxSoundS1.setEnabled(false);
					txtOrderOfOpS1.setEnabled(false);
					cbxPracticeS1.setEnabled(false);
					btnStartSs1.setEnabled(false);
				}else{
					currentTrineeS1=inventory.findTrinee(getId((String)cbxPickTraineeS1.getSelectedItem()));
					btnHistoryS1.setEnabled(true);
					cbxPracticeS1.setEnabled(true);
					if(isInitMeeting==false&&isInitTrinee==false)
						initMeetingList(inventory,currentTrineeS1);
				}
			}
		});

		cbxPracticeS1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxPracticeS1.getSelectedIndex()==-1){
					btn1S1.setEnabled(false);btn2S1.setEnabled(false);btn3S1.setEnabled(false);btn4S1.setEnabled(false);btn5S1.setEnabled(false);
					chbxSoundS1.setEnabled(false);
					txtOrderOfOpS1.setEnabled(false);
					btnStartSs1.setEnabled(false);
				}else{
					btn1S1.setEnabled(true);btn2S1.setEnabled(true);btn3S1.setEnabled(true);btn4S1.setEnabled(true);btn5S1.setEnabled(true);
					chbxSoundS1.setEnabled(true);
					txtOrderOfOpS1.setEnabled(true);
					btnStartSs1.setEnabled(true);
				}
			}
		});

		btnHistoryS1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textTrineeSearch.setText(currentTrineeS1.toString());
				btnSearch.doClick();
			}
		});

		//select Sound s1
		chbxSoundS1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(chbxSoundS1.isSelected()){
					s1Sound=true;
				}else{
					s1Sound=false;
				}
			}
		});

		btnStartSs1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Meeting currentMeeting=null;
				String meetingSelected=((String)cbxPracticeS1.getSelectedItem()).substring(3).replaceAll("\\)","");
				if(meetingSelected.equals(" Meeting")){
					currentMeeting=new Meeting();
					currentTrineeS1.addMeeting(currentMeeting);
				}else{
					currentMeeting=inventory.findMeetingInTrinee(currentTrineeS1, meetingSelected);
				}
				if(currentMeeting==null){
					JOptionPane.showMessageDialog(null, "EROR: try new trinee");
					return;
				}
				//build order of stations
				StringBuilder sb=new StringBuilder();
				s1OperationOrder=txtOrderOfOpS1.getText().replaceAll("-", "");
				for(int i=s1OperationOrder.length() ; i<5 ; i++){
					s1OperationOrder=s1OperationOrder.concat("9");
				}
				sb.append(s1OperationOrder);
				if(s1Sound==true)
					sb.append(1);
				else
					sb.append(0);
				s1Thread=new Thread(new StationOneHeadThread(currentMeeting, s1OperationOrder, frame));
				s1Thread.start();
				station1State(true);
			}
		});
	}

	public void station2PnlActionLisinors(){

		cbxTrineeS2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxTrineeS2.getSelectedIndex()==-1){
					btnHistoryS2.setEnabled(false);
					cbxPracticeS2.setEnabled(false);
					btnStartSs2.setEnabled(false);
				}else{
					currentTrineeS2=inventory.findTrinee(getId((String)cbxTrineeS2.getSelectedItem()));
					btnHistoryS2.setEnabled(true);
					cbxPracticeS2.setEnabled(true);
					if(isInitMeeting==false&&isInitTrinee==false)
						initMeetingList(inventory,currentTrineeS2);
				}
			}
		});

		cbxPracticeS2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxPracticeS2.getSelectedIndex()==-1){
					btnStartSs2.setEnabled(false);
				}else{
					btnStartSs2.setEnabled(true);
				}
			}
		});

		btnHistoryS2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textTrineeSearch.setText(currentTrineeS2.toString());
				btnSearch.doClick();
			}
		});

		btnStartSs2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Meeting currentMeeting=null;
				String meetingSelected=((String)cbxPracticeS2.getSelectedItem()).substring(3).replaceAll("\\)","");
				if(meetingSelected.equals(" Meeting")){
					currentMeeting=new Meeting();
					currentTrineeS2.addMeeting(currentMeeting);
				}else{
					currentMeeting=inventory.findMeetingInTrinee(currentTrineeS2, meetingSelected);
				}
				if(currentMeeting==null){
					JOptionPane.showMessageDialog(null, "EROR: try new trinee");
					return;
				}
				s2Thread=new Thread(new StationTwoHandThread(currentMeeting, frame));
				s2Thread.start();
				station2State(true,currentTrineeS2);
			}
		});
	}

	public void station3PnlActionLisinors(){

	}

	public void station4PnlActionLisinors(){
		cbxTrineeS4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cbxTrineeS4.getSelectedIndex()==-1){
					btnHistoryS4.setEnabled(false);
					cbxPracticeS4.setEnabled(false);
					btnStartSs4.setEnabled(false);
				}else{
					currentTrineeS4=inventory.findTrinee(getId((String)cbxTrineeS4.getSelectedItem()));
					btnHistoryS4.setEnabled(true);
					cbxPracticeS4.setEnabled(true);
					if(isInitMeeting==false&&isInitTrinee==false)
						initMeetingList(inventory,currentTrineeS4);
				}
			}
		});

		cbxPracticeS4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxPracticeS4.getSelectedIndex()==-1){
					btnStartSs4.setEnabled(false);
				}else{
					btnStartSs4.setEnabled(true);
				}
			}
		});

		btnHistoryS4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textTrineeSearch.setText(currentTrineeS4.toString());
				btnSearch.doClick();
			}
		});

		btnStartSs4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Meeting currentMeeting=null;
				String meetingSelected=((String)cbxPracticeS4.getSelectedItem()).substring(3).replaceAll("\\)","");
				if(meetingSelected.equals(" Meeting")){
					currentMeeting=new Meeting();
					currentTrineeS4.addMeeting(currentMeeting);
				}else{
					currentMeeting=inventory.findMeetingInTrinee(currentTrineeS4, meetingSelected);
				}
				if(currentMeeting==null){
					JOptionPane.showMessageDialog(null, "EROR: try new trinee");
					return;
				}
				s4Thread=new Thread(new StationTwoHandThread(currentMeeting, frame));
				s4Thread.start();
				station4State(true,currentTrineeS4);
			}
		});
	}

	public void station1State(boolean isRunning){
		if(isRunning){
			btnStartS1.setText("Station 1 is running");
			btnStartS1.setEnabled(false);
		}else{
			btnStartS1.setText("Start Station 1");
			btnStartS1.setEnabled(true);
		}
	}

	public void station2State(boolean isRunning,Trainee t){
		if(isRunning){
			btnStartS2.setText("Station 2 is running");
			btnHistoryS2.setEnabled(false);
			cbxPracticeS2.setEnabled(false);
			btnStartSs2.setEnabled(false);
		}else{
			btnStartS2.setText("Start Station 2");
			initMeetingList(inventory,t);
			btnHistoryS2.setEnabled(true);
			cbxPracticeS2.setEnabled(true);
			btnStartSs2.setEnabled(true);
		}		
	}
	
	public void station4State(boolean isRunning,Trainee t){
		if(isRunning){
			btnStartS4.setText("Station 2 is running");
			btnHistoryS4.setEnabled(false);
			cbxPracticeS4.setEnabled(false);
			btnStartSs4.setEnabled(false);
		}else{
			btnStartS4.setText("Start Station 2");
			initMeetingList(inventory,t);
			btnHistoryS4.setEnabled(true);
			cbxPracticeS4.setEnabled(true);
			btnStartSs4.setEnabled(true);
		}		
	}
}
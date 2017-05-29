package mqmessaging.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

import mqmessaging.mqconnect.MQConnection;
import mqmessaging.util.AppUtil;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

public class MQMessagingWindow {

	private JFrame frmMqmessaging;
	private JTextField queueManagerField;
	private JTextField queueNameField;
	private JTextField channelField;
	private JTextField mqHostField;
	private JTextField sendMsgField;
	private JTextField getMsgField;
	private MQConnection mqConn;
	JLabel lblConnStatus;
	JButton btnSend;
	JButton btnGet;
	JButton btnDisconnect;
	static AppUtil appUtil = new AppUtil();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MQMessagingWindow window = new MQMessagingWindow();
					window.frmMqmessaging.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MQMessagingWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMqmessaging = new JFrame();
		frmMqmessaging.setTitle("MQMessaging");
		frmMqmessaging.setBounds(100, 100, 594, 300);
		frmMqmessaging.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMqmessaging.setResizable(false);		
		frmMqmessaging.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
		panel.setBounds(10, 11, 228, 226);
		frmMqmessaging.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblQueueManager = new JLabel("Queue Manager:");
		lblQueueManager.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQueueManager.setBounds(6, 43, 107, 14);
		panel.add(lblQueueManager);
		
		JLabel lblConnection = new JLabel("MQ Information");
		lblConnection.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnection.setBounds(66, 11, 93, 14);
		panel.add(lblConnection);
		
		JLabel lblQueueName = new JLabel("Queue Name:");
		lblQueueName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblQueueName.setBounds(25, 74, 88, 14);
		panel.add(lblQueueName);
		
		JLabel lblChannel = new JLabel("Channel:");
		lblChannel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblChannel.setBounds(57, 105, 56, 14);
		panel.add(lblChannel);
		
		JLabel lblMqHost = new JLabel("MQ Host:");
		lblMqHost.setHorizontalAlignment(SwingConstants.RIGHT);
		lblMqHost.setBounds(46, 136, 67, 14);
		panel.add(lblMqHost);
		
		btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setBounds(6, 161, 107, 23);
		panel.add(btnDisconnect);
		
		queueManagerField = new JTextField();
		queueManagerField.setBounds(123, 40, 95, 20);
		panel.add(queueManagerField);
		queueManagerField.setColumns(10);
		queueManagerField.setText(appUtil.getProperty("QueueManager"));
		queueManagerField.setEditable(false);
		
		queueNameField = new JTextField();
		queueNameField.setBounds(123, 71, 95, 20);
		panel.add(queueNameField);
		queueNameField.setColumns(10);
		queueNameField.setText(appUtil.getProperty("QueueName"));
		queueNameField.setEditable(false);
		
		channelField = new JTextField();
		channelField.setBounds(123, 102, 95, 20);
		panel.add(channelField);
		channelField.setColumns(10);
		channelField.setText(appUtil.getProperty("MQChannel"));
		channelField.setEditable(false);
		
		mqHostField = new JTextField();
		mqHostField.setBounds(123, 133, 95, 20);
		panel.add(mqHostField);
		mqHostField.setColumns(10);
		mqHostField.setText(appUtil.getProperty("MQHost"));
		mqHostField.setEditable(false);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(130, 161, 88, 23);
		panel.add(btnConnect);
		
		lblConnStatus = new JLabel("Disconnected");
		lblConnStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblConnStatus.setBounds(75, 200, 84, 14);
		panel.add(lblConnStatus);
		lblConnStatus.setForeground(Color.RED);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		panel_1.setBounds(248, 11, 334, 226);
		frmMqmessaging.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblSendMessage = new JLabel("Send Message");
		lblSendMessage.setHorizontalAlignment(SwingConstants.LEFT);
		lblSendMessage.setBounds(10, 11, 99, 14);
		panel_1.add(lblSendMessage);
		
		sendMsgField = new JTextField();
		sendMsgField.setBounds(10, 43, 313, 20);
		panel_1.add(sendMsgField);
		sendMsgField.setColumns(10);
		
		btnSend = new JButton("Put");
		btnSend.setBounds(141, 74, 61, 23);
		panel_1.add(btnSend);
		
		JLabel lblReceiveMessage = new JLabel("Receive Message");
		lblReceiveMessage.setHorizontalAlignment(SwingConstants.LEFT);
		lblReceiveMessage.setBounds(10, 105, 114, 14);
		panel_1.add(lblReceiveMessage);
		
		getMsgField = new JTextField();
		getMsgField.setBounds(10, 130, 313, 20);
		panel_1.add(getMsgField);
		getMsgField.setEditable(false);
		getMsgField.setColumns(10);
		
		btnGet = new JButton("Get");
		btnGet.setBounds(141, 161, 61, 23);
		panel_1.add(btnGet);
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getMsg = mqConn.getMessage(queueNameField.getText());
				getMsgField.setText(getMsg);
			}
		});
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(mqConn.putMessageLocalQ(sendMsgField.getText(),queueNameField.getText())){
					JOptionPane.showMessageDialog(frmMqmessaging, "Message sent to the queue.", "Success", JOptionPane.INFORMATION_MESSAGE);
					cleanFields();
				}else{
					JOptionPane.showMessageDialog(frmMqmessaging, "Failed to send message to the queue. Please Chek!", "Error", JOptionPane.ERROR_MESSAGE);					
				}
			}
		});
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mqConn = new MQConnection();
				if(mqConn.connectMQ(queueManagerField.getText(),channelField.getText(),mqHostField.getText())){
					lblConnStatus.setText("Connected");
					lblConnStatus.setForeground(Color.GREEN);
					updateFields(true);
					btnConnect.setEnabled(false);
				}else{
					lblConnStatus.setForeground(Color.RED);
				}
			}
		});
		btnDisconnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(mqConn.disconnectMQ()){
					lblConnStatus.setText("Disconnected");
					lblConnStatus.setForeground(Color.RED);
					updateFields(false);
					mqConn = null;
					btnConnect.setEnabled(true);
					cleanFields();
				}
			}
		});
		
		JMenuBar menuBar = new JMenuBar();
		frmMqmessaging.setJMenuBar(menuBar);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				AboutMQMessagingWindow aboutMQmsg = new AboutMQMessagingWindow();
				aboutMQmsg.setVisible(true);
			}});
		menuBar.add(mntmAbout);
		updateFields(false);
	}
	
	private void updateFields(boolean newValue){
		this.sendMsgField.setEnabled(newValue);
		this.btnSend.setEnabled(newValue);
		this.btnGet.setEnabled(newValue);
		this.btnDisconnect.setEnabled(newValue);
	}
	
	private void cleanFields(){
		this.sendMsgField.setText("");
		this.getMsgField.setText("");
	}	
}

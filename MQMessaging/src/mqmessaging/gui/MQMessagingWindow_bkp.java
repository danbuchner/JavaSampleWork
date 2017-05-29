package mqmessaging.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import mqmessaging.mqconnect.MQConnection;
import mqmessaging.util.AppUtil;

import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		frmMqmessaging.setBounds(100, 100, 626, 339);
		frmMqmessaging.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMqmessaging.setResizable(false);		
		
		JPanel panel_1 = new JPanel();
		frmMqmessaging.getContentPane().add(panel_1, BorderLayout.WEST);
		
		JPanel panel = new JPanel();
		panel_1.add(panel);
		
		JLabel lblQueueManager = new JLabel("Queue Manager:");
		
		queueManagerField = new JTextField();
		queueManagerField.setColumns(10);
		queueManagerField.setText(appUtil.getProperty("QueueManager"));
		queueManagerField.setEditable(false);
		
		JLabel lblQueueName = new JLabel("Queue Name:");
		
		queueNameField = new JTextField();
		queueNameField.setColumns(10);
		queueNameField.setText(appUtil.getProperty("QueueName"));
		queueNameField.setEditable(false);
		
		JLabel lblChannel = new JLabel("Channel:");
		
		JLabel lblMqHost = new JLabel("MQ Host:");
		
		channelField = new JTextField();
		channelField.setColumns(10);
		channelField.setText(appUtil.getProperty("MQChannel"));
		channelField.setEditable(false);
		
		mqHostField = new JTextField();
		mqHostField.setColumns(10);
		mqHostField.setText(appUtil.getProperty("MQHost"));
		mqHostField.setEditable(false);
		
		JButton btnConnect = new JButton("Connect");
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
		
		lblConnStatus = new JLabel("Disconnected");
		lblConnStatus.setForeground(Color.RED);
		
		JLabel lblConnection = new JLabel("MQ Connection");
		
		JSeparator separator = new JSeparator();
		
		JLabel lblSendMessage = new JLabel("Send Message");
		
		sendMsgField = new JTextField();
		sendMsgField.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		btnSend = new JButton("Put");
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
		
		JLabel lblReceiveMessage = new JLabel("Receive Message");
		
		btnGet = new JButton("Get");
		btnGet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String getMsg = mqConn.getMessage(queueNameField.getText());
				getMsgField.setText(getMsg);
			}
		});
		
		getMsgField = new JTextField();
		getMsgField.setEditable(false);
		getMsgField.setColumns(10);
		
		btnDisconnect = new JButton("Disconnect");
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
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addContainerGap()
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblQueueName)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(queueNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblQueueManager)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(queueManagerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblChannel)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(channelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnDisconnect)
												.addComponent(lblMqHost))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
												.addComponent(btnConnect)
												.addComponent(mqHostField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(82)
									.addComponent(lblConnection)))
							.addGap(46))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblConnStatus)
							.addGap(79)))
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED, 322, Short.MAX_VALUE)
											.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(sendMsgField, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
											.addContainerGap()))
									.addGroup(gl_panel.createSequentialGroup()
										.addGap(131)
										.addComponent(btnSend)
										.addContainerGap()))
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblReceiveMessage)
									.addContainerGap()))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(getMsgField, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(lblSendMessage)
								.addContainerGap()))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(131)
							.addComponent(btnGet)
							.addContainerGap())))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(33)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblConnection)
								.addComponent(lblSendMessage))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
										.addComponent(queueManagerField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(sendMsgField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblQueueManager))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(queueNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblQueueName))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(channelField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblReceiveMessage)
												.addComponent(lblChannel))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(mqHostField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(getMsgField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblMqHost)))
										.addComponent(btnSend))
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup()
											.addGap(11)
											.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnConnect)
												.addComponent(btnDisconnect)))
										.addGroup(gl_panel.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(btnGet)))
									.addGap(18)
									.addComponent(lblConnStatus))
								.addComponent(separator_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 282, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(115, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
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

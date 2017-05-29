package mqmessaging.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AboutMQMessagingWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AboutMQMessagingWindow frame = new AboutMQMessagingWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AboutMQMessagingWindow() {
		setResizable(false);
		setTitle("About MQMessaging");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 429, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);		
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("MQ Messaging");
		lblNewLabel.setBounds(149, 11, 89, 14);
		panel.add(lblNewLabel);
		
		JLabel lblVersion = new JLabel("Version 1.0.0.0");
		lblVersion.setBounds(149, 36, 89, 14);
		panel.add(lblVersion);
		
		JLabel lblCopyright = new JLabel("Copyright \u00a9 2017");
		lblCopyright.setBounds(149, 61, 129, 14);
		panel.add(lblCopyright);
		
		JLabel lblDanilobuchnercom = new JLabel("DaniloBuchner.Com");
		lblDanilobuchnercom.setBounds(149, 86, 114, 14);
		panel.add(lblDanilobuchnercom);		
		
		JLabel img = new JLabel();		
		img.setIcon(new ImageIcon(AboutMQMessagingWindow.class.getResource("/img/aboutImg.PNG")));
		img.setBounds(10, 11, 129, 229);
		panel.add(img);
		
		JTextPane txtpnThisIsA = new JTextPane();
		txtpnThisIsA.setEditable(false);
		txtpnThisIsA.setText("This is a simple MQ Messaging java program to send and receive string from a queue.\r\nNOTE: To change connection details, please change config.properties file");
		txtpnThisIsA.setBounds(149, 111, 246, 95);
		panel.add(txtpnThisIsA);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnNewButton.setBounds(304, 217, 89, 23);
		panel.add(btnNewButton);
	}
}

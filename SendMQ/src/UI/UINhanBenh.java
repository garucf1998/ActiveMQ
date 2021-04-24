package UI;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import Model.SinhVien;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.JButton;
import java.util.Date;
import java.util.Properties;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import org.apache.log4j.BasicConfigurator;

import com.google.gson.Gson;

public class UINhanBenh extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField txtngaysinh;
	private JTextField txtma;
	private JTextField txthoten;
	private JButton btnNewButton;
	
private static String url =ActiveMQConnection.DEFAULT_BROKER_URL; // "tcp://localhost:61616";
	
	private static String subject = "Duy Vien";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UINhanBenh frame = new UINhanBenh();
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
	public UINhanBenh() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ma Sinh Vien: ");
		lblNewLabel.setBounds(10, 41, 112, 20);
		contentPane.add(lblNewLabel);
		
		txtma = new JTextField();
		txtma.setBounds(132, 41, 276, 20);
		contentPane.add(txtma);
		txtma.setColumns(10);
		
		txtngaysinh = new JTextField();
		txtngaysinh.setColumns(10);
		txtngaysinh.setBounds(132, 72, 276, 20);
		contentPane.add(txtngaysinh);
		
		JLabel lblNewLabel_1 = new JLabel("ngay sinh: ");
		lblNewLabel_1.setBounds(10, 72, 87, 20);
		contentPane.add(lblNewLabel_1);
		
		txthoten = new JTextField();
		txthoten.setColumns(10);
		txthoten.setBounds(132, 103, 276, 20);
		contentPane.add(txthoten);
		
		JLabel lblNewLabel_2 = new JLabel("ho ten: ");
		lblNewLabel_2.setBounds(10, 103, 87, 20);
		contentPane.add(lblNewLabel_2);
		
	
		
		 btnNewButton = new JButton("Nhan benh");
		btnNewButton.setBounds(192, 216, 160, 23);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o=e.getSource();
		if(o==btnNewButton)
		{
				SinhVien sv= new SinhVien(Long.parseLong(txtma.getText()), txthoten.getText(), txtngaysinh.getText());
			try {
				Send(sv);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
	}
	public void Send(SinhVien a) throws Exception {
		ConnectionFactory conAMQ = new ActiveMQConnectionFactory();
		Connection con = conAMQ.createConnection();
		con.start();
		
		
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination des = session.createQueue(subject);
		
		MessageProducer mProducer = session.createProducer(des);
		Gson gson = new Gson();
    		    
		String json = gson.toJson(a);
		Message msg=session.createTextMessage();

		msg=session.createTextMessage(json);
		mProducer.send(msg);
		session.close();
		con.close();
	}
	
}

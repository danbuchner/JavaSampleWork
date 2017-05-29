package mqmessaging.mqconnect;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.jms.JMSMessage;
import com.ibm.jms.JMSTextMessage;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnection;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQQueueReceiver;
import com.ibm.mq.jms.MQQueueSender;
import com.ibm.mq.jms.MQQueueSession;

public class MQConnection {
	
	MQQueueSession session;
	MQQueue queue;
	MQQueueConnection connection;	
	
	public boolean connectMQ(String strQueueManager,String strChannel,String strMqHost ){
		boolean result = false;
		try {
		      MQQueueConnectionFactory cf = new MQQueueConnectionFactory();		    
		      cf.setHostName(strMqHost);
		      cf.setPort(1414);
		      //cf.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
		      cf.setQueueManager(strQueueManager);
		      cf.setChannel(strChannel);
		      connection = (MQQueueConnection) cf.createQueueConnection();		      
		      connection.start();	     
		      System.out.println("Connection Started!");
		      result = true;
		    }
		    catch (JMSException jmsex) {
		      System.out.println(jmsex);
		      System.out.println("Connection Failed!");
		    }
		    catch (Exception ex) {
		      System.out.println(ex);
		      System.out.println("Connection Failed!");
		    }
			return result;
	}
	
	public boolean disconnectMQ(){
		boolean result = false;
		try {
			  connection.close();	     
		      System.out.println("Connection Ended!");
		      result = true;
		    }
		    catch (JMSException jmsex) {
		      System.out.println(jmsex);
		      System.out.println("Connection Failed!");
		    }
		    catch (Exception ex) {
		      System.out.println(ex);
		      System.out.println("Connection Failed!");
		    }
			return result;
	}
	
	public boolean putMessageLocalQ(String messageSend,String queueName){
		boolean result = false;
		try {
			session = (MQQueueSession) connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			queue = (MQQueue) session.createQueue("queue:///" + queueName); 
			MQQueueSender sender =  (MQQueueSender) session.createSender(queue);			
			JMSTextMessage message = (JMSTextMessage) session.createTextMessage(messageSend);
			 System.out.println("Sent message:\\n" + message);
			sender.send(message);
			sender.close();
			session.close();
			result = true;
		} catch (JMSException e) {			
			e.printStackTrace(); 
		}
		return result;
	}
	
	public String getMessage(String queueName){
		String result = "";
		MQQueueReceiver receiver;
		try {
			session = (MQQueueSession) connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		    queue = (MQQueue) session.createQueue("queue:///" + queueName); 
			receiver = (MQQueueReceiver) session.createReceiver(queue);
			JMSMessage receivedMessage = (JMSMessage) receiver.receive(10000);
			String replyString = "";
			if (receivedMessage instanceof TextMessage) {
				  replyString = ((TextMessage) receivedMessage).getText();	
				  System.out.println("\\nReceived message:\\n" + replyString);
				} else {				  
				  System.out.println("Reply message was not a TextMessage");
				  replyString = "Reply message was not a TextMessage";
				}			
			result = replyString;
			receiver.close();
			session.close();
		} catch (JMSException e) {			
			e.printStackTrace();
		}		
		return result;		
	}
}

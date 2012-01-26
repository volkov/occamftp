package svolkov;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.FtpSession;
import org.apache.ftpserver.listener.ListenerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OcammFtpServer {
	public static void main(String[] args) throws FtpException {
		ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				"server.xml");
		FtpServer ftpServer = (FtpServer) classPathXmlApplicationContext
				.getBean("server");
		ftpServer.start();
	}
}

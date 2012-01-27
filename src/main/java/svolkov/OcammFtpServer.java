package svolkov;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Just starts FtpServer with configuration.
 * @author Sergey Volkov
 *
 */
public class OcammFtpServer {
	
	private static final String CONFIGURATION_FILE = "server.xml";

	public static void main(String[] args) throws FtpException {
		ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext(
				CONFIGURATION_FILE);
		final FtpServer ftpServer = (FtpServer) classPathXmlApplicationContext
				.getBean("server");
		ftpServer.start();
		Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
            	ftpServer.stop();
            }
        });
	}
}

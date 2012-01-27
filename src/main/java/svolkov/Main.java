package svolkov;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import org.apache.ftpserver.ftplet.FtpException;

public class Main {
	
	public static void main(String[] args) throws FtpException, SocketException, IOException {
		if (args[0].equals("--server")){
			OcammFtpServer.main(args);
		} else {
			OcammFtpClient.main(args);
		}
	}
}

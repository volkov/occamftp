package svolkov;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;


public class OcammFtpClient {
	public static void main(String[] args) throws SocketException, IOException {
		FTPClient client = new FTPClient();
	    FileInputStream fis = null;

	    client.connect("localhost",2222);
	    client.login("admin", "admin");

	    String filename = "/home/vsa/skai/1";
	    fis = new FileInputStream(filename);
	    client.storeFile("1", fis);
	    client.logout();
	    fis.close();
	}
}

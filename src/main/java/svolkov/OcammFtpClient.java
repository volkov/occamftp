package svolkov;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.SocketException;

import org.apache.commons.net.ftp.FTPClient;

/**
 * Simple wrapper for apache FTPClient
 * @author Sergey Volkov
 *
 */
public class OcammFtpClient {

	/**
	 * Apache client.
	 */
	private FTPClient client;

	/**
	 * Creates client and connects to server.
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @throws SocketException
	 * @throws IOException
	 */
	public OcammFtpClient(String host, int port, String username,
			String password) throws SocketException, IOException {
		client = new FTPClient();

		client.connect(host, port);
		client.login(username, password);
	}
	
	/**
	 * Uploads file from local fs to ftp server
	 * @param src - local file
	 * @param dest - remote name
	 * @throws IOException
	 */
	public void upload(String src, String dest) throws IOException {
		
		checkFolders((new File(dest)).getParent());
		client.changeWorkingDirectory("");
		
		FileInputStream input = null;
		try {
			input = new FileInputStream(src);
			client.storeFile(dest, input);
			System.out.println("Upload finished");
		} finally {
			if (input != null) {
				input.close();
			}
		}
	}

	/**
	 * Creates folders for path if not exists.
	 * @param path
	 * @throws IOException
	 */
	private void checkFolders(String path) throws IOException {
		if (path != null) {
			if (!client.changeWorkingDirectory(path)) {
				checkFolders((new File(path)).getParent());
				System.out.println("Make remote directory:" +path);
				client.makeDirectory(path);
			}
		}
	}

	/**
	 * Copy file from local fs to server.
	 * Usage: source dest [ host [ port [ user [ password ]]]]
	 * @param args
	 * @throws SocketException
	 * @throws IOException
	 */
	public static void main(String[] args) throws SocketException, IOException {

		String host = "localhost";
		int port = 2222;
		String username = "anonymous";
		String password = "";
		
		String source = args[0];
		String dest = args[1];
		if (args.length>2){
			host = args[2];
		}
		if (args.length>3) {
			port = Integer.parseInt(args[3]);
		}
		if (args.length>4) {
			username = args[4];
		}
		if (args.length>5) {
			password = args[5];
		}

		OcammFtpClient client = new OcammFtpClient(host, port, username, password);
		client.upload(source, dest);
	}
}

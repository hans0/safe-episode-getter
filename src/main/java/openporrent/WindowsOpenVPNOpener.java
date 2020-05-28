package openporrent;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WindowsOpenVPNOpener {

	Robot robot;
	Process proxyProcess;
	String[] proxyPath;
	
	public WindowsOpenVPNOpener() {
		this.proxyProcess = null;
		this.proxyPath = new String[]{
				"C:\\Program Files\\OpenVPN\\bin\\openvpn-gui.exe",
				"--connect",
				//"--config",
				"se.protonvpn.com.udp.ovpn"
				//"--mode",
				//"p2p"
		};
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean connectToProxy() throws InterruptedException {
		try {
			this.proxyProcess = new ProcessBuilder(this.proxyPath).start();
			//
			
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(5000);

			robot.keyPress(KeyEvent.VK_ENTER);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public boolean disconnectFromProxy() {

		this.proxyProcess.destroy();
		return true;
	}
	
	public static void main(String[] args) throws IOException, AWTException, InterruptedException {

		
		WindowsOpenVPNOpener wovo = new WindowsOpenVPNOpener();
		
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(
		                whatismyip.openStream()
		                
						));
		String ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);
		in.close();
		
		wovo.connectToProxy();
		
		in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

		ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);
		in.close();
		
		wovo.disconnectFromProxy();
		
		/*
		
		Robot robot = new Robot();
		
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);
		in.close();
		
		
		//String proxyServicePath = "C:\\Program Files\\OpenVPN\\bin\\openvpnserv.exe";
		
		//Process proxyServiceProcess = new ProcessBuilder(proxyServicePath).start();
		
		String [] proxyPath = {
				"C:\\Program Files\\OpenVPN\\bin\\openvpn-gui.exe",
				"--connect",
				//"--config",
				"se.protonvpn.com.udp.ovpn"
				//"--mode",
				//"p2p"
				};
		
		Process proxyProcess = null;
		
		try {
			proxyProcess = new ProcessBuilder(proxyPath).start();
			//
			
			Thread.sleep(5000);
			robot.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(5000);

			robot.keyPress(KeyEvent.VK_ENTER);

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Thread.sleep(10000);

		in = new BufferedReader(new InputStreamReader(
                whatismyip.openStream()));

		ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);
		in.close();
		
		
		
		proxyProcess.destroy();
		*/
	}
	
	
	
	
	
	

}

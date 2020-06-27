package openporrent;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LinuxProtonVPNOpener extends VPNOpener {

	public LinuxProtonVPNOpener() {
		this.proxyPath = new String[]{
				"sudo", 
				"protonvpn",
				"--version"//"connect"
		};
		this.proxyProcess = null;
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean connectToProxy() throws InterruptedException {
		// TODO Auto-generated method stub
		try {
			this.proxyProcess = new ProcessBuilder(this.proxyPath).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	
	public static void main(String [] args) throws InterruptedException, IOException {
		/*LinuxProtonVPNOpener lpvo = new LinuxProtonVPNOpener();
		try {
			lpvo.connectToProxy();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread.sleep(5000);

		lpvo.proxyProcess.destroy();
		lpvo.proxyProcess.destroyForcibly();
		*/
		/*
		String[] cmd = {"/bin/bash","-c","echo password| sudo -S ls"};
		Process pb = Runtime.getRuntime().exec(cmd);
		
		String line;
		BufferedReader input = new BufferedReader(new InputStreamReader(pb.getInputStream()));
		while ((line = input.readLine()) != null) {
		    System.out.println(line);
		}
		input.close();
		*/
		String [] cmd = /*{"/bin/bash","-c","echo password"};*/{"protonvpn",  "--version"};
		ProcessBuilder builder = new ProcessBuilder(cmd);
		builder.redirectErrorStream(true);
		Process process = builder.start();
		InputStream is = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String line = null;
		while ((line = reader.readLine()) != null) {
		   System.out.println(line);
		}
		
	}
}

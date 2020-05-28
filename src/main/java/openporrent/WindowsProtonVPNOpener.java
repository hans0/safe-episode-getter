package openporrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WindowsProtonVPNOpener {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String [] proxyPath = {
				"C:\\Program Files (x86)\\Proton Technologies\\ProtonVPN\\ProtonVPN.exe",
				"--connect"
				//"--config",
				//"se.protonvpn.com.udp.ovpn"
				//"--mode",
				//"p2p"
				};
		
		Process proxyProcess = null;
		
		try {
			proxyProcess = new ProcessBuilder(proxyPath).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStream is = null;
		if (proxyProcess != null) {
			is = proxyProcess.getInputStream();
		}
		
		proxyProcess.destroy();
	
	}

}

package openporrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Arrays;

public class WindowsProcessOpener {

	// rick
	
	
	public static void main(String[] args) throws IOException {
		// getting current IP address
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));

		String ip = in.readLine(); //you get the IP as a String
		System.out.println(ip);
		in.close();
		//String proxyPath = "C:\\Program Files\\OpenVPN\\bin\\openvpn-gui.exe";
		
		
		
		String [] proxyPath = {
				"C:\\Program Files\\OpenVPN\\bin\\openvpn-gui.exe",
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
		
		
		
		
		// Trying to get HideMe VPN to work
		// No idea how to connect after startup
		// Startup takes too long
		/* PROXY ATTEMPT 1 
		String proxyPath = "C:\\Program Files (x86)\\hide.me VPN\\Hide.me.exe";
		
		Process proxyProcess = null;
		try {
			proxyProcess = new ProcessBuilder(proxyPath).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		
		
		// TODO Auto-generated method stub
		String [] s = new String[] {
				
				
				
				// ATTEMPT 4
				// program location
				"C:\\Users\\Hans\\Downloads\\aria2-1.35.0-win-64bit-build1\\aria2c.exe",
				// torrent as argument, magnet can also go here
				"C:\\Users\\Hans\\Downloads\\Doom_Manual_Final_Doom_archive.torrent",
				// target directory
				"--dir=C:\\Users\\Hans\\Downloads\\ariatest",
				// I think this means seed for 1 second or minute
				"--seed-time=1",
				// I was testing this, so allowing it to be overwritten is important
				"--allow-overwrite=true"
				
				
				// ATTEMPT 3
				/*
			"C:\\Users\\Hans\\Downloads\\aria2-1.35.0-win-64bit-build1\\aria2c.exe",
			"--on-download-stop=hello.sh",
			"C:\\Users\\Hans\\\\Downloads\\2020-02-13-raspbian-buster-full.zip.torrent"
				*/
				
			// ATTEMPT 2
			// No success
			//	"C:\\Windows\\system32\\WindowsPowerShell\\v1.0\\powershell.exe",
			//"C:\\Program Files (x86)\\Deluge\\deluge-console.exe"
			
			// ATTEMPT 1
			// This runs, but does not start the torrent	
			/*
			"C:\\Program Files (x86)\\Deluge\\deluge.exe",
			"add",
			"C:\\Users\\Hans\\Downloads\\2020-02-13-raspbian-buster-full.zip.torrent"
			/*,
			"-p",
			"D:\\"
			*/
		};
		
		Process process = null;
		try {
			process = new ProcessBuilder(s).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (process == null) return;
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;

		//System.out.printf("Output of running %s is:", Arrays.toString(args));

		while ((line = br.readLine()) != null) {
			System.out.println(ip);
			System.out.println(line);
		}
		
		// FOR ATTEMPTS 1 - 4
		/*
		Runtime run = Runtime.getRuntime();
		try {
			//System.out.println(s[0]);
			run.exec(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		process.destroy();
		proxyProcess.destroy();
		
		System.out.println(proxyProcess.isAlive());
		System.out.println(process.isAlive());

	}
}

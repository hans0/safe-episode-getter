package openporrent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LinuxPorrentDownloader extends PorrentDownloader {

	public LinuxPorrentDownloader() {
		this.porrentPathAndArguments = new ArrayList<String>();
	}
	
	public void downloadPorrent() throws IOException{
		// TODO Auto-generated method stub
		Process porrentProcess = null;
		try {
			porrentProcess = new ProcessBuilder(porrentPathAndArguments).start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (porrentProcess == null) return;
		InputStream is = porrentProcess.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		porrentProcess.destroy();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (porrentProcess.isAlive()) {
			System.out.println("still alive");
			porrentProcess.destroyForcibly();
		}
	}
	
	public static void main(String [] args) {
		LinuxPorrentDownloader lpd = new LinuxPorrentDownloader();
		lpd.porrentPathAndArguments.add("aria2c");

		lpd.porrentPathAndArguments.add("--seed-time=0");
		
		lpd.porrentPathAndArguments.add("-d");
		lpd.porrentPathAndArguments.add("/home/hans/Downloads");
		
		lpd.porrentPathAndArguments.add("-T");	// add double-quotes for magnet links?
		lpd.porrentPathAndArguments.add("/home/hans/Downloads/2020-05-27-raspios-buster-lite-armhf.zip.torrent");
		
		
		
		try {
			lpd.downloadPorrent();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
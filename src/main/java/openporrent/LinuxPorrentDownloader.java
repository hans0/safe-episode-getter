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
	}
	
	public static void main(String [] args) {
		LinuxPorrentDownloader lpd = new LinuxPorrentDownloader();
		lpd.porrentPathAndArguments.add("aria2");
		lpd.porrentPathAndArguments.add("-h");
	}
}
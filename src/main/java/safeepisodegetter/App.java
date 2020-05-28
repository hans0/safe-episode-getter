package safeepisodegetter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import getlinks.LinkGetter;
import getlinks.ThirteenThirtySevenLinkGetter;
import openporrent.WindowsOpenVPNOpener;

public class App {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		// 1. open file for queries
		ArrayList<String> directories = new ArrayList<String>(5);
		ArrayList<String> queries = new ArrayList<String>(5);
		File queryFile = new File("C:\\Users\\Hans\\Desktop\\NewFolder\\testArgs.txt");

		Scanner sc = new Scanner(queryFile);
		
		while (sc.hasNext()) {
			String currentLine = sc.nextLine();
			if ("DIR".equals(currentLine)){
				directories.add(sc.nextLine());
				continue;
			}
			if ("QUERY".equals(currentLine)) {
				queries.add(sc.nextLine());
				continue;
			}
		}

		sc.close();
		
		System.out.println(directories);
		System.out.println(queries);
		
		// 2. Connect to proxy
		WindowsOpenVPNOpener wovo = new WindowsOpenVPNOpener();
		
		wovo.connectToProxy();
		
		// 3. Get all magnet links, save to file
		LinkGetter lg = null;
		String site = "1337x.to";
		lg = new ThirteenThirtySevenLinkGetter();
		
		/*
		if (args[1].contains("1337x.to")) {
			//lg = new ThirteenThirtySevenLinkGetter();
		} else {
			return;
		}
		*/
		lg.getLinks(site);
		// 4. Choose best torrent (1080p first, then choose highest number of seeders)
		File magnetFile = lg.getOutputFile();
		
		Scanner sc1 = new Scanner(magnetFile);
		String magnetLink = "";
		while (sc1.hasNext()) {
			String s = sc1.nextLine();
			if (s.contains("1080p")) {
				magnetLink = s;
				break;
			}
		}
		
		sc1.close();
		
		// 4. Download torrent
		
		
		
		
		
		
		// 5. Disconnect from proxy
		
		wovo.disconnectFromProxy();
	}

}

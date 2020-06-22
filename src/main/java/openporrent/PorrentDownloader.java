package openporrent;

import java.io.IOException;
import java.util.ArrayList;

public abstract class PorrentDownloader {
	public ArrayList<String> porrentPathAndArguments ;
	public abstract void downloadPorrent() throws IOException;
}

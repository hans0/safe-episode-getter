package openporrent;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

public class LinuxProtonVPNOpener extends VPNOpener {

	public LinuxProtonVPNOpener() {
		this.proxyPath = new String[]{
				"sudo protonvpn ",
				""
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

}

package openporrent;

import java.awt.Robot;


public abstract class VPNOpener {

	Robot robot;
	Process proxyProcess;
	String[] proxyPath;
	public abstract boolean connectToProxy() throws InterruptedException;
}

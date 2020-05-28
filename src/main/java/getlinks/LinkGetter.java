package getlinks;

import java.io.File;
import java.io.IOException;

public interface LinkGetter {

	public String getDestFile();

	public void setDestFile(String destFile);

	public File getOutputFile();

	public void setOutputFile(File outputFile);
	
	public boolean getLinks(String query) throws IOException;
}

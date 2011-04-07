package v1;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main {

	public static Logger logger;
	
	static { // logging bits
	    try {
	      int limit = 1000000; // how big our log file will be
	      FileHandler fh = new FileHandler("DSLog.log", limit, 1, true);
	      fh.setFormatter(new SimpleFormatter());
	      logger = Logger.getLogger("DSLog");
	      logger.addHandler(fh);
	    }
	    catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	public static ProjectHandler ph;


	public static void main(String[] args) {
		logger.info("Starting program");
		ph = new ProjectHandler();
		ph.createChild(102);
		ph.createChild(102);
		ph.showProjects();
	}
}

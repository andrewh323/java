package measuringSorts;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.io.IOException;
public class Instrumentation {
	
	public long[] start = new long[99];
	public long finish;
	public String logString = "";
	private boolean onOff = false;
	private int format;
	public static long total;
	
	private static Instrumentation singleton;
	public static Instrumentation ins = Instrumentation.getIns();
	
	public Instrumentation() {
		start[format] = System.nanoTime();
		finish = System.nanoTime();
		format = 0;
		total = 0;
	}
	
	public static Instrumentation getIns() {
		if (singleton == null) {
			singleton = new Instrumentation();
		}
		return singleton;
	}
	
	void startTiming(String comment) {
		if (onOff) {
			start[format] = System.nanoTime()/1000000;
			String newLogLine = new String(new char[format]).replace("\0", "|    ") + "STARTTIMING: " + comment + "\n";
			System.out.println(newLogLine);
			comment(newLogLine);
			format++;
		}
	}
	
	void stopTiming(String comment) {
		if (onOff) {
			format--;
			finish = System.nanoTime()/1000000 - start[format];
			String newLogLine = new String(new char[format]).replace("\0", "|    ") + "STOPTIMING:  " + comment + " " + finish + "ms\n";
			System.out.println(newLogLine);
			comment(newLogLine);
		}
		if (format == 0) {
			total += finish;
		}
	}
	
	String comment(String comment) {
		if (onOff) {
			logString = logString + comment;
			return logString;
		}
		else
			return null;
	}
	
	void dump(String fileName) {
		if (onOff) {
			if (fileName == null) {
				String timeStamp = new SimpleDateFormat("ddyyMMhhmmss").format(new java.util.Date());
				fileName = "instrumentation" + timeStamp + ".log";
			} else
				fileName = fileName + ".log";
			try {
				FileWriter logFile = new FileWriter(fileName);
				BufferedWriter buffer = new BufferedWriter(logFile);
				buffer.write(logString);
				buffer.close();
				System.out.println(fileName + " created");
			}
			catch (IOException e) {
				System.out.println("An error has occurred.");
				e.printStackTrace();
			}
		}
	}
	
	void activate(boolean onOff) {
		this.onOff = onOff;
	}
	
	public static void main(String[] args) {
		ins.activate(true);
		ins.startTiming("test");
//		ins.dump(null);
//		ins.comment("Hello World");
		ins.stopTiming("test");
	}
}

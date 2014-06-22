package it.hackcaffebabe.logger;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * <p>
 * Simple singleton class to instance a logger.<br>
 * Use the code below to retrieve the same logger object into the project.
 * <pre>
 * Logger g = Logger.getInstance();
 * g.write( Tag.ERROR, "asD" ); (to write a log message on the print stream)
 * </pre>
 * And to set the logger in Production mode:
 * <p><pre>
 * g.disableTag(Tag.DEBUG);
 * g.setPrintStream( new PrintStream( new File( "~/a.log" ) ); (to set the stream on the log file)
 * </pre></p>
 * </p>
 * 
 * @author Andrea Ghizzoni. More info at andrea.ghz@gmail.com
 * @version 1.0
 */
public class Logger
{
	/** The default main stream*/
	public static final PrintStream DEFAULT_PRINT_STREAM = System.out;
	/* The output stream to write the log*/
	private PrintStream printStream;

	private static Logger logger;
	private String messagePattern = "[%s] %s | %-7s: %s\n";//[<date&hours>]<class.method caller> | <tag>: <log>
	/* The set of disable tag */
	private Set<Tag> disableTag = new HashSet<>();

	/**
	 * Returns the instance of logger object.<br> 
	 * @return {@link Logger} the object represents the logger manager.
	 */
	public static Logger getInstance(){
		if(Logger.logger == null)
			Logger.logger = new Logger();
		return Logger.logger;
	}

	/**
	 * Instance the logger with default print stream.
	 */
	private Logger(){
		this.printStream = Logger.DEFAULT_PRINT_STREAM;
	}

//====================================================================================================//
// METHOD
//====================================================================================================//		
	/**
	 * Write a specific log message with tag {@link Tag}
	 * @param tag {@link Tag} the tag of log message.
	 * @param obj {@link String} the message of log.
	 */
	public synchronized void write(Tag tag, Object obj){
		if(obj == null || obj.toString().isEmpty())
			return;
		if(this.disableTag.contains( tag ))
			return;

		StackTraceElement s = new Exception().getStackTrace()[1];
		String formatted = String.format( "%s.%s", s.getClassName(), s.getMethodName() );
		String date = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.SSS" ).format( new Date() );
		this.printStream.printf( this.messagePattern, date, formatted, tag.toString(), obj.toString() );
		this.printStream.flush();
	}

	/**
	 * This method disable specific tag on <code>write()</code> method.
	 * @param t {@link Tag} the tag to disable. Pass null to clear all disable tag.
	 */
	public void disableTag(Tag t){
		if(t == null)
			this.disableTag.clear();
		this.disableTag.add( t );
	}

//====================================================================================================//
// SETTER
//====================================================================================================//
	/**
	 * Sets the default print stream on witch will be write the log message.
	 * @param printStream {@link PrintStream} the stream that will be write the log message.
	 * @throws IllegalArgumentException if printStream is null.
	 */
	public void setPrintStream(PrintStream printStream) throws IllegalArgumentException{
		if(printStream == null)
			throw new IllegalArgumentException( "Print stream can not ben null." );

		this.printStream = printStream;
	}

//====================================================================================================//
// GETTER
//====================================================================================================//
	/**
	 * Returns the print stream that will be write all the log message.
	 * @return {@link PrintStream} the print stream.
	 */
	public PrintStream getPrintStream(){
		return this.printStream;
	}
}
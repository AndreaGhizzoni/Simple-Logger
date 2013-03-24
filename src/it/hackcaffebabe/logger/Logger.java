package it.hackcaffebabe.logger;

import java.io.PrintStream;
import java.util.Date;

/**
 * Simple skeleton class to instance a logger.<br><br>
 * Use the code below to retrieve the same logger object into the project.<br>
 * <p><code>Logger g = Logger.getInstance();</code><br></p>
 * After to get the instance use:<br>
 * - <code>setPrintStream( PrintStream stream )</code> to set the stream on which to write the log messages.<br> 
 * - <code>write( String tag, String log )</code> to write a log message on the print stream.<br>
 * <br>
 * Copyright &copy 2013. All rights reserved. 
 * @author Andrea Ghizzoni. More info at andrea.ghz@gmail.com
 *
 */
public class Logger
{
	/**The default main stream*/
	public static final PrintStream DEFAULT_RPRINT_STREAM = System.out;
	/**The output stream to write the log*/
	private PrintStream printStream;

	private static Logger logger;
	private String messagePattern = "[%s] %-11s: %s";//[<date hours>]<tag>:\t<log>
	
	
	/**
	 * Returns the instance of logger object.<br> 
	 * @return {@link Logger} the object represents the logger manager.
	 */
	public static Logger getInstance()
	{
		if( Logger.logger == null )
			Logger.logger = new Logger();
		return Logger.logger;
	}
	
	
	/**
	 * Instance the logger with default print stream.
	 */
	private Logger()
	{
		this.printStream = Logger.DEFAULT_RPRINT_STREAM;
	}
	

/*====================================================================================================*/
/*====================================================================================================*/
/*																									  */
/*										   	   METHODS												  */	
/*																									  */
/*====================================================================================================*/
/*====================================================================================================*/		
	/**
	 * Write a specific log message with tag {@link Tag}
	 * @param tag {@link Tag} the tag of log message.
	 * @param log {@link String} the message of log.
	 */
	public synchronized void write( Tag tag, String log )
	{
		if( log == null || log.isEmpty() )
			return;
	
		this.printStream.append( String.format( this.messagePattern, new Date(), tag.toString(), log ) );
		this.printStream.append( '\n' );
		this.printStream.flush();//flush the steam
	}
	
	
/*====================================================================================================*/
/*====================================================================================================*/
/*																									  */
/*										   	   SETTER											  	  */	
/*																									  */
/*====================================================================================================*/
/*====================================================================================================*/	
	/**
	 * Sets the default print stream on witch will be write the log message.
	 * @param printStream {@link PrintStream} the stream that will be write the log message.
	 * @throws IllegalArgumentException {@link Exception} if printStream is null.
	 */
	public void setPrintStream( PrintStream printStream ) throws IllegalArgumentException
	{
		if( printStream == null )
			throw new IllegalArgumentException( "Print stream can not ben null." );
		
		this.printStream = printStream;
	}
	
	
/*====================================================================================================*/
/*====================================================================================================*/
/*																									  */
/*										   	   GETTER											  	  */	
/*																									  */
/*====================================================================================================*/
/*====================================================================================================*/	
	/**
	 * Returns the print stream that will be write all the log message.
	 * @return {@link PrintStream} the print stream.
	 */
	public PrintStream getPrintStream()
	{
		return this.printStream;
	}
}
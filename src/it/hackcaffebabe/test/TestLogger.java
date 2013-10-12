package it.hackcaffebabe.test;

import it.hackcaffebabe.logger.Logger;
import it.hackcaffebabe.logger.Tag;

public class TestLogger
{
	public static void main(String[] args){
		m1();
	}
	
	public static void m1(){
		m2();
	}
	
	public static void m2(){
		m3();	
	}
	
	public static void m3(){
		m4();
	}
	
	public static void m4(){
		m5();
	}
	
	public static void m5(){
		m6();
	}
	
	public static void m6(){
		 new C1(1,1).cm1();
	}
	
	public static class C1{
		int s =0;
		C1(int i, int o){
			s=i+o;
		}
		public void cm1(){
			Logger.getInstance().write( Tag.DEBUG, "asd" );
			Logger.getInstance().write( Tag.ERRORS, "asd" );
			Logger.getInstance().write( Tag.PANIC, "asd" );
			Logger.getInstance().write( Tag.INFO, "asd" );
			Logger.getInstance().write( Tag.VERBOSE, "asd" );
			Logger.getInstance().write( Tag.WARNING, "asd" );
		}
	}
}

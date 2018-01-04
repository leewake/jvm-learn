package com.pangpang.jvm.load.constant;
/** 
* @author  : lijingwei
* @version ：2018年1月4日 下午4:18:39 
*/
public class ConstantClass {
	
	public static final String HELLOWORLD = "hello world";
	
	static{
		System.out.println("ConstantClass init!");
	}
	
	/**
	 * run main(), get the result:
	 * ConstantClass init!
	 * hello world
	 */
	public static void main(String[] args) {
		System.out.println(ConstantClass.HELLOWORLD);
	}
	
}

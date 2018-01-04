package com.pangpang.jvm.load.supsub;

/** 
* @author  : lijingwei
* @version ：2018年1月4日 下午4:19:18 
*/
public class RunClass {
	
	/**
	 * run main(), get the result:
	 * SuperClass init!
	 * 123
	 * not show 'SubClass init!'
	 */
	public static void main(String[] args) {
		System.out.println(SubClass.value);
	}

}

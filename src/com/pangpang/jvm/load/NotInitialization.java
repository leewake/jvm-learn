package com.pangpang.jvm.load;
/** 
* @author  : lijingwei
* @version ：2018年1月4日 下午4:19:18 
*/
public class NotInitialization {
	
	/**
	 * run main(), get the result:
	 * not show 'ConstantClass init!'
	 * hello world
	 */
	public static void main(String[] args) {
		System.out.println(ConstantClass.HELLOWORLD);
	}

}

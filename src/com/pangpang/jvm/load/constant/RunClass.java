package com.pangpang.jvm.load.constant;

import com.pangpang.jvm.load.constant.ConstantClass;

/** 
* @author  : lijingwei
* @version ：2018年1月4日 下午4:19:18 
*/
public class RunClass {
	
	/**
	 * run main(), get the result:
	 * not show 'ConstantClass init!'
	 * only show 'hello world'
	 */
	public static void main(String[] args) {
		System.out.println(ConstantClass.HELLOWORLD);
	}

}

package com.pangpang.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/** 
* @author  : lijingwei
* @version ：2018年1月24日 上午10:08:32 
*/
public class PhaserDemo implements Runnable{
	
	@SuppressWarnings("unused")
	private String threadName;
	private Phaser phaser;
	
	public PhaserDemo(String threadName, Phaser phaser) {
		this.threadName = threadName;
		this.phaser = phaser;
		phaser.register();
	}

	@Override
	public void run() {
		System.err.println("This is phaser " + phaser.getPhase());
		//这个应该统计的是注册数
		System.err.println("phaser " + phaser.getPhase() + " 到达的参与者数为:" + phaser.getArrivedParties());
		phaser.arriveAndAwaitAdvance();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		phaser.arriveAndDeregister();
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Phaser phaser = new Phaser(1);
		
		executorService.submit(new PhaserDemo("Thread-One", phaser));
		executorService.submit(new PhaserDemo("Thread-Two", phaser));
		executorService.submit(new PhaserDemo("Thread-Three", phaser));
		
		//这一步是因为实例化Phaser时,参与者为1
		int arriveAndAwaitAdvance = phaser.arriveAndAwaitAdvance();
		System.out.println("---------");
		System.out.println(arriveAndAwaitAdvance);
		System.out.println("---------");
		
		System.out.println(phaser.getPhase());
		System.out.println(phaser.getRegisteredParties());
		System.out.println();
		
		executorService.submit(new PhaserDemo("Thread-Four", phaser));
		executorService.submit(new PhaserDemo("Thread-Five", phaser));
		phaser.arriveAndAwaitAdvance();
		System.out.println(phaser.getPhase());
		System.out.println(phaser.getRegisteredParties());
		System.out.println();
		
		int arriveAndDeregister = phaser.arriveAndDeregister();
		System.out.println("注销主线程时,所处的阶段:" + arriveAndDeregister);
		System.out.println(phaser.awaitAdvance(2));
		System.out.println(phaser.arriveAndAwaitAdvance());
	}

}

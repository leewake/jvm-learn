package com.pangpang.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author : lijingwei
 * @version ：2018年1月23日 下午4:58:05
 */
public class CyclicBarrierDemo implements Runnable {

	private CyclicBarrier cyclicBarrier;

	public CyclicBarrierDemo(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " is awaiting!");
			//每进行一次await(),会将参与者-1,最后通过与参与者相减,即可求出等待的线程数
			cyclicBarrier.await();
			System.out.println(Thread.currentThread().getName() + " has released!");
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
			@Override
			public void run() {
				System.out.println("All previous tasks are completed");
			}
		});
		Thread threadOne = new Thread(new CyclicBarrierDemo(cyclicBarrier), "Thread one");
		Thread threadTwo = new Thread(new CyclicBarrierDemo(cyclicBarrier), "Thread two");

		System.err.println(cyclicBarrier.getParties());
		if (!cyclicBarrier.isBroken()) {
			threadOne.start();
			threadTwo.start();
			//sleep一秒，不然太快，不会触发CyclicBarrierDemo中的await方法
			//从而使得getNumberWaiting()方法为0
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.err.println(cyclicBarrier.getNumberWaiting());
	}

}

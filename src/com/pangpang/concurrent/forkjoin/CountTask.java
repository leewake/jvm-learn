package com.pangpang.concurrent.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/** 
* @author  : lijingwei
* @version ：2018年1月23日 上午10:30:03 
*/
public class CountTask extends RecursiveTask<Long> {

	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD = 2;//阈值
	private Long start;
	private Long end;
	
	public CountTask(Long start, Long end) {
		super();
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		Long sum = 0L;
		if (end - start <= THRESHOLD) {
			for (Long i = start; i <= end; i++) {
				sum += i;
			}
		} else {
			Long middle = (start + end) / 2;
			CountTask subCountTask1 = new CountTask(start, middle);
			CountTask subCountTask2 = new CountTask(middle + 1, end);
			
			subCountTask1.fork();
			subCountTask2.fork();
			
			Long leftSum = subCountTask1.join();
			Long rightSum = subCountTask2.join();
			
			sum = leftSum + rightSum;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool = new ForkJoinPool();
		CountTask countTask = new CountTask(1L, 1000000L);
		ForkJoinTask<Long> submit = forkJoinPool.submit(countTask);
		try {
			System.err.println(submit.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
	}

}

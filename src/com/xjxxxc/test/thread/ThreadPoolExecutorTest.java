package com.xjxxxc.test.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
* @author: xujunxian
* @Description:   创建线程的测试代码 
*/
public class ThreadPoolExecutorTest {
	/**
	 * 循环次数
	 */
	private static Integer COUNT = 10;

	/**
	 * 创建一个可缓存线程池，如果线程池长度超过处理需要，
	 * 可灵活回收空闲线程，若无可回收，则新建线程。
	 */
	public static void newCachedThreadPool() {
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < COUNT; i++) {
			final int index = i;
			try {
				Thread.sleep(index * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			cachedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println(index);
				}
			});
		}
	}

	/**
	 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	 */
	public static void newFixedThreadPool() {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < COUNT; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	 * 创建一个定长线程池，支持定时及周期性任务执行。
	 * 表示延迟3秒执行。
	 */
	public static void newScheduledThreadPool() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 3 seconds");
			}
		}, 3, TimeUnit.SECONDS);
	}

	/**
	 * 创建一个定长线程池，支持定时及周期性任务执行。
	 * 表示延迟1秒后每3秒执行一次。
	 */
	public static void newScheduledThreadPool2() {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}

	/**
	 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，
	 * 保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	 */
	public static void newSingleThreadExecutor() {
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		for (int i = 0; i < COUNT; i++) {
			final int index = i;
			singleThreadExecutor.execute(new Runnable() {
				@Override
				public void run() {
					try {
						System.out.println(index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}

	}

	public static void main(String[] args) {
		newCachedThreadPool();

		newFixedThreadPool();

		newScheduledThreadPool();
		newScheduledThreadPool2();

		newSingleThreadExecutor();

	}
}
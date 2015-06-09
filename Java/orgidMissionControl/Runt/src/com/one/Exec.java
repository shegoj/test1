package com.one;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * 
 * @author UC186742
 *
 */
public class Exec {
	
	
	
	public static void main  (String rags[]) 
	{
		
		List <Runnable> runnables = new ArrayList<Runnable>();
		
		// instantiate MyRunnables 
		for (int i = 1; i < 7; ++i)
		{
			runnables.add(new MyRunnable(i));
		}
		
		Executor executor = Executors.newFixedThreadPool(3);
		
		for (Runnable task : runnables)
		{
			executor.execute(task);
		}
	}

}

class MyRunnable implements Runnable {
	
	int i ; //  thread number in the list

	public MyRunnable (int num) 
	{
		
		this.i = num;
	}
	
	@Override
	public void run () 
	{
		
		/** Just do a simple task to test how the executor works 
		 *  and combine with executor class
		 */
		
		System.out.println("run called successfully " + i);
		
	}
}

package nl.fw.yapool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ScheduledExecutorService;

import nl.fw.yapool.PoolPruner;
import nl.fw.yapool.PruneTask;
import nl.fw.yapool.PrunedPool;

public class SharedTestPruner extends PoolPruner {
	
	@Override
    protected PruneTask createPruneTask(ScheduledExecutorService executor, PrunedPool<?> pool) {
		return new PruneTestTask(executor, pool);
	}
	
	public static class PruneTestTask extends PruneTask {
		
		public CountDownLatch latch;
		
		public PruneTestTask (ScheduledExecutorService executor, PrunedPool<?> pool) {
			super(executor, pool);
			latch = new CountDownLatch(1);
		}
		
		@Override
		public void run() {
			latch.countDown();
			super.run();
		}
	}
}

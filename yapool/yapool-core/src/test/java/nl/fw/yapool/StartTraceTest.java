package nl.fw.yapool;

import nl.fw.yapool.listener.LeaserAcquiredTrace;

public class StartTraceTest extends LeaserAcquiredTrace {

	public int logAmount;
	public boolean showLog;
	
	public StartTraceTest(BoundPool<?> pool) {
		super(pool);
	}
	
	@Override
	protected void log(String msg) {
		logAmount++;
		if (showLog) {
			super.log(msg);
		}
	}

}

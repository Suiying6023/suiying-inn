package 第九章.习题.Nine6;

public class Stopwatch {
	private long startTime;
	private long stopTime;
	private boolean running;

	public void start() {
		this.startTime = System.currentTimeMillis();
		this.running = true;
	}

	public void stop() {
		this.stopTime = System.currentTimeMillis();
		this.running = false;
	}

	public long getElapsedTime() {
		long elapsed;
		if (running) {
			elapsed = (System.currentTimeMillis() - startTime);
		} else {
			elapsed = (stopTime - startTime);
		}
		return elapsed;
	}

	public void reset() {
		this.startTime = 0;
		this.stopTime = 0;
		this.running = false;
	}
}

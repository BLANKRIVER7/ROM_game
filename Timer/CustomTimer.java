package Timer;

public class CustomTimer implements Runnable {
	private int time = 1;
	private int time2 = 1;
	private int time3 = 1;
	private boolean thFlag = true;
	private Thread th;
	
	@Override
	public void run() {
		int time2Cnt = 0;
		int time3Cnt = 0;
		try {
			while(thFlag) {
				time2Cnt++;
				time3Cnt++;
				
				time ++;
				
				if(time2Cnt >= 10) {
					time2++;
					time2Cnt = 0;
				}
				if(time3Cnt >= 10) {
					time3++;
					time3Cnt = 0;
				}
				th.sleep(500);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getTime() {
		return time;
	}
	public void setTime2(int time2) {
		this.time2 = time2;
	}
	public int getTime2() {
		return time2;
	}
	public void setTime3(int time3) {
		this.time3 = time3;
	}
	public int getTime3() {
		return time3;
	}
	public void setThFlag(boolean thFlag) {
		this.thFlag = thFlag; 
	}
	public void setTh(Thread th) {
		this.th = th;
	}
}

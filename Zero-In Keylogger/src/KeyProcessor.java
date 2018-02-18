import java.util.concurrent.Executors;

public class KeyProcessor {

	public static void main(String[] args) throws InterruptedException{
		Executors.newSingleThreadExecutor().execute(new Runnable() {
			@Override
			public void run() {
				KeyInputter.main(args);
			}
		});
		while (true) {
			//Making booleans for whether or not gaming is suspected/detected
			boolean maybeFramed = false;
			boolean defMaf = false;
			
			//making ints for how many sus keys typed in last 10 seconds and how many total keys in last 10 seconds
			int tenSusSum = 0;
			int tenSum = 0;
			
			//making ints for how many sus keys typed in last 30 seconds and how many total keys in last 30 seconds
			int thirtySusSum = 0;
			int thirtySum = 0;
			
			//if statement is to prevent checking for sus activity from a small time frame
			if ((System.currentTimeMillis() + 500 - KeyInputter.startTime) % 10000 == 0) {
				//add all the sus keys together into tenSusSum
				for (int i = 0; i < 8; i++) {
					tenSusSum +=KeyInputter.zerotensec[i]; 
				}
				tenSum = KeyInputter.zerotensec[8];
				
				if((double)tenSusSum/(double)tenSum >= 0.5 && tenSum > 10) {
					maybeFramed = true;
					System.out.println("test");
					
					//sleep the thread for 30 seconds, and prevent errors
					Thread.sleep(30000);
					
					//add all the sus keys together into thirtySusSum
					for (int i = 0; i < 8; i ++) {
						thirtySusSum += KeyInputter.zerotensec[i];
						thirtySusSum += KeyInputter.tentwentysec[i];
						thirtySusSum += KeyInputter.twentythirtysec[i];	
					}
					thirtySum += KeyInputter.zerotensec[8];
					thirtySum += KeyInputter.tentwentysec[8];
					thirtySum += KeyInputter.twentythirtysec[8];
					
					if (thirtySusSum/thirtySum >= .5 && thirtySum > 20) {
						defMaf = true;
						System.out.println("icle");
					}
				}
			}
			/**try { Thread.sleep(500); }
			catch(InterruptedException e) {}**/
			//System.out.println(maybeFramed + "" + defMaf);
			//System.out.println(tenSusSum + " " + tenSum);
			//System.out.println(KeyInputter.zerotensec[5]);
		}
	}

}

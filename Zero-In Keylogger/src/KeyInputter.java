import java.util.Map.Entry;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

public class KeyInputter {
	
	//Creating methods that add values to the arrays whenever keystrokes are recorded
	public static void addAll () {
		hundredsec[8]++;
		zerotensec[8]++;
	}
	public static void addW () {
		hundredsec[0]++;
		zerotensec[0]++;
	}
	public static void addA () {
		hundredsec[1]++;
		zerotensec[1]++;
	}
	public static void addS () {
		hundredsec[2]++;
		zerotensec[2]++;
	}
	public static void addD () {
		hundredsec[3]++;
		zerotensec[3]++;
	}
	public static void addUp () {
		hundredsec[4]++;
		zerotensec[4]++;
	}
	public static void addDown () {
		hundredsec[5]++;
		zerotensec[5]++;
	}
	public static void addLeft () {
		hundredsec[6]++;
		zerotensec[6]++;
	}
	public static void addRight () {
		hundredsec[7]++;
		zerotensec[7]++;
	}
	
	//creating arrays that record keystrokes of past 100 seconds, with individual measurements of every ten seconds
	// {w, a, s, d, up, down, left, right, total}
	public static int[] hundredsec = {0,0,0,0,0,0,0,0,0};
	public static int[] zerotensec = {0,0,0,0,0,0,0,0,0};
	public static int[] tentwentysec = {0,0,0,0,0,0,0,0,0};
	public static int[] twentythirtysec = {0,0,0,0,0,0,0,0,0};
	public static int[] thirtyfourtysec = {0,0,0,0,0,0,0,0,0};
	public static int[] fourtyfiftysec = {0,0,0,0,0,0,0,0,0};
	public static int[] fiftysixtysec = {0,0,0,0,0,0,0,0,0};
	public static int[] sixtyseventysec = {0,0,0,0,0,0,0,0,0};
	public static int[] seventyeightysec = {0,0,0,0,0,0,0,0,0};
	public static int[] eightyninetysec = {0,0,0,0,0,0,0,0,0};
	public static int[] ninetyhundredsec = {0,0,0,0,0,0,0,0,0};
	public static int[] hundredelevensec = {0,0,0,0,0,0,0,0,0};
	
	//Key logger
	private static boolean run = true;
	public static void main(String[] args) {
		//creating long startTime, to use as a reference for a timer. Every ten seconds the key counters will refresh
		long startTime = System.currentTimeMillis();
		
		//creating keyboard hook
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);

		System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
		
		//listing keyboard, this should be gotten rid of once program is done
		for(Entry<Long,String> keyboard:GlobalKeyboardHook.listKeyboards().entrySet()) {
			System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
		}
		
		//creating listeners to get the key presses
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			//key presses for pushing down
			@Override public void keyPressed(GlobalKeyEvent event) {
				//System.out.println(event);
				if(event.getVirtualKeyCode()==GlobalKeyEvent.VK_ESCAPE)
					run = false;
			}
			//key presses for lifting up
			@Override public void keyReleased(GlobalKeyEvent event) {
				//checking the input and adding keystrokes to the array accordingly
				addAll();
				if (event.getVirtualKeyCode()==GlobalKeyEvent.VK_W) {
					addW();
				}
				else if (event.getVirtualKeyCode()==GlobalKeyEvent.VK_A) {
					addA();
				}
				else if (event.getVirtualKeyCode()==GlobalKeyEvent.VK_S) {
					addS();
				}
				else if (event.getVirtualKeyCode()==GlobalKeyEvent.VK_D) {
					addD();
				}
				else if (event.getVirtualKeyCode()==GlobalKeyEvent.VK_UP) {
					addUp();
				}
				else if (event.getVirtualKeyCode()==GlobalKeyEvent.VK_DOWN) {
					addDown();
				}
				else if (event.getVirtualKeyCode()==GlobalKeyEvent.VK_LEFT) {
					addLeft();
				}
				else if (event.getVirtualKeyCode()==GlobalKeyEvent.VK_RIGHT) {
					addRight();
				}
				System.out.println(event); }
				
		});
		try {
			while(run) Thread.sleep(128);
		} catch(InterruptedException e) { /* nothing to do here */ }
		  finally { keyboardHook.shutdownHook(); }
	}
}

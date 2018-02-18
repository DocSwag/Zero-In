import java.util.Map.Entry;

import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;



public class KeyInputter {
	public int totalStrokes = 0;
	public int wStrokes = 0;
	public int aStrokes = 0;
	public int sStrokes = 0;
	public int dStrokes = 0;
	public int upStrokes = 0;
	public int downStrokes = 0;
	public int leftStrokes = 0;
	public int rightStrokes = 0;
	
	//Keylogger
	private static boolean run = true;
	public static void main(String[] args) {
		
		GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook(true);

		System.out.println("Global keyboard hook successfully started, press [escape] key to shutdown. Connected keyboards:");
		
		for(Entry<Long,String> keyboard:GlobalKeyboardHook.listKeyboards().entrySet()) {
			System.out.format("%d: %s\n", keyboard.getKey(), keyboard.getValue());
		}
		
		keyboardHook.addKeyListener(new GlobalKeyAdapter() {
			@Override public void keyPressed(GlobalKeyEvent event) {
				System.out.println(event);
				if(event.getVirtualKeyCode()==GlobalKeyEvent.VK_ESCAPE)
					run = false;
			}
			@Override public void keyReleased(GlobalKeyEvent event) {
				System.out.println(event); }
		});
		
		try {
			while(run) Thread.sleep(128);
		} catch(InterruptedException e) { /* nothing to do here */ }
		  finally { keyboardHook.shutdownHook(); }
	}
}

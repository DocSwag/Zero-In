import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class KeyInputter implements NativeKeyListener {
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

	//after 10 sec, run this method so all values get shifted (counter resets)
	public static void shifter () {
		for (int i = 0; i < 9; i ++) {
			hundredsec[i] -= ninetyhundredsec[i];
			ninetyhundredsec[i] = eightyninetysec[i];
			eightyninetysec[i] = seventyeightysec[i];
			seventyeightysec[i] = sixtyseventysec[i];
			sixtyseventysec[i] = fiftysixtysec[i];
			fiftysixtysec[i] = fourtyfiftysec[i];
			fourtyfiftysec[i] = thirtyfourtysec[i];
			thirtyfourtysec[i] = twentythirtysec[i];
			twentythirtysec[i] = tentwentysec[i];
			tentwentysec[i] = zerotensec[i];
			zerotensec [i] = 0;
		}
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
	
	//creating long startTime, to use as a reference for a timer. Every ten seconds the key counters will refresh
	static long startTime = System.currentTimeMillis();
	
	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));

		if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			if (e.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
				try {
					GlobalScreen.unregisterNativeHook();
				} catch (NativeHookException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		while (System.currentTimeMillis() - startTime >= 10000) {
			shifter();
			startTime += 10000;
		}
		
		//checking the input and adding keystrokes to the array accordingly
		addAll();
		if (e.getKeyCode()==NativeKeyEvent.VC_W) {
			addW();
		}
		else if (e.getKeyCode()==NativeKeyEvent.VC_A) {
			addA();
		}
		else if (e.getKeyCode()==NativeKeyEvent.VC_S) {
			addS();
		}
		else if (e.getKeyCode()==NativeKeyEvent.VC_D) {
			addD();
		}
		else if (e.getKeyCode()==NativeKeyEvent.VC_UP) {
			addUp();
		}
		else if (e.getKeyCode()==NativeKeyEvent.VC_DOWN) {
			addDown();
		}
		else if (e.getKeyCode()==NativeKeyEvent.VC_LEFT) {
			addLeft();
		}
		else if (e.getKeyCode()==NativeKeyEvent.VC_RIGHT) {
			addRight();
		}
	}

	public void nativeKeyTyped(NativeKeyEvent e) {
		System.out.println("Key Typed: " + e.getKeyText(e.getKeyCode()));
	}

	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();
		}
		catch (NativeHookException ex) {
			System.err.println("There was a problem registering the native hook.");
			System.err.println(ex.getMessage());

			System.exit(1);
		}

		GlobalScreen.addNativeKeyListener(new KeyInputter());
	}
}
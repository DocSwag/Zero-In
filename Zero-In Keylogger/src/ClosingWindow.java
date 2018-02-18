import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

public class ClosingWindow
{
    public static void main (String [] args){
        try
        {
            Robot key = new Robot();
            key.keyPress(KeyEvent.VK_META);
            key.keyPress(KeyEvent.VK_Q);
            key.keyRelease(KeyEvent.VK_META);
            key.keyRelease(KeyEvent.VK_Q);
        }
        catch(AWTException e)
        {
            e.printStackTrace();
        }
    }
}

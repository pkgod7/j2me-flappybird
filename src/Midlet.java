import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
public class Midlet extends MIDlet
{
    public FlappyBirdo c1=new FlappyBirdo();
    public void startApp()
    {
        Display.getDisplay(this).setCurrent(c1);
        //c1.run();
        c1.draw();
    }
    public void pauseApp()
    {
    }
    public void destroyApp(boolean unconditional)
    {
    }
}

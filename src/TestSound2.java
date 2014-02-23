import javax.microedition.media.*;
public class TestSound2 extends Thread
{   
    public String x1;
    public TestSound2(String x)
    {
        x1=x;
        try
        {
            Manager.createPlayer(getClass().getResourceAsStream(x1+".wav"),"audio/x-wav").start();
            Thread.sleep(50);
        }
        catch(Exception err)
        {
        }
    }
    public void run()
    {
    }
}
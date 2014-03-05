import javax.microedition.media.*;
public class TestSound2 extends Thread
{   
    public String x1;
    public Player p1;
    public TestSound2(String x)
    {
        x1=x;
    }
    public void run()
    {
        try
        {
            p1=Manager.createPlayer(getClass().getResourceAsStream(x1+".wav"),"audio/x-wav");
            p1.start();
            Thread.sleep(550);
        }
        catch(Exception err)
        {
        }
        finally
        {
            p1.deallocate();
            p1.close();
        }
    }
}
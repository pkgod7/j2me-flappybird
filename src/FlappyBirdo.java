import java.util.Random;
import javax.microedition.lcdui.game.*;
import javax.microedition.lcdui.*;
public class FlappyBirdo extends GameCanvas
{
    public LayerManager main=new LayerManager();
    public Sprite flappy;
    public int flappy1=50;
    public int flappy2=160;
    public int flappy3=5;
    public Sprite floor;
    public int floor1=0;
    public Sprite pipe[];
    public int pipe1[];
    public int pipe2[];
    public int pipe3=1;
    public Random pipe4;
    public Sprite pipe5[];
    public Sprite background;
    public int extraScore=0;
    public String extraScoreCheck[];
    public String extraPause="false";
    public String extraStatus="";
    public int extraDifficulty=4;
    public Sprite extraGameOver;
    public String extraDie="false";
    public String extraStart="false";
    public Sprite extraStart2;
    public String extraWin="false";
    public Sprite extraWin2;
    public String noob="true";
    public FlappyBirdo()
    {
        super(true);
        try
        {
            extraGameOver=new Sprite(Image.createImage("pk4.png"));
            extraGameOver.setVisible(false);
            main.append(extraGameOver);
            extraStart2=new Sprite(Image.createImage("pk5.png"));
            extraStart2.setVisible(true);
            main.append(extraStart2);
            flappy=new Sprite(Image.createImage("pk.png"),40,32);
            flappy.setFrame(8);
            main.append(flappy);
            extraWin2=new Sprite(Image.createImage("pk6.png"),300,300);
            extraWin2.setVisible(false);
            main.append(extraWin2);
            floor=new Sprite(Image.createImage("pk3.png"));
            main.append(floor);
            pipe=new Sprite[pipe3];
            pipe1=new int[pipe3];
            pipe2=new int[pipe3];
            pipe4=new Random();
            pipe5=new Sprite[pipe3];
            extraScoreCheck=new String[pipe3];
            for(int i=0; i<pipe3; i++)
            {
                pipe[i]=new Sprite(Image.createImage("pk2.png"));
                pipe1[i]=300+(i*100);
                pipe2[i]=-1*(pipe4.nextInt(100)+220);
                main.append(pipe[i]);
                pipe5[i]=new Sprite(Image.createImage("pk2.png"));
                main.append(pipe5[i]);
                extraScoreCheck[i]="true";
            }
            background=new Sprite(Image.createImage("pk1.png"));
            main.append(background);
        }
        catch(Exception err)
        {
        }
    }
    public void draw()
    {
        try
        {
            Graphics g = getGraphics();
            while(true)
            {
                keyboard(getKeyStates());
                g.setColor(255,255,255);
                g.fillRect(0,0,getWidth(),getHeight());
                flappy.setRefPixelPosition(flappy1,flappy2);
                if(extraPause.equals("false")&&extraStart.equals("true"))
                {
                        if(flappy.getFrame()==11)
                        {
                            flappy.setFrame(11);
                        }
                        else
                        {
                            flappy.nextFrame();
                        }
                        if(extraWin.equals("true"))
                        {
                            if(flappy.getFrame()>5)
                            {
                                flappy.setFrame(0);
                            }
                            extraStatus="Congratulation!";
                        }
                }
                floor.setRefPixelPosition(floor1,260);
                for(int i=0; i<pipe3; i++)
                {
                    pipe[i].setRefPixelPosition(pipe1[i],pipe2[i]);
                    pipe5[i].setRefPixelPosition(pipe1[i],320+130+pipe2[i]);
                }
                background.setRefPixelPosition(0,0);
                extraGameOver.setRefPixelPosition(20,50);
                extraStart2.setRefPixelPosition(12,50);
                extraWin2.setRefPixelPosition(0,0);
                extraWin2.nextFrame();
                main.paint(g,0,0);
                g.setColor(0,0,0);
                g.setFont(Font.getFont(Font.FACE_SYSTEM,Font.STYLE_BOLD,Font.SIZE_LARGE));
                g.drawString(""+extraScore,110,30,Graphics.TOP|Graphics.LEFT);
                g.drawString(extraStatus,0,290,Graphics.TOP|Graphics.LEFT);
                flushGraphics();
                if(pipe3==extraScore)
                {
                    extraWin="true";
                    extraWin2.setVisible(true);
                }
                if(extraStart.equals("true"))
                {
                    extraStatus="";
                    if(extraPause.equals("false"))
                    {
                        //gravity down
                        if(extraWin.equals("false"))
                        {
                            if((flappy2+1+flappy3)<250)
                            {
                                flappy2+=1;
                                flappy2+=flappy3;
                                flappy3+=1;
                            }
                        }
                        if(flappy2>=230)
                        {
                            flappy2=232;
                        }
                        //gravity left
                        if(floor1>-240)
                        {
                            floor1-=extraDifficulty;
                        }
                        if(floor1<=-220)
                        {
                            floor1=0;
                        }
                        //gravity left
                        for(int i=0; i<pipe3; i++)
                        {
                            if(pipe1[i]<flappy1)
                            {
                                if(extraScoreCheck[i].equals("true"))
                                {
                                    TestSound2 c1=new TestSound2("sound2");
                                    c1.start();
                                    extraScore+=1;
                                    extraScoreCheck[i]="false";
                                }
                            }
                            if(pipe1[i]>-50)
                            {
                                pipe1[i]-=extraDifficulty;
                            }
                        }
                    }
                    if(flappy.collidesWith(floor,false))
                    {
                        extraDie="true";
                        extraGameOver.setVisible(true);
                        extraStatus="Press fire key to restart";
                        extraPause="true";
                            if(noob.equals("true"))
                            {
                                noob="false";
                                TestSound2 c1=new TestSound2("sound3");
                                c1.start();
                            }
                    }
                    for(int i=0; i<pipe3; i++)
                    {
                        if(flappy.collidesWith(pipe[i],false))
                        {
                            extraDie="true";
                            extraGameOver.setVisible(true);
                            extraStatus="Press fire key to restart";
                            extraPause="true";
                            if(noob.equals("true"))
                            {
                                noob="false";
                                TestSound2 c1=new TestSound2("sound3");
                                c1.start();
                            }
                        }
                        if(flappy.collidesWith(pipe5[i],false))
                        {
                            extraDie="true";
                            extraGameOver.setVisible(true);
                            extraStatus="Press fire key to restart";
                            extraPause="true";
                            if(noob.equals("true"))
                            {
                                noob="false";
                                TestSound2 c1=new TestSound2("sound3");
                                c1.start();
                            }
                        }
                    }
                }
                Thread.sleep(80);
            }
        }
        catch(Exception err)
        {
        }
    }
    public void keyboard(int e)
    {
        if(extraStart.equals("true"))
        {
            if(extraPause.equals("false"))
            {
                if((e & FIRE_PRESSED)!=0)
                {
                        flappy.setFrame(0);
                        flappy3=0;
                        if(flappy2-20>-50)
                        {
                            flappy2-=20;
                        }
                        TestSound2 c1=new TestSound2("sound1");
                        c1.start();
                }
                if((e & RIGHT_PRESSED)!=0)
                {
                    extraStatus="HARD MODE";
                    extraDifficulty=8;
                }
                if((e & LEFT_PRESSED)!=0)
                {
                    extraStatus="EASY MODE";
                    extraDifficulty=4;
                }   
            }
            if(extraDie.equals("false"))
            {
                if((e & UP_PRESSED)!=0)
                {
                    extraStatus="PAUSE";
                    extraPause="true";
                }
                if((e & DOWN_PRESSED)!=0)
                {
                    extraStatus="RESUME";
                    extraPause="false";
                }
            }
            if(extraDie.equals("true"))
            {
                if((e & FIRE_PRESSED)!=0)
                {
                    noob="true";
                    extraScore=0;
                    extraPause="false";
                    extraDie="false";
                    flappy1=50;
                    flappy2=0;
                    extraGameOver.setVisible(false);
                    flappy.setFrame(8);
                    flappy3=0;
                    for(int i=0; i<pipe3; i++)
                    {
                        pipe1[i]=300+(i*100);
                        pipe2[i]=-1*(pipe4.nextInt(100)+220);
                        extraScoreCheck[i]="true";
                    }
                    TestSound2 c1=new TestSound2("sound1");
                    c1.start();
                }
            }
        }
        if(extraStart.equals("false"))
        {
            if((e & FIRE_PRESSED)!=0)
            {
                extraStart2.setVisible(false);
                extraStart="true";
                flappy.setFrame(0);
                flappy2-=70;
                TestSound2 c1=new TestSound2("sound1");
                c1.start();
            }
        }
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SWShip extends SmoothMover
{
    private String type; 
    private int num;

    private double speed=0;
    private double maxbackspeed=-1.0;
    private double maxspeed;
    private double turn;

    private double health;
    private double maxhealth;
    private HealthBoard hb;
    private GreenfootSound explosion=new GreenfootSound("sounds/Explosion.wav");
    private String explosionpng="images/explosion.png";

    private double damage;
    private double energy=0;
    private double fireenergy=20;
    private boolean fire=false;
    private double maxenergy=20;
    private double recharge;

    private int shotsFired=0;
    private boolean specialLasers;
    private String color;
    private GreenfootSound sound;//=new GreenfootSound("sounds/ISD-Laser1.wav");

    private String KeyFaster;
    private String KeyLeft;
    private String KeyRight;
    private String KeySlower;
    private String KeyShoot;

    public SWShip(String ptype,int pnum)
    {
        type=ptype;
        num=pnum;
        switch(num)
        {
            case 0:
            KeyFaster="w";
            KeyLeft="a";
            KeyRight="d";
            KeySlower="s";
            KeyShoot="space";
            break;

            case 1:
            KeyFaster="up";
            KeyLeft="left";
            KeyRight="right";
            KeySlower="down";
            KeyShoot="enter";
            break;

            case 2:
            KeyFaster="i";
            KeyLeft="j";
            KeyRight="l";
            KeySlower="k";
            KeyShoot="h";
            break;
        }

        if(type.equals("isd"))
        {
            setImage("images/isd.png");

            maxspeed=3;
            turn=1;

            health=100;

            damage=10;
            maxenergy=20;
            recharge=1;

            specialLasers=false;
            color="Gr";
            sound=new GreenfootSound("sounds/ISD-Laser1.wav");
        }

        if(type.equals("mcs"))
        {
            setImage("images/mcs.png");

            maxspeed=3;
            turn=1;

            health=100;

            damage=10;
            maxenergy=20;
            recharge=1;

            specialLasers=false;
            color="Ro";
            sound=new GreenfootSound("sounds/ISD-Laser1.wav");
        }

        if(type.equals("ds"))
        {
            setImage("images/deathstar.png");
            explosionpng="images/explosion-DS.png";

            maxspeed=0.5;
            maxbackspeed=-0.1;
            turn=0.2;

            health=1000;

            damage=10;
            maxenergy=1000;
            fireenergy=1000;
            recharge=2;

            specialLasers=false;
            color="Gr";
            sound=new GreenfootSound("sounds/ISD-Laser1.wav");
        }

        if(type.equals("tie"))
        {
            setImage("images/tie.png");

            maxbackspeed=1;
            speed=1;
            maxspeed=3.6;
            turn=3;

            health=7;

            damage=1;
            maxenergy=20;
            recharge=3;

            specialLasers=false;
            color="Gr2";
            sound=new GreenfootSound("sounds/TIE-f.wav");
        }

        if(type.equals("xwing"))
        {
            setImage("images/xwing.png");

            maxbackspeed=1;
            speed=1;
            maxspeed=5;
            turn=2;

            health=8;

            damage=1.5;
            maxenergy=20;
            recharge=5;

            specialLasers=true;
            color="Ro3";
            sound=new GreenfootSound("sounds/XWing-f.wav");
        }

        if(type.equals("falke"))
        {
            setImage("images/falke.png");

            maxbackspeed=-1;
            speed=1;
            maxspeed=6;
            turn=2;

            health=15;

            damage=2;
            maxenergy=20;
            recharge=5;

            specialLasers=true;
            color="Ro4";
            sound=new GreenfootSound("sounds/XWing-f.wav");
        }

        maxhealth=health;
    }

    /**
     * Act - do whatever the Ship wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(health<=maxhealth/2)
        {
            hb.setDrawCol(Color.RED);
        }
        hb.setValue(health);
        try
        {
            if(health<=0)
            {
                explosion();
            }
            move(speed);
            if(Greenfoot.isKeyDown(KeyFaster)) {
                if(speed<maxspeed)
                {
                    speed+=0.2;
                }
            }
            if(Greenfoot.isKeyDown(KeySlower)) {
                if(speed>maxbackspeed)
                {
                    speed-=0.1;
                }
            }
            if(Greenfoot.isKeyDown(KeyLeft)) {
                setRot(getRot()-turn);
            }
            if(Greenfoot.isKeyDown(KeyRight)) {
                setRot(getRot()+turn);
            }

            if((fire||Greenfoot.isKeyDown(KeyShoot))&&energy-fireenergy>=0) {
                shoot();
                if(type.equals("ds"))
                {
                    if(shotsFired<=100)
                    {
                        energy=maxenergy;
                        fire=true;
                    }
                    else
                    {
                        shotsFired=0;
                        fire=false;
                    }
                }
            }

            if(energy<=maxenergy)
            {
                energy+=recharge;
            }
            if(isAtEdge())
            {
                randomRotation();
                randomLocation();
            }
        }
        catch(NullPointerException ne)
        {}
        catch(IllegalStateException ie)
        {}
    }

    private void explosion()
    {
        hb.setValue(0);
        setImage(explosionpng);
        explosion.play();
        if(getOneIntersectingObject(SWShip.class)!=this&&getOneIntersectingObject(SWShip.class)!=null)
        {
            SWShip hitted=(SWShip)getOneIntersectingObject(SWShip.class);
            hitted.hit(2);
        }
        Greenfoot.delay(20);
        getWorld().removeObject(this);
    }

    public void shoot()
    {
        shotsFired++;
        if(!specialLasers)
        {
            getWorld().addObject(new SWLaser(getRotation(),this,color,damage),getX(),getY());
        }
        else
        {
            SWLaser swl=new SWLaser(getRotation(),this,color,damage);
            if(type.equals("xwing"))
            {
                getWorld().addObject(new LaserHilfe(swl,(shotsFired%2==0),getRotation(),5),getX(),getY());
            }
            if(type.equals("falke"))
            {
                getWorld().addObject(new LaserHilfe(swl,false,getRotation(),2),getX(),getY());
            }
        }
        sound.play();
        energy-=fireenergy;
    }

    public void hit(double damage)
    {
        health-=damage;
    }

    public void randomLocation()
    {
        setLocation(Greenfoot.getRandomNumber(getWorld().getWidth()),Greenfoot.getRandomNumber(getWorld().getHeight()));
    }

    public void randomRotation()
    {
        setRotation(Greenfoot.getRandomNumber(360));
    }

    public void addedToWorld(World w)
    {
        hb=new HealthBoard(type+": ",health);
        switch(num)
        {
            case 0:
            getWorld().addObject(hb,60,20);
            break;
            case 1:
            getWorld().addObject(hb,getWorld().getWidth()-50,20);
            break;
            case 2:
            getWorld().addObject(hb,60,getWorld().getHeight()-20);
            break;
            case 3:
            getWorld().addObject(hb,getWorld().getWidth()-50,getWorld().getHeight()-20);
            break;
        }

        randomLocation();
        randomRotation();
    }
}

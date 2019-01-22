import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Laser here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SWLaser extends SmoothMover
{
    private SWShip owner;
    private double damage=1;
    /** "Ro" rot
     * "Bl" blau
     * "Gr" gruen
     */
    public SWLaser(int rot,SWShip owner,String farbe,double damage,int move)
    { 
        this.owner=owner;
        this.damage=damage;
        setRotation(rot);
        setImage("images/laser"+farbe+".png");
        move(move);
    }
    public SWLaser(int rot,SWShip owner,String farbe,double damage)
    { 
        this.owner=owner;
        this.damage=damage;
        setRotation(rot);
        setImage("images/laser"+farbe+".png");
    }

    /**
     * Act - do whatever the Laser wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        try
        {
        if(reachedTarget())
        {
            SWShip hitted=(SWShip)getOneIntersectingObject(SWShip.class);
            hitted.hit(damage);
            getWorld().removeObject(this);
        }
        if(isAtEdge())
        {
            getWorld().removeObject(this);
            return;
        }
        move(10);
    }
        catch(NullPointerException ne)
        {}
        catch(IllegalStateException ie)
        {}
        catch(IllegalMonitorStateException ime)
        {}
    }

    public boolean reachedTarget()
    {
        return getOneIntersectingObject(SWShip.class)!=this.owner&&getOneIntersectingObject(SWShip.class)!=null;
    }
}

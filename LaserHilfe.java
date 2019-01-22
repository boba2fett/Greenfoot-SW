import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LaserHilfe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LaserHilfe extends SmoothMover
{
    private SWLaser swl;
    private boolean right;
    private int move;
    
    /**
     * Act - do whatever the LaserHilfe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public LaserHilfe(SWLaser pswl,boolean pright,int rot,int pmove)
    {
        swl=pswl;
        right=pright;
        move=pmove;
        setRotation(rot);
    }

    public void act() 
    {
        // Add your action code here.
        if(right)
        {   //rechts
            setRotation(getRotation()+90);
            move(move);
            setRotation(getRotation()-90);
        }
        else
        {   //links
            setRotation(getRotation()-90);
            move(move);
            setRotation(getRotation()+90);
        }
        getWorld().addObject(swl,getX(),getY());
        getWorld().removeObject(this);
    }    
}

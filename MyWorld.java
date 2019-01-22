


import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(875,700,1);
        SWShip sh1=new SWShip("isd",0);
        SWShip sh2=new SWShip("mcs",1);
        SWShip sh3=new SWShip("ds",2);
        addObject(sh1,0,0);
        addObject(sh2,0,0);
        addObject(sh3,0,0);
        setPaintOrder(HealthBoard.class,SmoothMover.class,World.class);
    }
}

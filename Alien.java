import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Alien here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Alien extends Scrollable
{
private int i = 0;

Rocket rocket;
    /**
     * Act - do whatever the Alien wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        rocket = getScrWorld().getRocket();
        moveRandom();
        kill();
    } 
    
    private void moveRandom()
    {
        if(i == 20)
        {
        
        double dx = rocket.getX() - getX();
        double dy = rocket.getY() - getY();
        
        double rotation = Math.atan2(dy, dx);
        rotation = Math.toDegrees(rotation);
        rotation = rotation + Greenfoot.getRandomNumber(50)-25;
        setRotation((int)rotation);
        i = 0;
    }
        
        else { i++;}
        move(1);
        
        
        
        
        /*Rocket rocket = getScrWorld().getRocket();
        move(2);
        turnTowards(rocket.getX(), getY());*/
    }
    private void kill()
    {
       if(newCollisonWith(Rocket.class)) 
       { rocket.receiveDamage(100);
        }
    }
}

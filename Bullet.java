import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;

/**
 * Write a description of class Bullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bullet extends Scrollable
{
    private int lifetime=0;
    private boolean playsoundbegin=false, playsoundhasended=true;
    GreenfootImage img=null;
    
    /**
     * Konstruktor von Bullet.
     * Stellt die Drehung so ein, dass die Kugel sich auf die Zielposition zubewegt.
     * 
     * @param x: X-Wert der Ausgangsposition
     * @param y: Y-Wert der Ausgangsposition
     * @param x2: X-Wert der Zielposition
     * @param y2: Y-Wert der Zielposition
     * 
     */
    public Bullet(int x, int y,int x2,int y2)
    {
        double dx = x2 - x;
        double dy = y2 - y;
        
        double rotation = Math.atan2(dy, dx);
        rotation = Math.toDegrees(rotation);
        setRotation((int)rotation);
        img = getImage();
        setvisibleImg(img);
        //System.out.println("Drehung:"+this.getRotation());
    }
    
    /**
     * Diese Methode wird immer angerufen, wenn die Taste 'Act' oder 'Run' gedrückt ist.
     * Die Kugel bewegt sich solange geradeaus, bis sie von der Welt gelöscht wird. ( nach lifetime frames)
     */
    public void act() 
    {
        // Add your action code here.
        move(7);
        lifetime+=1;
        
        /*if(getCollidingObject(Asteroid.class) != null)
        {
            System.out.println("vor loschen");
            getWorld().removeObject(getCollidingObject(Asteroid.class));
            getWorld().removeObject(this);
            playsoundbegin = true;
        }
        else
        {
            playsoundbegin = false;
        }*/
        if(lifetime >= 100)
        {
            space.removeObject(this);
            // getSpace().removeObject(this);
        }
        
    }    
}

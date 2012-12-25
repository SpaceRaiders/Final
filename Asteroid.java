import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Klasse Asteroid. Objekte dieser Klasse schweben ziellos durchs Weltall und können Rakete
 * beschädigen.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 04-11-2012
 */
public class Asteroid extends Scrollable
{
    /**
     * Definiert, ob die Rackette und die Asteroide gerade in Kollision sind.
     */
    //private boolean playSound=false;
    private GreenfootImage img = null;
    private int real_x,real_y;
    
    /**
     * Konstruktor. Erzeugt ein Objekt, das sich in ein eigene Richtung bewegt.
     */
    public Asteroid()
    {
        img = getImage();
        setvisibleImg(img);
        turn(Greenfoot.getRandomNumber(360));
    }
    /**
     * Diese Methode wird immer angerufen, wenn die Taste 'Act' oder 'Run' gedrückt ist.
     * Hier wird auf Mausklicks reagiert und eine Object der Bulet Klasse der Welt hinzugefügt.
     */
    public void act() 
    {
        // Add your action code here.
        super.act();
        move();
        checkKollision();
    }
    
    /**
     * Überprüft, ob Asteroiden mit irgendeine Rackette, Kugel oder andere Asteroide
     * kollidiert und handelt entsprechend.
     */
    private void checkKollision()
    {
        // Fall Kollision mit Rackette : Rackette bekommt Schaden.
        if(getCollidingObject(Rocket.class) != null)
        {
            Rocket rocket = (Rocket) getCollidingObject(Rocket.class);
            if(isRealCollision(rocket) == true)
            {
                rocket.receiveDamage();
            }
        }
        else
        {
            // Fall Kollision mit Kugel : Asteroid wird gelöcht.
            if(getCollidingObject(Bullet.class) != null)
            {
                //getScrWorld().removeObject((Scrollable)getCollidingObject(Bullet.class));
                //getScrWorld().removeObject((Scrollable)this);
                //getWorld().removeObj((Scrollable)getOneIntersectingObject(Bullet.class));
                //getWorld().removeObj((Scrollable)this);
            }
            else
            {
                // Fall Kollision mit andere Asteroide : diese Asteroid nimm eine andere Richtung.
                if(getCollidingObject(Asteroid.class) != null)
                {
                   turn(13);
                }
            }
        }
    }
    /**
     * Bewegt den Asteroiden und dreht ihn, wenn er am Rand des Spielfeldes ist.
     */
    private void move()
    {
        move(2);
        if (checkWorld())
        {
            turn(10);
        }
     }

    /**
     * Diese Methode erlaubt, die Richtung des Asteroides zu ändern wenn er sich
     * auf eine Ecke befindet (Siehe Methode move()).
     */
    private boolean checkWorld()
    {
        if(getRealX() < 5 || getRealX() > getWorld().getWidth() - 5)
        {
            return true;
        }
        if(getRealY() < 5 || getRealY() > getWorld().getHeight() - 5)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;

/**
 * Oberklasse aller SCrollbaren Objecte.
 * Wird zum einem zur identifizierung, ob ein Object Scrollbar ist genutzt. Dann gibt es noch ein paar andere Methoden,
 * z.B. setVisible inzwischen im Prinzip überholt, diese MEthode erstzt das Bild durch eine 1px großen transparentes Bild.
 * Ich bin mir momentan nicht sicher ob real_x überhaupt genutzt wird. 
 * Die move ist für die Raktete wichtig, amsonsten kann die Raktete die Welt velassen ( einfach im Konstuktor der Rakete super(true) anstatt super(false) um ausprobieren. 
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 18-11-2012
 */
public class Scrollable extends Actor
{
    /**
     * 
     */
    GreenfootImage img = null;

    /**
     * 
     */
    GreenfootImage img_trans=null;

    /**
     * 
     */
    private int atWorldEdge_grenze = 20;

    /**
     * 
     */
    private int real_x, real_y;

    /**
     * 
     */
    protected ScrollableWorld space = null;

    /**
     * 
     */
    private boolean canleave = true;
    
    /**    Speichert ob zurzeit eine Kollision mit einem Actor einer best Klasse ist */
    private HashMap<Class,Actor> collisions ;
    /**
     * Konstruktor
     */
    public Scrollable()
    {
        collisions=  new HashMap<Class,Actor>();
    }

    /**
     * Konstruktor
     */
    public Scrollable(boolean canleaveWorld)
    {
        canleave = canleaveWorld;
        collisions=  new HashMap<Class,Actor>();
    }

    /**
     * Diese Methode wird immer angerufen, wenn die Taste 'Act' oder 'Run' gedrückt ist.
     * Hier wird auf Mausklicks reagiert und eine Object der Bulet Klasse der Welt hinzugefügt.
     */
    public void act() 
    {
        setLocation(real_x+space.getShiftX(),real_y+space.getShiftY());
        checkvsbl();
    }

    /**
     * Bild übergeben, dass genutzt wird, wenn Objekt auf Bildschirm sichtbar.
     */
    public void setvisibleImg(GreenfootImage img_tmp)
    {
        img=img_tmp;
        img_trans=new GreenfootImage("void.png");
    }

    /**
     * Sichtbarkeit auf Bildschirm einstellen
     */
    public void setVisible(boolean vsbl)
    {
        if(vsbl)
        {
            setImage(img);
        }
        else
        {
            setImage(img_trans);
        }
    }

    /**
     * wenn Objekt am Rand des Feldes, wird es unsichtbar, siehe setVisible()
     */
    public void checkvsbl()
    {
        setVisible(!atWorldEdge());
    }

    /**
     * @return true, wenn weniger als atWorldEdge_grenze von Spielfeldrand entfernt
     */
    public boolean atWorldEdge()
    {
        if(getX() < atWorldEdge_grenze || getX() > getWorld().getWidth() - atWorldEdge_grenze 
        || getY() < atWorldEdge_grenze || getY() > getWorld().getHeight() - atWorldEdge_grenze)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * einmal kurz Startwerte für real_x und real_y übergeben , da sie nicht von Beginn an auf z.B. getX() zugreifen können
     */
    public void init(int x_start, int y_start, ScrollableWorld space_tmp)
    {
        real_x = x_start;
        real_y = y_start;
        space = space_tmp;
    }

    /**
     * @return Die Welt, wo das Objekt sich befindet.
     */
    public ScrollableWorld getScrWorld()
    {
        return space;
    }

    /**
     * Diese Funktion definiert, wo ein das Objekt sich in X befinden muss.
     * 
     * @param die Ware Y Position
     */
    public void setRealX(int realX)
    {
        real_x=realX;
    }

    /**
     * Diese Funktion definiert, wo ein das Objekt sich in Y befinden muss.
     * 
     * @param die Ware Y Position
     */
    public void setRealY(int realY)
    {
        real_y=realY;
    }

    /**
     * @return die Ware X Position
     */
    public int getRealX()
    {
        return real_x;
    }

    /**
     * @return die Ware Y Position
     */
    public int getRealY()
    {
        return real_y;
    }

    /**
     * Move von Actor überschrieben bzw erweitert, damit man die echte position ( nicht nur 
     * die auf dem Bildschirm) bekommt
     */
    public void move(int distance)
    {
        int movex=0, movey=0,dx, dy;
        dx= getX();
        dy= getY();
        super.move(distance);
        dx= getX()-dx;
        dy= getY()-dy;
        real_x+=dx;
        real_y+=dy;

        if(canleave)
        {
            //real_x+=dx;
            //real_y+=dy;
            //System.out.println("canleave");
        }
        else
        {

            while(real_x+space.getShiftX()+movex<0)
            {
                movex++;
                //System.out.println("movex vergröernt");
            }
            while(real_x+space.getShiftX()+movex>getWorld().getWidth())
            {
                movex--;
                //System.out.println("movex verkleinert");
            }
            while(real_y+space.getShiftY()+movey<0)
            {
                movey++;
                //System.out.println("movey vergröernt");
            }
            while(real_y+space.getShiftY()+movey>getWorld().getHeight())
            {
                movey--;
                //System.out.println("movey verkleinert");
            }
            real_x=real_x+movex;
            real_y=real_y+movey;

        }
        setLocation(real_x+space.getShiftX()+movex,real_y+space.getShiftY()+movey);

        //System.out.println(toString()+"   x:" +getX()+":"+(real_x+space.getShiftX())+":"+real_x+"   y:"+getY()+":"+real_y);
    }

    // ---------------------- Collision ----------------------------------------------------------
    
    /**
     * @return Collidierendes Object bzw null 
     */
    public Actor getCollidingObject(Class objClass)
    {
        List<Actor> actors = getIntersectingObjects(objClass);

        for(Actor actor : actors)
        {
            Scrollable scrble = (Scrollable) actor ;
            if(isRealCollision(scrble))
            {
                return actor;
            }
        }
        return null;
    }
    
    /**
     * @return Gibt true zurück, wenn gerade eine Kollision mit einem Objekt der Klasse besteht, ansonsten false
     */
    public boolean isColliding(Class objClass)
    {
        return getCollidingObject(objClass)!=null;
    }
    
    /**
     * Diese Methode überprüft die kollsion und gibt nur einmal true zurück auch wenn die kollision weiterhin besteht
     */
    public boolean newCollisonWith(Class objClass)
    {
        Actor a = getCollidingObject(objClass);
        if(a != null)
        {
            Actor a2 = collisions.get(objClass);
            collisions.put(objClass, a);
            if(a.equals(a2))
            {
                return false;
            }
            else
            {
                return true;
            }
        }
        else
        {
            collisions.put(objClass, null);
            return false;
        }
    }

    /**
     * Diese Methode überprüft, ob der Objekt objClass mit irgend ein Objekt kollidiert.
     * Diese Kollidierung ist visuel, dh falls beide Bildern unsichtbare Pixels haben und sich
     * berühren wird diese Methode trotzdem falls zurückgeben.
     * 
     * @return true wenn objClass mit irgend ein Objekt Kollidiert
     * @return false sonst.
     */
    /*public boolean isColliding4(Class objClass)
    {
        List<Actor> actors = getIntersectingObjects(objClass);
        for(Actor object : actors)
        {
            // hier gäbe es errors wenn objClass keine unterklasse von SCrollable oder Scrollable ist
            Scrollable tmp = (Scrollable) object;
            if(isRealCollision(tmp))
            {
                return true;
            }
        }
        return false;
    }*/
    
    /**
     * Diese Methode überprüft, ob die Kollision visuel ist; dh dass die Kollision zwichen unsichtbare Pixel nicht als
     * Kollision erkannt wird. Die Kollision wird als "real" erkannt wenn sie Visuel ist.
     * 
     * @return true wenn die Kollision Visuel ist
     * @return false wenn die Kollision nicht Visuel ist.
     */
    public boolean isRealCollision(Scrollable scrble)
    {
        boolean colliding = false;
        for(int i = 0; i < getImage().getWidth(); i++)
        {
            for(int j = 0; j < getImage().getHeight(); j++)
            {
                if(getImage().getColorAt(i, j).getAlpha() != 0)
                {
                    double x1_welt = getRealX() + (i - getImage().getWidth() / 2)*Math.cos(Math.toRadians(getRotation())) - (j - getImage().getHeight()/2)*Math.sin(Math.toRadians(getRotation()));

                    double y1_welt = getRealY() + (i - getImage().getWidth() / 2)*Math.sin(Math.toRadians(getRotation())) + (j - getImage().getHeight()/2)*Math.cos(Math.toRadians(getRotation()));

                    int x2_px = (int) (scrble.getImage().getWidth() /2  + (x1_welt - scrble.getRealX())* Math.cos(Math.toRadians(scrble.getRotation())) + (y1_welt - scrble.getRealY())*Math.sin(Math.toRadians(scrble.getRotation())));
                    int y2_px = (int) (scrble.getImage().getHeight() /2 - (x1_welt - scrble.getRealX())* Math.sin(Math.toRadians(scrble.getRotation())) + (y1_welt - scrble.getRealY())*Math.cos(Math.toRadians(scrble.getRotation())));
                    if(0 <= x2_px && 0 <= y2_px && x2_px < scrble.getImage().getWidth() && y2_px < scrble.getImage().getHeight()){

                        if(scrble.getImage().getColorAt(x2_px, y2_px).getAlpha() != 0)
                        {
                            if(getScrWorld().isTestmodus())
                            {
                                getImage().setColorAt(i, j, Color.RED);
                                colliding = true;
                            }
                            else
                            {
                                return true; // für Geschwindigkeit, im Normalfall kann man hier abbrechen
                            }
                        }
                    }
                }
            }
        }
        return colliding;
    }
    
    /* Hier die Methoden für Aufgabe 2*/
    
    /**
     * Diese Methode überprüft, ob der Objekt ObjClass
     * eine Kollision mit irgend ein anderen Objekt von
     * die Welt hat.
     *//*
    public Actor  getCollidingObject(Class ObjClass)
    {
        return getOneIntersectingObject(ObjClass);
    }
    */
   
    /**
     * Testet auf Kollision mit einer best KLasse
     *//*
    public boolean isColliding(Class objClass)
    {
    return getOneIntersectingObject(objClass) != null ;
    }*/

    /**
     * Testet auf neue Kollision( mit einer best. Klasse)
     *//*
    public boolean neueKollsionmit(Class objClass)
    Actor a = getCollidingObject(objClass);
    if(a!=null)
    {
        Actor a2 = collisions.get(objClass);
        collisions.put(objClass,a);
        if(a.equals(a2))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    else
    {
        return false;
    }
    */
}

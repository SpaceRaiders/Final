import greenfoot.*;

/**
 * Für Aufgabe 2
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 08-11-2012
 */
public class Pose  
{
    private int x,y,rot;
    private int shift_x,shift_y;
    private Scrollable scrble;

    /**
     * Konstruktor. Hier wird der Klasse Pose eine ?Referenz? auf ein Scrollable Object gegeben.
     * ( Scrollable ist eine Unterklasse von Actor).
     */
    public Pose(Scrollable scrble)
    {
        this.scrble=scrble;
        
    }
    
    /**
     * Konstruktor 2
     */
    public Pose(int x, int y, int rot)
    {
        this.x = x;
        this.y = y;
        this.rot = rot;
        
    }
    
    /**
     * Aktualisiert die Werte von Pose d.h. die x und y Koordinate und die Drehung.
     * Wird zu Beginn der act() Methode aufgerufen.
     */
    public void update()
    {
        x = scrble.getX();
        y = scrble.getY();
        rot = scrble.getRotation();
    }
    
    /**
     * Verschiebt das Scrollable-Object (die Rakete) so, dass der vorherige Zug rückgängig gemacht wird.
     */
    public void resetActor()
    {
        
        
        int dx,dy;
        dx=scrble.getX()-x;
        dy=scrble.getY()-y;
        scrble.setRealX(scrble.getRealX()-dx);
        scrble.setRealY(scrble.getRealY()-dy);
        scrble.setLocation(x,y);
        //System.out.println("Scroll: "+dx+":dx    "+dy+":dy");
        
        scrble.setRotation(rot);
    }
    
    /**
     * @return der x Wert von der Klasse
     */
    public int getX()
    {
        return x;
    }
    
    /**
     * @return der y Wert von der Klasse
     */
    public int getY()
    {
        return y;
    }
    
    /**
     * @return die gespeicherte Rotation von der Klasse
     */
    public int getRot()
    {
        return rot;
    }
    
    /**
     * @return ein String mit alle Werte angezeigt.
     */
    public String toString()
    {
        return "x/y" + x + ":" + y + "    rot:" + rot;
    }
}

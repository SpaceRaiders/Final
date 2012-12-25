import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList ;
/**
 * Oberklasse von SCrollbaren Welten. Implementiert einige Methoden um die Scrollbarkeit zu realisieren. 
 * Da wären einige Methoden um die Liste der Scrollbaren Objekte zu verwalten ( inijObj(), removeObject() )
 * und das eigentliche Scrollen. Hierfür wir din der act() bestimmt, ob und wenn ja wie weit die Rakete über die grenze ( vom Rand aus gesehen ( grenzeX,grenzeY)) geflogen ist und verschiebt dann alle Scrollbaren Objecte auf der WElt um dx,dy.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 09-11-2012
 */
public class ScrollableWorld extends World
{
//private ArrayList<greenfoot.Actor> objects = new ArrayList<greenfoot.Actor>();
    private ArrayList<Scrollable> objects = new ArrayList<Scrollable>();
    //private ArrayList<Point> realPos = new ArrayList<Point>();
    private int grenzeX,grenzeY,width=2400,height=1600,shiftX=0,shiftY=0,widthHUD=150;
    private HUD hud;
    private boolean testmodus=false;
    /**
     * Constructor for objects of class Space.
     * 
     */
    public ScrollableWorld(int screenWidth, int screenHeight, int cellsize, int width, int height)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(screenWidth, screenHeight, cellsize,false); 
        this.width=width;
        this.height=height;
        grenzeX = screenWidth/2;
        grenzeY= screenHeight/2;
           
        //createHUD();
        widthHUD=0;
    }
    private void createHUD()
    {
        hud=new HUD();
        addObject(hud,getWidth()-75,getHeight()/2);
        
        
    }
    /**
     * @param rocket Objekt von Rocket, das hinzugefügt wird.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     */
    public void initRocket(Scrollable rocket, int x,int y)
    {
        addObject(rocket, x, y);
        objects.add(rocket);
        rocket.init(x-shiftX,y-shiftY,this);
        act();
        //realPos.add(new Point(x,y));
    }
    /**
     * @param scrble Objekt einer Subklasse von Scrollable, das hinzugefügt wird.
     * @param x X-Koordinate
     * @param y Y-Koordinate
     */
    public void initObj(Scrollable scrble, int x,int y)
    {
        addObject(scrble, x, y);
        objects.add(scrble);
        scrble.init(x-shiftX,y-shiftY,this);
        //realPos.add(new Point(x,y));
    }
    /**
     * Act-Methode der World, achtet auf die Position der Rakete ( getRocket()) und verschieb alle Objekte dementsprechend.
     * 
     */
    public void act()
    {
        int dx=0,dy=0;
        if(getRocket().getX() < grenzeX && getRocket().getX() <= getRocket().getRealX())
        {
            dx= grenzeX - getRocket().getX() ;
            if(shiftX>0)// das hier sollte niemals kommen :D
            {
                System.out.println(shiftX + " bei If 1");
            }
        }
        if(getRocket().getX() > getWidth() - grenzeX && getWidth() - getRocket().getX() <= width-getRocket().getRealX())
        {
           // System.out.println("rechts");
            dx = getWidth() - (grenzeX+getRocket().getX());
        }
        if(getRocket().getY() < grenzeY && getRocket().getY() <= getRocket().getRealY())
        {
            dy = grenzeY - getRocket().getY()  ;
        }
        if(getRocket().getY() > getHeight() - grenzeY && getHeight() - getRocket().getY() <= height-getRocket().getRealY())
        {
            dy = getHeight() - (grenzeY+getRocket().getY());
        }
        //System.out.println("dx: "+dx+"    dy: "+dy);
        
        scroll(dx,dy);
        
    }
    public void scroll(int dx, int dy)
    {
        //System.out.println("Scroll: "+dx+":dx    "+dy+":dy");
        shiftX += dx;
        shiftY += dy;
        for(int i =0; i< objects.size();i++)
        {
            //realPos.get(i).setX();
            
            Actor tmp=objects.get(i);
            //System.out.println("Actor:" + tmp);
            tmp.setLocation(tmp.getX()+dx,tmp.getY()+dy);
            //tmp.setRealLocation(tmp.getRealX()+dx,tmp.getRealY()+dy);
        }
    }
    /**
     * @return die Rakete
     */
    public Rocket getRocket()
    {
        //System.out.println(objects.size());
        for (Scrollable scrble : objects) {
            if (scrble.getClass() == Rocket.class) {
                return (Rocket)scrble;
            }
            else{
                //System.out.println(scrble+" ist keine Rakete");
            }
        }
        System.out.println("Oo   keine Rakete da?");
        return null;
    }
    
    /**
     * Beim entfernen zu nutzen, amsonsten gibs Exception, weil beim Scrollen auf den Actor zugegriffen wird obwohl er nicht mehr in der Welt ist.
     * 
     */
    public void removeObject(Scrollable scrble)
    {
        super.removeObject(scrble);
        objects.remove(scrble);
    }
    /**
     * 
     */
    public int getRealWidth()
    {
        return width;
    }
    public int getRealHeight()
    {
        return height;
    }
    public void setShift(int shift_x,int shift_y)
    {
        shiftX=shift_x;
        shiftY=shift_y;
    }
    public int getShiftX()
    {
        return shiftX;
    }
    public int getShiftY()
    {
        return shiftY;
    }
    public void setTestmodus(boolean testen)
    {
        testmodus=testen;
    }
    public boolean isTestmodus()
    {
        return testmodus;
    }
}


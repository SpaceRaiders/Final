import java.util.ArrayList;

/**
 * Die Klasse implementiert einen Ringpuffer, d.h. einen Puffer, der eine
 * bestimmte Menge von Werten zwischenspeichern kann. Man kann mit
 * {@link push(int)} Werte hinzufügen und mit {@link pop()} wieder entnehmen.
 * Dabei liefert {@link pop()} die Werte in derselben Reihenfolge zurück, in
 * der sie mit {@link push(int)} eingefügt wurden, d.h. {@link pop()} liefert
 * immer das Element zurück, das schon am längsten im Puffer gespeichert ist.
 * Wird ein neues Element in den Puffer eingefügt, wenn seine Kapazität bereits
 * erreicht ist, geht das älteste Element verloren.
 * 
 * @author Vitalij Kochno - Yorick Netzer - Christophe Stilmant
 * @version 27-11-2012
 */
public class RingBuffer
{
    /**
     * Representiert der Buffer. In diese Tabelle werden alle Werte gespeichert.
     */
    private int[] ring;
    /**
     * Representiert das Element der Tabelle, mitdem wir gerade arbeiten. Diese Attribut
     * erlaubt uns, der älteste Elemente der Tabelle zu finden.
     */
    private int i;
    
    /**
     * Definiert die Größe der Tabelle, und dann auch die maximale Anzahl von Einträgen,
     * die gepuffert werden können.
     */
    private int capacity;
    
    /**
     * Definiert wieviele Elemente es schon in der Tabelle ring[] gibt.
     */
    private int lenght;
    
    /**
     * Erzeugt einen Ringpuffer.
     * @param capacity Die maximale Anzahl von Einträgen, die gepuffert werden können.
     */
    public RingBuffer(int capacity)
    {
        ring = new int[capacity];
        this.capacity = capacity;
        i = 0;
        lenght = 0;
    }
    
    /**
     * Fügt ein neues Element in den Ringpuffer ein.
     * @param value Der Wert, der eingefügt werden soll.
     */
    public void push(int value)
    {
        ring[i] = value;
        i++;
        if(lenght!=capacity)
        {
            lenght++;
        }    
        checki();
    }
    
    /**
     * Entnimmt das älteste Element aus dem Ringpuffer.
     * @return Das Element, das entnommen wurde.
     */
    public int pop()
    {
        /* Da das Element von der Tabelle gelöcht sein wird, muss man das 
         * Element in ein Temporale Variable tmp hinzufügen, um den Wert
         * zurücksenden. */
        int tmp;
        
        if(size() <= capacity)
        {
            tmp = ring[0];
            for(int j = 0; j < lenght - 1; j++)
            {
                ring[j] = ring[j+1];
            }
        }
        else
        {
            tmp = ring[i];
            for(int j = i; j < lenght - 1; j++)
            {
                ring[j] = ring[j+1]; 
            }
            ring[lenght-1] = ring[0];
            for(int k = 0; k < i; k++)
            {
                ring[k] = ring[k+1];
            }
        }
        i--;
        checki();
        lenght--;
        return tmp;
    }
    
    /**
     * Liefert das älteste Element aus dem Ringpuffer zurück, ohne es zu entnehmen.
     * @return Das älteste Element im Ringpuffer.
     */
    public int peek()
    {
        if(size() <= capacity)
        {
            return ring[0];
        }
        else
        {
            return ring[i];
        }
    }
    
    /**
     * Liefert die Anzahl der Elemente zurück, die sich im Puffer befinden, d.h. die
     * mit {@link pop()} entnommen werden könnten.
     * @return Die Anzahl der belegten Einträge im Puffer.
     */
    public int size()
    {
       return lenght;
    }
    
    /**
     * Überprüft, ob der Attribut i sich in ein gültige Intervall befindet.
     */
    private void checki()
    {
        if(i >= capacity)
        {
            i = 0;
        }
        if(i < 0)
        {
            i = capacity - 1;
        }
    }
    
    /**
     * Liefert alle Elemente von der Buffer zurück.
     * @return Eine String-Liste aller Elemente von der Buffer.
     */
    public String toString()
    {
        String answer = "{ ";
        for(int j = 0; j < size(); j++)
        {
            answer =  answer + ring[j]+ ", ";
        }
        answer = answer.substring(0, answer.length() - 2) + " }";
        return answer;
    }
    
    /**
     * Sagt, ob der Buffer leer ist oder nicht.
     * @return true falls der Buffer leer ist, false sonst.
     */
    public boolean isEmpty()
    {
        return lenght==0;
    }
}
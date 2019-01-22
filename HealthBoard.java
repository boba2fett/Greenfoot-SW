import greenfoot.*;  // (World, Actor, GreenfootImage, and Greenfoot)

/**
 * HealthBoard that displays an (optional) text and a number.
 * 
 * @author Michael Kolling  Bearbeitet F.T.
 * @version 1.1
 */
public class HealthBoard extends Actor
{
    public static final float FONT_SIZE = 18.0f;
    
    private double value;
    private String text;
    private int stringLength;
    private Font font;
    private Color col=Color.GREEN;
    /**
     * Create a HealthBoard without text. 
     */
    public HealthBoard()
    {
        this("");
    }

    /**
     * Create a HealthBoard with prefix text, and 0 value.
     */
    public HealthBoard(String prefix)
    {
        this(prefix, 0);
    }
    
    /**
     * Create a HealthBoard with prefix text and value.
     */
    public HealthBoard(String prefix, double value)
    {
        this.value = value;
        text = prefix;
        stringLength = (text.length() + 4) * 10;

        GreenfootImage image = new GreenfootImage(stringLength, 22);
        setImage(image);
        font = image.getFont();
        font = font.deriveFont(FONT_SIZE);
        updateImage();
    }

    /**
     * Increment the HealthBoard by one.
     */
    public void setValue(double val)
    {
        value=val;
        updateImage();
    }

    /**
     * Return the current HealthBoard value.
     */
    public double getValue()
    {
        return value;
    }
    
    public void setDrawCol(Color color)
    {
        col=color;
    }
    
    /**
     * Make the image
     */
    private void updateImage()
    {
        GreenfootImage image = getImage();
        image.clear();
        image.setFont(font);
        image.setColor(col);
        image.drawString(text + value, 6, (int)FONT_SIZE);
    }
}

import greenfoot.*;

public class RatWorld extends World
{
    public RatWorld()
    {
        super(8, 8, 60);
        Greenfoot.setSpeed(25);
        setPaintOrder(Rat.class, Cat.class, Cheese.class, Mousetrap.class);
        setActOrder(Rat.class, Cat.class);
    }
}

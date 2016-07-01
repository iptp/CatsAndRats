import greenfoot.*;
import java.util.List;

public class Cat extends Animal
{
    public void act() 
    { 
        // Seek rats
        pathFinding(Rat.class, false);
        walk();
        
        // Eat rats
        List<Rat> rats = getIntersectingObjects(Rat.class);
        getWorld().removeObjects(rats);
    }
}

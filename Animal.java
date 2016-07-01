import greenfoot.*;
import java.util.*;

public class Animal extends Actor
{
    private boolean facingRight = true;
    private int faceX = 0;
    private int faceY = 0;
    
    public int[] getFace()
    {
        return new int[]{faceX, faceY};
    }

    public void setFace(int x, int y)
    {
        faceX = (int) Math.signum(x);
        faceY = (int) Math.signum(y);
        
        if(faceX != 0)
        {
            if(faceY != 0)
                faceY = 0;
                 
            if((faceX == 1) != facingRight)
            {
                facingRight = !facingRight;
                getImage().mirrorHorizontally();
            }
        }
    }
    
    public void walk()
    { 
        setLocation(getX() + faceX, getY() + faceY);
    }
    
    public List<Actor> findNearest(int x, int y, Class cls)
    {
        int nearestDist = -1;
        List<Actor> nearest = new ArrayList<Actor>();
        List<Actor> actors = getWorld().getObjects(cls);
        
        for(Actor a : actors)
        {
            int dist = Math.abs(x - a.getX()) + Math.abs(y - a.getY());
            
            if(nearestDist == -1 || dist <= nearestDist)
            {
                if(dist < nearestDist)
                    nearest.clear();
                
                nearestDist = dist;
                nearest.add(a);
            }
        }
        
        return nearest;
    }
    
    public boolean isWalkable(int x, int y)
    {
        if(x == getX() && y == getY())
            return true;
            
        boolean xValid = (0 <= x && x < getWorld().getWidth());
        boolean yValid = (0 <= y && y < getWorld().getHeight());
        
        boolean notOccupied = (getWorld().getObjectsAt(x, y, getClass()).size() == 0);
        
        return xValid && yValid && notOccupied;
    }
    
    public void pathFinding(Class cls, boolean goAway)
    {
        int[] face = getFace();
        
        if(getWorld().getObjects(cls).size() == 0)
        {
            setFace(0, 0);
            return;
        }
        
        int[][] offsets = { {face[0], face[1]}, {1, 0}, {0, 1}, {-1, 0}, {0, -1}, {0, 0} };
        
        int bestDist = -1;
        int[] best = null;

        Actor nearest;
        int posX, posY;
        int distX, distY;
        int dist;
        
        for(int i = 0; i < offsets.length; i++)
        {
            posX = getX() + offsets[i][0];
            posY = getY() + offsets[i][1];

            nearest = findNearest(posX, posY, cls).get(0);
            
            distX = Math.abs(nearest.getX() - posX);
            distY = Math.abs(nearest.getY() - posY);
            dist = distX * distX + distY * distY;
            
            if(goAway)
                dist *= -1;
            //getWorld().showText(""+dist, posX, posY);
            if((bestDist == -1 || dist < bestDist) && isWalkable(posX, posY))
            {
                bestDist = dist;
                best = offsets[i];
            }
        }
        
        setFace(best[0], best[1]);
    }
}

package Components;

import Actors.Enemy;
import Actors.PowerUp;
import Actors.Wall;
import Util.AABB;
import Util.Position2D;

import java.util.ArrayList;

public class CollisionComponent implements IRealTimeComponent
{
    ArrayList<ICollisionListener> observers;
    AABB aabb;
    ArrayList<AABB> toCompare;

    public CollisionComponent(AABB aabb,ArrayList<AABB> toCompare)
    {
        this.aabb = aabb;
        this.observers = new ArrayList<>();
        this.toCompare = toCompare;

    }
    @Override
    public void update(float deltaT)
    {
        // TODO:

        for(AABB stuff : toCompare)
        {
            if(this.aabb.collides(stuff))
            {
                // Which kind of aabb collides with our actor. We have to pass this to observers to take necessary action.
                notifyObservers(stuff);
                stuff = null;
            }
        }
    }
    public void notifyObservers(AABB box)
    {
        for(ICollisionListener observer: this.observers)
        {
            observer.aCollisionIsHappened(box);
        }
    }

    public void registerObserver(ICollisionListener collisionListener)
    {
        this.observers.add(collisionListener);
    }

    public void unregisterObserver(ICollisionListener collisionListener)
    {
        int observerIdx = this.observers.indexOf(collisionListener);
        this.observers.remove(observerIdx);
    }

}

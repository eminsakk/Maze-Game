package Components;

import Util.AABB;

public abstract class AbstractPatrolStrategy implements IRealTimeComponent
{
    protected AABB pos;
    protected int speed;

    public void changeSpeed()
    {
        this.speed = -(this.speed);
    }

    public void setPos(AABB box)
    {
        this.pos = box;
    }

    public void setSpeed(int speed)
    {
        this.speed = speed;
    }
}

package Actors;

import Components.*;
import Util.AABB;
import Util.Position2D;

import java.awt.*;
import java.util.ArrayList;

// Meta Actor Class
// Everything in the game is an actor
public abstract class AbstractActor extends AABB
{
    // TODO:
    public enum Directions {UP,DOWN,LEFT,RIGHT,NOTHING}

    // Components that we need in a concrete class.
    ArrayList<IRealTimeComponent> components;
    IDrawable asset;
    AbstractActor(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
    }

    public void update(float deltaT, Graphics2D g)
    {
        for(IRealTimeComponent component : this.components)
            component.update(deltaT);
        asset.draw(g,this);

    }

    public abstract boolean isDead();
}

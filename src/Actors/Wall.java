package Actors;

import Components.CollisionComponent;
import Components.IRealTimeComponent;
import Components.SpriteComponent;
import Util.AABB;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Wall extends AbstractActor
{
    private final String spritePath = "./../data/img/wall.png";
    /*"./../data/img/wall.png"*/

    public Wall(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        try
        {
            this.asset = new SpriteComponent(spritePath);

        }
        catch (IOException e)
        {
            System.out.println("Wall Sprite Component Error!");
        }

        this.components = new ArrayList<IRealTimeComponent>();
    }

    @Override
    public boolean isDead()
    {
        return false;
    }
}

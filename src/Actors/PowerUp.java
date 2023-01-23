package Actors;

import Components.CollisionComponent;
import Components.IRealTimeComponent;
import Components.PlayerInputComponent;
import Components.SpriteComponent;
import Util.AABB;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class PowerUp extends AbstractActor
{
    private final String spritePath = "./../data/img/scroll.png";
    private boolean isDeath;
    public PowerUp(Position2D<Float> pos, float szX, float szY)
    {
        super(pos, szX, szY);
        this.isDeath = false;
        try
        {
            this.asset = new SpriteComponent(spritePath);

        }
        catch (IOException e)
        {
            System.out.println("PowerUp Sprite Component Error!");
        }
        this.components = new ArrayList<IRealTimeComponent>();

    }

    public void changeStatus(boolean status)
    {
        this.isDeath = status;
    }

    @Override
    public boolean isDead()
    {
        return this.isDeath;
    }


}

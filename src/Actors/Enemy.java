package Actors;

import Components.*;
import Util.Position2D;
import Util.AABB;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Enemy extends AbstractActor
{
    private int health;
    private final String spritePath = "./../data/img/enemy.png";
    private int speed = 120; // Pixel per second
    AbstractPatrolStrategy strategy;
    CollisionComponent colCom;

    public Enemy(Position2D<Float> pos, float szX, float szY,ArrayList<AABB> AABBS)
    {
        super(pos, szX, szY);
        this.health = 100;
        try
        {
            this.asset = new SpriteComponent(spritePath);

        }
        catch (IOException e)
        {
            System.out.println("Enemy Sprite Component Error!");
        }

        this.components = new ArrayList<IRealTimeComponent>();
        this.colCom = new CollisionComponent(this,AABBS);

        ICollisionListener collListenerWall = new EnemyWallCollision(this);
        colCom.registerObserver(collListenerWall);
        this.components.add(colCom);

    }
    @Override
    public void update(float deltaT, Graphics2D g)
    {
        // TODO:
        for(IRealTimeComponent component : this.components)
            component.update(deltaT);
        asset.draw(g,this);
    }
    public void setStrategy(AbstractPatrolStrategy strategy)
    {
        this.strategy = strategy;
        strategy.setPos(this);
        strategy.setSpeed(this.speed);
        this.components.add(this.strategy);
    }

    public void changeSpeed()
    {
        strategy.changeSpeed();
    }
    private int getSpeed()
    {
        return this.speed;
    }

    public void decreaseHealth()
    {
        this.health -= 30;
    }

    public int getHealth()
    {
        return this.health;
    }
    @Override
    public boolean isDead()
    {
        if (this.health <= 0)
            return true;

        return false;
    }
}

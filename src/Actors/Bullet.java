package Actors;

import Components.*;
import Util.AABB;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Bullet extends AbstractActor
{
    // TODO:
    private final String spritePath = "./../data/img/bullet.png";
    private final int speed = 300; // Pixel per second.
    private final float lifeTime = 0.7f; //In seconds.
    private AbstractActor.Directions dir;
    private boolean isDeath;
    private float timeElapsed;
    ArrayList<AABB> walls;
    ArrayList<AABB> toCompare;
    CollisionComponent colCom;

    public Bullet(Position2D<Float> pos, float szX, float szY,AbstractActor.Directions dir,ArrayList<AABB> walls)
    {
        super(pos, szX, szY);
        isDeath = false;
        this.walls = walls;
        this.toCompare = new ArrayList<>();
        toCompare.addAll(this.walls);
        this.timeElapsed = 0.0f;
        try
        {
            this.asset = new SpriteComponent(spritePath);

        }
        catch (IOException e)
        {
            System.out.println("Bullet Sprite Component Error!");
        }
        this.colCom = new CollisionComponent(this,this.toCompare);
        this.components = new ArrayList<IRealTimeComponent>();
        ICollisionListener bulletWallCollision = new BulletWallCollision(this,this.walls);
        this.colCom.registerObserver(bulletWallCollision);
        this.components.add(this.colCom);

        this.dir = dir;
        this.shrink(0.5f);
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {

        for(IRealTimeComponent component : this.components)
            component.update(deltaT);


        if(dir == Directions.RIGHT)
            this.getPos().x += (deltaT * this.speed);
        if(dir == Directions.LEFT)
            this.getPos().x -= (deltaT * this.speed);
        if(dir == Directions.UP)
            this.getPos().y -= (deltaT * this.speed);
        if(dir == Directions.DOWN)
            this.getPos().y += (deltaT * this.speed);



        this.asset.draw(g,this);
        this.timeElapsed += deltaT;
    }


    public void setStatus(boolean bool)
    {
        this.isDeath = bool;
    }
    @Override
    public boolean isDead()
    {
        return this.isDeath || this.timeElapsed > this.lifeTime;
    }

}

package Actors;

import Components.*;
import Util.AABB;
import Util.Position2D;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends AbstractActor
{
    // TODO:
    private final String spritePath = "./../data/img/player.png";
    private final int speed = 110; // Pixel per second


    ArrayList<AABB> toCompare;
    private boolean isAlive;
    CollisionComponent colCom;
    ArrayList<Enemy> enemies;
    ArrayList<AABB> walls;
    ArrayList<PowerUp> scrolls;
    ArrayList<Bullet> bullets;
    public Player(Position2D<Float> pos, float szX, float szY,ArrayList<AABB> walls,ArrayList<Enemy> enemies,ArrayList<PowerUp> scrolls,ArrayList<Bullet> bullets)
    {
        super(pos, szX, szY);
        this.toCompare = new ArrayList<>();

        //Put other AABB s to another list to give it to constructor of a collision listener.
        this.enemies = enemies;
        this.walls = walls;
        this.scrolls = scrolls;
        this.bullets = bullets;

        toCompare.addAll(this.enemies);
        toCompare.addAll(this.scrolls);
        toCompare.addAll(this.walls);

        this.isAlive = true;


        // Asset reading.
        try
        {
            this.asset = new SpriteComponent(spritePath);
        }
        catch (IOException e)
        {
            System.out.println("Player Sprite Error");
        }

        // Adding decorators to decorator list in AbstractActor.
        this.components = new ArrayList<IRealTimeComponent>();
        this.components.add(new PlayerInputComponent(this.speed,this,this.bullets,this.walls));

        this.colCom = new CollisionComponent(this,this.toCompare);
        ICollisionListener playerWall = new PlayerWallCollision(this,this.walls);
        ICollisionListener playerScroll = new PlayerScrollCollision(this,this.scrolls);
        ICollisionListener playerEnemy = new PlayerEnemyCollision(this,this.enemies);


        this.colCom.registerObserver(playerScroll);
        this.colCom.registerObserver(playerEnemy);
        this.colCom.registerObserver(playerWall);
        this.components.add(this.colCom);
    }

    @Override
    public void update(float deltaT, Graphics2D g)
    {
        for(IRealTimeComponent component : this.components)
            component.update(deltaT);

        asset.draw(g,this);
    }

    public void setStatus(boolean status)
    {
        this.isAlive = status;
    }
    public int getSpeed()
    {
        return this.speed;
    }

    @Override
    public boolean isDead()
    {
        return this.isAlive;
    }
}

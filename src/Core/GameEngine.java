package Core;

import Actors.*;
import Components.*;
import Util.AABB;
import Util.GameMapLoader;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;


public class GameEngine
{
    private final Dimension screenSize;
    private final String currentMap;
    // Game Objects
    private Player player;

    // Concrete Types of the game
    private ArrayList<Wall> walls;
    private ArrayList<Enemy> enemies;
    private ArrayList<PowerUp> powerUps;
    private ArrayList<Bullet> bulletsInCirculation;

    // Extra Components.
    private ArrayList<IRealTimeComponent> miscComponents;

    private void ResetGame()
    {
        bulletsInCirculation.clear();
        walls.clear();
        enemies.clear();
        powerUps.clear();



        GameMapLoader map = new GameMapLoader(screenSize);
        boolean mapOK = map.loadMap(this.currentMap);
        if(!mapOK)
        {
            System.out.println("Util.Map Load Failed!");
            System.exit(1);
        }

        for(AABB wall : map.getLoadedWallAABBs())
        {
            this.walls.add(new Wall(wall.getPos(), wall.getSizeX(), wall.getSizeY()));
        }

        for(AABB scroll : map.getLoadedPowerUpAABBs())
        {
            this.powerUps.add(new PowerUp(scroll.getPos(),scroll.getSizeX(), scroll.getSizeY()));
        }

        for(AABB enemy : map.getLoadedEnemyXAABBs())
        {
            Enemy tmpEnemy = new Enemy(enemy.getPos(), enemy.getSizeX(), enemy.getSizeY(), map.getLoadedWallAABBs());
            tmpEnemy.setStrategy(new HorizontalPatrolStrategy());
            this.enemies.add(tmpEnemy);

        }

        for(AABB enemy : map.getLoadedEnemyYAABBs())
        {
            Enemy tmpEnemy = new Enemy(enemy.getPos(), enemy.getSizeX(), enemy.getSizeY(),map.getLoadedWallAABBs());
            tmpEnemy.setStrategy(new VerticalPatrolStrategy());
            this.enemies.add(tmpEnemy);
        }

        for(AABB enemy : map.getLoadedEnemyStationaryAABBs())
        {
            Enemy tmpEnemy = new Enemy(enemy.getPos(), enemy.getSizeX(), enemy.getSizeY(),map.getLoadedWallAABBs());
            tmpEnemy.setStrategy(new StationaryPatrolStrategy());
            this.enemies.add(tmpEnemy);
        }

        this.player = new Player(map.getLoadedPlayerAABB().getPos()
                ,map.getLoadedPlayerAABB().getSizeX(),
                map.getLoadedPlayerAABB().getSizeY(),map.getLoadedWallAABBs(),this.enemies,this.powerUps,bulletsInCirculation);
        this.player.setStatus(false);
        BulletEnemyCollisionHandler handler = new BulletEnemyCollisionHandler(this.enemies,bulletsInCirculation);

        miscComponents.add(handler);
    }

    public GameEngine(String mapFilePath, Dimension screenSize)
    {
        this.currentMap = mapFilePath;
        this.screenSize = screenSize;

        this.walls = new ArrayList<Wall>();
        this.enemies = new ArrayList<Enemy>();
        this.powerUps = new ArrayList<PowerUp>();
        this.bulletsInCirculation = new ArrayList<Bullet>();
        this.miscComponents = new ArrayList<IRealTimeComponent>();
        ResetGame();
    }

    public synchronized void update(float deltaT, Graphics2D currentDrawBuffer)
    {
        // Do update first
        walls.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        enemies.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        powerUps.forEach(actor -> actor.update(deltaT, currentDrawBuffer));
        bulletsInCirculation.forEach(actor-> actor.update(deltaT, currentDrawBuffer));
        player.update(deltaT, currentDrawBuffer);
        miscComponents.forEach(c -> c.update(deltaT));

        // Now stuff would die etc. check the states and delete
        enemies.removeIf(actor -> actor.isDead());
        powerUps.removeIf(actor -> actor.isDead());
        bulletsInCirculation.removeIf(actor -> actor.isDead());
        // If player dies game is over reset the system

        if(player.isDead())
            ResetGame();

        // If there are no power-ups left,
        // Player won the game!, still reset..
        if(powerUps.isEmpty())
            ResetGame();

        // And the game goes on forever...
    }
}

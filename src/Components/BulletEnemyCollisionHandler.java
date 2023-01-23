package Components;

import Actors.Bullet;
import Actors.Enemy;

import java.util.ArrayList;

public class BulletEnemyCollisionHandler implements IRealTimeComponent
{
    ArrayList<Enemy> enemies;
    ArrayList<Bullet> bullets;


    public BulletEnemyCollisionHandler(ArrayList<Enemy> enemies, ArrayList<Bullet> bullets)
    {
        this.enemies = enemies;
        this.bullets = bullets;
    }

    @Override
    public void update(float deltaT)
    {
        // TODO:
        for(Bullet bullet:bullets)
        {
            for(Enemy enemy : enemies)
            {
                if(bullet.collides(enemy))
                {
                    enemy.decreaseHealth();
                    if(enemy.getHealth() <= 0)
                        enemy = null;
                    bullet.setStatus(true);
                }
            }
        }


    }
}

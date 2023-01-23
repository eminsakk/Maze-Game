package Components;

import Actors.Enemy;
import Actors.Wall;
import Util.AABB;

import java.util.ArrayList;

public class EnemyWallCollision implements ICollisionListener
{

    Enemy correspondingEnemy;

    public EnemyWallCollision(Enemy enemy)
    {
        this.correspondingEnemy = enemy;
    }
    @Override
    public void aCollisionIsHappened(AABB box) {


       correspondingEnemy.moveIfCollide(box);
        correspondingEnemy.changeSpeed();
    }
}

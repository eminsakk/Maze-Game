package Components;

        import Actors.Bullet;
        import Actors.Player;
        import Actors.Wall;
        import Util.AABB;

        import java.util.ArrayList;

public class BulletWallCollision implements ICollisionListener
{

    private Bullet bullet;
    private ArrayList<AABB> walls;
    public BulletWallCollision(Bullet bullet, ArrayList<AABB> walls)
    {
        this.bullet = bullet;
        this.walls = walls;
    }
    @Override
    public void aCollisionIsHappened(AABB box) {

        for(AABB wall : walls)
        {
            if(wall == box)
                bullet.setStatus(true);
        }
    }
}

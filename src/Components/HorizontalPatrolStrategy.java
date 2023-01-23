package Components;

public class HorizontalPatrolStrategy extends AbstractPatrolStrategy
{

    @Override
    public void update(float deltaT)
    {
        this.pos.getPos().x += deltaT *this.speed;
    }
}

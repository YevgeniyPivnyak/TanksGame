package game.BField.AllTanks;


import game.BField.Destroyable;
import game.BField.Drawable;

public interface Tank extends Drawable, Destroyable {

    public void move();

    public Bullet fire();

    public void updateX(int x);

    public void updateY(int y);

}

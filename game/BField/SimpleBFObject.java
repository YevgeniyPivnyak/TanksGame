package game.BField;


import java.awt.*;

public abstract class SimpleBFObject implements BFObject {


    private int x;
    private int y;

    protected Color color;

    private boolean isDestroyed = false;

    public SimpleBFObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed) {
            g.setColor(this.color);
            g.fillRect(this.getX(), this.getY(), 64, 64);
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean isDestroyed() {
        return isDestroyed;
    }
}

import java.util.Random;

public class Food {

    private final int posX;
    private final int posY;

    public Food() {
        posX = generatePos(Graphics.Width);
        posY = generatePos(Graphics.Height);
    }

    private int generatePos(int size) {
        Random random = new Random();
        return random.nextInt(size / Graphics.Tick_Size) * Graphics.Tick_Size;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}

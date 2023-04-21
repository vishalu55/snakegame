import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Graphics extends JPanel implements ActionListener {

    static final int Height = 600;
    static final int Width = 600;
    static final int Tick_Size = 35;

    static final int Board_size = (Width*Height)/(Tick_Size*Tick_Size);

    final Font font = new Font("Times Roman",Font.BOLD,30);
    int[] SnakeX = new int[Board_size];
    int[] SnakeY = new int[Board_size];
    int snakelength;
    Food food;
    int FoodEaten;
    char direction = 'R';
    boolean isMoving  = false;
    final Timer timer = new Timer(150,this);

    public Graphics(){
        this.setPreferredSize(new Dimension(Width,Height));
        this.setBackground(Color.WHITE);
        this.setFocusable(true);
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (isMoving){
                    switch (e.getKeyCode()){
                        case KeyEvent.VK_LEFT:
                            if (direction !='R'){
                                direction = 'L';
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (direction !='L'){
                                direction = 'R';
                            }
                            break;
                        case KeyEvent.VK_UP:
                            if (direction !='D'){
                                direction = 'U';
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (direction !='U'){
                                direction = 'D';
                            }
                            break;
                    }
                } else{
                    start();
                }
            }
        });
        start();
    }

    protected void start(){
        SnakeX = new int[Board_size];
        SnakeY = new int[Board_size];
        snakelength = 5;
        FoodEaten = 0;
        direction = 'R';
        isMoving=true;
        spawnfood();
        timer.start();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        if (isMoving){
            g.setColor(Color.RED);
            g.fillOval(food.getPosX(),food.getPosY(),Tick_Size,Tick_Size);
            g.setColor(Color.BLACK);
            for (int i=0; i <snakelength;i++){
                g.fillRect(SnakeX[i],SnakeY[i], Tick_Size,Tick_Size);
            }
        }  else {
            String scoreText = String.format("The End... Press any key to play again!", FoodEaten);
            g.setColor(Color.BLACK);
            g.setFont(font);
            g.drawString(scoreText, (Width - getFontMetrics(g.getFont()).stringWidth(scoreText)) / 2, Height/2);
        }
    }

    protected void move(){
        for (int i =snakelength; i>0;i--){
            SnakeX[i] = SnakeX[i-1];
            SnakeY[i]= SnakeY[i-1];
        }
        switch(direction){
            case 'U'-> SnakeY[0] -= Tick_Size;
            case 'D'-> SnakeY[0] += Tick_Size;
            case 'L'-> SnakeX[0] -= Tick_Size;
            case 'R'-> SnakeX[0] += Tick_Size;

        }
    }
    protected void spawnfood(){
        food = new Food();

    }
    protected void eatFood(){
        if(SnakeX[0]==food.getPosX() && SnakeY[0] == food.getPosY()){
            snakelength++;
            spawnfood();
        }
    }

    protected void collisionTest(){
        for (int i = snakelength; i>0; i--){
            if((SnakeX[0]== SnakeX[i])&&(SnakeY[0] == SnakeY[i])){
    isMoving=false;
    break;
            }
        }
        if (SnakeX[0]<0 || SnakeY[0]>Width - Tick_Size || SnakeY[0] < 0 || SnakeY[0] > Height - Tick_Size){
            isMoving =false;
        }

        if (!isMoving){
            timer.stop();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (isMoving) {
            move();
            collisionTest();
            eatFood();
        }
        repaint();
    }
}

import javax.swing.*;

public class Snakegame extends JFrame {
        public Snakegame(){

                this.add(new Graphics());
            this.setTitle("Snake Game by Vishal");
            this.pack();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setResizable(false);
            this.setVisible(true);
            this.setLocationRelativeTo(null);

        }
}

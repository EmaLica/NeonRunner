package main;

public class NeonRunner {

    private GameWindow window;
    private GamePanel gamePanel;

    public NeonRunner(){
        gamePanel = new GamePanel();
        window = new GameWindow(gamePanel);

    }

}

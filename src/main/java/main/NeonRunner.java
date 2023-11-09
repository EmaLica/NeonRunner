package main;

public class NeonRunner {

    private GameWindow gameWindow;
    private GamePanel gamePanel;

    public NeonRunner() {
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();


    }

}

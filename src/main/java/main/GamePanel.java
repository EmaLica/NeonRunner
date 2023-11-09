package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 40;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;

    public GamePanel() {
        mouseInputs = new MouseInputs(this);

        importImg();
        loadAnimations();

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        animations = new BufferedImage[12][8];
        //y = 7 perche' nello sprite alla posizione y = 7
        //sono presenti le 4 animazioni idle (inattivo)
        for (int j = 0; j < animations.length; j++){
            for (int i = 0; i < animations[j].length; i++){
                animations[j][i] = img.getSubimage(i*46, j*48,46,48);
            }
        }

    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/Biker.png");

        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try{
                is.close();
            } catch (IOException cagacazzo) {
                cagacazzo.printStackTrace();
            }
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    public void setDirection(int direction) {
        this.playerAction = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        /**
         * Nello sprite biker.png, il player e' 48x48
         * e come se fosse "battaglia navale" per prendere un altro sprite,
         * uso le sue coordinate come se fosse una matrice e le moltiplico per i pixel (48)
         */
        updateAnimationTick();
        //subImg = img.getSubimage(1*48,1*48,48,48);
        setAnimation();
        updatePos();
        g.drawImage(animations[playerAction][aniIndex], (int) xDelta, (int) yDelta,138,144, null);
    }

    private void updatePos() {
        if(moving){
            switch(playerDir){
                case LEFT:
                    xDelta -= 5;
                    break;
                case UP:
                    yDelta -= 5;
                    break;
                case RIGHT:
                    xDelta += 5;
                    break;
                case DOWN:
                    yDelta += 5;
                    break;
            }
        }
    }

    private void setAnimation() {
        if(moving)
            playerAction = RUN;
        else
            playerAction = IDLE;
    }



    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction))
                aniIndex = 0;
        }
    }

}
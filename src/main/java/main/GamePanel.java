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

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta = 100, yDelta = 100;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 40;

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
        idleAni = new BufferedImage[4];
        //y = 7 perche' nello sprite alla posizione y = 7
        //sono presenti le 4 animazioni idle (inattivo)
        for (int i = 0; i < idleAni.length; i++){
            idleAni[i] = img.getSubimage(i, 7,46,48);
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

    public void changeXDelta(int value) {
        this.xDelta += value;

    }

    public void changeYDelta(int value) {
        this.yDelta += value;

    }

    public void setRectPos(int x, int y) {
        this.xDelta = x;
        this.yDelta = y;
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
        g.drawImage(idleAni[aniIndex], (int) xDelta, (int) yDelta,94,96, null);
    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= idleAni.length)
                aniIndex = 0;
        }
    }

}
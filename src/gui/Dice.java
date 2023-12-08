package gui;

import java.awt.Graphics;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Dice extends JPanel {

    Random rnd = new Random();
    int faceValue = 1;

    public Dice(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder // unsure pa ko here

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public void rollDice(){
        faceValue = rnd.nextInt(6) + 1;
        repaint();
    }

    public int getFaceValue(){
        return faceValue;
    }

    public Dice(int xCoord, int yCoord, int width, int height, String labelString) {
        // set border here

    }



}

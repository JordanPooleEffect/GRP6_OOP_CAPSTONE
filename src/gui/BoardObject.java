package gui;

import javax.swing.*;


public abstract class BoardObject extends JPanel {
    protected int xCoord;
    protected int yCoord;
    protected int width;
    protected int height;

    public BoardObject(int xCoord, int yCoord, int width, int height) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.width = width;
        this.height = height;
        this.setLayout(null);
    }

    // Add pata methods if needed
}

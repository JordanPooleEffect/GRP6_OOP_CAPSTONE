package gui;

import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.FontUIResource;

public class Board extends JPanel {


    private ArrayList<Square> allSquares = new ArrayList<Square>();
    private ArrayList<Square> unbuyableSquares = new ArrayList<Square>();

    public ArrayList<Square> getUnbuyableSquares(){
        return unbuyableSquares;
    }

    public ArrayList<Square> getAllSquares(){
        return allSquares;
    }

    public Square getSquareAtIndex(int location) {
        return allSquares.get(location);
    }

    public Board(int xCoord, int yCoord, int width, int height) {
        setBorder(new LineBorder(new Color(0, 0, 0)));
        setBounds(xCoord, yCoord, 612, 612);
        this.setLayout(null);
        initializeSquares();
    }



    private void initializeSquares() {



        String[] squareNames = {
                "Go",
                "Oriental Ave",
                "Community Chest",
                "Vermont Ave",
                "Connecticut Ave",
                "Roll once",
                "St. Charles Place",
                "Chance",
                "States Ave",
                "Virginia Ave",
                "Free Parking",
                "St. James Place",
                "Community Chest",
                "Tennessee Ave",
                "New York Ave",
                "Squeeze Play",
                "Pacific Ave",
                "North Carolina Ave",
                "Chance",
                "Pennsylvania Ave"
        };


        // squares on the top
        Square square00 = new Square(6,6,100,100,squareNames[0],135);
        this.add(square00);
        this.add(square00);
        allSquares.add(square00);
        unbuyableSquares.add(square00);
        // make more squares upto 19

        // setting prices
        square01.setPrice(100);
        square03.setPrice(100);
        square04.setPrice(120);

        square06.setPrice(140);
        square08.setPrice(140);
        square09.setPrice(160);

        square11.setPrice(180);
        square13.setPrice(180);
        square14.setPrice(200);

        square16.setPrice(300);
        square17.setPrice(300);
        square19.setPrice(320);

        // setting rent prcies
        square01.setRentPrice(6);
        square03.setRentPrice(6);
        square04.setRentPrice(8);

        square06.setRentPrice(10);
        square08.setRentPrice(10);
        square09.setRentPrice(12);

        square11.setRentPrice(14);
        square13.setRentPrice(14);
        square14.setRentPrice(16);

        square16.setRentPrice(26);
        square17.setRentPrice(26);
        square19.setRentPrice(28);

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }




}

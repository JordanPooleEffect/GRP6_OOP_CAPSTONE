package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class Player extends BoardObject {

    private int playerNumber;
    private Board gameBoard;

    JLabel lblPlayerNumber;
    static int totalPlayers = 0;

    private int currentSquareNumber = 0;
    private ArrayList<Integer> titleDeeds = new ArrayList<Integer>();
    private int wallet = 300; // initial money

    public ArrayList<Integer> getTitleDeeds() {
        return titleDeeds;
    }

    public int getWallet() {
        return wallet;
    }

    public void withdrawFromWallet(int amount) {
        wallet -= amount;

        if (wallet < 0) {

            handleBankruptcy();
        } else if (wallet == 0) {

            MonopolyMain.infoConsole.setText("Your balance is now $0. Ending your turn.");
            MonopolyMain.btnNextTurn.setEnabled(true);
            MonopolyMain.btnBuy.setEnabled(false);
            MonopolyMain.btnPayRent.setEnabled(false);
            MonopolyMain.btnRollDice.setEnabled(false);
        }
    }

    public boolean isBankrupt() {
        return wallet < 0;
    }


    private void handleBankruptcy() {
        // Existing code...

        // Show a dialog with options for surrendering or selling property
        int option = JOptionPane.showOptionDialog(
                this,
                "You are bankrupt! What do you want to do?",
                "Bankruptcy",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new Object[]{"Surrender", "Sell Property"},
                "Surrender");

        if (option == JOptionPane.YES_OPTION) {
            // Implement pa nato surrender logic
            surrender();
        } else {
            // Implement pa nato sell property logic
            sellProperties();
        }
    }

    private void surrender() {
        MonopolyMain.errorBox("Player " + playerNumber + " has surrendered. Game Over!", "Game Over");
        System.exit(0); // Terminate the game
    }


    private void sellProperties() {
        ArrayList<Integer> propertiesToSell = new ArrayList<>(titleDeeds);

        if (propertiesToSell.isEmpty()) {
            MonopolyMain.infoConsole.setText("You don't have any properties to sell. Ending your turn.");
            MonopolyMain.btnNextTurn.setEnabled(true);
            MonopolyMain.btnBuy.setEnabled(false);
            MonopolyMain.btnPayRent.setEnabled(false);
            MonopolyMain.btnRollDice.setEnabled(false);
            return;
        }

        // Create a mapping from property index to property name
        HashMap<Integer, String> propertyNames = new HashMap<>();
        for (Integer propertyIndex : propertiesToSell) {
            propertyNames.put(propertyIndex, gameBoard.getAllSquares().get(propertyIndex).getName());
        }

        String[] propertyNamesArray = propertyNames.values().toArray(new String[0]);

        String selectedPropertyName = (String) JOptionPane.showInputDialog(
                null,
                "You need to sell properties to cover your debt. Select properties to sell:",
                "Sell Properties",
                JOptionPane.QUESTION_MESSAGE,
                null,
                propertyNamesArray,
                propertyNamesArray[0]);  // Default to the first property

        if (selectedPropertyName != null) {
            // Find the property index based on the selected property name
            int propertyIndex = getKeyByValue(propertyNames, selectedPropertyName);
            int propertyPrice = gameBoard.getAllSquares().get(propertyIndex).getPrice();

            // Sell the selected property
            wallet += propertyPrice;
            titleDeeds.remove(Integer.valueOf(propertyIndex));
            Player.ledger.remove(propertyIndex);
            MonopolyMain.infoConsole.setText("Player " + playerNumber + " sold " + gameBoard.getAllSquares().get(propertyIndex).getName() + " for $" + propertyPrice);

            // Check if the player is still bankrupt
            if (wallet < 0) {
                // Player is still bankrupt after selling a property
                handleBankruptcy();
            } else {
                // Player has successfully sold a property
                /*MonopolyMain.btnNextTurn.setEnabled(true);
                MonopolyMain.btnBuy.setEnabled(false);
                MonopolyMain.btnPayRent.setEnabled(false);
                MonopolyMain.btnRollDice.setEnabled(false);*/
            }
        } else {
            // Player canceled the selection, end the turn
            MonopolyMain.infoConsole.setText("You canceled the sale. Ending your turn.");
            MonopolyMain.btnNextTurn.setEnabled(true);
            MonopolyMain.btnBuy.setEnabled(false);
            MonopolyMain.btnPayRent.setEnabled(false);
            MonopolyMain.btnRollDice.setEnabled(false);
        }
    }


    public void depositToWallet(int depositAmount) {
        wallet += depositAmount;
        System.out.println("Payday for player "+getPlayerNumber()+". You earned $200!");
    }


    public int getCurrentSquareNumber() {
        return currentSquareNumber;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public boolean hasTitleDeed(int squareNumber) {
        return titleDeeds.contains(squareNumber) ? true : false;
    }

    public void buyTitleDeed(int squareNumber) {
        int propertyPrice = gameBoard.getAllSquares().get(squareNumber).getPrice();

        if (wallet < propertyPrice) {
            MonopolyMain.errorBox("You can't afford to buy " + gameBoard.getAllSquares().get(squareNumber).getName() + ". Ending your turn.", "Insufficient Funds");
            MonopolyMain.btnNextTurn.setEnabled(true);
            MonopolyMain.btnBuy.setEnabled(false);
            MonopolyMain.btnPayRent.setEnabled(false);
            MonopolyMain.btnRollDice.setEnabled(false);
            return;
        }

        if(ledger.containsKey(squareNumber)) {
            System.out.println("It's already bought by someone. You cannot by here.");
        } else {
            titleDeeds.add(this.getCurrentSquareNumber());
            ledger.put(squareNumber, this.getPlayerNumber());

        }
    }


    public Player(int playerNumber, Color color, Board gameBoard) {
        super(playerNumber * 30, 33, 20, 28);
        this.playerNumber = playerNumber;
        this.setBackground(color);
        this.gameBoard = gameBoard;
        lblPlayerNumber = new JLabel("" + playerNumber);
        lblPlayerNumber.setFont(new Font("Lucida Grande", Font.BOLD, 15));
        lblPlayerNumber.setForeground(Color.WHITE);
        this.add(lblPlayerNumber);
    }

  //  public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    public void move(int dicesTotal) {
        if(currentSquareNumber + dicesTotal > 19) {
            depositToWallet(200);
        }
        int targetSquare = (currentSquareNumber + dicesTotal) % 20;
        currentSquareNumber = targetSquare;

        if(MonopolyMain.nowPlaying == 0) {
            this.setLocation(xLocationsOfPlayer1[targetSquare], yLocationsOfPlayer1[targetSquare]);
        } else {
            this.setLocation(xLocationsOfPlayer2[targetSquare], yLocationsOfPlayer2[targetSquare]);
        }

        if(ledger.containsKey(this.getCurrentSquareNumber())) {
            MonopolyMain.infoConsole.setText("This property belongs to player "+ledger.get(this.getCurrentSquareNumber()));
        }
        //ledger.put(this.getCurrentSquareNumber(), this.getPlayerNumber());
    }



    // by comparing player's coordinates according to the board, we will get it's
    // current square number
    // currently unused, found a better way
    public int getCurrentSquareNumberByCoordinates() {

        int x = this.getX();
        int y = this.getY();

        if(x < 100) {
            if(y < 100) {
                return 0;
            } else if(y > 100 && y < 200) {
                return 19;
            } else if(y > 200 && y < 300) {
                return 18;
            } else if(y > 300 && y < 400) {
                return 17;
            } else if(y > 400 && y < 500) {
                return 16;
            } else {
                return 15;
            }
        } else if(x > 100 && x < 200) {
            if(y < 100) {
                return 1;
            } else {
                return 14;
            }
        } else if(x > 200 && x < 300) {
            if(y < 100) {
                return 2;
            } else {
                return 13;
            }
        } else if(x > 300 && x < 400) {
            if(y < 100) {
                return 3;
            } else {
                return 12;
            }
        }else if(x > 400 && x < 500) {
            if(y < 100) {
                return 4;
            } else {
                return 11;
            }
        }
    }

}

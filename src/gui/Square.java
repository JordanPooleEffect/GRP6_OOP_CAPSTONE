package gui;


import javax.swing.JLabel;
import javax.swing.JPanel;

public class Square extends BoardObject {

    int number;
    private String name;
    String description;
    JLabel nameLabel;
    static int totalSquares = 0;
    private int price;
    private int rentPrice;

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }


    public Square(int xCoord, int yCoord, int width, int height, String labelString, int rotationDegrees) {
        super(xCoord, yCoord, width, height);
        number = totalSquares;
        totalSquares++;
        name = labelString;
        this.setLayout(null);

        if(rotationDegrees == 0) {
            nameLabel = new JLabel(labelString);
            this.add(nameLabel);
        } else {
            nameLabel = new JLabel(labelString) {
                protected void paintComponent() {
                    double x = getWidth()/2.0;
                    double y = getHeight()/2.0;
                    aT.rotate(Math.toRadians(rotationDegrees), x, y);
                    super.paintComponent();
                }
            };
            if(rotationDegrees == 90) {
                nameLabel.setBounds(20, 0, this.getWidth(), this.getHeight());
            }
            if(rotationDegrees == -90) {
                nameLabel.setBounds(-10, 0, this.getWidth(), this.getHeight());
            }
            if(rotationDegrees == 180) {
                nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
            }
            if(rotationDegrees == 135 || rotationDegrees == -135 || rotationDegrees == -45 || rotationDegrees == 45) {
                nameLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
            }
            nameLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
            nameLabel.setHorizontalAlignment(SwingConstants.CENTER);

            this.add(nameLabel);
        }

    }

    public void paintComponent(Graphics g) {

        }

    }

    private boolean isRentPaid = false;
    public boolean isRentPaid() {
        return isRentPaid;
    }
    public void setRentPaid(boolean pay) {
        isRentPaid = pay;
    }

}

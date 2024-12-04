package utils.helper;

import javax.swing.*;
import java.awt.*;

public class CharacterItem extends RoundedPanel {
    private ImageIcon image = new ImageIcon(getClass().getResource("/image/character/128x128/dragunov.png"));
    private JLabel imageLabel;
    private RopaLabel titleLabel;

    public CharacterItem() {
        setLayout(null);
        setPreferredSize(new Dimension(124, 180));
        setBackground(new Color(66, 21, 50));
        setRoundBottomLeft(25);
        setRoundBottomRight(25);
        imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBounds(0, 0, 124, 124);

        titleLabel = new RopaLabel("Dragunov");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFontSize(18);
        titleLabel.setBounds(0, 124 - 9, 124, 180 - 124);
        
        add(imageLabel);
        add(titleLabel);
    }

    public void setTitle(String title) {
        titleLabel.setText(title);
    }

    public String getTitle() {
        return titleLabel.getText();
    }

    public void setImage(ImageIcon image) {
        imageLabel.setIcon(image);
        this.image = image;
    }

    public ImageIcon getImage() {
        return (ImageIcon) imageLabel.getIcon();
    }
    
}

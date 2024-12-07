/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.helper;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Dammar
 */
public class ComboListHero extends RoundedPanel {

    private ImageIcon image = new ImageIcon(getClass().getResource("/image/character/1170x263/law.png"));
    private JLabel imageLabel;
    private RopaItalicLabel titleLabel;

    public ComboListHero() {
        setLayout(null);
        setPreferredSize(new Dimension(1170, 263 + 23));
        setBackground(new Color(8, 18, 38));
        setRoundBottomLeft(25);
        setRoundBottomRight(25);

        imageLabel = new JLabel();
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setBounds(0, 0, 1170, 263);

        titleLabel = new RopaItalicLabel("Combo Law");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFontSize(48);

        int margin = 9;
        Dimension labelSize = titleLabel.getPreferredSize();
        int x = getPreferredSize().width - labelSize.width - margin;
        int y = getPreferredSize().height - labelSize.height - margin;
        titleLabel.setBounds(x, y, labelSize.width, labelSize.height);

        JPanel border = new JPanel();
        border.setPreferredSize(new Dimension(1170, 263));
        border.setBackground(new Color(202, 28, 77));
        border.setBounds(0, 263 + 15, 1170, 8);

        add(imageLabel);
        add(titleLabel);
        add(border);

        setComponentZOrder(titleLabel, 0);
        setComponentZOrder(imageLabel, 1);
        setComponentZOrder(border, 2);
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

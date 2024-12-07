/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.helper;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Dammar
 */
class MakerLabel extends JPanel {
    private String characterName = "Law";
    private String maker = "Me";
    private String submitedAt = "07/12/2024";
    
    private RopaLabel labelSetup(int x, Color color){
        RopaLabel label = new RopaLabel();
        Dimension characterDim = label.getPreferredSize();
        label.setForeground(color);
        label.setText(characterName + " Combo by ");
        label.setFontSize(18);
        label.setBounds(x, 0, characterDim.width, characterDim.height);
        return label;
    }
    
    public MakerLabel(){
        setBackground(new Color(8, 18, 38));
        RopaLabel characterLabel = labelSetup(0, Color.white);
        RopaLabel makerLabel = labelSetup(characterLabel.getPreferredSize().width, Color.red);
    }
}

public class ComboListData extends JPanel {

    private String characterName = "Dragunov";
    private ImageIcon image = new ImageIcon(getClass().getResource("/image/character/128x128/dragunov.png"));
    private CharacterItem character = new CharacterItem();
    private String maker = "Me";
    private String submitedAt = "07/12/2024";

    public ComboListData() {
        int widthDefault = 1170;
        int heightDefault = 236;

        setLayout(null);
        setPreferredSize(new Dimension(widthDefault, heightDefault));
        setBackground(new Color(8, 18, 38));

        Dimension characterDimension = character.getPreferredSize();
        character.setBounds(0, 0, characterDimension.width, characterDimension.height);

        RoundedPanel data = new RoundedPanel();
        int dataWidth = widthDefault - characterDimension.width - 20;
        data.setPreferredSize(new Dimension(dataWidth, heightDefault));
        data.setBounds(characterDimension.width + 20, 0, dataWidth, 236);
        data.setRoundBottomLeft(25);
        data.setRoundBottomRight(25);
        data.setRoundTopLeft(25);
        data.setRoundTopRight(25);
        data.setLayout(null);
        data.setBackground(new Color(66, 21, 50));

        RopaLabel makerLabel = new RopaLabel();
        makerLabel.setText(characterName + " Combo by " + maker + " submitted at " + submitedAt);
        makerLabel.setFontSize(18);
        makerLabel.setBounds(10, 10, makerLabel.getPreferredSize().width, makerLabel.getPreferredSize().height);

        data.add(makerLabel);
        add(character);
        add(data);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test ComboListHero");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Tambahkan panel ComboListHero
            ComboListData panel = new ComboListData();
            frame.add(panel);

            // Atur ukuran dan tampilkan frame
            frame.pack();
            frame.setLocationRelativeTo(null); // Center the frame
            frame.setVisible(true);
        });
    }
}

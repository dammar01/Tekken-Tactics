package utils.helper;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class MakerLabel extends JPanel {

    private String characterName = "Law";
    private String maker = "Me";
    private String submitedAt = "07/12/2024";
    private RopaLabel characterNameLabel;
    private RopaLabel makerLabel;
    private RopaLabel submitedAtLabel;

    public MakerLabel() {
        setBackground(new Color(66, 21, 50));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        characterNameLabel = setupLabel(characterName + " combo by", Color.white);
        makerLabel = setupLabel(maker, Color.red);
        submitedAtLabel = setupLabel("submited at " + submitedAt, Color.white);
        
        add(characterNameLabel);
        add(makerLabel);
        add(submitedAtLabel);
    }

    private RopaLabel setupLabel(String t, Color color) {
        RopaLabel label = new RopaLabel();
        label.setText(t);
        label.setFontSize(18);
        label.setForeground(color);
        return label;
    }
    
    public void setCharacterName(String name){
        characterName = name;
        characterNameLabel.setText(characterName + " combo by");
    }
    
    public void setMaker(String name){
        maker = name;
        makerLabel.setText(maker);
    }
    
    
    public void setSubmitedAt(String date){
        submitedAt = date;
        submitedAtLabel.setText("submited at " + submitedAt);
    }
}

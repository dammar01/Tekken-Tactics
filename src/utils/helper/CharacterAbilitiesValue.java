/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

/**
 *
 * @author Dammar
 */
public class CharacterAbilitiesValue extends JPanel {
    private int value = 0;
    public CharacterAbilitiesValue() {
        setPreferredSize(new Dimension(405, 35));
        setBackground(new Color(66, 21, 50));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setValue(value);
    }
    

    public int getValue(){
        return this.value;
    }
    
    public void setValue(int value) {
        this.value = value;
        removeAll();
        for (int i = 0; i < 10; i++) {
            JPanel panel = new JPanel();
            panel.setPreferredSize(new Dimension(35, 24));
            if (i < value) {
                panel.setBackground(new Color(202, 28, 77));
            } else {
                panel.setBackground(Color.white);
            }
            add(panel);
        }
        revalidate();
        repaint();
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.helper;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import javax.swing.JTextArea;

/**
 *
 * @author Dammar
 */
public class RopaLabelArea extends JTextArea {

    private Font customFont;
    private String text = "text area";

    public RopaLabelArea(String text) {
        super();
        setText(text != null ? text.toString() : "");
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        setEditable(false);
        setBorder(null);
        initFont();
    }

    public RopaLabelArea() {
        super();
        setText(this.text);
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(true);
        setEditable(false);
        setBorder(null);
        initFont();
    }

    private void initFont() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/utils/font/RopaSans-Regular.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Font file not found");
            }
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            this.customFont = this.customFont.deriveFont(24f);
            setForeground(Color.white);
            setFont(this.customFont);
        } catch (Exception e) {
            e.printStackTrace();
            setFont(new Font("SansSerif", Font.PLAIN, 24));
        }
    }
    
    public void setFontSize(float size) {
        if (this.customFont != null) {
            setFont(this.customFont.deriveFont(size));
        }
    }
    
    public void setText(String text){
        setText(text);
        this.text = text;
    }
}

package utils.helper;

import javax.swing.*;
import java.awt.*;
import java.io.InputStream;

public class RopaItalicLabel extends JLabel {

    // Properti font kustom
    private Font customFont;

    // Konstruktor dengan teks
    public RopaItalicLabel(String text) {
        super(text);
        initFont();
    }

    // Konstruktor tanpa teks
    public RopaItalicLabel() {
        super();
        initFont();
    }

    private void initFont() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/utils/font/RopaSans-Italic.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Font file not found");
            }
            // Memuat font kustom
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
}

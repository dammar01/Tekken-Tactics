package utils.helper;

import java.awt.Color;
import java.awt.Font;
import java.io.InputStream;
import javax.swing.JTextArea;

public class RopaLabelArea extends JTextArea {

    private Font customFont;
    private String text = "text area";

    public RopaLabelArea(String t) {
        super();
        initFont();
        setText(t != null ? t : "");
        configureProperties();
    }

    public RopaLabelArea() {
        super();
        initFont();
        setText(this.text);
        configureProperties();
    }

    private void initFont() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/utils/font/RopaSans-Regular.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Font file not found");
            }
            this.customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(24f);
            setFont(this.customFont);
        } catch (Exception e) {
            e.printStackTrace();
            setFont(new Font("SansSerif", Font.PLAIN, 24));
        }
    }

    // Konfigurasi properti area teks
    private void configureProperties() {
        setLineWrap(true);
        setWrapStyleWord(true);
        setOpaque(false);
        setEditable(false);
        setBorder(null);
        setForeground(Color.WHITE); 
    }

    @Override
    public void setText(String t) {
        this.text = t;
        super.setText(text);
    }

    // Mengatur ukuran font
    public void setFontSize(float size) {
        if (this.customFont != null) {
            setFont(this.customFont.deriveFont(size));
        }
    }
}

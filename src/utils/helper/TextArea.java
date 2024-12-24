package utils.helper;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TextArea extends RoundedPanel {

    private Font customFont;
    private float fontSize = 24f;
    private JTextArea textArea;
    private ScrollBar scrollPane;

    public TextArea() {
        initFont();
        initTextArea();
        initResizeListener();
    }

    private void initFont() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/utils/font/RopaSans-Regular.ttf");
            if (fontStream == null) {
                throw new RuntimeException("Font file not found");
            }
            customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(fontSize);
        } catch (Exception e) {
            System.err.println("Failed to load custom font. Using default.");
            customFont = new Font("SansSerif", Font.PLAIN, (int) fontSize);
        }
    }

    private void initTextArea() {
        setLayout(new BorderLayout());
        setBackground(new Color(50, 50, 50));

        textArea = new JTextArea();
        textArea.setBackground(getBackground());
        textArea.setForeground(Color.WHITE);
        textArea.setCaretColor(Color.WHITE);
        textArea.setFont(customFont);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        scrollPane = new ScrollBar(textArea);
        scrollPane.setBorder(null);

        add(scrollPane, BorderLayout.CENTER);
    }

    private void initResizeListener() {
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setPreferredSize(getPreferredSize());
                if (scrollPane != null) {
                    scrollPane.setPreferredSize(getPreferredSize());
                }
                revalidate();
                repaint();
            }
        });
    }

    @Override
    public void setPreferredSize(Dimension preferredSize) {
        super.setPreferredSize(preferredSize);
        if (scrollPane != null) {
            scrollPane.setPreferredSize(preferredSize);
        }
        revalidate();
        repaint();
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
        if (textArea != null) {
            textArea.setBackground(bg);
        }
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        if (textArea != null) {
            textArea.setForeground(fg);
            textArea.setCaretColor(fg);
        }
    }

    public void setFontSize(float size) {
        this.fontSize = size;
        if (customFont != null) {
            customFont = customFont.deriveFont(fontSize);
            textArea.setFont(customFont);
        }
    }
    
    public void setText(String t){
        textArea.setText(t);
    }
    
    public String getText(){
        return textArea.getText();
    }
}

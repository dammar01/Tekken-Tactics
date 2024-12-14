/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils.helper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Dammar
 */
public class MyComboData extends JPanel {

    private String characterName = "Dragunov";
    private ImageIcon image = new ImageIcon(getClass().getResource("/image/character/128x128/dragunov.png"));
    private CharacterItem character = new CharacterItem();
    private String maker = "Me";
    private String submitedAt = "07/12/2024";
    private String moveName = "General Combo";
    private boolean favorite = false;
    private boolean deleteable = false;
    private String[] notation = {"1"};
    private String version = "v1.06";
    private int totalHit = 8;
    private int totalDamage = 67;
    
    private RopaLabel moveNameLabel;
    private MakerLabel makerLabel;
    private RoundedPanel dataPanel;
    private JPanel notationPanel;
    private JLabel favoriteIcon;
    private JLabel deleteIcon;
    private RoundedPanel additionalPanel;


    private void reloadPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    private void reloadPanel(RoundedPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    private RoundedPanel drawAdditional(String val, Color bg, Color fg) {
        RoundedPanel panel = new RoundedPanel();
        panel.setLayout(new FlowLayout());
        panel.setRoundBottomLeft(10);
        panel.setRoundBottomRight(10);
        panel.setRoundTopLeft(10);
        panel.setRoundTopRight(10);
        panel.setBackground(bg);
        RopaLabel text = new RopaLabel();
        text.setText(val);
        text.setFontSize(12);
        text.setForeground(fg);
        panel.add(text);
        return panel;
    }

    private RoundedPanel setupAdditional(String ver, int hit, int damage) {
        RoundedPanel additional = new RoundedPanel();
        additional.setLayout(new FlowLayout(StyleConstants.ALIGN_LEFT));

        RoundedPanel version = drawAdditional(ver, Color.red, Color.white);
        RoundedPanel hits = drawAdditional(Integer.toString(hit) + " hits", new Color(215, 220, 221), Color.black);
        RoundedPanel damages = drawAdditional(Integer.toString(damage) + " damages", new Color(215, 220, 221), Color.black);

        additional.add(version);
        additional.add(hits);
        additional.add(damages);

        additional.setPreferredSize(new Dimension(dataPanel.getWidth(), version.getPreferredSize().height + 5));
        additional.setBackground(new Color(66, 21, 50));
        return additional;
    }

    private RoundedPanel setupDataPanel(int w, int h, int x) {
        RoundedPanel data = new RoundedPanel();
        data.setPreferredSize(new Dimension(w, h));
        data.setBounds(x, 0, w, 236);
        data.setRoundBottomLeft(25);
        data.setRoundBottomRight(25);
        data.setRoundTopLeft(25);
        data.setRoundTopRight(25);
        data.setLayout(null);
        data.setBackground(new Color(66, 21, 50));
        return data;
    }
    
    private RopaLabel setupMoveName(){
        RopaLabel move = new RopaLabel();
        move.setText(moveName);
        move.setFontSize(18);
        move.setForeground(Color.white);
        move.setBackground(new Color(66, 21, 50));
        move.setBounds(10, 10, move.getPreferredSize().width, move.getPreferredSize().height);
        return move;
    }

    private MakerLabel setupMakerLabel() {
        MakerLabel makerLabel = new MakerLabel();
        makerLabel.setCharacterName(characterName);
        makerLabel.setMaker(maker);
        makerLabel.setSubmitedAt(submitedAt);
        return makerLabel;
    }

    private JLabel isFavorite(boolean val) {
        JLabel fav = new JLabel();
        String iconPath = val ? "/image/icon/normal/is_favorite.png"
                : "/image/icon/normal/not_favorite.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        fav.setIcon(icon);
        fav.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        int xPosition = dataPanel.getWidth() - icon.getIconWidth() - 10;
        fav.setBounds(xPosition, 10, icon.getIconWidth(), icon.getIconHeight());
        return fav;
    }
    
    private JLabel deleteCombo(boolean active){
        JLabel del = new JLabel();
        String iconPath = active ? "/image/icon/normal/trash_active.png"
                : "/image/icon/normal/trash_inactive.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        del.setIcon(icon);
        del.setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
        int xPosition = dataPanel.getWidth() - (icon.getIconWidth() * 2) - 10;
        del.setBounds(xPosition, 10, icon.getIconWidth(), icon.getIconHeight());
        return del;
    }

    private void addData(JPanel panel) {
        panel.removeAll();
        int x = 0, y = 0;
        int lastIconHeight = 0;

        for (int i = 0; i < notation.length; i++) {
            ImageIcon icon = new ImageIcon(getClass().getResource("/image/button/32x32/" + notation[i] + ".png"));
            int iconWidth = icon.getIconWidth();
            int iconHeight = icon.getIconHeight();
            if (x + iconWidth > panel.getWidth()) {
                x = 0;
                y += iconHeight;
            }
            JLabel label = new JLabel();
            label.setIcon(icon);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setBounds(x, y, iconWidth, iconHeight);
            panel.add(label);
            x += iconWidth;
            lastIconHeight = iconHeight;
        }
        panel.setPreferredSize(new Dimension(panel.getWidth(), y + lastIconHeight));
        panel.setBounds(panel.getX(), panel.getY(), panel.getWidth(), y + lastIconHeight);
        reloadPanel(panel);
    }

    private JPanel setupDataNotation() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(66, 21, 50));

        final int margin = 10;
        int panelWidth = 1170 - (margin * 2);
        int panelHeight = 263 - moveNameLabel.getHeight() - (margin * 2);
        int panelX = margin;
        int panelY = moveNameLabel.getHeight() + margin;

        panel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        panel.setBounds(panelX, panelY, panelWidth, panelHeight);

        addData(panel);
        return panel;
    }
    
    private void refreshAdditionalPanel() {
        dataPanel.remove(additionalPanel);
        additionalPanel = setupAdditional(version, totalHit, totalDamage);

        Dimension additionalSize = additionalPanel.getPreferredSize();
        additionalPanel.setBounds(10, dataPanel.getHeight() - additionalSize.height - 10, additionalSize.width, additionalSize.height);
        dataPanel.add(additionalPanel);

        reloadPanel(dataPanel);
        revalidate();
        repaint();
    }

    public MyComboData() {
        int widthDefault = 1170;
        int heightDefault = 236;

        setLayout(null);
        setBackground(new Color(8, 18, 38));

        Dimension characterDimension = character.getPreferredSize();
        character.setBounds(0, 0, characterDimension.width, characterDimension.height);

        int x = characterDimension.width + 20;
        int dataWidth = widthDefault - x;

        dataPanel = setupDataPanel(dataWidth, heightDefault, x);
        moveNameLabel = setupMoveName();
        makerLabel = setupMakerLabel();
        notationPanel = setupDataNotation();
        favoriteIcon = isFavorite(favorite);
        deleteIcon = deleteCombo(deleteable);
        additionalPanel = setupAdditional("v1.06", 8, 67);
        
        int dataPanelHeight = notationPanel.getHeight() + 20 + moveNameLabel.getHeight() + additionalPanel.getHeight();
        dataPanelHeight = Math.max(dataPanelHeight, 180);
        
        dataPanel.setPreferredSize(new Dimension(dataWidth, Math.max(dataPanelHeight, 180)));
        dataPanel.setBounds(dataPanel.getX(), dataPanel.getY(), dataWidth, Math.max(dataPanelHeight, 180));
        reloadPanel(dataPanel);

        Dimension additionalSize = additionalPanel.getPreferredSize();
        additionalPanel.setBounds(10, dataPanelHeight - additionalSize.height - 10, additionalSize.width, additionalSize.height);
        reloadPanel(additionalPanel);
        setPreferredSize(new Dimension(widthDefault, dataPanelHeight));
        
        int bound[] = {
            dataPanel.getBounds().width - makerLabel.getPreferredSize().width - 10,
            dataPanel.getBounds().height - makerLabel.getPreferredSize().height - 10,
            makerLabel.getPreferredSize().width,
            makerLabel.getPreferredSize().height
        };
        makerLabel.setBounds(bound[0], bound[1], bound[2], bound[3]);
        
        dataPanel.add(moveNameLabel);
        dataPanel.add(favoriteIcon);
        dataPanel.add(deleteIcon);
        dataPanel.add(makerLabel);
        dataPanel.add(notationPanel);
        dataPanel.add(additionalPanel);
        

        add(character);
        add(dataPanel);
    }
    
    public void setMovename(String name) {
        this.moveName = name;
        moveNameLabel.setText(name);
        revalidate();
        repaint();
    }

    public void setCharacterName(String name) {
        this.characterName = name;
        makerLabel.setCharacterName(name);
        revalidate();
        repaint();
    }

    public void setMaker(String name) {
        this.maker = name;
        makerLabel.setMaker(name);
    }

    public void setSubmitedAt(String date) {
        this.submitedAt = date;
        makerLabel.setSubmitedAt(date);
    }

    public void setImage(ImageIcon image) {
        this.image = image;
        character.setImage(image);
        revalidate();
        repaint();
    }

    public void setFavorite(boolean val) {
        this.favorite = val;
        String iconPath = this.favorite ? "/image/icon/normal/is_favorite.png"
                : "/image/icon/normal/not_favorite.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        this.favoriteIcon.setIcon(icon);
        reloadPanel(this.dataPanel);
        revalidate();
        repaint();
    }
    
    public void setDeleteable(boolean val) {
        this.deleteable = val;
        String iconPath = this.favorite ? "/image/icon/normal/trash_active.png"
                : "/image/icon/normal/trash_inactive.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(iconPath));
        this.deleteIcon.setIcon(icon);
        reloadPanel(this.dataPanel);
        revalidate();
        repaint();
    }

    public void setNotation(String[] data) {
        this.notation = data;

        dataPanel.remove(notationPanel);

        notationPanel = new JPanel();
        notationPanel.setLayout(null);
        notationPanel.setBackground(new Color(66, 21, 50));

        final int margin = 10;
        int panelWidth = dataPanel.getWidth() - (margin * 2);
        int x = 0, y = 0;
        int lastIconHeight = 0;

        for (String notationKey : notation) {
            String resourcePath = "/image/button/32x32/" + notationKey + ".png";
            ImageIcon icon = new ImageIcon(getClass().getResource(resourcePath));
            if (icon.getIconWidth() > 0 && icon.getIconHeight() > 0) {
                JLabel label = new JLabel();
                label.setIcon(icon);
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());

                notationPanel.add(label);
                x += icon.getIconWidth();

                if (x + icon.getIconWidth() > panelWidth) {
                    x = 0;
                    y += icon.getIconHeight();
                }

                lastIconHeight = icon.getIconHeight();
            } else {
                System.err.println("Icon not found: " + resourcePath);
            }
        }

        notationPanel.setPreferredSize(new Dimension(panelWidth, y + lastIconHeight));
        notationPanel.setBounds(margin, makerLabel.getHeight() + margin, panelWidth, y + lastIconHeight);
        dataPanel.add(notationPanel);

        int newHeight = makerLabel.getHeight() + margin + notationPanel.getPreferredSize().height + margin;
        dataPanel.setPreferredSize(new Dimension(dataPanel.getWidth(), Math.max(newHeight, 180)));
        reloadPanel(dataPanel);
        revalidate();
        repaint();
    }

    public void setVersion(String ver) {
        this.version = ver;
        refreshAdditionalPanel();
    }

    public void setHit(int hit) {
        this.totalHit = hit;
        refreshAdditionalPanel();
    }

    public void setDamage(int damage) {
        this.totalDamage = damage;
        refreshAdditionalPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test ComboListHero");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            MyComboData panel = new MyComboData();
            frame.add(panel);

            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
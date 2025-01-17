/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guide;

import home.Home;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import utils.helper.ScrollBar;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import utils.helper.Db;
import guide.Character;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dammar
 */
public class Guide extends javax.swing.JFrame {

    /**
     * Creates new form Guide
     */
    private int characterPanelHeight = 180 + 10;
    private int characterPanelWidth = 1125;
    private int currentYOffset = 120;

    private void reloadPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    private void updatePanel(JPanel panel, int x, int y, int w, int h) {
        panel.setPreferredSize(new java.awt.Dimension(w, h));
        panel.setBounds(x, y, w, h);
        reloadPanel(panel);
    }

    private String getLatestUpdated(Db db) {
        String latest = "";
        try {
            db.connect();
            String selectQuery = "SELECT * FROM `character` ORDER BY `character`.`last_updated` DESC LIMIT 1";
            ResultSet resultSet = db.executeQuery(selectQuery);
            while (resultSet.next()) {
                latest = resultSet.getDate("last_updated") + " " + resultSet.getTime("last_updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return latest;
    }

    private void addAnchor(utils.helper.CharacterItem item, HashMap<String, String> data) {
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Character character;
                try {
                    character = new Character(data);
                    character.setVisible(true);
                } catch (SQLException err) {
                    err.printStackTrace();
                }
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                item.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }
        });
    }

    private void drawCharacter(JPanel tier_panel, JPanel character_panel, List<HashMap<String, String>> data) {
        int row = (int) Math.ceil(data.size() / 8.0);
        characterPanelHeight = 180 * row + (row * 10);
        for (HashMap<String, String> character : data) {
            String code = character.get("code");
            String name = character.get("name");
            if (code != "_") {
                try {
                    utils.helper.CharacterItem item = new utils.helper.CharacterItem(name, code);
                    character_panel.add(item);
                    addAnchor(item, character);
                } catch (NullPointerException e) {
                    utils.helper.CharacterItem item = new utils.helper.CharacterItem(name, "dragunov");
                    character_panel.add(item);
                    addAnchor(item, character);
                }

            }
        }
        updatePanel(character_panel, character_panel.getX(), character_panel.getY(), characterPanelWidth, characterPanelHeight);
        updatePanel(tier_panel, tier_panel.getX(), currentYOffset, characterPanelWidth, characterPanelHeight + 70);
        currentYOffset += characterPanelHeight + 70 + 20;
    }

    public Guide() {
        initComponents();
        Db db = new Db();
        HashMap<String, ArrayList<HashMap<String, String>>> tierMap = new HashMap<>();
        tierMap.put("S", new ArrayList<>());
        tierMap.put("A", new ArrayList<>());
        tierMap.put("B", new ArrayList<>());
        tierMap.put("C", new ArrayList<>());
        tierMap.put("D", new ArrayList<>());
        try {
            db.connect();
            String selectQuery = "SELECT * FROM `character`";
            ResultSet resultSet = db.executeQuery(selectQuery);
            while (resultSet.next()) {
                String id = String.valueOf(resultSet.getInt("id"));
                String code = resultSet.getString("code");
                String name = resultSet.getString("name");
                String tier = resultSet.getString("tier");

                if (tierMap.containsKey(tier)) {
                    HashMap<String, String> characterData = new HashMap<>();
                    characterData.put("id", id);
                    characterData.put("code", code);
                    characterData.put("name", name);
                    tierMap.get(tier).add(characterData);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        drawCharacter(this.s_tier, this.s_tier_character, tierMap.get("S"));
        drawCharacter(this.a_tier, this.a_tier_character, tierMap.get("A"));
        drawCharacter(this.b_tier, this.b_tier_character, tierMap.get("B"));
        drawCharacter(this.c_tier, this.c_tier_character, tierMap.get("C"));
        drawCharacter(this.d_tier, this.d_tier_character, tierMap.get("D"));
        last_update.setText("Last updated: " + getLatestUpdated(db));
        updatePanel(main, 0, 0, 1281, currentYOffset);
        updatePanel(root, 0, 0, 1281, currentYOffset);
        ScrollBar scrollPane = new ScrollBar(root);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBounds(0, 0, main.getWidth(), 650);
        add(scrollPane);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        root = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        s_tier = new javax.swing.JPanel();
        red_box = new javax.swing.JPanel();
        s_tier_title = new utils.helper.RopaItalicLabel();
        s_tier_character = new javax.swing.JPanel();
        a_tier = new javax.swing.JPanel();
        orange_box = new javax.swing.JPanel();
        a_tier_title = new utils.helper.RopaItalicLabel();
        a_tier_character = new javax.swing.JPanel();
        b_tier = new javax.swing.JPanel();
        yellow_box = new javax.swing.JPanel();
        b_tier_title = new utils.helper.RopaItalicLabel();
        b_tier_character = new javax.swing.JPanel();
        c_tier = new javax.swing.JPanel();
        green_box = new javax.swing.JPanel();
        c_tier_title = new utils.helper.RopaItalicLabel();
        c_tier_character = new javax.swing.JPanel();
        d_tier = new javax.swing.JPanel();
        gray_box = new javax.swing.JPanel();
        d_tier_title = new utils.helper.RopaItalicLabel();
        d_tier_character = new javax.swing.JPanel();
        home_path = new utils.helper.RopaLabel();
        ropaLabel1 = new utils.helper.RopaLabel();
        last_update = new utils.helper.RopaLabel();
        edit_tier = new utils.helper.RoundedPanel();
        ropaLabel2 = new utils.helper.RopaLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1281, 650));
        setResizable(false);

        root.setMaximumSize(new java.awt.Dimension(1281, 650));
        root.setMinimumSize(new java.awt.Dimension(1281, 650));
        root.setPreferredSize(new java.awt.Dimension(1281, 650));
        root.setLayout(new javax.swing.BoxLayout(root, javax.swing.BoxLayout.LINE_AXIS));

        main.setBackground(new java.awt.Color(8, 18, 38));
        main.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.setMinimumSize(new java.awt.Dimension(1281, 650));
        main.setPreferredSize(new java.awt.Dimension(1281, 650));
        main.setLayout(null);

        s_tier.setBackground(new java.awt.Color(8, 18, 38));
        s_tier.setPreferredSize(new java.awt.Dimension(1125, 261));
        s_tier.setLayout(null);

        red_box.setBackground(new java.awt.Color(221, 10, 0));
        red_box.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout red_boxLayout = new javax.swing.GroupLayout(red_box);
        red_box.setLayout(red_boxLayout);
        red_boxLayout.setHorizontalGroup(
            red_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        red_boxLayout.setVerticalGroup(
            red_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        s_tier.add(red_box);
        red_box.setBounds(14, 14, 24, 24);

        s_tier_title.setText("S TIER");
        s_tier_title.setFontSize(48.0F);
        s_tier.add(s_tier_title);
        s_tier_title.setBounds(50, 0, 140, 53);

        s_tier_character.setBackground(new java.awt.Color(8, 18, 38));
        s_tier_character.setPreferredSize(new java.awt.Dimension(1125, 190));
        s_tier_character.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        s_tier.add(s_tier_character);
        s_tier_character.setBounds(0, 65, 1125, 190);
        s_tier_character.getAccessibleContext().setAccessibleDescription("");

        main.add(s_tier);
        s_tier.setBounds(50, 120, 1125, 261);

        a_tier.setBackground(new java.awt.Color(8, 18, 38));
        a_tier.setPreferredSize(new java.awt.Dimension(1125, 261));
        a_tier.setLayout(null);

        orange_box.setBackground(new java.awt.Color(197, 90, 24));
        orange_box.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout orange_boxLayout = new javax.swing.GroupLayout(orange_box);
        orange_box.setLayout(orange_boxLayout);
        orange_boxLayout.setHorizontalGroup(
            orange_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        orange_boxLayout.setVerticalGroup(
            orange_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        a_tier.add(orange_box);
        orange_box.setBounds(14, 14, 24, 24);

        a_tier_title.setText("A TIER");
        a_tier_title.setFontSize(48.0F);
        a_tier.add(a_tier_title);
        a_tier_title.setBounds(50, 0, 140, 53);

        a_tier_character.setBackground(new java.awt.Color(8, 18, 38));
        a_tier_character.setPreferredSize(new java.awt.Dimension(1125, 190));
        a_tier_character.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        a_tier.add(a_tier_character);
        a_tier_character.setBounds(0, 65, 1125, 190);

        main.add(a_tier);
        a_tier.setBounds(50, 380, 1125, 261);

        b_tier.setBackground(new java.awt.Color(8, 18, 38));
        b_tier.setPreferredSize(new java.awt.Dimension(1125, 261));
        b_tier.setLayout(null);

        yellow_box.setBackground(new java.awt.Color(243, 167, 18));
        yellow_box.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout yellow_boxLayout = new javax.swing.GroupLayout(yellow_box);
        yellow_box.setLayout(yellow_boxLayout);
        yellow_boxLayout.setHorizontalGroup(
            yellow_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        yellow_boxLayout.setVerticalGroup(
            yellow_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        b_tier.add(yellow_box);
        yellow_box.setBounds(14, 14, 24, 24);

        b_tier_title.setText("B TIER");
        b_tier_title.setFontSize(48.0F);
        b_tier.add(b_tier_title);
        b_tier_title.setBounds(50, 0, 140, 53);

        b_tier_character.setBackground(new java.awt.Color(8, 18, 38));
        b_tier_character.setPreferredSize(new java.awt.Dimension(1125, 190));
        b_tier_character.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        b_tier.add(b_tier_character);
        b_tier_character.setBounds(0, 65, 1125, 190);

        main.add(b_tier);
        b_tier.setBounds(50, 640, 1125, 261);

        c_tier.setBackground(new java.awt.Color(8, 18, 38));
        c_tier.setPreferredSize(new java.awt.Dimension(1125, 261));
        c_tier.setLayout(null);

        green_box.setBackground(new java.awt.Color(0, 129, 72));
        green_box.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout green_boxLayout = new javax.swing.GroupLayout(green_box);
        green_box.setLayout(green_boxLayout);
        green_boxLayout.setHorizontalGroup(
            green_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        green_boxLayout.setVerticalGroup(
            green_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        c_tier.add(green_box);
        green_box.setBounds(14, 14, 24, 24);

        c_tier_title.setText("C TIER");
        c_tier_title.setFontSize(48.0F);
        c_tier.add(c_tier_title);
        c_tier_title.setBounds(50, 0, 140, 53);

        c_tier_character.setBackground(new java.awt.Color(8, 18, 38));
        c_tier_character.setPreferredSize(new java.awt.Dimension(1125, 190));
        c_tier_character.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        c_tier.add(c_tier_character);
        c_tier_character.setBounds(0, 65, 1125, 190);

        main.add(c_tier);
        c_tier.setBounds(50, 900, 1125, 261);

        d_tier.setBackground(new java.awt.Color(8, 18, 38));
        d_tier.setPreferredSize(new java.awt.Dimension(1125, 261));
        d_tier.setLayout(null);

        gray_box.setBackground(new java.awt.Color(145, 156, 178));
        gray_box.setPreferredSize(new java.awt.Dimension(24, 24));

        javax.swing.GroupLayout gray_boxLayout = new javax.swing.GroupLayout(gray_box);
        gray_box.setLayout(gray_boxLayout);
        gray_boxLayout.setHorizontalGroup(
            gray_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        gray_boxLayout.setVerticalGroup(
            gray_boxLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );

        d_tier.add(gray_box);
        gray_box.setBounds(14, 14, 24, 24);

        d_tier_title.setText("D TIER");
        d_tier_title.setFontSize(48.0F);
        d_tier.add(d_tier_title);
        d_tier_title.setBounds(50, 0, 140, 53);

        d_tier_character.setBackground(new java.awt.Color(8, 18, 38));
        d_tier_character.setPreferredSize(new java.awt.Dimension(1125, 190));
        d_tier_character.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        d_tier.add(d_tier_character);
        d_tier_character.setBounds(0, 65, 1125, 190);

        main.add(d_tier);
        d_tier.setBounds(50, 900, 1125, 261);

        home_path.setText("Home ");
        home_path.setFontSize(20.0F);
        home_path.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_pathMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                home_pathMouseEntered(evt);
            }
        });
        main.add(home_path);
        home_path.setBounds(50, 50, 60, 22);

        ropaLabel1.setText(" / Guide");
        ropaLabel1.setFontSize(20.0F);
        main.add(ropaLabel1);
        ropaLabel1.setBounds(95, 50, 70, 22);

        last_update.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        last_update.setText("Last updated:  2024-12-15 19:52:26");
        last_update.setFontSize(20.0F);
        main.add(last_update);
        last_update.setBounds(908, 50, 330, 22);

        edit_tier.setBackground(new java.awt.Color(123, 15, 58));
        edit_tier.setRoundBottomLeft(10);
        edit_tier.setRoundBottomRight(10);
        edit_tier.setRoundTopLeft(10);
        edit_tier.setRoundTopRight(10);
        edit_tier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edit_tierMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                edit_tierMouseEntered(evt);
            }
        });
        edit_tier.setLayout(new java.awt.GridBagLayout());

        ropaLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ropaLabel2.setText("Edit Tier");
        ropaLabel2.setFontSize(20.0F);
        edit_tier.add(ropaLabel2, new java.awt.GridBagConstraints());

        main.add(edit_tier);
        edit_tier.setBounds(1109, 90, 130, 30);

        root.add(main);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(root, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(root, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void home_pathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pathMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_home_pathMouseClicked

    private void home_pathMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pathMouseEntered
        // TODO add your handling code here:
        home_path.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_home_pathMouseEntered

    private void edit_tierMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_tierMouseEntered
        // TODO add your handling code here:
        edit_tier.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_edit_tierMouseEntered

    private void edit_tierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edit_tierMouseClicked
        // TODO add your handling code here:
        EditTier edit_tier = new EditTier();
        edit_tier.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_edit_tierMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Guide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Guide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Guide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Guide.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Guide().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel a_tier;
    private javax.swing.JPanel a_tier_character;
    private utils.helper.RopaItalicLabel a_tier_title;
    private javax.swing.JPanel b_tier;
    private javax.swing.JPanel b_tier_character;
    private utils.helper.RopaItalicLabel b_tier_title;
    private javax.swing.JPanel c_tier;
    private javax.swing.JPanel c_tier_character;
    private utils.helper.RopaItalicLabel c_tier_title;
    private javax.swing.JPanel d_tier;
    private javax.swing.JPanel d_tier_character;
    private utils.helper.RopaItalicLabel d_tier_title;
    private utils.helper.RoundedPanel edit_tier;
    private javax.swing.JPanel gray_box;
    private javax.swing.JPanel green_box;
    private utils.helper.RopaLabel home_path;
    private utils.helper.RopaLabel last_update;
    private javax.swing.JPanel main;
    private javax.swing.JPanel orange_box;
    private javax.swing.JPanel red_box;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RopaLabel ropaLabel2;
    private javax.swing.JPanel s_tier;
    private javax.swing.JPanel s_tier_character;
    private utils.helper.RopaItalicLabel s_tier_title;
    private javax.swing.JPanel yellow_box;
    // End of variables declaration//GEN-END:variables
}

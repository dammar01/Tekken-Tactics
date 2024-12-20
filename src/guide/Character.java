/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guide;

import home.Home;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Cursor;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import utils.helper.Db;
import utils.helper.ScrollBar;

/**
 *
 * @author Dammar
 */
public class Character extends javax.swing.JFrame {

    /**
     * Creates new form Guide
     */
    private List<String> convertArray(String string_data) {
        String cleanedData = string_data.replace("[", "").replace("]", "").replace("\"", "");
        String[] array = cleanedData.split(",\\s*");
        List<String> list = new ArrayList<>(Arrays.asList(array));
        return list;
    }

    public static Object[][] appendToObjectArray(Object[][] original, Object[] newRow) {
        Object[][] newArray = new Object[original.length + 1][];
        for (int i = 0; i < original.length; i++) {
            newArray[i] = original[i];
        }
        newArray[original.length] = newRow;
        return newArray;
    }

    public Character() throws SQLException {
        initComponents();

        Db db = new Db();
        db.connect();
        String selectQuery = "SELECT * FROM `movesheet` WHERE `character_id` = ?";
        ResultSet raw_movesheet = db.executeQuery(selectQuery, 7);
        Object[][] data_movesheet = {};
        while (raw_movesheet.next()) {
            ArrayList<ImageIcon> img_movesheet = new ArrayList<>();
            List<String> notation_data = convertArray(raw_movesheet.getString("notation"));
            for (String item : notation_data) {
                img_movesheet.add(new ImageIcon(getClass().getResource("/image/button/32x32/" + item + ".png")));
            }
            Object[] new_data = {
                img_movesheet,
                raw_movesheet.getString("name_move"),
                raw_movesheet.getString("damage"),
                raw_movesheet.getString("frame_startup"),
                raw_movesheet.getString("hit_properties"),
                raw_movesheet.getString("notes")
            };
            data_movesheet = appendToObjectArray(data_movesheet, new_data);
        }
        movesheetTable1.setData(data_movesheet);

        ScrollBar scrollPane = new ScrollBar(root);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBounds(0, 0, main.getWidth(), 650);
        add(scrollPane);
    }

    public Character(HashMap<String, String> raw) throws SQLException {
        initComponents();

        Db db = new Db();
        db.connect();
        String selectQuery = "SELECT * FROM `character` WHERE `id` = ? LIMIT 1";
        ResultSet data = db.executeQuery(selectQuery, raw.get("id"));

        while (data.next()) {
            guide_path.setText("/ " + data.getString("name"));
            String difficultyValue = data.getString("difficulty");

            if ("Easy".equals(difficultyValue)) {
                difficulty.setForeground(new Color(0, 129, 72));
            } else if ("Medium".equals(difficultyValue)) {
                difficulty.setForeground(new Color(243, 167, 18));
            } else if ("Hard".equals(difficultyValue)) {
                difficulty.setForeground(new Color(221, 10, 0));
            }
            difficulty.setText("Difficulty: " + data.getString("difficulty"));

            ImageIcon image = new ImageIcon(getClass().getResource("/image/character/128x128/dragunov.png"));
            if (data.getString("code") != "_") {
                image = new ImageIcon(getClass().getResource("/image/character/128x128/" + data.getString("code") + ".png"));
            }
            characterItem1.setImage(image);
            characterItem1.setTitle(data.getString("name"));

            evasiveness_value.setValue(data.getInt("evasiveness"));
            mobility_value.setValue(data.getInt("mobility"));
            throw_game_value.setValue(data.getInt("throw_game"));
            combo_damage_value.setValue(data.getInt("combo_damage"));
            wall_carry_value.setValue(data.getInt("wall_carry"));
        }

        // sample datasheet table
        String selectQueryTable = "SELECT * FROM `movesheet` WHERE `character_id` = ?";
        ResultSet raw_movesheet = db.executeQuery(selectQueryTable, raw.get("id"));
        Object[][] data_movesheet = {};
        while (raw_movesheet.next()) {
            ArrayList<ImageIcon> img_movesheet = new ArrayList<>();
            List<String> notation_data = convertArray(raw_movesheet.getString("notation"));
            for (String item : notation_data) {
                img_movesheet.add(new ImageIcon(getClass().getResource("/image/button/32x32/" + item + ".png")));
            }
            Object[] new_data = {
                img_movesheet,
                raw_movesheet.getString("name_move"),
                raw_movesheet.getString("damage"),
                raw_movesheet.getString("frame_startup"),
                raw_movesheet.getString("hit_properties"),
                raw_movesheet.getString("notes")
            };
            data_movesheet = appendToObjectArray(data_movesheet, new_data);
        }
        movesheetTable1.setData(data_movesheet);

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
        home_path = new utils.helper.RopaLabel();
        guide_path = new utils.helper.RopaLabel();
        guide_path1 = new utils.helper.RopaLabel();
        back = new utils.helper.RoundedPanel();
        ropaLabel1 = new utils.helper.RopaLabel();
        character_image = new javax.swing.JPanel();
        difficulty = new utils.helper.RopaLabel();
        characterItem1 = new utils.helper.CharacterItem();
        character_abilites = new utils.helper.RoundedPanel();
        character_abilites_title = new utils.helper.RopaLabel();
        evasiveness = new javax.swing.JPanel();
        evasiveness_title = new utils.helper.RopaItalicLabel();
        evasiveness_value = new utils.helper.CharacterAbilitiesValue();
        mobility = new javax.swing.JPanel();
        mobility_title = new utils.helper.RopaItalicLabel();
        mobility_value = new utils.helper.CharacterAbilitiesValue();
        throw_game = new javax.swing.JPanel();
        throw_game_title = new utils.helper.RopaItalicLabel();
        throw_game_value = new utils.helper.CharacterAbilitiesValue();
        combo_damage = new javax.swing.JPanel();
        combo_damage_title = new utils.helper.RopaItalicLabel();
        combo_damage_value = new utils.helper.CharacterAbilitiesValue();
        wall_carry = new javax.swing.JPanel();
        wall_carry_title = new utils.helper.RopaItalicLabel();
        wall_carry_value = new utils.helper.CharacterAbilitiesValue();
        movesheet = new utils.helper.RoundedPanel();
        movesheet_title = new utils.helper.RopaLabel();
        movesheetTable1 = new utils.helper.MovesheetTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(1281, 650));
        setResizable(false);

        root.setMinimumSize(new java.awt.Dimension(1281, 650));
        root.setPreferredSize(new java.awt.Dimension(1281, 979));
        root.setLayout(new javax.swing.BoxLayout(root, javax.swing.BoxLayout.LINE_AXIS));

        main.setBackground(new java.awt.Color(8, 18, 38));
        main.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.setMinimumSize(new java.awt.Dimension(1281, 650));
        main.setPreferredSize(new java.awt.Dimension(1281, 650));
        main.setLayout(null);

        home_path.setText("Home  / ");
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
        home_path.setBounds(50, 50, 62, 22);

        guide_path.setText("/ Dragunov");
        guide_path.setFontSize(20.0F);
        main.add(guide_path);
        guide_path.setBounds(170, 50, 140, 22);

        guide_path1.setText("Guide");
        guide_path1.setFontSize(20.0F);
        guide_path1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guide_path1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guide_path1MouseEntered(evt);
            }
        });
        main.add(guide_path1);
        guide_path1.setBounds(115, 50, 60, 22);

        back.setBackground(new java.awt.Color(123, 15, 58));
        back.setRoundBottomLeft(10);
        back.setRoundBottomRight(10);
        back.setRoundTopLeft(10);
        back.setRoundTopRight(10);
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
        });
        back.setLayout(new java.awt.GridBagLayout());

        ropaLabel1.setText("<<  Back");
        ropaLabel1.setFontSize(20.0F);
        back.add(ropaLabel1, new java.awt.GridBagConstraints());

        main.add(back);
        back.setBounds(1060, 50, 117, 33);

        character_image.setBackground(new java.awt.Color(8, 18, 38));

        difficulty.setForeground(new java.awt.Color(243, 167, 18));
        difficulty.setText("Difficulty: Medium");
        difficulty.setFontSize(18.0F);

        javax.swing.GroupLayout character_imageLayout = new javax.swing.GroupLayout(character_image);
        character_image.setLayout(character_imageLayout);
        character_imageLayout.setHorizontalGroup(
            character_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(character_imageLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(character_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(difficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(characterItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        character_imageLayout.setVerticalGroup(
            character_imageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(character_imageLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(difficulty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(characterItem1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        main.add(character_image);
        character_image.setBounds(70, 110, 219, 225);

        character_abilites.setBackground(new java.awt.Color(66, 21, 50));
        character_abilites.setRoundBottomLeft(20);
        character_abilites.setRoundBottomRight(20);
        character_abilites.setRoundTopLeft(20);
        character_abilites.setRoundTopRight(20);
        character_abilites.setLayout(null);

        character_abilites_title.setText("| Character Abilities");
        character_abilites.add(character_abilites_title);
        character_abilites_title.setBounds(20, 20, 190, 27);

        evasiveness.setBackground(new java.awt.Color(66, 21, 50));
        evasiveness.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        evasiveness_title.setText("Evasiveness");
        evasiveness_title.setFontSize(20.0F);
        evasiveness.add(evasiveness_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 105, -1));

        evasiveness_value.setValue(5);
        evasiveness.add(evasiveness_value, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        character_abilites.add(evasiveness);
        evasiveness.setBounds(20, 60, 405, 65);

        mobility.setBackground(new java.awt.Color(66, 21, 50));
        mobility.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mobility_title.setText("Mobility");
        mobility_title.setFontSize(20.0F);
        mobility.add(mobility_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 105, -1));

        mobility_value.setValue(6);
        mobility.add(mobility_value, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        character_abilites.add(mobility);
        mobility.setBounds(20, 130, 405, 65);

        throw_game.setBackground(new java.awt.Color(66, 21, 50));
        throw_game.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        throw_game_title.setText("Throw Game");
        throw_game_title.setFontSize(20.0F);
        throw_game.add(throw_game_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 105, -1));

        throw_game_value.setValue(8);
        throw_game.add(throw_game_value, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        character_abilites.add(throw_game);
        throw_game.setBounds(20, 200, 405, 65);

        combo_damage.setBackground(new java.awt.Color(66, 21, 50));
        combo_damage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        combo_damage_title.setText("Combo Damage");
        combo_damage_title.setFontSize(20.0F);
        combo_damage.add(combo_damage_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 130, -1));

        combo_damage_value.setValue(8);
        combo_damage.add(combo_damage_value, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        character_abilites.add(combo_damage);
        combo_damage.setBounds(450, 60, 405, 65);

        wall_carry.setBackground(new java.awt.Color(66, 21, 50));
        wall_carry.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        wall_carry_title.setText("Wall Carry");
        wall_carry_title.setFontSize(20.0F);
        wall_carry.add(wall_carry_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 5, 105, -1));

        wall_carry_value.setValue(9);
        wall_carry.add(wall_carry_value, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, -1, -1));

        character_abilites.add(wall_carry);
        wall_carry.setBounds(450, 130, 405, 65);

        main.add(character_abilites);
        character_abilites.setBounds(290, 130, 890, 290);

        movesheet.setBackground(new java.awt.Color(66, 21, 50));
        movesheet.setRoundBottomLeft(20);
        movesheet.setRoundBottomRight(20);
        movesheet.setRoundTopLeft(20);
        movesheet.setRoundTopRight(20);

        movesheet_title.setText("| Move sheet");

        javax.swing.GroupLayout movesheetLayout = new javax.swing.GroupLayout(movesheet);
        movesheet.setLayout(movesheetLayout);
        movesheetLayout.setHorizontalGroup(
            movesheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movesheetLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(movesheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(movesheetTable1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(movesheet_title, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        movesheetLayout.setVerticalGroup(
            movesheetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(movesheetLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(movesheet_title, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(movesheetTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        main.add(movesheet);
        movesheet.setBounds(80, 460, 1100, 450);

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
            .addComponent(root, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
        // TODO add your handling code here:
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_backMouseEntered

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        Guide guide = new Guide();
        guide.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void guide_path1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guide_path1MouseClicked
        // TODO add your handling code here:
        Guide guide = new Guide();
        guide.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_guide_path1MouseClicked

    private void home_pathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pathMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_home_pathMouseClicked

    private void guide_path1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guide_path1MouseEntered
        // TODO add your handling code here:
        guide_path1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_guide_path1MouseEntered

    private void home_pathMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pathMouseEntered
        // TODO add your handling code here:
        home_path.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_home_pathMouseEntered

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
            java.util.logging.Logger.getLogger(Character.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Character.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Character.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Character.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Character().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Character.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.helper.RoundedPanel back;
    private utils.helper.CharacterItem characterItem1;
    private utils.helper.RoundedPanel character_abilites;
    private utils.helper.RopaLabel character_abilites_title;
    private javax.swing.JPanel character_image;
    private javax.swing.JPanel combo_damage;
    private utils.helper.RopaItalicLabel combo_damage_title;
    private utils.helper.CharacterAbilitiesValue combo_damage_value;
    private utils.helper.RopaLabel difficulty;
    private javax.swing.JPanel evasiveness;
    private utils.helper.RopaItalicLabel evasiveness_title;
    private utils.helper.CharacterAbilitiesValue evasiveness_value;
    private utils.helper.RopaLabel guide_path;
    private utils.helper.RopaLabel guide_path1;
    private utils.helper.RopaLabel home_path;
    private javax.swing.JPanel main;
    private javax.swing.JPanel mobility;
    private utils.helper.RopaItalicLabel mobility_title;
    private utils.helper.CharacterAbilitiesValue mobility_value;
    private utils.helper.RoundedPanel movesheet;
    private utils.helper.MovesheetTable movesheetTable1;
    private utils.helper.RopaLabel movesheet_title;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    private javax.swing.JPanel throw_game;
    private utils.helper.RopaItalicLabel throw_game_title;
    private utils.helper.CharacterAbilitiesValue throw_game_value;
    private javax.swing.JPanel wall_carry;
    private utils.helper.RopaItalicLabel wall_carry_title;
    private utils.helper.CharacterAbilitiesValue wall_carry_value;
    // End of variables declaration//GEN-END:variables
}

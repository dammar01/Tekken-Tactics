/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package combolist;

import auth.Login;
import guide.*;
import home.Home;
import java.awt.Cursor;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import utils.helper.ComboListData;
import utils.helper.Db;
import utils.helper.ScrollBar;
import java.sql.ResultSet;
import utils.helper.MyComboData;
import utils.helper.Session;

/**
 *
 * @author Dammar
 */
public class Character extends javax.swing.JFrame {

    /**
     * Creates new form Guide
     */
    private int h = 0;

    private void reloadPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    private void updatePanel(JPanel panel, int x, int y, int w, int h) {
        panel.setPreferredSize(new java.awt.Dimension(w, h));
        panel.setBounds(x, y, w, h);
        reloadPanel(panel);
    }

    public Character() {
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();

        int h = 0;
        for (int i = 0; i < 10; i++) {
            ComboListData data = new ComboListData();
            h += data.getPreferredSize().height + 20;
            combolist_data.add(data);
        }
        combolist_data.setPreferredSize(new java.awt.Dimension(combolist_data.getWidth(), h));
        combolist_data.setBounds(combolist_data.getX(), combolist_data.getY(), combolist_data.getWidth(), h);
        reloadPanel(combolist_data);

        h += combolist_data.getY() + 20;
        java.awt.Dimension dim = getPreferredSize();
        dim.height = h;
        root.setPreferredSize(dim);
        reloadPanel(root);
        ScrollBar scrollPane = new ScrollBar(root);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBounds(0, 0, main.getWidth(), 650);
        add(scrollPane);
    }

    private void setImageCharacter(Db db, int id) {
        try {
            db.connect();
            String q = "SELECT name, code FROM `character` WHERE `id` = ? LIMIT 1";
            ResultSet res = db.executeQuery(q, id);
            if (res.next()) {
                ImageIcon im = new ImageIcon(getClass().getResource("/image/character/1170x263/" + res.getString("code") + ".png"));
                comboListHero1.setImage(im);
                comboListHero1.setTitle("Combo " + res.getString("name"));
                guide_path.setText("/ " + res.getString("name"));
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
    }

    private void setData(Db db, int id) {
        try {
            db.connect();
            String q = "SELECT \n"
                    + "    `combo_list`.*, \n"
                    + "    `favorite_combo`.`id` IS NOT NULL AS `is_favorite`, \n"
                    + "    `user`.`username`, \n"
                    + "    `character`.`name` AS `character_name`, \n"
                    + "    `character`.`code` AS `character_code`\n"
                    + "FROM \n"
                    + "    `combo_list`\n"
                    + "LEFT JOIN \n"
                    + "    `favorite_combo` \n"
                    + "    ON `combo_list`.`id` = `favorite_combo`.`combo_id` \n"
                    + "    AND `favorite_combo`.`user_id` = ?\n"
                    + "INNER JOIN \n"
                    + "    `user` \n"
                    + "    ON `combo_list`.`user_id` = `user`.`id`\n"
                    + "INNER JOIN \n"
                    + "    `character` \n"
                    + "    ON `combo_list`.`character_id` = `character`.`id`\n"
                    + "WHERE \n"
                    + "    `combo_list`.`character_id` = ?\n"
                    + "ORDER BY \n"
                    + "    `combo_list`.`id`;";
            int user_id = Session.getId();
            ResultSet resultSet = db.executeQuery(q, user_id, id);
            while (resultSet.next()) {
                ComboListData data = new ComboListData(
                        resultSet.getInt("id"),
                        resultSet.getString("character_name"),
                        resultSet.getString("name_move"),
                        resultSet.getString("username"),
                        resultSet.getTimestamp("date_created").toString().replaceAll("\\.0$", ""),
                        resultSet.getString("character_code"),
                        resultSet.getBoolean("is_favorite"),
                        resultSet.getString("notation"),
                        resultSet.getString("version"),
                        resultSet.getInt("total_damage"),
                        resultSet.getInt("total_hits")
                );
                h += data.getPreferredSize().height + 20;
                combolist_data.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.disconnect();
                combolist_data.setPreferredSize(new java.awt.Dimension(combolist_data.getWidth(), h));
                combolist_data.setBounds(combolist_data.getX(), combolist_data.getY(), combolist_data.getWidth(), h);
                reloadPanel(combolist_data);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Character(int id) {
        if (Session.getId() == null) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
            return;
        }
        initComponents();
        Db db = new Db();
        setImageCharacter(db, id);

        setData(db, id);
        java.awt.Dimension dim = getPreferredSize();
        h += combolist_data.getY() + 20;
        dim.height = h;
        root.setPreferredSize(dim);
        reloadPanel(root);
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
        guide_path1 = new utils.helper.RopaLabel();
        guide_path = new utils.helper.RopaLabel();
        comboListHero1 = new utils.helper.ComboListHero();
        combolist_data = new javax.swing.JPanel();
        back = new utils.helper.RoundedPanel();
        ropaLabel1 = new utils.helper.RopaLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1280, 650));
        setPreferredSize(new java.awt.Dimension(1295, 650));
        setResizable(false);

        root.setMaximumSize(new java.awt.Dimension(1280, 650));
        root.setMinimumSize(new java.awt.Dimension(1280, 650));
        root.setPreferredSize(new java.awt.Dimension(1280, 650));
        root.setLayout(new javax.swing.BoxLayout(root, javax.swing.BoxLayout.LINE_AXIS));

        main.setBackground(new java.awt.Color(8, 18, 38));
        main.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.setMinimumSize(new java.awt.Dimension(1281, 650));
        main.setPreferredSize(new java.awt.Dimension(1281, 1480));
        main.setLayout(null);

        home_path.setText("Home  / ");
        home_path.setFontSize(20.0F);
        home_path.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                home_pathMouseEntered(evt);
            }
        });
        main.add(home_path);
        home_path.setBounds(50, 50, 62, 22);

        guide_path1.setText("Combo List");
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
        guide_path1.setBounds(115, 50, 100, 22);

        guide_path.setText("/ Law");
        guide_path.setFontSize(20.0F);
        main.add(guide_path);
        guide_path.setBounds(210, 50, 160, 22);
        main.add(comboListHero1);
        comboListHero1.setBounds(50, 120, 1170, 286);

        combolist_data.setBackground(new java.awt.Color(8, 18, 38));
        combolist_data.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 10));
        main.add(combolist_data);
        combolist_data.setBounds(50, 450, 1170, 263);

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

        root.add(main);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(root, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(root, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        ComboList comboList = new ComboList();
        comboList.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
        // TODO add your handling code here:
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_backMouseEntered

    private void guide_path1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guide_path1MouseClicked
        // TODO add your handling code here:
        ComboList comboList = new ComboList();
        comboList.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_guide_path1MouseClicked

    private void guide_path1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guide_path1MouseEntered
        // TODO add your handling code here:
        guide_path1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_guide_path1MouseEntered

    private void home_pathMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pathMouseEntered
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
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
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (Session.getId() == null) {
                    Login login = new Login();
                    login.setVisible(true);
                } else {
                    new Character().setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.helper.RoundedPanel back;
    private utils.helper.ComboListHero comboListHero1;
    private javax.swing.JPanel combolist_data;
    private utils.helper.RopaLabel guide_path;
    private utils.helper.RopaLabel guide_path1;
    private utils.helper.RopaLabel home_path;
    private javax.swing.JPanel main;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    // End of variables declaration//GEN-END:variables
}

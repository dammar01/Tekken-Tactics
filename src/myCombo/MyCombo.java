/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package myCombo;

import auth.Login;
import combolist.*;
import guide.*;
import home.Home;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import utils.helper.ComboListData;
import utils.helper.Db;
import utils.helper.MyComboData;
import utils.helper.ScrollBar;
import utils.helper.Session;

/**
 *
 * @author Dammar
 */
public class MyCombo extends javax.swing.JFrame {

    /**
     * Creates new form Guide
     */
    private int total_data = 0;
    private String filter_character = "%%";
    private String filter_name_move = "%%";

    private void reloadPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    private void setupCharacterData() {
        Db db = new Db();
        try {
            db.connect();
            String selectQuery = "SELECT * FROM `character` WHERE `tier` != '-' ORDER BY `id`";
            ResultSet resultSet = db.executeQuery(selectQuery);
            list_character.addItem("All");
            while (resultSet.next()) {
                list_character.addItem(resultSet.getString("name"));
            }
            list_character.setSelectedIndex(0);
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

    private void changeScrollBar() {
        list_character.setUI(new BasicComboBoxUI() {
            @Override
            protected ComboPopup createPopup() {
                return new BasicComboPopup(comboBox) {
                    @Override
                    protected ScrollBar createScroller() {
                        ScrollBar scroller = new ScrollBar(list);
                        return scroller;
                    }
                };
            }
        });
    }

    private void setupDataMyCombo(String character_name, String name_move) {
        Db db = new Db();
        int user_id = Session.getId();
        try {
            db.connect();
            String selectQuery = "SELECT \n"
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
                    + "    `combo_list`.`user_id` = ?\n"
                    + "    AND `character`.`name` LIKE ?\n"
                    + "    AND `combo_list`.`name_move` LIKE ?\n"
                    + "ORDER BY \n"
                    + "    `combo_list`.`id`;";
            ResultSet resultSet = db.executeQuery(selectQuery, user_id, user_id, character_name, name_move);
            while (resultSet.next()) {
                total_data++;
                MyComboData item = new MyComboData(
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
                Dimension panel_dim = combolist_data.getPreferredSize();
                if (total_data == 1) {
                    panel_dim.height = 0;
                }
                panel_dim.height += item.getMaxHeight() + 20;
                combolist_data.setPreferredSize(new Dimension(panel_dim.width, panel_dim.height));
                combolist_data.setBounds(combolist_data.getBounds().x, combolist_data.getBounds().y, combolist_data.getBounds().width, panel_dim.height + 20);
                combolist_data.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (total_data == 0){
                    JOptionPane.showMessageDialog(this, "Not found any data");
                }
                reloadPanel(combolist_data);
                Dimension root_size = root.getPreferredSize();
                root.setPreferredSize(new Dimension(root_size.width, combolist_data.getBounds().height + combolist_data.getBounds().y));
                reloadPanel(root);
                db.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public MyCombo() {
        if (Session.getId() == null) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
            return;
        }
        initComponents();
        setupCharacterData();
        changeScrollBar();

        setupDataMyCombo(filter_character, filter_name_move);

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
        combolist_data = new javax.swing.JPanel();
        add_combo = new utils.helper.RoundedPanel();
        ropaLabel1 = new utils.helper.RopaLabel();
        list_character = new javax.swing.JComboBox<>();
        search = new utils.helper.RoundedPanel();
        search_placeholder = new utils.helper.RopaLabel();
        search_input = new javax.swing.JTextField();
        btn_search = new utils.helper.RoundedPanel();
        search_label = new utils.helper.RopaLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1281, 650));
        setPreferredSize(new java.awt.Dimension(1281, 650));
        setResizable(false);

        root.setMinimumSize(new java.awt.Dimension(1281, 650));
        root.setPreferredSize(new java.awt.Dimension(1281, 650));
        root.setLayout(new javax.swing.BoxLayout(root, javax.swing.BoxLayout.LINE_AXIS));

        main.setBackground(new java.awt.Color(8, 18, 38));
        main.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.setMinimumSize(new java.awt.Dimension(1281, 650));
        main.setPreferredSize(new java.awt.Dimension(1281, 650));
        main.setLayout(null);

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
        home_path.setBounds(50, 50, 50, 22);

        guide_path1.setText("/ My Combo");
        guide_path1.setFontSize(20.0F);
        main.add(guide_path1);
        guide_path1.setBounds(100, 50, 110, 22);

        combolist_data.setBackground(new java.awt.Color(8, 18, 38));
        combolist_data.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 10));
        main.add(combolist_data);
        combolist_data.setBounds(50, 170, 1170, 263);

        add_combo.setBackground(new java.awt.Color(123, 15, 58));
        add_combo.setRoundBottomLeft(10);
        add_combo.setRoundBottomRight(10);
        add_combo.setRoundTopLeft(10);
        add_combo.setRoundTopRight(10);
        add_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                add_comboMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                add_comboMouseEntered(evt);
            }
        });
        add_combo.setLayout(new java.awt.GridBagLayout());

        ropaLabel1.setText("+  Add Combo");
        ropaLabel1.setFontSize(20.0F);
        add_combo.add(ropaLabel1, new java.awt.GridBagConstraints());

        main.add(add_combo);
        add_combo.setBounds(1080, 50, 130, 33);

        list_character.setBackground(new java.awt.Color(217, 217, 217));
        list_character.setFont(ropaLabel1.getFont());
        list_character.setForeground(new java.awt.Color(0, 0, 0));
        list_character.setBorder(null);
        list_character.setFocusable(false);
        list_character.setPreferredSize(new java.awt.Dimension(140, 30));
        list_character.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_characterActionPerformed(evt);
            }
        });
        main.add(list_character);
        list_character.setBounds(50, 110, 170, 30);

        search.setBackground(new java.awt.Color(217, 217, 217));
        search.setRoundBottomLeft(10);
        search.setRoundBottomRight(10);
        search.setRoundTopLeft(10);
        search.setRoundTopRight(10);
        search.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        search_placeholder.setForeground(new java.awt.Color(109, 109, 109));
        search_placeholder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/search_icon.png"))); // NOI18N
        search_placeholder.setText("Search Combo");
        search_placeholder.setFontSize(18.0F);
        search_placeholder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                search_placeholderMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                search_placeholderMouseEntered(evt);
            }
        });
        search.add(search_placeholder, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, -1));

        search_input.setBackground(new java.awt.Color(217, 217, 217));
        search_input.setFont(ropaLabel1.getFont());
        search_input.setForeground(new java.awt.Color(0, 0, 0));
        search_input.setBorder(null);
        search_input.setHighlighter(null);
        search_input.setPreferredSize(new java.awt.Dimension(960, 30));
        search_input.setRequestFocusEnabled(false);
        search_input.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                search_inputFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                search_inputFocusLost(evt);
            }
        });
        search_input.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                search_inputKeyTyped(evt);
            }
        });
        search.add(search_input, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 850, 30));

        main.add(search);
        search.setBounds(240, 110, 870, 30);

        btn_search.setBackground(new java.awt.Color(52, 255, 67));
        btn_search.setRoundBottomLeft(10);
        btn_search.setRoundBottomRight(10);
        btn_search.setRoundTopLeft(10);
        btn_search.setRoundTopRight(10);
        btn_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_searchMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_searchMouseEntered(evt);
            }
        });

        search_label.setForeground(new java.awt.Color(0, 0, 0));
        search_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/search_icon.png"))); // NOI18N
        search_label.setText("Search");
        search_label.setFontSize(16.0F);
        btn_search.add(search_label);

        main.add(btn_search);
        btn_search.setBounds(1120, 110, 90, 30);

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

    private void list_characterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_list_characterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_list_characterActionPerformed

    private void search_inputFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_inputFocusGained
        // TODO add your handling code here:
        if (search_input.getText().isEmpty())
            search_placeholder.setVisible(true);
        else
            search_placeholder.setVisible(false);
    }//GEN-LAST:event_search_inputFocusGained

    private void search_inputFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_search_inputFocusLost
        // TODO add your handling code here:
        if (search_input.getText().isEmpty())
            search_placeholder.setVisible(true);
    }//GEN-LAST:event_search_inputFocusLost

    private void search_placeholderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_placeholderMouseClicked
        // TODO add your handling code here:
        search_input.setFocusable(true);
    }//GEN-LAST:event_search_placeholderMouseClicked

    private void search_placeholderMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_search_placeholderMouseEntered
        // TODO add your handling code here:
        search_placeholder.setCursor(search_input.getCursor());
    }//GEN-LAST:event_search_placeholderMouseEntered

    private void search_inputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_inputKeyTyped
        // TODO add your handling code here:
        String input = search_input.getText() + evt.getKeyChar();
        if (input.isEmpty())
            search_placeholder.setVisible(true);
        else
            search_placeholder.setVisible(false);
    }//GEN-LAST:event_search_inputKeyTyped

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

    private void add_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_comboMouseClicked
        // TODO add your handling code here:
        AddCombo addCombo = new AddCombo();
        addCombo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_add_comboMouseClicked

    private void add_comboMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_add_comboMouseEntered
        // TODO add your handling code here:
        add_combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_add_comboMouseEntered

    private void btn_searchMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseEntered
        // TODO add your handling code here:
        btn_search.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_searchMouseEntered

    private void btn_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_searchMouseClicked
        // TODO add your handling code here:
        this.filter_character = list_character.getSelectedItem().toString().equals("All") ? "%%" : "%" + list_character.getSelectedItem().toString() + "%";
        this.filter_name_move = "%" + search_input.getText() + "%";
        combolist_data.removeAll();
        total_data = 0;
        setupDataMyCombo(filter_character, filter_name_move);
        reloadPanel(combolist_data);
        reloadPanel(root);
    }//GEN-LAST:event_btn_searchMouseClicked

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
            java.util.logging.Logger.getLogger(MyCombo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyCombo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyCombo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyCombo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                    new MyCombo().setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.helper.RoundedPanel add_combo;
    private utils.helper.RoundedPanel btn_search;
    private javax.swing.JPanel combolist_data;
    private utils.helper.RopaLabel guide_path1;
    private utils.helper.RopaLabel home_path;
    private javax.swing.JComboBox<String> list_character;
    private javax.swing.JPanel main;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RoundedPanel search;
    private javax.swing.JTextField search_input;
    private utils.helper.RopaLabel search_label;
    private utils.helper.RopaLabel search_placeholder;
    // End of variables declaration//GEN-END:variables
}

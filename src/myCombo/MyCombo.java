/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package myCombo;

import combolist.*;
import guide.*;
import home.Home;
import java.awt.Cursor;
import javax.swing.JFrame;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import utils.helper.ComboListData;
import utils.helper.ScrollBar;

/**
 *
 * @author Dammar
 */
public class MyCombo extends javax.swing.JFrame {

    /**
     * Creates new form Guide
     */
    private void reloadPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    public MyCombo() {
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1281, 650));
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
        combolist_data.setBounds(50, 450, 1170, 263);

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
        list_character.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        list_character.setBorder(null);
        list_character.setFocusable(false);
        list_character.setPreferredSize(new java.awt.Dimension(140, 30));
        list_character.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                list_characterActionPerformed(evt);
            }
        });
        main.add(list_character);
        list_character.setBounds(50, 110, 140, 30);

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
        search.add(search_input, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 0, 990, 30));

        main.add(search);
        search.setBounds(210, 110, 1000, 30);

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
                new MyCombo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.helper.RoundedPanel add_combo;
    private javax.swing.JPanel combolist_data;
    private utils.helper.RopaLabel guide_path1;
    private utils.helper.RopaLabel home_path;
    private javax.swing.JComboBox<String> list_character;
    private javax.swing.JPanel main;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RoundedPanel search;
    private javax.swing.JTextField search_input;
    private utils.helper.RopaLabel search_placeholder;
    // End of variables declaration//GEN-END:variables
}

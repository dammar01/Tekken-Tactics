/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package home;

import auth.Login;
import combolist.ComboList;
import guide.Guide;
import help.Help;
import java.awt.Cursor;
import javax.swing.*;
import myAccount.MyAccount;
import myCombo.MyCombo;
import utils.helper.Session;

/**
 *
 * @author Dammar
 */
public class Home extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public Home() {
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        menu = new javax.swing.JPanel();
        guide = new utils.helper.RoundedPanel();
        ropaLabel3 = new utils.helper.RopaLabel();
        jLabel2 = new javax.swing.JLabel();
        combo_list = new utils.helper.RoundedPanel();
        ropaLabel4 = new utils.helper.RopaLabel();
        jLabel3 = new javax.swing.JLabel();
        my_combo = new utils.helper.RoundedPanel();
        ropaLabel5 = new utils.helper.RopaLabel();
        jLabel4 = new javax.swing.JLabel();
        ropaLabel1 = new utils.helper.RopaLabel();
        jLabel1 = new javax.swing.JLabel();
        ropaLabel2 = new utils.helper.RopaLabel();
        bg = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1281, 650));
        setResizable(false);

        main.setMinimumSize(new java.awt.Dimension(1281, 650));
        main.setPreferredSize(new java.awt.Dimension(1281, 650));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        menu.setOpaque(false);
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        guide.setBackground(new java.awt.Color(123, 15, 58));
        guide.setPreferredSize(new java.awt.Dimension(280, 280));
        guide.setRoundBottomLeft(50);
        guide.setRoundBottomRight(50);
        guide.setRoundTopLeft(50);
        guide.setRoundTopRight(50);
        guide.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guideMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                guideMouseEntered(evt);
            }
        });
        guide.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ropaLabel3.setText("Guide");
        ropaLabel3.setFontSize(38.0F);
        guide.add(ropaLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/guide.png"))); // NOI18N
        guide.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        menu.add(guide, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, -1));

        combo_list.setBackground(new java.awt.Color(123, 15, 58));
        combo_list.setPreferredSize(new java.awt.Dimension(280, 280));
        combo_list.setRoundBottomLeft(50);
        combo_list.setRoundBottomRight(50);
        combo_list.setRoundTopLeft(50);
        combo_list.setRoundTopRight(50);
        combo_list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                combo_listMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                combo_listMouseEntered(evt);
            }
        });
        combo_list.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ropaLabel4.setText("Combo List");
        ropaLabel4.setFontSize(38.0F);
        combo_list.add(ropaLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/combo_list.png"))); // NOI18N
        combo_list.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        menu.add(combo_list, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, -1, -1));

        my_combo.setBackground(new java.awt.Color(123, 15, 58));
        my_combo.setPreferredSize(new java.awt.Dimension(280, 280));
        my_combo.setRoundBottomLeft(50);
        my_combo.setRoundBottomRight(50);
        my_combo.setRoundTopLeft(50);
        my_combo.setRoundTopRight(50);
        my_combo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                my_comboMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                my_comboMouseEntered(evt);
            }
        });
        my_combo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ropaLabel5.setText("My Combo");
        ropaLabel5.setFontSize(38.0F);
        my_combo.add(ropaLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/my_combo.png"))); // NOI18N
        my_combo.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, -1, -1));

        menu.add(my_combo, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 230, -1, -1));

        ropaLabel1.setText("Tactic's");
        ropaLabel1.setFontSize(24.0F);
        menu.add(ropaLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 170, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/utils/logo.png"))); // NOI18N
        menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, 40));

        ropaLabel2.setText("Welcome to");
        ropaLabel2.setFontSize(24.0F);
        menu.add(ropaLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, -1, -1));

        main.add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1390, 780));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/utils/bg.png"))); // NOI18N
        bg.setMaximumSize(new java.awt.Dimension(0, 0));
        bg.setMinimumSize(new java.awt.Dimension(0, 0));
        main.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, -1, 1020));

        jMenu2.setText("Account");

        jMenuItem1.setText("My Account");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        MyAccount myAccount = new MyAccount();
        myAccount.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void guideMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guideMouseEntered
        // TODO add your handling code here:
        guide.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_guideMouseEntered

    private void combo_listMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_listMouseEntered
        // TODO add your handling code here:
        combo_list.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_combo_listMouseEntered

    private void my_comboMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_my_comboMouseEntered
        // TODO add your handling code here:
        my_combo.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_my_comboMouseEntered

    private void guideMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guideMouseClicked
        // TODO add your handling code here:
        Guide guide = new Guide();
        guide.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_guideMouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        // TODO add your handling code here:
        Help help = new Help();
        help.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenu3MouseClicked

    private void combo_listMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_combo_listMouseClicked
        // TODO add your handling code here:
        ComboList comboList = new ComboList();
        comboList.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_combo_listMouseClicked

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void my_comboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_my_comboMouseClicked
        // TODO add your handling code here:
        MyCombo myCombo = new MyCombo();
        myCombo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_my_comboMouseClicked

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (Session.getId() == null) {
                    Login login = new Login();
                    login.setVisible(true);
                } else {
                    new Home().setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private utils.helper.RoundedPanel combo_list;
    private utils.helper.RoundedPanel guide;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel main;
    private javax.swing.JPanel menu;
    private utils.helper.RoundedPanel my_combo;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RopaLabel ropaLabel2;
    private utils.helper.RopaLabel ropaLabel3;
    private utils.helper.RopaLabel ropaLabel4;
    private utils.helper.RopaLabel ropaLabel5;
    // End of variables declaration//GEN-END:variables
}

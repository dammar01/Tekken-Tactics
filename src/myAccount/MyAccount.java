/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package myAccount;

import editProfile.Edit_profile;
import home.Home;
import javax.swing.*;
import java.awt.Cursor;

/**
 *
 * @author Dammar
 */
public class MyAccount extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public MyAccount() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
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
        myCombo = new utils.helper.RoundedPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        editProfile = new utils.helper.RoundedPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FavCombo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        roundedPanel1 = new utils.helper.RoundedPanel();
        jLabel4 = new javax.swing.JLabel();
        char1 = new javax.swing.JLabel();
        nickname = new javax.swing.JLabel();
        favChar = new javax.swing.JLabel();
        roundedPanel2 = new utils.helper.RoundedPanel();
        jLabel5 = new javax.swing.JLabel();
        char2 = new javax.swing.JLabel();
        roundedPanel3 = new utils.helper.RoundedPanel();
        jLabel7 = new javax.swing.JLabel();
        combo1 = new javax.swing.JLabel();
        roundedPanel4 = new utils.helper.RoundedPanel();
        jLabel8 = new javax.swing.JLabel();
        combo2 = new javax.swing.JLabel();
        home_path = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        ropaLabel1 = new utils.helper.RopaLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1281, 650));

        main.setBackground(new java.awt.Color(8, 18, 38));
        main.setMinimumSize(new java.awt.Dimension(1281, 650));
        main.setPreferredSize(new java.awt.Dimension(1281, 650));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        myCombo.setBackground(new java.awt.Color(123, 15, 58));
        myCombo.setPreferredSize(new java.awt.Dimension(280, 280));
        myCombo.setRoundBottomLeft(50);
        myCombo.setRoundBottomRight(50);
        myCombo.setRoundTopLeft(50);
        myCombo.setRoundTopRight(50);
        myCombo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Yu Gothic UI", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("My combo");
        myCombo.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 60, 100, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/My_account.png"))); // NOI18N
        myCombo.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 110, 80));

        main.add(myCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 440, 280, 150));

        editProfile.setBackground(new java.awt.Color(123, 15, 58));
        editProfile.setPreferredSize(new java.awt.Dimension(280, 280));
        editProfile.setRoundBottomLeft(50);
        editProfile.setRoundBottomRight(50);
        editProfile.setRoundTopLeft(50);
        editProfile.setRoundTopRight(50);
        editProfile.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                editProfileComponentRemoved(evt);
            }
        });
        editProfile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                editProfileMouseClicked(evt);
            }
        });
        editProfile.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setBackground(new java.awt.Color(255, 255, 255));
        jLabel17.setFont(new java.awt.Font("Yu Gothic UI", 1, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Edit Profile");
        editProfile.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 60, 90, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/edit_profile.png"))); // NOI18N
        editProfile.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, -1, -1));

        main.add(editProfile, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 440, 290, 150));

        FavCombo.setBackground(new java.awt.Color(255, 255, 255));
        FavCombo.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        FavCombo.setForeground(new java.awt.Color(255, 255, 255));
        FavCombo.setText("Favorite Combo");
        main.add(FavCombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 220, 150, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/character/128x128/dragunov_circle.png"))); // NOI18N
        main.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 40, -1, -1));

        roundedPanel1.setBackground(new java.awt.Color(123, 15, 58));
        roundedPanel1.setRoundBottomLeft(20);
        roundedPanel1.setRoundBottomRight(20);
        roundedPanel1.setRoundTopLeft(20);
        roundedPanel1.setRoundTopRight(20);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/tiny_drogunov(1).png"))); // NOI18N

        char1.setBackground(new java.awt.Color(255, 255, 255));
        char1.setFont(new java.awt.Font("Yu Gothic UI", 1, 17)); // NOI18N
        char1.setForeground(new java.awt.Color(255, 255, 255));
        char1.setText("Dragunov");

        javax.swing.GroupLayout roundedPanel1Layout = new javax.swing.GroupLayout(roundedPanel1);
        roundedPanel1.setLayout(roundedPanel1Layout);
        roundedPanel1Layout.setHorizontalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(char1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );
        roundedPanel1Layout.setVerticalGroup(
            roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel1Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(roundedPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(char1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        main.add(roundedPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 290, 50));

        nickname.setBackground(new java.awt.Color(255, 255, 255));
        nickname.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        nickname.setForeground(new java.awt.Color(255, 255, 255));
        nickname.setText("Nickname");
        main.add(nickname, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 170, 100, 30));

        favChar.setBackground(new java.awt.Color(255, 255, 255));
        favChar.setFont(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        favChar.setForeground(new java.awt.Color(255, 255, 255));
        favChar.setText("Favorite Character");
        main.add(favChar, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 170, 30));

        roundedPanel2.setBackground(new java.awt.Color(123, 15, 58));
        roundedPanel2.setRoundBottomLeft(20);
        roundedPanel2.setRoundBottomRight(20);
        roundedPanel2.setRoundTopLeft(20);
        roundedPanel2.setRoundTopRight(20);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/tiny_drogunov(1).png"))); // NOI18N

        char2.setBackground(new java.awt.Color(255, 255, 255));
        char2.setFont(new java.awt.Font("Yu Gothic UI", 1, 17)); // NOI18N
        char2.setForeground(new java.awt.Color(255, 255, 255));
        char2.setText("Dragunov");

        javax.swing.GroupLayout roundedPanel2Layout = new javax.swing.GroupLayout(roundedPanel2);
        roundedPanel2.setLayout(roundedPanel2Layout);
        roundedPanel2Layout.setHorizontalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(char2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(87, Short.MAX_VALUE))
        );
        roundedPanel2Layout.setVerticalGroup(
            roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel2Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(roundedPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(char2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        main.add(roundedPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 330, -1, -1));

        roundedPanel3.setBackground(new java.awt.Color(123, 15, 58));
        roundedPanel3.setRoundBottomLeft(20);
        roundedPanel3.setRoundBottomRight(20);
        roundedPanel3.setRoundTopLeft(20);
        roundedPanel3.setRoundTopRight(20);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/tiny_drogunov(1).png"))); // NOI18N

        combo1.setBackground(new java.awt.Color(255, 255, 255));
        combo1.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        combo1.setForeground(new java.awt.Color(255, 255, 255));
        combo1.setText("General Combo (Multiple Launcher)");

        javax.swing.GroupLayout roundedPanel3Layout = new javax.swing.GroupLayout(roundedPanel3);
        roundedPanel3.setLayout(roundedPanel3Layout);
        roundedPanel3Layout.setHorizontalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        roundedPanel3Layout.setVerticalGroup(
            roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel3Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(roundedPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        main.add(roundedPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 270, 290, -1));

        roundedPanel4.setBackground(new java.awt.Color(123, 15, 58));
        roundedPanel4.setRoundBottomLeft(20);
        roundedPanel4.setRoundBottomRight(20);
        roundedPanel4.setRoundTopLeft(20);
        roundedPanel4.setRoundTopRight(20);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/tiny_drogunov(1).png"))); // NOI18N

        combo2.setBackground(new java.awt.Color(255, 255, 255));
        combo2.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        combo2.setForeground(new java.awt.Color(255, 255, 255));
        combo2.setText("General Combo (Multiple Launcher)");

        javax.swing.GroupLayout roundedPanel4Layout = new javax.swing.GroupLayout(roundedPanel4);
        roundedPanel4.setLayout(roundedPanel4Layout);
        roundedPanel4Layout.setHorizontalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundedPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(26, Short.MAX_VALUE))
        );
        roundedPanel4Layout.setVerticalGroup(
            roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundedPanel4Layout.createSequentialGroup()
                .addContainerGap(8, Short.MAX_VALUE)
                .addGroup(roundedPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addContainerGap())
        );

        main.add(roundedPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 330, -1, -1));

        home_path.setBackground(new java.awt.Color(255, 255, 255));
        home_path.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        home_path.setForeground(new java.awt.Color(255, 255, 255));
        home_path.setText("Home");
        home_path.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home_pathMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                home_pathMouseEntered(evt);
            }
        });
        main.add(home_path, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 40, 30));

        jLabel19.setBackground(new java.awt.Color(255, 255, 255));
        jLabel19.setFont(new java.awt.Font("Yu Gothic UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("/ My Account");
        main.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 110, 30));

        ropaLabel1.setText("ropaLabel1");
        main.add(ropaLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, -1, -1));

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
        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.DEFAULT_SIZE, 1293, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(main, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void editProfileComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_editProfileComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_editProfileComponentRemoved

    private void editProfileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editProfileMouseClicked
        // TODO add your handling code here:
        Edit_profile edit_profile = new Edit_profile();
        edit_profile.setVisible(true);
        this.dispose();
                
                
    }//GEN-LAST:event_editProfileMouseClicked

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

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
            java.util.logging.Logger.getLogger(MyAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new MyAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FavCombo;
    private javax.swing.JLabel char1;
    private javax.swing.JLabel char2;
    private javax.swing.JLabel combo1;
    private javax.swing.JLabel combo2;
    private utils.helper.RoundedPanel editProfile;
    private javax.swing.JLabel favChar;
    private javax.swing.JLabel home_path;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel main;
    private utils.helper.RoundedPanel myCombo;
    private javax.swing.JLabel nickname;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RoundedPanel roundedPanel1;
    private utils.helper.RoundedPanel roundedPanel2;
    private utils.helper.RoundedPanel roundedPanel3;
    private utils.helper.RoundedPanel roundedPanel4;
    // End of variables declaration//GEN-END:variables
}

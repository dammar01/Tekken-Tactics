/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package help;

import auth.DatabaseConnection;
import guide.*;
import home.Home;
import java.awt.BorderLayout;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import utils.helper.Db;
import utils.helper.ScrollBar;
import java.sql.Connection;
import java.sql.Statement;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import utils.helper.RopaLabel;
import utils.helper.RoundedPanel;

/**
 *
 * @author Dammar
 */
public class Faq extends javax.swing.JFrame {


    public Faq() {
        initComponents();

        ScrollBar scrollPane = new ScrollBar(root);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBounds(0, 0, main.getWidth(), 650);
        add(scrollPane);

        loadDataFromDatabase();

    }

    private void loadDataFromDatabase() {
        try {
            Connection connection = DatabaseConnection.getConnection();
            String query = "SELECT title, content FROM faq "; // Query database
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            jPanelContainer.setLayout(new BoxLayout(jPanelContainer, BoxLayout.Y_AXIS));

            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");

                // Panel individu untuk setiap data
                RoundedPanel panelCard = new RoundedPanel();
                panelCard.setLayout(new GridLayout(2, 1));
                panelCard.setBackground(new Color(123, 15, 58));

                panelCard.setRoundBottomLeft(20);
                panelCard.setRoundBottomRight(20);
                panelCard.setRoundTopLeft(20);
                panelCard.setRoundTopRight(20);

                panelCard.setBorder(new EmptyBorder(20, 20, 20, 20));

                // Label title
                RopaLabel lblTitle = new RopaLabel(title);
                lblTitle.setForeground(Color.WHITE);
                lblTitle.setFontSize(20);

                // Label content
                RopaLabel lblContent = new RopaLabel("<html>" + content + "</html>");
                lblContent.setForeground(Color.WHITE);
                lblContent.setFontSize(14);

                // Tambahkan komponen ke panel
                panelCard.add(lblTitle);
                panelCard.add(lblContent);

                // Tambahkan panelCard ke jPanelContainer
                jPanelContainer.add(panelCard);
                jPanelContainer.add(Box.createRigidArea(new Dimension(0, 10))); // Jarak antar card

            }

            // Refresh tampilan JPanel
            jPanelContainer.revalidate();
            jPanelContainer.repaint();
            statement.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        root = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        home_path = new utils.helper.RopaLabel();
        FAQ_path = new utils.helper.RopaLabel();
        Help_path = new utils.helper.RopaLabel();
        back = new utils.helper.RoundedPanel();
        ropaLabel1 = new utils.helper.RopaLabel();
        ropaLabel2 = new utils.helper.RopaLabel();
        jPanelContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(1295, 650));
        setResizable(false);

        root.setMinimumSize(new java.awt.Dimension(1281, 650));
        root.setPreferredSize(new java.awt.Dimension(1281, 1700));
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
        home_path.setBounds(90, 50, 60, 22);

        FAQ_path.setText("/ FAQ");
        FAQ_path.setFontSize(20.0F);
        main.add(FAQ_path);
        FAQ_path.setBounds(190, 50, 140, 22);

        Help_path.setText("/ Help");
        Help_path.setFontSize(20.0F);
        Help_path.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Help_pathMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Help_pathMouseEntered(evt);
            }
        });
        main.add(Help_path);
        Help_path.setBounds(140, 50, 50, 22);

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

        ropaLabel2.setText("FAQ");
        main.add(ropaLabel2);
        ropaLabel2.setBounds(100, 130, 99, 27);

        jPanelContainer.setBackground(new java.awt.Color(8, 18, 38));
        jPanelContainer.setToolTipText("");
        jPanelContainer.setMinimumSize(new java.awt.Dimension(40, 40));
        jPanelContainer.setPreferredSize(new java.awt.Dimension(40, 40));
        jPanelContainer.setLayout(new javax.swing.BoxLayout(jPanelContainer, javax.swing.BoxLayout.LINE_AXIS));
        main.add(jPanelContainer);
        jPanelContainer.setBounds(100, 180, 1080, 700);
        jPanelContainer.getAccessibleContext().setAccessibleName("");

        root.add(main);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(root, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(root, javax.swing.GroupLayout.DEFAULT_SIZE, 798, Short.MAX_VALUE)
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
        Help help = new Help();
        help.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_backMouseClicked

    private void Help_pathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Help_pathMouseClicked
        // TODO add your handling code here:
        Help help = new Help();
        help.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_Help_pathMouseClicked

    private void home_pathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home_pathMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_home_pathMouseClicked

    private void Help_pathMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Help_pathMouseEntered
        // TODO add your handling code here:
        Help_path.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_Help_pathMouseEntered

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
            java.util.logging.Logger.getLogger(Faq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Faq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Faq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Faq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Faq().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.helper.RopaLabel FAQ_path;
    private utils.helper.RopaLabel Help_path;
    private utils.helper.RoundedPanel back;
    private utils.helper.RopaLabel home_path;
    private javax.swing.JPanel jPanelContainer;
    private javax.swing.JPanel main;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RopaLabel ropaLabel2;
    // End of variables declaration//GEN-END:variables
}

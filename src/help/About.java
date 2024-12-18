/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package help;

import myCombo.*;
import combolist.*;
import guide.*;
import home.Home;
import java.awt.Cursor;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.*;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import utils.helper.ComboListData;
import utils.helper.Db;
import utils.helper.ScrollBar;

/**
 *
 * @author Dammar
 */
public class About extends javax.swing.JFrame {

    /**
     * Creates new form Guide
     */
    private void reloadPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    public About() {
        initComponents();
        Db db = new Db();
        try {
            db.connect();
            String selectQuery = "SELECT * FROM `help`";
            ResultSet resultSet = db.executeQuery(selectQuery);
            String title = "";
            while (resultSet.next()) {
                title = resultSet.getString("tipe");
                String tipe = resultSet.getString("tipe");
                String content = resultSet.getString("content");
                content = content.replace("¬†¬†¬†¬†¬†¬†", "          ");
                content = content.replace("‚Ä®", "\n");
                int line = resultSet.getInt("line");
                int is_new_line = resultSet.getInt("is_new_line");
                int is_title = resultSet.getInt("is_title");
                ropaLabelArea1.setText(content);
            }
            ropaLabel1.setText(title);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
        about_path = new utils.helper.RopaLabel();
        help_path = new utils.helper.RopaLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ropaLabelArea1 = new utils.helper.RopaLabelArea();
        ropaLabel1 = new utils.helper.RopaLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(1281, 650));
        setResizable(false);

        root.setMinimumSize(new java.awt.Dimension(1281, 650));
        root.setPreferredSize(new java.awt.Dimension(1281, 863));
        root.setLayout(new javax.swing.BoxLayout(root, javax.swing.BoxLayout.LINE_AXIS));

        main.setBackground(new java.awt.Color(8, 18, 38));
        main.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.setMinimumSize(new java.awt.Dimension(1281, 650));
        main.setPreferredSize(new java.awt.Dimension(1281, 863));
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

        about_path.setText("/ About");
        about_path.setFontSize(20.0F);
        main.add(about_path);
        about_path.setBounds(150, 50, 110, 22);

        help_path.setText("/ Help");
        help_path.setFontSize(20.0F);
        main.add(help_path);
        help_path.setBounds(100, 50, 110, 22);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        ropaLabelArea1.setBackground(new java.awt.Color(8, 18, 38));
        ropaLabelArea1.setColumns(20);
        ropaLabelArea1.setRows(5);
        ropaLabelArea1.setText("");
        ropaLabelArea1.setCaretColor(new java.awt.Color(8, 18, 38));
        jScrollPane1.setViewportView(ropaLabelArea1);

        main.add(jScrollPane1);
        jScrollPane1.setBounds(50, 140, 1180, 690);

        ropaLabel1.setText("Title");
        main.add(ropaLabel1);
        ropaLabel1.setBounds(50, 90, 290, 27);

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
            .addComponent(root, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new About().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.helper.RopaLabel about_path;
    private utils.helper.RopaLabel help_path;
    private utils.helper.RopaLabel home_path;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel main;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RopaLabelArea ropaLabelArea1;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package help;

import home.Home;
import java.awt.Color;
import java.sql.ResultSet;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import utils.helper.Db;
import utils.helper.ScrollBar;
import utils.helper.Session;

/**
 *
 * @author Dammar
 */
public class CrudFaq extends javax.swing.JFrame {

    private Object[][] data_faq = {};
    private boolean isSelected = false;
    private int rowSelected = -1;

    public static Object[][] appendToObjectArray(Object[][] original, Object[] newRow) {
        Object[][] newArray = new Object[original.length + 1][];
        for (int i = 0; i < original.length; i++) {
            newArray[i] = original[i];
        }
        newArray[original.length] = newRow;
        return newArray;
    }

    private void fillInput(int row) {
        Object[] rowData = crudFaqTable1.getSelectedRowData(row);
        input_title.setText(rowData[1].toString());
        input_content.setText(rowData[2].toString());
    }

    private int getDataLatestId() {
        Db db = new Db();
        int id = -1;
        try {
            db.connect();
            String q = "SELECT id FROM `faq` ORDER BY `id` LIMIT 1";
            ResultSet res = db.executeQuery(q);
            while(res.next()){
                id = res.getInt("id");
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
        return id;
    }

    private void updateData(int row, Object[] new_data) {
        Db db = new Db();
        try {
            db.connect();
            Object[] data = crudFaqTable1.getSelectedRowData(row);
            String q = "UPDATE `faq` SET `title` = ?, `content` = ? WHERE id = ?";
            db.executeUpdate(q, new_data[0], new_data[1], data[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.disconnect();
                crudFaqTable1.setSelectedRowData(row, 1, new_data[0].toString());
                crudFaqTable1.setSelectedRowData(row, 2, new_data[1].toString());
                JOptionPane.showMessageDialog(this, "Update data successful!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addData(Object[] data) {
        Db db = new Db();
        try {
            db.connect();
            String q = "INSERT INTO `faq` (`title`, `content`) VALUES (?, ?)";
            db.executeUpdate(q, data[0], data[1]);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                input_content.setText("");
                input_title.setText("");
                db.disconnect();
                
                int id = getDataLatestId();
                Object[] table_data = {String.valueOf(id), data[0], data[1]};
                data_faq = appendToObjectArray(data_faq, table_data);
                crudFaqTable1.setData(data_faq);
                crudFaqTable1.revalidate();
                crudFaqTable1.repaint();
                JOptionPane.showMessageDialog(this, "Add data successful!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void removeData(int row) {
        Db db = new Db();
        try {
            db.connect();
            Object[] data = crudFaqTable1.getSelectedRowData(row);
            String q = "DELETE FROM `faq` WHERE `id` = ?";
            db.executeUpdate(q, data[0]);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                db.disconnect();
                crudFaqTable1.revalidate();
                crudFaqTable1.repaint();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setupEventDelete() {
        btn_del.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isSelected) {
                    btn_del.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_del.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isSelected) {
                    int response = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure to delete this data?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        input_content.setText("");
                        input_title.setText("");
                        isSelected = false;
                        removeData(rowSelected);
                        crudFaqTable1.removeSelectedRowData(rowSelected);
                        disableBtn();
                    }
                }
            }
        });
    }

    private void setupEventUnselect() {
        btn_unselect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isSelected) {
                    btn_unselect.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_unselect.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (isSelected) {
                    isSelected = false;
                    disableBtn();
                }
            }
        });
    }

    private void activateBtn() {
        btn_unselect.setBackground(new Color(240, 148, 11));
        btn_del.setBackground(new Color(123, 15, 58));
        label_del.setForeground(new Color(255, 255, 255));
    }

    private void disableBtn() {
        btn_unselect.setBackground(new Color(78, 57, 30));
        btn_del.setBackground(new Color(42, 17, 44));
        label_del.setForeground(new Color(102, 102, 102));
        crudFaqTable1.setSelecedRow(-1);
        rowSelected = -1;
    }

    private void tableOnSelect() {
        ListSelectionModel selectionModel = crudFaqTable1.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = crudFaqTable1.getSelectedRow();
                if (selectedRow != -1) {
                    fillInput(selectedRow);
                    isSelected = true;
                    rowSelected = selectedRow;
                    activateBtn();
                }
            }
        });
    }

    public CrudFaq() {
        initComponents();
        loadDataFromDatabase();
        setupEventDelete();
        setupEventUnselect();
        tableOnSelect();

        ScrollBar scrollPane = new ScrollBar(root);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBounds(0, 0, main.getWidth(), 650);
        add(scrollPane);
    }

    private void loadDataFromDatabase() {
        Db db = new Db();
        try {
            db.connect();
            String q = "SELECT * FROM `faq`";
            ResultSet res = db.executeQuery(q);
            while (res.next()) {
                Object[] tmp = {res.getInt("id"), res.getString("title"), res.getString("content")};
                data_faq = appendToObjectArray(data_faq, tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                crudFaqTable1.setData(data_faq);
                db.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        FAQ_path1 = new utils.helper.RopaLabel();
        crudFaqTable1 = new utils.helper.CrudFaqTable();
        input_area = new javax.swing.JPanel();
        container_title = new javax.swing.JPanel();
        area_title = new utils.helper.RoundedPanel();
        input_title = new javax.swing.JTextField();
        label_title = new utils.helper.RopaLabel();
        container_content = new javax.swing.JPanel();
        label_content = new utils.helper.RopaLabel();
        area_content = new utils.helper.RoundedPanel();
        input_content = new utils.helper.TextArea();
        btn_unselect = new utils.helper.RoundedPanel();
        label_clear = new utils.helper.RopaLabel();
        btn_save = new utils.helper.RoundedPanel();
        label_save = new utils.helper.RopaLabel();
        btn_del = new utils.helper.RoundedPanel();
        label_del = new utils.helper.RopaLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(1281, 650));
        setResizable(false);

        root.setMinimumSize(new java.awt.Dimension(1281, 650));
        root.setPreferredSize(new java.awt.Dimension(1281, 974));
        root.setLayout(new javax.swing.BoxLayout(root, javax.swing.BoxLayout.LINE_AXIS));

        main.setBackground(new java.awt.Color(8, 18, 38));
        main.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.setMinimumSize(new java.awt.Dimension(1281, 650));
        main.setPreferredSize(new java.awt.Dimension(1281, 974));
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

        FAQ_path.setText("/ Manage");
        FAQ_path.setFontSize(20.0F);
        main.add(FAQ_path);
        FAQ_path.setBounds(240, 50, 90, 22);

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

        ropaLabel2.setText("Manage FAQ");
        main.add(ropaLabel2);
        ropaLabel2.setBounds(90, 130, 150, 27);

        FAQ_path1.setText("/ FAQ");
        FAQ_path1.setFontSize(20.0F);
        FAQ_path1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FAQ_path1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FAQ_path1MouseEntered(evt);
            }
        });
        main.add(FAQ_path1);
        FAQ_path1.setBounds(190, 50, 50, 22);
        main.add(crudFaqTable1);
        crudFaqTable1.setBounds(90, 510, 1092, 340);

        input_area.setBackground(new java.awt.Color(8, 18, 38));
        input_area.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        container_title.setBackground(new java.awt.Color(8, 18, 38));
        container_title.setLayout(null);

        area_title.setBackground(new java.awt.Color(217, 217, 217));
        area_title.setRoundBottomLeft(10);
        area_title.setRoundBottomRight(10);
        area_title.setRoundTopLeft(10);
        area_title.setRoundTopRight(10);

        input_title.setBackground(new java.awt.Color(217, 217, 217));
        input_title.setFont(label_title.getFont());
        input_title.setForeground(new java.awt.Color(0, 0, 0));
        input_title.setBorder(null);
        input_title.setName("Evasiveness"); // NOI18N

        javax.swing.GroupLayout area_titleLayout = new javax.swing.GroupLayout(area_title);
        area_title.setLayout(area_titleLayout);
        area_titleLayout.setHorizontalGroup(
            area_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_titleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_title, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
                .addContainerGap())
        );
        area_titleLayout.setVerticalGroup(
            area_titleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_titleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_title, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        container_title.add(area_title);
        area_title.setBounds(0, 30, 660, 40);

        label_title.setText("Title");
        label_title.setFontSize(18.0F);
        container_title.add(label_title);
        label_title.setBounds(0, 10, 99, 20);

        input_area.add(container_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 70));

        container_content.setBackground(new java.awt.Color(8, 18, 38));
        container_content.setLayout(null);

        label_content.setText("Content");
        label_content.setFontSize(18.0F);
        container_content.add(label_content);
        label_content.setBounds(0, 10, 99, 20);

        area_content.setBackground(new java.awt.Color(217, 217, 217));
        area_content.setRoundBottomLeft(10);
        area_content.setRoundBottomRight(10);
        area_content.setRoundTopLeft(10);
        area_content.setRoundTopRight(10);

        input_content.setBackground(new java.awt.Color(217, 217, 217));
        input_content.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout area_contentLayout = new javax.swing.GroupLayout(area_content);
        area_content.setLayout(area_contentLayout);
        area_contentLayout.setHorizontalGroup(
            area_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_contentLayout.createSequentialGroup()
                .addComponent(input_content, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        area_contentLayout.setVerticalGroup(
            area_contentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_contentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_content, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        container_content.add(area_content);
        area_content.setBounds(0, 40, 1090, 190);

        input_area.add(container_content, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1090, 230));

        btn_unselect.setBackground(new java.awt.Color(78, 57, 30));
        btn_unselect.setRoundBottomLeft(10);
        btn_unselect.setRoundBottomRight(10);
        btn_unselect.setRoundTopLeft(10);
        btn_unselect.setRoundTopRight(10);
        btn_unselect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_unselectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_unselectMouseEntered(evt);
            }
        });
        btn_unselect.setLayout(new java.awt.GridBagLayout());

        label_clear.setForeground(new java.awt.Color(0, 0, 0));
        label_clear.setText("UNSELECT");
        btn_unselect.add(label_clear, new java.awt.GridBagConstraints());

        input_area.add(btn_unselect, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 20, 120, 50));

        btn_save.setBackground(new java.awt.Color(52, 255, 67));
        btn_save.setRoundBottomLeft(10);
        btn_save.setRoundBottomRight(10);
        btn_save.setRoundTopLeft(10);
        btn_save.setRoundTopRight(10);
        btn_save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_saveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_saveMouseEntered(evt);
            }
        });
        btn_save.setLayout(new java.awt.GridBagLayout());

        label_save.setForeground(new java.awt.Color(0, 0, 0));
        label_save.setText("SAVE");
        btn_save.add(label_save, new java.awt.GridBagConstraints());

        input_area.add(btn_save, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 20, 120, 50));

        btn_del.setBackground(new java.awt.Color(42, 17, 44));
        btn_del.setRoundBottomLeft(10);
        btn_del.setRoundBottomRight(10);
        btn_del.setRoundTopLeft(10);
        btn_del.setRoundTopRight(10);
        btn_del.setLayout(new java.awt.GridBagLayout());

        label_del.setForeground(new java.awt.Color(102, 102, 102));
        label_del.setText("DELETE");
        btn_del.add(label_del, new java.awt.GridBagConstraints());

        input_area.add(btn_del, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 20, 120, 50));

        main.add(input_area);
        input_area.setBounds(90, 180, 1090, 330);

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
        Faq faq = new Faq();
        faq.setVisible(true);
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

    private void FAQ_path1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FAQ_path1MouseEntered
        // TODO add your handling code here:
        FAQ_path1.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_FAQ_path1MouseEntered

    private void FAQ_path1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FAQ_path1MouseClicked
        // TODO add your handling code here:
        Faq faq = new Faq();
        faq.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_FAQ_path1MouseClicked

    private void btn_unselectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_unselectMouseEntered
        // TODO add your handling code here:
        btn_unselect.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_unselectMouseEntered

    private void btn_unselectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_unselectMouseClicked
        // TODO add your handling code here:
        input_content.setText("");
        input_title.setText("");
        isSelected = false;
        disableBtn();
    }//GEN-LAST:event_btn_unselectMouseClicked

    private void btn_saveMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_saveMouseEntered
        // TODO add your handling code here:
        btn_save.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_saveMouseEntered

    private void btn_saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_saveMouseClicked
        // TODO add your handling code here:
        String[] data = {input_title.getText(), input_content.getText()};
        if (isSelected && rowSelected != -1) {
            updateData(rowSelected, data);
        } else {
            addData(data);
        }
    }//GEN-LAST:event_btn_saveMouseClicked

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
            java.util.logging.Logger.getLogger(CrudFaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrudFaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrudFaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrudFaq.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new CrudFaq().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.helper.RopaLabel FAQ_path;
    private utils.helper.RopaLabel FAQ_path1;
    private utils.helper.RopaLabel Help_path;
    private utils.helper.RoundedPanel area_content;
    private utils.helper.RoundedPanel area_title;
    private utils.helper.RoundedPanel back;
    private utils.helper.RoundedPanel btn_del;
    private utils.helper.RoundedPanel btn_save;
    private utils.helper.RoundedPanel btn_unselect;
    private javax.swing.JPanel container_content;
    private javax.swing.JPanel container_title;
    private utils.helper.CrudFaqTable crudFaqTable1;
    private utils.helper.RopaLabel home_path;
    private javax.swing.JPanel input_area;
    private utils.helper.TextArea input_content;
    private javax.swing.JTextField input_title;
    private utils.helper.RopaLabel label_clear;
    private utils.helper.RopaLabel label_content;
    private utils.helper.RopaLabel label_del;
    private utils.helper.RopaLabel label_save;
    private utils.helper.RopaLabel label_title;
    private javax.swing.JPanel main;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RopaLabel ropaLabel2;
    // End of variables declaration//GEN-END:variables
}

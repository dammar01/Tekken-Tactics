/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guide;

import home.Home;
import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.JFrame;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import utils.helper.Db;
import utils.helper.ScrollBar;
import utils.helper.Session;

/**
 *
 * @author Dammar
 */
public class Character extends javax.swing.JFrame {

    private HashMap<String, String> this_data = new HashMap<>();
    private int row_selected = -1;

    /**
     * Creates new form Guide
     */
    private List<String> convertArray(String string_data) {
        String cleanedData = string_data.replace("[", "").replace("]", "");
        if (cleanedData.contains("\"")) {
            cleanedData.replace("\"", "");
        }
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
    private void removeData(int row){
        int id = movesheetTable1.getSelectedRowId(row);
        Db db = new Db();
        try{
            db.connect();
            String q = "DELETE FROM `movesheet` WHERE `id` = ?";
            db.executeUpdate(q, id);
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

    private void activateBtn() {
        btn_edit.setBackground(new Color(240, 148, 11));
        btn_del.setBackground(new Color(251, 59, 87));
        label_del.setForeground(Color.white);
    }

    private void disableBtn() {
        btn_edit.setBackground(new Color(78, 57, 30));
        btn_del.setBackground(new Color(42, 17, 44));
        label_del.setForeground(new Color(187, 187, 187));
    }

    private void setupEventEdit() {
        btn_edit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (row_selected != -1) {
                    btn_edit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_edit.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (row_selected != -1) {
                    int id = movesheetTable1.getSelectedRowId(row_selected);
                    ManageMovesheet manage_movesheet = new ManageMovesheet(this_data, id);
                    manage_movesheet.setVisible(true);
                    dispose();
                }
            }
        });
    }

    private void setupEventDelete() {
        btn_del.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (row_selected != -1) {
                    btn_del.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn_del.setCursor(Cursor.getDefaultCursor());
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (row_selected != -1) {
                    int response = JOptionPane.showConfirmDialog(
                            null,
                            "Are you sure to delete this data?",
                            "Confirmation",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

                    if (response == JOptionPane.YES_OPTION) {
                        removeData(row_selected);
                        movesheetTable1.removeSelectedRowData(row_selected);
                        disableBtn();
                        row_selected = -1;
                        JOptionPane.showMessageDialog(root, "Delete data successful!");
                    }
                }
            }
        });
    }

    private void tableOnSelect() {
        ListSelectionModel selectionModel = movesheetTable1.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectionModel.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = movesheetTable1.getSelectedRow();
                if (selectedRow != -1) {
                    row_selected = selectedRow;
                    activateBtn();
                }
            }
        });
    }

    public Character() throws SQLException {
        initComponents();
        this_data.put("id", "7");
        this_data.put("name", "Dragunov");
        this_data.put("code", "dragunov");
        if (Session.isAdmin()) {
            tableOnSelect();
            setupEventEdit();
            setupEventDelete();
        } else {
            movesheet.remove(btn_manage);
        }

        Db db = new Db();
        db.connect();
        String selectQuery = "SELECT * FROM `movesheet` WHERE `character_id` = ?";
        ResultSet raw_movesheet = db.executeQuery(selectQuery, 7);
        Object[][] data_movesheet = {};
        while (raw_movesheet.next()) {
            ArrayList<ImageIcon> img_movesheet = new ArrayList<>();
            List<String> notation_data = convertArray(raw_movesheet.getString("notation"));
            for (String item : notation_data) {
                ImageIcon img = new ImageIcon(getClass().getResource("/image/button/32x32/default.png"));
                try {
                    item = item.replace("\"", "");
                    img = new ImageIcon(getClass().getResource("/image/button/32x32/" + item + ".png"));
                } catch (NullPointerException e) {
                    System.out.println("Notation " + item + " not found");
                }
                img_movesheet.add(img);
            }
            Object[] new_data = {
                raw_movesheet.getString("id"),
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
        if (Session.isAdmin()) {
            tableOnSelect();
            setupEventEdit();
            setupEventDelete();
        } else {
            movesheet.remove(btn_manage);
        }
        this_data.put("id", raw.get("id"));
        this_data.put("code", raw.get("code"));
        this_data.put("name", raw.get("name"));

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
                ImageIcon img = new ImageIcon(getClass().getResource("/image/button/32x32/default.png"));
                try {
                    item = item.replace("\"", "");
                    img = new ImageIcon(getClass().getResource("/image/button/32x32/" + item + ".png"));
                } catch (NullPointerException e) {
                    System.out.println("Notation " + item + " not found");
                }
                img_movesheet.add(img);
            }
            Object[] new_data = {
                raw_movesheet.getString("id"),
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
        java.awt.GridBagConstraints gridBagConstraints;

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
        btn_manage = new javax.swing.JPanel();
        btn_add = new utils.helper.RoundedPanel();
        label_add = new utils.helper.RopaLabel();
        btn_edit = new utils.helper.RoundedPanel();
        label_edit = new utils.helper.RopaLabel();
        btn_del = new utils.helper.RoundedPanel();
        label_del = new utils.helper.RopaLabel();

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
        movesheet.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        movesheet_title.setText("| Movesheet");
        movesheet.add(movesheet_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 17, 190, -1));
        movesheet.add(movesheetTable1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 62, -1, 355));

        btn_manage.setBackground(new java.awt.Color(66, 21, 50));
        btn_manage.setLayout(new java.awt.GridBagLayout());

        btn_add.setBackground(new java.awt.Color(52, 255, 67));
        btn_add.setRoundBottomLeft(10);
        btn_add.setRoundBottomRight(10);
        btn_add.setRoundTopLeft(10);
        btn_add.setRoundTopRight(10);
        btn_add.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_addMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_addMouseEntered(evt);
            }
        });

        label_add.setForeground(new java.awt.Color(0, 0, 0));
        label_add.setText("Add");
        label_add.setFontSize(18.0F);
        btn_add.add(label_add);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 43;
        gridBagConstraints.ipady = -1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 0, 35, 0);
        btn_manage.add(btn_add, gridBagConstraints);

        btn_edit.setBackground(new java.awt.Color(78, 57, 30));
        btn_edit.setRoundBottomLeft(10);
        btn_edit.setRoundBottomRight(10);
        btn_edit.setRoundTopLeft(10);
        btn_edit.setRoundTopRight(10);

        label_edit.setForeground(new java.awt.Color(0, 0, 0));
        label_edit.setText("Edit");
        label_edit.setFontSize(18.0F);
        btn_edit.add(label_edit);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 42;
        gridBagConstraints.ipady = -1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 10, 35, 0);
        btn_manage.add(btn_edit, gridBagConstraints);

        btn_del.setBackground(new java.awt.Color(42, 17, 44));
        btn_del.setRoundBottomLeft(10);
        btn_del.setRoundBottomRight(10);
        btn_del.setRoundTopLeft(10);
        btn_del.setRoundTopRight(10);

        label_del.setForeground(new java.awt.Color(102, 102, 102));
        label_del.setText("Delete");
        label_del.setFontSize(18.0F);
        btn_del.add(label_del);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 25;
        gridBagConstraints.ipady = -1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(35, 10, 35, 0);
        btn_manage.add(btn_del, gridBagConstraints);

        movesheet.add(btn_manage, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 20, -1, 40));

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

    private void btn_addMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_btn_addMouseEntered

    private void btn_addMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_addMouseClicked
        // TODO add your handling code here:
        ManageMovesheet manage_movesheet = new ManageMovesheet(this_data);
        manage_movesheet.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_addMouseClicked

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
    private utils.helper.RoundedPanel btn_add;
    private utils.helper.RoundedPanel btn_del;
    private utils.helper.RoundedPanel btn_edit;
    private javax.swing.JPanel btn_manage;
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
    private utils.helper.RopaLabel label_add;
    private utils.helper.RopaLabel label_del;
    private utils.helper.RopaLabel label_edit;
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

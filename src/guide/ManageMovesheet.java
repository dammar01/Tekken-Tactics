/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package guide;

import myCombo.*;
import auth.Login;
import home.Home;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;
import javax.swing.plaf.basic.ComboPopup;
import utils.helper.Db;
import utils.helper.ScrollBar;
import utils.helper.Session;

/**
 *
 * @author Dammar
 */
public class ManageMovesheet extends javax.swing.JFrame {

    /**
     * Creates new form Guide
     */
    private HashMap<String, String> character_data = new HashMap<>();
    private LinkedList<String> notation_list = new LinkedList<>();
    private int id = -1;

    private String[] convertArray(String string_data) {
        String cleanedData = string_data.replace("[", "").replace("]", "");
        if (cleanedData.contains("\"")) {
            cleanedData = cleanedData.replace("\"", "");
        }
        return cleanedData.split(",\\s*");
    }

    private void reloadPanel(JPanel panel) {
        panel.revalidate();
        panel.repaint();
    }

    private void addNotation(java.awt.event.MouseEvent evt) {
        int componentCount = notation_input.getComponentCount();
        Rectangle max_size = notation_input.getBounds();
        JLabel source = (JLabel) evt.getComponent();
        File image = new File(source.getIcon().toString());
        String file_name = image.getName().replace("%5e", "^");

        this.notation_list.add(file_name.substring(0, file_name.length() - 4));
        if (componentCount > 0) {
            JLabel lastComponent = (JLabel) notation_input.getComponent(componentCount - 1);
            if (lastComponent.getBounds().x + lastComponent.getIcon().getIconWidth()
                    + source.getIcon().getIconWidth() > max_size.width) {
                max_size.height += 74;
                notation_input.setPreferredSize(new Dimension(max_size.width, max_size.height));
                notation_input.setBounds(max_size);

                Rectangle old_notation_data_rect = notation_data.getBounds();
                notation_data.setBounds(old_notation_data_rect.x, old_notation_data_rect.y + 74,
                        old_notation_data_rect.width, old_notation_data_rect.height);

                Rectangle old_notation_rect = notation.getBounds();
                notation.setPreferredSize(new Dimension(old_notation_rect.width, old_notation_rect.height));
                notation.setBounds(old_notation_rect.x, old_notation_rect.y, old_notation_rect.width,
                        old_notation_rect.height + 74);
                root.setPreferredSize(new Dimension(root.getPreferredSize().width, root.getPreferredSize().height + 74));
                reloadPanel(root);
                reloadPanel(notation);
                reloadPanel(notation_data);

            }
        }
        JLabel label = new JLabel("");
        label.setIcon(source.getIcon());
        notation_input.add(label);
        notation_input.revalidate();
        notation_input.repaint();
    }

    private void addNotation(JLabel source) {
        int componentCount = notation_input.getComponentCount();
        Rectangle max_size = notation_input.getBounds();
        File image = new File(source.getIcon().toString());
        String file_name = image.getName().replace("%5e", "^");

        this.notation_list.add(file_name.substring(0, file_name.length() - 4));
        if (componentCount > 0) {
            JLabel lastComponent = (JLabel) notation_input.getComponent(componentCount - 1);
            if (lastComponent.getBounds().x + lastComponent.getIcon().getIconWidth()
                    + source.getIcon().getIconWidth() > max_size.width) {
                max_size.height += 74;
                notation_input.setPreferredSize(new Dimension(max_size.width, max_size.height));
                notation_input.setBounds(max_size);

                Rectangle old_notation_data_rect = notation_data.getBounds();
                notation_data.setBounds(old_notation_data_rect.x, old_notation_data_rect.y + 74,
                        old_notation_data_rect.width, old_notation_data_rect.height);

                Rectangle old_notation_rect = notation.getBounds();
                notation.setPreferredSize(new Dimension(old_notation_rect.width, old_notation_rect.height));
                notation.setBounds(old_notation_rect.x, old_notation_rect.y, old_notation_rect.width,
                        old_notation_rect.height + 74);
                root.setPreferredSize(new Dimension(root.getPreferredSize().width, root.getPreferredSize().height + 74));
                reloadPanel(root);
                reloadPanel(notation);
                reloadPanel(notation_data);

            }
        }
        JLabel label = new JLabel("");
        label.setIcon(source.getIcon());
        notation_input.add(label);
        notation_input.revalidate();
        notation_input.repaint();
    }

    private void delNotation() {
        int componentCount = notation_input.getComponentCount();
        Rectangle max_size = notation_input.getBounds();
        if (componentCount > 0) {
            JLabel lastComponent = (JLabel) notation_input.getComponent(componentCount - 1);
            if (lastComponent.getBounds().x == 10 && lastComponent.getBounds().y > 10) {
                max_size.height -= 74;
                notation_input.setPreferredSize(new Dimension(max_size.width, max_size.height));
                notation_input.setBounds(max_size);

                Rectangle old_notation_data_rect = notation_data.getBounds();
                notation_data.setBounds(old_notation_data_rect.x, old_notation_data_rect.y - 74,
                        old_notation_data_rect.width, old_notation_data_rect.height);

                Rectangle old_notation_rect = notation.getBounds();
                notation.setPreferredSize(new Dimension(old_notation_rect.width, old_notation_rect.height));
                notation.setBounds(old_notation_rect.x, old_notation_rect.y, old_notation_rect.width,
                        old_notation_rect.height - 74);

                root.setPreferredSize(new Dimension(root.getPreferredSize().width, root.getPreferredSize().height - 74));
                reloadPanel(root);
                reloadPanel(notation);
                reloadPanel(notation_data);
            }
            notation_input.remove(lastComponent);
            this.notation_list.removeLast();
            notation_input.revalidate();
            notation_input.repaint();
        }
    }

    private void clearAllNotations() {
        notation_input.removeAll();
        notation_list.clear();
        notation_input.setPreferredSize(new Dimension(0, 0));
        notation_input.revalidate();
        notation_input.repaint();
    }

    private void resetInput() {
        input_damage.setText("");
        input_frame_start.setText("");
        input_hit_properties.setSelectedItem("Select Hit Properties");
        input_name_move.setText("");
        input_notes.setText("");
        clearAllNotations();
    }

    private void insertData(HashMap<String, String> data) {
        Db db = new Db();
        try {
            db.connect();
            String q = "INSERT INTO `movesheet` (`character_id`, `notation`, `name_move`, `damage`, `frame_startup`, `hit_properties`, `notes`) VALUES (?, ?, ?, ?, ?, ?, ?)";
            db.executeUpdate(
                    q,
                    character_data.get("id"),
                    data.get("notation"),
                    data.get("name_move"),
                    data.get("damage"),
                    data.get("frame_startup"),
                    data.get("hit_properties"),
                    data.get("notes")
            );
            JOptionPane.showMessageDialog(this, "Add data successful!");
            resetInput();
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

    private void updateData(HashMap<String, String> data) {
        Db db = new Db();
        try {
            db.connect();
            String q = "UPDATE `movesheet` SET `notation` = ?, `name_move` = ?, `damage` = ?, `frame_startup` = ?, `hit_properties` = ?, `notes` = ? WHERE id = ?";
            db.executeUpdate(
                    q,
                    data.get("notation"),
                    data.get("name_move"),
                    data.get("damage"),
                    data.get("frame_startup"),
                    data.get("hit_properties"),
                    data.get("notes"),
                    id
            );
            JOptionPane.showMessageDialog(this, "Update data successful!");
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

    private void changeScrollBar(JComboBox combo_box) {
        combo_box.setUI(new BasicComboBoxUI() {
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

    private void fillInput() {
        Db db = new Db();
        try {
            db.connect();
            String q = "SELECT * FROM `movesheet` WHERE id = ? LIMIT 1";
            ResultSet res = db.executeQuery(q, id);
            while (res.next()) {
                input_damage.setText(res.getString("damage"));
                input_frame_start.setText(res.getString("frame_startup"));
                input_hit_properties.setSelectedItem(res.getString("hit_properties"));
                input_name_move.setText(res.getString("name_move"));
                input_notes.setText(res.getString("notes"));
                String[] notation = convertArray(res.getString("notation"));
                for (String nota : notation) {
                    JLabel label = new JLabel();
                    ImageIcon img = new ImageIcon(getClass().getResource("/image/button/64x64/default.png"));
                    try {
                        img = new ImageIcon(getClass().getResource("/image/button/64x64/" + nota + ".png"));
                    } catch (NullPointerException e) {
                        System.out.println("Notation " + nota + " not found");
                    }
                    label.setIcon(img);
                    addNotation(label);
                }
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

    public ManageMovesheet() {
        character_data.clear();
        initComponents();
        changeScrollBar(input_hit_properties);

        character_data.put("id", "34");
        character_data.put("name", "Yoshimitsu");
        character_data.put("code", "yoshimitsu");

        reloadPanel(root);
        ScrollBar scrollPane = new ScrollBar(root);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBounds(0, 0, main.getWidth(), 650);
        add(scrollPane);
    }

    public ManageMovesheet(HashMap<String, String> char_data) {
        character_data.clear();
        initComponents();
        changeScrollBar(input_hit_properties);

        character_data.put("id", char_data.get("id"));
        character_data.put("name", char_data.get("name"));
        character_data.put("code", char_data.get("code"));

        reloadPanel(root);
        character_path.setText("/ " + char_data.get("name"));

        ScrollBar scrollPane = new ScrollBar(root);
        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(10);
        scrollPane.setBounds(0, 0, main.getWidth(), 650);
        add(scrollPane);
    }

    public ManageMovesheet(HashMap<String, String> char_data, int id) {
        this.id = id;
        character_data.clear();
        initComponents();
        fillInput();
        changeScrollBar(input_hit_properties);

        character_data.put("id", char_data.get("id"));
        character_data.put("name", char_data.get("name"));
        character_data.put("code", char_data.get("code"));

        reloadPanel(root);
        character_path.setText("/ " + char_data.get("name"));

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
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        root = new javax.swing.JPanel();
        main = new javax.swing.JPanel();
        home_path = new utils.helper.RopaLabel();
        manage_movesheet_path = new utils.helper.RopaLabel();
        character_path = new utils.helper.RopaLabel();
        guide_path1 = new utils.helper.RopaLabel();
        container_name_move = new javax.swing.JPanel();
        area_name_move = new utils.helper.RoundedPanel();
        input_name_move = new javax.swing.JTextField();
        label_name_move = new utils.helper.RopaLabel();
        container_damage = new javax.swing.JPanel();
        area_damage = new utils.helper.RoundedPanel();
        input_damage = new javax.swing.JTextField();
        label_damage = new utils.helper.RopaLabel();
        container_frame_start = new javax.swing.JPanel();
        area_frame_start = new utils.helper.RoundedPanel();
        input_frame_start = new javax.swing.JTextField();
        label_frame_start = new utils.helper.RopaLabel();
        back = new utils.helper.RoundedPanel();
        ropaLabel1 = new utils.helper.RopaLabel();
        save = new utils.helper.RoundedPanel();
        save_label = new utils.helper.RopaLabel();
        backspace = new utils.helper.RoundedPanel();
        backspace_label = new utils.helper.RopaLabel();
        notation = new javax.swing.JPanel();
        notation_label = new utils.helper.RopaLabel();
        notation_input = new utils.helper.RoundedPanel();
        notation_data = new javax.swing.JPanel();
        btn_1 = new javax.swing.JLabel();
        btn_2 = new javax.swing.JLabel();
        btn_3 = new javax.swing.JLabel();
        btn_4 = new javax.swing.JLabel();
        btn_1_2 = new javax.swing.JLabel();
        btn_1_3 = new javax.swing.JLabel();
        btn_1_4 = new javax.swing.JLabel();
        btn_2_3 = new javax.swing.JLabel();
        btn_2_4 = new javax.swing.JLabel();
        btn_3_4 = new javax.swing.JLabel();
        btn_1_2_3 = new javax.swing.JLabel();
        btn_1_2_4 = new javax.swing.JLabel();
        btn_1_3_4 = new javax.swing.JLabel();
        btn_2_3_4 = new javax.swing.JLabel();
        btn_1_2_3_4 = new javax.swing.JLabel();
        btn_n = new javax.swing.JLabel();
        btn_f = new javax.swing.JLabel();
        btn_df = new javax.swing.JLabel();
        btn_d = new javax.swing.JLabel();
        btn_db = new javax.swing.JLabel();
        btn_b = new javax.swing.JLabel();
        btn_ub = new javax.swing.JLabel();
        btn_u = new javax.swing.JLabel();
        btn_uf = new javax.swing.JLabel();
        btn_f1 = new javax.swing.JLabel();
        btn_df1 = new javax.swing.JLabel();
        btn_d1 = new javax.swing.JLabel();
        btn_db1 = new javax.swing.JLabel();
        btn_b1 = new javax.swing.JLabel();
        btn_ub1 = new javax.swing.JLabel();
        btn_u1 = new javax.swing.JLabel();
        btn_uf1 = new javax.swing.JLabel();
        btn_brace1 = new javax.swing.JLabel();
        btn_brace2 = new javax.swing.JLabel();
        btn_next = new javax.swing.JLabel();
        btn_colon = new javax.swing.JLabel();
        btn_tilde = new javax.swing.JLabel();
        btn_comma = new javax.swing.JLabel();
        btn_delay1 = new javax.swing.JLabel();
        btn_delay2 = new javax.swing.JLabel();
        btn_bb = new javax.swing.JLabel();
        btn_fbl = new javax.swing.JLabel();
        btn_fb = new javax.swing.JLabel();
        btn_wbo = new javax.swing.JLabel();
        btn_wbl = new javax.swing.JLabel();
        btn_wb = new javax.swing.JLabel();
        btn_h = new javax.swing.JLabel();
        btn_r = new javax.swing.JLabel();
        btn_rage = new javax.swing.JLabel();
        btn_cc = new javax.swing.JLabel();
        btn_ch = new javax.swing.JLabel();
        btn_cd = new javax.swing.JLabel();
        btn_dash = new javax.swing.JLabel();
        btn_fc = new javax.swing.JLabel();
        btn_hold = new javax.swing.JLabel();
        btn_ss = new javax.swing.JLabel();
        btn_ssl = new javax.swing.JLabel();
        btn_ssr = new javax.swing.JLabel();
        btn_swl = new javax.swing.JLabel();
        btn_swr = new javax.swing.JLabel();
        btn_w = new javax.swing.JLabel();
        btn_wr = new javax.swing.JLabel();
        btn_ws = new javax.swing.JLabel();
        btn_mdash = new javax.swing.JLabel();
        btn_ddash = new javax.swing.JLabel();
        btn_iwr = new javax.swing.JLabel();
        btn_iws = new javax.swing.JLabel();
        container_hit_properties = new javax.swing.JPanel();
        label_hit_properties = new utils.helper.RopaLabel();
        input_hit_properties = new javax.swing.JComboBox<>();
        container_notes = new javax.swing.JPanel();
        label_notes = new utils.helper.RopaLabel();
        area_notes = new utils.helper.RoundedPanel();
        input_notes = new utils.helper.TextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setPreferredSize(new java.awt.Dimension(1287, 650));
        setResizable(false);

        root.setMinimumSize(new java.awt.Dimension(1287, 650));
        root.setPreferredSize(new java.awt.Dimension(1281, 1099));
        root.setLayout(new javax.swing.BoxLayout(root, javax.swing.BoxLayout.LINE_AXIS));

        main.setBackground(new java.awt.Color(8, 18, 38));
        main.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        main.setMinimumSize(new java.awt.Dimension(0, 0));
        main.setPreferredSize(new java.awt.Dimension(1290, 1099));
        main.setLayout(null);

        home_path.setText("Home /");
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
        home_path.setBounds(50, 50, 60, 22);

        manage_movesheet_path.setText("/ Manage Movesheet");
        manage_movesheet_path.setFontSize(20.0F);
        main.add(manage_movesheet_path);
        manage_movesheet_path.setBounds(260, 50, 160, 22);

        character_path.setText("/ Yoshimitsu");
        character_path.setFontSize(20.0F);
        character_path.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                character_pathMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                character_pathMouseEntered(evt);
            }
        });
        main.add(character_path);
        character_path.setBounds(160, 50, 110, 22);

        guide_path1.setText("  Guide");
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
        guide_path1.setBounds(100, 50, 60, 22);

        container_name_move.setBackground(new java.awt.Color(8, 18, 38));
        container_name_move.setLayout(null);

        area_name_move.setBackground(new java.awt.Color(217, 217, 217));
        area_name_move.setRoundBottomLeft(10);
        area_name_move.setRoundBottomRight(10);
        area_name_move.setRoundTopLeft(10);
        area_name_move.setRoundTopRight(10);

        input_name_move.setBackground(new java.awt.Color(217, 217, 217));
        input_name_move.setFont(label_name_move.getFont());
        input_name_move.setForeground(new java.awt.Color(0, 0, 0));
        input_name_move.setBorder(null);
        input_name_move.setName("Evasiveness"); // NOI18N

        javax.swing.GroupLayout area_name_moveLayout = new javax.swing.GroupLayout(area_name_move);
        area_name_move.setLayout(area_name_moveLayout);
        area_name_moveLayout.setHorizontalGroup(
            area_name_moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_name_moveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_name_move, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );
        area_name_moveLayout.setVerticalGroup(
            area_name_moveLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_name_moveLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_name_move, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        container_name_move.add(area_name_move);
        area_name_move.setBounds(0, 30, 260, 40);

        label_name_move.setText("Name Move");
        label_name_move.setFontSize(18.0F);
        container_name_move.add(label_name_move);
        label_name_move.setBounds(0, 10, 99, 20);

        main.add(container_name_move);
        container_name_move.setBounds(50, 110, 260, 70);

        container_damage.setBackground(new java.awt.Color(8, 18, 38));
        container_damage.setLayout(null);

        area_damage.setBackground(new java.awt.Color(217, 217, 217));
        area_damage.setRoundBottomLeft(10);
        area_damage.setRoundBottomRight(10);
        area_damage.setRoundTopLeft(10);
        area_damage.setRoundTopRight(10);

        input_damage.setBackground(new java.awt.Color(217, 217, 217));
        input_damage.setFont(label_damage.getFont());
        input_damage.setForeground(new java.awt.Color(0, 0, 0));
        input_damage.setBorder(null);
        input_damage.setName("Evasiveness"); // NOI18N

        javax.swing.GroupLayout area_damageLayout = new javax.swing.GroupLayout(area_damage);
        area_damage.setLayout(area_damageLayout);
        area_damageLayout.setHorizontalGroup(
            area_damageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_damageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_damage, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        area_damageLayout.setVerticalGroup(
            area_damageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_damageLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_damage, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        container_damage.add(area_damage);
        area_damage.setBounds(0, 30, 100, 40);

        label_damage.setText("Damage");
        label_damage.setFontSize(18.0F);
        container_damage.add(label_damage);
        label_damage.setBounds(0, 10, 99, 20);

        main.add(container_damage);
        container_damage.setBounds(330, 110, 100, 70);

        container_frame_start.setBackground(new java.awt.Color(8, 18, 38));
        container_frame_start.setLayout(null);

        area_frame_start.setBackground(new java.awt.Color(217, 217, 217));
        area_frame_start.setRoundBottomLeft(10);
        area_frame_start.setRoundBottomRight(10);
        area_frame_start.setRoundTopLeft(10);
        area_frame_start.setRoundTopRight(10);

        input_frame_start.setBackground(new java.awt.Color(217, 217, 217));
        input_frame_start.setFont(label_frame_start.getFont());
        input_frame_start.setForeground(new java.awt.Color(0, 0, 0));
        input_frame_start.setBorder(null);
        input_frame_start.setName("Evasiveness"); // NOI18N

        javax.swing.GroupLayout area_frame_startLayout = new javax.swing.GroupLayout(area_frame_start);
        area_frame_start.setLayout(area_frame_startLayout);
        area_frame_startLayout.setHorizontalGroup(
            area_frame_startLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_frame_startLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_frame_start, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                .addContainerGap())
        );
        area_frame_startLayout.setVerticalGroup(
            area_frame_startLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_frame_startLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_frame_start, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        container_frame_start.add(area_frame_start);
        area_frame_start.setBounds(0, 30, 100, 40);

        label_frame_start.setText("Frame Start");
        label_frame_start.setFontSize(18.0F);
        container_frame_start.add(label_frame_start);
        label_frame_start.setBounds(0, 10, 99, 20);

        main.add(container_frame_start);
        container_frame_start.setBounds(450, 110, 100, 70);

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

        ropaLabel1.setText("<< Back");
        ropaLabel1.setFontSize(20.0F);
        back.add(ropaLabel1, new java.awt.GridBagConstraints());

        main.add(back);
        back.setBounds(1080, 50, 130, 33);

        save.setBackground(new java.awt.Color(52, 255, 67));
        save.setRoundBottomLeft(10);
        save.setRoundBottomRight(10);
        save.setRoundTopLeft(10);
        save.setRoundTopRight(10);
        save.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveMouseEntered(evt);
            }
        });
        save.setLayout(new java.awt.GridBagLayout());

        save_label.setForeground(new java.awt.Color(0, 0, 0));
        save_label.setText("SAVE");
        save_label.setFontSize(42.0F);
        save.add(save_label, new java.awt.GridBagConstraints());

        main.add(save);
        save.setBounds(960, 340, 150, 70);

        backspace.setBackground(new java.awt.Color(123, 15, 58));
        backspace.setRoundBottomLeft(10);
        backspace.setRoundBottomRight(10);
        backspace.setRoundTopLeft(10);
        backspace.setRoundTopRight(10);
        backspace.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backspaceMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backspaceMouseEntered(evt);
            }
        });
        backspace.setLayout(new java.awt.GridBagLayout());

        backspace_label.setForeground(new java.awt.Color(0, 0, 0));
        backspace_label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icon/normal/backspace.png"))); // NOI18N
        backspace_label.setFontSize(42.0F);
        backspace.add(backspace_label, new java.awt.GridBagConstraints());

        main.add(backspace);
        backspace.setBounds(1130, 340, 90, 70);

        notation.setBackground(new java.awt.Color(8, 18, 38));
        notation.setLayout(null);

        notation_label.setText("Notation");
        notation_label.setFontSize(18.0F);
        notation.add(notation_label);
        notation_label.setBounds(0, 0, 99, 20);

        notation_input.setBackground(new java.awt.Color(217, 217, 217));
        notation_input.setRoundBottomLeft(10);
        notation_input.setRoundBottomRight(10);
        notation_input.setRoundTopLeft(10);
        notation_input.setRoundTopRight(10);
        notation_input.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        notation.add(notation_input);
        notation_input.setBounds(0, 30, 1160, 80);

        main.add(notation);
        notation.setBounds(50, 420, 1160, 120);

        notation_data.setBackground(new java.awt.Color(8, 18, 38));
        notation_data.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 20, 20));

        btn_1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/1.png"))); // NOI18N
        btn_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1MousePressed(evt);
            }
        });
        notation_data.add(btn_1);

        btn_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/2.png"))); // NOI18N
        btn_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_2MousePressed(evt);
            }
        });
        notation_data.add(btn_2);

        btn_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/3.png"))); // NOI18N
        btn_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_3MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_3MousePressed(evt);
            }
        });
        notation_data.add(btn_3);

        btn_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/4.png"))); // NOI18N
        btn_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_4MousePressed(evt);
            }
        });
        notation_data.add(btn_4);

        btn_1_2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/1+2.png"))); // NOI18N
        btn_1_2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1_2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1_2MousePressed(evt);
            }
        });
        notation_data.add(btn_1_2);

        btn_1_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/1+3.png"))); // NOI18N
        btn_1_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1_3MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1_3MousePressed(evt);
            }
        });
        notation_data.add(btn_1_3);

        btn_1_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/1+4.png"))); // NOI18N
        btn_1_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1_4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1_4MousePressed(evt);
            }
        });
        notation_data.add(btn_1_4);

        btn_2_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/2+3.png"))); // NOI18N
        btn_2_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_2_3MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_2_3MousePressed(evt);
            }
        });
        notation_data.add(btn_2_3);

        btn_2_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/2+4.png"))); // NOI18N
        btn_2_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_2_4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_2_4MousePressed(evt);
            }
        });
        notation_data.add(btn_2_4);

        btn_3_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/3+4.png"))); // NOI18N
        btn_3_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_3_4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_3_4MousePressed(evt);
            }
        });
        notation_data.add(btn_3_4);

        btn_1_2_3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/1+2+3.png"))); // NOI18N
        btn_1_2_3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1_2_3MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1_2_3MousePressed(evt);
            }
        });
        notation_data.add(btn_1_2_3);

        btn_1_2_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/1+2+4.png"))); // NOI18N
        btn_1_2_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1_2_4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1_2_4MousePressed(evt);
            }
        });
        notation_data.add(btn_1_2_4);

        btn_1_3_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/1+3+4.png"))); // NOI18N
        btn_1_3_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1_3_4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1_3_4MousePressed(evt);
            }
        });
        notation_data.add(btn_1_3_4);

        btn_2_3_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/2+3+4.png"))); // NOI18N
        btn_2_3_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_2_3_4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_2_3_4MousePressed(evt);
            }
        });
        notation_data.add(btn_2_3_4);

        btn_1_2_3_4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/1+2+3+4.png"))); // NOI18N
        btn_1_2_3_4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_1_2_3_4MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_1_2_3_4MousePressed(evt);
            }
        });
        notation_data.add(btn_1_2_3_4);

        btn_n.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/n.png"))); // NOI18N
        btn_n.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_nMousePressed(evt);
            }
        });
        notation_data.add(btn_n);

        btn_f.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/f.png"))); // NOI18N
        btn_f.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_fMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_fMousePressed(evt);
            }
        });
        notation_data.add(btn_f);

        btn_df.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/df.png"))); // NOI18N
        btn_df.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dfMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dfMousePressed(evt);
            }
        });
        notation_data.add(btn_df);

        btn_d.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/d.png"))); // NOI18N
        btn_d.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dMousePressed(evt);
            }
        });
        notation_data.add(btn_d);

        btn_db.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/db.png"))); // NOI18N
        btn_db.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dbMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dbMousePressed(evt);
            }
        });
        notation_data.add(btn_db);

        btn_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/b.png"))); // NOI18N
        btn_b.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_bMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_bMousePressed(evt);
            }
        });
        notation_data.add(btn_b);

        btn_ub.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/ub.png"))); // NOI18N
        btn_ub.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ubMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ubMousePressed(evt);
            }
        });
        notation_data.add(btn_ub);

        btn_u.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/u.png"))); // NOI18N
        btn_u.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_uMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_uMousePressed(evt);
            }
        });
        notation_data.add(btn_u);

        btn_uf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/uf.png"))); // NOI18N
        btn_uf.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ufMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ufMousePressed(evt);
            }
        });
        notation_data.add(btn_uf);

        btn_f1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^f.png"))); // NOI18N
        btn_f1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_f1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_f1MousePressed(evt);
            }
        });
        notation_data.add(btn_f1);

        btn_df1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^d^f.png"))); // NOI18N
        btn_df1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_df1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_df1MousePressed(evt);
            }
        });
        notation_data.add(btn_df1);

        btn_d1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^d.png"))); // NOI18N
        btn_d1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_d1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_d1MousePressed(evt);
            }
        });
        notation_data.add(btn_d1);

        btn_db1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^d^b.png"))); // NOI18N
        btn_db1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_db1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_db1MousePressed(evt);
            }
        });
        notation_data.add(btn_db1);

        btn_b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^b.png"))); // NOI18N
        btn_b1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_b1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_b1MousePressed(evt);
            }
        });
        notation_data.add(btn_b1);

        btn_ub1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^u^b.png"))); // NOI18N
        btn_ub1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ub1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ub1MousePressed(evt);
            }
        });
        notation_data.add(btn_ub1);

        btn_u1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^u.png"))); // NOI18N
        btn_u1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_u1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_u1MousePressed(evt);
            }
        });
        notation_data.add(btn_u1);

        btn_uf1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^u^f.png"))); // NOI18N
        btn_uf1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_uf1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_uf1MousePressed(evt);
            }
        });
        notation_data.add(btn_uf1);

        btn_brace1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/[.png"))); // NOI18N
        btn_brace1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_brace1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_brace1MousePressed(evt);
            }
        });
        notation_data.add(btn_brace1);

        btn_brace2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/].png"))); // NOI18N
        btn_brace2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_brace2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_brace2MousePressed(evt);
            }
        });
        notation_data.add(btn_brace2);

        btn_next.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/'next'.png"))); // NOI18N
        btn_next.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_nextMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_nextMousePressed(evt);
            }
        });
        notation_data.add(btn_next);

        btn_colon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/'colon'.png"))); // NOI18N
        btn_colon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_colonMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_colonMousePressed(evt);
            }
        });
        notation_data.add(btn_colon);

        btn_tilde.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/~.png"))); // NOI18N
        btn_tilde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_tildeMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_tildeMousePressed(evt);
            }
        });
        notation_data.add(btn_tilde);

        btn_comma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/comma.png"))); // NOI18N
        btn_comma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_commaMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_commaMousePressed(evt);
            }
        });
        notation_data.add(btn_comma);

        btn_delay1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/'delay1'.png"))); // NOI18N
        btn_delay1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_delay1MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_delay1MousePressed(evt);
            }
        });
        notation_data.add(btn_delay1);

        btn_delay2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/'delay2'.png"))); // NOI18N
        btn_delay2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_delay2MouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_delay2MousePressed(evt);
            }
        });
        notation_data.add(btn_delay2);

        btn_bb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^b^b!.png"))); // NOI18N
        btn_bb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_bbMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_bbMousePressed(evt);
            }
        });
        notation_data.add(btn_bb);

        btn_fbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^f^bl!.png"))); // NOI18N
        btn_fbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_fblMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_fblMousePressed(evt);
            }
        });
        notation_data.add(btn_fbl);

        btn_fb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^f^b!.png"))); // NOI18N
        btn_fb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_fbMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_fbMousePressed(evt);
            }
        });
        notation_data.add(btn_fb);

        btn_wbo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^w^bo!.png"))); // NOI18N
        btn_wbo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_wboMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_wboMousePressed(evt);
            }
        });
        notation_data.add(btn_wbo);

        btn_wbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^w^bl!.png"))); // NOI18N
        btn_wbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_wblMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_wblMousePressed(evt);
            }
        });
        notation_data.add(btn_wbl);

        btn_wb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^w^b!.png"))); // NOI18N
        btn_wb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_wbMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_wbMousePressed(evt);
            }
        });
        notation_data.add(btn_wb);

        btn_h.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^h.png"))); // NOI18N
        btn_h.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_hMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_hMousePressed(evt);
            }
        });
        notation_data.add(btn_h);

        btn_r.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^r.png"))); // NOI18N
        btn_r.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_rMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_rMousePressed(evt);
            }
        });
        notation_data.add(btn_r);

        btn_rage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/rage.png"))); // NOI18N
        btn_rage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_rageMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_rageMousePressed(evt);
            }
        });
        notation_data.add(btn_rage);

        btn_cc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/cc.png"))); // NOI18N
        btn_cc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ccMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ccMousePressed(evt);
            }
        });
        notation_data.add(btn_cc);

        btn_ch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^c^h.png"))); // NOI18N
        btn_ch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_chMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_chMousePressed(evt);
            }
        });
        notation_data.add(btn_ch);

        btn_cd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^c^d.png"))); // NOI18N
        btn_cd.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_cdMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_cdMousePressed(evt);
            }
        });
        notation_data.add(btn_cd);

        btn_dash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/dash.png"))); // NOI18N
        btn_dash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_dashMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_dashMousePressed(evt);
            }
        });
        notation_data.add(btn_dash);

        btn_fc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^f^c.png"))); // NOI18N
        btn_fc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_fcMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_fcMousePressed(evt);
            }
        });
        notation_data.add(btn_fc);

        btn_hold.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/'hold'.png"))); // NOI18N
        btn_hold.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_holdMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_holdMousePressed(evt);
            }
        });
        notation_data.add(btn_hold);

        btn_ss.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^s^s.png"))); // NOI18N
        btn_ss.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ssMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ssMousePressed(evt);
            }
        });
        notation_data.add(btn_ss);

        btn_ssl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^s^s^l.png"))); // NOI18N
        btn_ssl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_sslMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_sslMousePressed(evt);
            }
        });
        notation_data.add(btn_ssl);

        btn_ssr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^s^s^r.png"))); // NOI18N
        btn_ssr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ssrMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ssrMousePressed(evt);
            }
        });
        notation_data.add(btn_ssr);

        btn_swl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^s^w^l.png"))); // NOI18N
        btn_swl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_swlMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_swlMousePressed(evt);
            }
        });
        notation_data.add(btn_swl);

        btn_swr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^s^w^l.png"))); // NOI18N
        btn_swr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_swrMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_swrMousePressed(evt);
            }
        });
        notation_data.add(btn_swr);

        btn_w.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^w!.png"))); // NOI18N
        btn_w.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_wMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_wMousePressed(evt);
            }
        });
        notation_data.add(btn_w);

        btn_wr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^w^r.png"))); // NOI18N
        btn_wr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_wrMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_wrMousePressed(evt);
            }
        });
        notation_data.add(btn_wr);

        btn_ws.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/^w^s.png"))); // NOI18N
        btn_ws.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_wsMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_wsMousePressed(evt);
            }
        });
        notation_data.add(btn_ws);

        btn_mdash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/mdash.png"))); // NOI18N
        btn_mdash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_mdashMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_mdashMousePressed(evt);
            }
        });
        notation_data.add(btn_mdash);

        btn_ddash.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/ddash.png"))); // NOI18N
        btn_ddash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_ddashMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_ddashMousePressed(evt);
            }
        });
        notation_data.add(btn_ddash);

        btn_iwr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/i^w^r.png"))); // NOI18N
        btn_iwr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_iwrMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_iwrMousePressed(evt);
            }
        });
        notation_data.add(btn_iwr);

        btn_iws.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button/64x64/i^w^s.png"))); // NOI18N
        btn_iws.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_iwsMouseEntered(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btn_iwsMousePressed(evt);
            }
        });
        notation_data.add(btn_iws);

        main.add(notation_data);
        notation_data.setBounds(30, 540, 1180, 540);

        container_hit_properties.setBackground(new java.awt.Color(8, 18, 38));
        container_hit_properties.setLayout(null);

        label_hit_properties.setText("Hit Properties");
        label_hit_properties.setFontSize(18.0F);
        container_hit_properties.add(label_hit_properties);
        label_hit_properties.setBounds(0, 10, 110, 20);

        input_hit_properties.setBackground(new java.awt.Color(217, 217, 217));
        input_hit_properties.setFont(label_hit_properties.getFont());
        input_hit_properties.setForeground(new java.awt.Color(0, 0, 0));
        input_hit_properties.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Hit Properties", "High", "Mid", "Low" }));
        input_hit_properties.setBorder(null);
        input_hit_properties.setFocusable(false);
        input_hit_properties.setPreferredSize(new java.awt.Dimension(140, 30));
        input_hit_properties.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                input_hit_propertiesActionPerformed(evt);
            }
        });
        container_hit_properties.add(input_hit_properties);
        input_hit_properties.setBounds(0, 30, 200, 40);

        main.add(container_hit_properties);
        container_hit_properties.setBounds(570, 110, 200, 70);

        container_notes.setBackground(new java.awt.Color(8, 18, 38));
        container_notes.setLayout(null);

        label_notes.setText("Notes");
        label_notes.setFontSize(18.0F);
        container_notes.add(label_notes);
        label_notes.setBounds(0, 0, 99, 20);

        area_notes.setBackground(new java.awt.Color(217, 217, 217));
        area_notes.setRoundBottomLeft(10);
        area_notes.setRoundBottomRight(10);
        area_notes.setRoundTopLeft(10);
        area_notes.setRoundTopRight(10);

        input_notes.setBackground(new java.awt.Color(217, 217, 217));
        input_notes.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout area_notesLayout = new javax.swing.GroupLayout(area_notes);
        area_notes.setLayout(area_notesLayout);
        area_notesLayout.setHorizontalGroup(
            area_notesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_notesLayout.createSequentialGroup()
                .addComponent(input_notes, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        area_notesLayout.setVerticalGroup(
            area_notesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area_notesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(input_notes, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addContainerGap())
        );

        container_notes.add(area_notes);
        area_notes.setBounds(0, 20, 860, 190);

        main.add(container_notes);
        container_notes.setBounds(50, 200, 860, 210);

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

    private boolean validateInput() {
        if (input_name_move.getText() == ""
                || input_damage.getText() == ""
                || notation_list.size() == 0
                || input_frame_start.getText() == ""
                || input_hit_properties.getSelectedItem() == "Select Hit Properties") {
            JOptionPane.showMessageDialog(this, "Please fill all the input field!");
            return false;
        }
        return true;
    }
    private void saveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMouseClicked
        // TODO add your handling code here:
        if (validateInput()) {
            HashMap data = new HashMap<>();
            data.put("notation", notation_list.toString());
            data.put("name_move", input_name_move.getText());
            data.put("damage", input_damage.getText());
            data.put("frame_startup", input_frame_start.getText());
            data.put("hit_properties", input_hit_properties.getSelectedItem());
            data.put("notes", input_notes.getText());
            if (id == -1) {
                insertData(data);
            } else {
                updateData(data);
            }
        }
    }//GEN-LAST:event_saveMouseClicked

    private void guide_path1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guide_path1MouseClicked
        // TODO add your handling code here:
        Guide guide = new Guide();
        guide.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_guide_path1MouseClicked

    private void guide_path1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guide_path1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_guide_path1MouseEntered

    private void input_hit_propertiesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_input_hit_propertiesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_input_hit_propertiesActionPerformed

    private void character_pathMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_character_pathMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_character_pathMouseEntered

    private void character_pathMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_character_pathMouseClicked
        // TODO add your handling code here:
        Character character;
        try {
            character = new Character(character_data);
            character.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }//GEN-LAST:event_character_pathMouseClicked

    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        // TODO add your handling code here:
        Character character;
        try {
            character = new Character(character_data);
            character.setVisible(true);
            this.dispose();
        } catch (SQLException ex) {
            System.out.println(ex.getCause());
        }
    }//GEN-LAST:event_backMouseClicked

    private void saveMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_saveMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_saveMouseEntered

    private void backspaceMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_backspaceMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_backspaceMouseEntered

    private void btn_1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_1MouseEntered

    private void btn_2MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_2MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_2MouseEntered

    private void btn_3MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_3MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_3MouseEntered

    private void btn_4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_4MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_4MouseEntered

    private void btn_1_2MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_2MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_1_2MouseEntered

    private void btn_1_3MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_3MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_1_3MouseEntered

    private void btn_1_4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_4MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_1_4MouseEntered

    private void btn_1_2_3MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_2_3MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_1_2_3MouseEntered

    private void btn_1_2_4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_2_4MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_1_2_4MouseEntered

    private void btn_1_2_3_4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_2_3_4MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_1_2_3_4MouseEntered

    private void btn_2_3MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_2_3MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_2_3MouseEntered

    private void btn_2_4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_2_4MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_2_4MouseEntered

    private void btn_3_4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_3_4MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_3_4MouseEntered

    private void btn_2_3_4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_2_3_4MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_2_3_4MouseEntered

    private void btn_1_3_4MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_3_4MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_1_3_4MouseEntered

    private void btn_nMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_nMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_nMouseEntered

    private void btn_fMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_fMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_fMouseEntered

    private void btn_dfMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_dfMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_dfMouseEntered

    private void btn_dMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_dMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_dMouseEntered

    private void btn_dbMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_dbMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_dbMouseEntered

    private void btn_bMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_bMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_bMouseEntered

    private void btn_ubMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ubMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_ubMouseEntered

    private void btn_uMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_uMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_uMouseEntered

    private void btn_ufMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ufMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_ufMouseEntered

    private void btn_f1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_f1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_f1MouseEntered

    private void btn_df1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_df1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_df1MouseEntered

    private void btn_d1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_d1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_d1MouseEntered

    private void btn_db1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_db1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_db1MouseEntered

    private void btn_b1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_b1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_b1MouseEntered

    private void btn_ub1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ub1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_ub1MouseEntered

    private void btn_u1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_u1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_u1MouseEntered

    private void btn_uf1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_uf1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_uf1MouseEntered

    private void btn_brace1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_brace1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_brace1MouseEntered

    private void btn_brace2MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_brace2MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_brace2MouseEntered

    private void btn_nextMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_nextMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_nextMouseEntered

    private void btn_colonMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_colonMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_colonMouseEntered

    private void btn_tildeMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_tildeMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_tildeMouseEntered

    private void btn_commaMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_commaMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_commaMouseEntered

    private void btn_delay1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_delay1MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_delay1MouseEntered

    private void btn_delay2MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_delay2MouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_delay2MouseEntered

    private void btn_bbMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_bbMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_bbMouseEntered

    private void btn_fblMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_fblMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_fblMouseEntered

    private void btn_fbMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_fbMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_fbMouseEntered

    private void btn_wboMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wboMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_wboMouseEntered

    private void btn_wblMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wblMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_wblMouseEntered

    private void btn_wbMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wbMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_wbMouseEntered

    private void btn_hMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_hMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_hMouseEntered

    private void btn_rageMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_rageMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_rageMouseEntered

    private void btn_ccMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ccMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_ccMouseEntered

    private void btn_chMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_chMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_chMouseEntered

    private void btn_dashMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_dashMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_dashMouseEntered

    private void btn_fcMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_fcMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_fcMouseEntered

    private void btn_holdMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_holdMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_holdMouseEntered

    private void btn_ssMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ssMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_ssMouseEntered

    private void btn_sslMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_sslMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_sslMouseEntered

    private void btn_ssrMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ssrMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_ssrMouseEntered

    private void btn_swlMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_swlMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_swlMouseEntered

    private void btn_swrMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_swrMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_swrMouseEntered

    private void btn_wMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_wMouseEntered

    private void btn_wrMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wrMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_wrMouseEntered

    private void btn_wsMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wsMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_wsMouseEntered

    private void btn_mdashMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_mdashMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_mdashMouseEntered

    private void btn_ddashMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ddashMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_ddashMouseEntered

    private void btn_cdMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_cdMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_cdMouseEntered

    private void btn_rMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_rMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_rMouseEntered

    private void btn_iwrMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_iwrMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_iwrMouseEntered

    private void btn_iwsMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_iwsMouseEntered
        // TODO add your handling code here:
        evt.getComponent().setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_btn_iwsMouseEntered

    private void btn_1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_1MousePressed

    private void btn_2MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_2MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_2MousePressed

    private void backspaceMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_backspaceMouseClicked
        // TODO add your handling code here:
        delNotation();
    }// GEN-LAST:event_backspaceMouseClicked

    private void backMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_backMouseEntered
        // TODO add your handling code here:
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_backMouseEntered

    private void home_pathMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_home_pathMouseEntered
        // TODO add your handling code here:
        home_path.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_home_pathMouseEntered

    private void home_pathMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_home_pathMouseClicked
        // TODO add your handling code here:
        Home home = new Home();
        home.setVisible(true);
        this.dispose();

    }// GEN-LAST:event_home_pathMouseClicked

    private void add_comboMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_add_comboMouseEntered
        // TODO add your handling code here:
        character_path.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }// GEN-LAST:event_add_comboMouseEntered

    private void btn_3MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_3MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_3MousePressed

    private void btn_4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_4MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_4MousePressed

    private void btn_1_2MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_2MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_1_2MousePressed

    private void btn_1_3MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_3MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_1_3MousePressed

    private void btn_1_4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_4MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_1_4MousePressed

    private void btn_2_3MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_2_3MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_2_3MousePressed

    private void btn_2_4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_2_4MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_2_4MousePressed

    private void btn_3_4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_3_4MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_3_4MousePressed

    private void btn_1_2_3MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_2_3MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_1_2_3MousePressed

    private void btn_1_2_4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_2_4MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_1_2_4MousePressed

    private void btn_1_3_4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_3_4MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_1_3_4MousePressed

    private void btn_2_3_4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_2_3_4MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_2_3_4MousePressed

    private void btn_1_2_3_4MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_1_2_3_4MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_1_2_3_4MousePressed

    private void btn_nMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_nMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_nMousePressed

    private void btn_fMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_fMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_fMousePressed

    private void btn_dfMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_dfMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_dfMousePressed

    private void btn_dMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_dMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_dMousePressed

    private void btn_dbMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_dbMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_dbMousePressed

    private void btn_bMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_bMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_bMousePressed

    private void btn_ubMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ubMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_ubMousePressed

    private void btn_uMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_uMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_uMousePressed

    private void btn_ufMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ufMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_ufMousePressed

    private void btn_f1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_f1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_f1MousePressed

    private void btn_df1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_df1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_df1MousePressed

    private void btn_d1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_d1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_d1MousePressed

    private void btn_db1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_db1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_db1MousePressed

    private void btn_b1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_b1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_b1MousePressed

    private void btn_ub1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ub1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_ub1MousePressed

    private void btn_u1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_u1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_u1MousePressed

    private void btn_uf1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_uf1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_uf1MousePressed

    private void btn_brace1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_brace1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_brace1MousePressed

    private void btn_brace2MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_brace2MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_brace2MousePressed

    private void btn_nextMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_nextMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_nextMousePressed

    private void btn_colonMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_colonMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_colonMousePressed

    private void btn_tildeMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_tildeMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_tildeMousePressed

    private void btn_commaMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_commaMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_commaMousePressed

    private void btn_delay1MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_delay1MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_delay1MousePressed

    private void btn_delay2MousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_delay2MousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_delay2MousePressed

    private void btn_bbMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_bbMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_bbMousePressed

    private void btn_fblMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_fblMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_fblMousePressed

    private void btn_fbMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_fbMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_fbMousePressed

    private void btn_wboMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wboMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_wboMousePressed

    private void btn_wblMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wblMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_wblMousePressed

    private void btn_wbMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wbMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_wbMousePressed

    private void btn_hMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_hMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_hMousePressed

    private void btn_rMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_rMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_rMousePressed

    private void btn_rageMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_rageMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_rageMousePressed

    private void btn_ccMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ccMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_ccMousePressed

    private void btn_chMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_chMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_chMousePressed

    private void btn_cdMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_cdMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_cdMousePressed

    private void btn_dashMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_dashMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_dashMousePressed

    private void btn_fcMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_fcMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_fcMousePressed

    private void btn_holdMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_holdMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_holdMousePressed

    private void btn_ssMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ssMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_ssMousePressed

    private void btn_sslMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_sslMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_sslMousePressed

    private void btn_ssrMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ssrMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_ssrMousePressed

    private void btn_swlMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_swlMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_swlMousePressed

    private void btn_swrMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_swrMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_swrMousePressed

    private void btn_wMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_wMousePressed

    private void btn_wrMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wrMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_wrMousePressed

    private void btn_wsMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_wsMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_wsMousePressed

    private void btn_mdashMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_mdashMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_mdashMousePressed

    private void btn_ddashMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_ddashMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_ddashMousePressed

    private void btn_iwrMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_iwrMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_iwrMousePressed

    private void btn_iwsMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_btn_iwsMousePressed
        // TODO add your handling code here:
        addNotation(evt);
    }// GEN-LAST:event_btn_iwsMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManageMovesheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageMovesheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageMovesheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageMovesheet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (Session.getId() == null) {
                    Login login = new Login();
                    login.setVisible(true);
                } else {
                    ManageMovesheet manage_movesheet = new ManageMovesheet();
                    manage_movesheet.setVisible(true);
                }
//                ManageMovesheet manage_movesheet = new ManageMovesheet();
//                manage_movesheet.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private utils.helper.RoundedPanel area_damage;
    private utils.helper.RoundedPanel area_frame_start;
    private utils.helper.RoundedPanel area_name_move;
    private utils.helper.RoundedPanel area_notes;
    private utils.helper.RoundedPanel back;
    private utils.helper.RoundedPanel backspace;
    private utils.helper.RopaLabel backspace_label;
    private javax.swing.JLabel btn_1;
    private javax.swing.JLabel btn_1_2;
    private javax.swing.JLabel btn_1_2_3;
    private javax.swing.JLabel btn_1_2_3_4;
    private javax.swing.JLabel btn_1_2_4;
    private javax.swing.JLabel btn_1_3;
    private javax.swing.JLabel btn_1_3_4;
    private javax.swing.JLabel btn_1_4;
    private javax.swing.JLabel btn_2;
    private javax.swing.JLabel btn_2_3;
    private javax.swing.JLabel btn_2_3_4;
    private javax.swing.JLabel btn_2_4;
    private javax.swing.JLabel btn_3;
    private javax.swing.JLabel btn_3_4;
    private javax.swing.JLabel btn_4;
    private javax.swing.JLabel btn_b;
    private javax.swing.JLabel btn_b1;
    private javax.swing.JLabel btn_bb;
    private javax.swing.JLabel btn_brace1;
    private javax.swing.JLabel btn_brace2;
    private javax.swing.JLabel btn_cc;
    private javax.swing.JLabel btn_cd;
    private javax.swing.JLabel btn_ch;
    private javax.swing.JLabel btn_colon;
    private javax.swing.JLabel btn_comma;
    private javax.swing.JLabel btn_d;
    private javax.swing.JLabel btn_d1;
    private javax.swing.JLabel btn_dash;
    private javax.swing.JLabel btn_db;
    private javax.swing.JLabel btn_db1;
    private javax.swing.JLabel btn_ddash;
    private javax.swing.JLabel btn_delay1;
    private javax.swing.JLabel btn_delay2;
    private javax.swing.JLabel btn_df;
    private javax.swing.JLabel btn_df1;
    private javax.swing.JLabel btn_f;
    private javax.swing.JLabel btn_f1;
    private javax.swing.JLabel btn_fb;
    private javax.swing.JLabel btn_fbl;
    private javax.swing.JLabel btn_fc;
    private javax.swing.JLabel btn_h;
    private javax.swing.JLabel btn_hold;
    private javax.swing.JLabel btn_iwr;
    private javax.swing.JLabel btn_iws;
    private javax.swing.JLabel btn_mdash;
    private javax.swing.JLabel btn_n;
    private javax.swing.JLabel btn_next;
    private javax.swing.JLabel btn_r;
    private javax.swing.JLabel btn_rage;
    private javax.swing.JLabel btn_ss;
    private javax.swing.JLabel btn_ssl;
    private javax.swing.JLabel btn_ssr;
    private javax.swing.JLabel btn_swl;
    private javax.swing.JLabel btn_swr;
    private javax.swing.JLabel btn_tilde;
    private javax.swing.JLabel btn_u;
    private javax.swing.JLabel btn_u1;
    private javax.swing.JLabel btn_ub;
    private javax.swing.JLabel btn_ub1;
    private javax.swing.JLabel btn_uf;
    private javax.swing.JLabel btn_uf1;
    private javax.swing.JLabel btn_w;
    private javax.swing.JLabel btn_wb;
    private javax.swing.JLabel btn_wbl;
    private javax.swing.JLabel btn_wbo;
    private javax.swing.JLabel btn_wr;
    private javax.swing.JLabel btn_ws;
    private utils.helper.RopaLabel character_path;
    private javax.swing.JPanel container_damage;
    private javax.swing.JPanel container_frame_start;
    private javax.swing.JPanel container_hit_properties;
    private javax.swing.JPanel container_name_move;
    private javax.swing.JPanel container_notes;
    private utils.helper.RopaLabel guide_path1;
    private utils.helper.RopaLabel home_path;
    private javax.swing.JTextField input_damage;
    private javax.swing.JTextField input_frame_start;
    private javax.swing.JComboBox<String> input_hit_properties;
    private javax.swing.JTextField input_name_move;
    private utils.helper.TextArea input_notes;
    private utils.helper.RopaLabel label_damage;
    private utils.helper.RopaLabel label_frame_start;
    private utils.helper.RopaLabel label_hit_properties;
    private utils.helper.RopaLabel label_name_move;
    private utils.helper.RopaLabel label_notes;
    private javax.swing.JPanel main;
    private utils.helper.RopaLabel manage_movesheet_path;
    private javax.swing.JPanel notation;
    private javax.swing.JPanel notation_data;
    private utils.helper.RoundedPanel notation_input;
    private utils.helper.RopaLabel notation_label;
    private javax.swing.JPanel root;
    private utils.helper.RopaLabel ropaLabel1;
    private utils.helper.RoundedPanel save;
    private utils.helper.RopaLabel save_label;
    // End of variables declaration//GEN-END:variables
}

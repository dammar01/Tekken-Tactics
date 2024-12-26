package utils.helper;

import java.awt.*;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import javax.swing.border.AbstractBorder;
import javax.swing.border.MatteBorder;

public class MovesheetTable extends JPanel {

    private JTable table;
    private JScrollPane scrollPane;
    private Font font;

    // Helper method to set column width
    private void setColumnWidth(JTable table, int col, int width) {
        table.getColumnModel().getColumn(col).setMinWidth(width);
        table.getColumnModel().getColumn(col).setMaxWidth(width);
    }

    // Setup columns of the table
    public void setupColumn() {
        // Set fixed column widths
        setColumnWidth(table, 0, 0);
        setColumnWidth(table, 1, 200);
        setColumnWidth(table, 2, 200);
        setColumnWidth(table, 3, 80);
        setColumnWidth(table, 4, 100);
        setColumnWidth(table, 5, 100);
        setColumnWidth(table, 6, 356);
        table.setDragEnabled(false);
        table.setCellSelectionEnabled(true);
        table.setColumnSelectionAllowed(false);
        table.setBackground(new Color(66, 21, 50));
        table.setForeground(Color.white);
        table.setGridColor(Color.black);
        table.setFont(font);

        JTableHeader header = this.table.getTableHeader();
        header.setDefaultRenderer(new CustomHeaderRenderer(this.font));
        header.setPreferredSize(new Dimension(header.getPreferredSize().width, 48));
        header.setResizingAllowed(false);
        header.setReorderingAllowed(false);
        header.setFocusable(false);

        CustomCellRenderer customColumn = new CustomCellRenderer();
        for (int i = 2; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(customColumn);
        }
    }

    // Constructor to initialize the table
    public MovesheetTable() {
        setBackground(new Color(66, 21, 50));
        setLayout(new BorderLayout());
        this.font = loadFont("/utils/font/RopaSans-Regular.ttf", 16f);

        // Column names and initial empty data
        String[] columnNames = {"id", "Moveset", "Name move", "Damage", "Frame startup", "Hit properties", "Notes"};
        Object[][] data = {};

        // Create custom table model and set it to the table
        DefaultTableModel model = new MultiImageTableModel(data, columnNames);
        this.table = new JTable(model);
        this.table.getColumnModel().getColumn(1).setCellRenderer(new MultiImageRenderer());
        this.table.setPreferredScrollableViewportSize(new Dimension(1036, 100));
        this.scrollPane = new ScrollBar(this.table);
        add(scrollPane, BorderLayout.CENTER);
        this.setupColumn();
    }

    // Load font from resource
    private Font loadFont(String fontPath, float size) {
        try {
            InputStream fontStream = getClass().getResourceAsStream(fontPath);
            if (fontStream == null) {
                throw new RuntimeException("Font file not found");
            }
            return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(size);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return new Font("Serif", Font.BOLD, 20);
        }
    }

    // Adjust row heights dynamically based on content
    private void adjustRowHeights() {
        for (int row = 0; row < table.getRowCount(); row++) {
            int maxHeight = table.getRowHeight(); // Default row height

            for (int column = 0; column < table.getColumnCount(); column++) {
                Component comp = table.prepareRenderer(table.getCellRenderer(row, column), row, column);

                // Calculate preferred height of each component
                if (comp != null) {
                    Dimension preferredSize = comp.getPreferredSize();
                    maxHeight = Math.max(maxHeight, preferredSize.height);
                }
            }

            // Set row height to the calculated maximum height
            table.setRowHeight(row, maxHeight + 10); // Add padding for better spacing
        }
    }

    // Set data for the table
    public void setData(Object[][] data) {
        String[] columnNames = {"id", "Moveset", "Name move", "Damage", "Frame startup", "Hit properties", "Notes"};
        MultiImageTableModel model = new MultiImageTableModel(data, columnNames);
        this.table.setModel(model);
        this.table.getColumnModel().getColumn(1).setCellRenderer(new MultiImageRenderer());
        this.adjustRowHeights();
        this.setupColumn();
    }
    
    public void removeSelectedRowData(int row) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.removeRow(row);
    }

    public ListSelectionModel getSelectionModel() {
        return this.table.getSelectionModel();
    }

    public int getSelectedRow() {
        return this.table.getSelectedRow();
    }

    public Integer getSelectedRowId(int rowIndex) {
        if (rowIndex < 0 || rowIndex >= table.getRowCount()) {
            throw new IllegalArgumentException("Row index out of bounds.");
        }

        Object value = table.getValueAt(rowIndex, 0);
        if (value == null) {
            throw new NullPointerException("The value at the specified row and column is null.");
        }

        try {
            return Integer.valueOf(value.toString());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("The value cannot be converted to an Integer.");
        }
    }

    public class CustomHeaderRenderer extends DefaultTableCellRenderer {

        private Font font;

        public CustomHeaderRenderer(Font font) {
            this.font = font;
            setOpaque(false);
            setBackground(Color.WHITE);
            setForeground(Color.BLACK);
            setFont(font);
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(new MatteBorder(0, 1, 0, 1, Color.BLACK));

            return this;
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int width = getWidth();
            int height = getHeight();
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, width, height, 0, 0);
            g2.setColor(Color.BLACK);
            g2.drawRoundRect(0, 0, width - 1, height - 1, 0, 0);
            super.paintComponent(g2);

            g2.dispose();
        }
    }

    public class CustomCellRenderer extends DefaultTableCellRenderer {

        private final JTextArea textArea;

        public CustomCellRenderer() {
            setOpaque(true);
            setBackground(new Color(66, 21, 50));
            setForeground(Color.white);
            setFont(font);
            setHorizontalAlignment(SwingConstants.CENTER);

            textArea = new JTextArea();
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setOpaque(true);
            textArea.setBackground(new Color(66, 21, 50));
            textArea.setForeground(Color.white);
            textArea.setFont(getFont());
            textArea.setEditable(false);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            textArea.setText(value != null ? value.toString() : "");
            textArea.setSize(table.getColumnModel().getColumn(column).getWidth(), Short.MAX_VALUE);
            // Adjust height based on the content
            int preferredHeight = textArea.getPreferredSize().height;
            table.setRowHeight(row, Math.max(preferredHeight + 30, table.getRowHeight()));
            textArea.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
            if (isSelected) {
                textArea.setBackground(new Color(202, 28, 100));
            } else {
                textArea.setBackground(new Color(66, 21, 50));
            }
            textArea.revalidate();
            textArea.repaint();
            return textArea;
        }
    }

    // Custom TableModel to handle multi-image columns
    private static class MultiImageTableModel extends DefaultTableModel {

        public MultiImageTableModel(Object[][] data, String[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            // Set column 0 to handle List<ImageIcon> as its value type
            if (columnIndex == 1) {
                return List.class;
            }
            return String.class;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Disable editing
        }
    }

    // Custom cell renderer for displaying multiple images in a single cell
    private static class MultiImageRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            // If the value is a list of ImageIcons
            if (value instanceof List) {
                List<ImageIcon> icons = (List<ImageIcon>) value;
                JPanel panel = new JPanel();
                panel.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
                panel.setOpaque(true);
                panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

                // Add each icon to the panel
                for (ImageIcon icon : icons) {
                    JLabel label = new JLabel(icon);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    panel.add(label);
                }
                panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));

                if (isSelected) {
                    panel.setBackground(new Color(202, 28, 100));
                } else {
                    panel.setBackground(new Color(66, 21, 50));
                }
                panel.revalidate();
                panel.repaint();
                return panel;
            }
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
            return this;
        }
    }
}

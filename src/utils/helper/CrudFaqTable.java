package utils.helper;

import java.awt.*;
import java.io.InputStream;
import javax.swing.*;
import javax.swing.table.*;
import java.util.List;
import javax.swing.border.MatteBorder;

public class CrudFaqTable extends JPanel {

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
        setColumnWidth(table, 1, 280);
        setColumnWidth(table, 2, 800);
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
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(customColumn);
        }
    }

    // Constructor to initialize the table
    public CrudFaqTable() {
        setBackground(new Color(66, 21, 50));
        setLayout(new BorderLayout());
        this.font = loadFont("/utils/font/RopaSans-Regular.ttf", 16f);

        // Column names and initial empty data
        String[] columnNames = {"", "Title", "Content"};
        Object[][] data = {};

        // Create custom table model and set it to the table
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        this.table = new JTable(model);
        this.table.setPreferredScrollableViewportSize(new Dimension(1090, 100));
        this.scrollPane = new ScrollBar(this.table);
        this.table.setRowHeight(140);
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

    // Set data for the table
    public void setData(Object[][] data) {
        String[] columnNames = {"", "Title", "Content"};
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        this.table.setModel(model);
        this.setupColumn();
    }

    public void setSelecedRow(int row) {
        if (row != -1) {
            this.table.setRowSelectionInterval(row, row);
        } else {
            table.clearSelection();
        }
    }

    public ListSelectionModel getSelectionModel() {
        return this.table.getSelectionModel();
    }

    public int getSelectedRow() {
        return this.table.getSelectedRow();
    }

    public void setSelectedRowData(int row, int column, String new_val) {
        TableModel model = this.table.getModel();
        model.setValueAt(new_val, row, column);
    }
    
    public void removeSelectedRowData(int row) {
        DefaultTableModel model = (DefaultTableModel) this.table.getModel();
        model.removeRow(row);
    }

    public Object[] getSelectedRowData(int rowIndex) {
        Object[] rowData = new Object[table.getColumnCount()];
        for (int i = 0; i < table.getColumnCount(); i++) {
            rowData[i] = table.getValueAt(rowIndex, i);
        }
        return rowData;
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
            textArea.setSize(table.getColumnModel().getColumn(column).getWidth() - 40, table.getRowHeight());
            textArea.setPreferredSize(new Dimension(table.getColumnModel().getColumn(column).getWidth() - 40, table.getRowHeight()));
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
}

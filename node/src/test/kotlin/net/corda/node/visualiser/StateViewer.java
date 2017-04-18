package net.corda.node.visualiser;

import kotlin.*;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class StateViewer {
    private JPanel root;
    private JTable propsTable;

    public static void main(String[] args) {
        JFrame frame = new JFrame("StateViewer");
        List<Pair<String, Object>> props = new ArrayList<Pair<String, Object>>();
        props.add(new Pair<String, Object>("a", 123));
        props.add(new Pair<String, Object>("things", "bar"));
        frame.setContentPane(new StateViewer(props).root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(800, 600);
    }

    public static void show(List<Pair<String, Object>> props) {
        JFrame frame = new JFrame("StateViewer");
        StateViewer viewer = new StateViewer(props);
        frame.setContentPane(viewer.root);
        frame.pack();
        frame.setSize(600, 300);

        viewer.propsTable.getColumnModel().getColumn(0).setMinWidth(150);
        viewer.propsTable.getColumnModel().getColumn(0).setMaxWidth(150);

        frame.setVisible(true);
    }

    private StateViewer(final List<Pair<String, Object>> props) {
        propsTable.setModel(new AbstractTableModel() {
            @Override
            public int getRowCount() {
                return props.size();
            }

            @Override
            public int getColumnCount() {
                return 2;
            }

            @Override
            public String getColumnName(int column) {
                if (column == 0)
                    return "Attribute";
                else if (column == 1)
                    return "Value";
                else
                    return "?";
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                if (columnIndex == 0)
                    return props.get(rowIndex).getFirst();
                else
                    return props.get(rowIndex).getSecond();
            }
        });
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new BorderLayout(15, 15));
        root.setBackground(new Color(-1));
        root.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15), null));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, label1.getFont().getSize()));
        label1.setText("State viewer");
        root.add(label1, BorderLayout.NORTH);
        final JScrollPane scrollPane1 = new JScrollPane();
        root.add(scrollPane1, BorderLayout.CENTER);
        propsTable = new JTable();
        propsTable.setAutoResizeMode(3);
        propsTable.setShowHorizontalLines(false);
        scrollPane1.setViewportView(propsTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }
}

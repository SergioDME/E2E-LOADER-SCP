/*
 * Created by JFormDesigner on Thu Jun 09 17:36:13 CEST 2022
 */

package View;
import Services.HomeView.E2eTestTableModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * @author unknown
 */
public class Home extends JFrame {
    public Home() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        panel1 = new JPanel();
        panel2 = new JPanel();
        pathJLabel = new JLabel();
        pathJField = new JTextField();
        pathJButton = new JButton();
        panel3 = new JPanel();
        scrollPane1 = new JScrollPane();
        e2eTestCases = new JTable();
        panel4 = new JPanel();
        scrollPane2 = new JScrollPane();
        scriptChosenTable = new JTable();
        panel5 = new JPanel();
        scriptNameJText = new JTextField();
        panel6 = new JPanel();
        label1 = new JLabel();
        saveButton = new JButton();
        CHART = new JPanel();

        //======== this ========
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout(5, 5));

        //======== panel1 ========
        {
            panel1.setLayout(new BorderLayout(5, 5));

            //======== panel2 ========
            {
                panel2.setLayout(new BorderLayout(5, 5));

                //---- pathJLabel ----
                pathJLabel.setText("Select End-to-End Test Folder..");
                panel2.add(pathJLabel, BorderLayout.CENTER);
                //panel2.add(pathJField, BorderLayout.CENTER);

                //---- pathJButton ----
                pathJButton.setText("Browse");
                pathJButton.setPreferredSize(new Dimension(30, 30));
                panel2.add(pathJButton, BorderLayout.WEST);
                pathJButton.addActionListener(e->{
                    JFileChooser chooser = new JFileChooser();
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setDialogTitle("Select Folder");

                    if (chooser.showOpenDialog(panel2) == JFileChooser.APPROVE_OPTION) {
                        File selectedPath = chooser.getSelectedFile();

                        JOptionPane.showMessageDialog(
                                panel2,
                                "Selected path:\n" + selectedPath.getAbsolutePath()
                        );

                        String path = selectedPath.getAbsolutePath();
                        this.setPath(path);
                        ArrayList<String> results =  new ArrayList<>();
                        File[] files = new File(path).listFiles();
                        for(File file : files) {
                            if(file.isFile()) {
                                results.add(file.getName());
                            }
                        }
                        E2eTestTableModel e2eTestTableModel = new E2eTestTableModel(results);
                        this.getE2eTestCases().setModel(e2eTestTableModel);
                        this.getE2eTestCases().getColumnModel().getColumn(0).setMaxWidth(30);
                        this.getE2eTestCases().getColumnModel().getColumn(2).setMaxWidth(30);
                    }
                });
            }
            panel1.add(panel2, BorderLayout.PAGE_START);

            //======== panel3 ========
            {
                panel3.setLayout(new BorderLayout(5, 5));

                //======== scrollPane1 ========
                {
                    scrollPane1.setMaximumSize(new Dimension(150, 150));
                    scrollPane1.setMinimumSize(new Dimension(150, 150));
                    scrollPane1.setPreferredSize(new Dimension(150, 150));

                    //---- e2eTestCases ----
                    e2eTestCases.setMaximumSize(new Dimension(500, 500));
                    e2eTestCases.setPreferredSize(new Dimension(500, 500));
                    e2eTestCases.setMinimumSize(new Dimension(500, 500));
                    e2eTestCases.setPreferredScrollableViewportSize(new Dimension(200, 200));
                    scrollPane1.setViewportView(e2eTestCases);
                }
                panel3.add(scrollPane1, BorderLayout.NORTH);

                //======== panel4 ========
                {
                    panel4.setLayout(new BorderLayout(5, 5));

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setMaximumSize(new Dimension(150, 150));
                        scrollPane2.setMinimumSize(new Dimension(150, 150));
                        scrollPane2.setPreferredSize(new Dimension(150, 150));

                        //---- scriptChosenTable ----
                        scriptChosenTable.setPreferredSize(new Dimension(150, 150));
                        scrollPane2.setViewportView(scriptChosenTable);
                    }
                    panel4.add(scrollPane2, BorderLayout.CENTER);

                    //======== panel5 ========
                    {
                        panel5.setLayout(new BorderLayout(5, 5));
                        panel5.add(scriptNameJText, BorderLayout.CENTER);

                        //======== panel6 ========
                        {
                            panel6.setLayout(new BorderLayout(5, 5));

                            //---- label1 ----
                            label1.setText(".jmx");
                            panel6.add(label1, BorderLayout.WEST);

                            //---- saveButton ----
                            saveButton.setText("SAVE");
                            saveButton.setBackground(new Color(245,121,0));
                            saveButton.setForeground(Color.WHITE);
                            panel6.add(saveButton, BorderLayout.CENTER);
                        }
                        panel5.add(panel6, BorderLayout.EAST);

                        //======== CHART ========
                        {
                            CHART.setMaximumSize(new Dimension(1000, 200));
                            CHART.setMinimumSize(new Dimension(1000, 200));
                            CHART.setLayout(new BorderLayout(5, 5));
                        }
                        panel5.add(CHART, BorderLayout.NORTH);
                    }
                    panel4.add(panel5, BorderLayout.SOUTH);
                }
                panel3.add(panel4, BorderLayout.CENTER);
            }
            panel1.add(panel3, BorderLayout.CENTER);
        }
        contentPane.add(panel1, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    private JPanel panel1;
    private JPanel panel2;
    private JLabel pathJLabel;
    private JTextField pathJField;
    private JButton pathJButton;
    private JPanel panel3;
    private JScrollPane scrollPane1;
    private JTable e2eTestCases;
    private JPanel panel4;
    private JScrollPane scrollPane2;
    private JTable scriptChosenTable;
    private JPanel panel5;
    private JTextField scriptNameJText;
    private JPanel panel6;
    private JLabel label1;
    private JButton saveButton;
    private JPanel CHART;
    // JFormDesigner - End of variables declaration  //GEN-END:variables

    private String path;

    public void setPath(String p) {this.path=p;}
    public String getPath(){return this.path;}

    public JPanel getCHART() {
        return CHART;
    }

    public JTable getScriptChosenTable() {
        return scriptChosenTable;
    }

    public void setScriptChosenTable(JTable scriptChosenTable) {
        this.scriptChosenTable = scriptChosenTable;
    }

    public JTextField getScriptNameJText() {
        return scriptNameJText;
    }

    public void setScriptNameJText(JTextField scriptNameJText) {
        this.scriptNameJText = scriptNameJText;
    }

    public JButton getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(JButton saveButton) {
        this.saveButton = saveButton;
    }

    public JLabel getPathJLabel() {
        return pathJLabel;
    }

    public void setPathJLabel(JLabel pathJLabel) {
        this.pathJLabel = pathJLabel;
    }

    public JTable getE2eTestCases() {
        return e2eTestCases;
    }

    public void setE2eTestCases(JTable e2eTestCases) {
        this.e2eTestCases = e2eTestCases;
    }


    public JTextField getPathJField() {
        return pathJField;
    }

    public void setPathJField(JTextField pathJField) {
        this.pathJField = pathJField;
    }


    public JButton getPathJButton() {
        return pathJButton;
    }

    public void setPathJButton(JButton pathJButton) {
        this.pathJButton = pathJButton;
    }

}

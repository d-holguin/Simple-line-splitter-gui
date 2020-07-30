/*
 * Main gui
 *     Copyright (c) 2020.  Dante
 *
 *     This program FilerReaderSplitLines is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program FilerReaderSplitLines is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program FilerReaderSplitLines .  If not, see <https://www.gnu.org/licenses/>.
 *
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.util.StringTokenizer;

/**
 * @ author Dante
 */

public class SplitLineGUI extends JFrame {


    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 400;

    private int linesToSplit;
    private JTextField rowAmountTField;
    private String fileToProcess;
    private String fileDirectoryName;
    private String fileName;
    private JTextArea infoTextArea;


    /**
     * adds the nested layout panel which contains all the panels to the
     * calculator frame making the gui calculator.
     */
    public SplitLineGUI() {

        createAllPanels();


        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }

    public void createAllPanels() {

        JPanel mainPanelLayout = new JPanel();
        mainPanelLayout.setLayout(new GridLayout(4, 1));
        mainPanelLayout.add(fileChoosePanel());
        mainPanelLayout.add(rowToSplitInfo());
        mainPanelLayout.add(outFileChooser());
        mainPanelLayout.add(outPutInfo());

        add(mainPanelLayout);

    }


    public JPanel fileChoosePanel() {
        final int FIELD_WIDTH = 30;

        JPanel mainPanel = new JPanel();
        JButton btn = new JButton("Choose A File To Process");

        JTextField fileTField = new JTextField(FIELD_WIDTH);
        fileTField.setEditable(false);
        btn.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();
            // set chooser options
            chooser.setDialogTitle("Choose A File To Process.");
            chooser.setPreferredSize(new Dimension(600, 400));
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();
                StringTokenizer st = new StringTokenizer(f.getName(), ".");
                fileName = "\\" + st.nextToken();

                // read  and/or display the file somehow. ....

                fileTField.setText(chooser.getSelectedFile().getAbsolutePath());
                fileToProcess = chooser.getSelectedFile().getAbsolutePath().trim();
            }

        });

        setVisible(true);
        setVisible(true);


        //=============================================================================================================

        mainPanel.add(btn);
        mainPanel.add(fileTField);
        mainPanel.add(fileTField);

        return mainPanel;
    }

    public JPanel outFileChooser() {

        final int FIELD_WIDTH = 30;

        JPanel panel2 = new JPanel();


        JButton outBtn = new JButton("Choose An Output Directory");

        JTextField outFileTField = new JTextField(FIELD_WIDTH);


        outFileTField.setEditable(false);
        outBtn.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            // optionally set chooser options ...
            //chooser.setCurrentDirectory(new java.io.File("."));
            chooser.setDialogTitle("Choose A Directory/Folder To Output Files To.");
            chooser.setPreferredSize(new Dimension(650, 400));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            chooser.setApproveButtonText("Choose directory");
            // chooser.setAcceptAllFileFilterUsed(false);
            int option = chooser.showDialog(null,
                    "Select Directory");

            if (option == JFileChooser.APPROVE_OPTION) {
                File f = chooser.getSelectedFile();

                if (!f.isDirectory()) {
                    f = f.getParentFile();
                }


                // displays file path in uneditable tfield and gets the dir path to output files to
                outFileTField.setText(f.getAbsolutePath());

                fileDirectoryName = f.getAbsolutePath();

            }


        });

        panel2.add(outBtn);
        panel2.add(outFileTField);


        return panel2;
    }

    public JPanel outPutInfo() {

        JPanel outInfoPanel = new JPanel();

        JButton runButton = new JButton("RUN");

        JTextArea infoTextArea = new JTextArea(4, 25);
        JScrollPane infoTAScroll = new JScrollPane(infoTextArea);

        infoTAScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        infoTAScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        infoTextArea.setEditable(false);

        outInfoPanel.add(runButton);
        outInfoPanel.add(infoTAScroll);
        class ProcessTheFile extends Component implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                try {
                    linesToSplit = Integer.parseInt(rowAmountTField.getText().trim());
                    ImageIcon icon = new ImageIcon("D:\\AllProjects\\JavaCleanProjects\\FilerReaderSplitLines\\Simple-line-splitter\\resources\\Megumin.jpg");
                    int dialogButton = JOptionPane.YES_NO_OPTION;
                    String formatedFileName = fileName.substring(1, fileName.length() - 1);




                    int dialogResult = JOptionPane.showConfirmDialog(this, "Are you sure you want to split (" + formatedFileName + ") \nevery "
                                    + NumberFormat.getInstance().format(linesToSplit) + "  lines to\n " + fileDirectoryName,

                            "Process File Confirmation", dialogButton, JOptionPane.INFORMATION_MESSAGE, icon);
                                    //formats the lines to split to a more readable format



                    if (dialogResult == 0) {


                        selectFile();
                        System.out.println("Yes option");

                    } else {
                        System.out.println("No Option");
                        return;

                    }

                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(null, "Enter only whole positive numbers in the text Field. You Entered " + error.getMessage());
                    rowAmountTField.setText("");
                }

            }

        }


        ActionListener processFileListener = new ProcessTheFile();
        runButton.addActionListener(processFileListener);


        return outInfoPanel;
    }

    public JPanel rowToSplitInfo() {

        JPanel rowInfoPanel = new JPanel();

        rowAmountTField = new JTextField("", 10);

        JLabel rowAmountLabel = new JLabel("Enter The Amount Of Lines To Split, Numbers Only.");

        rowInfoPanel.add(rowAmountLabel);
        rowInfoPanel.add(rowAmountTField);
        return rowInfoPanel;
    }

    public void selectFile() {
        long startTime = System.currentTimeMillis();

        ReadFile readFile = new ReadFile(fileToProcess);
        readFile.splitFile(fileToProcess, linesToSplit, fileDirectoryName, fileName);

        long endTime = System.currentTimeMillis();
        System.out.println("That took " + (endTime - startTime) + " milliseconds");


    }


}

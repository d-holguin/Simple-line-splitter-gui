/*
 * Displays the main Jframe of the program and the LAF
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


import com.formdev.flatlaf.FlatLightLaf;


import javax.swing.*;


/**
 * @ author Dante
 */

public class MainViewer {


    public static void main(String[] args) {


        FlatLightLaf.install();
        JFrame.setDefaultLookAndFeelDecorated(true); //sets the window decoration

        JFrame frame = new SplitLineGUI();
        ImageIcon img = new ImageIcon(MainViewer.class.getResource("fileIconTopLeftDecoration.png"));

      //ImageIcon image = new ImageIcon(this.getClass().getResource("D:\\AllProjects\\JavaCleanProjects\\FilerReaderSplitLines\\Simple-line-splitter\\resources\\Megumin.jpg"));
//
       frame.setIconImage(img.getImage());


        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Split line GUI");
        frame.setVisible(true);


    }
}











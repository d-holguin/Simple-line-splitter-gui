/*
 * Program to read a file and split it on it on its lines.
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
import java.io.*;
import java.util.Arrays;
import java.util.List;


/**
 * @ author Dante
 */

class ReadFile {

    private BufferedReader br;
    private List<String> allowedExtensions = Arrays.asList(".txt", ".csv", ".java", ".sql" , "rtf");

    ReadFile(String fileName) {
        File file = new File(fileName);
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error reading file " );
        }
    }

    private String obtainExtension(String fileName) {
        String extension = "";
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i);
        }
        return extension;
    }

    private String writeSplitFile(String splittedFileName, int numRows, String textLine) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(splittedFileName));
        for (int i = 0; i < numRows && textLine != null; i++) {
            writer.write(textLine + "\n");
            textLine = br.readLine();
        }
        writer.close();
        return textLine;
    }

    void splitFile(String fileName, int numRows, String fileDir, String theFile) {
        String st;
        int numFiles = 0;


        System.out.println(fileName);

        try {
            String extensionFile = obtainExtension(fileName);

            if (br != null && allowedExtensions.contains(extensionFile)) {
                st = br.readLine();
                while (st != null) {

                    String newName = fileDir  + theFile + "-v" + numFiles + extensionFile;

                    st = writeSplitFile(newName, numRows, st);

                    numFiles++;

                    System.out.println(newName);
                }
                br.close();
            } else {
                JOptionPane.showMessageDialog(null, "Error, Incompatible file " );
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}

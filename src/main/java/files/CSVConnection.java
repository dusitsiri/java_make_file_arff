package files;

import com.opencsv.CSVReader;
import databases.DBCsvConnection;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CSVConnection {

    static String[][] mapData = new String[2216][15];
    static String[] attribute = {"business", "startup", "marketing", "leadership", "news", "technology", "eco", "iot", "digital", "life", "entrepreneur", "innovation", "#ai", "data", "social"};

    public static void main(String[] args) {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Would you like to do[s:showfile, r:readfile, w:writefile, 0:exit]?: ");
            String text = bf.readLine();
            while (text != null) {
                if (text.matches("[rR]")) {
                    System.out.print("Enter your filename to read: ");
                    text = bf.readLine();
                    readFile(text);
                } else if (text.matches("[wW]")) {
                    System.out.print("Enter your filename to write: ");
                    text = bf.readLine();
                    writeFile(text);
                } else if (text.matches("[sS]")) {
                    System.out.print("Enter your filename to show: ");
                    text = bf.readLine();
                    showFile(text);
                } else {
                    System.exit(0);
                }
                System.out.print("Would you like to do[s:showfile, r:readfile, w:writefile, 0:exit]?: ");
                text = bf.readLine();
            }
        } catch (IOException e) {
            System.err.println("read or write error!!");
        }
    }

    public static void readFile(String fileName) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int i = 0; i < attribute.length; i++) {
                    if (line.toLowerCase().trim().contains(attribute[i])) {
                        mapData[row][i] = "yes";
                    } else {
                        mapData[row][i] = "no";
                    }
                }
                row++;
            }
            reader.close();
        } catch (
                IOException e) {
            System.err.println("Cannot open file");
        }
    }


    public static void writeFile(String filename) {
        File file;
        try {
            file = new File(filename);
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write("@relation dataTechnology\n\n");
            for (int i = 0; i < attribute.length; i++) {
                writer.write("@attribute " + attribute[i] + " {yes, no}\n");
            }
            writer.write("\n");
            writer.write("@data\n");
            for (int i = 0; i < mapData.length; i++) {
                for (int j = 0; j < attribute.length; j++) {
                    if (j < 14) {
                        writer.write(mapData[i][j] + ",");
                    } else if (j == 14) {
                        writer.write(mapData[i][j] + "\n");
                    }
                }
            }
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void showFile(String filename) {
        try {
            BufferedReader bf = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = bf.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

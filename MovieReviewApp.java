package project3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.*;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;

public class MovieReviewApp{
    static JFrame f;
    static JTextField inputText;
    static JTextArea outText;
    static JButton load,delete,search;
    static JFileChooser fileChooser;


    static final Scanner CONSOLE_INPUT = new Scanner(System.in);
    private final static String newline = "\n";

    MovieReviewApp(){
        f = new JFrame("MovieReviewApp");
        GridLayout grid = new GridLayout(5,3,2,4);
        inputText =new JTextField();


        outText = new JTextArea(50, 20);
        outText.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(outText);




        load = new JButton("Load Database");
        delete = new JButton("Delete");
        search = new JButton("Search");


        //f.add(outText);
        f.add(scrollPane);
        f.add(load);
        f.add(delete);
        f.add(search);
        f.add(inputText);


        f.setLayout(grid);
        f.setVisible(true);
        f.setSize(350,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);

    }


    private static class ButtonListener implements ActionListener {
        ReviewHandler rh = new ReviewHandler();

        public ButtonListener(ReviewHandler rh){
            this.rh = rh;
        }

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == load){
                // 1. Load new movie review collection (given a folder or a file path).
                //System.out.println("Please input the path of file or folder.");
                // ./Data/Movie-reviews/neg

                fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int returnVal = fileChooser.showOpenDialog(null);


                if (returnVal == JFileChooser.APPROVE_OPTION){
                    outText.append("Loading DataBase from: " + newline + fileChooser.getSelectedFile().getAbsolutePath());
                }
                else{
                    outText.append("Selection cancelled" + newline);
                }


         /*
                String path = inputText.getText();
                inputText.setText(newline);
                System.out.println(path);

                //check if path exists


                if (Files.exists(Path.of(path))){
                    System.out.println("loaded reviews @$");
                    outText.append("Please input real class (0, 1, 2)." + newline);
                    outText.append("0 = negative, 1 = positive, 2 = unknown." + newline);

                    //default is unknown
                    int realClass = 2;
                    String reviewStr = CONSOLE_INPUT.nextLine();

                    if (!reviewStr.matches("-?(0|[1-9]\\d*)")) {
                        // Input is not an integer
                        System.out.println("Illegal input.");
                    } else {
                        realClass = Integer.parseInt(reviewStr);
                        if (realClass < 0 && realClass > 2){
                            System.out.println("Not a correct choice, setting ground truth classification to UNKNOWN");
                            realClass = 2;
                        }
                        rh.loadReviews(path, realClass);
                        System.out.println("Database size: " + rh.getDatabase().size());
                    }
                }else{
                    System.out.println("Not a correct file or folder: " + path);
                    System.out.println("Try again.");
                }




          */




            }











            if (e.getSource()== delete){
                // 2. Delete movie review from database (given its id).
                System.out.println("Please input review ID.");
                String idStr = CONSOLE_INPUT.nextLine();

                if (!idStr.matches("-?(0|[1-9]\\d*)")) {
                    // Input is not an integer
                    System.out.println("Illegal input.");
                } else {
                    int id = Integer.parseInt(idStr);
                    rh.deleteReview(id);
                }
            }


            if (e.getSource() == search){
                // 3. Search movie reviews in database by id or by matching a substring.
                System.out.println("Please input your command (1, 2).");
                System.out.println("1 = search by ID.");
                System.out.println("2 = search by substring");
                String command = CONSOLE_INPUT.nextLine();
                if (command.equals("1")) {
                    System.out.println("Please input review ID.");
                    String idStr = CONSOLE_INPUT.nextLine();
                    if (!idStr.matches("-?(0|[1-9]\\d*)")) {
                        // Input is not an integer
                        System.out.println("Illegal input.");
                    } else {
                        int id = Integer.parseInt(idStr);
                        MovieReview mr = rh.searchById(id);
                        if (mr != null) {
                            //printTableHead();
                            //printTableContent(mr);
                        } else {
                            System.out.println("Review not found.");
                        }
                    }

                } else if (command.equals("2")) {
                    System.out.println("Please input substring.");
                    String substring = CONSOLE_INPUT.nextLine();
                    List<MovieReview> reviewList = rh.searchBySubstring(substring);
                    if (reviewList != null) {
                        printTableHead();
                        for (MovieReview mr : reviewList) {
                            printTableContent(mr);
                        }
                        System.out.println(reviewList.size() + " reviews found.");

                    } else {
                        System.out.println("Review not found.");
                    }
                } else {
                    System.out.println("Illegal input.");
                }
            }
        }



        public static void printTableHead() {
            String line = "------------------------------------------------------------------------------------------";
            String information = "| ";
            information = information + String.format("%4s", "ID") + " @ ";
            information = information + String.format("%53s", "Text") + " @ ";
            information = information + String.format("%10s", "Predicted") + " @ ";
            information = information + String.format("%10s", "Real") + " @ ";

            System.out.println(line);
            System.out.println(information);
            System.out.println(line);
        }

        public static void printTableContent(MovieReview mr) {
            String line = "------------------------------------------------------------------------------------------";
            String information = "| ";
            information = information + String.format("%4s", mr.getId()) + " @ ";
            information = information + String.format("%53s", mr.getText().substring(0, 50)+"..." ) + " @ ";
            information = information + String.format("%10s", mr.getPredictedScore()) + " @ ";
            information = information + String.format("%10s", mr.getPredictedScore()) + " @ ";

            System.out.println(information);
            System.out.println(line);
        }

    }
    public static void main(String [] args) {
        // Create ReviewHandler object
        ReviewHandler rh = new ReviewHandler();

        new MovieReviewApp();
        ButtonListener listen = new ButtonListener(rh);

        outText.append("Please click load database and find the directory containing the database." + newline);

        //-------------------------Stuff from orignal movie review app ----------------------
        // Check if the correct number of arguments was provided
        /*
        if (args.length < 2) {
            System.err.println("Please provide command liner arguments: <posFilePath> and <negFilePath>");
            return;
        }
        */


        String pathToPosWords = "./data/positive-words.txt";
        String pathToNegWords = "./data/negative-words.txt";



        try {
            // Load the positive and negative words
            rh.loadPosNegWords(pathToPosWords, pathToNegWords);
            System.out.println("loaded words");
        } catch (IOException ex) {
            System.err.println("Could not load positive and negative words. "
                    + "Please check that the file paths are correct and try again.");
            return;
        }

        try {
            // Load database if it exists.
            rh.loadDB();
        } catch (IOException ex) {
            System.err.println("Error accessing the database file.");
            return;
        }

        String welcomeMessage = "\nDatabase size: " + rh.getDatabase().size() +
                "\nChoose one of the following functions:\n\n"
                + "\t 1. Load new movie review collection (given a folder or a file path).\n"
                + "\t 2. Delete movie review from database (given its id).\n"
                + "\t 3. Search movie reviews in database by id or by matching a substring.\n";
        //System.out.println(welcomeMessage);
        //t.setText(welcomeMessage);
        //-------------------------------------------------------------------------------------
        load.addActionListener(listen);
        delete.addActionListener(listen);
        search.addActionListener(listen);

    }
}
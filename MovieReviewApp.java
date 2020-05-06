package project3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MovieReviewApp{
    static JFrame f;
    static JTextField t;
    static JButton load,delete,search;

    static final Scanner CONSOLE_INPUT = new Scanner(System.in);

    MovieReviewApp(){
        f=new JFrame("MovieReviewApp");
        GridLayout grid = new GridLayout(8,3,2,4);
        t=new JTextField();

        load = new JButton("Load");
        delete = new JButton("Delete");
        search = new JButton("Search");

        f.add(t);
        f.add(load);
        f.add(delete);
        f.add(search);

        f.setLayout(grid);
        f.setVisible(true);
        f.setSize(350,500);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setResizable(true);
    }
    private static class ButtonListener implements ActionListener {
        ReviewHandler rh;

        public ButtonListener(ReviewHandler rh){
            this.rh = rh;
        }

        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == load){
                // 1. Load new movie review collection (given a folder or a file path).
                System.out.println("Please input the path of file or folder.");
                // ./Data/Movie-reviews/neg
                String path = CONSOLE_INPUT.nextLine();
                //check if path exists
                if (Files.exists(Path.of(path))){

                    System.out.println("Please input real class (0, 1, 2).");
                    System.out.println("0 = negative, 1 = positive, 2 = unknown.");

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
                            printTableHead();
                            printTableContent(mr);
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

        //-------------------------Stuff from orignal movie review app ----------------------
        // Check if the correct number of arguments was provided
        if (args.length < 2) {
            System.err.println("Please provide command liner arguments: <posFilePath> and <negFilePath>");
            return;
        }

        String pathToPosWords = args[0];
        String pathToNegWords = args[1];



        try {
            // Load the positive and negative words
            rh.loadPosNegWords(pathToPosWords, pathToNegWords);
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
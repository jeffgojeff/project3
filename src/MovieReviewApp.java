package project3;

import java.util.Scanner;

import javax.swing.*;
import java.awt.event.*;
//import java.awt.GridLayout;
import java.awt.*;
import java.io.*;

/**
 * Main application class. Provides functionality for interacting with the user.
 @author metsis
 @author tesic
 @author wen
 */
public class MovieReviewApp implements ActionListener {

    // Used to read from System's standard input
    static final Scanner CONSOLE_INPUT = new Scanner(System.in);

    public ReviewHandler rh = new ReviewHandler();

    public JFrame mainFrame, reviewFrame, negPos, warningWords, warningFrame;
    public JButton loadDB, loadReviews, newWindow,neg, pos, okWords, okWarning, okReviews, selectReviews, okWarnReviews;
    public JRadioButton rb1, rb2, rb3;
    public JFileChooser fileChooser;
    public JLabel warning, reviewsClass, reviewsInfo, warningReviews;
    public JPanel reviewsBG;
    public JTextField reviewsPath;



    MovieReviewApp(){
        //  load words \\
        //**************\\
        negPos = new JFrame("Load Words");
        GridLayout grid1 = new GridLayout(2,2,50,10);

        neg = new JButton("Select Negative Words File");
        neg.addActionListener(this);
        pos = new JButton("Select Positive Words File");
        pos.addActionListener(this);
        okWords = new JButton("Load Words");
        okWords.addActionListener(this);

        negPos.add(neg);
        negPos.add(pos);
        negPos.add(okWords);

        negPos.setLayout(grid1);
        ///change back to true
        negPos.setVisible(true);
        negPos.setSize(500, 200);
        negPos.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        negPos.setResizable(false);


        //   warning   \\
        //**************\\
        warningWords = new JFrame("Warning");
        GridLayout grid2 = new GridLayout(2,1,100,50);

        warning = new JLabel("Please select word lists before continuing");
        okWarning = new JButton("Ok");
        okWarning.addActionListener(this);

        warningWords.add(warning);
        warningWords.add(okWarning);

        warningWords.setLayout(grid2);
        warningWords.setVisible(false);
        warningWords.setSize(300, 200);
        warningWords.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        warningWords.setResizable(false);





        //  Main Frame \\
        //**************\\

        mainFrame = new JFrame("MovieReviewApp");
        GridLayout grid = new GridLayout(5,3,2,4);

        loadDB = new JButton("Load DataBase");
        loadDB.addActionListener(this);

        loadReviews = new JButton("Load Reviews");
        loadReviews.addActionListener(this);

        newWindow = new JButton("new Window");
        newWindow.addActionListener(this);


        mainFrame.add(loadDB);
        mainFrame.add(loadReviews);
        mainFrame.add(newWindow);

        mainFrame.setLayout(grid);
        ///change back to false
        mainFrame.setVisible(false);
        mainFrame.setSize(350,500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);




        // load reviews class selection \\
        //*******************************\\
        reviewFrame = new JFrame("Load Reviews");
        GridLayout gridReview = new GridLayout(4,2,2,4);

        reviewsClass = new JLabel("Please select the real class for the reviews");
        reviewsClass.setHorizontalAlignment(JLabel.CENTER);

        reviewsBG = new JPanel();
        ButtonGroup group = new ButtonGroup();

        rb1 = new JRadioButton("Negative");
        rb1.addActionListener(this);
        rb2 = new JRadioButton("Positive");
        rb2.addActionListener(this);
        rb3 = new JRadioButton("Unknown");
        rb3.addActionListener(this);

        okReviews = new JButton("Load Selected Reviews");
        okReviews.addActionListener(this);
        selectReviews = new JButton("Open File");
        selectReviews.addActionListener(this);

        group.add(rb1);
        group.add(rb2);
        group.add(rb3);

        reviewsPath = new JTextField();
        reviewsInfo = new JLabel("Enter reviews path: ");



        reviewsBG.add(rb1);
        reviewsBG.add(rb2);
        reviewsBG.add(rb3);

        reviewFrame.add(reviewsClass);
        reviewFrame.add(reviewsBG);
        reviewFrame.add(reviewsInfo);
        reviewFrame.add(reviewsPath);
        reviewFrame.add(okReviews);

        reviewFrame.setLayout(gridReview);
        reviewFrame.setSize(600,300);
        reviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reviewFrame.setVisible(false);





         //  loadReviews Warning \\
        //***********************\\
        warningFrame = new JFrame("Warning");
        GridLayout gridReviews = new GridLayout(2,1,100,50);

        warningReviews = new JLabel("Please select real class and enter a correct destination before continuing");
        warningReviews.setHorizontalAlignment(JLabel.CENTER);
        okWarnReviews = new JButton("Ok");
        okWarnReviews.addActionListener(this);

        warningFrame.add(warningReviews);
        warningFrame.add(okWarnReviews);

        warningFrame.setLayout(gridReviews);
        warningFrame.setVisible(false);
        warningFrame.setSize(300, 200);
        warningFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        warningFrame.setResizable(false);






    }



    public void actionPerformed (ActionEvent event) {



        if (event.getSource() == loadDB) {
            //fileChooser = new JFileChooser();
            //fileChooser.setCurrentDirectory(new File("."));
            //int returnVal = fileChooser.showOpenDialog(null);

            try {
                rh.loadDB();
                System.out.println("loaded");
            } catch (IOException e) {
                System.out.println("Error Loading DataBase");
                System.out.println("Please try again");
                return;
            }
        }











        if (event.getSource() == loadReviews) {

            reviewFrame.setVisible(true);

        }
        if(event.getSource() == okReviews){
            int realClass = -1;

            if(rb1.isSelected()){ realClass = 0; }
            if(rb2.isSelected()){ realClass = 1; }
            if(rb3.isSelected()){ realClass = 2; }
            if(realClass == -1 || reviewsPath.getText().isEmpty()){
                System.out.println("box box: " + reviewsPath.getText());
                warningFrame.setVisible(true);
            }

            if(realClass < 2 && realClass > -1 && !reviewsPath.getText().isEmpty()){
                try {
                    rh.loadReviews(reviewsPath.getText(), realClass);
                    reviewFrame.dispose();
                }
                catch(NullPointerException e){
                    warningFrame.setVisible(true);
                    System.out.println("error loading reviews");
                    return;
                }
            }




        }
        if(event.getSource() == okWarnReviews){
            warningFrame.dispose();
        }











        if (event.getSource() == pos) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                rh.setPosWordsFilePath(fileChooser.getSelectedFile().getAbsolutePath().toString());
                System.out.println("testing filePath: " + rh.getPosWordsFilePath());
            }

        }
        if (event.getSource() == neg) {
            fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("."));
            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                rh.setNegWordsFilePath(fileChooser.getSelectedFile().getAbsolutePath().toString());
                System.out.println("testing filePath: " + rh.getNegWordsFilePath());
            }
        }
        if (event.getSource() == okWords) {
            if ((rh.getPosWordsFilePath() != "") && (rh.getNegWordsFilePath() != "") &&
                    (rh.getNegWordsFilePath().contains(".txt")) && rh.getPosWordsFilePath().contains(".txt")) {
                try {
                    rh.loadPosNegWords();
                    //System.out.println("Passed");
                    negPos.dispose();
                    mainFrame.setVisible(true);
                } catch (IOException e) {
                    System.out.println("Error Loading Words");
                    System.out.println("Please try again");
                    return;
                }
            }
            else{
                //System.out.println("failed");
                warningWords.setVisible(true);
            }
        }
        if(event.getSource() == okWarning){
            warningWords.dispose();
        }






    }









    public static void main(String [] args) {

        new MovieReviewApp();


    }

        /*
        // Check if the correct number of arguments was provided
        if (args.length < 2) {
            System.err.println("Please provide command liner arguments: <posFilePath> and <negFilePath>");
            return;
        }

        String pathToPosWords = args[0];
        String pathToNegWords = args[1];

        // Create ReviewHandler object
        ReviewHandler rh = new ReviewHandler();

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
                + "\t 0. Exit program.\n"
                + "\t 1. Load new movie review collection (given a folder or a file path).\n"
                + "\t 2. Delete movie review from database (given its id).\n"
                + "\t 3. Search movie reviews in database by id or by matching a substring.\n";

        //Output welcome message
        System.out.println(welcomeMessage);
        String selection = CONSOLE_INPUT.nextLine();

        while (!selection.equals("0")) {
            switch (selection) {
                case "1":
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
                    break;
                case "2":
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

                    break;

                case "3":
                    // 3. Search movie reviews in database by id or by matching a substring.
                    System.out.println("Please input your command (1, 2).");
                    System.out.println("1 = search by ID.");
                    System.out.println("2 = search by substring");
                    String command = CONSOLE_INPUT.nextLine();
                    if (command.equals("1")) {
                        System.out.println("Please input review ID.");
                        idStr = CONSOLE_INPUT.nextLine();
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
                    break;
                case "h":
                    System.out.println(welcomeMessage);
                    break;
                default:
                    System.out.println("That is not a recognized command. Please enter another command or 'h' to list the commands.");
                    break;
            }

            System.out.println("Please enter another command or 'h' to list the commands.\n");
            selection = CONSOLE_INPUT.nextLine();
        }

        try {
            // Save the database before exiting.
            rh.saveDB();
        } catch (IOException ex) {
            System.err.println("Error: The database file could not be saved.");
        }
        System.out.println("Goodbye!");
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

         */

}
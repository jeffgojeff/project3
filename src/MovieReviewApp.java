package project3;

import java.util.Scanner;

import javax.swing.*;
import java.awt.event.*;
//import java.awt.GridLayout;
import java.awt.*;
import java.io.*;
import java.util.List;

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

    public JFrame mainFrame, reviewFrame, negPos, warningWords, warningFrame,
            searchIdFrame, searchSubFrame, deleteFrame;
    public JButton loadDB, loadReviews, neg, pos, okWords,
            okWarning, okReviews, selectReviews, okWarnReviews,
            searchReview, searchId, searchClose, searchSub, searchSubClose,
            searchSubReview, deleteReview, deleteReviewButton, deleteReviewClose, saveDB;
    public JRadioButton rb1, rb2, rb3;
    public JFileChooser fileChooser;
    public JLabel warning, reviewsClass, reviewsInfo, warningReviews, searchLabel, searchSubLabel,
                deleteLabel, mainLabel;
    public JPanel reviewsBG, mainPanel;
    public JTextField reviewsPath, searchIdText, searchSubText, deleteText;
    public JTextArea mainArea, searchIdArea, searchSubArea;
    public JTable mainTable;



    MovieReviewApp(){

        //  load words \\
        //**************\\
        negPos = new JFrame("Load Words");
        GridBagLayout grid1 = new GridBagLayout();
        GridBagConstraints w = new GridBagConstraints();
        w.anchor = GridBagConstraints.CENTER;
        negPos.setLayout(grid1);
        w.insets = new Insets(2,2,2,2);

        w.gridx = 0;
        w.gridy = 0;
        neg = new JButton("Select Negative Words File");
        neg.addActionListener(this);
        negPos.add(neg, w);

        w.gridx=1;
        pos = new JButton("Select Positive Words File");
        pos.addActionListener(this);
        negPos.add(pos, w);

        w.insets = new Insets(20,0,0,0);
        w.gridx=0;
        w.gridy=1;
        w.gridwidth = 2;
        okWords = new JButton("Load Selected Files");
        okWords.addActionListener(this);
        negPos.add(okWords, w);




        ///change back to true
        negPos.setVisible(false);
        negPos.setSize(500, 200);
        negPos.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        negPos.setResizable(false);


        //   warning   \\
        //**************\\
        warningWords = new JFrame("Warning");
        GridBagLayout grid2 = new GridBagLayout();
        warningWords.setLayout(grid2);
        GridBagConstraints cc = new GridBagConstraints();
        //cc.fill = GridBagConstraints.HORIZONTAL;
        cc.insets = new Insets(2,2,2,2);
        cc.anchor = GridBagConstraints.CENTER;

        cc.gridx=0;
        cc.gridy=0;
        warning = new JLabel("Please select word lists before continuing");
        warningWords.add(warning, cc);

        cc.insets = new Insets(20,0,0,0);
        cc.gridy=1;
        okWarning = new JButton("OK");
        okWarning.addActionListener(this);
        warningWords.add(okWarning, cc);

        warningWords.setVisible(false);
        warningWords.setSize(300, 180);
        warningWords.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        warningWords.setResizable(false);



        //  Main Frame \\
        //**************\\

        mainFrame = new JFrame("MovieReviewApp");
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        mainFrame.setLayout(grid);
        c.insets = new Insets(2,2,2,2);
        c.anchor = GridBagConstraints.CENTER;

        mainLabel = new JLabel("Movie Review App");
        //Font labelFont = label.getFont();
        mainLabel.setFont(new Font(mainLabel.getFont().getName(), Font.BOLD, 26));
        c.gridy = 0;
        c.gridx = 0;
        mainFrame.add(mainLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;

        mainPanel = new JPanel();
        mainPanel.setLayout(grid);

        c.gridx = 0;
        c.gridy = 1;
        c.ipadx = 15;
        c.ipady = 50;
        loadDB = new JButton("Load DataBase");
        loadDB.addActionListener(this);
        mainPanel.add(loadDB, c);

        c.gridx = 1;
        c.gridy = 1;
        saveDB = new JButton("Save DataBase");
        saveDB.addActionListener(this);
        mainPanel.add(saveDB, c);

        c.gridx = 0;
        c.gridy = 2;
        loadReviews = new JButton("Load Reviews");
        loadReviews.addActionListener(this);
        mainPanel.add(loadReviews,c);

        c.gridx = 1;
        c.gridy = 2;
        searchReview = new JButton("search id");
        searchReview.addActionListener(this);
        mainPanel.add(searchReview, c);

        c.gridx = 0;
        c.gridy = 3;
        searchSubReview = new JButton("search sub");
        searchSubReview.addActionListener(this);
        mainPanel.add(searchSubReview, c);

        c.gridx = 1;
        c.gridy = 3;
        deleteReview = new JButton("delete Review");
        deleteReview.addActionListener(this);
        mainPanel.add(deleteReview, c);

        c.gridx = 0;
        c.gridy = 3;
        mainFrame.add(mainPanel, c);


        /*
        mainArea = new JTextArea();
        mainArea.setEditable(false);
         */
        String[] columnNames = { "Id", "something", "something" };
        String[][] data = {
                { "Kundan Kumar Jha", "4031", "CSE" },
                { "Anand Jha", "6014", "IT" }
        };
        mainTable = new JTable(data, columnNames);

        //c.gridx = 0;
        c.gridy = 4;
        c.ipadx = 800;
        c.ipady = 300;

        mainFrame.add(mainTable, c);


        ///change back to false
        mainFrame.setVisible(false);
        mainFrame.setSize(800,800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);




        // load reviews class selection \\
        //*******************************\\
        reviewFrame = new JFrame("Load Reviews");
        GridBagLayout gridReview = new GridBagLayout();
        reviewFrame.setLayout(gridReview);
        GridBagConstraints g = new GridBagConstraints();
        g.insets = new Insets(2,2,2,2);
        g.anchor = GridBagConstraints.CENTER;
        g.fill = GridBagConstraints.HORIZONTAL;

        reviewsClass = new JLabel("Please select the real class for the reviews");
        g.gridx = 0;
        g.gridy = 0;
        g.ipadx = 10;
        g.ipady = 20;
        reviewFrame.add(reviewsClass, g);

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

        reviewsBG.add(rb1);
        reviewsBG.add(rb2);
        reviewsBG.add(rb3);

        g.gridx=1;
        g.gridy=0;
        g.ipady=20;
        reviewFrame.add(reviewsBG, g);

        g.gridx=0;
        g.gridy=1;
        reviewsInfo = new JLabel("Enter reviews path: ");
        reviewFrame.add(reviewsInfo, g);

        g.gridx=1;
        g.gridy=1;
        g.ipadx=50;
        g.ipady=20;
        reviewsPath = new JTextField();
        reviewFrame.add(reviewsPath, g);

        g.anchor = GridBagConstraints.CENTER;
        g.fill = GridBagConstraints.NONE;
        g.insets= new Insets(20,2,2,2);
        g.gridx=0;
        g.gridy=3;
        g.ipadx=30;
        g.ipady=20;
        g.gridwidth = 2;
        reviewFrame.add(okReviews, g);

        reviewFrame.setSize(600,300);
        reviewFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        reviewFrame.setVisible(true);



         //  loadReviews Warning \\
        //***********************\\
        warningFrame = new JFrame("Warning");
        GridBagLayout gridReviews = new GridBagLayout();
        warningFrame.setLayout(gridReviews);

        g.gridx=0;
        g.gridy=0;
        warningReviews = new JLabel("Please select real class and enter a correct destination before continuing");
        warningFrame.add(warningReviews, g);

        g.gridx=0;
        g.gridy=1;
        g.ipady=0;
        g.ipadx=30;
        g.gridwidth = 0;
        okWarnReviews = new JButton("OK");
        okWarnReviews.addActionListener(this);
        warningFrame.add(okWarnReviews, g);


        warningFrame.setVisible(false);
        warningFrame.setSize(550, 200);
        warningFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        warningFrame.setResizable(false);



         //  search review  \\
        //*******************\
        searchIdFrame = new JFrame("Search by ID");
        GridBagLayout gridSearch = new GridBagLayout();
        searchIdFrame.setLayout(gridSearch);
        GridBagConstraints con = new GridBagConstraints();
        //con.fill = GridBagConstraints.HORIZONTAL;
        //g.anchor = GridBagConstraints.CENTER;



        con.gridx=0;
        con.gridy=0;
        searchLabel = new JLabel("Enter ID:");
        searchIdFrame.add(searchLabel, con);

        con.gridx=1;
        con.gridy=0;
        con.ipadx=100;
        searchIdText = new JTextField();
        searchIdFrame.add(searchIdText, con);


        con.gridx=0;
        con.gridy=1;
        con.ipadx=300;
        con.ipady=300;
        con.gridwidth=2;
        searchIdArea = new JTextArea();
        searchIdArea.setEditable(false);
        searchIdFrame.add(searchIdArea, con);

        con.gridx=0;
        con.gridy=2;
        con.ipadx=0;
        con.ipady=0;
        searchId = new JButton("Search");
        searchId.addActionListener(this);
        searchIdFrame.add(searchId, con);

        con.gridx=1;
        con.gridy=2;
        searchClose = new JButton("Close");
        searchClose.addActionListener(this);
        searchIdFrame.add(searchClose, con);


        searchIdFrame.setVisible(true);
        searchIdFrame.setSize(600, 500);
        searchIdFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchIdFrame.setResizable(true);


         //  search review substring  \\
        //*****************************\\
        searchSubFrame = new JFrame("Search by SubString");
        GridLayout gridSub = new GridLayout(3,2);

        searchSub = new JButton("Search");
        searchSub.addActionListener(this);
        searchSubClose = new JButton("Close");
        searchSubClose.addActionListener(this);

        searchSubText = new JTextField();
        searchSubArea = new JTextArea();
        searchSubLabel = new JLabel("enter substring");

        searchSubFrame.add(searchSubLabel);
        searchSubFrame.add(searchSubText);
        searchSubFrame.add(searchSub);
        searchSubFrame.add(searchSubArea);
        searchSubFrame.add(searchSubClose);

        searchSubFrame.setLayout(gridSub);
        searchSubFrame.setVisible(false);
        searchSubFrame.setSize(300, 200);
        searchSubFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchSubFrame.setResizable(true);


         //  delete review  \\
        //*******************\\
        deleteFrame = new JFrame("delete review");
        GridLayout gridDelete = new GridLayout(3,2);

        deleteReviewButton = new JButton("delete review");
        deleteReviewButton.addActionListener(this);
        deleteReviewClose = new JButton("close");
        deleteReviewClose.addActionListener(this);
        deleteText = new JTextField();
        deleteLabel = new JLabel("enter id to be deleted");

        deleteFrame.add(deleteLabel);
        deleteFrame.add(deleteText);
        deleteFrame.add(deleteReviewButton);
        deleteFrame.add(deleteReviewClose);

        deleteFrame.setLayout(gridDelete);
        deleteFrame.setVisible(false);
        deleteFrame.setSize(300, 200);
        deleteFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        deleteFrame.setResizable(true);









    }



    public void actionPerformed (ActionEvent event) {



        if (event.getSource() == loadDB) {
            try {
                rh.loadDB();
                System.out.println("loaded");
            } catch (IOException e) {
                System.out.println("Error Loading DataBase");
                System.out.println("Please try again");
                return;
            }

        }
        if(event.getSource()== saveDB){
            try {
                rh.saveDB();
            }
            catch(IOException e){
                System.out.println("failed to save database");
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



        if(event.getSource() == searchReview){
            searchIdFrame.setVisible(true);
        }
        if(event.getSource() == searchId){
            int temp = Integer.parseInt(searchIdText.getText());
            MovieReview mr = rh.searchById(temp);
            searchIdArea.setText("");
            searchIdArea.append("Review ID: " + mr.getId() + "\n" +
                                "Real Score: " + mr.getRealScore() + "\n" +
                                "Predicted Score: " + mr.getPredictedScore() + "\n" +
                                "Text: " + mr.getText().substring(0,20) + "...");


        }
        if(event.getSource() == searchClose){
            searchIdFrame.dispose();
        }


        if(event.getSource() == searchSubReview){
            searchSubFrame.setVisible(true);
        }
        if(event.getSource() == searchSub){
            String temp = searchSubText.getText();
            List<MovieReview> reviewList = rh.searchBySubstring(temp);
            for( MovieReview mr : reviewList ) {
                searchSubArea.setText("");
                searchSubArea.append("Review ID: " + mr.getId() + "\n" +
                        "Real Score: " + mr.getRealScore() + "\n" +
                        "Predicted Score: " + mr.getPredictedScore() + "\n" +
                        "Text: " + mr.getText().substring(0, 20) + "...");
            }

        }
        if(event.getSource() == searchSubClose){
            searchSubFrame.dispose();
        }


        if(event.getSource() == deleteReview){
            deleteFrame.setVisible(true);
        }
        if(event.getSource() == deleteReviewButton){
            int temp = Integer.parseInt(deleteText.getText());
            rh.deleteReview(temp);
            deleteText.setText("");

        }
        if(event.getSource() == deleteReviewClose){
            deleteFrame.dispose();
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
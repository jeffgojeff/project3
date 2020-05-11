package project3;

import javax.swing.*;
import java.awt.event.*;
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

    public ReviewHandler rh = new ReviewHandler();

    public JFrame mainFrame, reviewFrame, negPos, warningWords, warningFrame,
            searchIdFrame, searchSubFrame, deleteFrame;
    public JButton loadDB, loadReviews, neg, pos, okWords,
            okWarning, okReviews, selectReviews, okWarnReviews,
            searchReview, searchId, searchClose, searchSub, searchSubReview,
            deleteReview, deleteReviewButton, deleteReviewClose, saveDB,
            subClose;
    public JRadioButton rb1, rb2, rb3;
    public JFileChooser fileChooser;
    public JLabel warning, reviewsClass, reviewsInfo, warningReviews, searchLabel, subLabelAgain,
                deleteLabel, mainLabel, accuracy, totalReviews, searchLabelAgain, subLabel, useless;
    public JPanel reviewsBG, subPanel, accPanel, searchPanel, loadPanel, bottomPanel;
    public JTextField reviewsPath, searchSubText, deleteText, searchIdText;
    public JTextArea searchIdArea, searchSubArea, deleteArea;
    public JTable mainTable;
    public JScrollPane mainTablePane, idPane, subPane, deletePane;


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
        negPos.setVisible(true);
        negPos.setSize(500, 200);
        negPos.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        negPos.setResizable(false);

        //   warning   \\
        //**************\\
        warningWords = new JFrame("Warning");
        GridBagLayout grid2 = new GridBagLayout();
        warningWords.setLayout(grid2);
        GridBagConstraints cc = new GridBagConstraints();
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
        mainFrame.setLayout(grid);
        GridBagConstraints c = new GridBagConstraints();

        c.insets = new Insets(10,50,10,10);
        c.gridy = 0;
        mainLabel = new JLabel("Movie Review App");
        mainLabel.setFont(new Font(mainLabel.getFont().getName(), Font.BOLD, 26));
        mainFrame.add(mainLabel, c);

        accPanel = new JPanel();
        accPanel.setLayout(new FlowLayout());

        accuracy = new JLabel("Accuracy: ");
        totalReviews = new JLabel("Database Size: ");

        accPanel.add(accuracy);
        accPanel.add(totalReviews);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,50,10,10);
        mainFrame.add(accPanel,c);

        Object [][]data = new Object[2000][4];
        String[] columnNames = { "ID", "Predicted", "Real Score", "FilePath" };

        mainTable = new JTable(data, columnNames);
        mainTable.setEnabled(true);
        mainTablePane = new JScrollPane(mainTable);

        c = new GridBagConstraints();
        c.insets = new Insets(1,10,10,10);
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 4;
        mainFrame.add(mainTable.getTableHeader(), c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10,10,10,10);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 4;
        mainFrame.add(mainTablePane, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10,50,10,10);

        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        useless = new JLabel("");
        bottomPanel.add(useless);
        mainFrame.add(bottomPanel, c);

        searchPanel = new JPanel();
        searchPanel.setLayout(new GridLayout(1,3));

        searchReview = new JButton("Search ID");
        searchReview.addActionListener(this);
        searchSubReview = new JButton("Search Sub");
        searchSubReview.addActionListener(this);
        deleteReview = new JButton("Delete Review");
        deleteReview.addActionListener(this);

        searchPanel.add(searchReview);
        searchPanel.add(searchSubReview);
        searchPanel.add(deleteReview);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(10,50,10,10);
        mainFrame.add(searchPanel, c);

        loadPanel = new JPanel();
        loadPanel.setLayout(new GridLayout(1,3));

        loadReviews = new JButton("Load Reviews");
        loadReviews.addActionListener(this);
        loadDB = new JButton("Load DataBase");
        loadDB.addActionListener(this);
        saveDB = new JButton("Save DataBase");
        saveDB.addActionListener(this);

        loadPanel.add(loadReviews);
        loadPanel.add(loadDB);
        loadPanel.add(saveDB);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(10,50,10,10);
        mainFrame.add(loadPanel, c);

        mainFrame.setVisible(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(true);
        mainFrame.pack();

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
        reviewFrame.setVisible(false);

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

         // search review Id \\
        //********************\\
        searchIdFrame = new JFrame("Search by ID");
        GridBagLayout gridSearch = new GridBagLayout();
        searchIdFrame.setLayout(gridSearch);

        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(10,10,10,10);
        searchLabel = new JLabel("Enter ID:");
        searchIdFrame.add(searchLabel, c);

        c = new GridBagConstraints();
        c.gridx=1;
        c.gridy=0;
        c.insets = new Insets(10,10,10,10);
        searchIdText = new JTextField(15);
        searchIdFrame.add(searchIdText, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        c.gridwidth = 2;
        c.insets = new Insets(10,10,10,10);
        searchIdArea = new JTextArea(10,15);
        idPane = new JScrollPane(searchIdArea);
        searchIdArea.setEditable(false);
        searchIdFrame.add(idPane, c);

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx=0;
        c.gridy=2;
        c.insets = new Insets(10,10,10,10);
        searchLabelAgain = new JLabel("");

        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=3;
        c.insets = new Insets(10,10,10,10);
        searchId = new JButton("Search");
        searchId.addActionListener(this);
        searchIdFrame.add(searchId, c);

        c = new GridBagConstraints();
        c.gridx=1;
        c.gridy=3;
        c.insets = new Insets(10,10,10,10);
        searchClose = new JButton("Close");
        searchClose.addActionListener(this);
        searchIdFrame.add(searchClose, c);

        searchIdFrame.setVisible(false);
        searchIdFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchIdFrame.setResizable(true);
        searchIdFrame.pack();

         //  search review substring  \\
        //*****************************\\
        searchSubFrame = new JFrame("Search by Substring");
        subPanel = new JPanel();
        GridBagLayout gridSub = new GridBagLayout();
        searchSubFrame.setLayout(gridSub);

        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(10,10,10,10);
        subLabel = new JLabel("Enter string:");
        searchSubFrame.add(subLabel, c);

        c = new GridBagConstraints();
        c.gridx=1;
        c.gridy=0;
        c.gridwidth = 2;
        c.insets = new Insets(10,10,10,10);
        searchSubText = new JTextField(15);
        searchSubFrame.add(searchSubText, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        c.gridwidth = 4;
        c.insets = new Insets(10,10,10,10);
        searchSubArea = new JTextArea(10,15);
        subPane = new JScrollPane(searchSubArea);
        searchSubArea.setEditable(false);
        searchSubFrame.add(subPane, c);

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx=0;
        c.gridy=2;
        subLabelAgain = new JLabel("");
        searchSubFrame.add(subLabelAgain, c);

        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=3;
        c.insets = new Insets(10,10,10,10);
        searchSub = new JButton("Search");
        searchSub.addActionListener(this);
        searchSubFrame.add(searchSub, c);

        c = new GridBagConstraints();
        c.gridx=1;
        c.gridy=3;
        c.insets = new Insets(10,10,10,10);
        subClose = new JButton("Close");
        subClose.addActionListener(this);
        searchSubFrame.add(subClose, c);

        searchSubFrame.setVisible(false);
        searchSubFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        searchSubFrame.setResizable(true);
        searchSubFrame.pack();


         //  delete review  \\
        //*******************\\
        deleteFrame = new JFrame("delete review");
        GridBagLayout gridDelete = new GridBagLayout();
        deleteFrame.setLayout(gridDelete);

        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        c.insets = new Insets(10,10,10,10);
        deleteLabel = new JLabel("Enter Id To Delete: ");
        deleteFrame.add(deleteLabel, c);

        c = new GridBagConstraints();
        c.gridx=1;
        c.gridy=0;
        c.gridwidth = 2;
        c.insets = new Insets(10,10,10,10);
        deleteText = new JTextField(15);
        deleteFrame.add(deleteText, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=1;
        c.gridwidth = 4;
        c.insets = new Insets(10,10,10,10);
        deleteArea = new JTextArea(10,15);
        deletePane = new JScrollPane(deleteArea);
        deleteArea.setEditable(false);
        deleteFrame.add(deletePane, c);

        c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=3;
        c.insets = new Insets(10,10,10,10);
        deleteReviewButton = new JButton("Delete");
        deleteReviewButton.addActionListener(this);
        deleteFrame.add(deleteReviewButton, c);

        c = new GridBagConstraints();
        c.gridx=1;
        c.gridy=3;
        c.insets = new Insets(10,10,10,10);
        deleteReviewClose = new JButton("Close");
        deleteReviewClose.addActionListener(this);
        deleteFrame.add(deleteReviewClose, c);

        deleteFrame.setVisible(false);
        deleteFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        deleteFrame.setResizable(true);
        deleteFrame.pack();

    }


    public void actionPerformed (ActionEvent event) {

        if (event.getSource() == loadDB) {
            try {
                rh.loadDB();
                updateTable();
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
                warningFrame.setVisible(true);
            }
            if(realClass < 3 && realClass > -1 && !reviewsPath.getText().isEmpty()){
                try {
                    rh.loadReviews(reviewsPath.getText(), realClass);
                    updateTable();
                    updateAccuracy();
                    mainFrame.repaint();
                    mainFrame.pack();
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
                    negPos.dispose();
                    mainFrame.setVisible(true);
                } catch (IOException e) {
                    System.out.println("Error Loading Words");
                    System.out.println("Please try again");
                    return;
                }
            }
            else{
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
            searchIdArea.setText("");
            String idStr = searchIdText.getText();
            if (!idStr.matches("-?(0|[1-9]\\d*)")) {
                searchIdArea.setText("Illegal input.");
            }
            else if (!rh.getDatabase().containsKey(Integer.parseInt(idStr))) {
                searchIdArea.setText("Id " + idStr + " not found.");
            }
            else {
                int id = Integer.parseInt(idStr);
                MovieReview mr = rh.searchById(id);
                searchIdArea.setText("");
                searchIdArea.append("Review Id: " + mr.getId() + "\n" +
                        "Real Score: " + mr.getRealScore() + "\n" +
                        "Predicted Score: " + mr.getPredictedScore() + "\n" +
                        "Text: " + mr.getText().substring(0,35) + "...");
            }
        }
        if(event.getSource() == searchClose){
            searchIdFrame.dispose();
        }
        if(event.getSource() == searchSubReview){
            searchSubFrame.setVisible(true);
        }

        if(event.getSource() == searchSub){
            int counter = 0;
            String temp = searchSubText.getText();
            subLabelAgain.setText("");

            List<MovieReview> reviewList = rh.searchBySubstring(temp);
            if(reviewList != null) {
                for (MovieReview mr : reviewList) {
                    //searchSubArea.setText("");
                    searchSubArea.append("Review ID: " + mr.getId() + "\n" +
                            "Real Score: " + mr.getRealScore() + "\n" +
                            "Predicted Score: " + mr.getPredictedScore() + "\n" +
                            "Text: " + mr.getText().substring(0, 30) + "..." + "\n");
                    counter++;
                }
                searchSubArea.append("\n" + "Total reviews found containing \"" + temp + "\": " + counter);
            }
            else{
                searchSubArea.setText("Substring couldn't be found");
            }
        }
        if(event.getSource() == subClose){
            searchSubFrame.dispose();
        }
        if(event.getSource() == deleteReview){
            deleteFrame.setVisible(true);
        }
        if(event.getSource() == deleteReviewButton){

            String idStr = deleteText.getText();

            if (!idStr.matches("-?(0|[1-9]\\d*)")) {
                deleteArea.setText("Invalid Input");
            }
            else if (!rh.getDatabase().containsKey(Integer.parseInt(idStr))) {
                deleteArea.setText("Id " + idStr + " not found.");
            }
            else {
                int id = Integer.parseInt(idStr);
                rh.deleteReview(id);
                updateTable();
                totalReviews.setText("Database Size: " + rh.getDatabase().size());
                deleteArea.setText("Review " + id + " deleted");
            }

        }
        if(event.getSource() == deleteReviewClose){
            deleteFrame.dispose();
        }

    }

public void updateTable(){
        int id = 0;
        int pred = 1;
        int real = 2;
        int path = 3;
        int loop =0;

        for (MovieReview mr : rh.getDatabase().values()){
            mainTable.setValueAt(mr.getId(), loop, id);
            mainTable.setValueAt(mr.getPredictedScore(), loop, pred);
            mainTable.setValueAt(mr.getRealScore(), loop, real);
            mainTable.setValueAt(mr.getFilePath(), loop, path);
            loop++;
        }
}

    public void updateAccuracy() {
        double counter = 0;
        double acc;
        int newAcc;

        for (MovieReview mr : rh.getDatabase().values()) {
            if (mr.getRealScore() == mr.getPredictedScore()) {
                counter++;
            }
        }

        acc = (counter / rh.getDatabase().size() * 100);
        newAcc = (int)acc;
        accuracy.setText("Accuracy: " + newAcc +"%");
        totalReviews.setText("Database Size: " + rh.getDatabase().size());
    }

}
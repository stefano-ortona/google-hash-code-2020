package com.google.fantasticgeneration.hashcode_2020;
import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.ProblemContainer;
import com.google.fantasticgeneration.hashcode_2020.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilsFile {

    /* If file structure is preserved (i.e. first line header, rest of the file data)
        Edit this file as following:
        1. define type of header items and data items
        2. generate setters and getters for header and data
        3. define logic of createHeader() and createData()
     */

    // 1. define type of header items and data items
    private int[] firstRow;
    private int[] secondRow;
    private int bookAmount;
    private int librariesAmount;
    private int availableTime;
    private ProblemContainer problemContainer;
    private List<Book> books;
    private List<Library> libraries;

    // 2. generate setters and getters for header and data

    public int getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) {
        this.bookAmount = bookAmount;
    }

    public int getLibrariesAmount() {
        return librariesAmount;
    }

    public void setLibrariesAmount(int librariesAmount) {
        this.librariesAmount = librariesAmount;
    }

    public int getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(int availableTime) {
        this.availableTime = availableTime;
    }

    public int[] getFirstRow() {
        return firstRow;
    }

    public void setFirstRow(int[] firstRow) {
        this.firstRow = firstRow;
    }

    public int[] getSecondRow() {
        return secondRow;
    }

    public void setSecondRow(int[] secondRow) {
        this.secondRow = secondRow;
    }

    public ProblemContainer getProblemContainer() {
        return problemContainer;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Library> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<Library> libraries) {
        this.libraries = libraries;
    }


    //3. define logic of createHeader() and createData()

    public void createModels() {
        String firstLine = getFirstLineOfFile();
        String[] firstLineSplit = splitString(firstLine, " ");
        int[] firstLineConverted = convertArrayOfStringToArrayOfInt(firstLineSplit);

        // First row
        this.setBookAmount(firstLineConverted[0]);
        this.setLibrariesAmount(firstLineConverted[1]);
        this.setAvailableTime(firstLineConverted[2]);

        // Second row
        String secondLine = this.file[1];
        String[] secondLineSplit = splitString(secondLine, " ");
        int[] secondLineConverted = convertArrayOfStringToArrayOfInt(secondLineSplit);

        books = new ArrayList<>();
        for (int i = 0; i < this.getBookAmount(); i ++){
            Book b = new Book(i, secondLineConverted[i]);
            books.add(b);
        }

        this.setBooks(books);
//
//        this.setTargetCellAmount(secondLineConverted[0]);
//        this.setCoveredRadius(secondLineConverted[1]);
//        this.setAvailableBalloons(secondLineConverted[2]);
//        this.setTurns(secondLineConverted[3]);
//
//        // Third row
//        String thirdRow = this.file[2];
//        String[] thirdRowSplit = splitString(thirdRow, " ");
//        int[] thirdRowConverted = convertArrayOfStringToArrayOfInt(thirdRowSplit);
//
//        this.setInitialCellX(thirdRowConverted[0]);
//        this.setInitialCellY(thirdRowConverted[1]);
//
//        // Init Grid
//        grid = new boolean[this.getRow()][this.getColumns()];
//        for (int r = 0; r < this.getRow(); r ++){
//            for (int c = 0; c < this.getColumns(); c ++){
//                // Init all to false
//                grid[r][c] = false;
//            }
//        }
//        // mark target cells to true
//        for (int i = 0; i<this.getTargetCellAmount(); i++){
//            // 3 fixed row
//            String currentRow = this.file[2 + 1 + i];
//            String[] currentRowSplit = splitString(currentRow, " ");
//            int[] currentRowConverted = convertArrayOfStringToArrayOfInt(currentRowSplit);
//            // Set to true
//            grid[currentRowConverted[0]][currentRowConverted[1]] = true;
//        }
//        this.setGrid(grid);
//
//        // Init winds - List<Pair[][]> winds;
//        int index = 2 + this.getTargetCellAmount();
//        winds = new ArrayList<>();
//
//        for (int j = 0; j< this.getHeights(); j++){
//            Pair[][] matrix = new Pair[this.getColumns()][this.getRow()];
//            for (int k = 0; k < this.getRow(); k++){
//                index++;
//                String currentWind = this.file[index];
//                String[] currentWindSplit = splitString(currentWind, " ");
//                int[] currentWindConverted = convertArrayOfStringToArrayOfInt(currentWindSplit);
//
//                for (int p = 0; p < currentWindConverted.length; p++){
//                    Pair pa = new Pair(currentWindConverted[p], currentWindConverted[p + 1]);
//                    int columnIndex = p / 2;
//                    matrix[columnIndex][k] = pa;
////                    System.out.println(columnIndex + " x");
////                    System.out.println(k + " y");
////                    System.out.println(pa.toString());
////                    System.out.println("-");
//                    p++;
//                }
//            }
//
//            //System.out.println("------- Layer " + j);
//            winds.add(matrix);
//        }
//        this.setWinds(winds);
//
//        //System.out.println(this.getWinds().get());
//
//        // Init balloons
//        baloons = new ArrayList<>();
//        for (int b = 0; b < this.getAvailableBalloons(); b ++){
//            //int id, int row, int column, int height
//            Baloon bal = new Baloon(b, this.getInitialCellX(), this.getInitialCellY(), 0);
//            baloons.add(bal);
//        }
//
//        this.setBaloons(baloons);
//
//    }
//
//    public void setProblemContainer(ProblemContainer problemContainer) {
//        this.problemContainer = problemContainer;
//    }
//
//    public void createProblemContainer() {
//        Status status = new  Status(this.getBaloons(), this.getGrid(), this.getHeights(), this.getTurns(), this.getWinds(), this.getCoveredRadius());
//        problemContainer = new ProblemContainer(status);
//        this.setProblemContainer(problemContainer);
    }


    // ====== Do not change below here

    //private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/google/com/ortona/hashcode/final_2020/";
    private String[] file;

    public void setFile(String[] file) {
        this.file = file;
    }

    public String[] getFile() {
        return file;
    }

    // Constructor
    public UtilsFile(String filepath) {

        try {
            File file = new File(RESOURCE_PATH + filepath);
            String absolutePath = file.getAbsolutePath();

            //LOGGER.info("File absolute path:" + absolutePath);
            readFile(absolutePath);

            createModels();
            //LOGGER.info("Header creation: done");

            //LOGGER.info("Data creation: start");
            //createProblemContainer();
            //LOGGER.info("Data creation: done");
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    // Utils
    public void readFile(String filepath) throws IOException {

        String line;
        List<String> list = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(filepath));

        while ((line = in.readLine()) != null) {
            list.add(line);  //Add line to file
        }

        //Cast List to Array of Array
        String[] stringArr = list.toArray(new String[0]);

        this.setFile(stringArr);

    }

    public String getFirstLineOfFile() {
        return this.file[0];
    }

    public String[] splitString(String string, String separator) {
        return string.split(separator);
    }

    public String[] cloneArrayOfString(String[] source, Integer start, Integer to) {
        return Arrays.copyOfRange(source, start, to);
    }

    public char[] convertStringToArrayOfChar(String string) {
        return string.toCharArray();
    }

    public char[][] convertArrayOfStringToArrayOfCharArrays(String[] dataRaw) {

        char[][] result = new char[dataRaw.length][dataRaw[0].length()];

        for (int i = 0; i < dataRaw.length; i++) {
            result[i] = convertStringToArrayOfChar(dataRaw[i]);
        }
        return result;
    }

    public String convertArrayOfChartToString(char[] a) {
        return new String(a);
    }

    public int[] convertArrayOfStringToArrayOfInt(String[] strings) {
        return Arrays.asList(strings).stream().mapToInt(Integer::parseInt).toArray();

    }

}

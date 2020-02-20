package com.google.fantasticgeneration.hashcode_2020;
import com.google.fantasticgeneration.hashcode_2020.model.Library;
import com.google.fantasticgeneration.hashcode_2020.model.ProblemContainer;
import com.google.fantasticgeneration.hashcode_2020.model.Book;
import com.google.fantasticgeneration.hashcode_2020.model.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
    private Status status;
    private Map<Integer, Book> id2Book;

    // 2. generate setters and getters for header and data

    public void setProblemContainer(ProblemContainer problemContainer) {
        this.problemContainer = problemContainer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

        // First row - Book amount, Libraries amount, Available time"
        this.setBookAmount(firstLineConverted[0]);
        this.setLibrariesAmount(firstLineConverted[1]);
        this.setAvailableTime(firstLineConverted[2]);

        System.out.println("Book amount " + this.getBookAmount());
        System.out.println("Libraries amount " + this.getLibrariesAmount());
        System.out.println("Available time " + this.getAvailableTime());

        System.out.println();

        // Second row - Books definitions
        System.out.println("______________________________________________ Books");
        String secondLine = this.file[1];
        String[] secondLineSplit = splitString(secondLine, " ");
        int[] secondLineConverted = convertArrayOfStringToArrayOfInt(secondLineSplit);

        books = new ArrayList<>();
        id2Book = new HashMap<>();
        for (int i = 0; i < this.getBookAmount(); i ++){
            Book b = new Book(i, secondLineConverted[i]);
            books.add(b);
            id2Book.put(i, b);
            System.out.println("Added book: " + b.toString());

        }
        this.setBooks(books);
        System.out.println();

        // Libraries
        System.out.println("______________________________________________ Libraries");
        libraries = new ArrayList<>();
        int index = 1; // two fixed row
        for (int j = 0; j < this.getLibrariesAmount(); j++){

            // First row: number of books, signupTime, parallelBooks
            index++;
            String firstRow = this.file[index];
            String[] firstRowSplit = splitString(firstRow, " ");
            int[] firstRowConverted = convertArrayOfStringToArrayOfInt(firstRowSplit);
            int bookInLibrary = firstRowConverted[0];
            int librarySignupTime = firstRowConverted[1];
            int libraryParallelBooks = firstRowConverted[2];

            System.out.println("Library id: " + j);
            System.out.println("Books in library: " + bookInLibrary);
            System.out.println("Sign up time: " + librarySignupTime);
            System.out.println("Parallel books: " + libraryParallelBooks);

            // Second row: book in library
            index++;
            String secondRow = this.file[index];
            String[] secondRowSplit = splitString(secondRow, " ");
            int[] secondRowSplitConverted = convertArrayOfStringToArrayOfInt(secondRowSplit);
            List<Book> booksToAdd = new ArrayList<>();

            for (int k = 0; k < bookInLibrary; k ++){
                int id = secondRowSplitConverted[k];
                Book tempBook = id2Book.get(id);
                booksToAdd.add(tempBook);
                System.out.println("...Adding book: " + tempBook.toString());
            }
            // public Library(int id, List<Book> books, int signupTime, int parallelBooks) {
            Library library = new Library(j, booksToAdd, librarySignupTime, libraryParallelBooks);
            libraries.add(library);

            System.out.println("Library created: " + library.toString());
            System.out.println();
        }

        // Status
        System.out.println("______________________________________________ Status");
        // 	public Status(List<Library> libraries, int maxDays) {
        status = new Status(libraries, this.getAvailableTime());
        this.setStatus(status);
        System.out.println(status.toString());

    }


    // ====== Do not change below here

    //private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String RESOURCE_PATH = "src/main/resources/com/google/fantasticgeneration/hashcode_2020/";
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

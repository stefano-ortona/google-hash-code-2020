package com.google.fantasticgeneration.hashcode_2020;
import org.junit.Assert;
import org.junit.Test;

public class UtilsFileTest {

    static private final String filePathExample = "a_example.txt";
//    static private final String filePathExample = "b_read_on.txt";
//    static private final String filePathExample = "c_incunabula.txt";
//    static private final String filePathExample = "d_tough_choices.txt";
//    static private final String filePathExample = "e_so_many_books.txt";
    //    static private final String filePathExample = "f_libraries_of_the_world.txt";
    static private UtilsFile fr = new UtilsFile(filePathExample);


    @Test
    public void testGoal() {
        Assert.assertEquals(true, true);
    }
}




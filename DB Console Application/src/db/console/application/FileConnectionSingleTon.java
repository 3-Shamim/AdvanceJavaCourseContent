package db.console.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shamim
 */
public class FileConnectionSingleTon {

    private static RandomAccessFile ACCESS_FILE = null;
    private static final FileConnectionSingleTon INSTANCE = new FileConnectionSingleTon();
    
    private FileConnectionSingleTon() {

        try {
            ACCESS_FILE = new RandomAccessFile("products.txt", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileConnectionSingleTon.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static RandomAccessFile getAccessFile() {
        
        return ACCESS_FILE;
    }

}

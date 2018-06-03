package db.console.application;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shamim
 */
public class FileConnectionSingleTon {

    private static final FileConnectionSingleTon INSTANCE = new FileConnectionSingleTon();
    
    private static RandomAccessFile ACCESS_FILE = null;
    
    private FileConnectionSingleTon() {
        
        try {
            ACCESS_FILE = new RandomAccessFile("products.txt", "rw");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileConnectionSingleTon.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static RandomAccessFile getAccessFile()
    {
        return ACCESS_FILE;
    }
    
}

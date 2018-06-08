/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.console.application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author monirulhasan
 */
public class DBConsoleApplication {

    public DBConsoleApplication() {
        Product product = new Product(49, "OnePlus 5T", 55000);
        Product product1 = new Product(40, "Apple", 150);
        Product product2 = new Product(35, "Monitor", 12000);
        System.out.println(product2);

        ProductDAO productDao = new ProductDAOMySQLImplementation();
//        ProductDAO productDao = new ProductDAOFileImplementation();
        productDao.insertProduct(product2);
//        List<Product> productList = productDao.getAllProducts();
        
//        for (Product product : productList)
//            System.out.println(product);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new DBConsoleApplication();
    }

}

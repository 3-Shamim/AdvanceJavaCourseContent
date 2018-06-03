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
        Product product = new Product(45, "Apple", 100);
        Product product1 = new Product(46, "Orange", 900);
        Product product2 = new Product(47, "Laptop", 15000);
//        System.out.println(product);

//        ProductDAO productDao = new ProductDAOMySQLImplementation();
        ProductDAO productDao = new ProductDAOFileImplementation();
//        productDao.insertProduct(product);
//        productDao.insertProduct(product1);
        productDao.insertProduct(product2);

//        productDao.deleteProduct(product1);

        List<Product> products = productDao.getAllProducts();
        products.forEach(System.out::println);
        
//        productDao.updateProduct(product1);
        
//        productDao.getAllProducts().forEach(System.out::println);

        System.out.println("Single Product = " + productDao.getProduct(47));
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

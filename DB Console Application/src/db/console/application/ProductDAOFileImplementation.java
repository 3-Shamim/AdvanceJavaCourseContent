/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.console.application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author monirulhasan
 */
public class ProductDAOFileImplementation implements ProductDAO {

    private List<Product> products;

    @Override
    public void insertProduct(Product product) {
        try {
            // Task #2: use a singleton that gives you a file handler
            // WIth SingleTon
            RandomAccessFile accessFile = FileConnectionSingleTon.getAccessFile();
            accessFile.seek(accessFile.length());
            accessFile.writeBytes("productId=" + product.getProductId() + "\r\n");
            accessFile.writeBytes("productName=" + product.getProductName() + "\r\n");
            accessFile.writeBytes("unitPrice=" + product.getUnitPrice() + "\r\n");
            accessFile.close();
        } catch (IOException ex) {
            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Normal Way
//        try (RandomAccessFile productFile = new RandomAccessFile("products.txt", "rw")) {
//            productFile.seek(productFile.length());
//            productFile.writeBytes("productId=" + product.getProductId() + "\r\n");
//            productFile.writeBytes("productName=" + product.getProductName() + "\r\n");
//            productFile.writeBytes("unitPrice=" + product.getUnitPrice() + "\r\n");
//            productFile.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    // Task #1: implement the remaining methods
    @Override
    public List<Product> getAllProducts() {
        products = new ArrayList<>();

        int count = 0;
        int id = 0;
        String name = "";
        double price;

        try (RandomAccessFile file = new RandomAccessFile("products.txt", "r")) {
            String line;
            while ((line = file.readLine()) != null) {
                count++;
                switch (count) {
                    case 1:
//                        String arrays[] = line.split("=");
//                        int id = Integer.parseInt(arrays[1]);
                        int index = line.indexOf("=");
                        id = Integer.parseInt(line.substring(index + 1));
                        break;
                    case 2:
                        int index1 = line.indexOf("=");
                        name = line.substring(index1 + 1);
                        break;
                    case 3:
                        int index3 = line.indexOf("=");
                        price = Double.parseDouble(line.substring(index3 + 1));
                        products.add(new Product(id, name, price));
                        count = 0;
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return products;
    }

    @Override
    public Product getProduct(int productId) {

        Product p = new Product();

        for (Product product : getAllProducts()) {
            if (product.getProductId() == productId) {
                p = product;
            }
        }
        return p;
    }

    @Override
    public void deleteProduct(Product product) {
        List<Product> allProducts = getAllProducts();
        try (RandomAccessFile productFile = new RandomAccessFile("products.txt", "rw")) {

            productFile.setLength(0);

            for (Product allProduct : allProducts) {
                if (allProduct.getProductId() != product.getProductId()) {
                    productFile.writeBytes("productId=" + allProduct.getProductId() + "\r\n");
                    productFile.writeBytes("productName=" + allProduct.getProductName() + "\r\n");
                    productFile.writeBytes("unitPrice=" + allProduct.getUnitPrice() + "\r\n");
                }
            }
            productFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void updateProduct(Product product) {
        List<Product> allProducts = getAllProducts();
        try (RandomAccessFile productFile = new RandomAccessFile("products.txt", "rw")) {

            productFile.setLength(0);

            for (Product allProduct : allProducts) {
                if (allProduct.getProductId() == product.getProductId()) {
                    allProduct = product;
                }
                productFile.writeBytes("productId=" + allProduct.getProductId() + "\r\n");
                productFile.writeBytes("productName=" + allProduct.getProductName() + "\r\n");
                productFile.writeBytes("unitPrice=" + allProduct.getUnitPrice() + "\r\n");
            }
            productFile.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProductDAOFileImplementation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

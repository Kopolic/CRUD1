package crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length==0){
            return;
        }
        String fileName = "product.txt";
        ArrayList<Product> products = new ArrayList<>();
        int idList = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            while (bufferedReader.ready()){
                Product product = prodCreate(bufferedReader.readLine());
                products.add(product);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        if ("-c".equals(args[0])){
            for (Product product: products) {
                if (product.getId() > idList){
                    idList = product.getId();
                }
            }
            String name = args[args.length - 3];
            if (name.length() > 30){
                name = name.substring(0,30);
            }
            String price = args[args.length - 2];
            if (price.length() > 8){
                price = price.substring(0,8);
            }
            String quantity = args[args.length - 1];
            if (quantity.length() > 4){
                quantity = quantity.substring(0,4);
            }
            Product product1 = new Product(++idList,name,price,quantity);
            products.add(product1);
        }
        try(FileWriter fw=new FileWriter(fileName)) {
            for (Product product:products) {
                fw.write(product.toString());

            }

        }
        
    }
    public static Product prodCreate(String readLine){
        int id;
        String name;
        String price;
        String quantity;
        id = Integer.parseInt(readLine.substring(0,8).trim());
        name = readLine.substring(8,30).trim();
        price = readLine.substring(38,46).trim();
        quantity = readLine.substring(46,58).trim();
        return new Product(id,name,price,quantity);
    }
}

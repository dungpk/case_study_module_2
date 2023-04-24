package manager;
import base.IProduct;
import base.ProductAbstract;
import fileIO.ReadToFile;
import fileIO.WriteToFile;
import model.platform.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import model.platform.TypeProduct;


public class ProductManager extends ProductAbstract implements IProduct, Serializable {

    private final String PRODUCT_LIST = "product.txt";
    public List<Product> productList = new ArrayList<>();

    static ReadToFile<Product> readDataToFile = new ReadToFile<>();
    static WriteToFile<Product> writeDataToFile = new WriteToFile<>();
    public ProductManager() {
        readProductList();
    }

    @Override
    public void searchProduct(String name) {
        //TODO
    }

    @Override
    public void sortByPrice() {
        productList = readDataToFile.readToFile(PRODUCT_LIST);
        List<Product> sortedList =  productList.stream()
                .sorted(Comparator.comparing(Product::getPrice))
                .collect(Collectors.toList());
        for (Product pr : sortedList) {
            System.out.println(pr);
        }
    }

    @Override
    public void display(TypeProduct type) {
        readProductList();
        if (Objects.isNull(type)) {
            for (Product product : productList) {
                System.out.println(product);
            }
        } else {
            for (Product product : productList) {
                if (type.getNameType().equals(product.getTypeProduct()
                        .getNameType()) &&
                        type.getSexType().equals(product.getTypeProduct()
                                .getSexType())) {
                    System.out.println(product);
                }
            }
        }
    }

    public void displayByTypeSex(TypeProduct type) {
        readProductList();
        if (Objects.isNull(type)) {
            for (Product product : productList) {
                System.out.println(product);
            }
        } else {
            for (Product product : productList) {
                if (type.getNameType().equals(product.getTypeProduct()
                        .getNameType())) {
                    System.out.println(product);
                }
            }
        }
    }

    public void displayByTypeName(TypeProduct type) {
        readProductList();
        if (Objects.isNull(type)) {
            for (Product product : productList) {
                System.out.println(product);
            }
        } else {
            for (Product product : productList) {
                if (type.getNameType().equals(product.getTypeProduct()
                        .getNameType())) {
                    System.out.println(product);
                }
            }
        }
    }


    @Override
    public void insertProduct(Product product) {
        int index = getIndexByName(product.getName());
        if (index >= 0) {
            productList.set(index, product);
        } else {
            productList.add(product);
        }
        writeProductList();
    }

    @Override
    public void removeProduct(String name) {
        int index = getIndexByName(name);
        if (index >= 0) {
            productList.remove(index);
        } else {
            System.out.println("product not found!");
        }
        writeProductList();
    }

    @Override
    public void updatePriceProduct(String name, int price) {
        readProductList();
        int index = getIndexByName(name);
        if (index < 0) {
            System.out.println("Product not found!");
        } else {
            for (Product product : productList) {
                if (product.getName().equals(name)) {
                    product.setPrice(price);
                    break;
                }
            }
        }
        writeProductList();
    }

    @Override
    public void updateQuantityProduct(String name, int quantity) {
        int index = getIndexByName(name);
        if (index < 0) {
            System.out.println("Product not found!");
        } else {
            for (Product product : productList) {
                if (product.getName().equals(name)) {
                    product.setQuantity(quantity);
                    break;
                }
            }
        }
        writeProductList();
    }

    public int getIndexByName(String name) {
        int index = -1;
        readProductList();
            for (Product product : productList ) {
                if (product.getName().equals(name)) {
                    return productList.indexOf(product);
                }
            }
        return index;
    }

    private void readProductList() {
        productList = readDataToFile.readToFile(PRODUCT_LIST);
    }
    private void writeProductList( ){
        writeDataToFile.writeToFile(PRODUCT_LIST,productList);
    }
}

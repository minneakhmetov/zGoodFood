package services;

import models.Product;
import dto.ProductCart;
import dto.TwoProductCart;
import repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<List<Product>> getAllProducts(){
        List<List<Product>> result = new ArrayList<List<Product>>();
        List<Product> products = productRepository.readAll();
        ArrayList<Product> productsFirst = new ArrayList<Product>();
        ArrayList<Product> productsSecond = new ArrayList<Product>();
        ArrayList<Product> productsThird = new ArrayList<Product>();
        int a = 0;
        for (int i = 0; i < products.size(); i++){
            if(a == 0){
                productsFirst.add(products.get(i));
                a++;
                continue;
            }
            if(a == 1){
                productsSecond.add(products.get(i));
                a++;
                continue;
            }
            if(a == 2){
                productsThird.add(products.get(i));
                a = 0;
                continue;
            }
        }
        result.add(productsFirst);
        result.add(productsSecond);
        result.add(productsThird);
        return result;
    }


    public Product getProduct(Long id){
        return productRepository.read(id);
    }

    public void addToCart(Long userId, Long productId){
        productRepository.addToCart(userId, productId);
    }

    public List<ProductCart> getCartByUser(Long id){
        List<ProductCart> productCarts = productRepository.readCart(id);
//        List<TwoProductCart> twoProductCarts = new ArrayList<TwoProductCart>();
////        for(int i = 0; i< productCarts.size(); i++){
////            TwoProductCart twoProductCart = TwoProductCart.builder()
////                    .firstId(productCarts.get(i).getId())
////                    .firstItemId(productCarts.get(i).getItemId())
////                    .firstDescription(productCarts.get(i).getDescription())
////                    .firstName(productCarts.get(i).getName())
////                    .firstPrice(productCarts.get(i).getPrice())
////                    .firstPhotoUrl(productCarts.get(i).getPhotoUrl())
////                    .secondDescription(productCarts.get(++i).getDescription())
////                    .secondId(productCarts.get(i).getId())
////                    .secondPhotoUrl(productCarts.get(i).getPhotoUrl())
////                    .secondName(productCarts.get(i).getName())
////                    .secondItemId(productCarts.get(i).getItemId())
////                    .secondPrice(productCarts.get(i).getPrice())
////                    .build();
////            twoProductCarts.add(twoProductCart);
////        }
////        return twoProductCarts;
        return productCarts;
    }
    public void deleteFromCart(Long itemId){
        productRepository.deleteFromCart(itemId);
    }
}
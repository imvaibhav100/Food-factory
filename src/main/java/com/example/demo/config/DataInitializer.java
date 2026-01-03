package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if products already exist
        if (productRepository.count() > 0) {
            System.out.println("Products already exist in database. Skipping initialization.");
            return;
        }

        System.out.println("Initializing products in database...");

        // Biryani Items (3 items - only veg)
        createProduct("Veg Biryani", 180.0, "Delicious vegetable biryani with mixed vegetables", 
            "/Images/biryani/veg-biryani.jpg");
        createProduct("Hyderabadi Dum Biryani", 280.0, "Authentic Hyderabadi style slow-cooked biryani", 
            "/Images/biryani/veg-dum-biryani.jpeg");
        createProduct("Veg Pulao", 160.0, "Mildly spiced rice with mixed vegetables", 
            "/Images/biryani/Lucknowi-biryani.jpg");

        // Snacks (1 item)
        createProduct("Spring Roll", 79.0, "Veg stuffed crispy spring rolls", 
            "/Images/chinese/momo.webp");
            
        // Chinese Items (7 items - only veg)
        createProduct("Hakka Noodles", 160.0, "Stir-fried noodles with vegetables and sauces", 
            "/Images/chinese/chowmein.jpg");
        createProduct("Veg Manchurian", 180.0, "Crispy vegetable balls in spicy gravy", 
            "/Images/chinese/Fried-rice-manchurian.jpg");
        createProduct("Veg Fried Rice", 150.0, "Flavorful fried rice with vegetables", 
            "/Images/biryani/veg-biryani.jpg");
        createProduct("Veg Chowmein", 149.0, "Stir-fried noodles with mixed vegetables", 
            "/Images/chinese/chowmein.jpg");
        createProduct("Schezwan Noodles", 119.0, "Spicy noodles with veggies and Schezwan sauce", 
            "/Images/chinese/chowmein.jpg");
        createProduct("Paneer Chilli", 210.0, "Cottage cheese in spicy Chinese-style gravy", 
            "/Images/chinese/chilli-Paneer.jfif");
        createProduct("Gobi Manchurian", 170.0, "Crispy cauliflower in Manchurian gravy", 
            "/Images/chinese/Fried-rice-manchurian.jpg");

        // North Indian Food (10 items)
        createProduct("Dal Makhani", 160.0, "Creamy black lentils with butter and cream", 
            "/Images/vegetable/veg.jpg");
        createProduct("Chole Bhature", 139.0, "Spicy chickpea curry with fried bread", 
            "/Images/north-india-food/chola-bhatura.jpg");
        createProduct("Rajma Chawal", 130.0, "Red kidney beans curry with steamed rice", 
            "/Images/vegetable/veg.jpg");
        createProduct("Chilli Potato", 99.0, "Crispy potato tossed in spicy sauce", 
            "/Images/north-india-food/Honey-Chilli-Potato.jpg");
        createProduct("Gulab Jamun", 50.0, "Sweet milk-based dessert balls", 
            "/Images/north-india-food/Gulab Jamun.jpg");
        createProduct("Laccha Paratha", 30.0, "Layered flatbread with butter", 
            "/Images/north-india-food/Laccha-Paratha.jpg");
        createProduct("Samosa", 30.0, "Crispy pastry filled with spiced potatoes", 
            "/Images/north-india-food/chola-bhatura.jpg");
        createProduct("Cold Coffee", 69.0, "Chilled coffee with ice cream", 
            "/Images/TEA.jpg");
        createProduct("Chocolate Shake", 89.0, "Rich chocolate milkshake with cream", 
            "/Images/TEA2.jpg");
        createProduct("Masala Dosa", 110.0, "Crispy rice crepe with spiced potato filling", 
            "/Images/north-india-food/chola-bhatura.jpg");

        // Paneer Items (6 items)
        createProduct("Paneer Tikka", 210.0, "Grilled cottage cheese marinated in spices", 
            "/Images/paneer/Kadai-Paneer.jpg");
        createProduct("Kadai Paneer", 170.0, "Cottage cheese with bell peppers in spicy gravy", 
            "/Images/paneer/Kadai-Paneer.jpg");
        createProduct("Shahi Paneer", 220.0, "Royal paneer curry in cashew-based gravy", 
            "/Images/paneer/paneer-butter-masala.jpg");
        createProduct("Palak Paneer", 180.0, "Cottage cheese in creamy spinach gravy", 
            "/Images/vegetable/veg.jpg");
        createProduct("Paneer Butter Masala", 290.0, "Paneer cubes in rich tomato-butter gravy", 
            "/Images/paneer/paneer-butter-masala.jpg");
        createProduct("Paneer Do Pyaza", 270.0, "Paneer with double the amount of onions", 
            "/Images/paneer/paneer-do-pyaza.jpg");

        // Vegetable Items (6 items)
        createProduct("Mix Veg Curry", 150.0, "Assorted vegetables in flavorful curry", 
            "/Images/vegetable/veg.jpg");
        createProduct("Aloo Gobi", 140.0, "Potato and cauliflower with Indian spices", 
            "/Images/vegetable/veg.jpg");
        createProduct("Malai Kofta", 250.0, "Vegetable dumplings in creamy gravy", 
            "/Images/vegetable/veg.jpg");
        createProduct("Veg Korma", 170.0, "Mixed vegetables in mild cashew gravy", 
            "/Images/vegetable/veg.jpg");
        createProduct("Baingan Bharta", 160.0, "Roasted eggplant mash with spices", 
            "/Images/vegetable/veg.jpg");
        createProduct("Mushroom Masala", 180.0, "Button mushrooms in spicy tomato gravy", 
            "/Images/vegetable/veg.jpg");

        // Fast Food & Snacks (10 items)
        createProduct("Veg Burger", 59.0, "Crispy veg patty, lettuce, mayo, soft bun", 
            "/Images/north-india-food/chola-bhatura.jpg");
        createProduct("Cheese Pizza", 149.0, "Cheesy pizza with tomato sauce and veggies", 
            "/Images/north-india-food/Laccha-Paratha.jpg");
        createProduct("French Fries", 49.0, "Golden fried potato fries, salted", 
            "/Images/north-india-food/Honey-Chilli-Potato.jpg");
        createProduct("Paneer Roll", 89.0, "Paneer tikka, salad, wrapped in soft roti", 
            "/Images/north-india-food/Soya-Chaap.jpg");
        createProduct("Veg Sandwich", 60.0, "Grilled sandwich with vegetables and cheese", 
            "/Images/north-india-food/chola-bhatura.jpg");
        createProduct("Masala Papad", 40.0, "Crispy papad topped with onion-tomato mix", 
            "/Images/north-india-food/Laccha-Paratha.jpg");
        createProduct("Pani Puri", 50.0, "Crispy shells filled with spicy tangy water", 
            "/Images/north-india-food/chola-bhatura.jpg");
        createProduct("Pakora", 70.0, "Deep-fried fritters with vegetables", 
            "/Images/north-india-food/chola-bhatura.jpg");
        createProduct("Garlic Bread", 90.0, "Toasted bread with garlic butter", 
            "/Images/north-india-food/Laccha-Paratha.jpg");
        createProduct("Pasta Alfredo", 160.0, "Creamy white sauce pasta", 
            "/Images/chinese/chowmein.jpg");

        System.out.println("Successfully initialized " + productRepository.count() + " products!");
    }

    private void createProduct(String name, double price, String description, String imageUrl) {
        Product product = new Product();
        product.setPname(name);
        product.setPprice(price);
        product.setPdescription(description);
        product.setImageUrl(imageUrl);
        productRepository.save(product);
        System.out.println("Created product: " + name);
    }
}


package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.ProductCategory;
import backend_group_5.we_lead_bootcamp.model.enums.Flavours;
import backend_group_5.we_lead_bootcamp.model.enums.Sauces;
import backend_group_5.we_lead_bootcamp.model.enums.Sizes;
import backend_group_5.we_lead_bootcamp.model.enums.Toppings;
import backend_group_5.we_lead_bootcamp.service.ProductCategoryService;
import backend_group_5.we_lead_bootcamp.service.ProductService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@Component
@Profile("generate-catalog-products")
@RequiredArgsConstructor
public class ProductCatalogSampleContentCreator extends BaseComponent implements CommandLineRunner{
    private final ProductService productService;
    private final ProductCategoryService productCategoryService;

    private final StoreService storeService;
    @Override
    public void run(String... args) throws Exception{
        //create productCategory
        ProductCategory newCategory = productCategoryService.create(ProductCategory.builder()
                .description("Rich Aromatic Coffee Blends")
                .name("Cafe").build());
        logger.info("Created {}.", newCategory);

        //CreateAll productCategories
        List<ProductCategory> productCategories= List.of(
                ProductCategory.builder().description("Italian Pizza").name("Pizza").build(),
                ProductCategory.builder().description("Fresh Artisanal Bread Varieties").name("Bakery").build(),
                ProductCategory.builder().description("Delicious Morning Menu Options").name("Breakfast").build(),
                ProductCategory.builder().description("Sizzling Grilled Flavor Creations").name("Grill").build(),
                ProductCategory.builder().description("Delectable Sweet Treats Galore").name("Pastry").build(),
                ProductCategory.builder().description("Juicy Gourmet Burger Creations").name("Burger").build());

        var productCategoriesCreated = productCategoryService.createAll(productCategories);
        logger.info("Created {} product Categories.", productCategoriesCreated.size());
        productCategoriesCreated.stream()
                .sorted(Comparator.comparing(ProductCategory::getId))
                .forEach(p -> logger.debug("{}. {}", p.getId(), p));

        //Create Product
        Product newProduct=productService.create(Product.builder()
                .serial("SN1000-0001")
                .name("Fredo Espresso")
                .price(BigDecimal.valueOf(4.30))
                .description("Hot espresso shaken with ice cubes")
                .productCategory(productCategoryService.getById(1L))
                .store(storeService.getStoreByName("Store4"))
                .sizes(Sizes.MEDIUM)
                .flavours(Flavours.NONE)
                .sauces(Sauces.NONE)
                .toppings(Toppings.NONE)
                .build());
        logger.info("Created {}!",newProduct);

        //CreateAll Products
        List<Product> products = List.of(
                Product.builder().serial("SN1000-0002").name("HOT CHOCOLATE")
                        .price(BigDecimal.valueOf(3.00)).productCategory(productCategoryService.getById(1L))
                        .description("Classic Chocolate").store(storeService.getStoreByName("Store4"))
                        .sizes(Sizes.MEDIUM)
                        .flavours(Flavours.STRAWBERRY)
                        .toppings(Toppings.WHIPPED_CREAM)
                        .sauces(Sauces.NONE)
                        .build(),
                Product.builder().serial("SN1100-0001").name("WAFFLE")
                        .price(BigDecimal.valueOf(6.50)).productCategory(productCategoryService.getById(4L))
                        .description("Waffle with Nutella").store(storeService.getStoreByName("Store9"))
                        .sizes(Sizes.NONE)
                        .flavours(Flavours.NONE)
                        .sauces(Sauces.NONE)
                        .toppings(Toppings.BISCUIT)
                        .toppings(Toppings.WHIPPED_CREAM).build(),
                Product.builder().serial("SN1100-0002").name("GREEK GIROS")
                        .price(BigDecimal.valueOf(5.40)).productCategory(productCategoryService.getById(5L))
                        .description("Greek Giros with fries").store(storeService.getStoreByName("Store3"))
                        .sizes(Sizes.LARGE)
                        .flavours(Flavours.NONE)
                        .sauces(Sauces.TZATZIKI)
                        .sauces(Sauces.KETCHUP)
                        .sauces(Sauces.MUSTARD)
                        .toppings(Toppings.NONE).build(),
                Product.builder().serial("SN1100-0003").name("Burger")
                        .price(BigDecimal.valueOf(8.00))
                        .productCategory(productCategoryService.getById(7L))
                        .store(storeService.getStoreByName("Store5"))
                        .description("Best Burger in town!")
                        .sizes(Sizes.LARGE)
                        .flavours(Flavours.NONE)
                        .sauces(Sauces.NONE)
                        .toppings(Toppings.BACON)
                        .build());

        //ADD MORE PRODUCTS AS NEEDED

        var productsCreated = productService.createAll(products);
        logger.info("Created {} products.", productsCreated.size());
        productsCreated.stream()
                .sorted(Comparator.comparing(Product::getId))
                .forEach(p -> logger.debug("{}. {}", p.getId(), p));
    }
}

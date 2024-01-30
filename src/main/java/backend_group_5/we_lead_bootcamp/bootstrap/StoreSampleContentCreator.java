package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.Review;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.model.enums.StoreCategoryVariation;
import backend_group_5.we_lead_bootcamp.service.StoreCategoryService;
import backend_group_5.we_lead_bootcamp.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@Profile("generate-stores")
@RequiredArgsConstructor
public class StoreSampleContentCreator extends BaseComponent implements CommandLineRunner {
    private final StoreService storeService;
    private final StoreCategoryService storeCategoryService;

    // New line: Track whether data has been created
    private boolean hasCreatedData = false;
    @Override
    public void run(String... args) throws Exception {
        // New lines: Check if data has already been created
        if (!hasCreatedData) {
            // Create store categories
            StoreCategory category1 = storeCategoryService.createCategory(
                    new StoreCategory("Category1", StoreCategoryVariation.SUSHI));
            StoreCategory category2 = storeCategoryService.createCategory(
                    new StoreCategory("Category2", StoreCategoryVariation.VEGAN));

            logger.info("Created store categories: {}, {}", category1, category2);

            // Create stores
            Store store1 = storeService.createStore(Store.builder()
                    .name("SUSHI THERMAIKOS")
                    .address("Ignatiou 7")
                    .phone("1112233258")
                    .vatNumber("VAT359")
                    .minOrderAmount(BigDecimal.valueOf(17.50))
                    .category(StoreCategoryVariation.SUSHI)
                    .deliveryTime(60)
                    .build());

            Store store2 = storeService.createStore(Store.builder()
                    .name("FALAFFEL TOWER")
                    .address("Ethnikis Amynis 2")
                    .phone("9980986790")
                    .vatNumber("VAT845")
                    .minOrderAmount(BigDecimal.valueOf(7))
                    .category(StoreCategoryVariation.VEGAN)
                    .deliveryTime(30)
                    .build());

            logger.info("Created stores: {}, {}", store1, store2);


//            Create reviews for the stores
            Review review5 = new Review(2.0, "Food came late", store1);
            Review review6 = new Review(3.0, "Food was average", store1);

            Review review7 = new Review(5.0, "Nice falaffel", store2);
            Review review8 = new Review(5.0, "Best in town", store2);

            storeService.addReviewToStore(store1.getId(), review5);
            storeService.addReviewToStore(store1.getId(), review6);

            storeService.addReviewToStore(store2.getId(), review7);
            storeService.addReviewToStore(store2.getId(), review8);

            logger.info("Created reviews for the stores");

            // Make requests for every service
            logger.info("Find store by ID: {}", storeService.getById(store1.getId()));
            logger.info("Find store by name: {}", storeService.getStoreByName("Store1"));
            logger.info("Find all stores: {}", storeService.findAll());
            logger.info("Find all stores by category BAKERY: {}", storeService.findAllStoresByCategory(StoreCategoryVariation.BAKERY));
            logger.info("Find top-rated stores for GRILL category: {}", storeService.findTopRatedStores(5, StoreCategoryVariation.GRILL));
            logger.info("Find stores with min order amount: {}", storeService.findStoresWithMinOrderAmount(BigDecimal.valueOf(5.0)));
            logger.info("Calculate average rating for Store2: {}", storeService.calculateAverageRating(store2.getId()));
            logger.info("Get delivery time for Store1: {}", storeService.getDeliveryTime(store1.getId()));
            logger.info("Find reviews by Store1: {}", storeService.findReviewsByStore(store1));

//            Store newstore = storeService.createStore(Store.builder()
//                .name("Store1")
//                .address("Address 1")
//                .phone("231512345")
//                .vatNumber("VAT000")
//                .minOrderAmount(BigDecimal.valueOf(5))
//                .category(StoreCategoryVariation.BAKERY)
//                .deliveryTime(30)
//                .build());
//        logger.info("Created {}.", newstore);

        List<Store> stores = List.of(
                Store.builder().name("MAMA BAKERY").address("Karolou 1").phone("698765432")
                        .vatNumber("VAT456").minOrderAmount(BigDecimal.valueOf(5.0)).category(StoreCategoryVariation.ASIAN_FOOD).deliveryTime(60).build(),
                Store.builder().name("ASIAN HOUSE").address("Evzonon 32").phone("987654321")
                        .vatNumber("VAT789").minOrderAmount(BigDecimal.valueOf(5.5)).category(StoreCategoryVariation.GRILL).deliveryTime(30).build(),
                Store.builder().name("SOUVLAKI BAR").address("Nikis 103").phone("698761223")
                        .vatNumber("VAT001").minOrderAmount(BigDecimal.valueOf(7.5)).category(StoreCategoryVariation.CAFE).deliveryTime(25).build(),
                Store.builder().name("COFFEE HUGS").address("Athinon 53").phone("698761333")
                        .vatNumber("VAT002").minOrderAmount(BigDecimal.valueOf(15.0)).category(StoreCategoryVariation.BURGER).deliveryTime(35).build(),
                Store.builder().name("BURGER TEMPLE").address("Gounari 5").phone("698877665")
                        .vatNumber("VAT003").minOrderAmount(BigDecimal.valueOf(2.5)).category(StoreCategoryVariation.DELI).deliveryTime(50).build(),
                Store.builder().name("DERLICATESSEN").address("Olympou 35").phone("231098887")
                        .vatNumber("VAT004").minOrderAmount(BigDecimal.valueOf(5.0)).category(StoreCategoryVariation.GRILL).deliveryTime(35).build(),
                Store.builder().name("KOUTOUKI").address("Filippou 2").phone("231023345")
                        .vatNumber("VAT005").minOrderAmount(BigDecimal.valueOf(7.5)).category(StoreCategoryVariation.CREPERIE).deliveryTime(20).build(),
                Store.builder().name("CREPOMANIAC").address("Eirinis 17").phone("231012234")
                        .vatNumber("VAT006").minOrderAmount(BigDecimal.valueOf(3.5)).category(StoreCategoryVariation.BREAKFAST).deliveryTime(30).build(),
                Store.builder().name("AT TIFANNY'S").address("Egnatias 15").phone("698776655")
                        .vatNumber("VAT007").minOrderAmount(BigDecimal.valueOf(3.5)).category(StoreCategoryVariation.BRUNCH).deliveryTime(45).build()
                // Add more stores as needed
        );

        List<Store> createdStores = storeService.createAllStores(stores);
        logger.info("Created {} stores.", createdStores.size());
        createdStores.forEach(store -> logger.debug("{}. {}", store.getId(), store));

            hasCreatedData = true;
        } else {
            // New line: Log that sample data already created
            logger.info("Sample data already created. Skipping data creation.");
        }
        //The application will check if the hasCreatedData flag is false.
        //If it's false, it will create the sample data and set hasCreatedData to true.
        //If it's already true, it will log a message indicating that the sample data has already been created and will skip the data creation.



    }

    //examples for normal cases, edge cases, data validation cases, and negative testing cases.


}


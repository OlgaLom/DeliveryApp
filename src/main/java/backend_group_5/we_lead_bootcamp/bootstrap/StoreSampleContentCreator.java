package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategoryVariation;
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

    // New line: Track whether data has been created
    private boolean hasCreatedData = false;
    @Override
    public void run(String... args) throws Exception {
        // New lines: Check if data has already been created
        if (!hasCreatedData) {
        Store newstore = storeService.createStore(Store.builder()
                .name("Store1")
                .address("Address 1")
                .phone("231512345")
                .vatNumber("VAT000")
                .minOrderAmount(BigDecimal.valueOf(5))
                .category(StoreCategoryVariation.BAKERY)
                .deliveryTime(30)
                .build());
        logger.info("Created {}.", newstore);

        List<Store> stores = List.of(
                Store.builder().name("Store2").address("Address 2").phone("698765432")
                        .vatNumber("VAT456").minOrderAmount(BigDecimal.valueOf(5.0)).category(StoreCategoryVariation.ASIAN_FOOD).deliveryTime(60).build(),
                Store.builder().name("Store3").address("Address 3").phone("987654321")
                        .vatNumber("VAT789").minOrderAmount(BigDecimal.valueOf(5.5)).category(StoreCategoryVariation.GRILL).deliveryTime(30).build(),
                Store.builder().name("Store4").address("Address 4").phone("698761223")
                        .vatNumber("VAT001").minOrderAmount(BigDecimal.valueOf(7.5)).category(StoreCategoryVariation.CAFE).deliveryTime(25).build(),
                Store.builder().name("Store5").address("Address 5").phone("698761333")
                        .vatNumber("VAT002").minOrderAmount(BigDecimal.valueOf(15.0)).category(StoreCategoryVariation.BURGER).deliveryTime(35).build(),
                Store.builder().name("Store6").address("Address 6").phone("698877665")
                        .vatNumber("VAT003").minOrderAmount(BigDecimal.valueOf(2.5)).category(StoreCategoryVariation.DELI).deliveryTime(50).build(),
                Store.builder().name("Store7").address("Address 7").phone("231098887")
                        .vatNumber("VAT004").minOrderAmount(BigDecimal.valueOf(5.0)).category(StoreCategoryVariation.GRILL).deliveryTime(35).build(),
                Store.builder().name("Store8").address("Address 8").phone("231023345")
                        .vatNumber("VAT005").minOrderAmount(BigDecimal.valueOf(7.5)).category(StoreCategoryVariation.CREPERIE).deliveryTime(20).build(),
                Store.builder().name("Store9").address("Address 9").phone("231012234")
                        .vatNumber("VAT006").minOrderAmount(BigDecimal.valueOf(3.5)).category(StoreCategoryVariation.BREAKFAST).deliveryTime(30).build(),
                Store.builder().name("Store10").address("Address 10").phone("698776655")
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


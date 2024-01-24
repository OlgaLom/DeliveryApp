package backend_group_5.we_lead_bootcamp.bootstrap;

import backend_group_5.we_lead_bootcamp.base.BaseComponent;
import backend_group_5.we_lead_bootcamp.model.Store;
import backend_group_5.we_lead_bootcamp.model.StoreCategory;
import backend_group_5.we_lead_bootcamp.service.ProductService;
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

    @Override
    public void run(String... args) throws Exception {
        Store newstore = storeService.createStore(Store.builder()
                .name("Store Name1")
                .address("Address 1")
                .phone(231012345)
                .vatNumber("")
                .minOrderAmount(BigDecimal.valueOf(5))
                .category(StoreCategory.BAKERY)
                .product());
        logger.info("Created {}.", newstore);
        createSampleStores();


    }
        private void createSampleStores() {
            List<Store> stores = List.of(
                    Store.builder().name("Store1").address("Address 1").phone(231012345)
                            .vatNumber("VAT123").minOrderAmount(BigDecimal.valueOf(5.0)).build(),
                    Store.builder().name("Store2").address("Address 2").phone(987654321)
                            .vatNumber("VAT456").minOrderAmount(BigDecimal.valueOf(5.5)).build(),
                    Store.builder().name("Store3").address("Address 3").phone(231012345)
                            .vatNumber("VAT001").minOrderAmount(BigDecimal.valueOf(7.5)).build(),
                    Store.builder().name("Store4").address("Address 4").phone(12345679)
                            .vatNumber("VAT002").minOrderAmount(BigDecimal.valueOf(15.0)).build()
                    // Add more stores as needed
            );

            List<Store> createdStores = storeService.createAllStores(stores);

            logger.info("Created {} stores.", createdStores.size());
            createdStores.forEach(store -> logger.debug("{}. {}", store.getId(), store));
        }

    private void createSampleStoresForCategory(StoreCategory storeCategory) {
        List<Store> stores = List.of(
                Store.builder().name(storeCategory.getDisplayName() + " One").address("Tsimiski 1").phone(123456789)
                        .vatNumber("VAT123").minOrderAmount(BigDecimal.valueOf(10.0)).category(storeCategory).build(),
                Store.builder().name(storeCategory.getDisplayName() + " Two").address("Filippou 2").phone(987654321)
                        .vatNumber("VAT456").minOrderAmount(BigDecimal.valueOf(15.0)).category(storeCategory).build(),
                // more and more and more
        );

        //ta methods telika prepei na ta grapsw or no?
        List<Store> createdStores = storeService.createAllStores(stores);

        logger.info("Created {} {} stores.", createdStores.size(), storeCategory.getDisplayName());
        createdStores.forEach(store -> logger.debug("{}. {}", store.getId(), store));
    }

}


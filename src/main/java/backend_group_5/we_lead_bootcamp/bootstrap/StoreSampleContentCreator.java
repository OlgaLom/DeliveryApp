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
                .minOrderAmount(5)
                .category(StoreCategory.BAKERY)
                .product());
        logger.info("Created {}.", newstore);


    }

    @Override
    public void run(String... args) throws Exception {
        // Create sample stores for each store category
        createSampleStoresForCategory(StoreCategory.CAFE);
        createSampleStoresForCategory(StoreCategory.PIZZERIA);
        createSampleStoresForCategory(StoreCategory.BAKERY);
        // more and more and more
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


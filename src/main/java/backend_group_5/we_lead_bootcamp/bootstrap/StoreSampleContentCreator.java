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

    @Override
    public void run(String... args) throws Exception {
        Store newstore = storeService.createStore(Store.builder()
                .name("Store1")
                .address("Address 1")
                .phone(231012345)
                .vatNumber("VAT123")
                .minOrderAmount(BigDecimal.valueOf(5))
                .category(StoreCategoryVariation.BAKERY)
                .build());
        logger.info("Created {}.", newstore);

        List<Store> stores = List.of(
                Store.builder().name("Store2").address("Address 2").phone(698765432)
                        .vatNumber("VAT456").minOrderAmount(BigDecimal.valueOf(5.0)).category(StoreCategoryVariation.ASIAN_FOOD).build(),
                Store.builder().name("Store3").address("Address 3").phone(698761111)
                        .vatNumber("VAT789").minOrderAmount(BigDecimal.valueOf(5.5)).category(StoreCategoryVariation.GRILL).build(),
                Store.builder().name("Store4").address("Address 4").phone(698761222)
                        .vatNumber("VAT001").minOrderAmount(BigDecimal.valueOf(7.5)).category(StoreCategoryVariation.CAFE).build(),
                Store.builder().name("Store5").address("Address 5").phone(698761333)
                        .vatNumber("VAT002").minOrderAmount(BigDecimal.valueOf(15.0)).category(StoreCategoryVariation.BURGER).build()
                // Add more stores as needed
        );

        List<Store> createdStores = storeService.createAllStores(stores);

        logger.info("Created {} stores.", createdStores.size());
        createdStores.forEach(store -> logger.debug("{}. {}", store.getId(), store));


    }

    //examples for normal cases, edge cases, data validation cases, and negative testing cases.


}


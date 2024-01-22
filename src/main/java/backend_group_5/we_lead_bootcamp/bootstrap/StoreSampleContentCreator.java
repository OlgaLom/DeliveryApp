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

}

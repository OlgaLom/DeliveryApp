package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Product;

public interface ProductRepository extends BaseRepository<Product, Long> {
    Product findBySerial(final String serial);
}

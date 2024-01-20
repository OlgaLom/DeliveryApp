package backend_group_5.we_lead_bootcamp.repository;

import backend_group_5.we_lead_bootcamp.model.Product;
import backend_group_5.we_lead_bootcamp.model.Variation;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryImpl extends BaseRepositoryImpl<Product> implements ProductRepository{
    private final ConcurrentHashMap<Long,Product> storage =new ConcurrentHashMap<>();
    private final AtomicLong sequence=new AtomicLong(0);

    @Override
    protected ConcurrentHashMap<Long,Product>getStorage(){
        return storage;
    }

    @Override
    protected AtomicLong getSequence(){
        return sequence;
    }


    @Override
    public Product findBySerial(final String serial) {
        return storage.values()
                .stream()
                .filter(c->serial.equalsIgnoreCase(c.getSerial()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product findByDescription(String description) {
        return storage.values()
                .stream()
                .filter(c->description.equalsIgnoreCase(c.getDescription()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product findByName(String productName) {
        return storage.values()
                .stream()
                .filter(c->productName.equalsIgnoreCase(c.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Product findByPrice(BigDecimal productPrice) {
        return storage.values()
                .stream()
                .filter(c->productPrice.equals(c.getPrice()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Variation> getVariationBySize(String productName, Variation.Size size) {
    Product product=findByName(productName);
    if(product!=null){
        return product.getVariations().stream()
                .filter(variation->size.equals(variation.getSize()))
                .collect(Collectors.toList());

    }
    return null;
    }

    @Override
    public List<Variation> getVariationByFlavours(String productName, Variation.Flavours flavours) {
        Product product = findByName(productName);
        if (product != null) {
            return product.getVariations().stream()
                    .filter(variation -> flavours.equals(variation.getFlavours()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public List<Variation> getVariationBySauces(String productName, Variation.Sauces sauces) {
        Product product=findByName(productName);
        if(product!=null){
            return product.getVariations().stream()
                    .filter(variation->sauces.equals(variation.getSauces()))
                    .collect(Collectors.toList());

        }
        return null;
    }

    @Override
    public List<Variation> getVariationByToppings(String productName, Variation.Toppings toppings) {
        Product product=findByName(productName);
        if(product!=null){
            return product.getVariations().stream()
                    .filter(variation->toppings.equals(variation.getToppings()))
                    .collect(Collectors.toList());

        }
        return null;
    }


}

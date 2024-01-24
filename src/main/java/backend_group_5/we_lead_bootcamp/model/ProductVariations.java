package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class ProductVariations extends BaseModel{
    private Sizes sizes;
    private Flavours flavours;
    private Sauces sauces;
    private Toppings toppings;
    public enum Sizes {
        SMALL, MEDIUM, LARGE
    }

    public enum Flavours {
        CARAMEL, HAZELNUT,SALTED_CARAMEL,COCONUT, PUMPKIN_SPICE,
        CINNAMON_DOLCE,STRAWBERRY, VANILLA
    }
    //public static Flavours getDefault(){return Flavours.CARAMEL;}
    public enum Sauces {
        KETCHUP, MUSTARD, YELLOW_SAUCE, TZATZIKI, TERIYAKI,
    }

    public enum Toppings {
        BISCUIT, SPRINKLES, CAPRICE, WHIPPED_CREAM, NUTS, MARSHMALLOWS,CINNAMON,
        CHEESE, PEPERONI, ONIONS, OLIVES, BACON
    }
}



package backend_group_5.we_lead_bootcamp.model;

import jakarta.persistence.Entity;
import lombok.Getter;
@Entity
@Getter
public class Variation extends BaseModel{
    private Size size;
    private Flavours flavours;
    private Sauces sauces;
    private Toppings toppings;
    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    public enum Flavours {
        CARAMEL, HAZELNUT,SALTED_CARAMEL,COCONUT, PUMPKIN_SPICE,
        CINNAMON_DOLCE,STRAWBERRY, VANILLA
    }

    public enum Sauces {
        KETCHUP, MUSTARD, YELLOW_SAUCE, TZATZIKI, TERIYAKI,
    }

    public enum Toppings {
        BISCUIT, SPRINKLES, CAPRICE, WHIPPED_CREAM, NUTS, MARSHMALLOWS,CINNAMON,
        CHEESE, PEPERONI, ONIONS, OLIVES, BACON
    }
}



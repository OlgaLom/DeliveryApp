package backend_group_5.we_lead_bootcamp.model;

import lombok.Getter;

@Getter
public class Variation {
    private Size size;
    private Flavours flavours;
    private Sauces sauces;
    private Toppings toppings;
    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    public enum Flavours {
        CARAMEL, HAZELNUT
    }

    public enum Sauces {
        KETCHUP, MUSTARD, YELLOW_SAUCE, TZATZIKI, TYROKAFTERI
    }

    public enum Toppings {
        BISCUIT, SPRINKLES, CAPRICE, WHIPPED_CREAM, NUTS, MARSHMALLOWS,
        CHEESE, PEPERONI, ONIONS, OLIVES, BACON
    }
}



package backend_group_5.we_lead_bootcamp.model.enums;

import lombok.Getter;

@Getter
public enum StoreCategoryVariation {

    CAFE("Cafe"),
    PIZZERIA("Pizzeria"),
    BAKERY("Bakery"),
    CREPERIE("CREPERIE"),
    JUICE_BAR("Juice Bar"),
    SMOOTHIES("Smoothies"),
    ICE_CREAM("Ice_Cream"),
    SUSHI("Sushi"),
    CUISINE("Cuisine"),
    VEGAN("Vegan"),
    VEGETARIAN("Vegetarian"),
    BURGER("Burger"),
    ASIAN_FOOD("Asian Food"),
    BREAKFAST("Breakfast"),
    SEAFOOD("Seafood"),
    MEXICAN("Mexican"),
    STEAKHOUSE("Steakhouse"),
    MARKET("Market"),
    DELI("Delicatessen"),
    PASTRY_SHOP("Pastry"),
    COCKTAILS("Cocktails"),
    GRILL("Grill"),
    ETHNIC("Ethnic"),
    INDIAN("Indian"),
    BRUNCH("Brunch"),
    SALADS("Salads"),
    SANDWICH("Sandwich"),
    BEVERAGE("Beverage Drinks"),
    OTHER("Other");

    private final String displayName;

    StoreCategoryVariation(String displayName) {
        this.displayName = displayName;
    }
}


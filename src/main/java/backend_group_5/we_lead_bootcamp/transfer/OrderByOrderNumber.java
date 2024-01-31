package backend_group_5.we_lead_bootcamp.transfer;

public record OrderByOrderNumber<
        orderNumber,
        orderTotal,
        paymentMethod,
        createDAte,
        orderStatus,
        firstName,
        LastName,
        email,
        storeName>(
        orderNumber orderNumber,
        orderTotal orderTotal,
        paymentMethod paymentMethod,
        createDAte createDAte,
        orderStatus orderStatus,
        firstName firstName,
        LastName LastName,
        email email,
        storeName storeName) {
}

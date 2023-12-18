package backend_group_5.we_lead_bootcamp.transfer;

public record ApiError(Integer status, String message, String path) {
}

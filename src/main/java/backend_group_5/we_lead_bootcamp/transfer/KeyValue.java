package backend_group_5.we_lead_bootcamp.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KeyValue<K, V>( @JsonProperty("Store Name")K key,  @JsonProperty("Revenue")V value)  {

}

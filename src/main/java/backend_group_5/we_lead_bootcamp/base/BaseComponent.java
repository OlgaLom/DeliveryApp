package backend_group_5.we_lead_bootcamp.base;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class BaseComponent {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @PostConstruct
    private void init(){ logger.trace("Loaded {}.",getClass().getName()); }

    private void destroy(){ logger.trace("Unloading {}", getClass().getName());}

}

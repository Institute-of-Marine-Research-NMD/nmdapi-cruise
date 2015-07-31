package no.imr.nmdapi.nmdcruise.service.config;

import no.imr.nmdapi.nmdcruise.service.NMDCruiseService;
import no.imr.nmdapi.nmdcruise.service.NMDCruiseServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This contains all configuration for the reference services.
 *
 * @author kjetilf
 */
@Configuration
public class NMDCruiseServiceConfig {

    /**
     * Creates the service implementation.
     *
     * @return  A reference service implementation.
     */
    @Bean(name="nmdCruiseService")
    public NMDCruiseService getNMDCruiseService() {
        return new NMDCruiseServiceImpl();
    }

}

package no.imr.nmdapi.nmdcruise.service.config;

import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author kjetilf
 */
public class TestNMDCruiseServiceConfig {

    private NMDCruiseServiceConfig config = new NMDCruiseServiceConfig();

    @Test
    public void testGetNMDCruiseService() {
        assertNotNull(config.getNMDCruiseService());
    }

}

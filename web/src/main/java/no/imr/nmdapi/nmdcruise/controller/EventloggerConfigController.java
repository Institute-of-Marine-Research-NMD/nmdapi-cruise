package no.imr.nmdapi.nmdcruise.controller;

import no.imr.framework.logging.slf4j.aspects.stereotype.ArgumentLogging;
import no.imr.framework.logging.slf4j.aspects.stereotype.PerformanceLogging;
import no.imr.nmdapi.generic.nmdeventloggerconfig.domain.jaxb.EventloggerConfigType;
import no.imr.nmdapi.nmdcruise.service.NMDCruiseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author kjetilf
 */
@Controller
public class EventloggerConfigController {

    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(EventloggerConfigController.class);
    /**
     * Service layer object for nmd mission queries.
     */
    @Autowired
    private NMDCruiseService nmdCruiseService;

    /**
     * Get eventloggerconfig
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param type
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}/config", method = RequestMethod.GET, produces = {"application/xml;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object getConfig(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestParam(value = "type") String type) {
        LOGGER.info("Start CruiseController.getInfo");
        return nmdCruiseService.getConfig(missiontype, year, platform, delivery, type);
    }

    /**
     * Get namepsace for data.
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param type
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}/config", method = RequestMethod.POST, produces = {"application/xml;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void postConfig(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestParam(value = "type") String type, @RequestBody EventloggerConfigType eventloggerConfig) {
        LOGGER.info("Start CruiseController.postConfig");
        nmdCruiseService.insertConfig(missiontype, year, platform, delivery, type, eventloggerConfig);
    }

    /**
     * Get namepsace for data.
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param type
     * @param eventloggerConfig
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}/config", method = RequestMethod.PUT, produces = {"application/xml;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void putConfig(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestParam(value = "type") String type, @RequestBody EventloggerConfigType eventloggerConfig) {
        LOGGER.info("Start CruiseController.postConfig");
        nmdCruiseService.updateConfig(missiontype, year, platform, delivery, type, eventloggerConfig);
    }

}

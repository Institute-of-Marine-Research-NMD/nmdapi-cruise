package no.imr.nmdapi.nmdcruise.controller;

import no.imr.framework.logging.slf4j.aspects.stereotype.PerformanceLogging;
import no.imr.nmd.commons.cruise.jaxb.CruiseType;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller object for mission requests.
 *
 * @author kjetilf
 */
@Controller
public class CruiseController {

    /**
     * Url part that defines it as mission.
     */
    public static final String CRUISE_URL = "/nmdcruise";

    /**
     * Class logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CruiseController.class);

    /**
     * Service layer object for nmd mission queries.
     */
    @Autowired
    private NMDCruiseService nmdCruiseService;

    /**
     * Get data for mission.
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return Response object.
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object find(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start CruiseController.findByMission");
        return nmdCruiseService.getData(missiontype, year, platform, delivery);
    }

    /**
     * Delete biotic data for mission.
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start MissionController.deleteByMission");
        nmdCruiseService.deleteData(missiontype, year, platform, delivery);
    }

    /**
     *  Insert mission data for mission.
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param missionData
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void insert(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestBody CruiseType cruise) {
        LOGGER.info("Start MissionController.insertByMission");
        nmdCruiseService.insertData(missiontype, year, platform, delivery, cruise);
    }

    /**
     * Update  mission data for mission.
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param missionData
     */
    @PerformanceLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void update(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestBody CruiseType cruise) {
        LOGGER.info("Start MissionController.updateByMission");
        nmdCruiseService.updateData(missiontype, year, platform, delivery, cruise);
    }

}


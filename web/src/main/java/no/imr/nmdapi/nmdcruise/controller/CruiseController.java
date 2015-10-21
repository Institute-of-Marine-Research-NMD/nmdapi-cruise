package no.imr.nmdapi.nmdcruise.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import no.imr.framework.logging.slf4j.aspects.stereotype.ArgumentLogging;
import no.imr.framework.logging.slf4j.aspects.stereotype.PerformanceLogging;
import no.imr.nmd.commons.cruise.jaxb.CruiseType;
import no.imr.nmdapi.exceptions.BadRequestException;
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
 * Controller object for mission requests.
 *
 * @author kjetilf
 */
@Controller
public class CruiseController {

    /**
     * Url part that defines it as mission.
     */
    public static final String CRUISE_URL = "/cruise";

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
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.GET, produces = {"application/xml;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object find(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start CruiseController.findByMission");
        return nmdCruiseService.getData(missiontype, year, platform, delivery);
    }

    /**
     * Get data by id or cruise number.
     *
     * @param cruisenr
     * @return Response object.
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object find(@RequestParam(value = "cruisenr", required = true) String cruisenr, @RequestParam(value = "shipname", required = true) String shipname, HttpServletRequest request) throws MalformedURLException, URISyntaxException {
        LOGGER.info("Start BioticController.find");
        if (cruisenr != null) {
            URI uri = (new URI(request.getRequestURL().toString())).resolve(".");
            return nmdCruiseService.getDataByCruiseNr(cruisenr, shipname, uri.toString());
        } else {
            throw new BadRequestException("Cruisenr parameters must be set.");
        }
    }

    /**
     * Get data by id or cruise number.
     *
     * @param httpServletResponse
     * @param cruisenr
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/find", method = RequestMethod.HEAD)
    @ResponseBody
    public void find(HttpServletResponse httpServletResponse, @RequestParam(value = "cruisenr", required = false) String cruisenr, @RequestParam(value = "shipname", required = true) String shipname) {
        LOGGER.info("Start BioticController.find");
        if (nmdCruiseService.hasDataByCruiseNr(cruisenr, shipname)) {
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        } else {
            httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * Does the mission have data
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.HEAD)
    @ResponseBody
    public void  hasData(HttpServletResponse httpServletResponse,@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start CruiseController.hasData");
        if (nmdCruiseService.hasData(missiontype, year, platform, delivery)){
           httpServletResponse.setStatus(HttpServletResponse.SC_OK);

        } else {
         httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
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
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void delete(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start CruiseController.deleteByMission");
        nmdCruiseService.deleteData(missiontype, year, platform, delivery);
    }

    /**
     *  Insert mission data for mission.
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param cruise
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void insert(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestBody CruiseType cruise) {
        LOGGER.info("Start CruiseController.insertByMission");
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
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void update(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery, @RequestBody CruiseType cruiseData) {
        LOGGER.info("Start CruiseController.updateByMission");
        nmdCruiseService.updateData(missiontype, year, platform, delivery, cruiseData);
    }

    /**
     * Get namepsace for data.
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return
     */
    @PerformanceLogging
    @ArgumentLogging
    @RequestMapping(value = "/{missiontype}/{year}/{platform}/{delivery}", method = RequestMethod.GET, params = {"type=info"}, produces = {"application/xml;charset=UTF-8", "application/json;charset=UTF-8"})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object getInfo(@PathVariable(value = "missiontype") String missiontype, @PathVariable(value = "year") String year, @PathVariable(value = "platform") String platform, @PathVariable(value = "delivery") String delivery) {
        LOGGER.info("Start CruiseController.getInfo");
        return nmdCruiseService.getInfo(missiontype, year, platform, delivery);
    }

}


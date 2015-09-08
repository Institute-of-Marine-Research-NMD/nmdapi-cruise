package no.imr.nmdapi.nmdcruise.service;

import no.imr.nmd.commons.cruise.jaxb.CruiseType;


/**
 * Service API for mission data.
 *
 * @author kjetilf
 */
public interface NMDCruiseService {

    /**
     * Get .
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return              Mission data.
     */
    Object getData(String missiontype, String year, String platform, String delivery);

    /**
     * Delete
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     */
    void deleteData(String missiontype, String year, String platform, String delivery);

    /**
     * Update
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param cruise   Mission data.
     */
    void updateData(String missiontype, String year, String platform, String delivery, Object cruise);

    /**
     * Insert
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @param cruise
     */
    void insertData(String missiontype, String year, String platform, String delivery, Object cruise);

    /**
     *
     * @param cruisenr
     * @return
     */
    Object getDataByCruiseNr(String cruisenr);

    /**
     *
     * @param cruisenr
     * @return
     */
    boolean hasDataByCruiseNr(String cruisenr);

    /**
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return
     */
    boolean hasData(String missiontype, String year, String platform, String delivery);


}

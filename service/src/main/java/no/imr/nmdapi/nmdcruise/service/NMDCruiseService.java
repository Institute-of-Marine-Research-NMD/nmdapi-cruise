package no.imr.nmdapi.nmdcruise.service;

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
     * @param shipname
     * @param contextpath
     * @return
     */
    Object getDataByCruiseNr(String cruisenr, String shipname, String contextpath);

    /**
     *
     * @param cruisenr
     * @param shipname
     * @return
     */
    boolean hasDataByCruiseNr(String cruisenr, String shipname);

    /**
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return
     */
    boolean hasData(String missiontype, String year, String platform, String delivery);

    /**
     *
     * @param missiontype
     * @param year
     * @param platform
     * @param delivery
     * @return
     */
    Object getInfo(String missiontype, String year, String platform, String delivery);
}

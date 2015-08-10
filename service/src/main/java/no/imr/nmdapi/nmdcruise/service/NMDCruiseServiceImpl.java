package no.imr.nmdapi.nmdcruise.service;

import no.imr.nmd.commons.cruise.jaxb.CruiseType;
import no.imr.nmdapi.dao.file.NMDDataDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * NMDMission service layer implementation.
 *
 * @author kjetilf
 */
public class NMDCruiseServiceImpl implements NMDCruiseService {

    @Autowired
    private NMDDataDao nmdDataDao;

    @Override
    public Object getData(final String missiontype, final String year, final String platform, final String delivery) {
        return nmdDataDao.get(missiontype, year, platform, delivery, CruiseType.class);
    }

    @Override
    public void deleteData(final String missiontype, final String year, final String platform, final String delivery) {
        nmdDataDao.delete(missiontype, year, platform, delivery);
        nmdDataDao.deleteDataset(missiontype, year, platform, delivery, "CRUISE");
    }

   @Override
    public void insertData(final String missiontype, final String year, final String platform, final String delivery, final CruiseType cruise) {
        nmdDataDao.insert(missiontype, year, platform, delivery, cruise, CruiseType.class);
        nmdDataDao.insertDataset(missiontype, year, platform, delivery, "CRUISE");
    }


    @Override
    public void updateData(final String missiontype, final String year, final String platform, final String delivery, final CruiseType cruise) {
        nmdDataDao.update(missiontype, year, platform, delivery, cruise, CruiseType.class);
    }

    @Override
    public Object getDataByCruiseNr(String cruisenr) {
        return null;
    }

}

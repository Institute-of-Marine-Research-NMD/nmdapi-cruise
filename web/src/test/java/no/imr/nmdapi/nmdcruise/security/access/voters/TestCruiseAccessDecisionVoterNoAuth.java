package no.imr.nmdapi.nmdcruise.security.access.voters;

import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import no.imr.nmd.commons.cruise.jaxb.CruiseType;
import no.imr.nmd.commons.dataset.jaxb.DataTypeEnum;
import no.imr.nmd.commons.dataset.jaxb.QualityEnum;
import no.imr.nmdapi.dao.file.NMDDatasetDao;
import no.imr.nmdapi.dao.file.config.CommonDaoConfig;
import no.imr.nmdapi.nmdcruise.controller.CruiseController;
import no.imr.nmdapi.nmdcruise.security.access.voters.TestCruiseAccessDecisionVoterNoAuth.Init;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionVoter;
import static org.springframework.security.access.AccessDecisionVoter.ACCESS_ABSTAIN;
import static org.springframework.security.access.AccessDecisionVoter.ACCESS_DENIED;
import static org.springframework.security.access.AccessDecisionVoter.ACCESS_GRANTED;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author kjetilf
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CommonDaoConfig.class, Init.class, CruiseAccessDecisionVoter.class})
public class TestCruiseAccessDecisionVoterNoAuth {

    @org.springframework.context.annotation.Configuration
    public static class Init {
        @Bean
        public Configuration configuration() {
            Configuration cfg = new PropertiesConfiguration();
            cfg.addProperty("pre.data.dir", System.getProperty("java.io.tmpdir"));
            cfg.addProperty("default.writerole", "SG-WRITE-BIOTIC");
            cfg.addProperty("default.readrole", "unrestricted");
            cfg.addProperty("app.packages", "no.imr.nmd.commons.dataset.jaxb:no.imr.nmd.commons.cruise.jaxb");
            return cfg;
        }
    }


    @Autowired
    private NMDDatasetDao datasetDao;

    @Autowired
    private AccessDecisionVoter<FilterInvocation>  accessDecisionVoter;

    /**
     * Test that the voter abstains if it's not a cruise url.
     */
    @Test
    public void testNotBioticUrl() {
        Authentication auth = mock(Authentication.class);
        FilterInvocation filter = mock(FilterInvocation.class);
        doReturn("/reference").when(filter).getFullRequestUrl();
        Collection<ConfigAttribute> confAttr = mock(Collection.class);
        assertEquals(ACCESS_ABSTAIN, accessDecisionVoter.vote(auth, filter, confAttr));
    }

    /**
     * Test that HEAD is allowed when not authenticated
     */
    @Test
    public void testHeadNoAuth() {
        Authentication auth = mock(Authentication.class);
        doReturn(Boolean.FALSE).when(auth).isAuthenticated();
        FilterInvocation filter = mock(FilterInvocation.class);
        doReturn(CruiseController.CRUISE_URL).when(filter).getFullRequestUrl();
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn(HttpMethod.HEAD.name());
        when(filter.getHttpRequest()).thenReturn(servletRequest);
        Collection<ConfigAttribute> confAttr = mock(Collection.class);
        assertEquals(ACCESS_GRANTED, accessDecisionVoter.vote(auth, filter, confAttr));
    }

    /**
     * Test that POST is not allowed when not authenticated
     */
    @Test
    public void testPostNoAuth() {
        Authentication auth = mock(Authentication.class);
        doReturn(Boolean.FALSE).when(auth).isAuthenticated();
        FilterInvocation filter = mock(FilterInvocation.class);
        doReturn(CruiseController.CRUISE_URL).when(filter).getFullRequestUrl();
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn(HttpMethod.POST.name());
        when(filter.getHttpRequest()).thenReturn(servletRequest);
        Collection<ConfigAttribute> confAttr = mock(Collection.class);
        assertEquals(ACCESS_DENIED, accessDecisionVoter.vote(auth, filter, confAttr));
    }

    /**
     * Test that PUT is not allowed when not authenticated
     */
    @Test
    public void testPutNoAuth() {
        Authentication auth = mock(Authentication.class);
        doReturn(Boolean.FALSE).when(auth).isAuthenticated();
        FilterInvocation filter = mock(FilterInvocation.class);
        doReturn(CruiseController.CRUISE_URL).when(filter).getFullRequestUrl();
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn(HttpMethod.PUT.name());
        when(filter.getHttpRequest()).thenReturn(servletRequest);
        when(filter.getRequestUrl()).thenReturn("/Forskningsfartøy/2015/G O Sars_LMEL/2015101");
        Collection<ConfigAttribute> confAttr = mock(Collection.class);
        assertEquals(ACCESS_DENIED, accessDecisionVoter.vote(auth, filter, confAttr));
    }

    /**
     * Test that PUT is not allowed when not authenticated
     */
    @Test
    public void testDeleteNoAuth() {
        Authentication auth = mock(Authentication.class);
        doReturn(Boolean.FALSE).when(auth).isAuthenticated();
        FilterInvocation filter = mock(FilterInvocation.class);
        doReturn(CruiseController.CRUISE_URL).when(filter).getFullRequestUrl();
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn(HttpMethod.DELETE.name());
        when(filter.getHttpRequest()).thenReturn(servletRequest);
        when(filter.getRequestUrl()).thenReturn("/Forskningsfartøy/2015/G O Sars_LMEL/2015101");
        Collection<ConfigAttribute> confAttr = mock(Collection.class);
        assertEquals(ACCESS_DENIED, accessDecisionVoter.vote(auth, filter, confAttr));
    }

    /**
     * Test that GET is allowed when not authenticated
     * and there are missing arguments.
     */
    @Test
    public void testGetNoAuthMissingArgs() {
        Authentication auth = mock(Authentication.class);
        doReturn(Boolean.FALSE).when(auth).isAuthenticated();
        FilterInvocation filter = mock(FilterInvocation.class);
        doReturn(CruiseController.CRUISE_URL).when(filter).getFullRequestUrl();
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn(HttpMethod.GET.name());
        when(filter.getHttpRequest()).thenReturn(servletRequest);
        when(filter.getRequestUrl()).thenReturn("/Forskningsfartøy/2015/G O Sars_LMEL");
        Collection<ConfigAttribute> confAttr = mock(Collection.class);
        assertEquals(ACCESS_GRANTED, accessDecisionVoter.vote(auth, filter, confAttr));
    }

    /**
     * Test that GET is allowed when not authenticated and is unrestricted
     */
    @Test
    public void testGetNoAuthUnrestricted() {
        CruiseType mission = new CruiseType();
        if (datasetDao.hasData(DataTypeEnum.CRUISE, "data", "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101")) {
            datasetDao.delete(DataTypeEnum.CRUISE, "data", true, "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101");
        }
        if (datasetDao.hasDataset(DataTypeEnum.CRUISE, "data", "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101")) {
            datasetDao.removeDataset(DataTypeEnum.CRUISE, "data", "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101");
        }
        datasetDao.insert(DataTypeEnum.CRUISE, "data", mission, "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101");
        datasetDao.createDataset("SG-WRITE", "unrestricted", "" ,"imr", QualityEnum.NONE, DataTypeEnum.CRUISE, "data", null, "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101");

        Authentication auth = mock(Authentication.class);
        doReturn(Boolean.FALSE).when(auth).isAuthenticated();
        FilterInvocation filter = mock(FilterInvocation.class);
        doReturn(CruiseController.CRUISE_URL).when(filter).getFullRequestUrl();
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn(HttpMethod.GET.name());
        when(filter.getHttpRequest()).thenReturn(servletRequest);
        when(filter.getRequestUrl()).thenReturn("/Forskningsfartøy/2015/G O Sars_LMEL/2015101");
        Collection<ConfigAttribute> confAttr = mock(Collection.class);
        assertEquals(ACCESS_GRANTED, accessDecisionVoter.vote(auth, filter, confAttr));
    }

    /**
     * Test that GET is not allowed when not authenticated and is is not unrestricted
     */
    @Test
    public void testGetNoAuthNotUnrestricted() {
        CruiseType mission = new CruiseType();
        if (datasetDao.hasData(DataTypeEnum.CRUISE, "data", "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101")) {
            datasetDao.delete(DataTypeEnum.CRUISE, "data", true, "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101");
        }
        if (datasetDao.hasDataset(DataTypeEnum.CRUISE, "data", "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101")) {
            datasetDao.removeDataset(DataTypeEnum.CRUISE, "data", "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101");
        }
        datasetDao.insert(DataTypeEnum.CRUISE, "data", mission, "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101");
        datasetDao.createDataset("SG-WRITE", "SG-READ", "" ,"imr", QualityEnum.NONE, DataTypeEnum.CRUISE, "data", null, "Forskningsfartøy", "2015", "G O Sars_LMEL", "2015101");

        Authentication auth = mock(Authentication.class);
        doReturn(Boolean.FALSE).when(auth).isAuthenticated();
        FilterInvocation filter = mock(FilterInvocation.class);
        doReturn(CruiseController.CRUISE_URL).when(filter).getFullRequestUrl();
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        when(servletRequest.getMethod()).thenReturn(HttpMethod.GET.name());
        when(filter.getHttpRequest()).thenReturn(servletRequest);
        when(filter.getRequestUrl()).thenReturn("/Forskningsfartøy/2015/G O Sars_LMEL/2015101");
        Collection<ConfigAttribute> confAttr = mock(Collection.class);
        assertEquals(ACCESS_DENIED, accessDecisionVoter.vote(auth, filter, confAttr));
    }

}

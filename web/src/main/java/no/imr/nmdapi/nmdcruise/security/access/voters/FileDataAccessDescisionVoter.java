package no.imr.nmdapi.nmdcruise.security.access.voters;

import java.util.Collection;
import no.imr.nmdapi.dao.file.NMDDataDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

/**
 *
 * @author kjetilf
 */
@Service
public class FileDataAccessDescisionVoter implements AccessDecisionVoter<FilterInvocation> {

    @Autowired
    private NMDDataDao dataDao;

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(FilterInvocation.class);
    }

    @Override
    public int vote(Authentication auth, FilterInvocation filterArg, Collection<ConfigAttribute> cfgArgs) {
        if (filterArg.getHttpRequest().getMethod().equalsIgnoreCase(HttpMethod.GET.name())) {
            return ACCESS_GRANTED;
        } else if (filterArg.getHttpRequest().getMethod().equalsIgnoreCase(HttpMethod.PUT.name()) ||
                filterArg.getHttpRequest().getMethod().equalsIgnoreCase(HttpMethod.DELETE.name())) {
            return ACCESS_GRANTED;
        } else {
            return ACCESS_ABSTAIN;
        }
    }

}

package no.imr.nmdapi.nmdcruise.converters.mapper;

import com.sun.xml.bind.marshaller.NamespacePrefixMapper;

/**
 *
 * @author kjetilf
 */
public class CruiseNamespacePrefixMapper extends NamespacePrefixMapper {

    public static final String BIOTIC_NS = "http://www.imr.no/formats/nmdcruise/v1";

    @Override
    public String getPreferredPrefix(String namespaceUri,
                               String suggestion,
                               boolean requirePrefix) {
        return "";
    }

}

package org.uefs.n.sos.util;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestModel {

    public static String generateObservation(String station, String stationName, String latitude, String longitude, String timestamp, String temperature, String humidity) {
        return ""
                + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<env:Envelope\n"
                + "    xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\"\n"
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2003/05/soap-envelope http://www.w3.org/2003/05/soap-envelope/soap-envelope.xsd\">\n"
                + "    <env:Body>\n"
                + "        <sos:InsertObservation\n"
                + "            xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n"
                + "            xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n"
                + "            xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n"
                + "            xmlns:sml=\"http://www.opengis.net/sensorML/1.0.1\"\n"
                + "            xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n"
                + "            xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                + "            xmlns:om=\"http://www.opengis.net/om/2.0\"\n"
                + "            xmlns:sams=\"http://www.opengis.net/samplingSpatial/2.0\"\n"
                + "            xmlns:sf=\"http://www.opengis.net/sampling/2.0\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sos.xsd            http://www.opengis.net/samplingSpatial/2.0 http://schemas.opengis.net/samplingSpatial/2.0/spatialSamplingFeature.xsd\">\n"
                + "            <sos:offering>Temperatura</sos:offering>\n"
                + "            <sos:offering>Umidade</sos:offering>\n"
                + "            <sos:observation>\n"
                + "                <om:OM_Observation gml:id=\"o1\">\n"
                + "                    <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "                    <om:phenomenonTime>\n"
                + "                        <gml:TimeInstant gml:id=\"phenomenonTimeTemp\">\n"
                + "                            <gml:timePosition>" + timestamp + ".000-03:00</gml:timePosition>\n"
                + "                        </gml:TimeInstant>\n"
                + "                    </om:phenomenonTime>\n"
                + "                    <om:resultTime xlink:href=\"#phenomenonTimeTemp\"/>\n"
                + "                    <om:procedure xlink:href=\"" + station + "\"/>\n"
                + "                    <om:observedProperty xlink:href=\"Temperatura\"/>\n"
                + "                    <om:featureOfInterest>\n"
                + "                        <sams:SF_SpatialSamplingFeature gml:id=\"" + station + "-ssf\">\n"
                + "                            <gml:identifier codeSpace=\"\">" + station + "-feature</gml:identifier>\n"
                + "                            <gml:name>" + stationName + "</gml:name>\n"
                + "                            <sf:sampledFeature xlink:href=\"" + station + "-1\"/>\n"
                + "                            <sams:shape>\n"
                + "                                <gml:Point gml:id=\"" + station + "\">\n"
                + "                                    <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">" + latitude + " " + longitude + "</gml:pos>\n"
                + "                                </gml:Point>\n"
                + "                            </sams:shape>\n"
                + "                        </sams:SF_SpatialSamplingFeature>\n"
                + "                    </om:featureOfInterest>\n"
                + "                    <om:result xsi:type=\"gml:MeasureType\" uom=\"Cel\">" + temperature + "</om:result>\n"
                + "                </om:OM_Observation>\n"
                + "            </sos:observation>\n"
                + "            <sos:observation>\n"
                + "                <om:OM_Observation gml:id=\"o2\">\n"
                + "                    <om:type xlink:href=\"http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement\"/>\n"
                + "                    <om:phenomenonTime>\n"
                + "                        <gml:TimeInstant gml:id=\"phenomenonTimeHum\">\n"
                + "                            <gml:timePosition>" + timestamp + ".000-03:00</gml:timePosition>\n"
                + "                        </gml:TimeInstant>\n"
                + "                    </om:phenomenonTime>\n"
                + "                    <om:resultTime xlink:href=\"#phenomenonTimeHum\"/>\n"
                + "                    <om:procedure xlink:href=\"" + station + "\"/>\n"
                + "                    <om:observedProperty xlink:href=\"Umidade\"/>\n"
                + "                    <om:featureOfInterest>\n"
                + "                        <sams:SF_SpatialSamplingFeature gml:id=\"" + station + "-ssf\">\n"
                + "                            <gml:identifier codeSpace=\"\">" + station + "-feature</gml:identifier>\n"
                + "                            <gml:name>" + stationName + "</gml:name>\n"
                + "                            <sf:sampledFeature xlink:href=\"" + station + "-1\"/>\n"
                + "                            <sams:shape>\n"
                + "                                <gml:Point gml:id=\"" + station + "\">\n"
                + "                                    <gml:pos srsName=\"http://www.opengis.net/def/crs/EPSG/0/4326\">" + latitude + " " + longitude + "</gml:pos>\n"
                + "                                </gml:Point>\n"
                + "                            </sams:shape>\n"
                + "                        </sams:SF_SpatialSamplingFeature>\n"
                + "                    </om:featureOfInterest>\n"
                + "                    <om:result xsi:type=\"gml:MeasureType\" uom=\"%\">" + humidity + "</om:result>\n"
                + "                </om:OM_Observation>\n"
                + "            </sos:observation>\n"
                + "        </sos:InsertObservation>\n"
                + "    </env:Body>\n"
                + "</env:Envelope>";
    }

    public static String generateSensor(String station, JSONObject json) throws JSONException {
        JSONObject data = json.getJSONObject(station);
        return ""
                + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<env:Envelope\n"
                + "    xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\"\n"
                + "    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.w3.org/2003/05/soap-envelope http://www.w3.org/2003/05/soap-envelope/soap-envelope.xsd http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sosInsertSensor.xsd     http://www.opengis.net/swes/2.0 http://schemas.opengis.net/swes/2.0/swes.xsd\">\n"
                + "    <env:Body>\n"
                + "        <swes:InsertSensor\n"
                + "            xmlns:swes=\"http://www.opengis.net/swes/2.0\"\n"
                + "            xmlns:sos=\"http://www.opengis.net/sos/2.0\"\n"
                + "            xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n"
                + "            xmlns:sml=\"http://www.opengis.net/sensorml/2.0\"\n"
                + "            xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n"
                + "            xmlns:xlink=\"http://www.w3.org/1999/xlink\"\n"
                + "            xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "            xmlns:gco=\"http://www.isotc211.org/2005/gco\"\n"
                + "            xmlns:gmd=\"http://www.isotc211.org/2005/gmd\" service=\"SOS\" version=\"2.0.0\" xsi:schemaLocation=\"http://www.opengis.net/sos/2.0 http://schemas.opengis.net/sos/2.0/sosInsertSensor.xsd     http://www.opengis.net/swes/2.0 http://schemas.opengis.net/swes/2.0/swes.xsd\">\n"
                + "            <swes:procedureDescriptionFormat>http://www.opengis.net/sensorml/2.0</swes:procedureDescriptionFormat>\n"
                + "            <swes:procedureDescription>\n"
                + "                <sml:PhysicalSystem\n"
                + "                    xmlns:gco=\"http://www.isotc211.org/2005/gco\"\n"
                + "                    xmlns:gmd=\"http://www.isotc211.org/2005/gmd\"\n"
                + "                    xmlns:gml=\"http://www.opengis.net/gml/3.2\"\n"
                + "                    xmlns:sml=\"http://www.opengis.net/sensorml/2.0\"\n"
                + "                    xmlns:swe=\"http://www.opengis.net/swe/2.0\"\n"
                + "                    xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"
                + "                    xmlns:xlink=\"http://www.w3.org/1999/xlink\" gml:id=\"" + station + "\" xsi:schemaLocation=\"http://www.opengis.net/sensorml/2.0 http://schemas.opengis.net/sensorML/2.0/sensorML.xsd http://www.opengis.net/swe/2.0 http://schemas.opengis.net/sweCommon/2.0/swe.xsd\">\n"
                + "                    <gml:description>" + data.getString("description") + "</gml:description>\n"
                + "                    <gml:identifier codeSpace=\"uniqueID\">" + station + "</gml:identifier>\n"
                + "                    <sml:keywords>\n"
                + "                        <sml:KeywordList>\n"
                + "                            <sml:keyword>Weather Station</sml:keyword>\n"
                + "                            <sml:keyword>Temperature</sml:keyword>\n"
                + "                            <sml:keyword>Humidity</sml:keyword>\n"
                + "                            <sml:keyword>Local Climate Zone</sml:keyword>\n"
                + "                            <sml:keyword>Feira de Santana</sml:keyword>\n"
                + "                        </sml:KeywordList>\n"
                + "                    </sml:keywords>\n"
                + "                    <sml:identification>\n"
                + "                        <sml:IdentifierList>\n"
                + "                            <sml:identifier>\n"
                + "                                <sml:Term definition=\"http://www.nexosproject.eu/dictionary/definitions.html#UUID\">\n"
                + "                                    <sml:label>UUID</sml:label>\n"
                + "                                    <sml:value>" + station + "</sml:value>\n"
                + "                                </sml:Term>\n"
                + "                            </sml:identifier>\n"
                + "                            <sml:identifier>\n"
                + "                                <sml:Term definition=\"http://www.nexosproject.eu/dictionary/definitions.html#shortName\">\n"
                + "                                    <sml:label>Short name</sml:label>\n"
                + "                                    <sml:value>" + data.getString("name") + "</sml:value>\n"
                + "                                </sml:Term>\n"
                + "                            </sml:identifier>\n"
                + "                        </sml:IdentifierList>\n"
                + "                    </sml:identification>\n"
                + "                    <sml:capabilities name=\"offerings\">\n"
                + "                        <sml:CapabilityList>\n"
                + "                            <sml:capability name=\"offeringID\">\n"
                + "                                <swe:Text definition=\"urn:ogc:def:identifier:OGC:offeringID\">\n"
                + "                                    <swe:label>offeringID</swe:label>\n"
                + "                                    <swe:value>Temperatura</swe:value>\n"
                + "                                </swe:Text>\n"
                + "                            </sml:capability>\n"
                + "                            <sml:capability name=\"offeringID\">\n"
                + "                                <swe:Text definition=\"urn:ogc:def:identifier:OGC:offeringID\">\n"
                + "                                    <swe:label>offeringID</swe:label>\n"
                + "                                    <swe:value>Umidade</swe:value>\n"
                + "                                </swe:Text>\n"
                + "                            </sml:capability>\n"
                + "                        </sml:CapabilityList>\n"
                + "                    </sml:capabilities>\n"
                + "                    <sml:contacts>\n"
                + "                        <sml:ContactList>\n"
                + "                            <sml:contact xlink:title=\"pointOfContact\">\n"
                + "                                <gmd:CI_ResponsibleParty>\n"
                + "                                    <gmd:organisationName>\n"
                + "                                        <gco:CharacterString>UEFS</gco:CharacterString>\n"
                + "                                    </gmd:organisationName>\n"
                + "                                    <gmd:contactInfo>\n"
                + "                                        <gmd:CI_Contact>\n"
                + "                                            <gmd:address>\n"
                + "                                                <gmd:CI_Address>\n"
                + "                                                    <gmd:city>\n"
                + "                                                        <gco:CharacterString>Feira de Santana</gco:CharacterString>\n"
                + "                                                    </gmd:city>\n"
                + "                                                    <gmd:country>\n"
                + "                                                        <gco:CharacterString>Brazil</gco:CharacterString>\n"
                + "                                                    </gmd:country>\n"
                + "                                                </gmd:CI_Address>\n"
                + "                                            </gmd:address>\n"
                + "                                        </gmd:CI_Contact>\n"
                + "                                    </gmd:contactInfo>\n"
                + "                                    <gmd:role>\n"
                + "                                        <gmd:CI_RoleCode codeList=\"http://www.isotc211.org/2005/resources/Codelist/gmxCodelists.xml#CI_RoleCode/\" codeListValue=\"CI_RoleCode_pointOfContact\">Point of Contact</gmd:CI_RoleCode>\n"
                + "                                    </gmd:role>\n"
                + "                                </gmd:CI_ResponsibleParty>\n"
                + "                            </sml:contact>\n"
                + "                            <sml:contact xlink:title=\"owner\">\n"
                + "                                <gmd:CI_ResponsibleParty>\n"
                + "                                    <gmd:organisationName>\n"
                + "                                        <gco:CharacterString>UEFS</gco:CharacterString>\n"
                + "                                    </gmd:organisationName>\n"
                + "                                    <gmd:contactInfo>\n"
                + "                                        <gmd:CI_Contact>\n"
                + "                                            <gmd:address>\n"
                + "                                                <gmd:CI_Address>\n"
                + "                                                    <gmd:deliveryPoint>\n"
                + "                                                        <gco:CharacterString>Av. Transnordestina, s/n - Novo Horizonte</gco:CharacterString>\n"
                + "                                                    </gmd:deliveryPoint>\n"
                + "                                                    <gmd:city>\n"
                + "                                                        <gco:CharacterString>Feira de Santana</gco:CharacterString>\n"
                + "                                                    </gmd:city>\n"
                + "                                                    <gmd:postalCode>\n"
                + "                                                        <gco:CharacterString>44036900</gco:CharacterString>\n"
                + "                                                    </gmd:postalCode>\n"
                + "                                                    <gmd:country>\n"
                + "                                                        <gco:CharacterString>Brasil</gco:CharacterString>\n"
                + "                                                    </gmd:country>\n"
                + "                                                </gmd:CI_Address>\n"
                + "                                            </gmd:address>\n"
                + "                                        </gmd:CI_Contact>\n"
                + "                                    </gmd:contactInfo>\n"
                + "                                    <gmd:role>\n"
                + "                                        <gmd:CI_RoleCode codeList=\"http://www.isotc211.org/2005/resources/Codelist/gmxCodelists.xml#CI_RoleCode/\" codeListValue=\"CI_RoleCode_owner\">Owner</gmd:CI_RoleCode>\n"
                + "                                    </gmd:role>\n"
                + "                                </gmd:CI_ResponsibleParty>\n"
                + "                            </sml:contact>\n"
                + "                            <sml:contact xlink:title=\"operator\">\n"
                + "                                <gmd:CI_ResponsibleParty>\n"
                + "                                    <gmd:organisationName>\n"
                + "                                        <gco:CharacterString>Natanael Simoes</gco:CharacterString>\n"
                + "                                    </gmd:organisationName>\n"
                + "                                    <gmd:contactInfo>\n"
                + "                                        <gmd:CI_Contact>\n"
                + "                                            <gmd:phone>\n"
                + "                                                <gmd:CI_Telephone>\n"
                + "                                                    <gmd:voice>\n"
                + "                                                        <gco:CharacterString>+556921030100</gco:CharacterString>\n"
                + "                                                    </gmd:voice>\n"
                + "                                                </gmd:CI_Telephone>\n"
                + "                                            </gmd:phone>\n"
                + "                                            <gmd:address>\n"
                + "                                                <gmd:CI_Address>\n"
                + "                                                    <gmd:deliveryPoint>\n"
                + "                                                        <gco:CharacterString>Rodovia RO 257, Km 13, Zona Rural</gco:CharacterString>\n"
                + "                                                    </gmd:deliveryPoint>\n"
                + "                                                    <gmd:city>\n"
                + "                                                        <gco:CharacterString>Ariquemes</gco:CharacterString>\n"
                + "                                                    </gmd:city>\n"
                + "                                                    <gmd:administrativeArea>\n"
                + "                                                        <gco:CharacterString>Rondonia</gco:CharacterString>\n"
                + "                                                    </gmd:administrativeArea>\n"
                + "                                                    <gmd:postalCode>\n"
                + "                                                        <gco:CharacterString>76878899</gco:CharacterString>\n"
                + "                                                    </gmd:postalCode>\n"
                + "                                                    <gmd:country>\n"
                + "                                                        <gco:CharacterString>Brasil</gco:CharacterString>\n"
                + "                                                    </gmd:country>\n"
                + "                                                    <gmd:electronicMailAddress>\n"
                + "                                                        <gco:CharacterString>natanael.simoes@ifro.edu.br</gco:CharacterString>\n"
                + "                                                    </gmd:electronicMailAddress>\n"
                + "                                                </gmd:CI_Address>\n"
                + "                                            </gmd:address>\n"
                + "                                        </gmd:CI_Contact>\n"
                + "                                    </gmd:contactInfo>\n"
                + "                                    <gmd:role>\n"
                + "                                        <gmd:CI_RoleCode codeList=\"http://www.isotc211.org/2005/resources/Codelist/gmxCodelists.xml#CI_RoleCode/\" codeListValue=\"CI_RoleCode_custodian\">Operator</gmd:CI_RoleCode>\n"
                + "                                    </gmd:role>\n"
                + "                                </gmd:CI_ResponsibleParty>\n"
                + "                            </sml:contact>\n"
                + "                        </sml:ContactList>\n"
                + "                    </sml:contacts>\n"
                + "                    <sml:featuresOfInterest>\n"
                + "                        <sml:FeatureList definition=\"http://www.opengis.net/def/featureOfInterest/identifier\">\n"
                + "                            <swe:label>featuresOfInterest</swe:label>\n"
                + "                            <sml:feature xlink:href=\"" + station + "-feature\"/>\n"
                + "                        </sml:FeatureList>\n"
                + "                    </sml:featuresOfInterest>\n"
                + "                    <sml:position>\n"
                + "                        <swe:DataRecord>\n"
                + "                            <swe:field name=\"longitude\">\n"
                + "                                <swe:Quantity referenceFrame=\"urn:ogc:crs:EPSG:4326\" axisID=\"Lon\">\n"
                + "                                    <swe:uom code=\"deg\"/>\n"
                + "                                    <swe:value>" + data.getJSONObject("center").getString("lng") + "</swe:value>\n"
                + "                                </swe:Quantity>\n"
                + "                            </swe:field>\n"
                + "                            <swe:field name=\"latitude\">\n"
                + "                                <swe:Quantity referenceFrame=\"urn:ogc:crs:EPSG:4326\" axisID=\"Lat\">\n"
                + "                                    <swe:uom code=\"deg\"/>\n"
                + "                                    <swe:value>" + data.getJSONObject("center").getString("lat") + "</swe:value>\n"
                + "                                </swe:Quantity>\n"
                + "                            </swe:field>\n"
                + "                        </swe:DataRecord>\n"
                + "                    </sml:position>\n"
                + "                </sml:PhysicalSystem>\n"
                + "            </swes:procedureDescription>\n"
                + "            <swes:observableProperty>Temperatura</swes:observableProperty>\n"
                + "            <swes:observableProperty>Umidade</swes:observableProperty>\n"
                + "            <swes:metadata>\n"
                + "                <sos:SosInsertionMetadata>\n"
                + "                    <sos:observationType>http://www.opengis.net/def/observationType/OGC-OM/2.0/OM_Measurement</sos:observationType>\n"
                + "                    <sos:featureOfInterestType>http://www.opengis.net/def/samplingFeatureType/OGC-OM/2.0/SF_SamplingPoint</sos:featureOfInterestType>\n"
                + "                </sos:SosInsertionMetadata>\n"
                + "            </swes:metadata>\n"
                + "        </swes:InsertSensor>\n"
                + "    </env:Body>\n"
                + "</env:Envelope>";
    }

}

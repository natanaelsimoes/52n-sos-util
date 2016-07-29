package org.uefs.n.sos.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1")
public class UtilServiceController {

    JSONObject json;

    public UtilServiceController() throws IOException, JSONException {
        Resource resource = new ClassPathResource("stations.json");
        InputStream jsonStream = resource.getInputStream();
        String jsonPath = IOUtils.toString(jsonStream);
        this.json = new JSONObject(jsonPath);
    }

    @RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.GET)
    public String defaultOperation() {
        return "{"
                + serviceInformation("timestamp", "Server clock", "Get actual date and time from server.") + ","
                + serviceInformation("observation", "Send observation", "Post an observation.") + ","
                + serviceInformation("sensor/insert", "Insert sensor", "Insert a sensor/station in the network")
                + "}";
    }

    private String serviceInformation(String id, String label, String description) {
        return "{\"id\":\"" + id + "\",\"label\":\"" + label + "\",\"description\":\"" + description + "\"}";
    }

    @RequestMapping(value = "/timestamp", method = RequestMethod.GET)
    public String getTimestamp() {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        String dow = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        SimpleDateFormat sdf = new SimpleDateFormat("|MM|dd|yyyy|HH|mm|ss");
        return dow + sdf.format(date);
    }

    @RequestMapping(value = "/observation/{station}/{temperature}/{humidity}/{timestamp}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public String sendObservation(@PathVariable String station, @PathVariable int temperature, @PathVariable int humidity, @PathVariable String timestamp) {
        JSONObject stationData = this.json.getJSONObject(station);
        String stationName = stationData.getString("name");
        String latitude = stationData.getJSONObject("center").getString("lat");
        String longitude = stationData.getJSONObject("center").getString("lng");
        String xml = RequestModel.generateObservation(station, stationName, latitude, longitude, timestamp, String.valueOf(temperature), String.valueOf(humidity));
        return this.doRequest(xml);
    }

    @RequestMapping(value = "/sensor/insert/{station}", produces = MediaType.APPLICATION_XML_VALUE, method = RequestMethod.POST)
    public String insertSensor(@PathVariable String station) {
        String xml = RequestModel.generateSensor(station, this.json);
        return xml;
        //return this.doRequest(xml);
    }

    private String doRequest(String xml) {
        String serviceURL = json.getString("serviceURL");
        HttpHeaders header = new HttpHeaders();
        MediaType soap = new MediaType("application", "soap+xml");
        header.setContentType(soap);
        HttpEntity<String> entity = new HttpEntity<String>(xml, header);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> response = restTemplate.postForEntity(serviceURL, entity, String.class);
        return response.getBody();
    }

}

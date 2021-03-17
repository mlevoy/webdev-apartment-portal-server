package com.example.ApartmentPortalServer.repositories;

import com.example.ApartmentPortalServer.models.Property;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PropertyApiDataRepository {
  String apiKey = "5eae7e88acac2e29d9f8ade63d88d54f";

  public List<Property> getProperties(String zipCode) throws ParseException {
    String url = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/sale/snapshot?address1=" + zipCode + "&address2=" + zipCode + "&radius=10&pageSize=100";
    Map<String, Object> responseBodyMap = atomGetCall(url);
    return parseResponseBodyIntoProperties(responseBodyMap);
  }

  private List<Property> parseResponseBodyIntoProperties(Map<String, Object> responseBody) throws ParseException {
    List<Map<String, Object>> properties = (List<Map<String, Object>>) responseBody.get("property");
    List<Property> result = new ArrayList<>();
    for (Map<String, Object> propertyMap : properties) {
      Property property = parsePropertyMap(propertyMap);
      result.add(property);
    }
    return result;
  }

  private Property parsePropertyMap(Map<String, Object> propertyMap) throws ParseException {
    Property property = new Property();
    property.setId(String.valueOf(((Map<String, Object>) propertyMap.get("identifier")).get("obPropId")));
    Map<String, Object> propertyAddress = (Map<String, Object>) propertyMap.get("address");
    property.setAddress((String) propertyAddress.get("oneLine"));
    property.setName((String) propertyAddress.get("oneLine"));
    property.setLocality((String) propertyAddress.get("locality"));
    property.setCity((String) propertyAddress.get("locality"));
    property.setPostal((String) propertyAddress.get("postal1"));
    property.setState((String) propertyAddress.get("countrySubd"));
    property.setType((String) (((Map<String, Object>) propertyMap.get("summary")).get("propclass")));
    property.setBuiltYear((Integer) (((Map<String, Object>) propertyMap.get("summary")).get("yearbuilt")));
    Map<String, Object> propertyBuilding = (Map<String, Object>) propertyMap.get("building");
    property.setSize(new Float((Integer) ((Map<String, Object>) propertyBuilding.get("size")).get("universalsize")));
    property.setBed((Integer) (((Map<String, Object>) propertyBuilding.get("rooms")).get("beds")));
    property.setBath((Double) (((Map<String, Object>) propertyBuilding.get("rooms")).get("bathstotal")));
    Map<String, Object> propertySale = (Map<String, Object>) propertyMap.get("sale");
    String datePostedString = (String) propertySale.get("salesearchdate");
    if (datePostedString != null && !datePostedString.isEmpty()) {
      Date datePosted = new SimpleDateFormat("yyyy-MM-dd").parse(datePostedString);
      property.setDatePosted(datePosted);
    }
    String dateAvailableString = (String) propertySale.get("saleTransDate");
    if (dateAvailableString != null && !dateAvailableString.isEmpty()) {
      Date dateAvailable = new SimpleDateFormat("yyyy-MM-dd").parse(dateAvailableString);
      property.setDateAvailable(dateAvailable);
    }
    property.setAmount(new Double((Integer) ((Map<String, Object>) (propertySale.get("amount"))).get("saleamt")));
    property.setDescription("This property is added from Atom Data API");
    property.setSeller("5e9e0d06c3a78746c1d6528a");
    return property;
  }

  public Property getPropertyById(String id) throws ParseException {
    String url = "https://api.gateway.attomdata.com/propertyapi/v1.0.0/property/detail?id=" + id;
    Map<String, Object> responseMap = atomGetCall(url);
    Map<String, Object> propertyMap = ((List<Map<String, Object>>) responseMap.get("property")).get(0);
    String addressLine1 = (String) ((Map<String, Object>) propertyMap.get("address")).get("line1");
    String addressLine2 = (String) ((Map<String, Object>) propertyMap.get("address")).get("line2");
    String url2 = String.format("https://api.gateway.attomdata.com/propertyapi/v1.0.0/sale/detail?address1=%s&address2=%s",
            URLEncoder.encode(addressLine1),
            URLEncoder.encode(addressLine2));
    List<Property> properties = parseResponseBodyIntoProperties(atomGetCall(url2));
    return properties.get(0);
  }

  private Map<String, Object> atomGetCall(String url) {
    Header headerKey1 = new Header("accept", "application/json");
    Header headerKey2 = new Header("apikey", apiKey);
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> responseBodyMap = new HashMap<>();

    HttpClient client = new HttpClient();
    GetMethod method = new GetMethod(url);

    method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
            new DefaultHttpMethodRetryHandler(3, false));
    method.addRequestHeader(headerKey1);
    method.addRequestHeader(headerKey2);

    try {
      int statusCode = client.executeMethod(method);

      if (statusCode != HttpStatus.SC_OK) {
        System.err.println("Method failed: " + method.getStatusLine());
      }
      byte[] responseBody = method.getResponseBody();
      responseBodyMap
              = objectMapper.readValue(new String(responseBody), new TypeReference<Map<String, Object>>() {
      });
    } catch (HttpException e) {
      System.err.println("Fatal protocol violation: " + e.getMessage());
      e.printStackTrace();
    } catch (IOException e) {
      System.err.println("Fatal transport error: " + e.getMessage());
      e.printStackTrace();
    } finally {
      method.releaseConnection();
    }
    return responseBodyMap;
  }


}

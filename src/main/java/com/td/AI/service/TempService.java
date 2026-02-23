package com.td.AI.service;

import com.td.AI.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

@Service
public class TempService {

    /*@Autowired
    RestTemplate restTemplate;*/

    RestClient restClient ;

    public TempService(RestClient restClient){
        this.restClient = restClient;
    }

    public Response getResponse(String location){
        return restClient.get().uri(builder -> builder.path("/current.json").queryParam("key","1a6d595b4e0d47aab77112846262002").queryParam("q", location).build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve().body(Response.class);
    }
    /*@Autowired
    RestTemplate restTemplate;

    private String baseurl = "http://api.weatherapi.com/v1/current.json";
    private String apikeyname = "key";
    private String apiKey= "1a6d595b4e0d47aab77112846262002";

    public Response getResponse(String location) {
        String url = baseurl+"?"+apikeyname+"="+apiKey+"&q="+location;
        return restTemplate.getForObject(url,Response.class);
    }*/


}

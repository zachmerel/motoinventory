package com.example.motoinventoryservice.util.feign;

//import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@FeignClient(name = "VIN-LOOKUP")
public interface VinLookUpClient {

    @RequestMapping(value = "/vehicle/{vin}", method = RequestMethod.GET)
    public Map<String,String> getVehicleInformation(String vin);
}

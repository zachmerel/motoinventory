package com.example.motoinventoryservice.controller;

import com.example.motoinventoryservice.dao.MotoInventoryDao;
import com.example.motoinventoryservice.model.Motorcycle;
import com.example.motoinventoryservice.util.feign.VinLookUpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.Random;

@RestController
@RefreshScope
public class MotoInventoryController{

    @Autowired
    private final VinLookUpClient client;
    @Autowired
    private MotoInventoryDao motoInventoryDao;
    public MotoInventoryController(VinLookUpClient client) {
        this.client = client;
    }

    @RequestMapping(value = "/motorcycles", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Motorcycle createMotorcycle(@RequestBody @Valid Motorcycle motorcycle) {
        Random rnd = new Random();

        motorcycle.setId(rnd.nextInt(9999));

        return motoInventoryDao.addMotorcycle(motorcycle);
    }

    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Motorcycle getMotorcycle(@PathVariable int motoId) {
        if (motoId < 1) {
           throw new IllegalArgumentException("MotoId must be greater than 0.");
        }

//        Motorcycle moto = new Motorcycle();
//        moto.setId(motoId);
//        moto.setVin("54321");
//        moto.setMake("Ducati");
//        moto.setModel("Multistrada Enduro");
//        moto.setYear("2018");
//        moto.setColor("Red");
//
//        return moto;
        return motoInventoryDao.getMotorcycle(motoId);
    }

    @RequestMapping(value = "/motorcycles/{motoId}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMotorcycle(@PathVariable("motoId") int motoId) {
        // do nothing here - in a real application we would delete the entry from
        // the backing data store.
    }

    @PutMapping(value = "/motorcycles/{motoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateMotorcycle(@RequestBody @Valid Motorcycle motorcycle, @PathVariable int motoId) {
        // make sure the motoId on the path matches the id of the motorcycle object
        if (motoId != motorcycle.getId()) {
            throw new IllegalArgumentException("Motorcycle ID on path must match the ID in the Motorcycle object.");
        }

        // do nothing here - in a real application we would update the entry in the backing data store

    }

    @RequestMapping(value = "/vehicle/{vin}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,String> getVehicleInformation(@PathVariable String vin){
        return client.getVehicleInformation(vin);
    }
}

package com.neohack.backend.feignclient;

import com.neohack.backend.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//TODO возможно не понадобится, на всяк случай, чтобы был под рукой
@FeignClient(name = "smth-service-client", url = "${service.url.smth}")
public interface ConveyorClient {

    @PostMapping("/smth_url")
    List<?> getSmthFromSmth();
}

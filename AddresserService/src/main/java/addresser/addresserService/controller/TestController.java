package addresser.addresserService.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("/test1")
    public List<String> test1(){
        List<String> array = new ArrayList<String>();
        array.add("test1");
        array.add("test1");
        return array;
    }
}

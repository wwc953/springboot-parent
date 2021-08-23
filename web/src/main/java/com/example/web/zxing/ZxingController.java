package com.example.web.zxing;

import com.example.tools.zxing.ZxingUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ZxingController {

    @GetMapping(value = "/getImage/{no}")
    public String getImage(@PathVariable String no) throws Exception {
        return ZxingUtils.getImage(no);
    }

}

package com.example.demo.controller;

import com.example.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/testSplitTable", method = RequestMethod.GET)
    @ResponseBody
    public int testSplitTable() {
        return testService.testSplitTable();
    }

    @RequestMapping(value="/updateTest", method = RequestMethod.POST)
    @ResponseBody
    public int updateTest(String key, String value, Integer id) {
        return testService.updateTest(key, value, id);
    }

    @RequestMapping(value="/saveTest", method = RequestMethod.POST)
    @ResponseBody
    public int saveTest(String key, String value) {
        return testService.saveTest(key, value);
    }

    @RequestMapping(value="/delTest", method = RequestMethod.POST)
    @ResponseBody
    public int delTest(Integer id) {
        return testService.delTest(id);
    }

    @RequestMapping(value = "/getTest", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> getTest(Integer id) {
        return testService.getTest(id);
    }
}

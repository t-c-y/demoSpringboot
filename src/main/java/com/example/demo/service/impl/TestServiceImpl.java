package com.example.demo.service.impl;

import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import com.example.demo.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public int testSplitTable() {
        String uuid = null;
        int num = 0;
        for(int i=0; i<6; i++) {
            uuid = Utils.generateUUID();
            System.out.println(uuid);
            if(!StringUtils.isEmpty(uuid)) {
                num = Integer.parseInt(String.valueOf(uuid.charAt(0)), 16) % 3;
                testMapper.saveTableTest("test_"+num, uuid, "test");
            }
        }
        return 0;
    }

    @Override
    public int updateTest(String key, String value, Integer id) {
        return testMapper.updateTest(key, value, id);
    }

    @Override
    public int saveTest(String key, String value) {
        return testMapper.saveTest(key, value);
    }

    @Override
    public Map<String, Object> getTest(int id) {
        return testMapper.getTest(id);
    }

    @Override
    public int delTest(int id) {
        return testMapper.delTest(id);
    }
}

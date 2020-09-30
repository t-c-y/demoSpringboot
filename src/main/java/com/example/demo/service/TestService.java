package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface TestService {

    int testSplitTable();

    int saveTest(String key, String vlaue);

    int updateTest(String key, String vlaue, Integer id);

    Map<String, Object> getTest(int id);

    int delTest(int id);
}

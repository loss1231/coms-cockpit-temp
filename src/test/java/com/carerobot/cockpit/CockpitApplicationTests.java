package com.carerobot.cockpit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.carerobot.cockpit.common.annotation.DataSource;
import com.carerobot.cockpit.common.enums.DataSourceEnum;
import com.carerobot.cockpit.common.util.DateUtils;
import com.carerobot.cockpit.model.AccountRecord;
import com.carerobot.cockpit.service.AccidentReportService;
import com.carerobot.cockpit.service.AccountRecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

@SpringBootTest
class CockpitApplicationTests {

    @Autowired
    private AccountRecordService accidentReportService;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {

    }

}

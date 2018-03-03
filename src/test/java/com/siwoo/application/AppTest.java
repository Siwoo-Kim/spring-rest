package com.siwoo.application;

import com.siwoo.application.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class AppTest {

    @Autowired
    ApplicationContext context;

    @Test
    public void testContext(){
        assertNotNull(context);
    }
}

package org.osiam.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.osiam.example.hystrix.HystrixApplication;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HystrixApplication.class)
@WebAppConfiguration
public class HystrixApplicationTests {

	@Test
	public void contextLoads() {
	}

}

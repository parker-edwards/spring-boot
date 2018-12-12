/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sample.tomcat;

import java.net.MalformedURLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.lightstep.tracer.jre.JRETracer;
import com.lightstep.tracer.shared.Options;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.opentracing.Tracer;

@SpringBootApplication
public class SampleTomcatApplication {

	private static Log logger = LogFactory.getLog(SampleTomcatApplication.class);

	@Bean
	protected ServletContextListener listener() {
		return new ServletContextListener() {

			@Override
			public void contextInitialized(ServletContextEvent sce) {
				logger.info("ServletContext initialized");
			}

			@Override
			public void contextDestroyed(ServletContextEvent sce) {
				logger.info("ServletContext destroyed");
			}

		};
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleTomcatApplication.class, args);
	}

	@Bean
	public io.opentracing.Tracer tracer() throws InterruptedException, MalformedURLException {
		Options options = new Options.OptionsBuilder()
				.withAccessToken("insert-access-token")
				.withComponentName("LightStep Parker Test")
				.withVerbosity(4)
				.build();
		return new JRETracer(options);
	}
}

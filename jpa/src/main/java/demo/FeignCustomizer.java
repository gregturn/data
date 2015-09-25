/*
 * Copyright 2015 the original author or authors.
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
package demo;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Greg Turnquist
 */
@Configuration
public class FeignCustomizer implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

	@Override
	public void onApplicationEvent(EmbeddedServletContainerInitializedEvent embeddedServletContainerInitializedEvent) {
		int port = embeddedServletContainerInitializedEvent.getEmbeddedServletContainer().getPort();
		XForwardingHostInterceptor interceptor =
				embeddedServletContainerInitializedEvent.getApplicationContext().getBean(XForwardingHostInterceptor.class);
		interceptor.setPort(port);
	}

	@Bean
	public RequestInterceptor requestInterceptor() {
		return new XForwardingHostInterceptor();
	}

	static class XForwardingHostInterceptor implements RequestInterceptor {

		@Value("${eureka.instance.hostname}")
		private String host;

		private int port;

		@Override
		public void apply(RequestTemplate template) {
			template.header("x-forwarded-host", host + ":" + this.port);
		}

		public int getPort() {
			return port;
		}

		public void setPort(int port) {
			this.port = port;
		}
	}

}

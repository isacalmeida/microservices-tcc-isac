package br.edu.unoesc.sistemautils.arquitetura.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

import brave.sampler.Sampler;

@Configuration
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableHypermediaSupport(type = { HypermediaType.HAL })
public abstract class ApplicationStarter {

	@Bean
	public Sampler getSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}

	@Bean
	public AnnotationConfigApplicationContext getAnnotationConfigApplicationContext() {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
		applicationContext.scan(getClass().getPackage().getName());
		applicationContext.refresh();
		return applicationContext;
	}
}

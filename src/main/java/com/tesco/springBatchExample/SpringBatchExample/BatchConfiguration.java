package com.tesco.springBatchExample.SpringBatchExample;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.tesco.springBatchExample.items.CustomItemProcessor;
import com.tesco.springBatchExample.items.CustomItemReader;
import com.tesco.springBatchExample.items.CustomItemWriter;
import com.tesco.springBatchExample.model.User;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public ResourceLoader resourceLoader;

	@Bean
	public StaxEventItemReader<User> staxReader() {
		StaxEventItemReader<User> staxEventItemReader = new StaxEventItemReader<>();
		Resource resource = resourceLoader.getResource("user.xml");
		staxEventItemReader.setResource(resource);
		staxEventItemReader.setFragmentRootElementName("user");

		Map<String, String> aliases = new HashMap<>();
		aliases.put("user", "com.tesco.springBatchExample.model.User");

		XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
		xStreamMarshaller.setAliases(aliases);

		staxEventItemReader.setUnmarshaller(xStreamMarshaller);

		return staxEventItemReader;
	}
	
	@Bean
	public CustomItemReader customReader() {
		return new CustomItemReader();
	}

	@Bean
	public CustomItemProcessor processor() {
		return new CustomItemProcessor();
	}

	@Bean
	public CustomItemWriter writer() {
		return new CustomItemWriter();

	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<User, User>chunk(10)
				.reader(customReader()).processor(processor()).writer(writer())
				.build();
	}

	@Bean
	public Job importUserJob(Step step1) {
		return jobBuilderFactory.get("importUserJob").incrementer(new RunIdIncrementer()).flow(step1).end().build();
	}
}
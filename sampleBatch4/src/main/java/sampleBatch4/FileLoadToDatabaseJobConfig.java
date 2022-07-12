package sampleBatch4;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class FileLoadToDatabaseJobConfig {
	@Autowired
	JobBuilderFactory jobFactory;
	@Autowired
	StepBuilderFactory stepFactory;
	
	@Bean
	public Job fileLoadToDatabaseJob() {
		return jobFactory.get("fileLoadToDatabaseJob")
				.incrementer(new RunIdIncrementer())
				.start(fileLoadToDatabaseStep())
				.build();
	}
	
	@Bean
	public Step fileLoadToDatabaseStep() {
		return stepFactory.get("fileLoadToDatabaseStep")
				.<Book,Book>chunk(5)
				.reader(csvFileItemReader())
				.processor(loggingItemProcessor())
				.writer(bookJpaItemWriter())
				.build();
	}
	@StepScope
	@Bean
	public CsvFileItemReader csvFileItemReader() {
		Resource fileResource=new FileSystemResource("src/main/resources/book.csv");
		CsvFileItemReader itemReader=new CsvFileItemReader();
		itemReader.setResource(fileResource);
		itemReader.setName("csvFileItemReader");
		itemReader.setSaveState(false);
		return itemReader;
	}
	@StepScope
	@Bean
	public LoggingItemProcessor loggingItemProcessor() {
		return new LoggingItemProcessor();
	}
	@StepScope
	@Bean
	public BookJpaItemWriter bookJpaItemWriter() {
		return new BookJpaItemWriter();
	}
}

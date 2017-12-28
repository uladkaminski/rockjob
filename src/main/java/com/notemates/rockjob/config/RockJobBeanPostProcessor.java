package com.notemates.rockjob.config;

import com.notemates.rockjob.annotation.RockJob;
import com.notemates.rockjob.job.RockJobRunnable;
import com.notemates.rockjob.resolver.RockJobManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class RockJobBeanPostProcessor implements BeanPostProcessor {
    private Map<String, RockJobRunnable> jobs = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Annotation annotation : bean.getClass().getAnnotations()) {
            if (annotation.annotationType().equals(RockJob.class) && bean instanceof RockJobRunnable){
                RockJob oyperJobAnnotation = (RockJob) annotation;
                String jobTitle = oyperJobAnnotation.jobTitle();
                jobs.put(jobTitle, (RockJobRunnable) bean);
            }
        }
        return bean;
    }

    @Bean
    public RockJobManager rockJobManager(){
        return new RockJobManager(jobs);
    }
}
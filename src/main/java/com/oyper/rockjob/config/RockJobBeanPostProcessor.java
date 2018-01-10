package com.oyper.rockjob.config;

import com.oyper.rockjob.annotation.RockJob;
import com.oyper.rockjob.exception.RockJobException;
import com.oyper.rockjob.job.RockJobRunnable;
import com.oyper.rockjob.resolver.RockJobManager;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

@Configuration
public class RockJobBeanPostProcessor implements BeanPostProcessor {
    private Map<String, Entry<Boolean, RockJobRunnable>> jobs = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        for (Annotation annotation : bean.getClass().getAnnotations()) {
            if (annotation.annotationType().equals(RockJob.class) ) {
                RockJob oyperJobAnnotation = (RockJob) annotation;
                String jobTitle = oyperJobAnnotation.jobTitle();
                boolean async = oyperJobAnnotation.async();
                if (!(bean instanceof RockJobRunnable)){
                    throw new RockJobException("RockJob " + jobTitle + " does not implement RockJobRunnable interface");
                }
                if (jobs.containsKey(jobTitle)){
                    throw new RockJobException("RockJob " + jobTitle + " has been duplicated");
                }
                jobs.put(jobTitle, new AbstractMap.SimpleEntry<>(async, (RockJobRunnable) bean));
            }
        }
        return bean;
    }

    @Bean
    public RockJobManager rockJobManager() {
        return new RockJobManager(jobs);
    }
}
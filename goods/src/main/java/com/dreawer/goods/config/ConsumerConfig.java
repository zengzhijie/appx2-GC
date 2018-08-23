package com.dreawer.goods.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.aliyun.openservices.ons.api.Consumer;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ConsumerConfig {

	@Value("${ons.consumerId}")
	private String consumerId;

	@Value("${ons.accessKey}")
	private String accessKey;

	@Value("${ons.secretKey}")
	private String secretKey;

	@Value("${ons.ONSAddr}")
	private String ONSAddr;

	@Value("${ons.topic.default}")
	private String TOPIC_DEFAULT;
	
	@Value("${ons.topic.delay}")
	private String TOPIC_DELAY;

	@Autowired
	private DefaultMessageListener defaultMessageListener;

	/**
	 * 创建阿里云消息消费者
	 * @return
	 */
	@Bean(initMethod = "start", destroyMethod = "shutdown")
	public Consumer consumer() {
		Properties properties = new Properties();
		// 您在 MQ 控制台创建的 Consumer ID
		properties.put(PropertyKeyConst.ConsumerId, consumerId);
		// 鉴权用 AccessKey，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.AccessKey, accessKey);
		// 鉴权用 SecretKey，在阿里云服务器管理控制台创建
		properties.put(PropertyKeyConst.SecretKey, secretKey);
		// 设置 TCP 接入域名（此处以公共云公网环境接入为例）
		properties.put(PropertyKeyConst.ONSAddr, ONSAddr);
		properties.put(PropertyKeyConst.ConsumeThreadNums, 5);
		// properties.put(PropertyKeyConst.ConsumeTimeout, "");
		// properties.put(PropertyKeyConst.OnsChannel, "");
		Consumer consumer = ONSFactory.createConsumer(properties);
		consumer.subscribe(TOPIC_DEFAULT, "lockInventory || releaseInventory || deductionInventory", defaultMessageListener);
		return consumer;
	}
	
}

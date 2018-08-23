package com.dreawer.goods.config;

import java.util.Properties;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.ONSFactory;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.PropertyKeyConst;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.exception.ONSClientException;

@Component
public class ProduceFactory {

	@Value("${ons.producerId}")
	private String producerId;

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
	
	private Producer producer;

    private Logger logger = Logger.getLogger(this.getClass()); // 日志记录器

	@PostConstruct
	public void init(){
		// producer 实例配置初始化
		Properties properties = new Properties();
		//您在控制台创建的Producer ID
		properties.setProperty(PropertyKeyConst.ProducerId, producerId);
		// AccessKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.setProperty(PropertyKeyConst.AccessKey, accessKey);
		// SecretKey 阿里云身份验证，在阿里云服务器管理控制台创建
		properties.setProperty(PropertyKeyConst.SecretKey, secretKey);
		//设置发送超时时间，单位毫秒
		properties.setProperty(PropertyKeyConst.SendMsgTimeoutMillis, "3000");
		// 设置 TCP 接入域名（此处以公共云生产环境为例）
		properties.setProperty(PropertyKeyConst.ONSAddr, ONSAddr);
		producer = ONSFactory.createProducer(properties);
		// 在发送消息前，必须调用start方法来启动Producer，只需调用一次即可
		producer.start();
	}
	
	public ProduceResult sendMessage(String json, String tags) {
		if (StringUtils.isBlank(json)) {
			return new ProduceResult(false, "参数不能为空");
		} else {
			String key = UUID.randomUUID().toString().replace("-", "");
			Message msg = new Message(TOPIC_DEFAULT, tags, json.getBytes());
			msg.setKey(key);
			return send(msg);
		}
	}

	public ProduceResult sendDelayMessage(String json, String tags, long time) {
		Message msg = new Message(TOPIC_DELAY, tags, json.getBytes());
		String key = UUID.randomUUID().toString().replace("-", "");
		msg.setKey(key);
		long delayTime = System.currentTimeMillis() + time;
		msg.setStartDeliverTime(delayTime);
		return send(msg);
	}

	public ProduceResult sendTimedMessage(String json, String tags, long time) {
		Message msg = new Message(TOPIC_DELAY, tags, json.getBytes());
		String key = UUID.randomUUID().toString().replace("-", "");
		msg.setKey(key);
		msg.setStartDeliverTime(time);
		return send(msg);
	}

	private ProduceResult send(Message msg) {
		try {
			SendResult e = producer.send(msg);
			return e != null ? new ProduceResult(true, e.getMessageId()) : new ProduceResult(false, "发送失败");
		} catch (ONSClientException e) {
			logger.error(e);
			return new ProduceResult(false, "发送失败");
		}
	}	
	
	
}
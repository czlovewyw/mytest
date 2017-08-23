package org.cz.online;

import org.cz.online.entity.Teamer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

@SpringBootApplication
@EnableCaching
public class OnlineApplication implements ApplicationListener<ContextRefreshedEvent> {
	public static String[] teamers = {"陈哲","王菲菲","李远庆","候俊辉","陆文虎","周天一","张晓林","邓明雄"};
	@Autowired
	private CacheManager cacheManager;
	public static void main(String[] args) {
		SpringApplication.run(OnlineApplication.class, args);
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
		for (String name: teamers){
			Teamer teamer = new Teamer();
			teamer.setName(name);
			teamer.setDoing(0);
			teamer.setDone(0);
			teamer.setStatus(true);
			cacheManager.getCache("team").put(name,teamer);
		}
	}
}

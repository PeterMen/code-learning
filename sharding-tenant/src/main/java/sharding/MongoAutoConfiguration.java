/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sharding;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PreDestroy;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link EnableAutoConfiguration Auto-configuration} for Mongo.
 *
 * @author Dave Syer
 * @author Oliver Gierke
 * @author Phillip Webb
 * @author Mark Paluch
 * @author Stephane Nicoll
 */
@Configuration
@ConditionalOnClass(MongoClient.class)
@EnableConfigurationProperties(MongoTenantProperties.class)
@ConditionalOnMissingBean(type = "org.springframework.data.mongodb.MongoDbFactory")
public class MongoAutoConfiguration {

	private final MongoClientOptions options;

	private final Environment environment;

	private Map<String, MongoClient> mongos;

	private MongoTenantProperties tenants;

	public MongoAutoConfiguration(MongoTenantProperties tenants, ObjectProvider<MongoClientOptions> options,
                                  Environment environment) {
		this.options = options.getIfAvailable();
		this.tenants = tenants;
		this.environment = environment;

		if(tenants.hasTenant()){
			mongos = new HashMap<>();
			for(String tenant :  tenants.getTenants().split(",")){
				MongoClientFactory factory = new MongoClientFactory(new MongoProperties(environment, tenant), environment);
				mongos.put(tenant, factory.createMongoClient(this.options));
			}
		}
	}

	@PreDestroy
	public void close() {
		mongos.forEach((tenant, mongo) -> mongo.close());
		if (this.mongos != null) {
			mongos.forEach((tenant, mongo) -> mongo.close());
		}
	}

	@Bean
	@ConditionalOnMissingBean(type = { "com.mongodb.MongoClient", "com.mongodb.client.MongoClient" })
	public MongoClient mongo() {

		return this.mongos.get(ThreadLocalTenant.getTenant());
	}

}

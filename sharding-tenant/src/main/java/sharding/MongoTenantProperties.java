/*
 * Copyright 2012-2018 the original author or authors.
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

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Configuration properties for Mongo.
 *
 * @author Dave Syer
 * @author Phillip Webb
 * @author Josh Long
 * @author Andy Wilkinson
 * @author Eddú Meléndez
 * @author Stephane Nicoll
 * @author Nasko Vasilev
 * @author Mark Paluch
 */
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class MongoTenantProperties {

	public  String tenants;

	public  String getTenants() {
		return tenants;
	}

	public  void setTenants(String tenants) {
		this.tenants = tenants;
	}

	public boolean hasTenant(){
		return this.tenants != null && this.tenants.length() != 0;
	}
}

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

import com.mongodb.MongoClientURI;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;

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
public class MongoProperties {

	private static String PREFIX = "spring.data.mongodb";

	private Environment env;

	private String tenant;

	public MongoProperties(Environment env, String tenant){
		this.env = env;
		this.tenant = tenant;
		if(tenant != null && tenant.length() != 0){
			PREFIX = tenant + '.' + PREFIX;
		}
	}

	/**
	 * Default port used when the configured port is {@code null}.
	 */
	public static final int DEFAULT_PORT = 27017;

	/**
	 * Default URI used when the configured URI is {@code null}.
	 */
	public static final String DEFAULT_URI = "mongodb://localhost/test";

	public String getHost() {
		return env.getProperty(PREFIX+".host");
	}

	public String getDatabase() {

		return env.getProperty(PREFIX+".database");
	}

	public String getAuthenticationDatabase() {
		return env.getProperty(PREFIX+".authenticationDatabase");
	}

	public String getUsername() {

		return env.getProperty(PREFIX+".username");
	}


	public char[] getPassword() {
		return env.getProperty(PREFIX+".password").toCharArray();
	}

//	public Class<?> getFieldNamingStrategy() {
//		return env.getProperty(PREFIX+".fieldNamingStrategy");
//	}


	public String getUri() {
		return env.getProperty(PREFIX+".uri");
	}

	public String determineUri() {
		String uri = env.getProperty(PREFIX+".uri");
		return (uri != null) ? uri : DEFAULT_URI;
	}


	public Integer getPort() {
		return Integer.valueOf(env.getProperty(PREFIX+".port"));
	}

	public String getGridFsDatabase() {
		return env.getProperty(PREFIX+".gridFsDatabase");
	}


	public String getMongoClientDatabase() {
		if (env.getProperty(PREFIX+".database") != null) {
			return env.getProperty(PREFIX+".database");
		}
		return new MongoClientURI(determineUri()).getDatabase();
	}

}

package sharding;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Import(MongoAutoConfiguration.class)
public @interface EnableMongoShard {
    String value();
}

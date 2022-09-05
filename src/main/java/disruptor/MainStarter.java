package disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.sun.java.accessibility.util.Translator;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public class MainStarter {

    public static void main(String[] args) throws InterruptedException {
        Disruptor d = new Disruptor( ()->new NameEvent(), 2, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread("sdfsdf");
            }
        }, ProducerType.MULTI, new BlockingWaitStrategy()
        );
        d.handleEventsWith(new Consumer());
        d.start();

        NameEvent o = new NameEvent() ;
        o.setName("sdfsdfsdfsdfsdfdsfsdfffffff");
//        d.publishEvent( o, 1l
//        );

        Thread.sleep(1200);
    }

}

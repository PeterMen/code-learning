package disruptor;

import com.lmax.disruptor.EventHandler;

public class Consumer implements EventHandler<NameEvent> {
    @Override
    public void onEvent(NameEvent s, long l, boolean b) throws Exception {
        System.out.println("s:"+s.getName()+"l:"+l+"b:"+b);
    }
}

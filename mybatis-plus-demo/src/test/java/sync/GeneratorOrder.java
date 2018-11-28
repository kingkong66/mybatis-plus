package sync;

import com.example.demo.common.config.DistrIdGenerator;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: Tai
 * @Date: 2018/11/28 14:39
 * @Description: CountDownLatch 并发测试
 */
@Slf4j
public class GeneratorOrder {

    public static String getOrderNo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String formDate = sdf.format(new Date());
        return formDate;
    }

    public static void main(String[] args) throws InterruptedException {
        int threadCount = 50;
        ExecutorService es = Executors.newFixedThreadPool(threadCount);
        CountDownLatch cdl = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前线程:" + Thread.currentThread().getName() + "准备就绪, 等待countdown为0后开始运行....");
                    try {
                        cdl.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("模拟请求获取订单号：" + DistrIdGenerator.createRandomId());
                }
            });
        }

        //等待两秒，等待并发线程初始化完成。
        Thread.sleep(2000);
        for (int j = 0; j < threadCount; j++) {
            cdl.countDown();
        }
        es.shutdown();
    }
}

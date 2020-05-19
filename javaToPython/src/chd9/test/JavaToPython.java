package chd9.test;
import chd9.client.MyTest;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @Author: donnie99
 * @Date: 2020/5/19 23:12
 * @Version 1.0
 */
public class JavaToPython {
    private static final Logger LOGGER = LoggerFactory.getLogger(JavaToPython.class);
    private final static String SERVER_IP="127.0.0.1";
    private final static Integer SERVER_PORT=3333;
    private final static Integer TIMEOUT=60000;
    public static void main(String[] args) {
        TTransport transport;
        TProtocol protocol;
        MyTest.Client client;

        transport = new TSocket(SERVER_IP,SERVER_PORT,TIMEOUT);
        // 协议要和服务端一致
        protocol = new TBinaryProtocol(transport);
        client = new MyTest.Client(protocol);
        String calculateId = "10000106";
        try {
            System.out.println("[Thrify ], start  => ");
            transport.open();
            LOGGER.info("java端远程调用Python>>>>>>>开始");
            //远程调用Python的第一个方法
            LOGGER.info("java端远程调用Python>>>>>第一个方法>>>>>>>开始");
            client.helloThrift();
            LOGGER.info("java端远程调用Python>>>>>第一个方法>>>>>>>结束");
            //远程调用Python的第二个方法
            LOGGER.info("java端远程调用Python>>>>>第二个方法>>>>>>>开始");
            String result = client.studyThrift();
            LOGGER.info(result);
            LOGGER.info("java端远程调用Python>>>>>第二个方法>>>>>>>结束");
            //远程调用Python的第三个方法
            LOGGER.info("java端远程调用Python>>>>>第三个方法>>>>>>>开始");
            String result1 = client.sayMsg("我学会java远程调用python了");
            LOGGER.info(result1);
            LOGGER.info("java端远程调用Python>>>>>第三个方法>>>>>>>结束");
        } catch (TException e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
                LOGGER.info("java端远程调用Python>>>>>>>结束");
            }
        }
    }





}

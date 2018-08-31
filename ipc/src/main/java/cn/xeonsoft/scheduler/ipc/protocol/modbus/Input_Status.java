package cn.xeonsoft.scheduler.ipc.protocol.modbus;

import java.util.Arrays;

import org.apache.commons.logging.Log;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsRequest;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsResponse;
import com.serotonin.util.queue.ByteQueue;

import cn.xeonsoft.scheduler.ipc.ByteHex;

/**
 * Created by DoBest on 2016/3/28.
 */
public class Input_Status {

    private static int countRead = 0;
    public static void inputStatusRead(String ip, int port,int slaveId, int start,int len,
                                       ResultListener resultListener){
        IpParameters params = new IpParameters();
        params.setHost(ip);//地址
        params.setPort(port);//端口
        ModbusFactory factory = new ModbusFactory();
        ModbusMaster master = factory.createTcpMaster(params, true);

        // 初始化
        try {
            master.init();
            readInputStatus(master, ip, port, slaveId, start, len, resultListener);
        } catch (ModbusInitException e) {
            e.printStackTrace();
            if (countRead<3){
                countRead++;
                inputStatusRead(ip, port, slaveId, start, len, resultListener);
            }else {
                System.out.println("请检查设置后重新连接");
                resultListener.onToast("请检查设置后重新连接");
            }
        } finally {
            master.destroy();
        }
    }

    private static void readInputStatus(ModbusMaster master,String ip, int port,int slaveId, int start, int len,
                                        ResultListener resultListener) {
        try {
            ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);
            ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);
            if (response.isException()) {
                System.out.println("Exception response: message=" + response.getExceptionMessage());
                resultListener.onToast("Exception response: message=" + response.getExceptionMessage());
            } else {
                ByteQueue byteQueue= new ByteQueue(12);
                response.write(byteQueue);
                resultListener.result(ByteHex.allData(byteQueue,len),ip, port, slaveId, start, 2);
                System.out.println(byteQueue + " Size: "+byteQueue.size());
                System.out.println(Arrays.toString(response.getShortData()));
                short[] list = response.getShortData();
                for(int i = 0; i < list.length; i++){
                    System.out.println(list[i]+"");
                }
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            if (countRead<3){
                countRead++;
                readInputStatus(master, ip, port, slaveId, start, len, resultListener);
            }else {
                System.out.println("请检查设置后重新连接");
                resultListener.onToast("请检查设置后重新连接");
            }
        }finally {
            master.destroy();
        }
    }
}

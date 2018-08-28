package cn.xeonsoft.scheduler.ipc.protocol.modbus;

import org.apache.commons.logging.Log;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.ReadCoilsRequest;
import com.serotonin.modbus4j.msg.ReadCoilsResponse;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;
import com.serotonin.modbus4j.msg.WriteCoilsRequest;
import com.serotonin.modbus4j.msg.WriteCoilsResponse;
import com.serotonin.util.queue.ByteQueue;

import cn.xeonsoft.scheduler.ipc.ByteHex;

/**
 * Created by DoBest on 2016/3/28.
 */
public class Coil_Status {

    private static int countRead = 0;
    private static int countWrite = 0;
    public static void coilStatusRead(String ip, int port,int slaveId, int start,int len,ResultListener resultListener){
        IpParameters params = new IpParameters();
        params.setHost(ip);//地址
        params.setPort(port);//端口
        ModbusFactory factory = new ModbusFactory();
        ModbusMaster master = factory.createTcpMaster(params, true);

        // 初始化
        try {
            master.init();
            readCoilStatus(master, ip, port, slaveId, start, len, resultListener);
        } catch (ModbusInitException e) {
            e.printStackTrace();
            if (countRead<3){
                countRead++;
                coilStatusRead(ip, port, slaveId, start, len, resultListener);
            }else {
                resultListener.onToast("请检查设置后重新连接");
            }
        } finally {
            master.destroy();
        }
    }

    private static void readCoilStatus(ModbusMaster master, String ip, int port, int slaveId, int start, int len, ResultListener resultListener) {
        try {
            ReadCoilsRequest request = new ReadCoilsRequest(slaveId, start, len);
            ReadCoilsResponse response = (ReadCoilsResponse) master.send(request);
            if (response.isException()) {
                System.out.println( "Exception response: message=" + response.getExceptionMessage());
                resultListener.onToast("Exception response: message=" + response.getExceptionMessage());
            } else {
                ByteQueue byteQueue= new ByteQueue(12);
                response.write(byteQueue);
                resultListener.result(ByteHex.allData(byteQueue,len), ip, port, slaveId, start, 1);
                System.out.println(byteQueue + " Size: " + byteQueue.size());
                System.out.println(ByteHex.allData(byteQueue, len).toString());
                System.out.println(Integer.toBinaryString((int) byteQueue.peek(3) & 0xff).length()+"");
                short[] list = response.getShortData();
                for(int i = 0; i < list.length; i++){
                    System.out.println( list[i]+"");
                }
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            if (countRead<3){
                countRead++;
                readCoilStatus(master, ip, port, slaveId, start, len,resultListener);
            }else {
                resultListener.onToast("请检查设置后重新连接");
            }
        }
    }

    public static void coilStatusWrite(String ip, int port, int slaveId, int start, boolean[] values){
        ModbusFactory modbusFactory = new ModbusFactory();
        // 设备ModbusTCP的Ip与端口，如果不设定端口则默认为502
        IpParameters params = new IpParameters();
        params.setHost(ip);
        params.setPort(port);
        // 参数1：IP和端口信息 参数2：保持连接激活
        ModbusMaster tcpMaster = modbusFactory.createTcpMaster(params, true);
        try {
            tcpMaster.init();
            writeCoilsStatus(tcpMaster,slaveId,start,values);
        } catch (ModbusInitException e) {
            e.printStackTrace();
            if (countWrite<3){
                countWrite++;
                coilStatusWrite(ip, port, slaveId, start, values);
            }else {
                System.out.println("此处出现问题了啊");
            }
        } finally {
            tcpMaster.destroy();
        }
    }

    private static void writeCoilsStatus(ModbusMaster master, int slaveId, int start, boolean[] values){
        try {
            WriteCoilsRequest request = new WriteCoilsRequest(slaveId, start, values);
            WriteCoilsResponse response = (WriteCoilsResponse) master.send(request);
            if (response.isException()){
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            }
            else {
                System.out.println("Success");
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            if (countWrite<3){
                countWrite++;
                writeCoilsStatus(master, slaveId, start, values);
            }else {
                System.out.println("请检查设置后重新写入");
            }
        }
        finally {
            master.destroy();
        }
    }

    public static void coilStatusWrite(String ip, int port, int slaveId, int start, boolean value, ResultListener resultListener){
        ModbusFactory modbusFactory = new ModbusFactory();
        // 设备ModbusTCP的Ip与端口，如果不设定端口则默认为502
        IpParameters params = new IpParameters();
        params.setHost(ip);
        params.setPort(port);
        // 参数1：IP和端口信息 参数2：保持连接激活
        ModbusMaster tcpMaster = modbusFactory.createTcpMaster(params, true);
        try {
            tcpMaster.init();
            writeCoilStatus(tcpMaster, slaveId, start, value, resultListener);
            System.out.println("===============");
        } catch (ModbusInitException e) {
            e.printStackTrace();
            if (countWrite<3){
                countWrite++;
                coilStatusWrite(ip, port, slaveId, start, value, resultListener);
            }else {
                System.out.println("此处出现问题了啊");
                resultListener.onToast("请检查设置后重新发送");
            }
        }
        finally {
            tcpMaster.destroy();
        }
    }

    private static void writeCoilStatus(ModbusMaster master, int slaveId, int start, boolean value, ResultListener resultListener){
        try {
            WriteCoilRequest request = new WriteCoilRequest(slaveId, start, value);
            WriteCoilResponse response = (WriteCoilResponse) master.send(request);
            if (response.isException()){
                System.out.println("Exception response: message=" + response.getExceptionMessage());
                resultListener.onToast("Exception response: message=" + response.getExceptionMessage());
            }
            else {
                System.out.println("Success");
                resultListener.onToast("Success");
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            if (countWrite<3){
                countWrite++;
                writeCoilStatus(master, slaveId, start, value, resultListener);
            }else {
                System.out.println("请检查设置后重新连接");
                resultListener.onToast("请检查设置后重新发送");
            }
        }
        finally {
            master.destroy();
        }
    }

}

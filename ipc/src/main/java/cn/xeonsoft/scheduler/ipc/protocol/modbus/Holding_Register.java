package cn.xeonsoft.scheduler.ipc.protocol.modbus;

import java.util.Arrays;

import org.apache.commons.logging.Log;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.msg.WriteRegisterRequest;
import com.serotonin.modbus4j.msg.WriteRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;
import com.serotonin.util.queue.ByteQueue;

import cn.xeonsoft.scheduler.ipc.ByteHex;

/**
 * Created by DoBest on 2016/3/28.
 */
public class Holding_Register {

    private static int countRead = 0;
    private static int countWrite = 0;
    public static void holdingRegisterRead(String ip, int port,int slaveId, int start,int len,
                                           ResultListener resultListener){
        IpParameters params = new IpParameters();
        params.setHost(ip);//地址
        params.setPort(port);//端口
        ModbusFactory factory = new ModbusFactory();
        ModbusMaster master = factory.createTcpMaster(params, true);

        // 初始化
        try {
            master.init();
            readHoldingRegisters(master, ip,port,slaveId, start, len, resultListener);
        } catch (ModbusInitException e) {
            e.printStackTrace();
            if (countRead<3){
                countRead++;
                holdingRegisterRead(ip,port,slaveId,start,len, resultListener);
            }else {
                System.out.println("请检查设置后重新连接");
                resultListener.onToast("请检查设置后重新连接");
            }
        } finally {
            master.destroy();
        }
    }

    private static void readHoldingRegisters(ModbusMaster master,String ip, int port, int slaveId, int start, int len,
                                             ResultListener resultListener) {
        try {
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(
                    slaveId, start, len);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master
                    .send(request);
            if (response.isException()) {
                System.out.println("Exception response: message=" + response.getExceptionMessage());
                resultListener.onToast("Exception response: message=" + response.getExceptionMessage());
            } else {
                ByteQueue byteQueue= new ByteQueue(12);
                response.write(byteQueue);
                resultListener.result(ByteHex.allDataR(byteQueue), ip, port, slaveId, start, 3);

                System.out.println(byteQueue + " Size: " + byteQueue.size());
                System.out.println(Arrays.toString(response.getData()));
                System.out.println(ByteHex.allDataR(byteQueue).toString());
                System.out.println("Count: "+countRead);
                short[] list = response.getShortData();
                for(int i = 0; i < list.length; i++){
                    System.out.println(list[i]+"");
                }
            }
        } catch (ModbusTransportException e) {
            e.printStackTrace();
            if (countRead<3){
                countRead++;
                readHoldingRegisters(master, ip, port, slaveId, start, len, resultListener);
            } else {
                System.out.println("请检查设置后重新连接");
                resultListener.onToast("请检查设置后重新连接");
            }
        }
    }

    public static void holdingRegisterWrite(String ip, int port, int slaveId, int start, short[] values){
        ModbusFactory modbusFactory = new ModbusFactory();
        // 设备ModbusTCP的Ip与端口，如果不设定端口则默认为502
        IpParameters params = new IpParameters();
        params.setHost(ip);
        params.setPort(port);
        // 参数1：IP和端口信息 参数2：保持连接激活
        ModbusMaster tcpMaster = modbusFactory.createTcpMaster(params, true);
        try {
            tcpMaster.init();
            writeHoldingRegisters(tcpMaster,slaveId,start,values);
            System.out.println("===============");
            
        } catch (ModbusInitException e) {
            e.printStackTrace();
            if (countWrite<3){
                countWrite++;
                holdingRegisterWrite(ip, port, slaveId, start, values);
            }else {
                System.out.println("此处出现问题了啊");
            }
        }finally {
            tcpMaster.destroy();
        }
    }

    private static void writeHoldingRegisters(ModbusMaster master,int slaveId, int start, short[] values){
        try {
            WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);
            WriteRegistersResponse response = (WriteRegistersResponse) master.send(request);
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
                writeHoldingRegisters(master, slaveId, start, values);
            }else {
                System.out.println("请检查设置后重新连接");
            }
        }
        finally {
            master.destroy();
        }
    }

    public static void holdingRegisterWrite(String ip, int port, int slaveId, int start, int value, ResultListener resultListener){
        ModbusFactory modbusFactory = new ModbusFactory();
        // 设备ModbusTCP的Ip与端口，如果不设定端口则默认为502
        IpParameters params = new IpParameters();
        params.setHost(ip);
        params.setPort(port);
        // 参数1：IP和端口信息 参数2：保持连接激活
        ModbusMaster tcpMaster = modbusFactory.createTcpMaster(params, true);
        try {
            tcpMaster.init();
            writeHoldingRegister(tcpMaster,slaveId,start,value,resultListener);
            System.out.println("===============");
        } catch (ModbusInitException e) {
            e.printStackTrace();
            if (countWrite<3){
                countWrite++;
                holdingRegisterWrite(ip, port, slaveId,start, value,resultListener);
            }else {
                System.out.println("此处出现问题了啊");
                resultListener.onToast("请检查设置后重新发送");
            }
        } finally {
            tcpMaster.destroy();
        }
    }

    private static void writeHoldingRegister(ModbusMaster master,int slaveId, int start, int value, ResultListener resultListener){
        try {
            WriteRegisterRequest request = new WriteRegisterRequest(slaveId, start, value);
            WriteRegisterResponse response = (WriteRegisterResponse) master.send(request);
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
                writeHoldingRegister(master,slaveId, start, value, resultListener);
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

package cn.xeonsoft.scheduler.ipc;

import java.util.ArrayList;

import cn.xeonsoft.scheduler.ipc.protocol.modbus.Coil_Status;
import cn.xeonsoft.scheduler.ipc.protocol.modbus.ResultListener;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        

        Coil_Status.coilStatusRead("120.79.72.60",22,01, 40001, 32, new ResultListener() {

			@Override
			public ArrayList<String> result(ArrayList<String> dataList, String ip, int port, int slaveId, int start,
					int type) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void onToast(String msg) {
				// TODO Auto-generated method stub
				
			}
        	
        });
    }
}

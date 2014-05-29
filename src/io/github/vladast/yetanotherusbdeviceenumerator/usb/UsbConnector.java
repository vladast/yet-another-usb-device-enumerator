/**
 * 
 */
package io.github.vladast.yetanotherusbdeviceenumerator.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Handler;
import android.os.Message;

/**
 * @author vladimir.stankovic
 *
 */
public class UsbConnector {
	
	public static final String TAG = UsbConnector.class.getSimpleName();
	
	private static final int MSG_POLL_DEVICES		= 0x0001;
	private static final int MSG_DEVICE_PROPS_READ	= 0x0002;
	
	private UsbManager mUsbManager;
	private Handler mConnectorEventHandler;
	private UsbConnectorEventListener mUsbConnectorEventListener;
	
	private boolean mSearch;
	
	UsbConnector(UsbManager usbManager) {
		mUsbManager = usbManager;
		mSearch = true;
		mConnectorEventHandler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            switch (msg.what) {
		            case MSG_POLL_DEVICES:
		            	mUsbConnectorEventListener.OnDebugMessage("Received MSG_POLL_DEVICES message");
		            	if(mSearch)
		            	{
		            		//checkDeviceStatus();
		            		mConnectorEventHandler.sendEmptyMessageDelayed(MSG_POLL_DEVICES, 1000);
		            	}
		            	break;
	                case MSG_DEVICE_DETECTED:
	                	mUsbConnectorEventListener.OnDebugMessage("Received MSG_DEVICE_DETECTED message");
	                	mUsbConnectorEventListener.OnDeviceConnected();
	                	//readDeviceData((UsbDevice)msg.obj);
	                    break;
	                default:
	                    super.handleMessage(msg);
	                    break;
	            }
	        }
	    };	
	}
}

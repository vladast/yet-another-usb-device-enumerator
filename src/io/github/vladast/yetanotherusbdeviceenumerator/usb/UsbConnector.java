/**
 * 
 */
package io.github.vladast.yetanotherusbdeviceenumerator.usb;

import java.util.ArrayList;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

/**
 * @author vladimir.stankovic
 *
 */
public class UsbConnector {
	
	public static final String TAG = UsbConnector.class.getSimpleName();
	
	private static final int MSG_POLL_DEVICES			= 0x0001;
	private static final int MSG_DEVICE_DETECTED		= 0x0002;
	private static final int MSG_DEVICES_DETECTED		= 0x0003;
	private static final int MSG_DEVICE_DESCRIPTOR_READ	= 0x0004;
	
	private UsbManager mUsbManager;
	private Handler mConnectorEventHandler;
	private OnUsbConnectorEventListener mUsbConnectorEventListener;
	
	private boolean mSearch;
	
	public UsbConnector(UsbManager usbManager) {
		mUsbManager = usbManager;
		mSearch = true;
		mConnectorEventHandler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            switch (msg.what) {
		            case MSG_POLL_DEVICES:
		            	mUsbConnectorEventListener.OnDebugMessage("Received MSG_POLL_DEVICES message");
		            	mUsbConnectorEventListener.OnPollingDevices();
		            	if(mSearch)
		            	{
		            		pollDevices();
		            		mConnectorEventHandler.sendEmptyMessageDelayed(MSG_POLL_DEVICES, 1000);
		            	}
		            	break;
	                case MSG_DEVICE_DESCRIPTOR_READ:
	                	mUsbConnectorEventListener.OnDebugMessage("Received MSG_DEVICE_DESCRIPTOR_READ message");
	                	mUsbConnectorEventListener.OnDeviceDescriptorRead((UsbDeviceDescriptor)msg.obj);
	                    break;
	                case MSG_DEVICE_DETECTED:
	                	break;
	                case MSG_DEVICES_DETECTED:
	                	break;
	                default:
	                    super.handleMessage(msg);
	                    break;
	            }
	        }
	    };	
	}
	
	public void registerListener(OnUsbConnectorEventListener onUsbConnectorEventListener) {
		mUsbConnectorEventListener = onUsbConnectorEventListener;
	}
	
	protected void pollDevices() {
		mUsbConnectorEventListener.OnDebugMessage("Enumerating devices...");
        
    	new AsyncTask<Void, Void, ArrayList<UsbDevice>>() {

			@Override
			protected ArrayList<UsbDevice> doInBackground(Void... arg0) {
				
				ArrayList<UsbDevice> usbDevices = new ArrayList<UsbDevice>();
				
				for (final UsbDevice usbDevice : mUsbManager.getDeviceList().values()) {
					mUsbConnectorEventListener.OnDebugMessage(String.format("Found device 0x%04x/0x%04x", usbDevice.getVendorId(), usbDevice.getProductId()));
					usbDevices.add(usbDevice);
				}
				
				return usbDevices;
			}
    		
			protected void onPostExecute(ArrayList<UsbDevice> usbDevices) {
				if(usbDevices != null && usbDevices.size() > 0) {
					Message msg = new Message();
					if(usbDevices.size() > 1) {
						msg.what = MSG_DEVICES_DETECTED;
						msg.obj = usbDevices;
					} else {
						msg.what = MSG_DEVICE_DETECTED;
						msg.obj = usbDevices.get(0);
					}
					mConnectorEventHandler.removeMessages(MSG_POLL_DEVICES);
					mConnectorEventHandler.sendMessage(msg);
				}
			}
			
    	}.execute((Void)null);
	}
	
	public void startPolling() {
		mConnectorEventHandler.sendEmptyMessage(MSG_POLL_DEVICES);
	}
	
	public void stopPolling() {
		mConnectorEventHandler.removeMessages(MSG_POLL_DEVICES);
	}
	
}

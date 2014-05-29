package io.github.vladast.yetanotherusbdeviceenumerator.usb;

public interface UsbConnectorEventListener {
	public void OnPollDevices();
	public void OnDeviceDataRead(UsbDeviceData usbDeviceData);
	public void OnErrorMessage(String message);
	public void OnDebugMessage(String message);
}

package io.github.vladast.yetanotherusbdeviceenumerator.usb;

public interface OnUsbConnectorEventListener {
	public void OnPollingDevices();
	public void OnDeviceDescriptorRead(UsbDeviceDescriptor usbDeviceData);
	public void OnErrorMessage(String message);
	public void OnDebugMessage(String message);
}

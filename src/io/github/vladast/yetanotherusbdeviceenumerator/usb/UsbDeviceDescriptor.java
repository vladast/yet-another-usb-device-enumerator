package io.github.vladast.yetanotherusbdeviceenumerator.usb;

public class UsbDeviceDescriptor {
	private short vendorId;
	private short productId;
	private byte usbClass;
	private byte usbProtocol;
	/**
	 * @return the vendorId
	 */
	public short getVendorId() {
		return vendorId;
	}
	/**
	 * @param vendorId the vendorId to set
	 */
	public void setVendorId(short vendorId) {
		this.vendorId = vendorId;
	}
	/**
	 * @return the productId
	 */
	public short getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(short productId) {
		this.productId = productId;
	}
	/**
	 * @return the usbClass
	 */
	public byte getUsbClass() {
		return usbClass;
	}
	/**
	 * @param usbClass the usbClass to set
	 */
	public void setUsbClass(byte usbClass) {
		this.usbClass = usbClass;
	}
	/**
	 * @return the usbProtocol
	 */
	public byte getUsbProtocol() {
		return usbProtocol;
	}
	/**
	 * @param usbProtocol the usbProtocol to set
	 */
	public void setUsbProtocol(byte usbProtocol) {
		this.usbProtocol = usbProtocol;
	}
	
	
}

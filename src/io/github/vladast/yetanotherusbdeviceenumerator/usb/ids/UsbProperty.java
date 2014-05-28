package io.github.vladast.yetanotherusbdeviceenumerator.usb.ids;

public class UsbProperty {

	private Tag _tag;
	private int _id;
	private String _description;
	
	UsbProperty() {
		_tag = Tag.NO_TAG;
		_id = 0;
		_description = "Undefined";
	}
	
	/**
	 * @return the _tag
	 */
	public Tag getTag() {
		return _tag;
	}
	/**
	 * @param _tag the _tag to set
	 */
	public void setTag(Tag tag) {
		_tag = tag;
	}
	/**
	 * @return the _id
	 */
	public int getId() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void setId(int id) {
		_id = id;
	}
	/**
	 * @return the _description
	 */
	public String getDescription() {
		return _description;
	}
	/**
	 * @param _description the _description to set
	 */
	public void setDescription(String description) {
		_description = description;
	}
	
}

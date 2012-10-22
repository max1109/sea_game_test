/*
 * IUsbConnectionHandler.java
 * This file is part of UsbController
 *
 * Copyright (C) 2012 - Manuel Di Cerbo
 *
 * UsbController is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * UsbController is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with UsbController. If not, see <http://www.gnu.org/licenses/>.
 */
package ch.serverbox.android.usbcontroller;

/**
 * (c) Neuxs-Computing GmbH Switzerland
 * @author Manuel Di Cerbo, 02.02.2012
 *
 */
public interface IUsbConnectionHandler {

	
	void onUsbStopped();//當 usb 停止

	void onErrorLooperRunningAlready();//當 錯誤

	void onDeviceNotFound();//當沒有找到裝置
	
	void onRead();//
}

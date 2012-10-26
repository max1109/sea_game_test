/*
 * UsbController.java
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

import java.util.HashMap;
import java.util.Iterator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbConstants;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;

/**
 * (c) Neuxs-Computing GmbH Switzerland
 * 
 * @author Manuel Di Cerbo, 02.02.2012 直接跟USB 做溝通的程式
 */
public class UsbController {

	private final Context mApplicationContext;
	private final UsbManager mUsbManager;
	private final IUsbConnectionHandler mConnectionHandler;
	private int VID = 0;
	private int PID = 0;
	protected static final String ACTION_USB_PERMISSION = "ch.serverbox.android.USB";
	private String tmpStr = "";
	private boolean send_data = false;

	/**
	 * Activity is needed for onResult
	 * 
	 * @param parentActivity
	 */
	public UsbController(Activity parentActivity,
			IUsbConnectionHandler connectionHandler, int vid, int pid) {
		mApplicationContext = parentActivity.getApplicationContext();
		mConnectionHandler = connectionHandler;
		mUsbManager = (UsbManager) mApplicationContext
				.getSystemService(Context.USB_SERVICE);
		VID = vid;
		PID = pid;
		init();

	}

	private void init() {
		enumerate(new IPermissionListener() {
			@Override
			public void onPermissionDenied(UsbDevice d) {

				UsbManager usbman = (UsbManager) mApplicationContext
						.getSystemService(Context.USB_SERVICE);
				PendingIntent pi = PendingIntent.getBroadcast(
						mApplicationContext, 0, new Intent(
								ACTION_USB_PERMISSION), 0);
				mApplicationContext.registerReceiver(mPermissionReceiver,
						new IntentFilter(ACTION_USB_PERMISSION));
				Log.e("tag", "****** onPermissionDenied ");

				usbman.requestPermission(d, pi);
			}
		});
	}

	public void stop() {
		l("stop");
		mStop = true;
		synchronized (sSendLock) {
			sSendLock.notifyAll();
		}
		try {
			if (mUsbThread != null)
				mUsbThread.join();
		} catch (InterruptedException e) {
			e(e);
		}
		mStop = false;
		mLoop = null;
		mUsbThread = null;

		try {
			mApplicationContext.unregisterReceiver(mPermissionReceiver);
		} catch (IllegalArgumentException e) {
		}

	}

	private UsbRunnable mLoop = null;
	private UsbSendRunnable send_loop = null;
	private Thread mUsbThread = null;
	private Thread send_usb_thread = null;

	private void startHandler(UsbDevice d) {
		if (mLoop != null) {
			mConnectionHandler.onErrorLooperRunningAlready();
			return;
		}
		mLoop = new UsbRunnable(d);
		mUsbThread = new Thread(mLoop);
		mUsbThread.start();
		send_loop = new UsbSendRunnable();
		send_usb_thread = new Thread(send_loop);
		send_usb_thread.start();
	}

	// send data
	public void send(String data) {
		send_loop.sendData(data);
		// send_usb_thread.notifyAll();
		synchronized (sSendLock) {
			sSendLock.notifyAll();
		}
	}

	public void resend() {
		send_loop.resend();
		// send_usb_thread.notifyAll();
		synchronized (sSendLock) {
			sSendLock.notifyAll();
		}
	}
	// 設定鮑率
	public void setUart(int br) {
		if (br == 38400) {
			uart = new byte[] { (byte) 0x00, (byte) 0x96, 0x00, 0x00, 0x00,
					0x00, 0x08 };// 38400
		} else {
			uart = new byte[] { (byte) 0x80, 0x25, 0x00, 0x00, 0x00, 0x00, 0x08 };// 9600
		}
	}

	private void enumerate(IPermissionListener listener) {
		l("enumerating");
		HashMap<String, UsbDevice> devlist = mUsbManager.getDeviceList();
		Iterator<UsbDevice> deviter = devlist.values().iterator();
		while (deviter.hasNext()) {
			UsbDevice d = deviter.next();
			// Log.e("d name " , d.getDeviceName());
			// Log.e("d vid " ,""+ d.getVendorId());
			// Log.e("d pid" , ""+d.getProductId());
			l("Found device: "
					+ String.format("%04X:%04X", d.getVendorId(),
							d.getProductId()));
			if (d.getVendorId() == VID && d.getProductId() == PID) {
				l("Device under: " + d.getDeviceName());
				if (!mUsbManager.hasPermission(d))
					listener.onPermissionDenied(d);
				else {
					startHandler(d);
					return;
				}
				break;
			}
		}
		l("no more devices found");
		mConnectionHandler.onDeviceNotFound();
	}

	private class PermissionReceiver extends BroadcastReceiver {
		private final IPermissionListener mPermissionListener;

		public PermissionReceiver(IPermissionListener permissionListener) {
			mPermissionListener = permissionListener;
		}

		@Override
		public void onReceive(Context context, Intent intent) {
			mApplicationContext.unregisterReceiver(this);

			if (intent.getAction().equals(ACTION_USB_PERMISSION)) {

				if (!intent.getBooleanExtra(
						UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
					mPermissionListener.onPermissionDenied((UsbDevice) intent
							.getParcelableExtra(UsbManager.EXTRA_DEVICE));
				} else {

					l("Permission granted");
					UsbDevice dev = (UsbDevice) intent
							.getParcelableExtra(UsbManager.EXTRA_DEVICE);
					if (dev != null) {
						if (dev.getVendorId() == VID
								&& dev.getProductId() == PID) {
							startHandler(dev);// has new thread
						}
					} else {
						e("device not present!");
					}
				}
			}

			if (intent.getAction()
					.equals(UsbManager.ACTION_USB_DEVICE_DETACHED)) {
				UsbDevice device = (UsbDevice) intent
						.getParcelableExtra(UsbManager.EXTRA_DEVICE);
				if (device != null) {
					// call your method that cleans up and closes communication
					// with the device
					l("清理和關閉與設備的通信");
				}
			}
		}

	}

	// MAIN LOOP
	private static final Object[] sSendLock = new Object[] {};// learned this
																// trick from
																// some google
																// example :)

	// basically an empty array is lighter than an actual new Object()...
	private boolean mStop = false;

	byte[] uart = null;

	int IN_BUFF_LEN = 128;
	byte[] inBuff = new byte[IN_BUFF_LEN];
	// byte[] bigBuff = new byte[BIG_BUFF_LEN];
	UsbDeviceConnection conn;
	UsbEndpoint epIN = null;
	UsbEndpoint epOUT = null;

	private class UsbSendRunnable implements Runnable {
		// private UsbDevice mDevice = null;
		private byte[] mData = new byte[1];
		UsbSendRunnable() {
			// mDevice = dev;
		}

		public void sendData(String data) {
			mData = (data).getBytes();
			l(data);
			send_data = true;
		}

		public void resend() {
			send_data = true;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true) {
				try {
					if ( send_data ) {
						conn.bulkTransfer(epOUT, mData, mData.length, 10);
						send_data = false;
						l("send data------------------------------------------------");
						Thread.sleep(900);

					}
					Thread.sleep(2900);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}

	}

	private class UsbRunnable implements Runnable {
		private UsbDevice mDevice = null;
		byte b[] = null;

		UsbRunnable(UsbDevice dev) {
			mDevice = dev;
		}

		@Override
		public void run() { // here the main USB functionality is implemented
			l("run");
			conn = mUsbManager.openDevice(mDevice);
			if (!conn.claimInterface(mDevice.getInterface(0), true)) {
				return;
			}
			// Arduino Serial usb Conv
			if (uart == null)
				setUart(38400);
			conn.controlTransfer(0x21, 34, 0, 0, null, 0, 0);
			conn.controlTransfer(0x21, 32, 0, 0, uart, uart.length, 0);

			epIN = null;
			epOUT = null;

			UsbInterface usbIf = mDevice.getInterface(0);
			for (int i = 0; i < usbIf.getEndpointCount(); i++) {
				if (usbIf.getEndpoint(i).getType() == UsbConstants.USB_ENDPOINT_XFER_BULK) {
					if (usbIf.getEndpoint(i).getDirection() == UsbConstants.USB_DIR_IN) {
						epIN = usbIf.getEndpoint(i);
						l("epIN Endpoint(" + i + ")");
					} else if (usbIf.getEndpoint(i).getDirection() == UsbConstants.USB_DIR_OUT) {
						epOUT = usbIf.getEndpoint(i);
						l("epOUT Endpoint(" + i + ")");
					}
				}
			}

			while (true) {// this is the main loop for transferring

				synchronized (sSendLock) {// ok there should be a OUT queue, no
											// guarantee that the byte is sent
											// actually
					try {
						// sSendLock.notify();
						sSendLock.wait(50);// 進入等待
						// l(" sSendLock.wait(50) 進入等待");
					} catch (InterruptedException e) {
						if (mStop) {
							mConnectionHandler.onUsbStopped();
							return;
						}
						l("error" + e.toString());
					}
				}

				int len = 0;
				try {
					len = conn.bulkTransfer(epIN, inBuff, IN_BUFF_LEN, 10);
					if (len > 0) {
						// Log.e("tag len" , "len = " + len );

						// System.arraycopy(inBuff, 0, bigBuff, totalLen, len);
						// totalLen += len;
						b = new byte[len];
						System.arraycopy(inBuff, 0, b, 0, len);
						tmpStr = new String(b);
						// Log.e(" tag tmp Str " , tmpStr );
					}
				} catch (Exception e) {

				}
				// }

				if (mStop) {
					l("onUsbStopped");
					mConnectionHandler.onUsbStopped();
					return;
				}
			}
		}
	}

	private BroadcastReceiver mPermissionReceiver = new PermissionReceiver(
			new IPermissionListener() {
				@Override
				public void onPermissionDenied(UsbDevice d) {
					l("Permission denied on " + d.getDeviceId());
				}
			});

	private static interface IPermissionListener {
		void onPermissionDenied(UsbDevice d);
	}

	public final static String TAG = "USBController";

	private void l(Object msg) {
		Log.e(TAG, ">==< " + msg.toString() + " >==<");
	}

	private void e(Object msg) {
		Log.e(TAG, ">==< " + msg.toString() + " >==<");
	}

	public String readStr() {
		String str = "";
		if (tmpStr.length() > 0) {
			str = tmpStr;
			tmpStr = "";

		}
		return str;
	}

}
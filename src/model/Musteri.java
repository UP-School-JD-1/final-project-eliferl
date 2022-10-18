package model;

import java.util.List;

public class Musteri implements Runnable {

	public Object siparisNum;
	public List<Yiyecek> siparis;

	public void run1() {
		// TODO Auto-generated method stub
		
	}

		private final String name;

		private static int runningCounter = 0;
		
		private static int cusCounter = 0;
		public static int maxCus = 40;
		public static Object lock = new Object();

		public Musteri(String name, List<Yiyecek> order) {
			this.name = name;
			this.siparis = siparis;
			this.siparisNum = ++runningCounter;
		}

		public String toString() {
			return name;
		}

		public void run() {
			
			Test.logEvent(TestEvent.musteriStarting(this));
			synchronized (lock) {
				while(cusCounter>=maxCus) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				cusCounter++;
			}		
			Test.logEvent(TestEvent.musteriEnteredCoffeeShop(this));
			synchronized (Test.waitSiparis) {
				Test.logEvent((TestEvent.musteriPlacedOrder(this, this.siparis, this.siparisNum)));
				Test.waitSiparis.add(this);
				Test.waitSiparis.notifyAll();
			}
			synchronized (this) {
				try {
					
					this.wait();
					Test.logEvent((TestEvent.musteriReceivedSiparis(this, this.siparis, this.siparisNum)));
					
					Thread.sleep(100);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}
			}
			Test.logEvent(TestEvent.musteriLeavingCoffeeShop(this));
			synchronized (lock) {
				cusCounter--;
				lock.notifyAll();
			}
		}
	}


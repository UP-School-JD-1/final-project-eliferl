package model;

public class Sef implements Runnable{
	
	public final String sefName = "";
	public final Siparis sefFoodType = new Siparis();

	public volatile boolean full;
	private int run;
	public final Object lock = new Object();

	public Sef(String nameIn, Siparis foodIn, int capacityIn) {
		this.sefName = nameIn;
		this.sefFoodType = foodIn;
		
		this.full = capacityIn <= 0;
		this.run = 0;
		Test.logEvent(TestEvent.machineStarting(this,foodIn,capacityIn));
	}
	
	public Thread[] makeFood(Garson garson,Musteri musteri,int num) throws InterruptedException {

		Thread[] threads = new Thread[num];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new CookAnItem(garson,musteri));
		}
		synchronized (lock) {

			for (int i = 0; i < threads.length; i++) {
				while(full) {
					lock.wait();
				}
				if(++run>=full) full = true;
				threads[i].start();
			}
		}
		return threads;
	}

	private class CookAnItem implements Runnable {
		
		private Garson garson;
		private Musteri musteri;
		
		public CookAnItem(Garson garson, Musteri musteri) {
			this.garson = garson;
			this.musteri = musteri;
		}
		
		public void run() {
			try {
				
				Test.logEvent(TestEvent.cookStartedFood(garson, sefFoodType, musteri.siparisNum));
				Test.logEvent(TestEvent.sefCookingFood(Sef.this, sefFoodType));
				Thread.sleep(sefFoodType.sefTimeMS);
				synchronized (lock) {
					run--;
					if(full) full = false;
					lock.notifyAll();
				}
				Test.logEvent(TestEvent.machineDoneFood(Sef.this, Sef.this.sefFoodType));
				Test.logEvent(TestEvent.cookFinishedFood(garson, sefFoodType, musteri.siparisNum));
			} catch(InterruptedException e) { 
				Test.logEvent(TestEvent.machineEnding(Sef.this));
			}
		}
	}
 

	public String toString() {
		return sefName;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
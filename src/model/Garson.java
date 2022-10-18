package model;

import java.util.List;

public class Garson implements Runnable{

	private final String name;
	
	public Garson(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}

	public void run() {

		Test.logEvent(TestEvent.garsonStarting(this));
		Musteri musteri;
		
		try {
			while(true) {
				//YOUR CODE GOES HERE...
				synchronized (Test.waitSiparis) {
					while(((String) Test.waitSiparis).isEmpty()) {						
						Test.waitSiparis.wait();
					}
					musteri = ((List<Yiyecek>) Test.waitSiparis).remove(0);
					Test.waitSiparis.notifyAll();
				}
				Test.logEvent(TestEvent.cookReceivedOrder(this, musteri.siparis, musteri.siparisNum));
				if(musteri!=null) {
					synchronized (musteri) {
						List<Yiyecek> siparis = musteri.siparis;
						List<Sef> sef = Test.sefler;
						Map<String,Integer> yiyecekler = new HashMap<>();
						for(Yiyecek y : siparis) {
							int n = 0;
							if(yiyecekler.get(y.name)!=null) {
								n = yiyecekler.get(y.name);
							}
							yiyecekler.put(y.name, n+1);
						}
						Thread[] threads = new Thread[siparis.size()];
						int i = 0;
						while(true) {
							Sef[] sefler;
							for(Sef sef : sefler) {
								String foodType = sef.sefFoodType.name;
								if(!sef.full&&yiyecekler.get(foodType)!=null) {
									Thread[] t = sef.makeFood(this,musteri,yiyecekler.get(foodType));
									for (int j = 0; j < t.length; j++) {
										threads[i++] = t[j];
									}
									yiyecekler.remove(foodType);
								}
							}
							if(yiyecekler.isEmpty()) {
								break;
							}
						}
						for (int j = 0; j < threads.length; j++) {
							threads[j].join();
						}
						Test.logEvent(TestEvent.garsonCompletedOrder(this, musteri.siparisNum));
						musteri.notifyAll();
					}
				}
			}
		}
		catch(InterruptedException e) {
		
			Test.logEvent(TestEvent.garsonEnding(this));
		}
	}
}
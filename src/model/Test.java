package model;

public class Test {

	public static Object waitSiparis;

	public static Object GarsonStarting(Garson garson) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void logEvent(Object garsonStarting) {
		// TODO Auto-generated method stub
		
	}

	public static List<TestEvent> events;  

	public static List<Musteri> waitOrder;
	
	public static List<Sef> sefler;

	public static void logEvent(TestEvent event) {
		events.add(event);
		System.out.println(event);
	}

	public static List<TestEvent> runSimulation(
			int musteriler, int numYiyecekler,
			int numMasa, 
			int SefCapacity,
			boolean randomSiparis
			) {

		events = Collections.synchronizedList(new ArrayList<TestEvent>());
		

		logEvent(TestEvent.startSimulation(numSiparis,
				numYiyecekler,
				numMasa,
				SefCapacity));


		waitOrder = new ArrayList<>();
		sef = new ArrayList<>();
		Musteri.maxCus = numMasa;

		sef.add(new Sef("BurgerforSef",YiyecekTipi.burger,SefCapacity));
		sef.add(new Sef("FriesforSef",YiyecekTipi.fries,SefCapacity));
		sef.add(new Sef("CoffeeforSef",YiyecekTipi.coffee,SefCapacity));
	
		Thread[] cooks = new Thread[numGarson];
		for (int i = 0; i < cooks.length; i++) {
			cooks[i] = new Thread(new Garson("Garson "+(i+1)));
			cooks[i].start();
		}
		// Build the customers.
		Thread[] customers = new Thread[numMusteri];
		LinkedList<Yiyecek> siparis;
		if (!randomOrders) {
			siparis = new LinkedList<Yiyecek>();
			siparis.add(YiyecekTipi.burger);
			siparis.add(YiyecekTipi.fries);
			siparis.add(YiyecekTipi.fries);
			siparis.add(YiyecekTipi.coffee);
			for(int i = 0; i < musteriler.length; i++) {
				customers[i] = new Thread(
						new Musteri("müsteri " + (i+1), order)
						);
			}
		}
		else {
			for(int i = 0; i < musteriler.length; i++) {
				Random rnd = new Random(27);
				int burgerCount = rnd.nextInt(3);
				int friesCount = rnd.nextInt(3);
				int coffeeCount = rnd.nextInt(3);
				siparis = new LinkedList<Yiyecek>();
				for (int b = 0; b < burgerCount; b++) {
					siparis.add(YiyecekTipi.burger);
				}
				for (int f = 0; f < friesCount; f++) {
					siparis.add(YiyecekTipi.fries);
				}
				for (int c = 0; c < coffeeCount; c++) {
					siparis.add(YiyecekTipi.coffee);
				}
				musteriler[i] = new Thread(
						new Musterler("Müşteriler " + (i+1), order));
			}
		}

		for(int i = 0; i < musteriler.length; i++) {
			musteriler[i].start();
		}


		try {
		
			for(int i = 0; i < musteriler.length; i++)
				musteriler[i].join();
			
		
			for(int i = 0; i < sefler.length; i++)
				sefler[i].interrupt();
			for(int i = 0; i < sefler.length; i++)
				sefler[i].join();

		}
		catch(InterruptedException e) {
			System.out.println("Test thread interrupted.");
		}

	
		logEvent(TestEvent.endSimulation());

		return events;
	}

	public static void main(String args[]) throws InterruptedException {
		
		int numMusteriler = 10;
		int numSefler =3;
		int numMasalar = 5;
		int sefCapacity = 4;
		boolean randomSiparisler = true;

		System.out.println("Did it work? " + 
				Validate.validateSimulation(
						runSimulation(
								numMusteriler, numSefler, 
								numMasalar, sefCapacity,
								randomSiparisler
								)
						)
				);
	}

}



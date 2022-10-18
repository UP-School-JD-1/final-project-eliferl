package model;

import java.util.List;

public class TestEvent {
	 public enum EventType {
	    	TestStarting,
	    	TestEnded,
	  
		    MusteriStarting,
	    	MusteriEnteredCoffeeShop,
	    	MusteriPlacedOrder,
	    	MusteriReceivedOrder,
	    	MusteriLeavingCoffeeShop,
	
	    	GarsonStarting,
	    	GarsonReceivedOrder,
	    	GarsonStartedFood,
		    GarsonFinishedFood,
	    	GarsonCompletedOrder,
	    	GarsonEnding,
	  
	    	SefStarting,
	    	SefStartingFood,
	    	SefDoneFood,
	    	SefEnding    			
	    };
	    public final EventType event;
	    public final Garson garson;
	    public final Musteri musteri;
	    public final Sef sef;
	    public final Yiyecek yiyecek;
	    public final int yiyecekSiparisi;
	    public final static int siparisNo;
	    public final int[] simParams;

	    private TestEvent(EventType event, 
				    Garson garson,
				    Musteri musteri,
				    Sef sef,
				    Yiyecek yiyecek,
				    List<Yiyecek> yiyecekSiparisi,
				    int yiyecekSiparisi1,
				    int[] simParams, int siparisNo) {
		this.event = event;
		this.garson = garson;
		this.musteri = musteri;
		this.sef = sef;
		this.yiyecek = yiyecek;
		this.yiyecekSiparisi = yiyecekSiparisi1;
		this.siparisNo = siparisNo;
		this.simParams = simParams;
	    }

	    public static TestEvent startSimulation(int numMusteri,
						   int numGarson,
						   int numMasa,
						   int kapasite) {
		int[] params = new int[4];
		params[0] = numMusteri;
		params[1] = numGarson;
		params[2] = numMasa;
		params[3] = kapasite;
		return new TestEvent(EventType.TestStarting,null,null,null,null,null,0,params, kapasite);
	    }

	    public static TestEvent endTest() {
		try {
			try {
				return new TestEvent(EventType.TestEnded,null,null,null,null,null,0,null, siparisNo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	    public static TestEvent musteriStarting(Musteri musteri) {
		try {
			return new TestEvent(EventType.MusteriStarting,
						   null,
						   musteri,
						   null,null,null,0,null, siparisNo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }

	    public static TestEvent musteriEnteredCoffeeShop(Musteri musteri) {
		return new TestEvent(EventType.MusteriEnteredCoffeeShop,
					   null,
					   musteri,
					   null,null,null,0,null);
	    }

	    public static TestEvent musteriPlacedOrder(Musteri musteri,
						    List<Yiyecek> siparis,
						    int siparisNumber) {
		return new TestEvent(EventType.MusteriPlacedOrder,
					   null,
					   musteri,
					   null, null,
					   siparis, siparisNumber,
					   null);
	    }

	    public static TestEvent customerReceivedOrder(Musteri musteri,
						    List<Yiyecek> siparis,
						    int orderNumber) {
		return new TestEvent(EventType.CustomerReceivedOrder,
					   null,
					   musteri,
					   null, null,
					   siparis, orderNumber,
					   null);
	    }

	    public static TestEvent musteriLeavingCoffeeShop(Musteri musteri) {
		return new TestEvent(EventType.MusteriLeavingCoffeeShop,
					   null,
					   musteri,
					   null, null, null, 0, null);
	    }

	    public static TestEvent garsonStarting(Garson garson) {
		return new TestEvent(EventType.GarsonStarting,
					   garson,
					   null,null,null,null,0,null);
	    }

	    public static TestEvent garsonReceivedOrder(Garson garson,
						     List<Yiyecek> siparis,
						     int orderNumber) {
		return new TestEvent(EventType.GarsonReceivedOrder,
					   garson,
					   null,null, null,
					   garson, orderNumber,
					   null);
	    }

	    public static TestEvent garsonStartedFood(Garson garson, Yiyecek yiyecek,
						   int orderNumber) {
		return new TestEvent(EventType.GarsonStartedFood,
					   garson,
					   null,null,
					   yiyecek,
					   null,
					   orderNumber,
					   null);
	    }

	    public static TestEvent garsonFinishedFood(Garson garson, Yiyecek yiyecek,
						   int orderNumber) {
		return new TestEvent(EventType.GarsonFinishedFood,
					   garson,
					   null,null,
					   yiyecek,
					   null,
					   orderNumber,
					   null);
	    }

	    public static TestEvent garsonCompletedOrder(
	    						Garson garson, int orderNumber) {
		return new TestEvent(EventType.GarsonCompletedOrder,
					   garson,
					   null,null,null,null,
					   orderNumber,
					   null);
	    }

	    public static TestEvent garsonEnding(Garson garson) {
		return new TestEvent(EventType.GarsonEnding, cook,
					   null,null,null,null,0,null);
	    }

	    public static TestEvent sefStarting(Sef sef, 
						   Yiyecek yiyecek, 
						   int kapasite) {
	    	int[] params = new int[1];
	    	params[0] = kapasite;	
	    	return new TestEvent(EventType.SefStarting,
					   null, null,
					   sef,
					   yiyecek,null,0,params);
	    }

	    public static TestEvent sefCookingFood(Sef sef,
						      Yiyecek yiyecek) {
		return new TestEvent(EventType.SefStartingFood,
					   null,null,
					   sef,
					   yiyecek,
					   null,0,null);
	    }

	    public static TestEvent machineDoneFood(Sef sef,
						   Yiyecek yiyecek) {
		return new TestEvent(EventType.SefDoneFood,
					   null,null,
					   sef,
					   yiyecek,
					   null,0,null);
	    }

	    public static TestEvent sefEnding(Sef sef) {
		return new TestEvent(EventType.SefEnding,
					   null,null,
					   sef,
					   null,null,0,null);
	    }

	    public String toString() {
		switch (event) {
	    	case TestStarting:
		    int numMusteri = simParams[0];
		    int numGarson = simParams[1];
		    int numMasa = simParams[2];
		    int kapasite = simParams[3];
		    return "Starting is test: "+numMusteri+" customers; "+
			numGarson+" garson; "+numMasa+" masa; "+
			"şef kapasitesi "+kapasite+".";
		    
		case TestEnded:
		    return "Test ended.";

		case TestStarting:
		    return musteri + " going to coffee shop.";

		case MusteriEnteredCoffeeShop:
		    return musteri + " entered coffee shop.";

		case MusteriPlacedOrder:
		    return musteri + " placing order " + siparisNumber + " " + siparisFood; 
		    
		case MusteriReceivedOrder:
		    return musteri + " received order " + siparisNumber + " " + siparisFood;

		case TestLeavingCoffeeShop:
		    return musteri + " leaving coffee shop.";

		case SefStarting:
		    return garson + " reporting for work.";

		case SefReceivedOrder:
		    return garson + " starting order "+ siparisNumber + " " + siparisFood;

		case SefStartedFood:
		    return garson + " cooking " + yiyecek + " for order " + orderNumber;

		case SefFinishedFood:
		    return garson + " finished " + yiyecek + " for order " + orderNumber;

		case SefCompletedOrder:
		    return garson + " completed order "+orderNumber;

		case SefEnding:
		    return garson + " going home for the night.";

		case GarsonStarting:
		    return sef + " starting up for making " + 
			yiyecek + "; " + simParams[0] +".";

		case GarsonStartingYiyecek:
		    return machine + " cooking " + yiyecek + ".";

		case GarsonDoneYiyecek:
		    return sef + " completed " + yiyecek + ".";

		case SefEnding:
		    return sef + " shutting down.";

		default:
		    throw new Error("Illegal event; can't be stringified");
		}
	    }

		public static TestEvent musteriReceivedSiparis(Musteri musteri2, List<Yiyecek> siparis, Object siparisNum) {
			// TODO Auto-generated method stub
			return null;
		}
	}
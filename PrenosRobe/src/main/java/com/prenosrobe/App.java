package com.prenosrobe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App
{
	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
//		
//		User user = new User("Mikaaaa", "Mikic", "Mikic", "mika", "mail2", "222333");
//		user.setId(1);
//		
//		Impression impression = new Impression("los", 3, user.getId());
//		impression.setUser(user);
//		user.getImpressions().add(impression);
//
//		DriverOffer driverOffer = new DriverOffer("Beograd 2", "Nis 2", new Date(2017 - 1900, 12 - 1, 13),
//				user.getId());
//		driverOffer.setStationNames(Arrays.asList("Novi Sad"));
//		driverOffer.setId(1);
//		driverOffer.setTime(new Time(14, 30, 15));
//		driverOffer.setStationsDriverOfferId();
//
//		user.getDriverOffers().add(driverOffer);
//
//		OfferStatus offerStatus = new OfferStatus("accepted");
//		offerStatus.setId(1);
//
//		ClaimerOffer claimerOffer = new ClaimerOffer("Beograd", "Subotica", "prenosi se frizider",
//				user.getId(), driverOffer.getId(), offerStatus.getId());
//		claimerOffer.setUser(user);
//		claimerOffer.setDriverOffer(driverOffer);
//		claimerOffer.setOfferStatus(offerStatus);
//		
//		offerStatus.addClaimerOffer(claimerOffer);
//		driverOffer.addClaimerOffer(claimerOffer);
//		user.getClaimerOffers().add(claimerOffer);

//		List<Station> stations = driverOffer.getStations();
		
		
		
//		Session session = OfferStatusDao.getInstance().openCurrentSessionWithTransaction();
//
//		session.save(user);
//		session.save(impression);
//		session.save(driverOffer);
//		session.save(offerStatus);
//		session.save(claimerOffer);
//		session.save(stations.get(0));
//		session.save(stations.get(1));
//		session.save(stations.get(2));		
//		OfferStatusDao.getInstance().closeCurrentSessionWithTransaction();
		
		// DRIVER_OFFER_DAO
//		DriverOfferDao.getInstance().openCurrentSessionWithTransaction();
//		DriverOfferDao.getInstance().sendDriverOffer(driverOffer);
//		List<DriverOffer> driverOffers = DriverOfferDao.getInstance().getDriverOffers();
//		driverOffer = DriverOfferDao.getInstance().getDriverOfferById(2);
//		List<DriverOffer> driverOffers = DriverOfferDao.getInstance().getDriverOffersByDriverId(1);
//		List<DriverOffer> driverOffers = DriverOfferDao.getInstance().getDriverOffersByClaimerId(1);
//		DriverOfferDao.getInstance().deleteDriverOffer(4);
//		DriverOfferDao.getInstance().updateDriverOffer(driverOffer);
//		DriverOfferDao.getInstance().closeCurrentSessionWithTransaction();
		
		//IMPRESSION_DAO
//		ImpressionDao.getInstance().openCurrentSessionWithTransaction();
//		ImpressionDao.getInstance().sendImpression(impression);
//		impression = ImpressionDao.getInstance().getImpressionByImpressionId(3);
//		List<Impression> impressions = ImpressionDao.getInstance().getImpressionsByUserId(1);
//		ImpressionDao.getInstance().closeCurrentSessionWithTransaction();
		
		//CLAIMER_OFFER_DAO
//		ClaimerOfferDao.getInstance().openCurrentSessionWithTransaction();
//		ClaimerOffer claimerOffer2 = ClaimerOfferDao.getInstance().getClaimerOfferById(2);
//		List<ClaimerOffer> claimerOffers = ClaimerOfferDao.getInstance().getClaimerOffersByDriverOfferId(1);
//		List<ClaimerOffer> claimerOffers = ClaimerOfferDao.getInstance().getClaimerOffersByClaimerId(1);
//		ClaimerOfferDao.getInstance().updateClaimerOfferStatus(2, 2);
//		ClaimerOfferDao.getInstance().deleteClaimerOffer(3);
//		ClaimerOfferDao.getInstance().closeCurrentSessionWithTransaction();
		
		//USER_DAO
//		UserDao.getInstance().openCurrentSessionWithTransaction();
//		User user2 = UserDao.getInstance().getUserById(1);
//		UserDao.getInstance().updateUser(user);
//		UserDao.getInstance().updateUserActivity(1, true);
//		UserDao.getInstance().closeCurrentSessionWithTransaction();
		
		// IMPRESSION_SERVICE
//		ImpressionService.getInstance().sendImpression(impression);
//		Impression impression2 = ImpressionService.getInstance().getImpressionByImpressionId(4);
//		List<Impression> impressions = ImpressionService.getInstance().getImpressionsByUserId(1);
		
		// USER_SERVICE
//		UserService.getInstance().sendUser(user);
//		User user2 = UserService.getInstance().getUserById(2);
//		SpringApplication.run(App.class, args);
//		UserService.getInstance().updateUser(user);
//		UserService.getInstance().updateUserActivity(2, false);
		
		//DRIVER_OFFER_SERVICE
//		DriverOfferService.getInstance().sendDriverOffer(driverOffer);
//		List<DriverOffer> driverOffers = DriverOfferService.getInstance().getDriverOffers(); 
		
//		System.out.println("Hello World!");
	}
}

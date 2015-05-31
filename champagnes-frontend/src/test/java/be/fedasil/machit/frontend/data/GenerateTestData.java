package be.fedasil.machit.frontend.data;

import javax.persistence.EntityManager;

import be.fedasil.matchit.backend.dao.PersistenceManager;
import be.fedasil.matchit.backend.model.Address;
import be.fedasil.matchit.backend.model.Contact;
import be.fedasil.matchit.backend.model.Location;
import be.fedasil.matchit.backend.model.Place;
import be.fedasil.matchit.backend.model.ReceptionCenter;
import be.fedasil.matchit.backend.model.Room;
import be.fedasil.matchit.backend.model.properties.PropertiesHolder;

/**
 * Created by tmaes on 29/04/2015.
 */
public class GenerateTestData {

    public static void main(String[] args) {

        EntityManager em = PersistenceManager.INSTANCE.getEntityManager();

        em.getTransaction().begin();

        Address adr1 = new Address("Central", "bvl", "3", "10", "1080",
                "IXELLES", "Belgium");
        Address adr2 = new Address("Comptabilite", "bvl", "3", "10", "1080",
                "IXELLES", "Belgium");

        em.persist(adr1);
        em.persist(adr2);

        ReceptionCenter receptionCenter1 = new ReceptionCenter();
        receptionCenter1.setOrganization("Charleroi");
        receptionCenter1.setEmailAddress("charleroi@fedasil.be");
        receptionCenter1.setPhoneNumber("02 213 12 11");
        receptionCenter1.setMaxNumberOfPlaces(200);
        receptionCenter1.setNetwork("FED");
        receptionCenter1.setCodeLocation("ARE");
        receptionCenter1.setReceptionName("Arendonk");
        receptionCenter1.setStructureType("Red Cross");
        receptionCenter1.setContactAddressId(adr1);
        receptionCenter1.setStructureType("STRUCTURE TYPE");
        receptionCenter1.setPropertiesHolder(generateProperties("receptionCenter"));

        em.persist(receptionCenter1);

        // Location
        Location location = new Location(receptionCenter1, adr2);
        location.setPropertiesHolder(generateProperties("location"));
        em.persist(location);

        // room1
        Room room1 = new Room();
        room1.setMaxNumberOfBeds(3);
        room1.setNumberOfAvailableBeds(3);
        room1.setLocation(location);
        room1.setPropertiesHolder(generateProperties("room"));

        em.persist(room1);

        // Place
        // place1
        Place place1 = new Place();
        place1.setConventionedPlace("nvelle");
        place1.setOccupancyStatus("average");
        place1.setPlaceCode("FEDCHA123");
        place1.setTypeBed("twijfelaar");
        place1.setTypePlace("famille");
        place1.setRoom(room1);
        place1.setPropertiesHolder(generateProperties("place"));

        // place2
        Place place2 = new Place();
        place2.setPlaceCode("FEDCHA222");
        place2.setConventionedPlace("art12");
        place2.setOccupancyStatus("free");
        place2.setTypeBed("Simple");
        place2.setTypePlace("Isolé");
        place2.setRoom(room1);
        place2.setPropertiesHolder(generateProperties("place"));

        // place3
        Place place3 = new Place();
        place3.setPlaceCode("FEDCHA225");
        place3.setConventionedPlace("art12");
        place3.setOccupancyStatus("free");
        place3.setTypeBed("Simple");
        place3.setTypePlace("Isolé");
        place3.setRoom(room1);
        place3.setPropertiesHolder(generateProperties("place"));

        Contact contact1 = new Contact();
        contact1.setName("Hans Teeuwen");
        contact1.setService("Secretary");
        contact1.setEmailAddress("hans.teeuwen@fedasil.be");
        contact1.setPhoneNumber("012/345678");
        contact1.setContactType("Good");
        contact1.setReceptionCenterContact(receptionCenter1);

        Contact contact2 = new Contact();
        contact2.setName("ikke den dikke");
        contact2.setService("president");
        contact2.setEmailAddress("ikke.dendikke@fedasil.be");
        contact2.setPhoneNumber("012/345678");
        contact2.setContactType("Good");
        contact2.setReceptionCenterContact(receptionCenter1);

        em.persist(place1);
        em.persist(place2);
        em.persist(place3);

        em.persist(contact1);
        em.persist(contact2);

        em.getTransaction().commit();
        em.close();
    }

    private static PropertiesHolder generateProperties(String entityName) {
        if (entityName.equals("place")) {
            PropertiesHolder placePh = new PropertiesHolder();
            placePh.addCategoryValues("category.general", "targetGroup", "family", "man alone");
            placePh.addCategoryValues("category.general", "availabilityTyoe", "PLAOCC001", "PLAOCC002");
            placePh.addCategoryValues("category.general", "extraInfo", "Green environment");
            placePh.addCategoryValues("category.general", "accessibilityLabel", "PLATYP004");
            return placePh;
        } else if (entityName.equals("location")) {
            PropertiesHolder locationPh = new PropertiesHolder();
            locationPh.addCategoryValues("category.health", "accessToHospital", "ADDSIT001");
            locationPh.addCategoryValues("category.health", "hospitalSpecialisation", "some random value");
            locationPh.addCategoryValues("category.education", "accessToNurserySchoolFR", "ADDSIT003");
            locationPh.addCategoryValues("category.education", "accessToNurserySchoolNL", "ADDSIT005");
            return locationPh;
        } else {
            PropertiesHolder ph = new PropertiesHolder();
            ph.addCategoryValues(entityName + ".cat1", "entry1", "value1");
            ph.addCategoryValues(entityName + ".cat1", "entry2", "value2");

            ph.addCategoryValues(entityName + ".cat2", "entry1", "value1");
            ph.addCategoryValues(entityName + ".cat2", "entry2", "value2");

            ph.addCategoryValues(entityName + ".cat3", "entry1", "value1");
            ph.addCategoryValues(entityName + ".cat3", "entry2", "value2");
            ph.addCategoryValues(entityName + ".cat3", "entry3", "value3");

            return ph;
        }
    }
}

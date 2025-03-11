package advertpackage.advertentity;

import net.datafaker.Faker;

import java.util.ArrayList;
import java.util.Random;

import static helper.TimeHelper.getCurrentDateAndTimeToNumber;
import static settingspackage.GetMessengerType.getRandomMessengerType;


public class AdvertContactEntity {
    int contactID;
    String person;
    String status;
    String email;
    String position;

    String created_at;
    String updated_at;

    ArrayList<Messenger> messengers;

    public static class Messenger {
        Integer messengerId;
        Integer messengerTypeId;
        String messengerTypeName;
        String messengerValue;

        public Messenger() {
        }

        public void generateMessenger() {
            Faker faker = new Faker();
            this.messengerTypeId = getRandomMessengerType();
            this.messengerValue = faker.animal().name() + getCurrentDateAndTimeToNumber();
        }

        public Integer getMessengerTypeId() {
            return messengerTypeId;
        }

        public void setMessengerTypeId(Integer messengerTypeId) {
            this.messengerTypeId = messengerTypeId;
        }

        public String getMessengerValue() {
            return messengerValue;
        }

        public void setMessengerValue(String messengerValue) {
            this.messengerValue = messengerValue;
        }

        public Integer getMessengerId() {
            return messengerId;
        }

        public void setMessengerId(Integer messengerId) {
            this.messengerId = messengerId;
        }

        public String getMessengerTypeName() {
            return messengerTypeName;
        }

        public void setMessengerTypeName(String messengerTypeName) {
            this.messengerTypeName = messengerTypeName;
        }
    }


    public void generateContactFields() {
        Faker faker = new Faker();
        this.person =  faker.name().fullName();
        this.status = "active";
        this.email =  faker.internet().emailAddress(this.person.replace(" ",""));
        this.position = faker.pokemon().name();

        ArrayList<Messenger> messengers = new ArrayList<>();
        for (int i = 0; i <= new Random().nextInt(3) + 1; i++) {
            Messenger messenger = new Messenger();
            messenger.generateMessenger();
            messengers.add(messenger);
        }
        this.messengers = messengers;
    }


    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getContactID() {
        return contactID;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public ArrayList<Messenger> getMessengers() {
        return messengers;
    }

    public void setMessengers(ArrayList<Messenger> messengers) {
        this.messengers = messengers;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
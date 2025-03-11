package advertpackage.advertentity;

import net.datafaker.Faker;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static helper.TimeHelper.getCurrentDateAndTimeToNumber;
import static settingspackage.GetMessengerType.getRandomMessengerType;


public class AdvertPaymentInfoEntity {
    double minPayout;
    List<AdvertRequisitesEntity> advertRequisitesList = new ArrayList<>();

    public AdvertPaymentInfoEntity() {
    }

    public void fillAdvertPaymentInfoWithRandomData() throws Exception {
        Faker faker = new Faker();
        this.minPayout = faker.number().randomNumber();
    }

    public double getMinPayout() {
        return minPayout;
    }

    public void setMinPayout(double minPayout) {
        this.minPayout = minPayout;
    }

    public List<AdvertRequisitesEntity> getAdvertRequisitesList() {
        return advertRequisitesList;
    }

    public void setAdvertRequisitesList(List<AdvertRequisitesEntity> advertRequisitesList) {
        this.advertRequisitesList = advertRequisitesList;
    }

    public void addAdvertRequisitesList(AdvertRequisitesEntity advertRequisites) {
        this.advertRequisitesList.add(advertRequisites);
    }
}
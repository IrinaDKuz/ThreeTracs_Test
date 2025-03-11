package advertpackage.advertentity;

import net.datafaker.Faker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static settingspackage.GetPaymentType.getRandomPaymentSystemInfo;
import static settingspackage.GetPaymentType.getRandomPaymentType;


public class AdvertRequisitesEntity {
    int requisitesId;
    String currency;
    int paymentSystemId;
    String paymentSystemTitle;
    Map<String, String> requisites = new HashMap<>();
    Boolean isDefault;

    public AdvertRequisitesEntity() {
    }

    public void fillAdvertRequisitesWithRandomData() {
        Faker faker = new Faker();
        this.paymentSystemId = getRandomPaymentType();
        this.currency = getRandomPaymentSystemInfo(this.paymentSystemId).getCurrency().getFirst();
        this.isDefault = new Random().nextBoolean();

        Map<String, Boolean> requisitesTitle = getRandomPaymentSystemInfo(this.paymentSystemId).getFields();
        for (Map.Entry<String, Boolean> field : requisitesTitle.entrySet()) {
            this.requisites.put(field.getKey(), faker.oscarMovie().movieName());
        }
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getPaymentSystemId() {
        return paymentSystemId;
    }

    public void setPaymentSystemId(int paymentSystemId) {
        this.paymentSystemId = paymentSystemId;
    }

    public String getPaymentSystemTitle() {
        return paymentSystemTitle;
    }

    public void setPaymentSystemTitle(String paymentSystemTitle) {
        this.paymentSystemTitle = paymentSystemTitle;
    }

    public Map<String, String> getRequisites() {
        return requisites;
    }

    public void setRequisites(Map<String, String> requisites) {
        this.requisites = requisites;
    }

    public int getRequisitesId() {
        return requisitesId;
    }

    public void setRequisitesId(int requisitesId) {
        this.requisitesId = requisitesId;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
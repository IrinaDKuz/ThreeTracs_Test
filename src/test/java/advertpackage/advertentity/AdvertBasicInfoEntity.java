package advertpackage.advertentity;

import net.datafaker.Faker;

import java.util.*;

import static adminpackage.GetAdmin.getRandomSpecificEnableAdmin;
import static helper.ConstantsName.MODEL_TYPES_MAP;
import static helper.GeoHelper.GEO_MAP;
import static helper.GeoHelper.getRandomKey;
import static settingspackage.GetCategory.getRandomCategory;
import static settingspackage.GetTag.getRandomTag;


public class AdvertBasicInfoEntity { //поля, которые заполняются на адверта *
    int id;
    String name;
    String companyLegalName;
    String status;
    Set<Integer> categories = new HashSet<>();
    List<String> geo = new ArrayList<>();
    List<String> pricingModel = new ArrayList<>();
    String note;
    int managerId;
    int accountManager;
    int salesManager;
    String siteUrl;
    Set<Integer> tagsId = new HashSet<>();
    int userRequestSource;
   String specificSecondName = "FOR EXPORT AFFISE";


    public void generateMinFields() {
        Faker faker = new Faker(); //создаем новую сущность (факер - это библиотека рандомных значений)
       // this.status = getRandomKey(ADVERT_STATUS_MAP);
        this.status = "active";
        this.name = faker.animal().scientificName();
        this.managerId = getRandomSpecificEnableAdmin(specificSecondName);
    }

    public void generateMaxFields() {
        Faker faker = new Faker();
        this.generateMinFields();

        int count = new Random().nextInt(5) + 1;
        for (int i = 0; i < count; i++) {
            this.categories.add(getRandomCategory());
        }

        for (int i = 0; i < count; i++) {
            this.geo.add(getRandomKey(GEO_MAP));
        }

        for (int i = 0; i < count; i++) {
            this.tagsId.add(getRandomTag());
        }

        this.pricingModel = Arrays.asList(getRandomKey(MODEL_TYPES_MAP));
        this.note = faker.lorem().paragraph();
        this.accountManager = getRandomSpecificEnableAdmin(specificSecondName);
        this.salesManager = getRandomSpecificEnableAdmin(specificSecondName);
        this.userRequestSource = getRandomSpecificEnableAdmin(specificSecondName);
        // this.siteUrl = faker.internet().url();
        this.siteUrl = "https://docs.google.com/" + faker.number().randomNumber();
        this.companyLegalName = faker.pokemon().name() + faker.number().randomNumber() + "@mailto.plus";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyLegalName() {
        return companyLegalName;
    }

    public void setCompanyLegalName(String companyLegalName) {
        this.companyLegalName = companyLegalName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Integer> getCategories() {
        return categories;
    }

    public void setCategories(Set<Integer> categories) {
        this.categories = categories;
    }

    public List<String> getGeo() {
        return geo;
    }

    public void setGeo(List<String> geo) {
        this.geo = geo;
    }

    public List<String> getPricingModel() {
        return pricingModel;
    }

    public void setPricingModel(List<String> pricingModel) {
        this.pricingModel = pricingModel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getAccountManager() {
        return accountManager;
    }

    public void setAccountManager(int accountManager) {
        this.accountManager = accountManager;
    }

    public int getSalesManager() {
        return salesManager;
    }

    public void setSalesManager(int salesManager) {
        this.salesManager = salesManager;
    }

    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public Set<Integer> getTagsId() {
        return tagsId;
    }

    public void setTagsId(Set<Integer> tagsId) {
        this.tagsId = tagsId;
    }

    public int getUserRequestSource() {
        return userRequestSource;
    }

    public void setUserRequestSource(int userRequestSource) {
        this.userRequestSource = userRequestSource;
    }
}
package advertpackage.advertentity;

import java.util.*;

import net.datafaker.Faker;

import static adminpackage.GetAdmin.getRandomEnableAdmin;
import static helper.ConstantsName.ADVERT_STATUS_MAP;
import static helper.ConstantsName.MODEL_TYPES_MAP;
import static helper.GeoHelper.*;
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


    public void generateMinFields() {
        Faker faker = new Faker(); //создаем новую сущность (факер - это библиотека рандомных значений)
        this.status = getRandomKey(ADVERT_STATUS_MAP);
        this.name = faker.animal().scientificName(); //пароль для нового админа
        this.managerId = getRandomEnableAdmin();
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
        this.note = faker.text().toString();
        this.accountManager = getRandomEnableAdmin();
        this.salesManager = getRandomEnableAdmin();
        this.userRequestSource = getRandomEnableAdmin();
        this.siteUrl = faker.internet().url();
        this.companyLegalName = faker.artist().name();
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
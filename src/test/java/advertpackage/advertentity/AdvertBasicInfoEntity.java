package advertpackage.advertentity;
import java.util.List;
import java.util.Set;

import net.datafaker.Faker;

import static adminpackage.GetAdmin.getRandomEnableAdmin;


public class AdvertBasicInfoEntity { //поля, которые заполняются на адверта *
    int id;
    String name;
    String companyLegalName;
    String status;
    Set<Integer> categories;
    List <String> geo;
    List <String> pricingModel;
    String note;
    int managerId;
    int accountManager;
    int salesManager;
    String siteUrl;
    Set<Integer> tagsId;
    int userRequestSource;


    public void generateMinFields() {
        Faker faker = new Faker(); //создаем новую сущность (факер - это библиотека рандомных значений)
        this.status = "enabled"; //ставим статус энейбл
        this.name = faker.animal().scientificName(); //пароль для нового админа
        this.managerId = getRandomEnableAdmin();
    }

    public void generateMaxFields() {
        Faker faker = new Faker(); //создаем новую сущность (факер - это библиотека рандомных значений)
        this.generateMinFields();
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
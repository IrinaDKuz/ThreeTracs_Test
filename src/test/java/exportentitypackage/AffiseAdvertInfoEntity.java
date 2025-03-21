package exportentitypackage;

import net.datafaker.Faker;

import java.util.*;

import static adminpackage.GetAdmin.getRandomEnableAdmin;
import static helper.ConstantsName.ADVERT_STATUS_MAP;
import static helper.ConstantsName.MODEL_TYPES_MAP;
import static helper.GeoHelper.GEO_MAP;
import static helper.GeoHelper.getRandomKey;
import static settingspackage.GetCategory.getRandomCategory;
import static settingspackage.GetTag.getRandomTag;


public class AffiseAdvertInfoEntity { //поля, которые заполняются на адверта *
    String id;
    String title;
    String siteUrl;
    String manager;
    String managerId;
    String note;
    Set<String> tagsId = new HashSet<>();
    String contact;
    String email;
    String skype;
    String address_1;
    String address_2;
    String city;
    String country;
    String zip_code;
    String vat_code;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String managerId) {
        this.manager = manager;
    }


    public String getSiteUrl() {
        return siteUrl;
    }

    public void setSiteUrl(String siteUrl) {
        this.siteUrl = siteUrl;
    }

    public Set<String> getTagsId() {
        return tagsId;
    }

    public void setTagsId(Set<String> tagsId) {
        this.tagsId = tagsId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getAddress_1() {
        return address_1;
    }

    public void setAddress_1(String address_1) {
        this.address_1 = address_1;
    }

    public String getAddress_2() {
        return address_2;
    }

    public void setAddress_2(String address_2) {
        this.address_2 = address_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getVat_code() {
        return vat_code;
    }

    public void setVat_code(String vat_code) {
        this.vat_code = vat_code;
    }

}
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
    Set<Integer> tagsId = new HashSet<>();

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

    public Set<Integer> getTagsId() {
        return tagsId;
    }

    public void setTagsId(Set<Integer> tagsId) {
        this.tagsId = tagsId;
    }

}
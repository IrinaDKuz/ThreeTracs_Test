package settingspackage.settingsentity;

import java.util.List;
import java.util.Map;


public class PaymentInfoEntity {
    Integer id;
    String title;
    List<String> currency;
    Map<String, Boolean> fields;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCurrency() {
        return currency;
    }

    public void setCurrency(List<String> currency) {
        this.currency = currency;
    }

    public Map<String, Boolean> getFields() {
        return fields;
    }

    public void setFields(Map<String, Boolean> fields) {
        this.fields = fields;
    }
}
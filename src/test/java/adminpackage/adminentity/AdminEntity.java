package adminpackage.adminentity;
import net.datafaker.Faker;


public class AdminEntity { //поля, которые заполняются на админа
    int id;
    String email;
    String status;
    Role role;
    String firstName;
    String secondName;
    String skype;
    String telegram;
    String phone;
    String lastLoginIp;
    String lastLoginDt;
    String workingHours;
    String updatedAt;
    String createdAt;

    String password;

    public class Role {
        int value;
        String label;
    }

    public void generateFields() { //генерирует случайное заполнение полей
      //  https://fakerjs.dev/api/person.html
        Faker faker = new Faker(); //создаем новую сущность (факер - это библиотека рандомных значений)
        this.status = "enabled"; //ставим статус энейбл
       // this.role = "enable"; TODO: Сделать роль
        this.password = "password"; //пароль для нового админа
        this.firstName = "NEW_EXPORT_E" + faker.name().firstName(); // генерим рандомное имя
        this.secondName = "NEW_EXPORT_E" + faker.name().lastName(); // генерим рандомное имя
        this.email =  faker.internet().emailAddress(this.firstName + this.secondName); //геерим рандомную почту
        this.skype = "live:" + faker.dessert().flavor(); //генерим почту
        this.telegram = "@" + faker.dessert().topping(); // генерим почту
        this.phone = faker.phoneNumber().cellPhone(); //генерим номер телефона
        this.workingHours = "10:00 - 18:00"; //время работы - захардкожено
    }

    public int getId() { //ЗАБЫЛ ДЛЯ ЧЕГО ЭТО
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getTelegram() {
        return telegram;
    }

    public void setTelegram(String telegram) {
        this.telegram = telegram;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Object getLastLoginDt() {
        return lastLoginDt;
    }

    public void setLastLoginDt(String lastLoginDt) {
        this.lastLoginDt = lastLoginDt;
    }

    public String getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(String workingHours) {
        this.workingHours = workingHours;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
package helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeoHelper {


    public static String getGeoRandomValue() {
        return getRandomValue(GEO_MAP);
    }

    public static String getRandomValue(Map<String, String> map) {
        return map.get(getRandomKey(map));
    }

    public static String getRandomKey(Map<String, String> map) {
        // Получаем массив ключей
        Object[] keys = map.keySet().toArray();
        // Выбираем случайный ключ из массива ключей
        return keys[new Random().nextInt(keys.length)].toString();
    }




    public static Map<String, String> GEO_MAP = Stream.of(new String[][]
            {
                    // {"all", "All"},
                    {"af", "Afghanistan"},
                    {"al", "Albania"},
                    {"dz", "Algeria"},
                    {"ad", "Andorra"},
                    {"ao", "Angola"}, {
                    "ag", "Antigua and barbuda"}, {
                    "ar", "Argentina"}, {
                    "am", "Armenia"}, {
                    "au", "Australia"}, {
                    "at", "Austria"}, {
                    "az", "Azerbaijan"}, {
                    "bs", "Bahamas"}, {
                    "bh", "Bahrain"}, {
                    "bd", "Bangladesh"}, {
                    "bb", "Barbados"}, {
                    "by", "Belarus"}, {
                    "be", "Belgium"}, {
                    "bz", "Belize"}, {
                    "bj", "Benin"}, {
                    "bt", "Bhutan"}, {
                    "bo", "Bolivia"}, {
                    "ba", "Bosnia and herzegovina"}, {
                    "bw", "Botswana"}, {
                    "br", "Brazil"}, {
                    "bn", "Brunei"}, {
                    "bg", "Bulgaria"}, {
                    "bf", "Burkina faso"}, {
                    "bi", "Burundi"}, {
                    "ci", "Cote divoire"}, {
                    "cv", "Cabo verde"}, {
                    "kh", "Cambodia"}, {
                    "cm", "Cameroon"}, {
                    "ca", "Canada"}, {
                    "cf", "Central african republic"}, {
                    "td", "Chad"}, {
                    "cl", "Chile"}, {
                    "cn", "China"}, {
                    "co", "Colombia"}, {
                    "km", "Comoros"}, {
                    "cr", "Costa rica"}, {
                    "hr", "Croatia"}, {
                    "cu", "Cuba"}, {
                    "cy", "Cyprus"}, {
                    "cz", "Czechia"}, {
                    "cd", "Democratic republic of the congo"}, {
                    "dk", "Denmark"}, {
                    "dj", "Djibouti"}, {
                    "dm", "Dominica"}, {
                    "do", "Dominican republic"}, {
                    "ec", "Ecuador"}, {
                    "eg", "Egypt"}, {
                    "sv", "El salvador"}, {
                    "gq", "Equatorial guinea"}, {
                    "er", "Eritrea"}, {
                    "ee", "Estonia"}, {
                    "sz", "Eswatini"}, {
                    "et", "Ethiopia"}, {
                    "fj", "Fiji"}, {
                    "fi", "Finland"}, {
                    "fr", "France"}, {
                    "ga", "Gabon"}, {
                    "gm", "Gambia"}, {
                    "ge", "Georgia"}, {
                    "de", "Germany"}, {
                    "gh", "Ghana"}, {
                    "gr", "Greece"}, {
                    "gd", "Grenada"}, {
                    "gt", "Guatemala"}, {
                    "gn", "Guinea"}, {
                    "gw", "Guinea bissau"}, {
                    "gf", "Guyana"}, {
                    "ht", "Haiti"}, {
                    "va", "Holy see"}, {
                    "hn", "Honduras"}, {
                    "hk", "Hongkong"}, {
                    "hu", "Hungary"}, {
                    "is", "Iceland"}, {
                    "in", "India"}, {
                    "id", "Indonesia"}, {
                    "ir", "Iran"}, {
                    "iq", "Iraq"}, {
                    "ie", "Ireland"}, {
                    "il", "Israel"}, {
                    "it", "Italy"}, {
                    "jm", "Jamaica"}, {
                    "jp", "Japan"}, {
                    "jo", "Jordan"}, {
                    "kz", "Kazakhstan"}, {
                    "ke", "Kenya"}, {
                    "ki", "Kiribati"}, {
                    "kw", "Kuwait"}, {
                    "kg", "Kyrgyzstan"}, {
                    "la", "Laos"}, {
                    "lv", "Latvia"}, {
                    "lb", "Lebanon"}, {
                    "ls", "Lesotho"}, {
                    "lr", "Liberia"}, {
                    "ly", "Libya"}, {
                    "li", "Liechtenstein"}, {
                    "lt", "Lithuania"}, {
                    "lu", "Luxembourg"}, {
                    "mg", "Madagascar"}, {
                    "mw", "Malawi"}, {
                    "my", "Malaysia"}, {
                    "mv", "Maldives"}, {
                    "ml", "Mali"}, {
                    "mt", "Malta"}, {
                    "mh", "Marshall islands"}, {
                    "mr", "Mauritania"}, {
                    "mu", "Mauritius"}, {
                    "mx", "Mexico"}, {
                    "fm", "Micronesia"}, {
                    "md", "Moldova"}, {
                    "mc", "Monaco"}, {
                    "mn", "Mongolia"}, {
                    "me", "Montenegro"}, {
                    "ma", "Morocco"}, {
                    "mz", "Mozambique"}, {
                    "mm", "Myanmar"}, {
                    "na", "Namibia"}, {
                    "nr", "Nauru"}, {
                    "np", "Nepal"}, {
                    "nl", "Netherlands"}, {
                    "nz", "New zealand"}, {
                    "ni", "Nicaragua"}, {
                    "ne", "Niger"}, {
                    "ng", "Nigeria"}, {
                    "kp", "North korea"}, {
                    "mk", "North macedonia"}, {
                    "no", "Norway"}, {
                    "om", "Oman"}, {
                    "pk", "Pakistan"}, {
                    "pw", "Palau"}, {
                    "ps", "Palestine state"}, {
                    "pa", "Panama"}, {
                    "pg", "Papua new guinea"}, {
                    "py", "Paraguay"}, {
                    "pe", "Peru"}, {
                    "ph", "Philippines"}, {
                    "pl", "Poland"}, {
                    "pt", "Portugal"}, {
                    "qa", "Qatar"}, {
                    "ro", "Romania"}, {
                    "ru", "Russia"}, {
                    "rw", "Rwanda"}, {
                    "kn", "Saint kitts and nevis"}, {
                    "lc", "Saint lucia"}, {
                    "vc", "Saint vincent and the grenadines"}, {
                    "ws", "Samoa"}, {
                    "sm", "San marino"}, {
                    "st", "Sao tome and principe"}, {
                    "sa", "Saudi arabia"}, {
                    "sn", "Senegal"}, {
                    "rs", "Serbia"}, {
                    "sc", "Seychelles"}, {
                    "sl", "Sierra leone"}, {
                    "sg", "Singapore"}, {
                    "sk", "Slovakia"}, {
                    "si", "Slovenia"}, {
                    "sb", "Solomon islands"}, {
                    "so", "Somalia"}, {
                    "za", "South africa"}, {
                    "kr", "South korea"}, {
                    "ss", "South sudan"}, {
                    "es", "Spain"}, {
                    "lk", "Sri lanka"}, {
                    "sd", "Sudan"}, {
                    "sr", "Suriname"}, {
                    "se", "Sweden"}, {
                    "ch", "Switzerland"}, {
                    "sy", "Syria"}, {
                    "tj", "Tajikistan"}, {
                    "tz", "Tanzania"}, {
                    "th", "Thailand"}, {
                    "tl", "Timor leste"}, {
                    "tg", "Togo"}, {
                    "to", "Tonga"}, {
                    "tt", "Trinidad and tobago"}, {
                    "tn", "Tunisia"}, {
                    "tr", "Turkey"}, {
                    "tm", "Turkmenistan"}, {
                    "tv", "Tuvalu"}, {
                    "ug", "Uganda"}, {
                    "ua", "Ukraine"}, {
                    "ae", "United arab emirates"}, {
                    "gb", "United kingdom"}, {
                    "us", "United states of america"}, {
                    "uy", "Uruguay"}, {
                    "uz", "Uzbekistan"}, {
                    "vu", "Vanuatu"}, {
                    "ve", "Venezuela"}, {
                    "vn", "Vietnam"}, {
                    "ye", "Yemen"}, {
                    "zm", "Zambia"}, {
                    "zw", "Zimbabwe"}
            }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

}


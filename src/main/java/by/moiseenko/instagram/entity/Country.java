package by.moiseenko.instagram.entity;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Country {

    private Integer id;
    private String name;
    private Iterable<City> cities;

    private Country() {}

    public static CountryBuilder builder() {
        return new Country().new CountryBuilder();
    }

    public class CountryBuilder {

        private CountryBuilder() {}

        public CountryBuilder id(Integer id) {
            Country.this.id = id;

            return this;
        }

        public CountryBuilder name(String name) {
            Country.this.name = name;

            return this;
        }

        public CountryBuilder cities(Iterable<City> cities) {
            Country.this.cities = cities;

            return this;
        }

        public Country build() {
            return Country.this;
        }
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


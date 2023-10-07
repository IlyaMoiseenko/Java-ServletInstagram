package by.moiseenko.instagram.entity;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class City {

    private int id;
    private String name;
    private Country country;

    private City() {}

    public static CityBuilder builder() {
        return new City().new CityBuilder();
    }

    public class CityBuilder {

        private CityBuilder() {}

        public CityBuilder id(int id) {
            City.this.id = id;

            return this;
        }

        public CityBuilder name(String name) {
            City.this.name = name;

            return this;
        }

        public CityBuilder country(Country country) {
            City.this.country = country;

            return this;
        }

        public City build() {
            return City.this;
        }
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                '}';
    }
}


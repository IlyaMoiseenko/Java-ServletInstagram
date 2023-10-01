package by.moiseenko.instagram.model;

/*
    @author Ilya Moiseenko on 23.09.23
*/

import java.util.List;

public class Country {

    private int id;
    private String name;
    private List<City> cities;

    public CountryBuilder builder() {
        return new Country.CountryBuilder();
    }

    public class CountryBuilder {

        private int id;
        private String name;
        private List<City> cities;

        public CountryBuilder id(int id) {
            this.id = id;

            return this;
        }

        public CountryBuilder name(String name) {
            this.name = name;

            return this;
        }

        public CountryBuilder cities(List<City> cities) {
            this.cities = cities;

            return this;
        }

        public Country build() {
            return Country.this;
        }
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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


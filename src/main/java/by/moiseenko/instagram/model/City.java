package by.moiseenko.instagram.model;

/*
    @author Ilya Moiseenko on 23.09.23
*/

public class City {

    private int id;
    private String name;
    private Country country;

    public CityBuilder builder() {
        return new City.CityBuilder();
    }

    public class CityBuilder {

        private int id;
        private String name;
        private Country country;

        public CityBuilder id(int id) {
            this.id = id;

            return this;
        }

        public CityBuilder name(String name) {
            this.name = name;

            return this;
        }

        public CityBuilder country(Country country) {
            this.country = country;

            return this;
        }

        public City build() {
            return City.this;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
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


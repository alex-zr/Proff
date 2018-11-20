package com.gmail.vladgural.jpahw1;

import javax.persistence.*;

@Entity
@Table(name = "menu_of_restaurant")
public class MenuOfRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name_of_dish", nullable = false)
    private String nameOfDish;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "weight", nullable = false)
    private Double weight;

    @Column(name = "discount", nullable = false)
    private Boolean discount;

    public MenuOfRestaurant() {

    }

    public MenuOfRestaurant(String nameOfDish, Double price, Double weight, Boolean discount) {
        this.nameOfDish = nameOfDish;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameOfDish() {
        return nameOfDish;
    }

    public void setNameOfDish(String nameOfDish) {
        this.nameOfDish = nameOfDish;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "MenuOfRestaurant{" +
                "id=" + id +
                ", nameOfDish='" + nameOfDish + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}

package ua.kiev.prog.jpa.sample1;

import javax.persistence.*;

@Entity
@Table(name = "menu")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "dish", nullable = false)
    private String dishName;

    @Column(name = "price", nullable = false)
    private Double dishPrice;

    @Column(name = "weight", nullable = false)
    private Double dishWeight;

    @Column(name = "discount", nullable = false)
    private Double dishDiscount;

    public Dish(String dishName, Double dishPrice, Double dishWeight, Double dishDiscount) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.dishWeight = dishWeight;
        this.dishDiscount = dishDiscount;
    }

    public Dish() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Double getDishDiscount() {
        return dishDiscount;
    }

    public void setDishDiscount(Double dishDiscount) {
        this.dishDiscount = dishDiscount;
    }

    public Double getDishPrice() {
        return dishPrice;
    }

    public void setDishPrice(Double dishPrice) {
        this.dishPrice = dishPrice;
    }

    public Double getDishWeight() {
        return dishWeight;
    }

    public void setDishWeight(Double dishWeight) {
        this.dishWeight = dishWeight;
    }

    @Override
    public String toString() {
        return "Dish:" + dishName + System.lineSeparator() + "Price:" + dishPrice + " UAH" + System.lineSeparator()
                + "Weight:" + dishWeight + " g" + System.lineSeparator() + "Discount:" + dishDiscount + " %" + System.lineSeparator();
    }
}

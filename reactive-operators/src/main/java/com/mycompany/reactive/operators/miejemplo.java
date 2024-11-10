package com.mycompany.reactive.operators;

import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;

class Order {
    private final String product;
    private final int quantity;
    private final double price;

    // Constructor de la clase Order
    public Order(String product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters para obtener los atributos del producto
    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

public class miejemplo {
    public static void main(String[] args) {
        // Lista de pedidos con productos, cantidad y precio
        List<Order> orders = Arrays.asList(
            new Order("Product A", 2, 50.0),  // Total = 100.0
            new Order("Product B", 1, 30.0),  // Total = 30.0
            new Order("Product A", 1, 50.0),  // Total = 50.0
            new Order("Product C", 3, 20.0)   // Total = 60.0
        );

        // Umbral de ventas para filtrar los pedidos
        double salesThreshold = 50.0;

        // Flujo reactivo de procesamiento de pedidos
        Observable.fromIterable(orders)  // Crear un Observable a partir de la lista de pedidos
                .filter(order -> order.getQuantity() * order.getPrice() > salesThreshold)  // Filtrar los pedidos cuyo total sea mayor al umbral
                .map(order -> {
                    double total = order.getQuantity() * order.getPrice();  // Calcular el total de cada pedido filtrado
                    System.out.println("Product: " + order.getProduct() + ", Total: " + total);  // Imprimir el producto y su total
                    return total;  // Devolver el total para procesarlo en los siguientes operadores
                })
                .reduce(Double::sum)  // Sumar el total de ventas de los pedidos filtrados
                .subscribe(total -> System.out.println("Total sales over threshold: " + total));  // Imprimir el total final de ventas
    }
}

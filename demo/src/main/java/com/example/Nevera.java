package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Nevera {
    // Atributos privados (8 atributos)
    private String marca;
    private String modelo;
    private double capacidadLitros;
    private boolean tieneCongelador;
    private String clasificacionEnergetica;
    private BigDecimal precio;
    private LocalDate fechaFabricacion;
    private int garantiaMeses;
    private String color; // Nuevo atributo adicional
    
    // Constructor vacío/por defecto
    public Nevera() {
        this.marca = "Genérica";
        this.modelo = "Estándar";
        this.capacidadLitros = 350.0;
        this.tieneCongelador = true;
        this.clasificacionEnergetica = "A+";
        this.precio = new BigDecimal("599.99");
        this.fechaFabricacion = LocalDate.now().minusMonths(6);
        this.garantiaMeses = 24;
        this.color = "Blanco";
    }
    
    // Constructor con parámetros usando setters
    public Nevera(String marca, String modelo, double capacidadLitros, boolean tieneCongelador,
                 String clasificacionEnergetica, BigDecimal precio, LocalDate fechaFabricacion,
                 int garantiaMeses, String color) {
        setMarca(marca);
        setModelo(modelo);
        setCapacidadLitros(capacidadLitros);
        setTieneCongelador(tieneCongelador);
        setClasificacionEnergetica(clasificacionEnergetica);
        setPrecio(precio);
        setFechaFabricacion(fechaFabricacion);
        setGarantiaMeses(garantiaMeses);
        setColor(color);
    }
    
    // Getters y Setters con validaciones
    public String getMarca() {
        return marca;
    }
    
    public void setMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacía");
        }
        if (marca.length() > 50) {
            throw new IllegalArgumentException("La marca no puede exceder 50 caracteres");
        }
        this.marca = marca.trim();
    }
    
    public String getModelo() {
        return modelo;
    }
    
    public void setModelo(String modelo) {
        if (modelo == null || modelo.trim().isEmpty()) {
            throw new IllegalArgumentException("El modelo no puede estar vacío");
        }
        if (modelo.length() > 50) {
            throw new IllegalArgumentException("El modelo no puede exceder 50 caracteres");
        }
        this.modelo = modelo.trim();
    }
    
    public double getCapacidadLitros() {
        return capacidadLitros;
    }
    
    public void setCapacidadLitros(double capacidadLitros) {
        if (capacidadLitros <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor a 0 litros");
        }
        if (capacidadLitros > 1000) {
            throw new IllegalArgumentException("La capacidad no puede exceder 1000 litros");
        }
        this.capacidadLitros = Math.round(capacidadLitros * 10.0) / 10.0; // 1 decimal
    }
    
    public boolean isTieneCongelador() {
        return tieneCongelador;
    }
    
    public void setTieneCongelador(boolean tieneCongelador) {
        this.tieneCongelador = tieneCongelador;
    }
    
    public String getClasificacionEnergetica() {
        return clasificacionEnergetica;
    }
    
    public void setClasificacionEnergetica(String clasificacionEnergetica) {
        if (clasificacionEnergetica == null || clasificacionEnergetica.trim().isEmpty()) {
            throw new IllegalArgumentException("La clasificación energética no puede estar vacía");
        }
        String[] clasificacionesValidas = {"A+++", "A++", "A+", "A", "B", "C", "D", "E", "F", "G"};
        boolean valida = false;
        for (String c : clasificacionesValidas) {
            if (c.equals(clasificacionEnergetica.trim())) {
                this.clasificacionEnergetica = c;
                valida = true;
                break;
            }
        }
        if (!valida) {
            throw new IllegalArgumentException("Clasificación energética inválida. Use: " + 
                                              String.join(", ", clasificacionesValidas));
        }
    }
    
    public BigDecimal getPrecio() {
        return precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        if (precio == null) {
            throw new IllegalArgumentException("El precio no puede ser nulo");
        }
        if (precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor a 0");
        }
        if (precio.compareTo(new BigDecimal("10000")) > 0) {
            throw new IllegalArgumentException("El precio no puede exceder $10,000");
        }
        this.precio = precio.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }
    
    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        if (fechaFabricacion == null) {
            throw new IllegalArgumentException("La fecha de fabricación no puede ser nula");
        }
        if (fechaFabricacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de fabricación no puede ser futura");
        }
        if (fechaFabricacion.getYear() < 2010) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a 2010");
        }
        this.fechaFabricacion = fechaFabricacion;
    }
    
    public int getGarantiaMeses() {
        return garantiaMeses;
    }
    
    public void setGarantiaMeses(int garantiaMeses) {
        if (garantiaMeses < 12) {
            throw new IllegalArgumentException("La garantía mínima es de 12 meses");
        }
        if (garantiaMeses > 60) {
            throw new IllegalArgumentException("La garantía no puede exceder 60 meses");
        }
        this.garantiaMeses = garantiaMeses;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("El color no puede estar vacío");
        }
        String[] coloresValidos = {"Blanco", "Negro", "Plateado", "Gris", "Acero Inoxidable", "Rojo", "Azul"};
        boolean valido = false;
        for (String c : coloresValidos) {
            if (c.equalsIgnoreCase(color.trim())) {
                this.color = c;
                valido = true;
                break;
            }
        }
        if (!valido) {
            throw new IllegalArgumentException("Color no válido. Use: " + String.join(", ", coloresValidos));
        }
    }
    
    @Override
    public String toString() {
        return String.format("Nevera [Marca: %s, Modelo: %s, Capacidad: %.1f L, Congelador: %s, " +
                           "Clasificación: %s, Precio: $%s, Fabricación: %s, " +
                           "Garantía: %d meses, Color: %s]",
                           marca, modelo, capacidadLitros, tieneCongelador ? "Sí" : "No", 
                           clasificacionEnergetica, precio.toString(), fechaFabricacion, 
                           garantiaMeses, color);
    }
}
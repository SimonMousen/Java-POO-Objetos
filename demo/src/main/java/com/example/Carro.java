package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;

public class Carro {
    // Atributos privados (9 atributos)
    private String marca;
    private String modelo;
    private int año;
    private String placa;
    private double kilometraje;
    private String tipoCombustible;
    private BigDecimal precio;
    private boolean automatico;
    private LocalDate fechaMatriculacion;
    private String color; // Nuevo atributo adicional
    
    // Constructor vacío/por defecto
    public Carro() {
        this.marca = "Genérica";
        this.modelo = "Sedán";
        this.año = LocalDate.now().getYear() - 3;
        this.placa = "ABC-123";
        this.kilometraje = 25000.5;
        this.tipoCombustible = "Gasolina";
        this.precio = new BigDecimal("15000.00");
        this.automatico = true;
        this.fechaMatriculacion = LocalDate.now().minusYears(2);
        this.color = "Negro";
    }
    
    // Constructor con parámetros usando setters
    public Carro(String marca, String modelo, int año, String placa, double kilometraje,
                String tipoCombustible, BigDecimal precio, boolean automatico, 
                LocalDate fechaMatriculacion, String color) {
        setMarca(marca);
        setModelo(modelo);
        setAño(año);
        setPlaca(placa);
        setKilometraje(kilometraje);
        setTipoCombustible(tipoCombustible);
        setPrecio(precio);
        setAutomatico(automatico);
        setFechaMatriculacion(fechaMatriculacion);
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
        if (marca.length() > 30) {
            throw new IllegalArgumentException("La marca no puede exceder 30 caracteres");
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
    
    public int getAño() {
        return año;
    }
    
    public void setAño(int año) {
        int añoActual = LocalDate.now().getYear();
        int añoMinimo = 1990;
        
        if (año < añoMinimo) {
            throw new IllegalArgumentException("El año no puede ser anterior a " + añoMinimo);
        }
        if (año > añoActual + 1) { // Permite modelos del próximo año
            throw new IllegalArgumentException("El año no puede ser mayor a " + (añoActual + 1));
        }
        this.año = año;
    }
    
    public String getPlaca() {
        return placa;
    }
    
    public void setPlaca(String placa) {
        if (placa == null || placa.trim().isEmpty()) {
            throw new IllegalArgumentException("La placa no puede estar vacía");
        }
        // Validación básica de formato (ej: ABC-123 o ABC123)
        String placaLimpia = placa.trim().toUpperCase().replaceAll("[^A-Z0-9]", "");
        
        if (placaLimpia.length() < 5 || placaLimpia.length() > 7) {
            throw new IllegalArgumentException("Formato de placa inválido. Debe tener entre 5-7 caracteres");
        }
        
        // Verificar que tenga al menos 3 letras y 2 números
        int letras = 0;
        int numeros = 0;
        
        for (char c : placaLimpia.toCharArray()) {
            if (Character.isLetter(c)) {
                letras++;
            } else if (Character.isDigit(c)) {
                numeros++;
            }
        }
        
        if (letras < 3 || numeros < 2) {
            throw new IllegalArgumentException("La placa debe tener al menos 3 letras y 2 números");
        }
        
        this.placa = placa.trim().toUpperCase();
    }
    
    public double getKilometraje() {
        return kilometraje;
    }
    
    public void setKilometraje(double kilometraje) {
        if (kilometraje < 0) {
            throw new IllegalArgumentException("El kilometraje no puede ser negativo");
        }
        if (kilometraje > 1000000) {
            throw new IllegalArgumentException("El kilometraje no puede exceder 1,000,000 km");
        }
        this.kilometraje = Math.round(kilometraje * 10.0) / 10.0; // 1 decimal
    }
    
    public String getTipoCombustible() {
        return tipoCombustible;
    }
    
    public void setTipoCombustible(String tipoCombustible) {
        if (tipoCombustible == null || tipoCombustible.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de combustible no puede estar vacío");
        }
        String[] tiposValidos = {"Gasolina", "Diesel", "Eléctrico", "Híbrido", "Gas Natural", "Hidrógeno"};
        boolean valido = false;
        for (String t : tiposValidos) {
            if (t.equalsIgnoreCase(tipoCombustible.trim())) {
                this.tipoCombustible = t;
                valido = true;
                break;
            }
        }
        if (!valido) {
            throw new IllegalArgumentException("Tipo de combustible no válido. Use: " + 
                                              String.join(", ", tiposValidos));
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
        if (precio.compareTo(new BigDecimal("500000")) > 0) {
            throw new IllegalArgumentException("El precio no puede exceder $500,000");
        }
        this.precio = precio.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    public boolean isAutomatico() {
        return automatico;
    }
    
    public void setAutomatico(boolean automatico) {
        this.automatico = automatico;
    }
    
    public LocalDate getFechaMatriculacion() {
        return fechaMatriculacion;
    }
    
    public void setFechaMatriculacion(LocalDate fechaMatriculacion) {
        if (fechaMatriculacion == null) {
            throw new IllegalArgumentException("La fecha de matriculación no puede ser nula");
        }
        if (fechaMatriculacion.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de matriculación no puede ser futura");
        }
        if (fechaMatriculacion.getYear() < 1990) {
            throw new IllegalArgumentException("La fecha no puede ser anterior a 1990");
        }
        this.fechaMatriculacion = fechaMatriculacion;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setColor(String color) {
        if (color == null || color.trim().isEmpty()) {
            throw new IllegalArgumentException("El color no puede estar vacío");
        }
        if (color.length() > 20) {
            throw new IllegalArgumentException("El color no puede exceder 20 caracteres");
        }
        this.color = color.trim();
    }
    
    // Método adicional para calcular antigüedad
    public int getAntiguedad() {
        return LocalDate.now().getYear() - año;
    }
    
    // Método adicional para determinar si es nuevo (menos de 1000 km)
    public boolean esNuevo() {
        return kilometraje < 1000;
    }
    
    @Override
    public String toString() {
        return String.format("Carro [Marca: %s, Modelo: %s, Año: %d, Placa: %s, " +
                           "Kilometraje: %.1f km, Combustible: %s, Precio: $%s, " +
                           "Transmisión: %s, Matriculación: %s, Color: %s, Antigüedad: %d años]",
                           marca, modelo, año, placa, kilometraje, tipoCombustible, 
                           precio.toString(), automatico ? "Automática" : "Manual", 
                           fechaMatriculacion, color, getAntiguedad());
    }
}
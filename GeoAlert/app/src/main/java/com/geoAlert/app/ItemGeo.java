package com.geoAlert.app;

public class ItemGeo {

    protected long id;
    protected String nombre;
    protected String direccion;
    protected String latitud;
    protected String longitud;
    protected String tipo;
    protected String prioridad;

    public ItemGeo() {
        this.nombre = "";
        this.direccion="";
        this.latitud="";
        this.longitud="";
        this.tipo="";
        this.prioridad="";
    }

    public ItemGeo(long id, String nombre,String direccion,String latitud, String longitud, String tipo, String prioridad) {
        this.id=id;
        this.nombre = nombre;
        this.direccion=direccion;
        this.latitud=latitud;
        this.longitud=longitud;
        this.tipo=tipo;
        this.prioridad=prioridad;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }


}
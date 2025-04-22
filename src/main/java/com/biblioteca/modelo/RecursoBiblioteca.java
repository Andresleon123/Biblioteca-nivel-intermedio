package com.biblioteca.modelo;

public abstract class RecursoBiblioteca {//No se puede instansiar o ya se encuentra extendida por otra clase
    protected String nombre;//Accesibole dentro de una misma clase, en la supclasse
    protected String autor;
    protected int fechaPublicacion;

    public RecursoBiblioteca(String nombre, String autor, int fechaPublicacion) {
        //Esto se refiere a la variable de instancia
        this.nombre = nombre;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
    }
    /*clase base para otros recursos como libros, revistas.
    COn esto podriamops agregar ya sea una revista articulo ya que siemrpre se necesita
    nombre, autor y el año de la publicacion
*/
    public String getNombre() {
        return nombre;
    }

    public String getAutor() {
        return autor;
    }

    public int getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setFechaPublicacion(int fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Autor: " + autor + ", Año: " + fechaPublicacion;
    }
}
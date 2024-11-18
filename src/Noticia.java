public class Noticia {
    private String titulo;
    private String fechaPub;
    private String autor;
    private String link;


    public Noticia() {
    }

    public Noticia(String titulo, String fechaPub, String autor, String link) {
        this.titulo = titulo;
        this.fechaPub = fechaPub;
        this.autor = autor;
        this.link = link;
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaPub() {
        return fechaPub;
    }

    public void setFechaPub(String fechaPub) {
        this.fechaPub = fechaPub;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Título: " + titulo + "\nFecha: " + fechaPub + "\nAutor: " + autor + "\nEnlace: " + link + "\n";
    }
}

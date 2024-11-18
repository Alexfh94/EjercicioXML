import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssParser {
    /*
    Método que convierte el RSS en una lista de noticias que podemos iterar para recuperar los datos que necesitamos
     */
    public List<Noticia> parseRss(String rssUrl) throws Exception {
        List<Noticia> noticias = new ArrayList<>();

        // Realizar la conexión HTTP para recuperar el RSS
        URL url = new URL(rssUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            // Parsear el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            // Recupera del documento la lista de elementos <noticia>
            NodeList items = document.getElementsByTagName("item");
            //Recorremos la lista de <noticia> guardando los datos de cada una en la lista de noticias que vamos a devolver
            for (int i = 0; i < items.getLength(); i++) {
                Element noticiaElement = (Element) items.item(i);
                //Se guarda cada elemento como una nueva noticia con titulo, fecha de publicacion, autor y el link de la propia noticia
                Noticia noticia = new Noticia();
                noticia.setTitulo(getTagValue("title", noticiaElement));
                noticia.setFechaPub(getTagValue("pubDate", noticiaElement));
                noticia.setAutor(getTagValue("author", noticiaElement));
                noticia.setLink(getTagValue("link", noticiaElement));

                noticias.add(noticia);
            }
        }
        //Devuelve la nueva lista de noticias parseada
        return noticias;
    }

    //Método que recupera el elemento con la etiqueta seleccionada
    private String getTagValue(String tagName, Element element) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}

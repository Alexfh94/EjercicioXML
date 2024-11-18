import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssParser {
    public List<Noticia> parseRss(String rssUrl) throws Exception {
        List<Noticia> noticias = new ArrayList<>();

        // Realizar la conexión HTTP
        URL url = new URL(rssUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        try (InputStream inputStream = connection.getInputStream()) {
            // Parsear el XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            // Extraer elementos <noticia>
            NodeList items = document.getElementsByTagName("noticia");
            for (int i = 0; i < items.getLength(); i++) {
                Element noticiaElement = (Element) items.item(i);

                Noticia noticia = new Noticia();
                noticia.setTitle(getTagValue("title", noticiaElement));
                noticia.setPubDate(getTagValue("pubDate", noticiaElement));
                noticia.setAuthor(getTagValue("author", noticiaElement));
                noticia.setLink(getTagValue("link", noticiaElement));

                noticias.add(noticia);
            }
        }
        return noticias;
    }

    private String getTagValue(String tagName, Element element) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }
}

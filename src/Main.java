import java.util.List;

public class Main {
    public static void main(String[] args) {
        String rssUrl = "https://www.europapress.es/rss/rss.aspx?buscar=inteligencia-artificial";

        try {
            // Parsear el RSS
            RssParser parser = new RssParser();
            List<Noticia> noticias = parser.parseRss(rssUrl);

            // Mostrar los datos en consola
            System.out.println("Artículos encontrados:");
            for (Noticia item : noticias) {
                System.out.println(item);
            }

            // Generar archivo XML
            String outputFileName = "25-11-2024-list.xml";
            XmlWriter xmlWriter = new XmlWriter();
            xmlWriter.writeRssItemsToXml(noticias, outputFileName);

            System.out.println("\nEl archivo XML ha sido creado: " + outputFileName);
        } catch (Exception e) {
            System.err.println("Error al procesar el RSS: " + e.getMessage());
        }
    }
}

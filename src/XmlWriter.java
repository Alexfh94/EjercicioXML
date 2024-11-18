import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
import java.util.List;

public class XmlWriter {
    public void writeRssItemsToXml(List<Noticia> noticias, String fileName) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // Crear el elemento raíz
        Element root = document.createElement("noticias");
        document.appendChild(root);

        // Añadir elementos
        for (Noticia noticia : noticias) {
            Element itemElement = document.createElement("noticia");

            appendChildWithText(document, itemElement, "title", noticia.getTitle());
            appendChildWithText(document, itemElement, "pubDate", noticia.getPubDate());
            appendChildWithText(document, itemElement, "author", noticia.getAuthor());
            appendChildWithText(document, itemElement, "link", noticia.getLink());

            root.appendChild(itemElement);
        }

        // Escribir el archivo
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //Configura el transformador para que formatee el XML con sangrías (opción "yes").
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }

    private void appendChildWithText(Document document, Element parent, String tagName, String textContent) {
        Element child = document.createElement(tagName);
        child.setTextContent(textContent);
        parent.appendChild(child);
    }
}

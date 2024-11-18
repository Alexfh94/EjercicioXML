import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import java.io.File;
import java.util.List;

public class XmlWriter {
    /*
    * Metodo que recibe la lista de noticias del rss y lo escribe en el nuevo documento xml
    *Parametros:
    * noticias La lista de noticias recuperada del rss
    * fileName nombre del archivo en el que se escribiran los datos
    * */
    public void writeRssItemsToXml(List<Noticia> noticias, String fileName) throws Exception {
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        // Crear el elemento raíz
        Element root = document.createElement("noticias");
        document.appendChild(root);

        // Añadir elementos
        for (Noticia noticia : noticias) {
            Element nodoNoticia = document.createElement("noticia");

            appendChildWithText(document, nodoNoticia, "titulo", noticia.getTitulo());
            appendChildWithText(document, nodoNoticia, "fechaPub", noticia.getFechaPub());
            appendChildWithText(document, nodoNoticia, "autor", noticia.getAutor());
            appendChildWithText(document, nodoNoticia, "link", noticia.getLink());

            root.appendChild(nodoNoticia);
        }

        // Escribir el archivo
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); //Configura el transformador para que formatee el XML con sangrías (opción "yes").
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(fileName));
        transformer.transform(source, result);
    }


    /*
    Metodo que añade un nodo al xml
    Parametros:
        document Documento al que se añade el nodo
        parent El nodo padre al que se añada el nuevo nodo
        tagName El nombre del nodo a añadir
        textContent el texto del nodo
    */
    private void appendChildWithText(Document document, Element parent, String tagName, String textContent) {
        Element child = document.createElement(tagName);
        child.setTextContent(textContent);
        parent.appendChild(child);
    }
}

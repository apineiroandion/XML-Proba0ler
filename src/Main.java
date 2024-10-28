import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        String ruta = "/home/dam/IdeaProjects/XML_Proba0/prueba.xml";
        //Creamos una instancia de la clase XMLInputFactory
        XMLInputFactory factory = XMLInputFactory.newInstance();
        FileReader fileReader = getFileReader(ruta);
        XMLStreamReader reader = getXMLStreamReader(factory, fileReader);
        leerXML(reader);

    }

    /**
     * Metodo que devuelve el FileReader
     * @param ruta del fichero a leer
     * @return instancia de FileReader
     */
    public static FileReader getFileReader(String ruta) {
        try {
            return new FileReader(ruta);
        } catch (FileNotFoundException e) {
            System.out.println("No se puede ler el archivo: "+ ruta + e);
            return null;
        }
    }

    /**
     * Metodo que devuelve el XMLStreamReader
     * @param factory instancia de XMLInputFactory
     * @param fileReader instancia de FileReader
     * @return instancia de XMLStreamReader
     */
    public static XMLStreamReader getXMLStreamReader(XMLInputFactory factory, FileReader fileReader) {
        try {
            return factory.createXMLStreamReader(fileReader);
        } catch (Exception e) {
            System.out.println("Error al crear el XMLStreamReader: " + e);
            return null;
        }
    }

    /**
     * Metodo que lee el archivo XML
     * @param reader instancia de XMLStreamReader
     */
    public static void leerXML(XMLStreamReader reader) {
        try {
            while (reader.hasNext()) {
                int tipo = reader.next();
                switch (tipo) {
                    case XMLStreamReader.START_ELEMENT:
                        System.out.println("Elemento de inicio: " + reader.getLocalName());
                        if (reader.getAttributeCount() > 0) {
                            for (int i = 0; i < reader.getAttributeCount(); i++) {
                                System.out.println("Atributo: " + reader.getAttributeLocalName(i) + " = " + reader.getAttributeValue(i));
                            }
                        }
                        break;
                    case XMLStreamReader.CHARACTERS:
                        System.out.println("Texto: " + reader.getText());
                        break;
                    case XMLStreamReader.END_ELEMENT:
                        System.out.println("Elemento de cierre: " + reader.getLocalName());
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo XML: " + e);
        }
    }
}

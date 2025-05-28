package Adapter.JSON_and_XML;

public class XMLAdapter implements ClientDataReader{
    private XMLReader xmlReader;

    public XMLAdapter(XMLReader xmlReader) {
        this.xmlReader = xmlReader;
    }

    @Override
    public void readClientData() {
        xmlReader.parseXML();
    }
}

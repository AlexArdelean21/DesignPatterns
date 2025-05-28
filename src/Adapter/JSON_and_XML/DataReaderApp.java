package Adapter.JSON_and_XML;

public class DataReaderApp {
    public static void main(String[] args){
        ClientDataReader xmlAdapter = new XMLAdapter(new XMLReader());
        ClientDataReader jsonAdapter = new JSONAdapter(new JSONReader());

        xmlAdapter.readClientData();
        jsonAdapter.readClientData();
    }
}
